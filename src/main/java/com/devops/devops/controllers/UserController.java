package com.devops.devops.controllers;


import com.devops.devops.models.BaseResponse;
import com.devops.devops.models.UserListModel;
import com.devops.devops.models.UserModel;
import com.devops.devops.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public BaseResponse create(@RequestBody UserModel userModel) {
        return userService.create(userModel);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getAll")
    public UserListModel getAll() {
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/login")
    public BaseResponse login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.login(email,password);
    }
}
