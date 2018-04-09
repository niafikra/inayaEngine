package com.niafikra.inaya.domain;

import com.niafikra.inaya.domain.person.Person;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The base entity for all inaya entities. They should inherit from this.
 * @author mbwana mbura
 */
@Data
@MappedSuperclass
public class InayaEntity implements Serializable {
    @GeneratedValue
    @Id
    private Long id;
    @Version
    private Long version;

    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;
    private Person createdBy;
    private Person updatedBy;

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }

        return 31 + id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (id == null) {
            // New entities are only equal if the instance if the same
            return super.equals(other);
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof InayaEntity)) {
            return false;
        }
        return id.equals(((InayaEntity) other).id);
    }

    public String getIdentifier() {
        return this.getClass().getCanonicalName() + ":" + id;
    }
}
