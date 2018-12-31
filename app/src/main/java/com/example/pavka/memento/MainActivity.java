package com.example.pavka.memento;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String USER = "user";

    private static SharedPreferences sPrefs;
    private final static String STORE_NAME = "store";
    private static Context context;

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
            Intent intent = new Intent(this, QuestionnaireActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int request, int response, Intent intent) {

    }
}
