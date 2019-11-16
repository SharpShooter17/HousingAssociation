package pl.dmcs.bujazdowski.domain.report;

import java.util.Objects;

public class Column {

    public final Class type;
    public final String property;
    public final String header;

    public Column(Class type, String property, String header) {
        this.type = type;
        this.property = property;
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return Objects.equals(property, column.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property);
    }
}
