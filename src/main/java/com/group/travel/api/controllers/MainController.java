/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.travel.api.controllers;

import com.group.travel.api.dtos.Users;
import com.group.travel.api.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hoang
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/user-manage", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MainController {

    @Autowired
    UsersRepository usersRepository;

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView m = new ModelAndView("home");
        return m;
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    Users createUsers(@RequestBody Users newUsers) {
        newUsers.setStatus(true);
        return usersRepository.save(newUsers);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{id}")
    Optional<Users> getUsersById(@PathVariable Long id) {
        return usersRepository.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{id}")
    Users updateUsers(@RequestBody Users newUsers, @PathVariable Long id) {
        Optional<Users> u = usersRepository.findById(id);
        u.get().setStatus(newUsers.isStatus());
        return usersRepository.save(u.get());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    void deleteUsers(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
}
