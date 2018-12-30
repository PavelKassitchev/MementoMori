package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AndroidUserHandler implements UserHandler{



    private SharedPreferences sPrefs = MainActivity.getsPrefs();
    private Gson gson = new Gson();
    private LifeSpanCalculator lifeSpanCalculator;

    @Override
    public void saveUser(User user)
    {
        String userString = gson.toJson(user);

        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString(MainActivity.USER, userString);
        editor.apply();

    }
    @Override
    public User extractUser() {
        sPrefs = MainActivity.getsPrefs();
        String userString = sPrefs.getString(MainActivity.USER, null);
        //TODO if null?
        return gson.fromJson(userString, User.class);
    }


    @Override
    public double calculateLifeSpan(User user) {
        lifeSpanCalculator = new Cocoo(user);
        double initialLifeSpan = lifeSpanCalculator.getLifeSpan();
        double currentAge = lifeSpanCalculator.getCurrentAge();
        double ratio = (initialLifeSpan - currentAge) / initialLifeSpan;
        double correction = lifeSpanCalculator.getCorrection();
        return initialLifeSpan + ratio * correction;
    }
}
