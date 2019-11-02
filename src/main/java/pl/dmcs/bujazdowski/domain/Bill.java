package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "BILL_T")
public class Bill
        extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private BillingType type;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "AMOUNT", nullable = false)
    private Currency amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "ID", nullable = false)
    private Apartment apartment;

    public Bill() {
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getAmount() {
        return amount;
    }

    public void setAmount(Currency amount) {
        this.amount = amount;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
