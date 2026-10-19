package org.example.springsecurity.models;

import java.util.Set;

public enum Role {

    USER( Set.of(Permissions.READ)),
    ADMIN(Set.of(Permissions.READ,Permissions.DELETE,Permissions.WRITE));

     private final Set<Permissions> permissions;

     Role(Set<Permissions>permissions){

         this.permissions=permissions;
     }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
