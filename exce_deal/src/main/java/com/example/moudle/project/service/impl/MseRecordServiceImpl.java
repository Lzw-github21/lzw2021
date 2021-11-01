package com.example.moudle.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.moudle.project.entity.MseRecord;
import com.example.moudle.project.mapper.MseRecordMapper;
import com.example.moudle.project.service.MseRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MseRecordServiceImpl extends ServiceImpl<MseRecordMapper, MseRecord> implements MseRecordService {

    @Override
    public List<MseRecord> getMse(){
        return baseMapper.getMse();
    }
    @Override
    public int updateMseRecord(MseRecord mseRecord){
        LambdaUpdateWrapper<MseRecord> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(MseRecord::getId,mseRecord.getId());
        int a = baseMapper.update(mseRecord,queryWrapper);
        return a;
    }
}
