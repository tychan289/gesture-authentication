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

import cityu.csfyp.tychan289.gestureauthentication.roomEntity.Frequency;

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
    private boolean timerRunning = false;
    private ArrayList data_x = new ArrayList();
    private ArrayList data_y = new ArrayList();
    private ArrayList data_z = new ArrayList();
    private int trial = 1;
    private Frequency frequencyX, frequencyY, frequencyZ;
    private Frequency frequencyA, frequencyB, frequencyC;
    private Frequency resultX, resultY, resultZ;

    //Constant
    private static final char x_type = 'x';
    private static final char y_type = 'y';
    private static final char z_type = 'z';
    private static final int trainingLimit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get views of activity_register
        textView = (TextView) findViewById(R.id.welcome_textView);

        //Change welcoming text from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        welcome(username);

        //Get sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void welcome(String username) {
        textView.setText("Welcome \n" + username);
    }

    //Toggle timer
    public void toggleSensor(View v) {
        //Finish training ...
        if (trial >= trainingLimit){

        }

        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    //Timer to record the sensor data per 10ms
    private void startTimer() {
        timerRunning = true;

        Log.i("TIMER", "Start recording accelerometer values");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Log.i("ACCELEROMETER VALUES", linear_acceleration[0] + "," + linear_acceleration[1] + "," + linear_acceleration[2]);
                data_x.add(linear_acceleration[0]);
                data_y.add(linear_acceleration[1]);
                data_z.add(linear_acceleration[2]);
            }
        }, 0, 10);
    }

    //Stop recording data and turn data into frequency objects
    private void stopTimer() {
        timer.cancel();
        timer.purge();
        Log.i("TIMER", "Stop recording accelerometer values");

        //Turn data into frequency objects
        if (trial > trainingLimit){ //save if finish training
            frequencyA = Classification.classify(data_x, x_type);
            frequencyB = Classification.classify(data_y, y_type);
            frequencyC = Classification.classify(data_z, z_type);

            //PROBLEM BUGGGGG...........
            resultX = Frequency.compare(frequencyA,frequencyX);
            resultY = Frequency.compare(frequencyB,frequencyY);
            resultZ = Frequency.compare(frequencyC,frequencyZ);
        } else if (trial == 1){ //new if trial < 1
            frequencyX = Classification.classify(data_x, x_type);
            frequencyY = Classification.classify(data_y, y_type);
            frequencyZ = Classification.classify(data_z, z_type);
        } else if (trial > 1){ //average if trial > 1
            frequencyX = Classification.classify(data_x, x_type, frequencyX);
            frequencyY = Classification.classify(data_y, y_type, frequencyY);
            frequencyZ = Classification.classify(data_z, z_type, frequencyZ);
        }
        Log.i("TRIAL","Finished trial " + trial);

        //Clear stored data
        data_x.clear();
        data_y.clear();
        data_z.clear();

        trial++;
        timerRunning = false;
    }

    //Sensor is non-stop loading acceleration data
    //Apply low-pass filter to filter the gravity
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
    }

    /* Unused method */
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
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
    /* Unused method */
}
