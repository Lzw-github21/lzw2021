package cn.huanzi.qch.springbootaop;

import org.springframework.stereotype.Service;

/**
 * @author lizhiwei
 * @date 2021/11/5 9:52
 */

@Service
public class Serve {

    public void test() throws Exception {
        if(true){
            throw new Exception();
        }
    }
}
