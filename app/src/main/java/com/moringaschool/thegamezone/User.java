package com.moringaschool.thegamezone;

public class User {
    String name, location , phone,email, password, age;

    public User(String name, String location, String phone, String email, String password, String age) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPreference() {
        return phone;
    }

    public void setPreference(String preference) {
        this.phone = preference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
