package de.zhao.community.service;

import de.zhao.community.dto.QuestionDTO;
import de.zhao.community.mapper.QuestionMapper;
import de.zhao.community.mapper.UserMapper;
import de.zhao.community.model.Question;
import de.zhao.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();

            //传统方法questionDTO.setId(question.getId());
            //此处使用spring的内置方法，用于快速拷贝两个对象间的数据。
            BeanUtils.copyProperties(question, questionDTO);

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
