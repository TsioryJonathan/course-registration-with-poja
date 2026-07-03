package org.onlydevs.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.*;

@Table(name = "\"user\"")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "First Name should not be blank")
  @NotNull
  private String firstName;

  @NotBlank(message = "Last Name should not be blank")
  @NotNull
  private String lastName;

  @Column(unique = true)
  @NotBlank(message = "Username should not be blank")
  @NotNull
  private String userName;

  @Column(unique = true)
  @NotBlank(message = "Email should not be blank")
  @NotNull
  @Email(message = "Email should be valid")
  private String email;
}
