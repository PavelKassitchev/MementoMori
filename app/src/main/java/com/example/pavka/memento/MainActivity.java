package com.example.pavka.memento;



import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String USER = "user";
    private final static int REQ_CODE_Q = 1;
    private final static int REQ_CODE_T = 2;

    private static SharedPreferences sPrefs;
    private final static String STORE_NAME = "store";


    private UserHandler userHandler;
    private User user;
    private TextView textHello, textCount;

    public static SharedPreferences getPrefs() {
        return sPrefs;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPrefs = getSharedPreferences(STORE_NAME, MODE_PRIVATE);

        textHello = findViewById(R.id.textHello);
        textCount = findViewById(R.id.textCount);

        userHandler = new AndroidUserHandler(this);

        try {
            user = userHandler.obtainUser();
        } catch (Exception e) {
            user = userHandler.cleanUser();
        }

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
        Intent intent = null;
        switch (v.getId()) {
            case R.id.buttonQ:
                //TODO change this mocking method to real one
                /*user.setName("Павел");
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
                try {
                    userHandler.saveUser(user);
                } catch (Exception e) {
                    //TODO process Exception and maybe re-organize the method
                    e.printStackTrace();
                }*/

                intent = new Intent(this, QuestionnaireActivity.class);
                startActivityForResult(intent, REQ_CODE_Q);

            break;
            case R.id.clean:
                user = userHandler.cleanUser();
                updateView(user);
                break;
                //TODO buttonT
            case R.id.buttonT:
                intent = new Intent(this, EventTrackerActivity.class);
                startActivityForResult(intent, REQ_CODE_T);
        }

    }

    @Override
    protected void onActivityResult(int request, int response, Intent intent) {

    }

    private void updateView(User user) {

        String invitation = getString(R.string.hello) + " " + user.getName() + "!";
        textHello.setText(invitation);
        if (user.getGender()!=0) {
            Date lastDate = userHandler.getLastDate(user);
            DateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            String counter = formatter.format(lastDate);
            textCount.setText(counter);
        }
        else {
            textCount.setText(getString(R.string.count));
        }
    }
}
