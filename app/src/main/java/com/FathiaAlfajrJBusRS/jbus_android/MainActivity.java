package com.FathiaAlfajrJBusRS.jbus_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;  // Import the Menu class
import android.view.MenuInflater;  // Import the MenuInflater class
import android.view.MenuItem;  // Import the MenuItem class
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.FathiaAlfajrJBusRS.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        Button acc_button = findViewById(R.id.button_start);
        acc_button.setOnClickListener(v-> {
            moveActivity(this, AboutMeActivity.class);
        });

        return true;
    }

    private void moveActivity(Context ctx, Class<?> cls){
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
