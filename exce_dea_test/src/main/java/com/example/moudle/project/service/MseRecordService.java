package com.example.moudle.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.moudle.project.entity.MseRecord;

import java.util.List;

public interface MseRecordService extends IService<MseRecord> {

    List<MseRecord> getMse();

    int updateMseRecord(MseRecord mseRecord);
}
