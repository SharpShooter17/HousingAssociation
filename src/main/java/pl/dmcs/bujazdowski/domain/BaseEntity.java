package pl.dmcs.bujazdowski.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "getId=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
