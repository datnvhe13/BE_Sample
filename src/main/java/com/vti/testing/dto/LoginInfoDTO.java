package com.vti.testing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginInfoDTO {
    private String username;
    private String fullName;
    private String departmentName;
}
