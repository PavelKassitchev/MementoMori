package com.example.pavka.memento;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.TextValueSanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String USER = "user";

    private static SharedPreferences sPrefs;
    private final static String STORE_NAME = "store";
    private static Context context;
    private UserHandler userHandler;
    private User user;
    private TextView textHello, textCount;

    public static SharedPreferences getPrefs() {
        return sPrefs;
    }
    public static Context getAppContext() { return context; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPrefs = getSharedPreferences(STORE_NAME, MODE_PRIVATE);
        context = getApplicationContext();



        userHandler = new AndroidUserHandler();

        user = userHandler.obtainUser();

        textHello = (TextView)findViewById(R.id.textHello);
        textCount = (TextView)findViewById(R.id.textCount);
        updateView(user);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonQ:
                //TODO change this mocking method to real one
                user.setName("Павел");
                user.setGender(User.MALE);
                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date birthDate = null;
                try {
                    birthDate = formatter.parse("22.11.1968");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                user.setBirthDate(birthDate);
                updateView(user);

            /*Intent intent = new Intent(this, QuestionnaireActivity.class);
            startActivityForResult(intent, 1);*/
        }
    }

    @Override
    protected void onActivityResult(int request, int response, Intent intent) {

    }

    public void updateView(User user) {

        String invitation = context.getString(R.string.hello) + " " + user.getName() + "!";
        textHello.setText(invitation);
        if (user.getGender()!=0) {
            Date lastDate = userHandler.getLastDate(user);
            DateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            String counter = formatter.format(lastDate);
            textCount.setText(counter);
        }
    }
}
