# DrawingView Android Library
------------
> You can easily implement a drawing view to your application. The drawing view lets you draw anything to the canvas using a lot of tools.

[![](https://jitpack.io/v/tolunaykan/DrawingView-Library-Android.svg)](https://jitpack.io/#tolunaykan/DrawingView-Library-Android)

![](https://github.com/tolunaykan/DrawingView-Library-Android/blob/master/justDrawingGif.gif?raw=true)

## Getting Started
* Step 1. Add the JitPack repository to your root build.gradle at the end of repositories:
```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```


* Step 2. Add the dependency
```java
dependencies {
    implementation 'com.github.tolunaykan:DrawingView-Library-Android:1.0.0'
}
```

* Step 3. Add drawing view to your layout you want to display on
```java
<com.tolunaykan.drawinglibrary.PaintView
    android:id="@+id/drawingView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

* Step 4. All you must to do add code below to your activity
```java
PaintView paintView = findViewById(R.id.drawingView);
```

## Other Settings
### 1. Brush Preferences
> You can set brush preferences
```java
paintView.setBrushColor(Color.BLACK);
paintView.setBrushSize(10);
```

### 2. Undo & Redo Operations
> Easily undoing and redoing your drawings
```java
paintView.undoDrawing();
paintView.redoDrawing();
```


### 3. Enable & Disable Eraser
> Choosing eraser or brush
```java
paintView.enableEraser();   //Enable eraser
paintView.disableEraser();  //Enable brush
```

### 4. Drawing Listener 
> Listening to changes in canvas
```java
paintView.addDrawingChangeListener(new DrawingChangeListener() {
    @Override
        public void onTouchStart(float x, float y) {
            //paintView2.startTouch(x,y); 
        }
        
    @Override
        public void onDrawingChange(float x, float y) {
            //paintView2.drawToCanvas(x,y);
        }
});
```

![](https://github.com/tolunaykan/DrawingView-Library-Android/blob/master/syncDrawingGif.gif?raw=true)


### 5. Draw to Canvas Programmatically 
> You can draw to canvas without touching. Look at example at 4
```java
paintView.startTouch(x,y);    //Setting initial touch on canvas
paintView.drawToCanvas(x,y);  //Drawing to canvas 
```

### 6. Other Preferences 
```java
paintView.setBackgroundColor(Color.WHITE);    //Setting background color of the canvas
paintView.setTouchTolerance(2f);              //Setting touch tolerance
paintView.clearCanvas();                      //Clearing canvas
paintView.getCanvasBitmap();                  //Getting image bitmap of canvas
```


## Developer
- Tolunay Kandırmaz
###### [LinkedIn](https://www.linkedin.com/in/tolunay-kand%C4%B1rmaz-12a734158/ "LinkedIn")
<a href = "mailto:tolunaykandirmaz@gmail.com">tolunaykandirmaz@gmail.com</a>
