package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.spring.boot.entity.User;

public interface UserMapper {
    
    @Select("SELECT * FROM t_user WHERE NAME = #{name}")
    User findByName(@Param("name") String name);
    
    @Insert("INSERT INTO t_user(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
    
    
    @Select("SELECT * FROM t_user ")
    List<User> findUserList();
}
