package com.andavid.touchevent.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

public class ViewGroupA extends RelativeLayout {
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
  public boolean dispatchTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      Logger.d("ACTION_DOWN");
      // 禁止父亲拦截事件，后续的 MOVE 和 UP 事件不会调用 RootView 的 onInterceptTouchEvent 方法
      getParent().requestDisallowInterceptTouchEvent(true);
    } else if (event.getAction() == MotionEvent.ACTION_UP) {
      Logger.d("ACTION_UP");
    }
    return super.dispatchTouchEvent(event);
//    return true;
//    return false;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      Logger.d("ACTION_DOWN");
    } else if (event.getAction() == MotionEvent.ACTION_UP) {
      Logger.d("ACTION_UP");
    }
    return super.onInterceptTouchEvent(event);
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
