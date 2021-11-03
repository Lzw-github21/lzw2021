package com.example.common.projectData;

public class UrlTest {
    private static void testRunnable(String url) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while(true){
                   // HttpClient httpClient = new HttpClient();
                   // httpClient.doGetTestOne(url);
                    try {

                        Thread.sleep(3900);
                        System.out.println(url);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }
    public static void main(String[] args) {
        testRunnable("http://localhost:18850/hello/getOne");
    }
}
