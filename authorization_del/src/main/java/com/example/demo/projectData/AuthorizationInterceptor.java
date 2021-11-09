//package com.example.demo.projectData;
//
//import cn.ecasoft.SpringContextUtil;
//import cn.ecasoft.basic.JsonData;
//import cn.ecasoft.basic.UserInfo;
//import cn.ecasoft.redis.RedisService;
//import cn.ecasoft.util.EncryptUtil;
//import cn.ecasoft.util.JwtUtil;
//import cn.ecasoft.utils.DBhelper;
//import cn.ecasoft.utils.StringUtils;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 数据交互鉴权拦截器
// *
// * @author yangsen
// * @date 2021/5/13 19:50
// */
//@Slf4j
//@Component
//public class AuthorizationInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private DBhelper dBhelper;
//    @Autowired
//    private EncryptUtil encryptUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String access_token = request.getHeader("token");
//        if (StringUtils.isBlank(access_token)) {
//            access_token = request.getHeader("Authorization");
//        }
//
//        if (StringUtils.isBlank(access_token)) {
//            access_token = request.getParameter("token");
//        }
//        if (StringUtils.isBlank(access_token)) {
//            // 鉴权参数在请求头还是请求参数中都可
//            String appKey = StringUtils.isNotBlank(request.getHeader("appKey")) ? request.getHeader("appKey") : request.getParameter("appKey");
//            String sign = StringUtils.isNotBlank(request.getHeader("sign")) ? request.getHeader("sign") : request.getParameter("sign");
//            String time = StringUtils.isNotBlank(request.getHeader("time")) ? request.getHeader("time") : request.getParameter("time");
//            if (StringUtils.isBlank(appKey) || StringUtils.isBlank(sign) || StringUtils.isBlank(time)) {
//                throw new Exception("缺少鉴权参数");
//            }
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("APPKEY", appKey);
//            Map accessMaps = dBhelper.QueryMap("", "data-select-userInf-appkey", map);
//            if (CollectionUtils.isEmpty(accessMaps)) {
//                throw new Exception("此appKey不存在！");
//            }
//            String appSecret = accessMaps.get("APPSECRET").toString();
//            if (!sign.equals(encryptUtil.getSign(appKey + appSecret + time))) {
//                throw new Exception("没有权限，非法签名！");
//            }
//            request.setAttribute("appName", accessMaps.get("APPNAME").toString());
//            request.setAttribute("userId", accessMaps.get("USERID").toString());
//            request.setAttribute("appKey", accessMaps.get("APPKEY").toString());
//            return true;
//        }
//        Claims claims = JwtUtil.parseJWT(access_token);
//        if (claims == null || "".equals(claims)) {
//            //验证不通过
//            throw new Exception("token无效");
//        }
//        String sessionId = (String) claims.get("sessionId");
//        if (sessionId == null || "".equals(sessionId)) {
//            //验证不通过
//            throw new Exception("token无效");
//        }
//        RedisService redisService = SpringContextUtil.getBean(RedisService.class);
//        Object r = redisService.get(sessionId);
//        if (r == null) {
//            //验证不通过
//            throw new Exception("token无效");
//        }
//        String user = r.toString();
//        if (user == null) {
//            //验证不通过
//            throw new Exception("token无效");
//        }
//        UserInfo userInfo = JsonData.parseObject(user, UserInfo.class);
//        if (userInfo == null) {
//            //验证不通过
//            throw new Exception("token无效");
//        }
//        request.setAttribute("userName", userInfo.getUserName());
//        request.setAttribute("userId", userInfo.getUserId());
//        return true;
//    }
//}
