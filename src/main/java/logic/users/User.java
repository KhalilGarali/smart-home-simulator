/**
 * parent class for all user classes
 * contains the name and the list of permissions for every class
 */

package main.java.logic.users;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    List<Permissions> permissions;
    public String name;

    public User(){
        permissions = new ArrayList<>();
    }

    public Boolean hasPermission(Permissions permission){
        return permissions.contains(permission);
    }
}
