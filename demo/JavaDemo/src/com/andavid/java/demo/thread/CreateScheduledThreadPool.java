package com.andavid.java.demo.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreateScheduledThreadPool {

  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        Date now = new Date();
        System.out.println("线程：" + Thread.currentThread().getName() + " Time: " + now);
      }
    }, 500, 500, TimeUnit.MILLISECONDS);

    Thread.sleep(5000);
    scheduledExecutorService.shutdown();
  }

}
