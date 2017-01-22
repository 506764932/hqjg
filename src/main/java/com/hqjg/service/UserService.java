package com.hqjg.service;

import com.hqjg.domain.vo.MapParam;
import com.hqjg.mapper.StudentMapper;
import com.hqjg.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygma on 16-9-2.
 */
@Service("userService")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<Map<String, Object>> findAll() {
        return userMapper.findAll();
    }

    public List<Map<String,Object>> findByName() {
       Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "mayaguang");
        return userMapper.findByName(map);
    }

    public void register(Map<String, Object> map) {
        userMapper.register(map);
    }

    /**
     * 根据参数查询用户
     * @param mapParam
     * @return
     */
    public Map<String,Object> findUserByMap(Map<String, Object> mapParam) {
        return userMapper.findUserByMap(mapParam);
    }
}
