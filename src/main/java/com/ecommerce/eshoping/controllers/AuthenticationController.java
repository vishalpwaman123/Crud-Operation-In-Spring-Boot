package com.ecommerce.eshoping.controllers;

import com.ecommerce.eshoping.dto.*;
import com.ecommerce.eshoping.models.User;
import com.ecommerce.eshoping.repositories.UserRepository;
import com.ecommerce.eshoping.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/Auth")
public class AuthenticationController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/SignUp")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpRequestDTO user) {
        //Optional<User> cUser = userRepository.findByUserName(user.getUserName());
//        if(cUser.isPresent()) {
//            return new ResponseEntity<>(new BasicResponse(false, "User Already exists"), HttpStatus.OK);
//        }
//        if(!user.getPassword().equals(user.getConfigPassword())) {
//            return new ResponseEntity<>(new BasicResponse(false, "Password and confirm password not matched"), HttpStatus.OK);
//        }
//        if(user.getRole().equalsIgnoreCase("admin") && !user.getMasterPassword().equals("India@123")) {
//            return new ResponseEntity<>(new BasicResponse(false, "Wrong Master Password"), HttpStatus.OK);
//        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setInsertionDate(new Date());
        newUser.setIsActive(true);
        userRepository.save(newUser);
        return new ResponseEntity<>(new BasicResponse(true, "User Registered"), HttpStatus.CREATED);
    }

    @PostMapping("/SignIn")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody User user) throws NoSuchAlgorithmException {
        Optional<User> cUser = userRepository
                .findAll()
                .stream()
                .filter(u-> u.getUserName().equalsIgnoreCase(user.getUserName()) && u.getPassword().equals(user.getPassword()))
                .findFirst();
        if(!cUser.isEmpty()) {
            final String token = jwtTokenUtil.generateToken(user);
            return new ResponseEntity<>(new SignInResponseDTO(true,"Sign In Successfully" ,token, cUser.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new SignInResponseDTO(false,"Sign In Failed", null, null), HttpStatus.OK);
        }
    }


}
