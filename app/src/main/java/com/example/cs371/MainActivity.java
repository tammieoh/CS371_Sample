package com.example.cs371;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // define variables outside of methods
    private Button button_hi;
    private int number = 0;
    private TextView textView_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // links the activity to the xml layout

        // look up the button by his id
        // add an event listener to listen for the clicks
        // when click happens i do something
        button_hi = findViewById(R.id.button_hello);
        textView_count = findViewById(R.id.textView_count);


        // create logs to keep track of errors
        Log.d("MainActivity", "I was not able to see the Toast when clicking button");
        button_hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle what happens after i click
//                sayHello(v);
                launchNextActivity(v);
            }
        });
    }

    public void sayHello(View v) {
        // create a text with a message saying hello
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void countUp(View v) {
        // increment the value and display in the text view
        number++;
        // when you see an object, you want to make sure its not null
        if(textView_count != null) {
            textView_count.setText(Integer.toString(number)); // set text takes a string
        }
    }
    public void countDown(View view) {
        if(number > 0) {
            number--;
        }
        if(textView_count != null) {
            textView_count.setText(Integer.toString(number));
        }
    }
    public void launchNextActivity(View view) {
        // create an intent and you need to specify from and to
        Intent intent = new Intent(this, SecondActivity.class);

        // data field
        // intent extras
        // both can pack data and send to the targeted activity

        // intent extras:
        // key/value pairs in a bundle

        // i want to pass the count number through intent to second activity and display in second activity
        // ex: 5 was passed

        String message = textView_count.getText().toString();
        intent.putExtra("count", message);

//        startActivity(intent);
        startActivityForResult(intent, 1);
    }
    // do something when the result is received
    // a lifecycle method
    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra("replyCount");
                textView_count.setText(reply);
            }
        }
    }
}