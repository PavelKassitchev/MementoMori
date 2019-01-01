package com.example.pavka.memento;


import java.util.Date;


public class AndroidUser implements User {


    private String name;
    private int gender;
    private Date birthDate;
    private int[] userData;


    public AndroidUser() {
        name = MainActivity.getAppContext().getString(R.string.default_username);
        gender = 0;
        birthDate = new Date();
        userData = new int[Questions.getLength()];
    }

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
