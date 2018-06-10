package cityu.csfyp.tychan289.gestureauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //UI
    EditText username_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");

        username_input = (EditText) findViewById(R.id.main_username_input);
    }

    //Register (training)
    public void register(View view) {
        String username = this.username_input.getText().toString();

        //Send username_input into view
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("username_input", username);
        startActivity(intent);
    }

    //Login (testing)
    public void login(View view){
        String username = this.username_input.getText().toString();

        //Send username_input into view
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("username_input", username);
        startActivity(intent);
    }
}
