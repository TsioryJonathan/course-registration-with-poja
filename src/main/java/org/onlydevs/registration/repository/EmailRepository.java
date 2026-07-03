package org.onlydevs.registration.repository;

import java.util.UUID;
import org.onlydevs.registration.model.EmailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailUser, UUID> {}
