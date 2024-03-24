package com.dvleo.springboot_demo.repository;

import com.dvleo.springboot_demo.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Insert("INSERT INTO users (email, password) VALUES (#{email}, #{password})")
    void insert(String email, String password);

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserEntity findById(long id);

    @Select("SELECT * FROM users WHERE email = #{email}")
    UserEntity findByEmail(String email);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(long id);
}
