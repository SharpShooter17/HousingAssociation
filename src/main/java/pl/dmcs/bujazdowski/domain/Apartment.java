package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APARTMENT_T")
public class Apartment
        extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "BLOCK_ID", referencedColumnName = "ID", nullable = false)
    private Block block;

    @Column(name = "NUMBER", nullable = false)
    private Short number;

    @ManyToMany
    @JoinTable(
            name = "APARTMENT_OCCUPANT_T",
            joinColumns = @JoinColumn(name = "APARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "OCCUPANT_ID"))
    private Set<User> occupants = new HashSet<>();

    @OneToMany(mappedBy = "apartment")
    private Set<Bill> bills = new HashSet<>();

}
