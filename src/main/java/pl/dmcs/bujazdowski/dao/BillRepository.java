package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmcs.bujazdowski.domain.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
