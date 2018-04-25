/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.niafikra.inaya.domain.security;

import com.niafikra.inaya.domain.HasName;
import com.niafikra.inaya.domain.InayaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A role which contains a collection of permissions
 *
 * @author mbwana jaffari mbura
 */
@Entity
@Table(name = "CR_Role")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends InayaEntity implements HasName {

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    public Role() {

    }


    /**
     * Add a permission to a role
     *
     * @param permission permission to be added
     */

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    /**
     * Removes a permission from a role
     *
     * @param permission permission to be removed
     */
    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }

    @Override
    public String toString() {
        return name;
    }

}
