package com.example.pavka.memento;


import java.util.Date;


public class AndroidUser implements User {

    public static final int MALE = 1;
    public static final int FEMALE = 2;

    private String name;
    private int gender;
    private Date birthDate;
    private int[] userData;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int[] getUserData() {
        return userData;
    }

    public void setUserData(int[] userData) {
        this.userData = userData;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
