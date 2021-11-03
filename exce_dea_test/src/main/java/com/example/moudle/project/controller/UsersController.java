package com.example.moudle.project.controller;

import com.example.common.constant.Result;
import com.example.moudle.project.entity.Users;
import com.example.moudle.project.service.UsersService;
import com.example.moudle.project.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hello")
public class UsersController {
    @Autowired
    UsersServiceImpl userService;

    @Autowired
    UsersService usersService;

    @GetMapping("/page")
    public Result<Object> get(){
        List<Users> data = userService.getName();
        return new Result<>().ok(data);
    }

    @PostMapping("/add")
    public Result<Users> add(@RequestBody(required = false) Users users){
        userService.save(users);
        userService.StartTestTask();
        return new Result<>();
    }

    @PostMapping("/update")
    public Result<Users> updataExecptionInfor (@RequestBody Users users){
        userService.updataExecptionInfor(users);
        usersService.StartTestTask();
        return new Result<>();
    }

    @DeleteMapping("/delete")
    public Result<Users> delete(@RequestParam int id){
        userService.removeById(id);
        usersService.StartTestTask();
        return new Result<>();
    }

    @GetMapping("/getTestHTTP")
    public String getTestHTTP(HttpServletRequest httpServletRequest){

        String requestAppKey = httpServletRequest.getParameter("appKey");
            Cookie[] s = httpServletRequest.getCookies();
            String string = httpServletRequest.getRequestedSessionId();
        String string2 = httpServletRequest.getMethod();
        httpServletRequest.changeSessionId();
        return string;
    }
}
