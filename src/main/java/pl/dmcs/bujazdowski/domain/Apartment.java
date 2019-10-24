package pl.dmcs.bujazdowski.domain;

import java.util.HashSet;
import java.util.Set;

public class Apartment {

    private Short number;
    private Set<Occupant> occupants = new HashSet<>();
    private Set<Bill> bills = new HashSet<>();

}
