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
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataX;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataY;
import cityu.csfyp.tychan289.gestureauthentication.roomEntity.AccelDataZ;

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
    private ArrayList data_y = new ArrayList();
    private ArrayList data_z = new ArrayList();
    private int trial = 1;
    private AccelDataX accelDataX;
    private AccelDataY accelDataY;
    private AccelDataZ accelDataZ;
    private double history, current, change;
    private boolean start_b = false;

    //Constant
    private static final char x_type = 'x';
    private static final char y_type = 'y';
    private static final char z_type = 'z';
    private static final int trainingLimit = 3;
    private static final String start = "START";
    private static final String stop = "STOP";
    private static final String data_s = "DATA";

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
            if (trial > trainingLimit){
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
                if (start_b){
                    data_x.add(data_values[0]);
                    data_y.add(data_values[1]);
                    data_z.add(data_values[2]);
                } else {
                    if (change > 0.15) {
                        start_b = true;
                        Log.i(data_s, "Start recording data...");
                        data_x.add(data_values[0]);
                        data_y.add(data_values[1]);
                        data_z.add(data_values[2]);
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

        accelDataX = new AccelDataX(username, TypeConvertors.ArrayListDoubletoString(data_x));
        accelDataY = new AccelDataY(username, TypeConvertors.ArrayListDoubletoString(data_y));
        accelDataZ = new AccelDataZ(username, TypeConvertors.ArrayListDoubletoString(data_z));

        db.accelDataXDao().insert(accelDataX);
        db.accelDataYDao().insert(accelDataY);
        db.accelDataZDao().insert(accelDataZ);

        //Turn data into frequency objects
//        if (trial == 1) {
//            frequencyX = (FrequencyX) Classification.classify(data_x, x_type, username);
//            frequencyY = (FrequencyY) Classification.classify(data_y, y_type, username);
//            frequencyZ = (FrequencyZ) Classification.classify(data_z, z_type, username);
//        } else {
//            frequencyX = (FrequencyX) Classification.classify(data_x, x_type, username, frequencyX);
//            frequencyY = (FrequencyY) Classification.classify(data_y, y_type, username, frequencyY);
//            frequencyZ = (FrequencyZ) Classification.classify(data_z, z_type, username, frequencyZ);
//            if (trial == trainingLimit) {
//                //Delete old if exists
//                if (db.frequencyXDao().getFrequencyX(username) != null){
//                    FrequencyX oldX = db.frequencyXDao().getFrequencyX(username);
//                    db.frequencyXDao().delete(oldX);
//                    FrequencyY oldY = db.frequencyYDao().getFrequencyY(username);
//                    db.frequencyYDao().delete(oldY);
//                    FrequencyZ oldZ = db.frequencyZDao().getFrequencyZ(username);
//                    db.frequencyZDao().delete(oldZ);
//                }
//                db.frequencyXDao().insert(frequencyX);
//                db.frequencyYDao().insert(frequencyY);
//                db.frequencyZDao().insert(frequencyZ);
//            }
//        }

        Log.i("TRIAL", "Finished trial " + trial);

        //Clear stored data
        data_x.clear();
        data_y.clear();
        data_z.clear();

        trial++;
        if (trial == trainingLimit) {
            instruction4.setText("");
        }
        timerRunning = false;
    }

    //Sensor is non-stop loading acceleration data
    //Apply low-pass filter to filter the gravity
    @Override
    public final void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT) = 0.05s / (0.05s + 0.01s)
        // with t, the low-pass filter's time-constant t = 0.05
        // and dT, the event delivery rate = 0.01s (10ms)

        history = current;
        current = Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]);
        change = Math.abs(history - current);
//        Log.i("Vector Change", Double.ArrayListDoubletoString(change));

        final double alpha = 0.05 / 0.06; //0.833...

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]; //0 + (1 - alpha) * acceleration
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        data_values[0] = event.values[0];
        data_values[1] = event.values[1];
        data_values[2] = event.values[2];
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
