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
    public final static String ANSWRES = "answers";
    private final static int REQ_CODE_Q = 1;
    private final static int REQ_CODE_T = 2;


    public final static String STORE_NAME = "store";


    private UserHandler userHandler;
    private User user;
    private TextView textHello, textCount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.buttonQ:

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
        try {
            user = userHandler.obtainUser();
        } catch (Exception e) {
            user = userHandler.cleanUser();
        }
        updateView(user);

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
