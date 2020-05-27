/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.travel.api.controllers;

import com.group.travel.api.dtos.AuthToken;
import com.group.travel.api.dtos.LoginUser;
import com.group.travel.api.dtos.Users;
import com.group.travel.api.repositories.UsersRepository;
import com.group.travel.api.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Users u = usersRepository.findByEmail(loginUser.getUsername()); 
        if(u.isStatus()==true){
            return ResponseEntity.ok(new AuthToken(token,u.getId(),u.getName(),u.getEmail(),u.getRole()));
        } else if(u.isStatus()==false){
            return new ResponseEntity<String>("Your account has been deactivated", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Invalid username or password", HttpStatus.BAD_REQUEST);
    }

}
