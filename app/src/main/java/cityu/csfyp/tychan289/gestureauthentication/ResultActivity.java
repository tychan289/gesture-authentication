package cityu.csfyp.tychan289.gestureauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Moonviler on 29/1/18.
 */

public class ResultActivity extends AppCompatActivity {
    //UI
    TextView result_text;
    TextView dist_text;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result");

        //Get views of activity_register
        result_text = (TextView) findViewById(R.id.result_result);
        dist_text = (TextView) findViewById(R.id.result_dist);


        //Change welcoming text from intent
        Intent intent = getIntent();
        String r = intent.getStringExtra("result");
        setResultText(r);

        String d = intent.getStringExtra("distance");
        setDistText(d);
    }

    private void setResultText(String s){
        result_text.setText(s);
    }

    private void setDistText(String s){
        dist_text.setText(s);
    }

    public void finish(View view){
        finish();
    }
}
