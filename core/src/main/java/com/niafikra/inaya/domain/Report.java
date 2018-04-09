package com.niafikra.inaya.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author mbwana on 7/14/15.
 */
@Entity(name = "CR_Report")
@Table(name = "CR_Report")
@Data
@EqualsAndHashCode(callSuper = true)
public class Report extends InayaEntity implements HasName {

    private String name;
    private String path;
    private String permission;
    private String icon;

    @Lob
    private String code;
}
