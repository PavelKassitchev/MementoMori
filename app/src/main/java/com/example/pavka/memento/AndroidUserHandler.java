package com.example.pavka.memento;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

public class AndroidUserHandler implements UserHandler{



    private SharedPreferences sPrefs = MainActivity.getPrefs();;
    private Gson gson = new Gson();
    private Cocoo cocoo;

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

            return user;
        }
        return gson.fromJson(userString, AndroidUser.class);
    }

   //TODO Correction coefficient?
  @Override
    public double calculateLifeSpan(User user) {
        cocoo = new AndroidCocoo(user);
        double initialLifeSpan = cocoo.getLifeSpan();
        double currentAge = cocoo.getCurrentAge();
        double ratio = (initialLifeSpan - currentAge) / initialLifeSpan;
        double correction = cocoo.getCorrection();
        return initialLifeSpan + ratio * correction;
    }

    @Override
    public Date getLastDate(User user) {
        long spanInMillis = user.getBirthDate().getTime() + (long)(calculateLifeSpan(user) * Cocoo.MILLIS_IN_YEAR);
        return new Date(spanInMillis);
    }

    @Override
    public User cleanUser() {
        sPrefs.edit().clear().apply();
        return new AndroidUser();
    }
}
