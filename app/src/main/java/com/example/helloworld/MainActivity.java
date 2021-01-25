package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chk1,chk2,chk3,chk4,chk5;
    private int SECOND_ACTIVITY_REQUEST_CODE=0;
    private String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO : Enter Toast messages for every change in state
        Log.i(tag,"Main activity created");
        Toast.makeText(this,"Main activity created", Toast.LENGTH_SHORT).show();
//        EditText usernameEditText = (EditText) findViewById(R.id.editText);
//        String sUsername = usernameEditText.getText().toString();
//        if (sUsername.matches("")) {
//            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
//            return;
//        }
        chk1 = (CheckBox)findViewById(R.id.checkBox);
        chk2 = (CheckBox)findViewById(R.id.checkBox2);
        chk3 = (CheckBox)findViewById(R.id.checkBox3);
        chk4 = (CheckBox)findViewById(R.id.checkBox4);
        chk5 = (CheckBox)findViewById(R.id.checkBox5);

        Button btn = (Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chk1.isChecked()){
                    chk1.setChecked(false);
                }

                if(chk2.isChecked()){
                    chk2.setChecked(false);
                }
                if(chk3.isChecked()){
                    chk3.setChecked(false);
                }

                if(chk4.isChecked()){
                    chk4.setChecked(false);
                }
                if(chk5.isChecked()){
                    chk5.setChecked(false);
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(tag,"Main activity Create_to_Start");
        Toast.makeText(this,"Main activity Create_to_Start", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(tag,"Main activity Start_to_Resume");
        Toast.makeText(this,"Main activity Start_to_Resume", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(tag,"Main activity Resume_to_Pause");
        Toast.makeText(this,"Main activity Resume_to_Pause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(tag,"Main activity Pause_to_Stop");
        Toast.makeText(this,"Main activity Pause_to_Stop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(tag,"Main activity destroyed");
        Toast.makeText(this,"Main activity destroyed", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt("questionId",mCurrentIndex);
    }

    public void gotoNextActivity(View v){

        Intent intent = new Intent(MainActivity.this, NextActivity.class);
        chk1 = (CheckBox)findViewById(R.id.checkBox);
        chk2 = (CheckBox)findViewById(R.id.checkBox2);
        chk3 = (CheckBox)findViewById(R.id.checkBox3);
        chk4 = (CheckBox)findViewById(R.id.checkBox4);
        chk5 = (CheckBox)findViewById(R.id.checkBox5);

        EditText edit1 = (EditText)findViewById(R.id.editText);
        String name = edit1.getText().toString();

        Bundle bundle = new Bundle();

        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
//        intent.putExtra("name",name);

        bundle.putString("name",name);
        TextView text1 = (TextView)findViewById(R.id.textView4);
        String message1 = text1.getText().toString();
        if(chk1.isChecked()){
//            intent.putExtra("pre1", message1);
            bundle.putString("pre1", message1);
        }

        TextView text2 = (TextView)findViewById(R.id.textView5);
        String message2 = text2.getText().toString();
        if(chk2.isChecked()){
//            intent.putExtra("pre2", message2);
            bundle.putString("pre2", message2);
        }

        TextView text3 = (TextView)findViewById(R.id.textView6);
        String message3 = text3.getText().toString();
        if(chk3.isChecked()){
//            intent.putExtra("pre3", message3);
            bundle.putString("pre3", message3);
        }

        TextView text4 = (TextView)findViewById(R.id.textView7);
        String message4 = text4.getText().toString();
        if(chk4.isChecked()){
//            intent.putExtra("pre4", message4);
            bundle.putString("pre4", message4);
        }

        TextView text5 = (TextView)findViewById(R.id.textView8);
        String message5 = text5.getText().toString();
        if(chk5.isChecked()){
//            intent.putExtra("pre5", message5);
            bundle.putString("pre5", message5);
        }
//        Log.i("Main Activity","main activity ResumedtoPause");
//        Toast.makeText(this,"main activity ResumedtoPause", Toast.LENGTH_SHORT).show();
        intent.putExtras(bundle);
//        startActivity(intent);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("status");
                Toast.makeText(this,returnString, Toast.LENGTH_LONG).show();
                // Set text view with string
//                TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText(returnString);
            }
        }
    }



}