package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.Role;
import pl.dmcs.bujazdowski.domain.User;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Set<User> findAllByRolesContains(Role role);
}
