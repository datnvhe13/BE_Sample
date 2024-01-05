package com.vti.testing.controller;

import com.vti.testing.dto.AccountDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilterForm;
import com.vti.testing.form.account.CreatingAccountForm;
import com.vti.testing.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AccountDTO> getAllAccounts(AccountFilterForm form){
        List<Account> accounts = accountService.getAllAccount(form);
        return modelMapper.map(accounts, new TypeToken<List<AccountDTO>>(){}.getType());
    }

    @GetMapping("{id}")
    public AccountDTO getAllAccountById(@PathVariable int id){
        Account account = accountService.getAccountById(id);
        return modelMapper.map(account, AccountDTO.class);
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreatingAccountForm form){
        accountService.createAccountForm(form);
        return new ResponseEntity<>("Create successfully !", HttpStatus.CREATED);
    }





}

















