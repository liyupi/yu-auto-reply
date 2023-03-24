package com.yupi.autoreply.api.zsxq.model;

import lombok.Data;

import java.util.List;

/**
 * 获取列表响应
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class ListTopicsResponse {

    private RespData respData;

    private boolean succeeded;

    @Data
    public static class Group {
        private long groupId;
        private String name;
        private String type;
    }

    @Data
    public static class Owner {
        private String avatarUrl;
        private long userId;
        private String name;
        private String location;
    }

    @Data
    public static class OwnerDetail {
        private int questionsCount;
        private String joinTime;
    }

    @Data
    public static class Question {
        private Owner owner;
        private boolean expired;
        private Questionee questionee;
        private boolean anonymous;
        private OwnerDetail ownerDetail;
        private String ownerLocation;
        private String text;
    }

    @Data
    public static class Questionee {
        private String avatarUrl;
        private long userId;
        private String name;
        private String alias;
        private String description;
        private String location;
    }

    @Data
    public static class RespData {
        private List<TopicsItem> topics;
    }

    @Data
    public static class TopicsItem {
        private int readingCount;
        private Question question;
        private boolean answered;
        private String createTime;
        private UserSpecific userSpecific;
        private int rewardsCount;
        private String type;
        private boolean digested;
        private int likesCount;
        private int commentsCount;
        private boolean sticky;
        private String topicId;
        private int readersCount;
        private Group group;
    }

    @Data
    public static class UserSpecific {
        private boolean subscribed;
        private boolean liked;
    }
}
