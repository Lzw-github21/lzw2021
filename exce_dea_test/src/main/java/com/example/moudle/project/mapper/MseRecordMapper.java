package com.example.moudle.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.moudle.project.entity.MseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MseRecordMapper extends BaseMapper<MseRecord> {

    @Select("SELECT * FROM mse_record")
    List<MseRecord> getMse();

}
