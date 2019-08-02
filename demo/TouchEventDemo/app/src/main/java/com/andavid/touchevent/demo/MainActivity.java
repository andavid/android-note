package com.andavid.touchevent.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = Static.TAG1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.i(TAG, Static.dispatchTouchEvent + ev.getAction());
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(TAG, Static.onTouchEvent + event.getAction());
    return super.onTouchEvent(event);
  }
}
