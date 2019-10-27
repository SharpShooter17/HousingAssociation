package pl.dmcs.bujazdowski.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APARTMENT_T")
public class Apartment
        extends BaseEntity {

    @Column(name = "BLOCK_ID", nullable = false)
    private Block block;

    @Column(name = "NUMBER", nullable = false)
    private Short number;

    private Set<User> occupants = new HashSet<>();

    private Set<Bill> bills = new HashSet<>();

}
