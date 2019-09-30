package de.zhao.community.controller;

import de.zhao.community.dto.CommentCreateDTO;
import de.zhao.community.dto.CommentDTO;
import de.zhao.community.dto.ResultDTO;
import de.zhao.community.enums.CommentTypeEnum;
import de.zhao.community.exception.CustomizeErrorCode;
import de.zhao.community.model.Comment;
import de.zhao.community.model.User;
import de.zhao.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.erroOf(CustomizeErrorCode.UN_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getText())) {
            return ResultDTO.erroOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setText(commentCreateDTO.getText());
        comment.setParentType(commentCreateDTO.getParentType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.insert(comment);
        return ResultDTO.successOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.successOf(commentDTOS);
    }
}
