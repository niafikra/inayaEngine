package com.niafikra.inaya.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Can be used to store arbitary name value pairs.
 *
 * @author mbwana mbura
 */
@Entity(name = "CR_NameValuePair")
@Table(name = "CR_NameValuePair")
@Data
@EqualsAndHashCode(callSuper = true)
public class NameValuePair extends InayaEntity implements HasName{

    private String name;
    @Lob
    private String value;

    public NameValuePair() {
    }

    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
