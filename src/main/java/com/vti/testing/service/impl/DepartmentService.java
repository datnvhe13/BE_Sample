package com.vti.testing.service.impl;

import com.vti.testing.entity.Account;
import com.vti.testing.entity.Department;
import com.vti.testing.form.DepartmentFilterForm;
import com.vti.testing.form.department.CreatingDepartmentForm;
import com.vti.testing.form.department.UpdatingDepartmentForm;
import com.vti.testing.repository.IAccountRepository;
import com.vti.testing.repository.IDepartmentRepository;
import com.vti.testing.service.IDepartmentService;
import com.vti.testing.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm form) {
        Specification<Department> where = DepartmentSpecification.buildWhere(form);
        return departmentRepository.findAll(where, pageable);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public void createDepartment(CreatingDepartmentForm form) {
        Department department = modelMapper.map(form, Department.class);
        departmentRepository.save(department);
        List<Account> accounts = department.getAccounts();
        accounts.forEach(account -> account.setDepartment(department));
        accountRepository.saveAll(accounts);
    }

    @Override
    public void updateDepartmentForm(UpdatingDepartmentForm form) {
        Department department = modelMapper.map(form, Department.class);
        departmentRepository.save(department);
    }
}
