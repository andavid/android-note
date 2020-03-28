package com.andavid.crash.demo;

import android.os.Handler;
import android.os.Looper;
import com.orhanobut.logger.Logger;

/**
 * @author djk
 * @since 2020/03/28
 */
public class AnrWatch extends Thread {

  private final Handler mUiHandler;
  private final long mTimeoutInterval;
  private volatile int mTick;
  private final Runnable mTicker;
  private boolean mIsStop;

  public AnrWatch() {
    super();
    setName("ANR-WatchDog " + getName());
    mUiHandler = new Handler(Looper.getMainLooper());
    mTimeoutInterval = 5000;
    mTick = 0;
    mTicker = new Runnable() {
      @Override
      public void run() {
        AnrWatch.this.mTick = (AnrWatch.this.mTick + 1) % 2147483647;
      }
    };
    mIsStop = false;
  }

  public void setStop(boolean stop) {
    mIsStop = stop;
  }

  @Override
  public void run() {
    while (true) {
      if (!mIsStop && !isInterrupted()) {
        int lastTick = mTick;
        mUiHandler.post(mTicker);
        try {
          Thread.sleep(mTimeoutInterval);
        } catch (InterruptedException e) {
          return;
        }

        if (mTick != lastTick) {
          continue;
        }

        Logger.d("App Not Responding");
        Thread mainThread = Looper.getMainLooper().getThread();
        StackTraceElement[] trace = mainThread.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement traceElement : trace) {
          sb.append("\t").append("at ").append(traceElement).append("\n");
        }
        Logger.d(sb.toString());

        return;
      }

      return;
    }
  }

}
