package org.onlydevs.registration.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.registration.endpoint.event.EventProducer;
import org.onlydevs.registration.endpoint.event.model.SendEmailRequested;
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

  @Transactional
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
                () -> new NoSuchElementException("Course with id: " + courseId + "does not exist"));
    var registration = Registration.builder().user(user).course(course).build();
    registrationRepository.save(registration);
    var event = SendEmailRequested.builder().to(user.getEmail()).build();
    eventProducer.accept(List.of(event));
  }
}
