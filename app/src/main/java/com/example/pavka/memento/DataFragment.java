package com.example.pavka.memento;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

//incapsulates the main view of QuestionnaireActivity
public class DataFragment extends Fragment {

    //question text
    private TextView textView;
    //each question is shown on a separate page
    private int page;
    //array of questions
    private final String[] QUESTIONS = Questions.getQuestions();
    //yes/no radio buttons
    RadioGroup rGroup;
    private User user;


    public DataFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_data, container, false);
        textView = v.findViewById(R.id.textView);
        rGroup = v.findViewById(R.id.radioGroup);
        page = ((QuestionnaireActivity)getActivity()).getPage();
        user = ((QuestionnaireActivity)getActivity()).getUser();

        update();
        return v;
    }



    public void setPage(int page) {
        this.page = page;
    }

    //sets question and checked radio button (if necessary)
    public void update() {

        textView.setText(QUESTIONS[page - 1]);
        rGroup.clearCheck();

            switch(user.getReply(page -1 )) {
                case -1:
                    rGroup.check(R.id.radioN);
                    break;

                case 1:
                    rGroup.check(R.id.radioY);
                    break;
            }

    }

    //collects user's replies - +1 for positive, -1 for negative
    public int getData() {
        switch(rGroup.getCheckedRadioButtonId()) {
            case R.id.radioY:
                return 1;
            case R.id.radioN:
                return -1;
            default:
                return 0;
        }
    }
}
