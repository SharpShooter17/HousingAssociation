package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.User;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Set<Apartment> findAllByOccupantsContaining(User occupant);
}
