package pl.dmcs.bujazdowski.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS_T")
public class Address
        extends BaseEntity {

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "CITY", nullable = false)
    private String city;

    public Address(String zipCode, String street, String number, String city) {
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode.equals(address.zipCode) &&
                street.equals(address.street) &&
                number.equals(address.number) &&
                city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, street, number, city);
    }
}
