package com.example.painterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.tolunaykan.drawinglibrary.DrawingChangeListener;
import com.tolunaykan.drawinglibrary.PaintView;

public class SyncDrawingActivity extends AppCompatActivity {

    private PaintView paintView, paintView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_drawing);

        paintView = findViewById(R.id.paintView1);
        paintView2 = findViewById(R.id.paintView2);

        paintView.setBackgroundColor(Color.CYAN);
        paintView.setBrushColor(Color.BLACK);

        paintView.addDrawingChangeListener(new DrawingChangeListener() {
            @Override
            public void onTouchStart(float x, float y) {
                paintView2.startTouch(x,y);
            }

            @Override
            public void onDrawingChange(float x, float y) {
                paintView2.drawToCanvas(x,y);
            }
        });

        paintView2.addDrawingChangeListener(new DrawingChangeListener() {
            @Override
            public void onTouchStart(float x, float y) {
                paintView.startTouch(x,y);
            }

            @Override
            public void onDrawingChange(float x, float y) {
                paintView.drawToCanvas(x,y);
            }
        });

    }
}
