package com.vti.testing.form.department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdatingDepartmentForm {
    private int id;
    private String name;
    private int total_Number;
    private String type;
    private Date createDate;
}
