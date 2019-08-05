package com.andavid.touchevent.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

public class View1 extends View {
  public View1(Context context) {
    super(context);
  }

  public View1(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public View1(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
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
