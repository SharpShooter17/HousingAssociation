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
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "ID", nullable = false)
    private Apartment apartment;

    public Bill() {
    }

    public String displayName() {
        return apartment.displayName();
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
