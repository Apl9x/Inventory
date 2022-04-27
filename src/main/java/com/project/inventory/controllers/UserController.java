package com.project.inventory.controllers;

import com.project.inventory.DTOs.LoginDto;
import com.project.inventory.entities.User;
import com.project.inventory.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Users", description = "The Users API for authentication and authorization")
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    @Operation(summary = "Log in with an existing user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public String login(@RequestBody @Valid LoginDto loginDto) {
        return userService.signin(loginDto.getUsername(), loginDto.getPassword()).orElseThrow(()->
                new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "CREATED")})
    public User signup(@RequestBody @Valid LoginDto loginDto){
        return userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Find all users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}
