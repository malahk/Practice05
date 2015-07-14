package ua.org.oracle.academy.malahk.models;

import java.util.Set;

/**
 * Created by Admin on 08.07.2015.
 */
public class Role {

    private int id;
    private String roleName;
    private Set<User> users;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (getId() != role.getId()) return false;
        if (!getRoleName().equals(role.getRoleName())) return false;
        return getUsers().equals(role.getUsers());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getRoleName().hashCode();
        result = 31 * result + getUsers().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }


}
