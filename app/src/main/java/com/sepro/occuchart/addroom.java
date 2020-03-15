package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addroom extends AppCompatActivity {

    EditText roomnum;
    Spinner roomtype;
    Button addtodatabase;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);


        addtodatabase = findViewById(R.id.add_button);
        roomnum = findViewById(R.id.roomnum);
        roomtype = findViewById(R.id.roomtype);
        String choices[] ={"Classroom","Lab","Seminar Hall"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,choices);
        roomtype.setAdapter(adap);

        addtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txt = (TextView) view;
                Map<String, Object> room = new HashMap<>();
                room.put("ROOM_NUM",roomnum.getText().toString().toUpperCase());
                room.put("TYPE", roomtype.getSelectedItem().toString());
                room.put("AVAILABLE", true);
                room.put("ALLOTTED_TO", "");

                db.collection("rooms").document(roomnum.getText().toString().toUpperCase()).set(room)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
                roomnum.setText("");



            }
        });


    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
//        finish();
    }
}
