package com.example.moudle.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.moudle.project.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
@Component
public interface UsersMapper extends BaseMapper<Users> {
    @Select("SELECT * FROM exception_infor")
    List<Users> getNameUser();

    @Update("update exception_infor set task_type = #{taskType} where task_name = #{taskName}")
    void setTaskType(@RequestParam("taskType") String taskType, @RequestParam("taskName") String taskName);

    @Update("update exception_infor set is_normal = #{status},test_time=NOW() where task_name = #{url}")
    void setTaskIsNormal(String url, String status);
}
