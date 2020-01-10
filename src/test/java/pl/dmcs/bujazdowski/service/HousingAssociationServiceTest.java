package pl.dmcs.bujazdowski.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.bujazdowski.configuration.ConfigTest;
import pl.dmcs.bujazdowski.domain.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@SpringJUnitConfig(ConfigTest.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HousingAssociationServiceTest {

    private final HousingAssociationService service;

    @Autowired
    HousingAssociationServiceTest(HousingAssociationService service) {
        this.service = service;
    }

    @Test
    @Order(1)
    void createBlocks() {
        service.addBlock(new Address("99-340", "Lipowa", "25", "Krośniewice"));
        List<Block> blocks = service.findAllBlocks();
        assert !blocks.isEmpty();
    }

    @Test
    @Order(2)
    void createApartments() {
        List<Block> allBlocks = service.findAllBlocks();
        allBlocks.forEach(block -> {
            service.addApartment(block.getId(), new Apartment(25));
        });
        assert !service.findAllApartments().isEmpty();
    }

    @Test
    @Order(3)
    @Sql(
            scripts = "/sql/users.sql",
            config = @SqlConfig(transactionMode = ISOLATED)
    )
    void assignOccupants() {
        List<User> users = service.findAllUsers();
        assert !users.isEmpty();
        Set<String> emails = users.stream().map(User::getEmail).collect(Collectors.toSet());
        service.findAllApartments().forEach(apartment -> service.updateOccupants(apartment.getId(), emails));
        assert service.findAllApartments().stream().mapToLong(apartment -> apartment.getOccupants().size()).sum() != 0;
    }

    @Test
    @Order(4)
    void addBills() {
        service.findAllApartments().forEach(apartment ->
                service.addBill(apartment.getId(), new Bill(BillingType.COLD_WATER, LocalDate.now(), 100.0, apartment))
        );
        assert service.findAllApartments().stream().mapToLong(apartment -> apartment.getBills().size()).sum() != 0;
    }
}