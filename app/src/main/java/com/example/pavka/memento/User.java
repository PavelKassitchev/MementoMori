package com.example.pavka.memento;

import java.util.Date;

public interface User {
    int MALE = 1;
    int FEMALE = 2;

    int getGender();

    void setGender(int gender);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    int[] getUserData();

    void setUserData(int[] userData);

    String getName();

    void setName(String name);
}
