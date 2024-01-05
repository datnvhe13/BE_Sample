package com.vti.testing.specification;

import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    private static final String USERNAME = "username";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String MIN_ID = "minId";
    private static final String MAX_ID = "maxId";

    public static Specification<Account> buildWhere(AccountFilterForm form){
        if(form == null){
            return null;
        }
        Specification<Account> whereUsername = new SpecificationIml(USERNAME, form.getSearch());
        Specification<Account> whereDepartmentName = new SpecificationIml(DEPARTMENT_NAME, form.getSearch());
        Specification<Account> whereMinId = new SpecificationIml(MIN_ID, form.getMinId());
        Specification<Account> whereMaxId = new SpecificationIml(MAX_ID, form.getMaxId());
        return Specification.where(whereUsername.or(whereDepartmentName)).and(whereMaxId.and(whereMinId));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SpecificationIml implements Specification<Account> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if(value == null){
                return null;
            }

            switch (key){
                case USERNAME:
                    // username LIKE '%value%'
                    return criteriaBuilder.like(root.get("username"), "%" + value + "%");
                case DEPARTMENT_NAME:
                    return criteriaBuilder.like(root.get("department").get("name"), "%" + value + "%");
                case MIN_ID:
                // id >= value
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
                case MAX_ID:
                    //id <= value
                    return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());

            }

            return null;
        }
    }

}





























