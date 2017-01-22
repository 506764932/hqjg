package com.hqjg.service;

import com.hqjg.mapper.CommentMapper;
import com.hqjg.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygma on 16-9-2.
 */
@Service("commentService")
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 查询所有评论列表
     * @return
     */
    public List<Map<String, Object>> findAll() {
        return commentMapper.findAll();
    }

    /**
     * 创建评论
     * @param map
     */
    public void createComment(Map<String, Object> map) {
        commentMapper.createComment(map);
    }

    /**
     * 删除评论
     * @param map
     */
    public void deleteComment(Map<String, Object> map) {
        commentMapper.deleteComment(map);
    }
}
