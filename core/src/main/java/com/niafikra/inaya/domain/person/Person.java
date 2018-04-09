package com.niafikra.inaya.domain.person;

import com.niafikra.inaya.domain.HasName;
import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "CR_Person")
@Table(name = "CR_Person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends InayaEntity implements HasName {

    private String firstName;
    private String middleName;
    private String surname;

    /**
     * Name spaced properties for modules need to manipulate extra meta data on the person class
     */
    @ElementCollection
    @MapKeyColumn(name = "person_property_name")
    @Column(name = "person_property_value", columnDefinition = "longtext")
    @CollectionTable(name = "CR_Person_Property",
            joinColumns = @JoinColumn(name = "person_id")
    )
    private Map<String, String> properties = new HashMap<>();

    public String getName() {
        return firstName + " " + middleName + " " + surname;
    }
}
