package com.andavid.handler.demo;

import android.app.IntentService;
import android.content.Intent;
import com.orhanobut.logger.Logger;

/**
 * @author djk
 * @since 2019/08/08
 */
public class MyIntentService extends IntentService {

  public MyIntentService() {
    super("MyIntentService");
  }

  /**
   * Creates an IntentService.  Invoked by your subclass's constructor.
   *
   * @param name Used to name the worker thread, important only for debugging.
   */
  public MyIntentService(String name) {
    super(name);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    String taskName = intent.getExtras().getString("taskName");
    switch (taskName) {
      case "task1":
        Logger.d("do task1");
        break;
      case "task2":
        Logger.d("do task2");
        break;
      default:
    }
  }

  @Override
  public void onCreate() {
    Logger.d("onCreate");
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Logger.d("onStartCommand");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    Logger.d("onDestroy");
    super.onDestroy();
  }
}
