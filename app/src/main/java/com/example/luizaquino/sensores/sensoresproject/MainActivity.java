package com.example.luizaquino.sensores.sensoresproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private TextView tvx;
    private TextView tvy;
    private TextView tvz;
    private TextView tvViewDetail;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvx = (TextView) findViewById(R.id.txv_x);
        tvy = (TextView) findViewById(R.id.txv_y);
        tvz = (TextView) findViewById(R.id.txv_z);
        tvViewDetail = (TextView) findViewById(R.id.txv_detail);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Float x = event.values[0];
        Float y = event.values[1];
        Float z = event.values[2];

        tvx.setText("Posição X: " + x.intValue() + " Float: " + x);
        tvx.setText("Posição Y: " + y.intValue() + " Float: " + y);
        tvx.setText("Posição Z: " + z.intValue() + " Float: " + z);

        if(y < 0){
            if(x > 0)
                tvViewDetail.setText("Virando para a ESQUERDA ficando invertido!");
            if(x < 0)
                tvViewDetail.setText("Virando para a DIREITA ficando invertido!");
        }
        else {
            if(x > 0)
                tvViewDetail.setText("Virando para a ESQUERDA!");
            if(x < 0)
                tvViewDetail.setText("Virando para a DIREITA!");  }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
