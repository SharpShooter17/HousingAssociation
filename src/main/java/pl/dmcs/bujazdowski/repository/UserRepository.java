package pl.dmcs.bujazdowski.repository;

import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.exception.UserAlreadyExists;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {

    private Set<User> users = new HashSet<>();

    public void saveUser(User user) {
        if (!users.add(user)) {
            throw new UserAlreadyExists(user);
        }
    }

    public Set<User> findAllUsers() {
        return users;
    }
}
