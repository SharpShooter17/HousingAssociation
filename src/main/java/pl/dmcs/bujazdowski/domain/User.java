package pl.dmcs.bujazdowski.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final static int daysToTokenExpiration = 7;

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private boolean enabled;
    private LocalDate tokenExpirationDate;
    private String token;
    private String hashedPassword;

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
        this.tokenExpirationDate = LocalDate.now().plusDays(daysToTokenExpiration);
    }

    public void newPassword(PasswordEncoder passwordEncoder, String plainPassword) {
        this.hashedPassword = passwordEncoder.encode(plainPassword);
    }

    String token() {
        return this.token;
    }

    LocalDate expirationDate() {
        return this.tokenExpirationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpirationDate.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, telephone);
    }
}

