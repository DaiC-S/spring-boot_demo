package com.dvleo.springboot_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    private long id;
    private String email;
    private String password;
}
