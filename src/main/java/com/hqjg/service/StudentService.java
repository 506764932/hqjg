package com.hqjg.service;

import com.hqjg.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygma on 16-9-2.
 */
@Service("studentService")
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    /*public List<Student> likeName(Map<String, Object> map){
        return studentMapper.likeName(map);
    }*/


    public List<Map<String, Object>> findAll() {
        return studentMapper.findAll();
    }

    public List<Map<String,Object>> findByName() {
       Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "mayaguang");
        return studentMapper.findByName(map);
    }
}
