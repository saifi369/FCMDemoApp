package com.saifi369.fcmdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyTag";
    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputText=findViewById(R.id.tv_output);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        Log.d(TAG, "onComplete: " + task.getResult().getToken());
                    }
                });

        if (getIntent() != null && getIntent().hasExtra("key1")) {
            mOutputText.setText("");

            for (String key : getIntent().getExtras().keySet()) {
                Log.d(TAG, "onCreate: Key: " + key + " Data: " + getIntent().getExtras().getString(key));
                mOutputText.append(getIntent().getExtras().getString(key) + "\n");
            }

        }

    }
}
