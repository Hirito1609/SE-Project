package com.sepro.occuchart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class password extends AppCompatActivity {
    // initialize variable
    PasscodeView passcodeView;
    String TAG = "passwordview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // assign variable
        passcodeView = findViewById(R.id.passcodeView1);

        passcodeView.setPasscodeLength(4)
                .setLocalPasscode("5678")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(),"Password is Wrong ERROR:5678 ",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        Log.d(TAG,number);
                        Intent intent = new Intent(password.this,addroom.class);
                        startActivity(intent);
                        finish();
                    }

                });
    }
}
