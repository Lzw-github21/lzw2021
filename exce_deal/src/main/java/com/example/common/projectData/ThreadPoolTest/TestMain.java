package com.example.common.projectData.ThreadPoolTest;

public class TestMain {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(-5);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        // 调用销毁方法，不执行剩下的任务
        threadPool.destory(false);
    }
}
