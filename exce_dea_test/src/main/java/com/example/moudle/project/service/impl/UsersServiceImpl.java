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
    public List<Users> getName() {
        List<Users> list = new ArrayList<>();
        list = baseMapper.getNameUser();
        return list;
    }

    @Override
    public Users getOneUser(Integer id) {

        Users users = baseMapper.selectById(id);
        return users;
    }

    @Override
    public int updataExecptionInfor(Users users) {

        LambdaUpdateWrapper<Users> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Users::getId, users.getId());
        int a = baseMapper.update(users, queryWrapper);
        return a;
    }

    @Override
    public Users getOneByUrl(String url) {
        LambdaUpdateWrapper<Users> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Users::getTaskName, url);
        Users a = baseMapper.selectOne(queryWrapper);
        return a;
    }


    /**
     * ????????????
     */
    public void doGetTestOne(String url, String personId) throws Exception {
        Users users = getOneByUrl(url);
        MseRecord mseRecord = new MseRecord();
        SendEmainT send = new SendEmainT();
        //send.sendEmail("1104563335@qq.com","??????????????????????????????");
        // ??????Http?????????(???????????????:???????????????????????????;??????:?????????HttpClient???????????????????????????)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // ??????Get??????
        HttpGet httpGet = new HttpGet(url);
        // ????????????
        CloseableHttpResponse response = null;
        try {
            // ??????????????????(??????)Get??????
            response = httpClient.execute(httpGet);
            String status = String.valueOf(response.getStatusLine().getStatusCode());

            if (status.equals("200")) {
                usersService.setTaskIsNormal(url, "200");
            } else {
                usersService.setTaskIsNormal(url, String.valueOf(response.getStatusLine().getStatusCode()));
                mseRecord.setId(users.getId());
                mseRecord.setTaskName(url);
                mseRecord.setStatus(String.valueOf(response.getStatusLine().getStatusCode()));
                mseRecord.setMessage("????????????");
                mseRecord.setCreateTime(LocalDateTime.now());
                mseRecordService.save(mseRecord);
                send.sendEmail(users.getPersonId(), String.valueOf(mseRecord));
            }
        } catch (Exception e) {
            usersService.setTaskIsNormal(url, "?????????");
            mseRecord.setId(users.getId());
            mseRecord.setTaskName(url);
            mseRecord.setStatus("?????????");
            mseRecord.setMessage("????????????");
            mseRecord.setCreateTime(LocalDateTime.now());
            mseRecordService.save(mseRecord);
            usersService.setTaskType("2", url);
            send.sendEmail(users.getPersonId(), String.valueOf(mseRecord) + "");
        } finally {
            try {
                // ????????????
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
     * ??????????????????
     */
    public void testRunnable(String url, String personId) {
        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (flag) {

                    try {
                        doGetTestOne(url, personId);
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
     * ???????????????????????????????????????????????????
     */
    @Override
    public void setTaskType(String taskType, String taskName) {
        baseMapper.setTaskType(taskType, taskName);
    }

    /**
     * ??????????????????????????????
     */
    @Component
    public class ApplicationRunnerImpl implements ApplicationRunner {

        int data[] = new int[3];

        @Override
        public void run(ApplicationArguments args) throws Exception {
            StartTestTask();
        }
    }

    /**
     * ????????????????????????
     */
    @Override
    public void StartTestTask() {

        //???1??????????????????????????????????????????
        flag = false;

        //???2????????????????????????????????????????????????
        List<Users> usersList = new ArrayList<>();
        usersList = baseMapper.getNameUser();
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //(3)??????????????????
        flag = true;
        System.out.println("??????????????????");
        for (int i = 0; i < usersList.size(); i++) {
            testRunnable(usersList.get(i).getTaskName(), usersList.get(i).getPersonId());
        }
    }

    /**
     * ???????????????????????????????????????????????????
     */
    @Override
    public void setTaskIsNormal(String url, String status) {
        baseMapper.setTaskIsNormal(url, status);
    }
}
