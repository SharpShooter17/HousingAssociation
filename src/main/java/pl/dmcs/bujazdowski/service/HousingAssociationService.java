package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.ApartmentRepository;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.*;
import pl.dmcs.bujazdowski.exception.NotFoundException;
import pl.dmcs.bujazdowski.security.OnlyAdministrator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class HousingAssociationService {

    private final BlockRepository blockRepository;
    private final ApartmentRepository apartmentRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public HousingAssociationService(BlockRepository blockRepository,
                                     ApartmentRepository apartmentRepository,
                                     AuthenticationService authenticationService) {
        this.blockRepository = blockRepository;
        this.apartmentRepository = apartmentRepository;
        this.authenticationService = authenticationService;
    }

    public Block findBlock(Long blockId) {
        return this.blockRepository.findById(blockId)
                .orElseThrow(() -> new NotFoundException("Can not find block with id: " + blockId));
    }

    public List<Block> findAllBlocks() {
        return this.blockRepository.findAll();
    }

    @Transactional
    @OnlyAdministrator
    public void addBlock(Address address) {
        blockRepository.save(new Block(address));
    }

    @Transactional
    @OnlyAdministrator
    public void addApartment(Long blockId, Apartment apartment) {
        Block block = this.findBlock(blockId);
        block.addApartment(apartment);
        apartmentRepository.save(apartment);
    }

    @Transactional
    public Set<Apartment> userApartments() {
        User user = authenticationService.currentUser();
        return this.apartmentRepository.findAllByOccupantsContaining(user);
    }
}
