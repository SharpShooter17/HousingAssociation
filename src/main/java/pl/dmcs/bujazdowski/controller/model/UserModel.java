package pl.dmcs.bujazdowski.controller.model;

import pl.dmcs.bujazdowski.domain.Role;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.UserAppI;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserModel implements UserAppI {

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private RoleType[] userRoles = new RoleType[]{RoleType.USER};

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String email, String telephone, RoleType[] userRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.userRoles = userRoles;
    }

    public RoleType[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(RoleType[] userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public void addRole(Role role) {
        List<RoleType> roleTypes = Arrays.stream(userRoles).collect(Collectors.toList());
        roleTypes.add(role.getName());
        userRoles = roleTypes.toArray(new RoleType[roleTypes.size()]);
    }

    @Override
    public Set<Role> getRoles() {
        return Arrays.stream(userRoles)
                .map(Role::new)
                .collect(Collectors.toSet());
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
        this.email = email;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
