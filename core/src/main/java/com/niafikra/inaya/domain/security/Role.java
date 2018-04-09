/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.niafikra.inaya.domain.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A role which contains a collection of permissions
 *
 * @author mbwana jaffari mbura
 */
@Entity
@Table(name = "CR_Role")
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String roleName;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();
    @Version
    private Long version;

    public Role() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
        if (permissions == null) permissions = new HashSet<>();
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return roleName;
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

}
