package com.example.pavka.memento;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

public class AndroidUserHandler implements UserHandler{



    private SharedPreferences sPrefs;
    private Gson gson = new Gson();
    private Context context;
    private final double CORRECTION_COEFFICIENT = 1.0;

    public AndroidUserHandler(Context context) {

        this.context = context;
        sPrefs = context.getSharedPreferences(MainActivity.STORE_NAME, Context.MODE_PRIVATE);
    }

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
            return new AndroidUser(context);
        }
        return gson.fromJson(userString, AndroidUser.class);
    }

    @Override
    public double calculateLifeSpan(User user) {
      Cocoo cocoo = new AndroidCocoo(user);
        double initialLifeSpan = cocoo.getLifeSpan();
        double currentAge = cocoo.getCurrentAge();
        double ratio = (initialLifeSpan - currentAge) / initialLifeSpan;
        double correction = cocoo.getCorrection();
        return initialLifeSpan + ratio * correction * CORRECTION_COEFFICIENT;
    }

    @Override
    public Date getLastDate(User user) {
        long spanInMillis = user.getBirthDate().getTime() + (long)(calculateLifeSpan(user) * Cocoo.MILLIS_IN_YEAR);
        return new Date(spanInMillis);
    }

    @Override
    public User cleanUser() {
        sPrefs.edit().clear().apply();
        return new AndroidUser(context);
    }
}
