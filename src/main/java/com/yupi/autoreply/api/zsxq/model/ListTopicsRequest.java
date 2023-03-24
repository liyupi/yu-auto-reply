package com.yupi.autoreply.api.zsxq.model;

import lombok.Data;

/**
 * 获取列表请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class ListTopicsRequest {

    /**
     * 星球 id
     */
    private String groupId;

    /**
     * 主题范围
     */
    private String scope;

    /**
     * 单页数量
     */
    private Integer count;

}
