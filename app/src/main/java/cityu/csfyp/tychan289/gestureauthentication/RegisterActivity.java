package cityu.csfyp.tychan289.gestureauthentication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity implements SensorEventListener {

    //Sensor
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Timer timer;

    //UI
    TextView textView;

    //Variables
    String username;
    private double gravity[] = new double[3];
    private double linear_acceleration[] = new double[3];
    private boolean sensorOn = true;
    private boolean start = true;
    private boolean timerRunning = false;
    private double max_x = 0;
    private double max_y = 0;
    private double max_z = 0;
    private double min_x = 0;
    private double min_y = 0;
    private double min_z = 0;
    private ArrayList data = new ArrayList();
    private int trial = 1;

    //range constant for defining class
    private static final double upper_x = 2;
    private static final double lower_x = -2;
    private static final double upper_y = 2;
    private static final double lower_y = -2;
    private static final double upper_z = 2;
    private static final double lower_z = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get views
        textView = (TextView) findViewById(R.id.welcome_textView);

        //Change welcoming text
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        welcome(username);

        //Get sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.unregisterListener(this);
    }

    private void welcome(String username){
        textView.setText("Welcome " + username);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT) = 0.05s / (0.05s + 0.01s)
        // with t, the low-pass filter's time-constant t = 0.05
        // and dT, the event delivery rate = 0.01s (10ms)

        final double alpha = 0.05 / 0.06; //0.833...

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]; //0 + (1 - alpha) * acceleration
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        //Log.i("ACCELEROMETER VALUES", "x : " + linear_acceleration[0] + " , y : " + linear_acceleration[1] + " , z : " + linear_acceleration[2]);
        //Log.i("ACCELEROMETER VALUES", linear_acceleration[0] + "," + linear_acceleration[1] + "," + linear_acceleration[2]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void toggleSensor(View v){
        if (timerRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        timerRunning = true;
        Log.i("TIMER","Start logging accelerometer values");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Log.i("ACCELEROMETER VALUES", linear_acceleration[0] + "," + linear_acceleration[1] + "," + linear_acceleration[2]);
                AccelerometerData accData = new AccelerometerData(linear_acceleration[0],linear_acceleration[1],linear_acceleration[2]);
                data.add(accData);

                // sx = (x + 2) / (2 + |-2|) * (4 / 20)
                // sy = (y + 1) / (1 + |-1|) * (2 / 20)
                // sz = (z + 1) / (1 + |-1|) * (2 / 20)
                if (linear_acceleration[0] > max_x) {max_x = linear_acceleration[0];}
                if (linear_acceleration[1] > max_y) {max_y = linear_acceleration[0];}
                if (linear_acceleration[2] > max_z) {max_z = linear_acceleration[0];}

                if (linear_acceleration[0] < min_x) {min_x = linear_acceleration[0];}
                if (linear_acceleration[1] < min_y) {min_y = linear_acceleration[0];}
                if (linear_acceleration[2] < min_z) {min_z = linear_acceleration[0];}
            }
        }, 0, 10);
    }

    private void stopTimer(){
        timer.cancel();
        timer.purge();
        Log.i("ACCELEROMETER VALUES","Maximum: " + max_x + "," + max_y + "," + max_z);
        Log.i("ACCELEROMETER VALUES","Minimum: " + min_x + "," + min_y + "," + min_z);
        Log.i("TIMER","Stop logging accelerometer values");
        timerRunning = false;
    }
}
