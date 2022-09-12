package pet.todotic.demoses.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    private final static String MY_EMAIL = "cloud@cbit-online.com";

    @Autowired
    private AmazonSimpleEmailServiceClient client;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendHello() {
        Destination destination = new Destination()
                .withToAddresses(MY_EMAIL);

        Message message = new Message()
                .withSubject(new Content("Prueba email"))
                .withBody(new Body(new Content("Hola mundo!!!")));

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withSource(MY_EMAIL)
                .withDestination(destination)
                .withMessage(message);

        client.sendEmail(emailRequest);
    }

    public void sendHtml() {
        Destination destination = new Destination()
                .withToAddresses(MY_EMAIL);

        Context context = new Context();
        context.setVariable("name", "Ana");
        context.setVariable("domain", "https://introduccioncloud.blogspot.com/");
        String html = templateEngine.process("hello", context);

        Content htmlContent = new Content().withData(html);
        Content textContent = new Content().withData("Versión en texto.");
        Body body = new Body().withHtml(htmlContent).withText(textContent);

        Message message = new Message()
                .withSubject(new Content("Prueba HTML"))
                .withBody(body);

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withSource(MY_EMAIL)
                .withDestination(destination)
                .withMessage(message);

        client.sendEmail(emailRequest);
    }

    public void sendTemplate() {
        Destination destination = new Destination()
                .withToAddresses(MY_EMAIL);

        Map<String, String> data = new HashMap<>();
        data.put("name", "Ana");
        data.put("domain", "https://introduccioncloud.blogspot.com/");

        try {
            String templateData = new ObjectMapper().writeValueAsString(data);

            SendTemplatedEmailRequest emailRequest = new SendTemplatedEmailRequest()
                    .withSource(MY_EMAIL)
                    .withDestination(destination)
                    .withTemplate("MiPlantillaSES")
                    .withTemplateData(templateData);

            client.sendTemplatedEmail(emailRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}