package cn.soda.community.controller;

import cn.soda.community.dto.CommentDTO;
import cn.soda.community.dto.QuestionDTO;
import cn.soda.community.enums.CommentTypeEnum;
import cn.soda.community.service.CommentService;
import cn.soda.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") long id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id); //以id获取到问题
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION); //获取到评论
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO); //获取相关问题
        questionService.incView(id); //累加阅读数
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
