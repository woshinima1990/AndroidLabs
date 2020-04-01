package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String PREFERENCE_FILE_NAME = "FileName";
    static final String EMAIL_ADDRESS_PREFERENCE = "EmailAddress";

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        String savedString = prefs.getString(EMAIL_ADDRESS_PREFERENCE, "");
        EditText emailAddress = findViewById(R.id.emailAddress);
        emailAddress.setText(savedString);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(bt -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            goToProfile.putExtra("EMAIL", emailAddress.getText().toString());
            startActivity(goToProfile);
        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs = getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        EditText emailAddress = findViewById(R.id.emailAddress);
        saveSharedPrefs(EMAIL_ADDRESS_PREFERENCE, emailAddress.getText().toString());
    }


    private void saveSharedPrefs(String name, String value)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(name, value);
        editor.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

}
