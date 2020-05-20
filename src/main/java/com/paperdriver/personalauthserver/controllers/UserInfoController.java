package com.paperdriver.personalauthserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @RequestMapping("/user")
    public Principal users(Principal user) {
        return user;
    }
}
