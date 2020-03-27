package com.andavid.crash.demo;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.orhanobut.logger.Logger;

/**
 * @author djk
 * @since 2020/03/27
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

  private Thread.UncaughtExceptionHandler mSystemDefaultHandler = null;
  private Thread.UncaughtExceptionHandler mExtraHandler = null;
  private static CrashHandler INSTANCE = null;

  private CrashHandler() {}

  public static CrashHandler getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CrashHandler();
    }
    return INSTANCE;
  }

  public void init() {
    // 先将系统默认的 UncaughtException 处理器保存下来
    mSystemDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    // 设置当前类为程序的默认异常处理类
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  @Override
  public void uncaughtException(@NonNull Thread thread, @NonNull Throwable ex) {
    handleException(ex);
    if (mExtraHandler != null) {
      mExtraHandler.uncaughtException(thread, ex);
    } else if (mSystemDefaultHandler != null) {
      mSystemDefaultHandler.uncaughtException(thread, ex);
    }
  }

  public void setExtraHandler(Thread.UncaughtExceptionHandler handler){
    mExtraHandler = handler;
  }

  private boolean handleException(Throwable ex) {
    if(ex == null){
      return false;
    }

    new Thread(){
      @Override
      public void run() {
        Looper.prepare();
        Logger.d("app crashed at " + System.currentTimeMillis());
        Looper.loop();
      };
    }.start();

    return true;
  }

}
