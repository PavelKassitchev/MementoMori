package com.example.pavka.memento;

import android.content.Context;

import java.util.Arrays;
import java.util.Date;


public class AndroidUser implements User {


    private String name;
    private int gender;
    private Date birthDate;
    private int[] userData;


    public AndroidUser(Context context) {
        name = context.getString(R.string.default_username);
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
        return new Date(birthDate.getTime());
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int[] getUserData()
    {
        int[] newUserData = Arrays.copyOf(userData, userData.length);
        return newUserData;
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

    @Override
    public int getReply(int i) {
        return userData[i];
    }

    @Override
    public void setReply(int i, int reply) {
        userData[i] = reply;
    }
}
