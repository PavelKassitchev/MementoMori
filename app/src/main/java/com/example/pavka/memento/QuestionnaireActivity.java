package com.example.pavka.memento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class QuestionnaireActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private int page;
    private final int LAST_PAGE = Questions.getLength();
    private NameFragment nameFragment;
    private DataFragment dataFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        pref = getPreferences(MODE_PRIVATE);
        fragmentManager = getSupportFragmentManager();
        page = obtainPage();
        setFragment(page);
    }

    @Override
    protected void onDestroy() {
        savePage(page);
        super.onDestroy();

    }

    private void setFragment(int page) {

        if (page == 0) {
            setNameFragment();
        }
        else {
            setDataFragment(page);
        }

    }

    private void setNameFragment() {
        nameFragment = new NameFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmtContainer, nameFragment);
        fragmentTransaction.commit();
    }

    private void setDataFragment(int page) {
        dataFragment = new DataFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmtContainer, dataFragment);
        fragmentTransaction.commit();
        dataFragment.setPage(page);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (page == 0) {
                    finish();
                }
                else {
                    if (page ==1) {
                        setNameFragment();
                        page--;
                    }
                    else {
                        dataFragment.setPage(--page);
                        dataFragment.update();
                    }
                }
                break;

            case R.id.button_next:
                if (page == LAST_PAGE) {
                    page = 0;
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if (page == 0) {
                        setDataFragment(++page);
                    }
                    else {
                        dataFragment.setPage(++page);
                        dataFragment.update();
                    }

                }
        }
    }

    private void savePage(int page) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("page", page);
        editor.apply();

    }
    private int obtainPage() {
        page = pref.getInt("page", 0);
        return page;
    }
}
