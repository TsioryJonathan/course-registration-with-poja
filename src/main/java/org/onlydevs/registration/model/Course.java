package org.onlydevs.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Table(name = "\"course\"")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "Title should not be blank")
  @NotNull
  private String title;

  @Builder.Default private Instant startDate = Instant.now();

  private Instant endDate;
}
