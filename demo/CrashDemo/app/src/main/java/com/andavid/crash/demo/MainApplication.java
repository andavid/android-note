package com.andavid.crash.demo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author djk
 * @since 2020/03/27
 */
public class MainApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    initLogger();
    initCrashHandler();
    startCrashCatch();
    startAnrWatch();
  }

  private void initLogger() {
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .tag("CrashDemo")
        .build();

    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
      @Override
      public boolean isLoggable(int priority, @Nullable String tag) {
        return BuildConfig.DEBUG;
      }
    });
  }

  private void initCrashHandler() {
    CrashHandler.getInstance().init();

    Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(ex, "catch exception");
      }
    };

    CrashHandler.getInstance().setExtraHandler(handler);
  }

  private void startCrashCatch() {
    // https://www.jianshu.com/p/01b69d91a3a8?d=1556064744823
    // https://github.com/android-notes/Cockroach

    // 1. 主线程的异常被 UncaughtExceptionHandler 捕获到了
    // 如果异常交给系统默认的异常处理器 KillApplicationHandler，会导致 crash
    // 如果异常不交给系统默认的异常处理器，会导致 ANR

    // 2. 通过下面这种方式，拦截主线程异常，使得即使主线程发生 crash 了，应用还能继续使用

    // 3. 所有子线程抛出的异常也可以被 UncaughtExceptionHandler 捕获到
    // 并且即使不把异常交给系统默认的异常处理器处理也不会导致 crash
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            Looper.loop();
          } catch (Exception e) {
            Logger.e(e, "main looper exception");
            UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
            if (handler != null) {
              Logger.d("default uncaught exception handler == " + handler.getClass().getName());
              handler.uncaughtException(Thread.currentThread(), e);
            }
          }
        }
      }
    });
  }

  private void startAnrWatch() {
    AnrWatch anrWatchDog = new AnrWatch();
    anrWatchDog.start();
  }

}
