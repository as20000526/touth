package com.example.touth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Instrumentation;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


import static com.example.touth.MainActivity.db;

public class MainActivity2 extends AppCompatActivity {

    List<listdata> data =new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    ArrayList<String> x = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new MyRecyclerViewAdapter(this, x);




         final Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

                Log.v("123",""+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                for (int i=0;i<data.size();i++) {
                    x.add(data.get(i).getx());
                    Log.d("x", "onComplete: "+x);
                }
            }

        };

        Observable.create(new ObservableOnSubscribe<String>(){
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                listdata ll;
                String sarchsqlite=" SELECT X,Y" +"FROM student";
                String selectSQL = "SELECT * FROM students";
                Cursor cursor = db.rawQuery(selectSQL,null);
                while (cursor.moveToNext()) { String id = cursor.getString(cursor.getColumnIndex("id"));
                String x = cursor.getString(cursor.getColumnIndex("X"));
                String y = cursor.getString(cursor.getColumnIndex("Y"));
                ll=new listdata(x,y,id);
                data.add(ll);
                }
                e.onComplete();
            }
        }).subscribe(observer);//订阅

    }

    }



