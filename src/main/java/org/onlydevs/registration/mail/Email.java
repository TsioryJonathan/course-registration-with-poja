package org.onlydevs.registration.mail;

import jakarta.mail.internet.InternetAddress;
import java.io.File;
import java.util.List;
import org.onlydevs.registration.PojaGenerated;

@PojaGenerated
public record Email(
    InternetAddress to,
    List<InternetAddress> cc,
    List<InternetAddress> bcc,
    String subject,
    String htmlBody,
    List<File> attachments) {}
