package com.niafikra.inaya.domain.person;

import com.niafikra.inaya.domain.HasName;
import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * This is a logical collection of people with a common aspect
 *
 * @author Boniface Chacha
 * @email boniface.chacha@niafikra.com,bonifacechacha@gmail.com
 * @date 11/14/15
 */
@Entity(name = "CR_Group")
@Table(name = "CR_Group")
@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends InayaEntity implements HasName{
    public static final String DEFAULT = "DEFAULT";

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private String type = DEFAULT;

    @Lob
    private byte[] photo;

    @ManyToMany
    private Set<Person> people = new HashSet<>();

    @ElementCollection
    @MapKeyColumn(name = "group_property_name")
    @Column(name = "group_property_value", columnDefinition = "longtext")
    @CollectionTable(name = "CR_Group_Property",
            joinColumns = @JoinColumn(name = "group_id")
    )
    private Map<String, String> properties = new HashMap<>();

    public Group setProperty(String propertyName, String propertyValue) {
        properties.put(propertyName, propertyValue);
        return this;
    }

    public String getProperty(String propertyName){
        return properties.get(propertyName);
    }

    @Override
    public String toString() {
        return name;
    }
}
