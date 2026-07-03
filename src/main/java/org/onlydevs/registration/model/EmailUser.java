package org.onlydevs.registration.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "\"email_user\"")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EmailUser {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @CreationTimestamp private Instant createdAt;

  @ManyToOne
  @JoinColumn(name = "registration_id", nullable = false)
  private Registration registration;
}
