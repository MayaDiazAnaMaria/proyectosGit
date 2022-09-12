package pet.todotic.demoses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.todotic.demoses.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/hello")
    void hello() {
        mailService.sendHello();
    }

    @GetMapping("/html")
    void html() {
        mailService.sendHtml();
    }

    @GetMapping("/template")
    void template() {
        mailService.sendTemplate();
    }

}