package com.noCountry.social_media_backend.infrastucture.adapter.in.controller;

import com.noCountry.social_media_backend.core.application.dto.UserDto;
import com.noCountry.social_media_backend.core.application.port.in.UserInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserInPort userInPort;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(this.userInPort.registerUser(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> userList() {
        return ResponseEntity.ok(this.userInPort.userList());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> userById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userInPort.userById(id));
    }

}
