package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.domain.MailTemplate;

@Service
public class MailSenderService {

    private final JavaMailSender emailSender;

    @Autowired
    public MailSenderService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMail(MailTemplate mailTemplate) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(mailTemplate.emailAddress());
        mailMessage.setSubject(mailTemplate.subject());
        mailMessage.setText(mailTemplate.emailBody());

        emailSender.send(mailMessage);
    }

}
