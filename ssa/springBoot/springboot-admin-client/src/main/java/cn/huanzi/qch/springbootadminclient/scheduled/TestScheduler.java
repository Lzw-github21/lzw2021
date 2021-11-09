package cn.huanzi.qch.springbootadminclient.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class TestScheduler {
//    /**
//     * 每隔5秒执行一次：5****?
//            *
//            *每隔1分钟执行一次：0*/1***?
//            *
//            *每天23点执行一次：0 0 23**?
//            *
//            *每天凌晨1点执行一次：0 0 1**?
//            *
//            *每月1号凌晨1点执行一次：0 0 1 1*?
//     */
    @Scheduled(cron="0/30 * * * * ?")
    private void test(){
        //在能被30整除的秒数执行一次
        log.error("这句话每30秒打印一次  "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
