package pl.dmcs.bujazdowski.domain;

public abstract class MailTemplate {

    private final String emailAddress;
    private final String subject;

    public MailTemplate(String emailAddress, String subject) {
        this.emailAddress = emailAddress;
        this.subject = subject;
    }

    public abstract String emailBody();

    public String subject() {
        return subject;
    }

    public String emailAddress() {
        return emailAddress;
    }
}
