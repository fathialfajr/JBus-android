package com.FathiaAlfajrJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.FathiaAlfajrJBusRS.jbus_android.model.Account;
import com.FathiaAlfajrJBusRS.jbus_android.model.BaseResponse;
import com.FathiaAlfajrJBusRS.jbus_android.model.Renter;
import com.FathiaAlfajrJBusRS.jbus_android.request.BaseApiService;
import com.FathiaAlfajrJBusRS.jbus_android.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRenterActivity extends AppCompatActivity {

    private BaseApiService mApiService;
    private Context mContext;
    private EditText companyName, address, phoneNumber;
    private Button renterButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_renter);

        mApiService = UtilsApi.getApiService();
        mContext = this;
        companyName = findViewById(R.id.companyName);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phoneNumber);
        renterButton = findViewById(R.id.renter_button);

        renterButton.setOnClickListener(v->{
            handleRenter();
        });
    }
    private void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    private void viewToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    protected void handleRenter() {
        // handling empty field
        String compName = companyName.getText().toString();
        String compAdd = address.getText().toString();
        String compPhone = phoneNumber.getText().toString();
        if (compName.isEmpty() || compAdd.isEmpty() || compPhone.isEmpty()) {
            Toast.makeText(mContext, "Field cannot be empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mApiService.registerRenter(LoginActivity.loggedAccount.id, compName, compAdd, compPhone).enqueue(new Callback<BaseResponse<Renter>>() {
            @Override
            public void onResponse(Call<BaseResponse<Renter>> call, Response<BaseResponse<Renter>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                BaseResponse<Renter> res = response.body();

                if (res.success) {
                    Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
                    LoginActivity.loggedAccount.company = res.payload;
                    moveActivity(mContext, AboutMeActivity.class); //pindah lagi ke account profile
                }

                Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<BaseResponse<Renter>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}