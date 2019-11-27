package pl.dmcs.bujazdowski.domain;

import java.util.Set;

public interface UserAppI {
    void addRole(Role role);

    Set<Role> getRoles();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getTelephone();

    void setTelephone(String telephone);
}
