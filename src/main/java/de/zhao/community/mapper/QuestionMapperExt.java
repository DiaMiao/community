package de.zhao.community.mapper;

import de.zhao.community.model.Question;
import de.zhao.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionMapperExt {

    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);
}