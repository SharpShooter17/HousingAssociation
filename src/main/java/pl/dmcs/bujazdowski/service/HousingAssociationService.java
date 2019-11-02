package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.ApartmentRepository;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Block;

import javax.transaction.Transactional;

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

    @Transactional
    public void addBlock(Address address) {
        blockRepository.save(new Block(address));
    }

    @Transactional
    public void addApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

}
