package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.connectors.Connector;
import ua.org.oracle.academy.malahk.models.Address;
import ua.org.oracle.academy.malahk.models.Role;
import ua.org.oracle.academy.malahk.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public class UserDAOImpl implements UserDAO {

    public static final String CREATE_USER = "insert users set firstName = ?, lastName = ?, age = ?, login = ?, password = ? ";
    public static final String GET_ALL = "select users.*, address.* from users left join address on users.id = address.id";
    public static final String GET_BY_ID = "select * from users where id = ?";
    public static final String UPDATE_USER = "update users set firstName = ?, lastName = ?, age = ?, login = ?, password = ?, role_id = ? where id = ?";
    public static final String DELETE_USER = "delete from users where id = ?";

    private static Connection connection;
    private static AddressDAOImpl addresImpl;
    private static RoleDAOImpl roleImpl;
    List<User> usersList;

    public UserDAOImpl () {
        connection = Connector.getConn();
        addresImpl = new AddressDAOImpl();
    }

    @Override
    public boolean create(User user) {

        boolean result = false;

        try {

            PreparedStatement createUser = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            createUser.setString(1, user.getFirstName());
            createUser.setString(2, user.getLastName());
            createUser.setInt(3, user.getAge());
            createUser.setString(4, user.getLogin());
            createUser.setString(5, user.getPassword());

            roleImpl.create(user.getRole());

            if(user.getAddress()!= null){
                addresImpl.create(user.getAddress());
            }

            result = createUser.execute();

            ResultSet createdUsersRS = createUser.getGeneratedKeys();
            while (createdUsersRS.next()) {
                user.setId(createdUsersRS.getInt(1));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAll() {

        try {

            Statement getAll  = connection.createStatement();
            ResultSet allRS = getAll.executeQuery(GET_ALL);
            usersList = new ArrayList<>();

            while (allRS.next()) {

                User user = new User();

                Integer id = allRS.getInt(1);
                String firstName = allRS.getString(2);
                String lastName = allRS.getString(3);
                String login = allRS.getString(4);
                String password = allRS.getString(5);
                Integer age = allRS.getInt(6);

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                user.setLogin(login);
                user.setPassword(password);

                Role role = new Role();
                String roleName = allRS.getString(7);
                role.setRoleName(roleName);
                user.setRole(role);

                if (allRS.getInt(8) != 0) {

                    Address address = new Address();
                    String country = allRS.getString(9);
                    String street = allRS.getString(10);
                    Integer zipCode = allRS.getInt(11);
                    address.setId(id);
                    address.setCountry(country);
                    address.setStreet(street);
                    address.setZipCode(zipCode);
                    user.setAddress(address);
                }

                usersList.add(user);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return usersList;

    }

    @Override
    public User getUser(Integer id) {

        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);

            ResultSet getUserRS = getById.executeQuery();
            User user = null;
            while (getUserRS.next()) {

                user = new User();

                String firstName = getUserRS.getString(2);
                String lastName = getUserRS.getString(3);
                String login = getUserRS.getString(4);
                String password = getUserRS.getString(5);
                Integer age = getUserRS.getInt(6);




                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(roleImpl.getRole(id));
                if (user.getAddress()!= null){
                    user.setAddress(addresImpl.getAddress(id));
                }
            }

            return user;


        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(User user) {


        boolean result = false;

        try {

            PreparedStatement updateUser = connection.prepareStatement(UPDATE_USER);
            updateUser.setString(1, user.getFirstName());
            updateUser.setString(2, user.getLastName());
            updateUser.setInt(3, user.getAge());
            updateUser.setString(4, user.getLogin());
            updateUser.setString(5, user.getPassword());
            updateUser.setInt(6, user.getRole().getId());
            updateUser.setInt(7, user.getId());



            int roleId = user.getRole().getId();
            user.getRole().setId(roleId);

            if (user.getAddress() != null) {
                addresImpl.update(user.getAddress());
            }

            result = updateUser.execute();

            return result;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(User user) {

        boolean result = false;

        try {

            PreparedStatement deleteUser = connection.prepareStatement(DELETE_USER);
            deleteUser.setInt(1, user.getId());

            if (user.getAddress() != null) {
                addresImpl.delete(user.getAddress());
            }

            result = deleteUser.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
