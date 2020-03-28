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
