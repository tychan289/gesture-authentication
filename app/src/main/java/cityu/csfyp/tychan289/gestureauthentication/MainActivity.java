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
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.username_editText);
        textView = (TextView) findViewById(R.id.remind_textView);
    }

    public void onClickBtn(View view){
        String username = editText.getText().toString();

        //Check if registered then open activity_register
        if (true) {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        //Check if new user then open activity


    }
}
