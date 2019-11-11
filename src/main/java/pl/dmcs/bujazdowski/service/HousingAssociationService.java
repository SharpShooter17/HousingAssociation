package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.ApartmentRepository;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Block;
import pl.dmcs.bujazdowski.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HousingAssociationService {

    private final BlockRepository blockRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public HousingAssociationService(BlockRepository blockRepository,
                                     ApartmentRepository apartmentRepository) {
        this.blockRepository = blockRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public Block findBlock(Long blockId) {
        return this.blockRepository.findById(blockId)
                .orElseThrow(() -> new NotFoundException("Can not find block with id: " + blockId));
    }

    public List<Block> findAllBlocks() {
        return this.blockRepository.findAll();
    }

    @Transactional
    public void addBlock(Address address) {
        blockRepository.save(new Block(address));
    }

    @Transactional
    public void addApartment(Long blockId, Apartment apartment) {
        Block block = this.findBlock(blockId);
        block.addApartment(apartment);
        apartmentRepository.save(apartment);
    }

    public List<Apartment> userApartments() {
        return this.apartmentRepository.findAll();
    }

}
