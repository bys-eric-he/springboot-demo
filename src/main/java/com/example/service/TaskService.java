package com.example.service;

import com.example.dto.TaskDto;

import java.util.List;

/**
 * Created by eric on 2017/3/2.
 */
public interface TaskService {
    /**
     * 所有任务列表
     */
    List<TaskDto> list();
    /**
     * 保存定时任务
     * @param dto
     */
    @SuppressWarnings("unchecked")
    void addJob(TaskDto dto);

    /**
     * 修改定时任务
     * @param dto
     */
    void edit(TaskDto dto);

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    void delete(String jobName, String jobGroup);

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    void pause(String jobName, String jobGroup);

    /**
     * 重新开始任务
     * @param jobName
     * @param jobGroup
     * 2016年10月10日上午9:40:58
     */
    void resume(String jobName, String jobGroup);
}
