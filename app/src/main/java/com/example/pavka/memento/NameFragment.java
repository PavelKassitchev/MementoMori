package com.example.pavka.memento;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NameFragment extends Fragment {

    private EditText editName;
    private RadioGroup genderGroup;
    private EditText editDate;


    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_name, container, false);
        editName = v.findViewById(R.id.editName);
        genderGroup = v.findViewById(R.id.genderGroup);
        editDate = v.findViewById(R.id.editDate);
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public String getName() {

        return editName.getText().toString();
    }

    public int getGender() {
        switch(genderGroup.getCheckedRadioButtonId()) {
            case R.id.radioF:
                return User.FEMALE;
            case R.id.radioM:
                return User.MALE;
            default:
                return 0;
        }
    }

    public Date getBirthDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = formatter.parse(editDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
