package com.example.pavka.memento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class QuestionnaireActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private int page;
    private final int LAST_PAGE = Questions.getLength();
    private final String PAGE = "page";
    private NameFragment nameFragment;
    private DataFragment dataFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView tv;
    private UserHandler userHandler;
    private int[] data = new int[LAST_PAGE];
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        tv = findViewById(R.id.test_count);

        pref = getSharedPreferences(MainActivity.STORE_NAME, MODE_PRIVATE);
        userHandler = new AndroidUserHandler(this);
        try {
            user = userHandler.obtainUser();
        } catch (Exception e) {
            user = userHandler.cleanUser();
        }
        try {
            data = userHandler.obtainUserAnswers();
        } catch (Exception e) {
            data = new int[LAST_PAGE];
        }
        fragmentManager = getSupportFragmentManager();
        page = obtainPage();
        setFragment(page);
    }

    @Override
    protected void onPause() {
        savePage(page);
        try {
            userHandler.saveUserAnswers(data);
        } catch (Exception e) {
            //TODO Exception processing
        }
        super.onPause();

    }

    public int getPage() {
        return page;
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
        tv.setText(page + "/" + LAST_PAGE);
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
                        tv.setText("");
                    }
                    else {
                        dataFragment.setPage(--page);
                        dataFragment.update();
                        tv.setText(page + "/" + LAST_PAGE);
                    }
                }
                break;

            case R.id.button_next:
                if (page == LAST_PAGE) {
                    data[LAST_PAGE - 1] = dataFragment.getData();
                    page = 0;
                    user.setUserData(data);
                    try {
                        userHandler.saveUser(user);
                    } catch (Exception e) {
                        userHandler.cleanUser();
                    }
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if (page == 0) {
                        user.setName(nameFragment.getName());
                        user.setGender(nameFragment.getGender());
                        user.setBirthDate(nameFragment.getBirthDate());
                        try {
                            userHandler.saveUser(user);
                        } catch (Exception e) {
                            user = userHandler.cleanUser();
                        }
                        setDataFragment(++page);
                    }
                    else {
                        data[page - 1] = dataFragment.getData();
                        dataFragment.setPage(++page);
                        dataFragment.update();
                        tv.setText(page + "/" + LAST_PAGE);
                    }

                }
        }
    }



    private void savePage(int page) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PAGE, page);
        editor.apply();

    }
    private int obtainPage() {
        page = pref.getInt(PAGE, 0);
        return page;
    }
}
