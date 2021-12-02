package cn.huanzi.qch.springboottimer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTimerApplicationTests {

    @Test
    public void contextLoads() {

        TestScheduler2 testScheduler2 = new TestScheduler2();
        testScheduler2.getAllTbTask();
    }

}
