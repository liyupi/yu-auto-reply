package com.yupi.autoreply.api.openai;

import com.yupi.autoreply.api.openai.model.CreateCompletionRequest;
import com.yupi.autoreply.api.openai.model.CreateCompletionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OpenAiApi 测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 **/
@SpringBootTest
class OpenAiApiTest {

    @Resource
    private OpenAiApi openAiApi;

    private static final String OPENAI_API_KEY = "你的 OPENAI_API_KEY";

    @Test
    void createCompletion() {
        CreateCompletionRequest request = new CreateCompletionRequest();
        request.setModel("text-davinci-003");
        request.setPrompt("我的提问");
        CreateCompletionResponse response = openAiApi.createCompletion(request, OPENAI_API_KEY);
        Assertions.assertNotNull(response);
    }
}