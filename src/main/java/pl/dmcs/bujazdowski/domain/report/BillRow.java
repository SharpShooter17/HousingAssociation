package pl.dmcs.bujazdowski.domain.report;

import pl.dmcs.bujazdowski.domain.BillingType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BillRow {

    private final Set<Column> columns = new HashSet<>();

    public BillRow() {

    }

    public Set<Column> getColumns() {
        return columns;
    }
}
