package org.onlydevs.registration.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import lombok.RequiredArgsConstructor;
import org.onlydevs.registration.model.Registration;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class PdfGenerationService {

  private final SpringTemplateEngine templateEngine;

  public byte[] generateInvoice(Registration registration) {
    var user = registration.getUser();
    var course = registration.getCourse();

    var context = new Context();
    context.setVariable("registration", registration);
    context.setVariable("user", user);
    context.setVariable("course", course);

    String html = templateEngine.process("invoice", context);

    try (var os = new ByteArrayOutputStream()) {
      var builder = new PdfRendererBuilder();
      builder.withHtmlContent(html, null);
      builder.toStream(os);
      builder.run();
      return os.toByteArray();
    } catch (Exception e) {
      throw new RuntimeException("Failed to generate invoice PDF", e);
    }
  }
}
