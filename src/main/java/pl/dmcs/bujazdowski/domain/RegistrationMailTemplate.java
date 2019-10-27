package pl.dmcs.bujazdowski.domain;

import java.time.LocalDate;

public class RegistrationMailTemplate extends MailTemplate {

    private final String token;
    private final LocalDate expirationDate;

    public RegistrationMailTemplate(User user) {
        super(user.getEmail());
        this.token = user.token();
        this.expirationDate = user.expirationDate();
    }

    @Override
    public String emailBody() {
        return "Welcome to our housing association!\n\n" +
                "Please confirm your account and set new password with this link http://example.com/confirm?token=" + this.token +
                "\nThis link will expire at " + expirationDate.toString();
    }
}