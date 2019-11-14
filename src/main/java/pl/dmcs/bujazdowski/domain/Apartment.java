package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "APARTMENT_T")
public class Apartment
        extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BLOCK_ID", referencedColumnName = "ID", nullable = false)
    private Block block;

    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "APARTMENT_OCCUPANT_T",
            joinColumns = @JoinColumn(name = "APARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "OCCUPANT_ID"))
    private Set<User> occupants = new HashSet<>();

    @OneToMany(mappedBy = "apartment", fetch = FetchType.EAGER)
    private Set<Bill> bills = new HashSet<>();

    public Apartment() {
    }

    public Apartment(Integer number) {
        this.number = number;
    }

    void locatedInBlock(Block block) {
        this.block = block;
    }

    public void addAllOccupants(Collection<User> occupants) {
        this.occupants.addAll(occupants);
    }

    public Block getBlock() {
        return block;
    }

    public Integer getNumber() {
        return number;
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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(block, apartment.block) &&
                Objects.equals(number, apartment.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, number);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "block=" + block +
                ", number=" + number +
                '}';
    }

    public String displayName() {
        return block.displayName() + "/" + number;
    }
}
