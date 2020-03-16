package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.Map;

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
                DocumentReference docrefttable = db.collection("timetable").document("Monday");
                //Query query = colrefcourse.whereEqualTo("NAME", document.getString("1"));

                Map<String,Object> allocationnn = new HashMap<>();
                docrefttable.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        final String[] roomnumber = new String[1];
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    int i;
                                    for(i =1 ; i<9;i=i+1)
                                    {
                                        Query q = colrefcourse.whereEqualTo("NAME", document.getString(String.valueOf(i)));
                                        String finalI = document.getString(String.valueOf(i));
                                        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> taskk) {
                                                if(taskk.isSuccessful()){
                                                    for (QueryDocumentSnapshot documentan : taskk.getResult()){
                                                        roomnumber[0] = documentan.getString("ROOM");
                                                        allocationnn.put(finalI, roomnumber[0]);
                                                        Log.d(TAG,finalI+"  "+ roomnumber[0]);
                                                        Log.d(TAG,documentan.getString("ROOM"));
                                                    }
                                                }else{
                                                    Log.d(TAG, "Error getting documents: ", taskk.getException());
                                                }
                                            }
                                        });
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                    }
                });

                db.collection("allocation").document("Monday").set(allocationnn)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(TAG, "allocation added successfully!");
                                Toast.makeText(getApplicationContext(),"Allocation added successfully!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding Allocation!");
                                Toast.makeText(getApplicationContext(),"Error adding Allocation",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
