package com.yupi.autoreply.model;

import lombok.Data;

/**
 * 单个任务配置
 */
@Data
public class TaskListItem {

    /**
     * 任务名
     */
    private String name = "";

    /**
     * 任务执行周期
     */
    private String cron = "0/30 * * * * ?";

    /**
     * 回答者
     */
    private String answerer = "openai";

    /**
     * 监控者
     */
    private String monitor = "zsxq";
}