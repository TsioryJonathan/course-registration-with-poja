package org.onlydevs.registration.repository;

import java.util.UUID;
import org.onlydevs.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
  boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
