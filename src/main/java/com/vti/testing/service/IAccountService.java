package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilterForm;
import com.vti.testing.form.account.CreatingAccountForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {

    List<Account> getAllAccount(AccountFilterForm form);

    Account getAccountById(int id);

    Account getAccountByUsername(String username);

    void createAccountForm(CreatingAccountForm form);
}
