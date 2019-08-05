package com.andavid.touchevent.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(false)
        .methodCount(1)
        .tag("TouchEventDemo")
        .build();

    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      Logger.d("ACTION_DOWN");
    } else if (event.getAction() == MotionEvent.ACTION_UP) {
      Logger.d("ACTION_UP");
    }
    return super.dispatchTouchEvent(event);
//    return true;
//    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      Logger.d("ACTION_DOWN");
    } else if (event.getAction() == MotionEvent.ACTION_UP) {
      Logger.d("ACTION_UP");
    }
    return super.onTouchEvent(event);
//    return true;
//    return false;
  }
}
