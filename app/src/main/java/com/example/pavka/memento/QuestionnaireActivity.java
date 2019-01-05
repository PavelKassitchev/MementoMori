package com.example.pavka.memento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionnaireActivity extends AppCompatActivity {
    private SharedPreferences pref;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        pref = getPreferences(MODE_PRIVATE);
        page = obtainPage();
    }

    public void onClick(View view) {
    }

    private void savePage(int page) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("page", page);

    }
    private int obtainPage() {
        page = pref.getInt("page", 0);
        return page;
    }
}
