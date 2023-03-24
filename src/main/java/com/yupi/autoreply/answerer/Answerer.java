package com.yupi.autoreply.answerer;

/**
 * 回答者
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface Answerer {

    /**
     * 回答
     *
     * @param prompt 提示语
     * @return 回答结果
     */
    String doAnswer(String prompt);
}
