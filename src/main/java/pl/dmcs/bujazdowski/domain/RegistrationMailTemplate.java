package pl.dmcs.bujazdowski.domain;

import java.time.LocalDate;

public class RegistrationMailTemplate extends MailTemplate {

    private final String token;
    private final LocalDate expirationDate;
    private final Long userId;
    private final String port;
    private final String address;

    public RegistrationMailTemplate(User user, String port, String address) {
        super(user.getEmail(), "Activation token");
        this.token = user.token();
        this.expirationDate = user.expirationDate();
        this.userId = user.getId();
        this.port = port;
        this.address = address;
    }

    @Override
    public String emailBody() {
        return "Welcome to our housing association!\n\n" +
                "Please confirm your account and set new password with this link http://" + address + ":" + port + "/page/user/activate/" +
                this.token + "/" + this.userId +
                "\nThis link will expire at " + expirationDate.toString();
    }
}
