package com.andavid.touchevent.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

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

    findViewById(R.id.vga).setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          Logger.d("ViewGroupA onTouch ACTION_DOWN");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
          Logger.d("ViewGroupA onTouch ACTION_UP");
        }
        return false;
      }
    });

    findViewById(R.id.vga).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d("ViewGroupA onClick");
      }
    });

    findViewById(R.id.view1).setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          Logger.d("View1 onTouch ACTION_DOWN");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
          Logger.d("View1 onTouch ACTION_UP");
        }
        return true;
      }
    });

    findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d("View1 onClick");
      }
    });
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
