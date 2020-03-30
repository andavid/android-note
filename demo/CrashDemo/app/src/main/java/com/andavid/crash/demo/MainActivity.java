package com.andavid.crash.demo;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.orhanobut.logger.Logger;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d("test crash");
        "crash".substring(10);
      }
    });

    findViewById(R.id.btn_anr).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d("test anr");
        printAllThread();

        try {
          Thread.sleep(1000 * 10 * 2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void printAllThread() {
    Logger.d("---- print all thread start ----");

    Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
    Set<Thread> threads = stacks.keySet();

    for (Thread thread : threads) {
      StringBuilder sb = new StringBuilder();
      sb.append("thread name: ").append(thread.getName()).append("\n")
          .append("thread id: ").append(thread.getId()).append("\n")
          .append("thread state: ").append(thread.getState()).append("\n")
          .append("thread priority: ").append(thread.getPriority()).append("\n")
          .append("thread group: ")
          .append((thread.getThreadGroup() == null) ? "null" : thread.getThreadGroup().getName())
          .append("\n");

      sb.append("thread stack trace: ").append("\n");
      StackTraceElement[] trace = stacks.get(thread);
      for (StackTraceElement traceElement : trace) {
        sb.append("  ").append("at ").append(traceElement).append("\n");
      }
      Logger.d(sb.toString());
    }

    Logger.d("---- print all thread end ----");
  }
}
