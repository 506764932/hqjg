package com.hqjg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by ygma on 16-9-1.
 */

@Mapper
public interface StudentMapper {

    /*Student getById(Map<String, Object> map);

    List<Student> likeName(Map<String, Object> map);

    String getNameById(Map<String, Object> map);*/

    @Select("SELECT name FROM student")
    List<Map<String,Object>> findAll();

    @Select("SELECT * FROM student WHERE name = #{name}")
    List<Map<String, Object>> findByName(Map map);

    /*@Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);*/

}
