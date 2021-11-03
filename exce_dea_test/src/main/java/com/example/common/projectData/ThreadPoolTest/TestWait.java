package com.example.common.projectData.ThreadPoolTest;

import jdk.nashorn.internal.objects.NativeJava;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 测试 wait和sleep方法
 * 按照网上说的：wait会释放锁，sleep不会释放锁，写个案例执行下。
 * @author 陈康明 qq：1123181523
 * @date 2019年3月10日
 */
public class TestWait {

    private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        TestWait test = new TestWait();
        WaitSleep w1 = test.new WaitSleep();
        WaitSleep w2 = test.new WaitSleep();
        WaitSleep w3 = test.new WaitSleep();
        w1.start();
        w2.start();
        w3.start();
    }

    class WaitSleep extends Thread{

        @Override
        public void run() {
            synchronized (list) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hhhhhhhh");
                    System.out.println((dateFormat.format(new Date())));
                    System.out.println(Thread.currentThread().getName()+"当前系统时间："+new SimpleDateFormat("hh:mm:ss").format(new Date()));
                    // 等待 10 秒， 看其他对象是否拿到锁
                    list.wait(10000);
                    //Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}