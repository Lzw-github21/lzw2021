package com.example.demo.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 数据交互鉴权拦截器
 *
 * @author yangsen
 * @date 2021/5/13 19:50
 */
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//            String appKey =request.getHeader("appKey");
//            if (StringUtils.isBlank(appKey)) {
//                throw new Exception("缺少鉴权参数");
//            }
//            if(!appKey.equals("lizhiwei"))
//            {
//                throw new Exception("鉴权参数不正确");
//            }
//            request.setAttribute("appKey", appKey);
            return true;
    }
}
