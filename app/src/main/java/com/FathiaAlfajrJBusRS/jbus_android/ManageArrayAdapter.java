package com.FathiaAlfajrJBusRS.jbus_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.FathiaAlfajrJBusRS.jbus_android.model.Bus;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.FathiaAlfajrJBusRS.jbus_android.model.Bus;
import java.util.ArrayList;
import java.util.List;

public class ManageArrayAdapter extends ArrayAdapter<Bus> {
    public ManageArrayAdapter(@NonNull Context context, List<Bus> list) {
        super(context, 0, list);
    }   @NonNull

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.manage_bus_view, parent, false);
        }

        Bus bus = getItem(position);
        if (bus != null) {
            TextView textView1 = currentItemView.findViewById(R.id.bus01);
            textView1.setText(bus.name);
        }
        return currentItemView;
    }
}