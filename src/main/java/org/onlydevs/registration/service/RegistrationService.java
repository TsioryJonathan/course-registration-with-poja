package org.onlydevs.registration.service;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.onlydevs.registration.endpoint.event.EventProducer;
import org.onlydevs.registration.endpoint.event.model.SendEmailRequested;
import org.onlydevs.registration.file.bucket.BucketComponent;
import org.onlydevs.registration.model.EmailUser;
import org.onlydevs.registration.model.Registration;
import org.onlydevs.registration.repository.CourseRepository;
import org.onlydevs.registration.repository.EmailRepository;
import org.onlydevs.registration.repository.RegistrationRepository;
import org.onlydevs.registration.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final RegistrationRepository registrationRepository;
  private final EmailRepository emailRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final EventProducer<SendEmailRequested> eventProducer;
  private final PdfGenerationService pdfGenerationService;
  private final BucketComponent bucketComponent;

  @Transactional
  @SneakyThrows
  public void register(UUID userId, UUID courseId) {
    var user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () -> new NoSuchElementException("User with id:" + userId + " does not exist."));
    var course =
        courseRepository
            .findById(courseId)
            .orElseThrow(
                () ->
                    new NoSuchElementException("Course with id: " + courseId + " does not exist"));

    var registration = Registration.builder().user(user).course(course).build();
    var savedRegistration = registrationRepository.save(registration);
    emailRepository.save(EmailUser.builder().registration(savedRegistration).build());

    var pdf = pdfGenerationService.generateInvoice(registration);
    var bucketKey = "invoice-" + userId + "-" + courseId + ".pdf";

    var tempFile = File.createTempFile("invoice-", ".pdf");

    try (var fos = new java.io.FileOutputStream(tempFile)) {
      fos.write(pdf);
    }

    bucketComponent.upload(tempFile, bucketKey);

    if (tempFile.exists()) {
      tempFile.delete();
    }

    String url = bucketComponent.presign(bucketKey, Duration.ofHours(1)).toString();

    var event =
        SendEmailRequested.builder()
            .to(user.getEmail())
            .body(
                "Successful registration for course "
                    + course.getTitle()
                    + " you can download your invoice here "
                    + url)
            .build();
    eventProducer.accept(List.of(event));
  }
}
