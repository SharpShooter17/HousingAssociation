package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.Role;
import pl.dmcs.bujazdowski.domain.RoleType;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType type);

    Set<Role> findAllByNameIn(Set<RoleType> names);

}
