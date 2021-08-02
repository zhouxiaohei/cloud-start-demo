package com.cloud.demo.bootswagger.config;

import lombok.Data;

/**
 * @ClassName ScheduledConfig
 * @Author JackZhou
 * @Date 2021/6/25  15:00
 **/
@Data
public class ScheduledConfig {
    private static final long serialVersionUID = -2879235995515459274L;

    private String taskName; //定时任务名称
    private String taskType; //定时任务类型
    private String taskTimes; //定时任务运行时间  17:00 / 9:00
    private String storeId;
    private int priority; // 优先级   约大越好
    private int retryTimes;  //重试次数
    private String severParams; // 服务环境参数配置
    private String taskCollectParams; // 任务采集参数配置

}
