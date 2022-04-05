package com.revature.YOUnique.models;

public class User {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String password;


    public User() {
    }

    public User(String firstName, String lastName, String dateOfBirth, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;

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

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "First name: " + firstName + "\nLast name: "
                + lastName + "\nDate of Birth: " + getDateOfBirth() + "\nUsername: " + getUsername() + "\nPassword: "
                + getPassword();
    }
}
