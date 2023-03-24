package com.yupi.autoreply.answerer;

/**
 * 默认回答者（降级）
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class DefaultAnswerer implements Answerer {

    @Override
    public String doAnswer(String prompt) {
        return "抱歉，我不理解您的问题：" + prompt;
    }
}
