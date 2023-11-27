package com.FathiaAlfajrJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView balance;
    private EditText topup;
    private Button button_topup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        username = findViewById(R.id.username);
        String initial = "Fathia";
        username.setText(initial);

        email = findViewById(R.id.email);
        balance = findViewById(R.id.balance);
        topup = findViewById(R.id.topup);
        button_topup = findViewById(R.id.button_topup);

    }
}
