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
    private final int LENGTH = Questions.getLength();
    private boolean isTempUser;

    public AndroidUserHandler(Context context) {

        this.context = context;
        sPrefs = context.getSharedPreferences(MainActivity.STORE_NAME, Context.MODE_PRIVATE);
    }

    public void setTempUser (boolean isTempUser) {
        this.isTempUser = isTempUser;
    }

    @Override
    public void saveUser(User user)
    {
        String userString = gson.toJson(user);

        SharedPreferences.Editor editor = sPrefs.edit();
        String userType = isTempUser? MainActivity.TEMP_USER : MainActivity.USER;
        editor.putString(userType, userString);
        editor.apply();

    }
    @Override
    public User obtainUser() {
        String userType = isTempUser? MainActivity.TEMP_USER : MainActivity.USER;
        String userString = sPrefs.getString(userType, null);
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

    @Override
    public void saveUserAnswers(int[] answers) throws Exception {
        String userString = gson.toJson(answers);

        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString(MainActivity.ANSWERS, userString);
        editor.apply();
    }

    @Override
    public int[] obtainUserAnswers() throws Exception {
        String userString = sPrefs.getString(MainActivity.ANSWERS, null);
        if (userString == null) {
            return new int[LENGTH];
        }
        return gson.fromJson(userString, int[].class);
    }

    @Override
    public User copyUser(User user) {
        User newUser = new AndroidUser(context);
        newUser.setName(user.getName());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setGender(user.getGender());
        newUser.setUserData(user.getUserData());
        return newUser;
    }
}
