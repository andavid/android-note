package com.andavid.java.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateFixedThreadPool {

  public static void main(String[] args) {
    // 创建一个固定数目的、可重用的线程池
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    for (int i = 1; i <= 10; i++) {
      final int taskId = i;

      fixedThreadPool.submit(new Runnable() {
        @Override
        public void run() {
          System.out.println("线程：" + Thread.currentThread().getName() + " 正在执行 task: " + taskId);
        }
      });
    }

    fixedThreadPool.shutdown();
  }

}
