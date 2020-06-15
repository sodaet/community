package cn.soda.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private String tag;
    private long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
