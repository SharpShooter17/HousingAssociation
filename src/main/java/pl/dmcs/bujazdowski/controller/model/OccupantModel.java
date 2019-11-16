package pl.dmcs.bujazdowski.controller.model;

import java.util.Set;

public class OccupantModel {

    private Set<String> occupantEmails;

    public OccupantModel() {
    }

    public Set<String> getOccupantEmails() {
        return occupantEmails;
    }

    public void setOccupantEmails(Set<String> occupantEmails) {
        this.occupantEmails = occupantEmails;
    }
}
