package com.sepro.occuchart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 123;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login); // this if implemented causes Login.java page flash while app opens
        setContentView(R.layout.activity_main); // vr - implementing this instead of the above line
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder()
                        .setDefaultCountryIso("in")
                        .build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        showSignInOptions();
//        if (getIntent().getBooleanExtra("EXIT", false))
//        {
//            finish();
//        }
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false)
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .setLogo(R.drawable.logo_wtxt)
                        .setTosAndPrivacyPolicyUrls(
                                "https://ocdamrita.com/terms.html",
                                "https://ocdamrita.com/privacy.html")
                        .build(), MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(new Intent(this, dashboard.class));
                finish();
                String usrn = user.getDisplayName();
                if(usrn!=null) {
                    Toast.makeText(this, "Welcome! " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Welcome! " + user.getPhoneNumber(), Toast.LENGTH_LONG).show();
                }
            } else {
                if(response != null)
                {
                    Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();

            }
        }
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
        finish();
    }

}