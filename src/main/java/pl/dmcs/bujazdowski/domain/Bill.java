package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "BILL_T")
public class Bill
        extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private BillingType type;

    @NotNull
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @PositiveOrZero(message = "{error.positive-or-zero}")
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "ID", nullable = false)
    private Apartment apartment;

    public Bill() {
    }

    public Bill(BillingType type,
                LocalDate date,
                @PositiveOrZero(message = "{error.positive-or-zero}") Double amount,
                Apartment apartment) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.apartment = apartment;
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
