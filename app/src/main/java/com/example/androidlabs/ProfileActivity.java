package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String ACTIVITY_NAME = "ProfileActivity";

    ImageButton mImageButton;

    @Override
    protected void onStart() {
        Log.e(ACTIVITY_NAME, "in function onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e(ACTIVITY_NAME, "in function onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(ACTIVITY_NAME, "in function onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.e(ACTIVITY_NAME, "in function onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e(ACTIVITY_NAME, "in function onResume");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ACTIVITY_NAME, "in function onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent fromMain = getIntent();
        String emailPassed = fromMain.getStringExtra("EMAIL");
        EditText emailAddress = findViewById(R.id.emailInputTextInProfile);
        emailAddress.setText(emailPassed);
        mImageButton = findViewById(R.id.imageButton);
        mImageButton.setOnClickListener(click -> dispatchTakePictureIntent());

        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(bt -> {
            Intent goToChat = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(goToChat);
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME, "in function onActivityResult");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

}
