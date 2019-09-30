package de.zhao.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentId;
    private String text;
    private Integer parentType;
}
