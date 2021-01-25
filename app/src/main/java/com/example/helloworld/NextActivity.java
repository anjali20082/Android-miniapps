package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    private int checkflag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Bundle bundle = getIntent().getExtras();

        Log.i("Next Activity","Next activity created");
        Toast.makeText(this,"Next activity created", Toast.LENGTH_SHORT).show();
//        Intent intent = getIntent();

        if (bundle != null){
            String name = bundle.getString("name");
            String value1 = bundle.getString("pre1");
            String value2 = bundle.getString("pre2");
            String value3 = bundle.getString("pre3");
            String value4 = bundle.getString("pre4");
            String value5 = bundle.getString("pre5");

            TextView name1 = (TextView)findViewById(R.id.textView2) ;
            name1.setTypeface(name1.getTypeface(), Typeface.BOLD);
            name1.setText("Rules selected by "+ name);

            TextView enteredValue1 = (TextView) findViewById(R.id.textView9);
            enteredValue1.setText(value1);

            TextView enteredValue2 = (TextView) findViewById(R.id.textView10);
            enteredValue2.setText(value2);

            TextView enteredValue3 = (TextView) findViewById(R.id.textView11);
            enteredValue3.setText(value3);

            TextView enteredValue4 = (TextView) findViewById(R.id.textView12);
            enteredValue4.setText(value4);

            TextView enteredValue5 = (TextView) findViewById(R.id.textView13);
            enteredValue5.setText(value5);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Next Activity","Next activity Create_to_Start");
        Toast.makeText(this,"Next activity Create_to_Start", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("Next Activity","Next activity Start_to_Resume");
        Toast.makeText(this,"Next activity Start_to_Resume", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Next Activity","Next activity Resume_to_Pause");
        Toast.makeText(this,"Next activity Resume_to_Pause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Next Activity","Next activity Pause_to_Stop");
        Toast.makeText(this,"Next activity Pause_to_Stop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Next Activity","Next activity destroyed");
        Toast.makeText(this,"Next activity destroyed", Toast.LENGTH_SHORT).show();
    }
    public void CheckStatus(View v){

        Intent intent = new Intent();
        TextView Str1 = (TextView)findViewById(R.id.textView9) ;
        TextView Str2 = (TextView)findViewById(R.id.textView10) ;
        TextView Str3 = (TextView)findViewById(R.id.textView11) ;
        TextView Str4 = (TextView)findViewById(R.id.textView12) ;
        TextView Str5 = (TextView)findViewById(R.id.textView13) ;
        String message1 = Str1.getText().toString();
        String message2 = Str2.getText().toString();
        String message3 = Str3.getText().toString();
        String message4 = Str4.getText().toString();
        String message5 = Str5.getText().toString();
        if(message1 != "" && message2!= "" && message3!= "" && message4 != "" && message5 != "")
        {
            intent.putExtra("status", "YOU ARE SAFE");
            checkflag = 1;
        }
        else
        {
            intent.putExtra("status", "YOU ARE NOT SAFE");
            checkflag = 0;
        }
//        Log.i("Next Activity","next activity ResumedtoPause");
        if(checkflag == 1) {
            Toast.makeText(this, "YOU ARE SAFE", Toast.LENGTH_LONG).show();
        }
        else if(checkflag == 0) {
            Toast.makeText(this, "YOU ARE NOT SAFE", Toast.LENGTH_LONG).show();
        }
        setResult(RESULT_OK, intent);
//        finish();
//        startActivity(intent);
    }
}