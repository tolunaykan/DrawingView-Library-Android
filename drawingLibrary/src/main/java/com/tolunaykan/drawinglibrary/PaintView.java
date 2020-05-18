package com.tolunaykan.drawinglibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class PaintView extends View {
    public static final int DEFAULT_BRUSH_SIZE = 20;
    public static final int DEFAULT_BRUSH_COLOR = Color.BLACK;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float DEFAULT_TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<DrawingPath> paths = new ArrayList<>();
    private ArrayList<DrawingPath> undoPaths = new ArrayList<>();
    private int brushColor;
    private int backgroundColor;
    private int brushSize;
    private float touchTolerance;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private DrawingChangeListener drawingChangeListener;
    private int tempBrushColor;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_BRUSH_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        brushColor = DEFAULT_BRUSH_COLOR;
        backgroundColor = DEFAULT_BG_COLOR;
        brushSize = DEFAULT_BRUSH_SIZE;
        touchTolerance = DEFAULT_TOUCH_TOLERANCE;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    public void setBrushColor(int color){
        brushColor = color;
    }

    public int getBrushColor() {
        return brushColor;
    }

    public void setBrushSize(int size){
        brushSize = size;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBackgroundColor(int color){
        backgroundColor = color;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setTouchTolerance(float tolerance) {
        touchTolerance = tolerance;
    }

    public float getTouchTolerance() {
        return touchTolerance;
    }

    public void clearCanvas(){
        paths.clear();
        invalidate();
    }

    public Bitmap getCanvasBitmap(){
        return mBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);

        for(DrawingPath drawingPath:paths){
            mPaint.setColor(drawingPath.color);
            mPaint.setStrokeWidth(drawingPath.strokeWidth);
            mCanvas.drawPath(drawingPath.path,mPaint);
        }

        canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
        canvas.restore();
    }

    public void startTouch(float x, float y){
        mPath = new Path();
        DrawingPath drawingPath = new DrawingPath(brushColor,brushSize,mPath);
        paths.add(drawingPath);
        mPath.reset();
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
        invalidate();
    }

    private void touchMove(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if(dx >= touchTolerance || dy >= touchTolerance){
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp(){
        mPath.lineTo(mX, mY);
    }

    public void drawToCanvas(float x, float y){
        mPath.lineTo(x,y);
        invalidate();
    }

    public void undoDrawing(){
        if(paths.size() > 0){
            undoPaths.add(paths.remove(paths.size()-1));
            invalidate();
        }
    }

    public void redoDrawing(){
        if(undoPaths.size() > 0){
            paths.add(undoPaths.remove(undoPaths.size()-1));
            invalidate();
        }
    }

    public void enableEraser(){
        tempBrushColor = brushColor;
        brushColor = backgroundColor;
    }

    public void disableEraser(){
        if(tempBrushColor != 0){
            brushColor = tempBrushColor;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                if(drawingChangeListener != null){
                    drawingChangeListener.onTouchStart(x,y);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                if(drawingChangeListener != null){
                    drawingChangeListener.onDrawingChange(x,y);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                if(drawingChangeListener != null){
                    drawingChangeListener.onDrawingChange(x,y);
                }
                invalidate();
                break;
        }
        return true;
    }

    public void addDrawingChangeListener(DrawingChangeListener listener){
        drawingChangeListener = listener;
    }
}
