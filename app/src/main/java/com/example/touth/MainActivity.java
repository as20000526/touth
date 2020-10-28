package com.example.touth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String DEBUG_TAG = "Velocity";
    private static String DATABASE_TABLE = "students";
    private VelocityTracker mVelocityTracker = null;
    private View myview;
    static SQLiteDatabase db;
    private StdDBHelper dbHelper;
    private TextView start;
    private report report;
    List<listdata> data =new ArrayList<>();

    // 建立SQLiteOpenHelper物件

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                Log.d("123", "X velocity: " + mVelocityTracker.getXVelocity(pointerId));
                Log.d("123", "Y velocity: " + mVelocityTracker.getYVelocity(pointerId));

                float horizontalOffset = mVelocityTracker.getXVelocity(pointerId);
                float verticalOffset = mVelocityTracker.getYVelocity(pointerId);
                String x = String.valueOf(horizontalOffset);
                String y= String.valueOf(verticalOffset);
                String insert = "INSERT INTO students(  X,Y)" +
                        "VALUES (" + x +","+ y +");";
                db.execSQL(insert);
                break;
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        start=findViewById(R.id.start);
        start.setOnClickListener(this);
//        myview=findViewById(R.id.myview);
        dbHelper = new StdDBHelper(this);
        db = dbHelper.getWritableDatabase(); // 開啟資料庫
    }
    protected void onStop() {
        super.onStop();
        db.close(); // 關閉資料庫
    }







    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                gotostart() ;
                break;

        }
    }
private void gotostart(){
    for ( int i=0;i<data.size();i++){
//        report=new report(Float.parseFloat(data.get(i).getx()),Float.parseFloat(data.get(i).gety()));
//        final Float x= Float.parseFloat(data.get(i).getx());
//        final Float y= Float.parseFloat(data.get(i).gety());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Instrumentation inst = new Instrumentation();
//                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
//                            MotionEvent.ACTION_DOWN, x,y, 0));
//                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
//                            MotionEvent.ACTION_UP,x,y, 0));
//                    Log.d("點擊位置", ""+x+y);
//                }catch(Exception e) {
//                    Log.e("Exception when ", e.toString());
//                }
//                //線程睡眠3s
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    // TODO Auto-generated catch block
////                    e.printStackTrace();
////                }
//            }
//        }).start();

    }
    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
    startActivity(intent);

}



}

