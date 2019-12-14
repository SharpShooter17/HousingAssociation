package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.domain.BillingType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BillingSchedulerService {

    private final HousingAssociationService service;

    @Autowired
    public BillingSchedulerService(HousingAssociationService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 * * * * *")
    public void billingScheduler() {
        List<Apartment> apartments = service.findAllApartments();
        apartments.forEach(this::addBills);
    }

    private void addBills(Apartment apartment) {
        Set<BillingType> missingBillsTypes = apartment.getBills()
                .stream()
                .filter(bill -> LocalDate.now().isEqual(bill.getDate()))
                .map(Bill::getType)
                .collect(Collectors.toSet());
        Stream.of(BillingType.values())
                .filter(type -> !missingBillsTypes.contains(type))
                .map(type -> new Bill(type, LocalDate.now(), 10.0, apartment))
                .forEach(bill -> service.addBill(apartment.getId(), bill));
    }

}
