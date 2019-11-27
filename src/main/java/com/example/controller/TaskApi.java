package com.example.controller;

import com.example.dto.TaskDto;
import com.example.response.ResponseResult;
import com.example.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 2017/3/2.
 */
@RestController
@RequestMapping("/api/task")
@Api(description = "task 定时调度任务")
@ResponseResult
public class TaskApi {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加定时任务")
    public void addJob() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(2);
        taskDto.setJobName("com.example.job.SecondJob");
        taskDto.setJobGroup("MyFirstGroup");
        taskDto.setJobDescription("This is my second job!");
        taskDto.setCronExpression("0/2 * * * * ?");
        taskDto.setJobStatus("0"); //任务的状态，0：启用；1：禁用；2：已删除
        taskDto.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));

        taskService.addJob(taskDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改定时任务")
    public void updateJob() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(2);
        taskDto.setJobName("com.example.job.SecondJob");
        taskDto.setJobGroup("MyFirstGroup");
        taskDto.setJobDescription("This is my second job!");
        taskDto.setCronExpression("0/5 * * * * ?");
        taskDto.setJobStatus("0"); //任务的状态，0：启用；1：禁用；2：已删除
        taskDto.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));

        taskService.edit(taskDto);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取定时任务列表")
    public List<TaskDto> getJobList() {
        return taskService.list();
    }

    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "暂停任务")
    public void pauseJob() {
        taskService.pause("com.example.job.MinuteJob", "MyFirstGroup");
    }
}
