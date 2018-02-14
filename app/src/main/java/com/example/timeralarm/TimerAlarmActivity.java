package com.example.timeralarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimerAlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private FloatingActionButton fab;
    public static final int ALARMTIMER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_alarm);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //INtent personalizado
                Intent intent = new Intent("com.example.timeralarm.intentpersonalizado");
                //ESte pending intent lo va a recibir un broadcast por eso se crea
                //con getBroadcast y no getActivity. EL flag es que si ya hubiera un pending intent que lo
                //actualice con los nuevos datos.
                PendingIntent pendingIntent = PendingIntent.getBroadcast(TimerAlarmActivity.this, ALARMTIMER, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);

                //Registro una alarma teniendo en cuenta el valor introducido en TimePicker
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                //Cuando salte la alarma vas a ejecutar este pending intent. La constante
                //dice que pueden prevalecer otras acciones antes de lanzar este pending intent.
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                finish();
            }
        });
    }
}
