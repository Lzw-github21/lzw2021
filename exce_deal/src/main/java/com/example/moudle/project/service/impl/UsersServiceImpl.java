package com.example.moudle.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.SendEmainT;
import com.example.moudle.project.entity.MseRecord;
import com.example.moudle.project.entity.Users;
import com.example.moudle.project.mapper.UsersMapper;
import com.example.moudle.project.service.MseRecordService;
import com.example.moudle.project.service.UsersService;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    private boolean flag = true;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MseRecordService mseRecordService;

    @Override
    public List<Users> getName(){
        List<Users> list = new ArrayList<>();
        list = baseMapper.getNameUser();
        return list;
    }

    @Override
    public Users getOneUser(Integer id){

        Users users = baseMapper.selectById(id);
        return users;
    }

    @Override
    public int updataExecptionInfor(Users users){

        LambdaUpdateWrapper<Users> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Users::getId,users.getId());
        int a = baseMapper.update(users,queryWrapper);
        return a;
    }

    @Override
    public Users getOneByUrl(String url){
        LambdaUpdateWrapper<Users> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Users::getTaskName,url);
        Users a = baseMapper.selectOne(queryWrapper);
        return a;
    }


    /**
     * 监测任务
     */
    public void doGetTestOne(String url,String personId) throws Exception {
        Users users =getOneByUrl(url);
        MseRecord mseRecord = new MseRecord();
        SendEmainT send = new SendEmainT();
        //send.sendEmail("1104563335@qq.com","接口异常，请尽快处理");
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            String status = String.valueOf(response.getStatusLine().getStatusCode());

            if(status.equals("200")){
                usersService.setTaskIsNormal(url,"200");
            }
            else {
                usersService.setTaskIsNormal(url,String.valueOf(response.getStatusLine().getStatusCode()));
                mseRecord.setId(users.getId());
                mseRecord.setTaskName(url);
                mseRecord.setStatus(String.valueOf(response.getStatusLine().getStatusCode()));
                mseRecord.setMessage("访问错误");
                mseRecord.setCreateTime(LocalDateTime.now());
                mseRecordService.save(mseRecord);
                send.sendEmail(users.getPersonId(),String.valueOf(mseRecord));
            }
        } catch (Exception e) {
            usersService.setTaskIsNormal(url,"无响应");
            mseRecord.setId(users.getId());
            mseRecord.setTaskName(url);
            mseRecord.setStatus("无响应");
            mseRecord.setMessage("访问错误");
            mseRecord.setCreateTime(LocalDateTime.now());
            mseRecordService.save(mseRecord);
            usersService.setTaskType("2",url);
            send.sendEmail(users.getPersonId(),String.valueOf(mseRecord));
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建监测线程
     */
    public void testRunnable(String url,String personId) {
        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while(flag){

                    try {
                        doGetTestOne(url,personId);
                        System.out.println(url);
                        System.out.println("123");
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    /**
     * 设置监测状态，将状态修改为正在监测
     */
    @Override
    public void setTaskType(String taskType,String taskName){
        baseMapper.setTaskType(taskType,taskName);
    }

    /**
     * 项目启动执行监测方法
     */
//    @Component
//    public class ApplicationRunnerImpl implements ApplicationRunner {
//
//        int data[] = new int[3];
//        @Override
//        public void run(ApplicationArguments args) throws Exception {
//            StartTestTask();
//        }
//    }

    /**
     * 开启所有监测任务
      */
    @Override
    public void StartTestTask() {

        //（1）清空所以正在运行的监测线程
        flag = false;

        //（2）查询数据库，得到待监测任务列表
        List<Users> usersList = new ArrayList<>();
        usersList = baseMapper.getNameUser();
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //(3)创建监测线程
        flag = true;
        System.out.println("监测线程启动");
        for (int i = 0; i <usersList.size() ; i++) {
            testRunnable(usersList.get(i).getTaskName(),usersList.get(i).getPersonId());
        }
    }

    /**
     * 设置探测是否正常，写入最新探测时间
     */
    @Override
    public void setTaskIsNormal(String url,String status){
        baseMapper.setTaskIsNormal(url,status);
    }
}
