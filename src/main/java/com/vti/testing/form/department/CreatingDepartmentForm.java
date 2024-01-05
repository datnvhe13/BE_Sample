package com.vti.testing.form.department;

import com.vti.testing.entity.Type;
import com.vti.testing.validate.AccountUsernameNotExists;
import com.vti.testing.validate.DepartmentNameNotExists;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreatingDepartmentForm {
    @Length(max = 50, message = "{Department.createDepartment.form.name.Length}")
    @NotBlank(message = "{Department.createDepartment.form.name.NotBlank}")
    @DepartmentNameNotExists
    private String name;
    @PositiveOrZero(message = "Total member must greater than or equal 0")
    private int total_Number;
    @Pattern(regexp = "Dev|Test|ScrumMaster|PM", message = "Type must Dev, Test, ScrumMaster, PM")
    private String type;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "Created date must not greater than today")
    private Date createDate;
    private List<@Valid Account> accounts;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Account{
        @Length(max = 50, message = "Username's length must less than or equal to 50")
        @NotBlank(message = "Username must not null")
        @AccountUsernameNotExists
        private String username;

    }

}
