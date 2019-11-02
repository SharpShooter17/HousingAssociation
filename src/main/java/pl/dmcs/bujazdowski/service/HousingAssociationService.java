package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.domain.Block;

import javax.transaction.Transactional;

@Service
public class HousingAssociationService {

    private final BlockRepository blockRepository;

    @Autowired
    public HousingAssociationService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Transactional
    public void addBlock(Address address) {
        blockRepository.save(new Block(address));
    }

}
