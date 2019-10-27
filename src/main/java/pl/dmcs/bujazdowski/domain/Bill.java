package pl.dmcs.bujazdowski.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "BILL_T")
public class Bill
        extends BaseEntity {

    @Column(name = "TYPE", nullable = false)
    private BillingType type;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "AMOUNT", nullable = false)
    private Currency amount;

}
