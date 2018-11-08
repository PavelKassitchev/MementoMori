package com.example.pavka.memento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case(R.id.toast):
                flag = 0;
                break;
            case(R.id.notification):
                flag = 1;
                break;
            case(R.id.button):
                Intent intent = new Intent(this, DelayedMessageService.class);
                intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.button_response));
                intent.putExtra("FLAG", flag);
                startService(intent);
        }

    }
}
