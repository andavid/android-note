package com.andavid.touchevent.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.andavid.touchevent.demo.Static;

public class ViewGroupA extends RelativeLayout {
  private static final String TAG = Static.TAG3;

  public ViewGroupA(Context context) {
    super(context);
  }

  public ViewGroupA(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.i(TAG, Static.dispatchTouchEvent + ev.getAction());
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    Log.i(TAG, Static.onInterceptTouchEvent + ev.getAction());
    return super.onInterceptTouchEvent(ev);
//        return true;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(TAG, Static.onTouchEvent + event.getAction());
    return super.onTouchEvent(event);
//        return true;
  }
}
