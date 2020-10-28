    package com.example.touth;

    import android.annotation.SuppressLint;
    import android.app.Instrumentation;
    import android.os.SystemClock;
    import android.util.Log;
    import android.view.MotionEvent;

    public class report extends Thread {
        private Float x,y;
        //400,689


        @Override
        public void run() {
            while(true)
            {
                //利用ProcessBuilder執行shell命令
                /*String[] order = {
                        "input",
                        "tap",
                        "" + x,
                        "" + y
                };
                Log.d("點擊位置", x+","+y);
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                // 可以不用在 Activity 中增加任何處理，各 Activity 都可以響應
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_DOWN, x, y, 0));
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_UP, x, y, 0));
                    Log.d("點擊位置", x+","+y);
                }catch(Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
                //線程睡眠3s
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        public report(Float x,Float y){
            this.x=x;
            this.y=y;
        }
    }
