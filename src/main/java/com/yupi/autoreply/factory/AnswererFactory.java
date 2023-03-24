package com.yupi.autoreply.factory;

import com.yupi.autoreply.answerer.Answerer;
import com.yupi.autoreply.answerer.DefaultAnswerer;
import com.yupi.autoreply.answerer.OpenAiAnswerer;

/**
 * 回答者工厂
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class AnswererFactory {

    /**
     * 创建回答者
     *
     * @param answerer
     * @return
     */
    public static Answerer createAnswerer(String answerer) {
        switch (answerer) {
            case "openai":
                return new OpenAiAnswerer();
            default:
                return new DefaultAnswerer();
        }
    }
}
