package pl.dmcs.bujazdowski.domain;

public abstract class MailTemplate {

    private final String emailAddress;

    public MailTemplate(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public abstract String emailBody();

    public String emailAddress() {
        return emailAddress;
    }
}
