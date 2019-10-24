package pl.dmcs.bujazdowski.domain;

import java.util.Objects;

public class Address {

    private String zipCode;
    private String street;
    private String number;
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
