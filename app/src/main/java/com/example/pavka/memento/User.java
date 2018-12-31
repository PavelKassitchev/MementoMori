package com.example.pavka.memento;

import java.util.Date;

public interface User {
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    public void init();

    public int getGender();

    public void setGender(int gender);

    public Date getBirthDate();


    public void setBirthDate(Date birthDate);


    public int[] getUserData();

    public void setUserData(int[] userData);

    public String getName();

    public void setName(String name);
}
