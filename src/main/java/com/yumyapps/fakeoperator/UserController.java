package com.yumyapps.fakeoperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable(name = "id") Long id) {
        return userRepository.findById(id).get();
    }

}
