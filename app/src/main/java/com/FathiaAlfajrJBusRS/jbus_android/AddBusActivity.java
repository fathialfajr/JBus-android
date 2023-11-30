package com.FathiaAlfajrJBusRS.jbus_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.FathiaAlfajrJBusRS.jbus_android.model.Account;
import com.FathiaAlfajrJBusRS.jbus_android.model.BaseResponse;
import com.FathiaAlfajrJBusRS.jbus_android.model.Bus;
import com.FathiaAlfajrJBusRS.jbus_android.model.BusType;
import com.FathiaAlfajrJBusRS.jbus_android.model.City;
import com.FathiaAlfajrJBusRS.jbus_android.model.Facility;
import com.FathiaAlfajrJBusRS.jbus_android.model.Renter;
import com.FathiaAlfajrJBusRS.jbus_android.model.Station;
import com.FathiaAlfajrJBusRS.jbus_android.request.BaseApiService;
import com.FathiaAlfajrJBusRS.jbus_android.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBusActivity extends AppCompatActivity {
    private BusType[] busType = BusType.values();
    private List<Station> stationList = new ArrayList<>();
    private City[] cityType = City.values();
    private int selectedDeptStationID;
    private int selectedArrStationID;
    private BusType selectedBusType;
    private Spinner busTypeSpinner;
    private CheckBox CheckBoxAC, CheckBoxWifi, CheckBoxToilet, CheckBoxLCD;
    private CheckBox coolboxCheckBox, lunchCheckBox, baggageCheckBox, electricCheckBox;
    private List<Facility> selectedFacilities = new ArrayList<>();
    private EditText busName, busCapacity, busPrice;
    private BaseApiService mApiService;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        mContext = this;
        mApiService = UtilsApi.getApiService();

        busTypeSpinner = this.findViewById(R.id.bus_type_dropdown);
        ArrayAdapter adBus = new ArrayAdapter(this, android.R.layout.simple_list_item_1, busType);
        adBus.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        busTypeSpinner.setAdapter(adBus);
        busTypeSpinner.setOnItemSelectedListener(busTypeOISL);

        busName = findViewById(R.id.bus_name);
        busCapacity= findViewById(R.id.capacity);
        busPrice = findViewById(R.id.price);
        CheckBoxAC = findViewById(R.id.checkBox_AC);
        CheckBoxWifi = findViewById(R.id.checkBox_wifi);
        CheckBoxToilet = findViewById(R.id.checkBox_toilet);
        CheckBoxLCD = findViewById(R.id.checkBox_LCDTV);
        coolboxCheckBox = findViewById(R.id.checkBox_CoolBox);
        lunchCheckBox = findViewById(R.id.checkBox_lunch);
        baggageCheckBox = findViewById(R.id.checkBox_largeBaggage);
        electricCheckBox = findViewById(R.id.checkBox_electricSocket);

        handleDeparture();
        handleArrival();

        Button busButton = findViewById(R.id.add_bus);
        busButton.setOnClickListener(v->{
            handleAddBus();
        });


    }
    AdapterView.OnItemSelectedListener busTypeOISL = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            selectedBusType = busType[position];
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    protected void handleDeparture() {
        mApiService.getAllStation().enqueue(new Callback<List<Station>>(){
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " +
                            response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                stationList = response.body();
                List <String> namelist = new ArrayList<>();
                for(int i = 0; i < stationList.size();i++)
                {
                    namelist.add(stationList.get(i).stationName);
                }
                Spinner departureSpinner = findViewById(R.id.depart_city_dropdown);
                ArrayAdapter deptBus = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, namelist);
                deptBus.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                departureSpinner.setAdapter(deptBus);


                AdapterView.OnItemSelectedListener deptOISL = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        selectedDeptStationID = stationList.get(position).id;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
                departureSpinner.setOnItemSelectedListener(deptOISL);

            }
            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void handleArrival() {
        mApiService.getAllStation().enqueue(new Callback<List<Station>>(){
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " +
                            response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                stationList = response.body();
                List <String> namelist = new ArrayList<>();
                for(int i = 0; i < stationList.size();i++)
                {
                    namelist.add(stationList.get(i).stationName);
                }
                Spinner departureSpinner = findViewById(R.id.arrival_city_dropdown);
                ArrayAdapter deptBus = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, namelist);
                deptBus.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                departureSpinner.setAdapter(deptBus);


                AdapterView.OnItemSelectedListener deptOISL = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        selectedDeptStationID = stationList.get(position).id;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                };
                departureSpinner.setOnItemSelectedListener(deptOISL);

            }
            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void handleAddBus() {
        String busname = busName.getText().toString();
        String buscapacity = busCapacity.getText().toString();
        String busprice = busPrice.getText().toString();
        int capace = Integer.valueOf(buscapacity);
        int prics = Integer.valueOf(busprice);
        selectedFacilities.clear();
        if (CheckBoxAC.isChecked()) { selectedFacilities.add(Facility.AC);}
        if (CheckBoxWifi.isChecked()) { selectedFacilities.add(Facility.WIFI);}
        if (CheckBoxToilet.isChecked()){ selectedFacilities.add(Facility.TOILET);}
        if (CheckBoxLCD.isChecked()){ selectedFacilities.add(Facility.LCD_TV);}
        if (coolboxCheckBox.isChecked()){ selectedFacilities.add(Facility.COOL_BOX);}
        if (lunchCheckBox.isChecked()){ selectedFacilities.add(Facility.LUNCH);}
        if (baggageCheckBox.isChecked()){ selectedFacilities.add(Facility.LARGE_BAGGAGE);}
        if (electricCheckBox.isChecked()){ selectedFacilities.add(Facility.ELECTRIC_SOCKET);}


        if (busname.isEmpty() || buscapacity.isEmpty() || busprice.isEmpty()) {
            Toast.makeText(mContext, "Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mApiService.create(LoginActivity.loggedAccount.id, busname, capace, selectedFacilities, selectedBusType, prics, selectedDeptStationID, selectedArrStationID).enqueue(new Callback<BaseResponse<Bus>>(){
            @Override
            public void onResponse(Call<BaseResponse<Bus>> call, Response<BaseResponse<Bus>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Application error " +
                            response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                BaseResponse<Bus> res = response.body();
                if (res.success) {
                    Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
                    moveActivity(mContext, ManageBusActivity.class);
                }
                else{
                    Toast.makeText(mContext, res.message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<Bus>> call, Throwable t) {
                Toast.makeText(mContext, "Problem with the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }
}