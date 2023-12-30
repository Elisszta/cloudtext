package com.cloudtextbe.cloudtextbe_ver2.controllers;

import com.cloudtextbe.cloudtextbe_ver2.domain.User;
import com.cloudtextbe.cloudtextbe_ver2.services.UserService;
import com.cloudtextbe.cloudtextbe_ver2.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> loginController(@RequestBody User user) {
        User curUser = userService.loginService(user);
        if (curUser != null) {
            return Result.success(curUser, "login success!");
        } else {
            return Result.error("1", "User name or password wrong.");
        }
    }

    @PostMapping("/register")
    public Result<User> registController(@RequestBody User newUser){
        User user = userService.registerService(newUser);
        if(user!=null){
            return Result.success(user,"register success");
        }else{
            return Result.error("456","User name already exists.");
        }
    }
}
