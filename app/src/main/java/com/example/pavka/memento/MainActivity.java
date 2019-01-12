package com.example.pavka.memento;



import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String USER = "user";
    public final static String TEMP_USER = "tempUser";
    public final static String ANSWERS = "answers";
    private final static int REQ_CODE_Q = 1;
    private final static int REQ_CODE_T = 2;


    public final static String STORE_NAME = "store";


    private UserHandler userHandler;
    private User user;
    private TextView textHello, textCount;
    private boolean isNewUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textHello = findViewById(R.id.textHello);
        textCount = findViewById(R.id.textCount);

        userHandler = new AndroidUserHandler(this);

        try {
            user = userHandler.obtainUser();
            if (user == null) {
                isNewUser = true;
                throw new Exception();
            }
        } catch (Exception e) {
            user = userHandler.cleanUser();
        }

        updateView(user);

    }


    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (v.getId()) {
            case R.id.buttonQ:

                intent = new Intent(this, QuestionnaireActivity.class);
                if (isNewUser) {
                    startActivityForResult(intent, REQ_CODE_T);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder((this));

                    builder.setTitle(R.string.dialog_title_Q).setMessage(R.string.dialog_message_Q).setCancelable(false);
                    builder.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("isShown", false);
                            startActivityForResult(intent, REQ_CODE_Q);
                        }
                    });
                    builder.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent.putExtra("isShown", true);
                            startActivityForResult(intent, REQ_CODE_Q);
                        }
                    });
                    builder.create().show();
                }
                break;
            case R.id.clean:
                user = userHandler.cleanUser();
                updateView(user);
                isNewUser = true;
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
            isNewUser = false;
            if (user == null) {
                isNewUser = true;
                throw new Exception();
            }
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
