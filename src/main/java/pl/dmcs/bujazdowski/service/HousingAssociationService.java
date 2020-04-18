package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.ApartmentRepository;
import pl.dmcs.bujazdowski.dao.BillRepository;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.*;
import pl.dmcs.bujazdowski.exception.AuthorizationException;
import pl.dmcs.bujazdowski.exception.NotFoundException;
import pl.dmcs.bujazdowski.exception.UserNotFoundException;
import pl.dmcs.bujazdowski.security.OnlyAdministrator;
import pl.dmcs.bujazdowski.security.OnlyModerator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class HousingAssociationService {

    private final BlockRepository blockRepository;
    private final ApartmentRepository apartmentRepository;
    private final AuthenticationService authenticationService;
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ReportService reportService;

    @Autowired
    public HousingAssociationService(BlockRepository blockRepository,
                                     ApartmentRepository apartmentRepository,
                                     AuthenticationService authenticationService,
                                     BillRepository billRepository,
                                     UserRepository userRepository,
                                     ReportService reportService) {
        this.blockRepository = blockRepository;
        this.apartmentRepository = apartmentRepository;
        this.authenticationService = authenticationService;
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.reportService = reportService;
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

    public Apartment findApartment(Long id) {
        return apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found apartment with id: " + id));
    }

    @Transactional
    public void addBill(Long apartmentId, Bill bill) {
        Apartment apartment = findApartment(apartmentId);
        bill.setApartment(apartment);
        billRepository.save(bill);
    }

    @Transactional
    @OnlyModerator
    public Set<User> findAllOccupants() {
        return userRepository.findAllByRolesContains(authenticationService.findRole(RoleType.USER));
    }

    @Transactional
    @OnlyModerator
    public void updateOccupants(Long apartmentId, Set<String> occupantEmails) {
        Apartment apartment = findApartment(apartmentId);
        Set<User> occupants = userRepository.findAllByEmailIn(occupantEmails);
        apartment.setOccupants(occupants);
        apartmentRepository.save(apartment);
    }

    @Transactional
    public byte[] downloadBillReport(Long billId) {
        /*User user = authenticationService.currentUser();
        Set<Apartment> userApartments = apartmentRepository.findAllByOccupantsContaining(user);
        Bill bill = userApartments.stream()
                .flatMap(apartment -> apartment.getBills().stream())
                .filter(b -> b.getId().equals(billId))
                .findFirst()
                .orElseThrow(() -> new AuthorizationException(user));*/
        return reportService.prepareReport(billRepository.findById(billId).get());
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }
}
