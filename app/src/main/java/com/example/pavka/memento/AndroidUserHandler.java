package com.example.pavka.memento;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

public class AndroidUserHandler implements UserHandler{

    private SharedPreferences sPrefs;
    private Gson gson = new Gson();
    private Context context;
    //a kind of correction coefficient to adjust values
    private final double CORRECTION_COEFFICIENT = 1.0;

    //if true, the UserHandler processes a temporary user, before saving as a real one
    private boolean isTempUser;

    public AndroidUserHandler(Context context) {

        this.context = context;
        sPrefs = context.getSharedPreferences(MainActivity.STORE_NAME, Context.MODE_PRIVATE);
    }

    //setter for isTempUser
    public void setTempUser (boolean isTempUser) {
        this.isTempUser = isTempUser;
    }

    @Override
    //saves the user in SharedPreferences thru Gson. Either as temporary or as real one
    public void saveUser(User user)
    {
        String userString = gson.toJson(user);

        SharedPreferences.Editor editor = sPrefs.edit();
        String userType = isTempUser? MainActivity.TEMP_USER : MainActivity.USER;
        editor.putString(userType, userString);
        editor.apply();

    }
    @Override
    //gets the user from SharedPreferences thru Gson. As a real if isTempUser = false, as a temporary if isTempUser = true
    public User obtainUser() {
        String userType = isTempUser? MainActivity.TEMP_USER : MainActivity.USER;
        String userString = sPrefs.getString(userType, null);

        return gson.fromJson(userString, AndroidUser.class);
    }

    @Override
    public double calculateLifeSpan(User user) {
      Cocoo cocoo = new AndroidCocoo(user);
      //remaining life span
        double initialLifeSpan = cocoo.getLifeSpan();
        //current age
        double currentAge = cocoo.getCurrentAge();
        //a remaining life span ratio
        double ratio = (initialLifeSpan - currentAge) / initialLifeSpan;
        //personal correction on INITIAL life span
        double correction = cocoo.getCorrection();
        //returns corrected life span
        return initialLifeSpan + ratio * correction * CORRECTION_COEFFICIENT;
    }

    @Override
    //returns the Date of death
    public Date getLastDate(User user) {
        long spanInMillis = user.getBirthDate().getTime() + (long)(calculateLifeSpan(user) * Cocoo.MILLIS_IN_YEAR);
        return new Date(spanInMillis);
    }

    @Override
    //cleans saved data and returns a just-born non-gender user
    public User cleanUser() {
        sPrefs.edit().clear().apply();
        return new AndroidUser(context);
    }


}
