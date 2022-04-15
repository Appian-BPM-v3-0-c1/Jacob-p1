package com.revature.YOUnique.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String username;
    private String password;
    private String address;
    private String city;
    private String state;
    private String zip;

    public User() {
    }

    public User(int id, String firstName, String lastName, String dateOfBirth, String email, String username, String password, String address, String city, String state, String zip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }

    public void setZip(String zip) { this.zip = zip; }

    @Override
    public String toString() {
        return "First Name: " + firstName
                + "\nLast Name: " + lastName
                + "\nDate of Birth: " + dateOfBirth
                + "\nEmail: " + email
                + "\nUsername: " + username
                + "\nPassword: " + password
                + "\nAddress: " + address
                + "\nCity: "+ city
                + "\nState: " + state
                + "\nZip: " + zip;
    }
    public String transactionToString() {
        return "Item Location: " + city + ", " + state + " " + zip;
    }
}
