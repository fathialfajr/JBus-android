package com.FathiaAlfajrJBusRS.jbus_android;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.FathiaAlfajrJBusRS.jbus_android.model.Account;
import com.FathiaAlfajrJBusRS.jbus_android.model.BaseResponse;
import com.FathiaAlfajrJBusRS.jbus_android.request.BaseApiService;
import com.FathiaAlfajrJBusRS.jbus_android.request.UtilsApi;

import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private BaseApiService mApiService;
    private Context mContext;
    private EditText name, email, password;
    private Button registerButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mContext = this;
        mApiService = UtilsApi.getApiService();

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(v -> {moveActivity(this, LoginActivity.class);});
        registerButton.setOnClickListener(v -> {handleRegister();});
    }

    private void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast (Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    protected void handleRegister() {
        String nameS = usernameEdit.getText().toString();
        String emailS = emailEdit.getText().toString();
        String passwordS = passwordEdit.getText().toString();

        if (nameS.isEmpty() || emailS.isEmpty() || passwordS.isEmpty()) {
            Toast.makeText(mContext, "Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make a registration API call
        mApiService.register(nameS, emailS, passwordS).enqueue(new Callback<BaseResponse<Account>>() {
            @Override
            public void onResponse(Call<BaseResponse<Account>> call, Response<BaseResponse<Account>> response) {
                // Handle potential 4xx & 5xx errors
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the response
                BaseResponse<Account> res = response.body();

                // If registration is successful, finish this activity (back to login activity)
                if (res.success) finish();
                Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse<Account>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
