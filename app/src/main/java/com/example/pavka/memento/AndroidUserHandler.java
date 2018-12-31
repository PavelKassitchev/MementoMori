package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

public class AndroidUserHandler implements UserHandler{



    private SharedPreferences sPrefs = MainActivity.getPrefs();;
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
    public User obtainUser() {
        String userString = sPrefs.getString(MainActivity.USER, null);
        if (userString == null) {
            User user = new AndroidUser();
            user.init();
            return user;
        }
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

    @Override
    public Date getLastDate(User user) {
        long spanInMillis = user.getBirthDate().getTime() + (long)(calculateLifeSpan(user) * LifeSpanCalculator.MILLIS_IN_YEAR);
        return new Date(spanInMillis);
    }
}
