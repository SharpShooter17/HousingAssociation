package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BLOCK_T")
public class Block
        extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "block", fetch = FetchType.EAGER)
    private Set<Apartment> apartments = new HashSet<>();

}
