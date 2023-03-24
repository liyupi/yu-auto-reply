package com.yupi.autoreply.monitor;

import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.yupi.autoreply.answerer.Answerer;
import com.yupi.autoreply.api.zsxq.ZsxqApi;
import com.yupi.autoreply.api.zsxq.model.AnswerRequest;
import com.yupi.autoreply.api.zsxq.model.AnswerResponse;
import com.yupi.autoreply.api.zsxq.model.ListTopicsRequest;
import com.yupi.autoreply.api.zsxq.model.ListTopicsResponse;
import com.yupi.autoreply.common.ErrorCode;
import com.yupi.autoreply.config.ZsxqConfig;
import com.yupi.autoreply.exception.BusinessException;
import com.yupi.autoreply.model.TaskListItem;
import com.yupi.autoreply.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识星球监控者
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Slf4j
public class ZsxqMonitor extends Monitor {

    private final ZsxqApi zsxqApi = SpringContextUtils.getBean(ZsxqApi.class);

    private final ZsxqConfig zsxqConfig = SpringContextUtils.getBean(ZsxqConfig.class);

    public ZsxqMonitor(TaskListItem taskListItem) {
        super(taskListItem);
    }

    @Override
    public void onMonitor(Answerer answerer) {
        String taskName = taskListItem.getName();
        log.info("任务 {} 监控开始", taskName);
        String cookie = zsxqConfig.getCookie();
        // 1. 获取未回答的问题列表
        ListTopicsRequest listTopicsRequest = new ListTopicsRequest();
        listTopicsRequest.setCount(20);
        listTopicsRequest.setGroupId(zsxqConfig.getGroupId());
        listTopicsRequest.setScope("unanswered_questions");
        ListTopicsResponse listTopicsResponse = zsxqApi.listTopics(listTopicsRequest, zsxqConfig.getCookie());
        List<ListTopicsResponse.TopicsItem> topics = listTopicsResponse.getRespData().getTopics();
        if (CollectionUtils.isEmpty(topics)) {
            log.info("暂无新提问");
            return;
        }
        for (ListTopicsResponse.TopicsItem topic : topics) {
            String question = topic.getQuestion().getText().trim();
            log.info("{} 收到新提问 \n 问题：{}", taskName, question);
            // 2. 获取回答
            String answer = answerer.doAnswer(question);
            // 3. 回复
            AnswerRequest answerRequest = new AnswerRequest();
            answerRequest.setTopicId(topic.getTopicId());
            AnswerRequest.ReqData reqData = new AnswerRequest.ReqData();
            reqData.setSilenced(zsxqConfig.getSilenced());
            reqData.setText(answer);
            reqData.setImage_ids(new ArrayList<>());
            answerRequest.setReq_data(reqData);
            AnswerResponse answerResponse = zsxqApi.answer(answerRequest, cookie);
            if (answerResponse.isSucceeded()) {
                log.info("{} 回答成功 \n 问题：{} \n 回答：{}", taskName, question, answer);
            } else {
                log.error("{} 回答失败 \n 问题：{}", taskName, question);
            }
            // 随机缓冲
            try {
                Thread.sleep(1000 + RandomUtil.randomInt(0, 2000));
            } catch (InterruptedException e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
            }
        }
        log.info("任务 {} 监控结束", taskName);
    }
}
