package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AndroidUserHandler implements UserHandler{



    private SharedPreferences sPrefs;
    private Gson gson = new Gson();


    public void saveUser(User user)
    {
        String userString = gson.toJson(user);
        sPrefs = MainActivity.getsPrefs();
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString(MainActivity.USER, userString);
        editor.apply();

    }
    public User extractUser() {
        sPrefs = MainActivity.getsPrefs();
        String userString = sPrefs.getString(MainActivity.USER, null);
        //TODO if null?
        return gson.fromJson(userString, User.class);
    }


    @Override
    public double calculateLifeSpan(User user) {
        LifeSpanCalculator lifeSpanCalculator = new Cocoo();
        return lifeSpanCalculator.getLifeSpan(user);
    }
}
