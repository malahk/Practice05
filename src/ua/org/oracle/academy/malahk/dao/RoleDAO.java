package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.models.Role;

import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public interface RoleDAO {

    boolean create(Role role);
    List<Role> getAll();
    Role getRole(Integer id);
    boolean update(Role role);
    boolean delete(Role role);
}
