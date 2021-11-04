package com.example.demo.mapper;

import com.example.demo.eneity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lizhiwei
 * @date 2021/11/4 16:19
 */
@Repository
public interface UserDao {

    //通过用户名查询用户
    public User findUserByUsername(String username);
}

