package com.example.painterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onJustDrawing(View view){
        Intent intent = new Intent(MainActivity.this, JustDrawingActivity.class);
        startActivity(intent);
    }

    public void onSyncDrawing(View view){
        Intent intent = new Intent(MainActivity.this, SyncDrawingActivity.class);
        startActivity(intent);
    }
}
