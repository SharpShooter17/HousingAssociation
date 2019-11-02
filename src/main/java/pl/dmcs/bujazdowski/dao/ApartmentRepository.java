package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.Apartment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
