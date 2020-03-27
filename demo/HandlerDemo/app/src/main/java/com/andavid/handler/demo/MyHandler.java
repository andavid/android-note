package com.andavid.handler.demo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.orhanobut.logger.Logger;

/**
 * @author djk
 * @since 2019/08/08
 */
public class MyHandler extends Handler {

  public MyHandler(Looper looper) {
    super(looper);
  }

  @Override
  public void handleMessage(Message msg) {
    Logger.d("MyHandler handle message: " + msg.toString());

    Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    mainThreadHandler.post(new Runnable() {
      @Override
      public void run() {
        Logger.d("run on main thread by handler.post");
      }
    });

    Message message = Message.obtain(mainThreadHandler, new Runnable() {
      @Override
      public void run() {
        Logger.d("run on main thread by handler.sendMessage");
      }
    });
    mainThreadHandler.sendMessage(message);
  }
}
