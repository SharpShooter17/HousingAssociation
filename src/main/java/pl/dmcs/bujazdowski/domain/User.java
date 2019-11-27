package pl.dmcs.bujazdowski.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.exception.InvalidTokenException;
import pl.dmcs.bujazdowski.exception.TokenExpiredException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.ws.handler.MessageContext;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER_T")
public class User
        extends BaseEntity
        implements UserDetails, UserAppI {

    private final static int daysToTokenExpiration = 7;

    @NotBlank
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Email(message = "Email is not valid!")
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE_T",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public void addRole(Role role) {
        roles.add(role);
    }

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

    private void validateToken(String token) {
        if (this.isTokenExpired()) {
            throw new TokenExpiredException();
        }

        if (this.token == null || !this.token.equals(token) || token.isEmpty()) {
            throw new InvalidTokenException();
        }
    }

    public void activateUser(String token, String hashedPassword) {
        validateToken(token);
        this.hashedPassword = hashedPassword;
        this.token = null;
        this.tokenExpirationDate = null;
        enable();
    }

    public String displayRoles() {
        return roles.stream()
                .map(Role::getName)
                .map(RoleType::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    String token() {
        return this.token;
    }

    LocalDate expirationDate() {
        return this.tokenExpirationDate;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.hashedPassword;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isTokenExpired() {
        return this.tokenExpirationDate != null && LocalDate.now().isAfter(this.tokenExpirationDate);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email;
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

