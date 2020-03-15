package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class uploadtable extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Database";

    @BindView(R.id.spinner)
    Spinner s1;
    @BindView(R.id.spinner2)
    Spinner s2;
    @BindView(R.id.spinner3)
    Spinner s3;
    @BindView(R.id.spinner4)
    Spinner s4;
    @BindView(R.id.spinner5)
    Spinner s5;
    @BindView(R.id.spinner6)
    Spinner s6;
    @BindView(R.id.spinner7)
    Spinner s7;
    @BindView(R.id.spinner8)
    Spinner s8;
    @BindView(R.id.submit_button)
    Button submit;
    @BindView(R.id.days_spinner)
    Spinner dayspin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadtable);
        ButterKnife.bind(this);

        String Courses[]={"Free","Compiler Design Lab","Computer Networks","Principles of Machine Learning","Software Engineering",
                "Compiler Design","Elective","Open Lab","Computer Networks Lab","Software Engineering lab",
                "Principles of Machine Learning Lab","Mentoring","Open Lab Tutorial","CIR","Elective Lab"};
        String days[]={"Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days);
        dayspin.setAdapter(ad);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Courses);
        s1.setAdapter(adap);
        s2.setAdapter(adap);
        s3.setAdapter(adap);
        s4.setAdapter(adap);
        s5.setAdapter(adap);
        s6.setAdapter(adap);
        s7.setAdapter(adap);
        s8.setAdapter(adap);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> timetable = new HashMap<>();
                timetable.put("1",s1.getSelectedItem().toString().trim());
                timetable.put("2",s2.getSelectedItem().toString().trim());
                timetable.put("3",s3.getSelectedItem().toString().trim());
                timetable.put("4",s4.getSelectedItem().toString().trim());
                timetable.put("5",s5.getSelectedItem().toString().trim());
                timetable.put("6",s6.getSelectedItem().toString().trim());
                timetable.put("7",s7.getSelectedItem().toString().trim());
                timetable.put("8",s8.getSelectedItem().toString().trim());

                db.collection("timetable").document(dayspin.getSelectedItem().toString().trim()).set(timetable)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "Timetable added successfully!");
                                Toast.makeText(getApplicationContext(),"Timetable added successfully!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding timetable!");
                                Toast.makeText(getApplicationContext(),"Error adding timetable",Toast.LENGTH_SHORT).show();
                            }
                        });
                }
        });

    }
}
