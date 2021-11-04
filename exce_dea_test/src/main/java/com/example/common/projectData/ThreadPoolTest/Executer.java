package com.example.common.projectData.ThreadPoolTest;

/**
 * 线程池必须实现的接口
 *
 * @author 陈康明 qq：1123181523
 * @date 2019年3月10日
 */
public interface Executer {

    /**
     * 添加工作方法
     */
    void execute(Runnable runnable);
}
