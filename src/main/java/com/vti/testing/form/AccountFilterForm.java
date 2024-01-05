package com.vti.testing.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Integer minId;
    private Integer maxId;
}
