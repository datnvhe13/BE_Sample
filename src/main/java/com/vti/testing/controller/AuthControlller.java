package com.vti.testing.controller;

import com.vti.testing.dto.LoginInfoDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
public class AuthControlller {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/login")
    public LoginInfoDTO login(Principal principal){
        String username = principal.getName();
        Account account = accountService.getAccountByUsername(username);
        return modelMapper.map(account, LoginInfoDTO.class);
    }
}





















