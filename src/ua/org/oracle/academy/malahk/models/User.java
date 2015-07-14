package ua.org.oracle.academy.malahk.models;

import java.util.Set;

/**
 * Created by Admin on 08.07.2015.
 */

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String login;
    private String password;
    private Address address;
    private Role role;
    private Set<MusicType> musicTypes;

    public User() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(Set<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getAddress().equals(user.getAddress())) return false;
        if (!getRole().equals(user.getRole())) return false;
        return musicTypes.equals(user.musicTypes);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getRole().hashCode();
        result = 31 * result + musicTypes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "address=" + address +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", musicTypes=" + musicTypes +
                '}';
    }
}
