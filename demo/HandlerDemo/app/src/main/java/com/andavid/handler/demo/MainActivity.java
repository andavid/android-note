package com.andavid.handler.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initLogger();
    initView();
  }

  private void initLogger() {
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(true)
        .methodCount(2)
        .tag("HandlerDemo")
        .build();

    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
  }

  private void initView() {
    findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        createThreadWithHandlerThread();
        startIntentService();
      }
    });
  }

  public void createThreadWithHandlerThread() {
    HandlerThread thread = new HandlerThread("handler_thread");
    thread.start();

    MyHandler threadHandler = new MyHandler(thread.getLooper());
    threadHandler.sendEmptyMessage(0);
  }

  public void startIntentService() {
    final Intent i1 = new Intent(this, MyIntentService.class);
    Bundle bundle = new Bundle();
    bundle.putString("taskName", "task1");
    i1.putExtras(bundle);
    startService(i1);

    Intent i2 = new Intent(this, MyIntentService.class);
    Bundle bundle2 = new Bundle();
    bundle2.putString("taskName", "task2");
    i2.putExtras(bundle2);
    startService(i2);

    startService(i1);
    startService(i2);

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        // 在上面所有 intent 被处理完后，系统会自动关闭服务
        // 这时候 startService 会重新走 onCreate 方法，会创建一个新的工作线程
        startService(i1);
      }
    }, 2000);
  }

}
