package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class UserHandler {


    private static SharedPreferences sPrefs = MainActivity.getsPrefs();
    private static Gson gson = new Gson();

    public static void saveUser(User user)
    {
        String userString = gson.toJson(user);
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString(MainActivity.USER, userString);
        editor.apply();

    }
    public static User getUser() {
        String userString = sPrefs.getString(MainActivity.USER, null);
        //TODO if null?
        return gson.fromJson(userString, User.class);
    }
}
