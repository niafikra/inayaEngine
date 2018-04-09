/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.niafikra.inaya.domain.security;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class represents a permission
 *
 * @author mbwana
 */
@Entity
@Table(name = "CR_Permission")
public class Permission implements Serializable {


    public static final String EDIT_PERSON_INFORMATION = "engine:edit:person information";
    /**
     * Representation of an edit permission type
     */
    public final static String EDIT = "edit";
    /**
     * Representation of a view permission type
     */
    public final static String VIEW = "view";
    /**
     * Representation of other permissions
     */
    public final static String MISC = "misc";
    public static final String CHANGE_THEME = "engine:edit:theme";
    public static final String VIEW_GROUPS = "engine:view:groups";

    @Id
    @GeneratedValue
    private Long id;
    /**
     * this is the name of the permission which represents the aactual operation that user will
     * need to perform in the system
     */
    @Column(unique = true)
    private String permissionName;
    /**
     * This is the module on which the permission will be acted on
     * the operation to be performed will be as a feature in the module
     */
    private String module;
    /**
     * This is a type of permission that user will have as per operation to be performe
     * The type can either be EDIT com VIEW
     */
    private String type = "";
    @Version
    private Long version;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean sync = true;

    public Permission() {
    }

    public Permission(String permissionName) {
        this.permissionName = permissionName;
        buildFromName();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String name) {
        this.permissionName = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionName != null ? permissionName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof String) {
            return (permissionName.contentEquals((String) object));
        } else if (object instanceof Permission) {
            Permission other = (Permission) object;
            if (other.getId() != null && id != null) return id.equals(other.getId());
            return ((other.getPermissionName().contentEquals(this.permissionName))
                    && (other.getModule().contentEquals(this.module))
                    // &&(other.getType().contentEquals(this.type))
            );
        } else {
            return false;
        }
    }

    /**
     * Set the type and module field from the name of the permission
     * make sure that the name is set first to use this
     */
    public void buildFromName() {
        String[] permData = permissionName.split("[:]");
        module = permData[0];
        type = permData[1];
    }

    @Override
    public String toString() {
        // return only permitted operation
        return permissionName.split("[:]")[2];
    }
}
