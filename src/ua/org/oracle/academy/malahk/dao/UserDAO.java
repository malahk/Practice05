package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.models.User;

import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public interface UserDAO {

    boolean create(User user);
    List<User> getAll();
    User getUser(Integer id);
    boolean update(User user);
    boolean delete(User user);
}


