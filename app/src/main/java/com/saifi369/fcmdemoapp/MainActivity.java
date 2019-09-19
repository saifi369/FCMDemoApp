package com.saifi369.fcmdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyTag";
    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputText=findViewById(R.id.tv_output);

        if (getIntent() != null && getIntent().hasExtra("key1")) {
            mOutputText.setText("");

            for (String key : getIntent().getExtras().keySet()) {
                Log.d(TAG, "onCreate: Key: " + key + " Data: " + getIntent().getExtras().getString(key));
                mOutputText.append(getIntent().getExtras().getString(key) + "\n");
            }

        }

    }
}
