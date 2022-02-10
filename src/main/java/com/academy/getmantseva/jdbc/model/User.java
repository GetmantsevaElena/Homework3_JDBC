package com.academy.getmantseva.jdbc.model;

import java.util.Objects;

public class User {

    private int userId;
    private String name;
    private String address;

    public User() {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getName().equals(user.getName()) && getAddress().equals(user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getAddress());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
