package net.chrisbay.model;

import java.util.Objects;

public abstract class AbstractEntity {

    private final Integer id;
    private static Integer nextId = 1;

    AbstractEntity () {
        this.id = nextId;
        nextId++;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
