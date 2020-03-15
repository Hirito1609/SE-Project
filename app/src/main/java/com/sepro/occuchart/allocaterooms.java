package com.sepro.occuchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class allocaterooms extends AppCompatActivity {

    @BindView(R.id.button_allocate)
    Button allocatebutton;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Database";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocaterooms);
        ButterKnife.bind(this);

        allocatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}
