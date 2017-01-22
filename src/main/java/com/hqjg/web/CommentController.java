package com.hqjg.web;

import com.hqjg.domain.vo.MapParam;
import com.hqjg.service.CommentService;
import com.hqjg.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by ygma on 16-9-1.
 */

@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentController extends BaseController implements CommandLineRunner {

    Log log = LogFactory.getLog(CommentController.class);

    @Resource
    private CommentService commentService;

    /**
     * 得到所有的评论列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<Map<String, Object>> findAll(Map<String, Object> map) {
        List<Map<String, Object>> commentList = commentService.findAll();
        return commentList;
    }

    /**
     * 创建评论
     * http://localhost:10111
     * /comment/createComment?
     * map[%27name%27]=%E9%A9%AC%E4%BA%9A%E5%B9%BF
     * &map[%27email%27]=ygma@ygma.com
     * &map[%27mobilePhone%27]=13699194795
     * &map[%27message%27]=%E6%88%91%E6%98%AF%E5%A5%BD%E4%BA%BA
     * @return
     */
    @RequestMapping("/createComment")
    public Map<String, Object> createComment(MapParam mapParam) {
        Map<String, Object> data = new HashMap<String, Object>();
        log.info(mapParam);
        commentService.createComment(mapParam.getMap());
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("map", mapParam.getMap());
        return data;
    }

    /**
     * 删除评论
     * http://localhost:10111
     * /comment/deleteComment?map[%27id%27]=2
     * @return
     */
    @RequestMapping("/deleteComment")
    public Map<String, Object> deleteComment(MapParam mapParam) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("map", mapParam.getMap());

        boolean isCommentIdExist = mapParam.getMap().containsKey("id");
        if(!isCommentIdExist) {
            data.put("code", "500");
            data.put("msg", "id不存在");
            return data;
        }
        commentService.deleteComment(mapParam.getMap());
        return data;
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
