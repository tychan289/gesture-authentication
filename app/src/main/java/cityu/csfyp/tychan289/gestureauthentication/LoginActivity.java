package cityu.csfyp.tychan289.gestureauthentication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cityu.csfyp.tychan289.gestureauthentication.AppDatabase.AppDatabase;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelData;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT1;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT2;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT3;

/**
 * Created by Moonviler
 */

public class LoginActivity extends AppCompatActivity implements SensorEventListener {

    //Sensor
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Timer timer;

    //UI
    TextView welcome_text;
    Button toggle_button;

    //Variables
    String username;
    private double gravity[] = new double[3];
    private double linear_acceleration[] = new double[3];
    private double data_values[] = new double[3];
    private boolean timerRunning = false;
    private boolean result = false;
    private ArrayList data_x = new ArrayList();
    private ArrayList data_t = new ArrayList();
    private ArrayList data_z = new ArrayList();
    private double distanceX, distanceZ, distanceT;
    private AccelDataT1 accelDataT1;
    private AccelDataT2 accelDataT2;
    private AccelDataT3 accelDataT3;
    private double totalAccel;
    private boolean recording = false;

    //Constant
    private static final String start_s = "START";
    private static final String stop_s = "STOP";
    private static final double alpha = 0.05 / 0.06; //0.833...
    private static final String data_s = "DATA";
    private static final String timer_s = "TIMER";
    private static final int DTWthreshold = 15000;

    //Database
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        //Get views of activity_register
        welcome_text = (TextView) findViewById(R.id.welcome);
        toggle_button = (Button) findViewById(R.id.login_toggle);

        //Change welcoming text from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username_input");
        welcome(username);

        //Get sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Get DB
        db = providesAppDatabase();
        accelDataT1 = db.accelDataT1Dao().getAccelData(username);
        accelDataT2 = db.accelDataT2Dao().getAccelData(username);
        accelDataT3 = db.accelDataT3Dao().getAccelData(username);
    }

    private AppDatabase providesAppDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    private void welcome(String username) {
        welcome_text.setText("Welcome \n" + username);
    }

    //Toggle timer
    public void toggleTimer(View v) {
        if (timerRunning) {
            stopTimer();
            toggle_button.setText(start_s);

            //Prompt result page
            Intent intent = new Intent(this, ResultActivity.class);
            if (result) {
                intent.putExtra("result", "Login success!");
            } else {
                intent.putExtra("result", "Login fail!");
            }
            intent.putExtra("distance", "x/" + distanceX + "\n z/" + distanceZ + "\n t/" + distanceT);
            startActivity(intent);
        } else {
            startTimer();
            toggle_button.setText(stop_s);
        }
    }

    //Timer to record the sensor data per 10ms
    private void startTimer() {
        timerRunning = true;
        result = false;
        distanceX = DTWthreshold;
        distanceZ = DTWthreshold;
        distanceT = DTWthreshold;

        Log.i("TIMER", "Start timer...");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                totalAccel = Math.sqrt(linear_acceleration[0] * linear_acceleration[0] + linear_acceleration[1] * linear_acceleration[1] + linear_acceleration[2] * linear_acceleration[2]);
                if (recording) {
                    data_x.add(linear_acceleration[0]);
                    data_z.add(linear_acceleration[2]);
                    data_t.add(totalAccel);
                    Log.i(data_s, linear_acceleration[0] + ", " + linear_acceleration[2] + ", " + totalAccel);
                } else {
                    if (totalAccel > 0.85) {
                        Log.i(data_s, "Start recording data...");
                        data_x.add(linear_acceleration[0]);
                        data_z.add(linear_acceleration[2]);
                        data_t.add(totalAccel);
                        recording = true;
                        Log.i(data_s, linear_acceleration[0] + ", " + linear_acceleration[2] + ", " + totalAccel);
                    }
                }
            }
        }, 0, 10);
    }

    //Stop recording data and turn data into frequency objects
    private void stopTimer() {
        timer.cancel();
        timer.purge();
        Log.i(timer_s, "Stop timer...");

        //Compare the overall time with average time of the three training data
        double avg_time = (accelDataT1.getData_x().size() + accelDataT2.getData_x().size() + accelDataT3.getData_x().size()) / 3;
        double diff = Math.abs(avg_time - data_x.size()) / avg_time;
        Log.i("INFO", "Average time: " + avg_time + ", time difference = " + (Math.abs(avg_time - data_x.size())) + ", difference in percentage = " + diff);

        //Use Dynamic Time Warping to find distance if the average time of training data is similar to test data
        if (diff < 0.15) {
            //Compare training data 1
            distanceX = DynamicTimeWarping.run(accelDataT1.getData_x(), data_x);
            distanceZ = DynamicTimeWarping.run(accelDataT1.getData_z(), data_z);
            distanceT = DynamicTimeWarping.run(accelDataT1.getData_t(), data_t);
            result = this.validateLogin(distanceX, distanceZ, distanceT);
            Log.d("DEBUG", "DTW Score x y z for T1:" + distanceX + ", " + distanceZ + ", " + distanceT + ", result is " + result);

            //Compare training data 2
            if (!result) {
                distanceX = DynamicTimeWarping.run(accelDataT2.getData_x(), data_x);
                distanceZ = DynamicTimeWarping.run(accelDataT2.getData_z(), data_z);
                distanceT = DynamicTimeWarping.run(accelDataT2.getData_t(), data_t);
                result = this.validateLogin(distanceX, distanceZ, distanceT);
                Log.d("DEBUG", "DTW Score x y z for T2:" + distanceX + ", " + distanceZ + ", " + distanceT + ", result is " + result);
            }

            //Compare training data 3
            if (!result) {
                distanceX = DynamicTimeWarping.run(accelDataT3.getData_x(), data_x);
                distanceZ = DynamicTimeWarping.run(accelDataT3.getData_z(), data_z);
                distanceT = DynamicTimeWarping.run(accelDataT3.getData_t(), data_t);
                result = this.validateLogin(distanceX, distanceZ, distanceT);
                Log.d("DEBUG", "DTW Score x y z for T3:" + distanceX + ", " + distanceZ + ", " + distanceT + ", result is " + result);
            }

//            if (result) {
            Log.d("Directions", DirectionAnalysingUnit.run(new AccelData("Login Attempt", data_x, data_z, data_t)));
//            }
        }

        //Log.d("DEBUG", "Breakpoint");

        //Clear stored data
        data_x.clear();
        data_z.clear();
        data_t.clear();

        timerRunning = false;
        recording = false;
    }

    //
    private boolean validateLogin(double distX, double distZ, double distT) {
        if (distX > DTWthreshold || distZ > DTWthreshold || distT > DTWthreshold) {
            return false;
        }
        return true;
    }

    //Sensor is non-stop loading acceleration data
    //Apply low-pass filter to filter the gravity
    @Override
    public final void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT) = 0.05s / (0.05s + 0.01s)
        // with t, the low-pass filter's time-constant t = 0.05
        // and dT, the event delivery rate = 0.01s (10ms)

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]; //0 + (1 - alpha) * acceleration
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

//        data_values[0] = event.values[0];
//        data_values[1] = event.values[1];
//        data_values[2] = event.values[2];

        //Log.i(data_s, "x/" + event.values[0] + ", y/" + event.values[1] + ", z/" + event.values[2]);
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
