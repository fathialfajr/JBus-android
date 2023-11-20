package com.FathiaAlfajrJBusRS.jbus_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.FathiaAlfajrJBusRS.R;
import com.FathiaAlfajrJBusRS.jbus_android.model.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusArrayAdapter extends ArrayAdapter<Bus> {
    private Context context;
    private List<Bus> busList;

    public BusArrayAdapter(Context context, List<Bus> busList) {

        super(context, R.layout.bus_view, busList);
        this.context = context;
        this.busList = busList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.bus_view, parent, false);
        }

        Bus bus = getItem(position);


        // then return the recyclable view
        return currentItemView;
    }
}