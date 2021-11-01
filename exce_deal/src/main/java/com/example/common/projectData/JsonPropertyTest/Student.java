package com.example.common.projectData.JsonPropertyTest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Student {

    @JsonProperty(value = "real_name")
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "realName='" + realName + '\'' +
                '}';
    }

    public static void main(String[] args) {

        Student student = new Student();
        student.setRealName("zhangsan");
        System.out.println(student.toString());
    }
}
