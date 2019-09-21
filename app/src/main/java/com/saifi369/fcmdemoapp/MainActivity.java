package com.saifi369.fcmdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyTag";
    private TextView mOutputText;
    private Button mBtnSubscribe;
    private Button mBtnUnsubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputText=findViewById(R.id.tv_output);
        mBtnSubscribe = findViewById(R.id.btn_sub);
        mBtnUnsubscribe = findViewById(R.id.btn_unsub);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> Log.d(TAG, "onComplete: " + task.getResult().getToken()));

        if (getIntent() != null && getIntent().hasExtra("key1")) {
            mOutputText.setText("");

            for (String key : getIntent().getExtras().keySet()) {
                Log.d(TAG, "onCreate: Key: " + key + " Data: " + getIntent().getExtras().getString(key));
                mOutputText.append(getIntent().getExtras().getString(key) + "\n");
            }

        }

        mBtnSubscribe.setOnClickListener(view -> {
            //subscribe topic here

            FirebaseMessaging.getInstance().subscribeToTopic("tennis-topic")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            Toast.makeText(this, "Topic Subscribed", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "Subscription failed", Toast.LENGTH_SHORT).show();
                    });

        });

        mBtnUnsubscribe.setOnClickListener(view -> {
            //unsubscribe topic here

            FirebaseMessaging.getInstance().unsubscribeFromTopic("tennis-topic")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            Toast.makeText(this, "Topic Unsubscribed", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "Action failed", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
