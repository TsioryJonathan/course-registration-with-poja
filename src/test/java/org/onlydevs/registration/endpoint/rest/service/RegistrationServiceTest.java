package org.onlydevs.registration.endpoint.rest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onlydevs.registration.endpoint.event.EventProducer;
import org.onlydevs.registration.endpoint.event.model.SendEmailRequested;
import org.onlydevs.registration.model.Course;
import org.onlydevs.registration.model.User;
import org.onlydevs.registration.repository.CourseRepository;
import org.onlydevs.registration.repository.EmailRepository;
import org.onlydevs.registration.repository.RegistrationRepository;
import org.onlydevs.registration.repository.UserRepository;
import org.onlydevs.registration.service.RegistrationService;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
  @Mock private RegistrationRepository registrationRepository;

  @Mock private EmailRepository emailRepository;

  @Mock private UserRepository userRepository;

  @Mock private CourseRepository courseRepository;

  @Mock private EventProducer<SendEmailRequested> eventProducer;

  @InjectMocks private RegistrationService service;

  private final UUID userID = UUID.randomUUID();
  private final UUID courseID = UUID.randomUUID();

  @Test
  void register_success() {
    var mata = User.builder().id(userID).email("mata@cu.te").build();
    var course = Course.builder().id(courseID).title("Love course").build();

    when(userRepository.findById(userID)).thenReturn(Optional.of(mata));
    when(courseRepository.findById(courseID)).thenReturn(Optional.of(course));

    service.register(userID, courseID);

    verify(registrationRepository).save(any());
    verify(emailRepository).save(any());
    verify(eventProducer).accept(anyList());
  }
}
