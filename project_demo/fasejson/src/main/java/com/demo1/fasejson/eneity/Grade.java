package com.demo1.fasejson.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhiwei
 * @date 2021/11/4 23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    private Long id;
    private String name;
    private List<Student> users = new ArrayList<Student>();

    // 省略 setter、getter

    public void addStudent(Student student) {
        users.add(student);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
