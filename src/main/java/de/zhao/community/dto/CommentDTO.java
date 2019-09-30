package de.zhao.community.dto;

import de.zhao.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer parentType;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String text;
    private User user;
    private Integer commentCount;
}
