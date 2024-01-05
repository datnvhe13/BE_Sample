package com.vti.testing.controller;

import com.vti.testing.dto.AccountDTO;
import com.vti.testing.dto.DepartmentDTO;
import com.vti.testing.entity.Department;
import com.vti.testing.form.DepartmentFilterForm;
import com.vti.testing.form.department.CreatingDepartmentForm;
import com.vti.testing.form.department.UpdatingDepartmentForm;
import com.vti.testing.service.IDepartmentService;
import com.vti.testing.validate.DepartmentIdExists;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin("*")
@Validated
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public Page<DepartmentDTO> getAllDepartments(Pageable pageable, DepartmentFilterForm form){
        Page<Department> departmentPage = departmentService.getAllDepartments(pageable, form);
        List<Department> departments = departmentPage.getContent();
        List<DepartmentDTO> departmentDTOS = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>(){}.getType());
        return new PageImpl<>(departmentDTOS, pageable, departmentPage.getTotalElements());
    }

    @GetMapping("{id}")
    public DepartmentDTO getDepartmentById(@PathVariable @DepartmentIdExists int id){
        Department department = departmentService.getDepartmentById(id);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @PostMapping
    public ResponseEntity createDepartment(@RequestBody @Valid CreatingDepartmentForm form){
            departmentService.createDepartment(form);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public void updateDepartment(@PathVariable @DepartmentIdExists int id, @RequestBody UpdatingDepartmentForm form){
        form.setId(id);
        departmentService.updateDepartmentForm(form);
    }



}
