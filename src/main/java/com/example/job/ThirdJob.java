package com.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eric on 2018/3/8.
 */
public class ThirdJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ThirdJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("ThirdJob->JobName: " + context.getJobDetail().getKey().getName() + " ->JobGroup：" + context.getJobDetail().getKey().getGroup() + " ->Execute Time：" +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
