package pl.dmcs.bujazdowski.service;

import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.domain.UserCredentials;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private Set<UserCredentials> users = new HashSet<>();

    public AuthenticationService() {
        this.users.add(new UserCredentials("admin@example.com", "password"));
    }

    public Boolean authenticateUser(UserCredentials userCredentials) {
        return users.contains(userCredentials);
    }

}
