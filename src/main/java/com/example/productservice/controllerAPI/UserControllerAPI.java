package com.example.productservice.controllerAPI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.productservice.controllerAPI.AppConstant.ALL_USERS;
import static com.example.productservice.controllerAPI.AppConstant.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class UserControllerAPI {
    private final UserService userService;

    @Autowired
    public UserControllerAPI(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(ALL_USERS)
    public List<UserDetailsData> getAllUsers() throws Exception {
        List<UserDetailsData> userList = userService.getAllUsers();
        return userList;
//        JSONObject jsonObject;
//        jsonObject= (JSONObject) userService.getAllUsers();
//        return jsonObject;
    }

}
