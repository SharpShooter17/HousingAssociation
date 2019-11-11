package pl.dmcs.bujazdowski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.bujazdowski.domain.Block;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface BlockRepository extends JpaRepository<Block, Long> {

    Optional<Block> findById(Long id);

}
