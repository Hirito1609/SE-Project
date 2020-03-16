package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.ValueEventListener;

public class bookroom extends AppCompatActivity {

    @BindView(R.id.spinnerday)
    Spinner select_day;
    @BindView(R.id.spinnerroom)
    Spinner select_room;
    @BindView(R.id.spinnerperiod)
    Spinner select_period;
    @BindView(R.id.buttonreserveroom)
    Button reserve;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Databasequery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookroom);
        ButterKnife.bind(this);

        String days[]={"Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days);
        select_day.setAdapter(ad);

        select_room.setVisibility(View.INVISIBLE);
        String periods[] = {"1","2","3","4","5","6","7","8"};
        ArrayAdapter<String> add = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,periods);
        select_period.setAdapter(add);

        String rooms[]={};

        Query q = db.collection("rooms").whereEqualTo("AVAILABLE",true);
        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    QuerySnapshot doc = task.getResult();
                    if(!doc.isEmpty())
                    {
//                        Log.d(TAG,doc.getDocuments().get(1));
//                        Task<DocumentSnapshot> taskk =;
//                        doc.getDocuments().get(1)=taskk.getResult();
                    }
                    else
                    {
                        Log.d(TAG, "No rooms available");
                    }
                }
            }
        });
//        q.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final List<String> titleList = new ArrayList<String>();
//                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    String titlename = dataSnapshot1.child("title").getValue(String.class);
//                    titleList.add(titlename);
//                }
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(bookroom.this, android.R.layout.simple_spinner_item, titleList);
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                select_room.setAdapter(arrayAdapter);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(bookroom.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> bookings = new HashMap<>();
                bookings.put("DAY",select_day.getSelectedItem().toString().trim());
                bookings.put("ROOM",select_room.getSelectedItem().toString().trim());
                bookings.put("PERIOD",select_period.getSelectedItem().toString().trim());



            }
        });


    }
}
