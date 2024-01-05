package com.vti.testing.specification;

import com.vti.testing.entity.Department;
import com.vti.testing.form.DepartmentFilterForm;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class DepartmentSpecification {

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String USERNAME = "username";
    private static final String CREATED_DATE = "createDate";
    private static final String MIN_YEAR = "minYear";

    public static Specification<Department> buildWhere(DepartmentFilterForm form){
        if(form == null){
            return null;
        }
        Specification<Department> whereDepartmentName = new SpecificationIml(DEPARTMENT_NAME, form.getSearch());
        Specification<Department> whereUserName = new SpecificationIml(USERNAME, form.getSearch());
        Specification<Department> whereCreatedDate = new SpecificationIml(CREATED_DATE, form.getCreateDate());
        Specification<Department> whereMinYear = new SpecificationIml(MIN_YEAR, form.getMinYear());
        return Specification.where(whereDepartmentName.or(whereUserName)).and(whereCreatedDate).and(whereMinYear);
    }

    @AllArgsConstructor
    public static class SpecificationIml implements Specification<Department>{

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if(value == null){
                return null;
            }
            switch (key){
                case DEPARTMENT_NAME:
                    //name like "%value%"
                    return criteriaBuilder.like(root.get("name"), "%" + value + "%");
                case USERNAME:
                    //
                    Join join = root.join("accounts", JoinType.LEFT);
                    return criteriaBuilder.like(join.get("username"), "%" + value + "%");
                case CREATED_DATE:
                    return criteriaBuilder.equal(root.get("createDate").as(java.sql.Date.class), (Date) value);
                case MIN_YEAR:
                    //Year(created_date) >= value
                    return criteriaBuilder.greaterThanOrEqualTo(
                            criteriaBuilder.function("YEAR", Integer.class, root.get("createDate")),
                            (Integer) value
                    );
            }
            return null;
        }
    }

}
