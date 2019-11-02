package pl.dmcs.bujazdowski.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "USER_T")
public class User extends BaseEntity {

    private final static int daysToTokenExpiration = 7;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "TELEPHONE", nullable = false)
    private String telephone;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "TOKEN_EXPIRATION_DATE")
    private LocalDate tokenExpirationDate;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "PASSWORD")
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

