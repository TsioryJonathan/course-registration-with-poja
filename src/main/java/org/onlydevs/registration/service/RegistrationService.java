package org.onlydevs.registration.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.onlydevs.registration.endpoint.event.EventProducer;
import org.onlydevs.registration.endpoint.event.model.SendEmailRequested;
import org.onlydevs.registration.model.Course;
import org.onlydevs.registration.model.Registration;
import org.onlydevs.registration.model.User;
import org.onlydevs.registration.repository.CourseRepository;
import org.onlydevs.registration.repository.EmailRepository;
import org.onlydevs.registration.repository.RegistrationRepository;
import org.onlydevs.registration.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationService {
  private final RegistrationRepository registrationRepository;
  private final EmailRepository emailRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final EventProducer<SendEmailRequested> eventProducer;

  @Transactional
  public void register(UUID userId, UUID courseId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> NoSuchElementException("User with id:" + id + " does not exist."));
    Course course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> NoSuchElementException("Course with id: " + id + "does not exist"));
    Registration registration = Registration.builder().user(user).course(course).build();
    registrationRepository.save(registration);
    var event = SendEmailRequested.builder().to(user.getEmail()).body("Welcome to the course!!!").build();
    eventProducer.accept(List.of(event));
  }
}
