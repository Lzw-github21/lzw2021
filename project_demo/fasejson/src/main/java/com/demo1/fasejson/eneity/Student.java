package com.demo1.fasejson.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lizhiwei
 * @date 2021/11/4 23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long   id;
    private String name;

    // 省略 setter、getter

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
