package com.fit3161.project.endpoint.CreateUser;

import com.fit3161.project.endpoint.CreateUser.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    //private final UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest request) {
        //return new ResponseEntity<String>(userService.getResponse(), userService.getStatus());
        return null;
    }
}