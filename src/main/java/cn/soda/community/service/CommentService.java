package cn.soda.community.service;

import cn.soda.community.dto.CommentDTO;
import cn.soda.community.enums.CommentTypeEnum;
import cn.soda.community.mapper.CommentMapper;
import cn.soda.community.mapper.QuestionMapper;
import cn.soda.community.mapper.UserMapper;
import cn.soda.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    public void incCommentCount(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        Question updateQuestion = new Question();
        updateQuestion.setCommentCount(question.getCommentCount() + 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        questionMapper.updateByExampleSelective(updateQuestion, questionExample);
    }

    public List<CommentDTO> listByTargetId(long id, CommentTypeEnum commentTypeEnum) {
        CommentExample commentExample = new CommentExample();
        Integer type = commentTypeEnum.getType();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        //判断是否由评论
        if (comments.size() == 0) {
            return new ArrayList<>();
        }


//        System.out.println(comments.stream().map(Comment::getParentId).distinct().collect(Collectors.toList()));

        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
//        System.out.println(commentators);
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);
//        System.out.println(userIds);

        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));


        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
