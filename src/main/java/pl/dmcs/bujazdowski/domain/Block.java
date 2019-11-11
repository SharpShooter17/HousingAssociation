package pl.dmcs.bujazdowski.domain;

import pl.dmcs.bujazdowski.exception.ApartmentAlreadyExists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BLOCK_T")
public class Block
        extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "block", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<Apartment> apartments = new HashSet<>();

    public Block(Address address) {
        this.address = address;
    }

    public Block() {
    }

    public void addApartment(Apartment apartment) {
        apartment.locatedInBlock(this);
        if (!this.apartments.add(apartment)) {
            throw new ApartmentAlreadyExists(apartment.getNumber());
        }
    }

    public Address getAddress() {
        return address;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Block block = (Block) o;
        return Objects.equals(address, block.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Block{" +
                "address=" + address +
                '}';
    }
}
