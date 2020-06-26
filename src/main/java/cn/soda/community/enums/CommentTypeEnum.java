package cn.soda.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private final Integer type;


    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

}
