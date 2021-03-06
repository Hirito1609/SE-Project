package com.sepro.occuchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.protobuf.NullValue;

public class login extends AppCompatActivity {

    Button btn_sign_out,delete,home;
    TextView display_name,display_email,display_phone;
    String TAG = "display parameters";
//    RelativeLayout mainpage; //xx3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        mainpage = findViewById(R.id.rl1); //xx3
//        mainpage.setVisibility(View.VISIBLE); //xx3 removed to avoid crash while clicked on profile pic
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        display_name = findViewById(R.id.disname);
        display_email = findViewById(R.id.disemail);
        display_phone = findViewById(R.id.disphone);
        String usemail = user.getEmail();

        String usn= user.getDisplayName();
        if (usn != null)
        {
            display_name.append(user.getDisplayName());
        }
        else
        {
            display_name.append(user.getPhoneNumber());
            display_phone.setVisibility(View.VISIBLE);
            display_phone.append(user.getPhoneNumber());
        }
        if(usemail != null)
        {
            display_email.setVisibility(View.VISIBLE);
            display_email.append(user.getEmail());
        }
//        getting user photo

//        Uri xx = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
//
//        ImageView imgView = (ImageView) findViewById(R.id.profile_pic);
//
//        imgView.setImageURI(Uri.parse(String.valueOf(xx)));
//        Log.d(TAG, xx.toString());


//

        btn_sign_out = findViewById(R.id.sign_out_button);
        home = findViewById(R.id.btn_home);
        delete = findViewById(R.id.del);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(login.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                signOut();
                            }
                        });
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(login.this);
                // Setting Dialog Title
                alertDialog.setTitle("Confirm Delete...");
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want delete ?");
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.danger_icon);
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthUI.getInstance()
                                .delete(login.this)

                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        signOut();
                                        Toast.makeText(getApplicationContext(),"Your Account has been deleted successfully!!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "You clicked on NO ", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        });
    }

    private void signOut() {

//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("EXIT", true);
//        startActivity(intent);

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    public void go_home(View view) {
        startActivity(new Intent(this, dashboard.class));
        finish();

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, dashboard.class));
        finish();
    }
}