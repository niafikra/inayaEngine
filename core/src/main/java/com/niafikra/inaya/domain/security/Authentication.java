/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niafikra.inaya.domain.security;

import com.niafikra.inaya.domain.InayaEntity;
import com.niafikra.inaya.domain.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains the authentication details and permissions for
 * The information is read from the database;
 *
 * @author mbwana
 */
@Entity
@Table(name = "CR_Authentication")
@Data
@EqualsAndHashCode(callSuper = true)
public class Authentication extends InayaEntity {

    public static final String SYSTEM_USER_NAME = "inaya";

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Person person;
    private String loginName = "";
    private String password = "";

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean blocked;
    private String locale = "";


    @ElementCollection
    @MapKeyColumn(name = "profile_setup_name")
    @Column(name = "profile_setup_value", columnDefinition = "longtext")
    @CollectionTable(name = "authentication_profile_setup",
            joinColumns = @JoinColumn(name = "authentication_id")
    )
    private Map<String, String> profileSetup = new HashMap<>();


    public Authentication() {
    }

    public Authentication(String loginName, String password, String locale, boolean blocked) {
        this.loginName = loginName;
        this.password = password;
        this.locale = locale;
        this.blocked = blocked;
    }

    public void setProfileSetup(String key, String setup) {
        profileSetup.put(key, setup);
    }

    public Set<Role> getRoles() {
        if (roles == null) roles = new HashSet<>();
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Check if this authenticated user has a certain permission
     *
     * @param permissionName
     * @return true if user has a specified permission
     */

    public boolean hasPermission(String permissionName) {
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                if (permission.getPermissionName().trim().replace(" ", "").equals(permissionName.trim().replace(" ", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasOneOfThePermissions(String... permissions) {
        for (String permission : permissions) {
            if (hasPermission(permission)) return true;
        }
        return false;
    }

    public String toString() {
        return person.getName();
    }

}
