package com.example.pavka.memento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private User user;
    private Button buttonNext;
    private boolean isShown;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        tv = findViewById(R.id.test_count);
        buttonNext = findViewById(R.id.button_next);
        intent = getIntent();
        isShown = intent.getBooleanExtra("isShown", false);

        pref = getSharedPreferences(MainActivity.STORE_NAME, MODE_PRIVATE);
        userHandler = new AndroidUserHandler(this);

        ((AndroidUserHandler) userHandler).setTempUser(true);
        if (isShown) {
            try {
                user = userHandler.obtainUser();
            } catch (Exception e) {
                user = userHandler.cleanUser();
            }

        } else {
            user = new AndroidUser(this);
        }

        fragmentManager = getSupportFragmentManager();
        page = obtainPage();

        if (page == 0) {
            NameFragment f = (NameFragment)fragmentManager.findFragmentById(R.id.frgmtContainer);

            if (f == null) {
                setNameFragment();

            } else {
                nameFragment = f;
            }

        }
        else {
            setDataFragment(page);
        }
    }

    @Override
    public void onBackPressed() {
        page = 0;
        savePage(page);

        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        savePage(page);
        try {
            userHandler.saveUser(user);
            intent.putExtra("isShown", true);
        } catch (Exception e) {
            //TODO Exception processing
        }
        super.onPause();

    }

    public int getPage() {
        return page;
    }

    public User getUser() {
        return user;
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
                    if (page == 1) {
                        setNameFragment();
                        page--;
                        tv.setText("");
                    }
                    else {
                        if (page == LAST_PAGE) {
                            buttonNext.setText(R.string.buttonNext);
                        }

                        dataFragment.setPage(--page);
                        dataFragment.update();
                        tv.setText(page + "/" + LAST_PAGE);

                        switch(user.getReply(page - 1)) {
                            case -1:
                                dataFragment.rGroup.check(R.id.radioN);
                                break;

                            case 1:
                                dataFragment.rGroup.check(R.id.radioY);
                                break;
                        }
                    }
                }
                break;

            case R.id.button_next:

                if (page == LAST_PAGE) {
                    user.setReply(LAST_PAGE - 1, dataFragment.getData());
                    page = 0;
                    isShown = false;
                    try {
                        userHandler.saveUser(user);
                    } catch (Exception e) {
                        userHandler.cleanUser();
                    }
                    ((AndroidUserHandler)userHandler).setTempUser(false);
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
                        if (page == LAST_PAGE - 1) {
                            buttonNext.setText(R.string.buttonFinish);
                        }
                        user.setReply(page - 1, dataFragment.getData());
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
