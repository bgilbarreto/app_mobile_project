package com.proyecto.androidjvapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText str1, str2, str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearOnClickEfecty();
        clearOnClickNumber();
        clearOnClickReference();

    }

    private void clearOnClickEfecty () {
        str1 = (EditText) findViewById(R.id.Efecty);
        str1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!str1.getText().equals("")) {
                    str1.setText("");
                }
            }
        });
    }

    private void clearOnClickNumber () {
        str2 = (EditText) findViewById(R.id.Number);
        str2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!str2.getText().equals("")) {
                    str2.setText("");
                }
            }
        });
    }

    private void clearOnClickReference () {
        str3 = (EditText) findViewById(R.id.reference);
        str3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!str3.getText().equals("")) {
                    str3.setText("");
                }
            }
        });
    }

}
