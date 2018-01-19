package cityu.csfyp.tychan289.gestureauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.main_username_input);
        textView = (TextView) findViewById(R.id.main_instruction);
    }

    //Register (training)
    public void register(View view) {
        String username = editText.getText().toString();

        //Send username into view
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //Login (testing)
    public void login(View view){
        String username = editText.getText().toString();

        //Send username into view
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
