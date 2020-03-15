package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Source;

import butterknife.BindView;
import butterknife.ButterKnife;

public class viewtable extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Database";
    @BindView(R.id.tvp1)
    TextView period1_tv;
    @BindView(R.id.tvp2)
    TextView period2_tv;
    @BindView(R.id.tvp3)
    TextView period3_tv;
    @BindView(R.id.tvp4)
    TextView period4_tv;
    @BindView(R.id.tvp5)
    TextView period5_tv;
    @BindView(R.id.tvp6)
    TextView period6_tv;
    @BindView(R.id.tvp7)
    TextView period7_tv;
    @BindView(R.id.tvp8)
    TextView period8_tv;
    @BindView(R.id.spinner_select)
    Spinner selectspinner;
    @BindView(R.id.button_gettable)
    Button gettable;
    @BindView(R.id.periodlist)
    LinearLayout periodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtable);
        ButterKnife.bind(this);

        String days[]={"Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days);
        selectspinner.setAdapter(ad);

        gettable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                periodlist.setVisibility(View.VISIBLE);
                Source source = Source.CACHE;
                DocumentReference docRef = db.collection("timetable").document(selectspinner.getSelectedItem().toString());
                docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                period1_tv.setText("Period 1 : "+document.getString("1"));
                                period2_tv.setText("Period 2 : "+document.getString("2"));
                                period3_tv.setText("Period 3 : "+document.getString("3"));
                                period4_tv.setText("Period 4 : "+document.getString("4"));
                                period5_tv.setText("Period 5 : "+document.getString("5"));
                                period6_tv.setText("Period 6 : "+document.getString("6"));
                                period7_tv.setText("Period 7 : "+document.getString("7"));
                                period8_tv.setText("Period 8 : "+document.getString("8"));
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }
        });

    }
}
