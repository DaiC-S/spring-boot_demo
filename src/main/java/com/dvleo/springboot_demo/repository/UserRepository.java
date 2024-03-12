package com.dvleo.springboot_demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    @Insert("INSERT INTO users (email, password) VALUES (#{email}, #{password})")
    void insert(String email, String password);
}
