package com.andavid.java.demo.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateSingleThreadPool {

  public static void main(String[] args) throws InterruptedException {
    // 创建一个单线程化的线程池，使用唯一的工作线程来执行任务，保证所有的任务按先进先出的顺序执行
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    for (int i = 1; i <= 5; i++) {
      final int taskId = i;
      singleThreadExecutor.submit(new Runnable() {
        @Override
        public void run() {
          System.out.println("线程：" + Thread.currentThread().getName() + " 正在执行 task: " + taskId);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

    singleThreadExecutor.shutdown();
  }

}
