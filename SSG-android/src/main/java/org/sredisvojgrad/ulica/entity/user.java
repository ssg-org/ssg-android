package org.sredisvojgrad.ulica.entity;

import com.turbomanage.storm.api.Entity;

/**
 * Created by Home on 7.5.2014..
 */
@Entity
public class user {


    private long userId;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String userCity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }


    public user() {
    }
}
