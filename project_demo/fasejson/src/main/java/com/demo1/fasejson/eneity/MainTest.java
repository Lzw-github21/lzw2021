package com.demo1.fasejson.eneity;

import com.alibaba.fastjson.JSON;

/**
 * @author lizhiwei
 * @date 2021/11/4 23:20
 */
public class MainTest {

    public static void main(String[] args) {
        Grade group = new Grade();
        group.setId(0L);
        group.setName("admin");

        Student student = new Student();
        student.setId(2L);
        student.setName("guest");

        Student rootUser = new Student();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addStudent(student);
        group.addStudent(rootUser);

        // 转换为 JSON
        String jsonString = JSON.toJSONString(group);
        System.out.println("JSON字符串：" + jsonString);

        // 转换为 对象BEAN
        Grade grade = JSON.parseObject(jsonString, Grade.class);
        System.out.println("JavaBean对象：" + grade);
    }
}