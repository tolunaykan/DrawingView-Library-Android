package com.tolunaykan.drawinglibrary;

import android.graphics.Path;

public class DrawingPath {

    public int color;
    public int strokeWidth;
    public Path path;

    public DrawingPath(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
