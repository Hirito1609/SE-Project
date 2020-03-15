package com.sepro.occuchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity {

    TextView display_name;
    CardView uploadtt, viewtt,viewroomstatus, bookroom, addrooms, allocroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        display_name = findViewById(R.id.dash_user);
        display_name.append(user.getDisplayName());
        uploadtt = findViewById(R.id.cvupload_tt);
        viewtt = findViewById(R.id.cvview_tt);
        viewroomstatus = findViewById(R.id.cvroom_status);
        bookroom = findViewById(R.id.cvreserve_room);
        addrooms = findViewById(R.id.cvadd_room);
        allocroom = findViewById(R.id.cvallocate_room);

        uploadtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goto for upload tt buuton
                Intent i = new Intent(dashboard.this,password2.class);
                startActivity(i);
            }
        });
        viewtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,viewtable.class);
                startActivity(i);
            }
        });
        viewroomstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,roomstatus.class);
                startActivity(i);
            }
        });
        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,bookroom.class);
                startActivity(i);
            }
        });
        addrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            // goto for add room buuton
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,password.class);
                startActivity(i);
            }
        });
        allocroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,allocaterooms.class);
                startActivity(i);
            }
        });


    }

    public void profile(View view) {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("EXIT", true);
//        startActivity(intent);

        startActivity(new Intent(this, login.class));
        finish();

    }
}
