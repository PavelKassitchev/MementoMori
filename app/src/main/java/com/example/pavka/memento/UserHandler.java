package com.example.pavka.memento;

import com.google.gson.Gson;

public class UserHandler {

    private static Gson gson = new Gson();

    public static String saveUserData(User user)
    {
        return gson.toJson(user);
    }
    public static User loadUserData(String user) {
        return gson.fromJson(user, User.class);
    }
}
