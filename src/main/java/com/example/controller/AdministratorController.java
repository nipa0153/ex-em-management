package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

/**
 * Administrator
 * 管理者情報を表すドメイン.
 * 
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    
    /**
     * 管理者情報を操作するサービス.
     * 
     */
    @Autowired
    private AdministratorService administratorService;


    /**
     * 管理者情報を挿入する.
     * @param form
     * @return
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert.html";
    }


    /**
     * 管理者情報を挿入する.
     * @param form
     * @return
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);

        return "redirect: /";
    
    /**
     * ログイン画面に遷移する.
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login.html";
    }
    

        
    }

}
