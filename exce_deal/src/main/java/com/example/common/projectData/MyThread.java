//package com.example.common.projectData;
//
//import com.example.common.util.HttpClient;
//import com.example.moudle.project.entity.HttpEntityDto;
//
//public class MyThread extends Thread {
//    boolean flag = true;
//    HttpEntityDto finallStatus = new HttpEntityDto();
//
//    public void run() {

//        while (flag) {
//            com.example.common.util.HttpClient httpClient = new HttpClient();
//            HttpEntityDto httpEntityDto = httpClient.doGetTestOne("http://localhost:18850/hello/page");
//            try {
//                if (httpEntityDto.getStatus() == 200) {
//                    finallStatus.setStatus(httpEntityDto.getStatus());
//                    finallStatus.setMessage("运行正常");
//                    Thread.sleep(1000);
//                } else {
//                    finallStatus.setStatus(httpEntityDto.getStatus());
//                    finallStatus.setMessage(httpEntityDto.getMessage());
//                    flag = false;
//                }
//
//            } catch (InterruptedException e) {
//                finallStatus.setStatus(404);
//                finallStatus.setMessage("运行错误");
//                e.printStackTrace();
//            }
//        }
//    }
//
//
////    public static void main(String[] args) {
////
////        MyThread t1 = new MyThread();
////        t1.start();
////        while (true) {
////            if (t1.isAlive()) {
////                System.out.println("Thread has not finished");
////            } else {
////                System.out.println("Finished");
////                System.out.println(t1.finallStatus);
////                break;
////            }
////        }
////        System.out.println(t1.finallStatus);
////    }
//}
