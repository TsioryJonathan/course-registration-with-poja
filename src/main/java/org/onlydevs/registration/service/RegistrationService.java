package org.onlydevs.registration.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.onlydevs.registration.model.Registration;
import org.onlydevs.registration.repository.CourseRepository;
import org.onlydevs.registration.repository.EmailRepository;
import org.onlydevs.registration.repository.RegistrationRepository;
import org.onlydevs.registration.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationService {
  private final RegistrationRepository registrationRepository;
  private final EmailRepository emailRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;

  @Transactional
  public void register(UUID userId, UUID courseId){
    var user = userRepository.findById(userId).orElseThrow(() -> NoSuchElementException("User with id:" + id +" does not exist."));
    var course = courseRepository.findById(courseId).orElseThrow(() -> NoSuchElementException("Course with id: " + id + "does not exist"));
    var registration = Registration.builder().user(user).course(course).build();
    registrationRepository.save(i)
  }
}
