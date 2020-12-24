package com.andavid.java.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateCachedThreadPool {

  public static void main(String[] args) throws InterruptedException {
    // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    for (int i = 1; i <= 5; i++) {
      final int taskId = i;

      // 如果放开以下注释，在新的任务提交之前，线程已经处于空闲状态，可以被复用执行任务
//      Thread.sleep(1000);

      cachedThreadPool.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("线程：" + Thread.currentThread().getName() + " 正在执行 task: " + taskId);
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

    cachedThreadPool.shutdown();
  }

}
