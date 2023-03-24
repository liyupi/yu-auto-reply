package com.yupi.autoreply.factory;

import com.yupi.autoreply.model.TaskListItem;
import com.yupi.autoreply.monitor.DefaultMonitor;
import com.yupi.autoreply.monitor.Monitor;
import com.yupi.autoreply.monitor.ZsxqMonitor;

/**
 * 监视者工厂
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class MonitorFactory {

    /**
     * 创建监视者
     *
     * @param monitor
     * @param taskListItem
     * @return
     */
    public static Monitor createMonitor(String monitor, TaskListItem taskListItem) {
        switch (monitor) {
            case "zsxq":
                return new ZsxqMonitor(taskListItem);
            default:
                return new DefaultMonitor(taskListItem);
        }
    }
}
