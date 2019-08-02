package com.andavid.touchevent.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.andavid.touchevent.demo.Static;

public class View1 extends View {
  private static final String TAG = Static.TAG4;

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
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.i(TAG, Static.dispatchTouchEvent + ev.getAction());
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(TAG, Static.onTouchEvent + event.getAction());
    return super.onTouchEvent(event);
//        return true;
  }
}
