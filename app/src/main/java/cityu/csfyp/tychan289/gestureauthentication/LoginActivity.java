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
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.FrequencyZ;

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
    private boolean result;
    private ArrayList data_x = new ArrayList();
    private ArrayList data_y = new ArrayList();
    private ArrayList data_z = new ArrayList();
    private FrequencyX frequencyX, frequencyA;
    private FrequencyY frequencyY, frequencyB;
    private FrequencyZ frequencyZ, frequencyC;
    private double distanceX, distanceY, distanceZ;
    private double history, current, change;
    private boolean start_b = false;

    //Constant
    private static final char x_type = 'x';
    private static final char y_type = 'y';
    private static final char z_type = 'z';
    private static final String start_s = "START";
    private static final String stop_s = "STOP";
    private final double alpha = 0.05 / 0.06; //0.833...
    private static final String right_s = "RIGHT";
    private static final String left_s = "LEFT";
    private static final String data_s = "DATA";
    private static final String timer_s = "TIMER";

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
            intent.putExtra("distance", "x/" + distanceX + "\n y/" + distanceY + "\n z/" + distanceZ);
            startActivity(intent);
        } else {
            startTimer();
            toggle_button.setText(stop_s);
        }
    }

    //Timer to record the sensor data per 10ms
    private void startTimer() {
        timerRunning = true;

        Log.i(timer_s, "Start timer...");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (start_b) {
                    data_x.add(data_values[0]);
                    data_y.add(data_values[1]);
                    data_z.add(data_values[2]);
                } else {
                    if (change > 0.15) {
                        start_b = true;
                        data_x.add(data_values[0]);
                        data_y.add(data_values[1]);
                        data_z.add(data_values[2]);
                        Log.i(data_s, "Start recording data...");
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

        //Use Dynamic Time Warping to find distance
        //DynamicTimeWarping.run()
        double scorex = DynamicTimeWarping.run(TypeConvertors.toArrayListDouble(db.accelDataXDao().getAccelData(username).getData()), data_x);
        double scorey = DynamicTimeWarping.run(TypeConvertors.toArrayListDouble(db.accelDataYDao().getAccelData(username).getData()), data_y);
        double scorez = DynamicTimeWarping.run(TypeConvertors.toArrayListDouble(db.accelDataZDao().getAccelData(username).getData()), data_z);

        Log.d("DEBUG", "DTW Score x y z :" + scorex + ", " + scorey + ", " + scorez);

//        //Turn testing data into frequency objects
//        frequencyA = (FrequencyX) Classification.classify(data_x, x_type, username);
//        frequencyB = (FrequencyY) Classification.classify(data_y, y_type, username);
//        frequencyC = (FrequencyZ) Classification.classify(data_z, z_type, username);
//
//        //Get stored object
//        frequencyX = db.frequencyXDao().getFrequencyX(username);
//        frequencyY = db.frequencyYDao().getFrequencyY(username);
//        frequencyZ = db.frequencyZDao().getFrequencyZ(username);
//
//        //Find Euclidean Distance
//        distanceX = Frequency.euclideanDistance(frequencyX, frequencyA);
//        distanceY = Frequency.euclideanDistance(frequencyY, frequencyB);
//        distanceZ = Frequency.euclideanDistance(frequencyZ, frequencyC);
//        Log.d("EDist", "x: " + distanceX + ", y: " + distanceY + ", z: " + distanceZ);
//        result = validateLogin(distanceX, distanceY, distanceZ);

        //Log.d("DEBUG", "Breakpoint");

        //Clear stored data
        data_x.clear();
        data_y.clear();
        data_z.clear();

        timerRunning = false;
    }

    //
    private boolean validateLogin(double distX, double distY, double distZ) {
        if (distX > 30 || distY > 30 || distZ > 30) {
            return false;
        }
        return true;
    }

    //Sensor is non-stop_s loading acceleration data
    //Apply low-pass filter to filter the gravity
    @Override
    public final void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT) = 0.05s / (0.05s + 0.01s)
        // with t, the low-pass filter's time-constant t = 0.05
        // and dT, the event delivery rate = 0.01s (10ms)

        history = current;
        current = Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]);
        change = Math.abs(history - current);
        Log.i("Vector Change", Double.toString(change));

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]; //0 + (1 - alpha) * acceleration
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        data_values[0] = event.values[0];
        data_values[1] = event.values[1];
        data_values[2] = event.values[2];

        Log.i(data_s, "x/" + event.values[0] + ", y/" + event.values[1] + ", z/" + event.values[2]);
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
