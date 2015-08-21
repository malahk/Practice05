package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.connectors.Connector;
import ua.org.oracle.academy.malahk.models.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public class AddressDAOImpl implements AddressDAO {

    public static final String CREATE_ADDRESS = "insert address set country = ?, street = ?, zipCode = ?, id = ?";
    public static final String GET_ALL = "select * from address";
    public static final String GET_BY_ID = "select * from address where id = ?";
    public static final String UPDATE_ADDRESS = "update address set country = ?, street = ?, zipCode = ? where id = ?";
    public static final String DELETE_ADDRESS = "delete from address where id = ?";

    private static Connection connection;

    public AddressDAOImpl () {
        connection = Connector.getConn();
    }

    @Override
    public boolean create(Address address)
    {
        boolean result = false;

        try {
            PreparedStatement createAddress = connection.prepareStatement(CREATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            createAddress.setString(1, address.getCountry());
            createAddress.setString(2, address.getStreet());
            createAddress.setInt(3, address.getZipCode());
            createAddress.setInt(4, address.getUser().getId());

            result = createAddress.execute();

            ResultSet createdAddressDB = createAddress.getGeneratedKeys();

            while (createdAddressDB.next()) {
                address.setId(createdAddressDB.getInt(1));
            }

            createAddress.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Address> getAll()
    {
        ArrayList<Address> addressList = null;
        try {
            Statement getAll  = connection.createStatement();
            ResultSet allRS = getAll.executeQuery(GET_ALL);
            Address address;
            addressList = new ArrayList<>();

            while (allRS.next()) {

                address = new Address();

                Integer id = allRS.getInt(1);
                String country = allRS.getString(2);
                String street = allRS.getString(3);
                Integer zipCode = allRS.getInt(6);

                address.setId(id);
                address.setCountry(country);
                address.setStreet(street);
                address.setZipCode(zipCode);
                addressList.add(address);
            }

            getAll.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return addressList;
    }

    @Override
    public Address getAddress(Integer id) {
        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);

            ResultSet getAddressRS = getById.executeQuery();
            Address address = null;
            while (getAddressRS.next()) {

                address = new Address();

                String country = getAddressRS.getString(2);
                String street = getAddressRS.getString(3);
                Integer zipcode = getAddressRS.getInt(6);

                address.setId(id);
                address.setCountry(country);
                address.setStreet(street);
                address.setZipCode(zipcode);

            }

            getById.close();
            return address;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Address address) {

        boolean result = false;

        try {

            PreparedStatement updateAddress = connection.prepareStatement(UPDATE_ADDRESS);
            updateAddress.setString(1, address.getCountry());
            updateAddress.setString(2, address.getStreet());
            updateAddress.setInt(3, address.getZipCode());
            updateAddress.setInt(4, address.getId());

            result = updateAddress.execute();
            updateAddress.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(Address address) {

        boolean result = false;

        try {

            PreparedStatement deleteAddress = connection.prepareStatement(DELETE_ADDRESS);
            deleteAddress.setInt(1, address.getId());

            result = deleteAddress.execute();
            deleteAddress.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
