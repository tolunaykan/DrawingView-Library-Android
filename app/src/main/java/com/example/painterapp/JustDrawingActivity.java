package com.example.painterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tolunaykan.drawinglibrary.DrawingChangeListener;
import com.tolunaykan.drawinglibrary.PaintView;

public class JustDrawingActivity extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_drawing);




        paintView = findViewById(R.id.drawingView);
        paintView.setBackgroundColor(Color.CYAN);
        paintView.setBrushColor(Color.BLACK);


    }

    public void onBrushColor(View view){
        paintView.setBrushColor(Color.WHITE);
    }

    public void onBrush(View view){
        paintView.disableEraser();
    }

    public void onBrushSize(View view){
        paintView.setBrushSize(40);
    }

    public void onUndo(View view){
        paintView.undoDrawing();
    }

    public void onEraser(View view){
        paintView.enableEraser();
    }

    public void onRedo(View view){
        paintView.redoDrawing();
    }
}
