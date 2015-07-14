package ua.org.oracle.academy.malahk.dao;

import ua.org.oracle.academy.malahk.models.Address;

import java.util.List;

/**
 * Created by Admin on 08.07.2015.
 */
public interface AddressDAO {

    boolean create(Address address);
    List<Address> getAll();
    Address getAddress(Integer id);
    boolean update(Address address);
    boolean delete(Address address);

}
