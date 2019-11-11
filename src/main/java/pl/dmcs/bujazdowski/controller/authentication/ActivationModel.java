package pl.dmcs.bujazdowski.controller.authentication;

import java.util.Objects;

public class ActivationModel {

    private Long userId;
    private String token;
    private String password;

    public ActivationModel(Long userId, String token, String password) {
        this.userId = userId;
        this.token = token;
        this.password = password;
    }

    public ActivationModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationModel that = (ActivationModel) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, token);
    }
}
