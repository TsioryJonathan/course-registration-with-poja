package org.onlydevs.registration.endpoint.rest.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.registration.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
  private final RegistrationService registrationService;

  @PostMapping
  public ResponseEntity<String> registrer(@RequestParam UUID userId, @RequestParam UUID courseId) {
    registrationService.register(userId, courseId);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successfull registration");
  }
}
