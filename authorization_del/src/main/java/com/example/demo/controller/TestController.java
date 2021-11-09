package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhiwei
 * @date 2021/11/4 18:56
 */
@RequestMapping("/text")
@RestController
public class TestController {

    @GetMapping("/login")
    public String Hello(){
        return "访问通过";
    }
}
