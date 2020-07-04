package cn.soda.community.dto;

import lombok.Data;

@Data
public class CommentDeleteDTO {
    private Long id;
    private Long parentId;
}
