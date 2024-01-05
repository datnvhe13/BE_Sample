package com.vti.testing.service.impl;

import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilterForm;
import com.vti.testing.form.account.CreatingAccountForm;
import com.vti.testing.repository.IAccountRepository;
import com.vti.testing.service.IAccountService;
import com.vti.testing.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Account> getAllAccount(AccountFilterForm form) {
        Specification<Account> where = AccountSpecification.buildWhere(form);
        return accountRepository.findAll(where);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void createAccountForm(CreatingAccountForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);
        if(typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Account account = modelMapper.map(form, Account.class);
        accountRepository.save(account);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getAccountByUsername(username);
        if(account == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(username, account.getPassword(), Collections.emptyList());
    }
}























