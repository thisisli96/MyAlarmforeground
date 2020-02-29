package com.example.myalarmforeground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);


        timePicker = findViewById(R.id.timePicker);

        final Intent intent = new Intent(this, MyService.class);
        ServiceCaller(intent);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                ServiceCaller(intent);
            }
        });
    }

    private void ServiceCaller(Intent intent) {
        stopService(intent);

        Integer alarmHours = timePicker.getCurrentHour();
        Integer alarmMinutes = timePicker.getCurrentMinute();

        intent.putExtra("alarmHour", alarmHours);
        intent.putExtra("alarmMinute", alarmMinutes);

        startService(intent);

    }
}
