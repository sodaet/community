package cn.soda.community.controller;

import cn.soda.community.dto.CommentCreateDTO;
import cn.soda.community.dto.CommentDTO;
import cn.soda.community.dto.ResultDTO;
import cn.soda.community.enums.CommentTypeEnum;
import cn.soda.community.mapper.CommentMapper;
import cn.soda.community.model.Comment;
import cn.soda.community.model.User;
import cn.soda.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Comment comment = new Comment();
        comment.setCommentator(user.getId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType()); //设置type=1，一级回复；type=2，二级回复，...
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount((long) 0);
        commentMapper.insert(comment);
        if (comment.getType() == 1) commentService.incCommentCount(commentCreateDTO.getParentId());
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
