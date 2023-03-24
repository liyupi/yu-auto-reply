package com.yupi.autoreply.monitor;

import com.yupi.autoreply.answerer.Answerer;
import com.yupi.autoreply.model.TaskListItem;

/**
 * 抽象监控者
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public abstract class Monitor {

    TaskListItem taskListItem;

    Monitor(TaskListItem taskListItem) {
        this.taskListItem = taskListItem;
    }

    /**
     * 触发监控
     *
     * @param answerer
     */
    public abstract void onMonitor(Answerer answerer);
}
