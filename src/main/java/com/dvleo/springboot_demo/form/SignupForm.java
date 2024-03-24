package com.dvleo.springboot_demo.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class SignupForm {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message ="Invalid email format")
    private String email;

    private String password;
}
