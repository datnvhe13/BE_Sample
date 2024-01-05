package com.vti.testing.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentFilterForm {
    private String search;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    private int minYear;
}
