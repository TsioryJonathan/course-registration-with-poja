package org.onlydevs.registration.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Table(
    name = "\"registration\"",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}))
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Registration {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @CreationTimestamp private Instant createdAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
