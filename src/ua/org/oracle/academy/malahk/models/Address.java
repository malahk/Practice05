package ua.org.oracle.academy.malahk.models;

/**
 * Created by Admin on 08.07.2015.
 */
public class Address {
    
    private Integer id;
    private String country;
    private String street;
    private int zipCode;
    private User user;

    public Address() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getZipCode() != address.getZipCode()) return false;
        if (!getId().equals(address.getId())) return false;
        if (!getCountry().equals(address.getCountry())) return false;
        if (!getStreet().equals(address.getStreet())) return false;
        return getUser().equals(address.getUser());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getZipCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", id=" + id +
                ", street='" + street + '\'' +
                ", zipCode=" + zipCode +
                ", user=" + user +
                '}';
    }
}
