package org.onlydevs.registration.endpoint.rest.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.onlydevs.registration.endpoint.rest.dto.CreateSubscriptionRequest;
import org.onlydevs.registration.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class RegistrationController {
  private final RegistrationService registrationService;

  @PostMapping("{courseId}/subscribe")
  public ResponseEntity<String> registrer(
      @PathVariable UUID courseId, @RequestBody CreateSubscriptionRequest request) {
    registrationService.register(request.getUserId(), courseId);
    return ResponseEntity.status(HttpStatus.CREATED).body("Successfull registration");
  }
}
