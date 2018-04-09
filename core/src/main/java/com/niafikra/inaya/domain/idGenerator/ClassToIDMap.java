package com.niafikra.inaya.domain.idGenerator;


import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The actual Entity stored in the database
 *
 * @author mbwana jaffari mbura <mbwanajm@gmail.com>
 * Date: 1/29/12
 * Time: 9:28 PM
 */
@Entity
@Table(name = "CR_ClassToIDMap")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassToIDMap extends InayaEntity {
    private String className;
    private Long identity;

    public ClassToIDMap() {
    }

    public ClassToIDMap(Long identity, String className) {
        this.identity = identity;
        this.className = className;
    }
}
