package com.FathiaAlfajrJBusRS.jbus_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.FathiaAlfajrJBusRS.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        usernameEdit = findViewById(R.id.username);
        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);

        Button create_button = findViewById(R.id.register_button);
        create_button.setOnClickListener(v -> moveActivity(this, LoginActivity.class));
    }

    private void moveActivity(Context ctx, Class<?> cls) {
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}
