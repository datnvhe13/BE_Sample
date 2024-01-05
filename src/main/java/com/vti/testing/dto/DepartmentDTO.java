package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.testing.entity.Account;
import com.vti.testing.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO implements Serializable {
    private int id;
    private String name;
    private int total_Number;
    private Type type;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    private List<AccountDTO> accounts;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AccountDTO{
        private String username;
    }

}
