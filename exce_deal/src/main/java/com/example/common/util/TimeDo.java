//package com.example.common.util;
//
//import com.example.moudle.project.entity.HttpEntityDto;
//
//public class TimeDo {
//    public void testRunnable(String url) {
//        final long timeInterval = 2000;
//        boolean flag = true;
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                boolean flag = true;
//                int status;
//                String message;
//                HttpEntityDto finallStatus = new HttpEntityDto();
//                while (flag) {
//                    HttpClient httpClient = new HttpClient();
//                    HttpEntityDto httpEntityDto = httpClient.doGetTestOne(url);
//                    try {
//                        if(httpEntityDto.getStatus() == 200){
//                                finallStatus.setStatus(httpEntityDto.getStatus());
//                                finallStatus.setMessage("运行正常");
//                                Thread.sleep(timeInterval);
//                        }
//                        else {
//                            finallStatus.setStatus(404);
//                            finallStatus.setMessage(httpEntityDto.getMessage());
//                            flag =false;
//                        }
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }
//
//    public static void main(String[] args) {
//        //testRunnable("http://localhost:18850/hello/page");
//        TimeDo timeDo = new TimeDo();
//        timeDo.testRunnable("http://localhost:18850/hello/page");
//    }
//}
