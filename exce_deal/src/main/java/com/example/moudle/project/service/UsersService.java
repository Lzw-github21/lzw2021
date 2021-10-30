package com.example.moudle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.moudle.project.entity.Users;

import java.util.List;

public interface UsersService extends IService<Users> {

    List<Users> getName();

    Users getOneUser(Integer id);

    int updataExecptionInfor(Users users);

    Users getOneByUrl(String url);

    void setTaskType(String taskType,String taskName);

    void StartTestTask( );

    void setTaskIsNormal(String url,String status);
}
