package com.example.pavka.memento;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class DataFragment extends Fragment {

    private TextView textView;
    private int page;
    private final String[] QUESTIONS = Questions.getQuestions();
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
