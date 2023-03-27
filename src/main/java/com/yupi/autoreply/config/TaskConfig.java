package com.yupi.autoreply.config;

import com.yupi.autoreply.job.JobMediator;
import com.yupi.autoreply.model.TaskListItem;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * 任务配置
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Configuration
@ConfigurationProperties(prefix = "task")
@Data
@Slf4j
public class TaskConfig implements SchedulingConfigurer {

    /**
     * 并发配置
     */
    private ConcurrentConfig concurrent = new ConcurrentConfig();

    /**
     * 任务列表
     */
    private List<TaskListItem> list;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (BooleanUtils.isTrue(concurrent.getEnable())) {
            // 如果开启并发，默认并发度为任务数，即全并发，可通过配置更改
            int size = Optional.ofNullable(concurrent.getSize()).orElse(list.size());
            taskRegistrar.setScheduler(Executors.newScheduledThreadPool(size));
        }
        log.info("--- 任务注册开始 ---");
        for (int i = 0; i < list.size(); i++) {
            TaskListItem taskListItem = list.get(i);
            if (StringUtils.isBlank(taskListItem.getName())) {
                taskListItem.setName("task" + (i + 1));
            }
            taskRegistrar.addCronTask(new JobMediator(taskListItem), taskListItem.getCron());
            log.info("任务注册成功 {}", taskListItem);
        }
        log.info("--- 任务注册结果 ---");
    }

    @Data
    public static class ConcurrentConfig {
        private Boolean enable = false;

        private Integer size;
    }
}
