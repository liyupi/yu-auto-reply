package com.yupi.autoreply.api.zsxq.model;

import lombok.Data;

import java.util.List;

/**
 * 回答请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class AnswerRequest {

    private String topicId;

    private ReqData req_data;

    @Data
    public static class ReqData {

        private String text;

        private List<String> image_ids;

        private Boolean silenced;
    }
}
