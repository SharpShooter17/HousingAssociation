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

}
