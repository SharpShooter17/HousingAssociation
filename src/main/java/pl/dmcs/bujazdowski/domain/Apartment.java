package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APARTMENT_T")
public class Apartment
        extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BLOCK_ID", referencedColumnName = "ID", nullable = false)
    private Block block;

    @Column(name = "NUMBER", nullable = false)
    private Short number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "APARTMENT_OCCUPANT_T",
            joinColumns = @JoinColumn(name = "APARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "OCCUPANT_ID"))
    private Set<User> occupants = new HashSet<>();

    @OneToMany(mappedBy = "apartment", fetch = FetchType.EAGER)
    private Set<Bill> bills = new HashSet<>();

    public Apartment() {
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Set<User> getOccupants() {
        return occupants;
    }

    public void setOccupants(Set<User> occupants) {
        this.occupants = occupants;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }
}
