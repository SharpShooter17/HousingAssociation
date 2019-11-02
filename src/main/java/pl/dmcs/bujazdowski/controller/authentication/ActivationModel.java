package pl.dmcs.bujazdowski.controller.authentication;

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
}
