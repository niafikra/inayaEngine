/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.niafikra.inaya.domain.security;

import com.niafikra.inaya.domain.HasName;
import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents a permission
 *
 * @author mbwana
 */
@Entity
@Table(name = "CR_Permission")
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends InayaEntity implements HasName {

    /**
     * this is the name of the permission which represents the actual operation that user will
     * need to perform in the system
     */
    @Column(unique = true)
    private String name;
    /**
     * This is the module on which the permission will be acted on
     * the operation to be performed will be as a feature in the module
     */
    private String module;

    /**
     * Check if need to sync with the permission constants
     */
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean sync = true;

    public Permission() {
    }

    /**
     * Create permission from qualified name
     * qualified name is of the form moduleName:permissionName
     * @param qualifiedName
     */
    public Permission(String qualifiedName) {
        this.name = qualifiedName;
        buildFromName();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    /**
     * Set the type and module field from the name of the permission
     * make sure that the name is set first to use this
     */
    public void buildFromName() {
        String[] permData = name.split("[:]");
        module = permData[0];
    }

    @Override
    public String toString() {
        // return only permitted operation
        return name.split("[:]")[1];
    }
}
