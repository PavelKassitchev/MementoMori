package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AndroidUserHandler implements UserHandler{


    private static SharedPreferences sPrefs;
    private static Gson gson = new Gson();

    public void saveUser(User user)
    {
        String userString = gson.toJson(user);
        sPrefs = MainActivity.getsPrefs();
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString(MainActivity.USER, userString);
        editor.apply();

    }
    public User getUser() {
        sPrefs = MainActivity.getsPrefs();
        String userString = sPrefs.getString(MainActivity.USER, null);
        //TODO if null?
        return gson.fromJson(userString, User.class);
    }
}
