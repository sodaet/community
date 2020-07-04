package cn.soda.community.controller;

import cn.soda.community.dto.CommentDeleteDTO;
import cn.soda.community.dto.ResultDTO;
import cn.soda.community.mapper.CommentMapper;
import cn.soda.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeleteController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody CommentDeleteDTO commentDeleteDTO) {
        commentMapper.deleteByPrimaryKey(commentDeleteDTO.getId());
//        System.out.println(ResultDTO.okOf());
        commentService.redCommentCount(commentDeleteDTO.getParentId());
        return ResultDTO.okOf();
    }
}
