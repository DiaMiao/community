package de.zhao.community.mapper;

import de.zhao.community.model.Comment;
import de.zhao.community.model.CommentExample;
import de.zhao.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
    int incLikeCount(Comment record);
}