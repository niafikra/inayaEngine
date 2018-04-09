/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.niafikra.inaya.domain.predefinedData;

import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Predefined data data object
 *
 * @author mbwana jaffari
 */

@Entity
@Table(name = "CR_PredefinedData")
@Data
@EqualsAndHashCode(callSuper = true)
public class PredefinedData extends InayaEntity {
    private Long predefinedDataID;

    @Column(length = 4)
    private String locale;
    private String question = "";
    private String answer = "";
    private String memberOf = "";

    public PredefinedData() {
    }

    public PredefinedData(String question, String answer, String memberOf) {
        this.question = question;
        this.answer = answer;
        this.memberOf = memberOf;
    }

    public PredefinedData(String question, String answer, String memberOf, String locale) {
        this.question = question;
        this.answer = answer;
        this.memberOf = memberOf;
        this.locale = locale;

    }

    @Override
    public String toString() {
        return answer;
    }
}
