package com.vti.testing.service;

import com.vti.testing.entity.Department;
import com.vti.testing.form.DepartmentFilterForm;
import com.vti.testing.form.department.CreatingDepartmentForm;
import com.vti.testing.form.department.UpdatingDepartmentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {
    Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm form);

    Department getDepartmentById(int id);

    void createDepartment(CreatingDepartmentForm form);

    void updateDepartmentForm(UpdatingDepartmentForm form);
}
