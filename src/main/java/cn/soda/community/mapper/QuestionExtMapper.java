package cn.soda.community.mapper;

import cn.soda.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    List<Question> selectRelated(Question question);
}
