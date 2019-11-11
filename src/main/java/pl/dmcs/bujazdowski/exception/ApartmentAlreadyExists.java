package pl.dmcs.bujazdowski.exception;

public class ApartmentAlreadyExists
        extends ApplicationException {

    public ApartmentAlreadyExists(Integer number) {
        super("Apartment with number" + number + " already exists!");
    }
}
