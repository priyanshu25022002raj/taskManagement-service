package com.easyLife.taskManagement.taskManagement.controllers;

import com.easyLife.taskManagement.taskManagement.dto.USerLoginResponse;
import com.easyLife.taskManagement.taskManagement.dto.UserDtoLogin;
import com.easyLife.taskManagement.taskManagement.dto.UserDtoResponse;
import com.easyLife.taskManagement.taskManagement.dto.UserLogin;
import com.easyLife.taskManagement.taskManagement.services.AuthenticationService;
import com.easyLife.taskManagement.taskManagement.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/taskApp")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/signUp")
    public ResponseEntity<UserDtoResponse> signUpUser(@RequestBody @Valid UserDtoLogin userDtoLogin){
        UserDtoLogin userDtoLogin1 = userService.signUpUser(userDtoLogin);
        UserDtoResponse userDtoResponse = modelMapper.map(userDtoLogin1, UserDtoResponse.class);
        return ResponseEntity.ok(userDtoResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<USerLoginResponse> loginUser(@RequestBody @Valid UserLogin userLogin, HttpServletRequest request, HttpServletResponse response){
        String accessToken = authenticationService.login(userLogin);
        Cookie cookie = new Cookie("token",accessToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(new USerLoginResponse(userLogin.getEmail(),accessToken), HttpStatus.OK);
    }

}
