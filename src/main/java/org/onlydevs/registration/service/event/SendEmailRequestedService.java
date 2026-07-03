package org.onlydevs.registration.service.event;

import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.onlydevs.registration.PojaGenerated;
import org.onlydevs.registration.endpoint.event.model.SendEmailRequested;
import org.onlydevs.registration.mail.Email;
import org.onlydevs.registration.mail.Mailer;
import org.springframework.stereotype.Service;

@PojaGenerated
@Service
@AllArgsConstructor
public class SendEmailRequestedService implements Consumer<SendEmailRequested> {
  private final Mailer mailer;

  @SneakyThrows
  @Override
  public void accept(SendEmailRequested sendEmailRequested) {
    InternetAddress recipientAddress = new InternetAddress(sendEmailRequested.getTo());
    mailer.accept(
        new Email(
            recipientAddress, List.of(), List.of(), "REGISTER SUCCESSFUL", "Welcome!!", List.of()));
  }
}
