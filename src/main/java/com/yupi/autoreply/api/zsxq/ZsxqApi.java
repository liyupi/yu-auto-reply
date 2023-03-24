package com.yupi.autoreply.api.zsxq;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.yupi.autoreply.api.zsxq.model.AnswerRequest;
import com.yupi.autoreply.api.zsxq.model.AnswerResponse;
import com.yupi.autoreply.api.zsxq.model.ListTopicsRequest;
import com.yupi.autoreply.api.zsxq.model.ListTopicsResponse;
import com.yupi.autoreply.common.ErrorCode;
import com.yupi.autoreply.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 知识星球接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Service
public class ZsxqApi {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/111.0";

    /**
     * 获取主题列表
     *
     * @param request
     * @param cookie
     * @return
     */
    public ListTopicsResponse listTopics(ListTopicsRequest request, String cookie) {
        String groupId = request.getGroupId();
        if (StringUtils.isBlank(groupId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 groupId");
        }
        if (StringUtils.isBlank(cookie)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 cookie");
        }
        String url = String.format("https://api.zsxq.com/v2/groups/%s/topics", groupId);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(request);
        String query = URLUtil.buildQuery(stringObjectMap, StandardCharsets.UTF_8);
        String result = HttpRequest.get(url)
                .header("cookie", cookie)
                .header("user-agent", USER_AGENT)
                .body(query)
                .execute()
                .body();
        return JSONUtil.toBean(result, ListTopicsResponse.class);
    }

    /**
     * 回答问题
     *
     * @param request
     * @param cookie
     * @return
     */
    public AnswerResponse answer(AnswerRequest request, String cookie) {
        String topicId = request.getTopicId();
        if (StringUtils.isBlank(topicId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 topicId");
        }
        if (StringUtils.isBlank(cookie)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 cookie");
        }
        String url = String.format("https://api.zsxq.com/v2/topics/%s/answer", topicId);
        String json = JSONUtil.toJsonStr(request);
        String result = HttpRequest.post(url)
                .header("cookie", cookie)
                .header("user-agent", USER_AGENT)
                .body(json)
                .execute()
                .body();
        return JSONUtil.toBean(result, AnswerResponse.class);
    }
}

