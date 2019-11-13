package pl.dmcs.bujazdowski.controller.model;

import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;

import java.util.Objects;

public class NewUserModel {

    private User user = new User();
    private RoleType[] roles = new RoleType[]{RoleType.USER};

    public NewUserModel() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleType[] getRoles() {
        return roles;
    }

    public void setRoles(RoleType[] roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserModel that = (NewUserModel) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, roles);
    }

    @Override
    public String toString() {
        return "NewUserModel{" +
                "user=" + user +
                ", roles=" + roles +
                '}';
    }
}
