package cityu.csfyp.tychan289.gestureauthentication;

import android.arch.persistence.room.Room;
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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cityu.csfyp.tychan289.gestureauthentication.AppDatabase.AppDatabase;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT1;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT2;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataT3;

public class RegisterActivity extends AppCompatActivity implements SensorEventListener {

    //Sensor
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Timer timer;

    //UI
    TextView welcome_text;
    Button toggle_button;
    TextView instruction4;

    //Variables
    String username;
    private double gravity[] = new double[3];
    private double linear_acceleration[] = new double[3];
    private double data_values[] = new double[3];
    private boolean timerRunning = false;
    private ArrayList data_x = new ArrayList();
    private ArrayList data_t = new ArrayList();
    private ArrayList data_z = new ArrayList();
    private int trial = 1;
    private AccelDataT1 accelDataT1;
    private AccelDataT2 accelDataT2;
    private AccelDataT3 accelDataT3;
    private double totalAccel;
    private boolean recording = false;

    //Constant
    private static final char x_type = 'x';
    private static final char y_type = 'y';
    private static final char z_type = 'z';
    private static final int trainingLimit = 3;
    private static final String start = "START";
    private static final String stop = "STOP";
    private static final String data_s = "DATA";
    private final double alpha = 0.05 / 0.06; //0.833...

    //Database
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");

        //Get views of activity_register
        welcome_text = (TextView) findViewById(R.id.welcome);
        toggle_button = (Button) findViewById(R.id.register_toggle);
        instruction4 = (TextView) findViewById(R.id.register_instruction5);

        //Change welcoming text from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username_input");
        welcome(username);

        //Get sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Get DB
        db = providesAppDatabase();
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
            toggle_button.setText(start);
            if (trial > trainingLimit) {
                //Prompt login page when finish training
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("username_input", username);
                startActivity(intent);
            }
        } else {
            startTimer();
            toggle_button.setText(stop);
        }
    }

    /*TODO: Program Flow
     * Determine starting point
     * Record acceleration data
     * Store data / extract data
     * Dynamic Time Warping(data)
     * Identify movement directions & time spent
     * */
    //TODO: Evaluation

    //Timer to record the sensor data per 10ms
    private void startTimer() {
        timerRunning = true;

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
        Log.i("TIMER", "Stop timer...");
        Log.i("TRIAL", "Finished trial " + trial);

        //Record three trials respectively
        switch (trial) {
            case 1:
                accelDataT1 = new AccelDataT1(username, (ArrayList<Double>) data_x.clone(), (ArrayList<Double>) data_z.clone(), (ArrayList<Double>) data_t.clone());
                break;
            case 2:
                accelDataT2 = new AccelDataT2(username, (ArrayList<Double>) data_x.clone(), (ArrayList<Double>) data_z.clone(), (ArrayList<Double>) data_t.clone());
                break;
            case 3:
                accelDataT3 = new AccelDataT3(username, (ArrayList<Double>) data_x.clone(), (ArrayList<Double>) data_z.clone(), (ArrayList<Double>) data_t.clone());
                break;
        }

        //Clear stored data
        data_x.clear();
        data_z.clear();
        data_t.clear();

        trial++;
        if (trial == trainingLimit) {
            instruction4.setText("");
        }

        //Finish all trials
        if (trial > trainingLimit) {
            db.accelDataT1Dao().insert(accelDataT1);
            db.accelDataT2Dao().insert(accelDataT2);
            db.accelDataT3Dao().insert(accelDataT3);
        }
        timerRunning = false;
        recording = false;
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
