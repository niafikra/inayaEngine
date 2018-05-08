/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niafikra.inaya.domain.security;

import com.niafikra.inaya.domain.InayaEntity;
import com.niafikra.inaya.domain.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains the authentication details and permissions for
 * The information is read from the database;
 *
 * @author mbwana
 */
@Entity
@Table(name = "CR_User")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends InayaEntity implements UserDetails {

    public static final String SYSTEM_USER_NAME = "inaya";

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Person person;
    private String username = "";
    private String password = "";
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    private boolean blocked;
    private String locale = "";


    @ElementCollection
    @MapKeyColumn(name = "profile_setup_name")
    @Column(name = "profile_setup_value", columnDefinition = "longtext")
    @CollectionTable(name = "cr_user_settings",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private Map<String, String> settings = new HashMap<>();


    public User() {
    }

    public User(String username, String password, String locale, boolean enabled) {
        this.username = username;
        this.password = password;
        this.locale = locale;
        this.enabled = enabled;
    }

    public void setSetting(String key, String setup) {
        settings.put(key, setup);
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getName())))
                .collect(Collectors.toSet());
    }

    /**
     * Check if this authenticated user has a certain permission
     *
     * @param permissionName
     * @return true if user has a specified permission
     */

    public boolean hasPermission(String permissionName) {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.getName().equals(permissionName));

    }

    public boolean hasOneOfThePermissions(String... permissions) {
        return Arrays.stream(permissions).anyMatch(this::hasPermission);
    }

    public String toString() {
        return person.getName();
    }

}
