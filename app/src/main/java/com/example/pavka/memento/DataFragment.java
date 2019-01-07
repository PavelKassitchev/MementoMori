package com.example.pavka.memento;

import android.content.Context;
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
    private final int LAST_PAGE = Questions.getLength();
    private RadioGroup rGroup;

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
        update();
        return v;
    }



    public void setPage(int page) {
        this.page = page;
    }

    public void update() {
        textView.setText(QUESTIONS[page - 1]);
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
