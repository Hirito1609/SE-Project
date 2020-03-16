package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

                CollectionReference colrefroom = db.collection("rooms");
                CollectionReference colrefcourse = db.collection("courses");
                CollectionReference colrefttable = db.collection("timetable");




            }
        });

    }
}
