package com.example.moudle;

import com.example.common.util.SendEmainT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExceDealApplicationTests {

    @SneakyThrows
    @Test
    void contextLoads() {
        SendEmainT send = new SendEmainT();
        send.sendEmail("1104563335@qq.com", "接口异常，请尽快处理");
    }
}
