package com.yupi.autoreply.api.zsxq;

import com.yupi.autoreply.api.zsxq.model.AnswerRequest;
import com.yupi.autoreply.api.zsxq.model.AnswerResponse;
import com.yupi.autoreply.api.zsxq.model.ListTopicsRequest;
import com.yupi.autoreply.api.zsxq.model.ListTopicsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ZsxqApi 测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 **/
@SpringBootTest
class ZsxqApiTest {

    @Resource
    private ZsxqApi zsxqApi;

    private static final String COOKIE = "你的 COOKIE";

    @Test
    void listTopics() {
        ListTopicsRequest request = new ListTopicsRequest();
        request.setScope("unanswered_questions");
        request.setCount(30);
        request.setGroupId("知识星球id");
        ListTopicsResponse listTopicsResponse = zsxqApi.listTopics(request, COOKIE);
        Assertions.assertNotNull(listTopicsResponse);
    }

    @Test
    void answer() {
        AnswerRequest request = new AnswerRequest();
        request.setTopicId("问题id");
        AnswerRequest.ReqData reqData = new AnswerRequest.ReqData();
        reqData.setText("我的回答");
        reqData.setSilenced(true);
        request.setReq_data(reqData);
        AnswerResponse answerResponse = zsxqApi.answer(request, COOKIE);
        Assertions.assertNotNull(answerResponse);
    }
}