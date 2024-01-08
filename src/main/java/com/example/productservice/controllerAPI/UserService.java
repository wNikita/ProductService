package com.example.productservice.controllerAPI;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final Repository repository;


    public UserService(Repository repository) {
        this.repository = repository;
    }


    public List<UserDetailsData> getAllUsers() throws Exception {
        List<UserDetailsData> user = repository.findAll();
        if(user.isEmpty())
        {
            throw  new Exception("User not found!!!!!!!!!!!!!");
        }
        return user;

    }
}
