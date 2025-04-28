package com.example.userService.Controller;


import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.DTO.UserResponseFromCompanyDto;
import com.example.userService.Client.CompanyClient;
import com.example.userService.Mapper.UserMapper;
import com.example.userService.Service.UserService;
import com.example.userService.Entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        log.info("Got all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/company/{companyId}")
    public List<UserResponseFromCompanyDto> getUsersByCompany(@PathVariable Long companyId) {
        log.info("Got company for user by ID: {}", companyId);
        return userService.getUsersByCompany(companyId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId){
        log.info("Got user by ID: {}", userId);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        log.info("Created user by name: {} {} with phone number: {} for company by Id: {}",
                userRequestDto.getFirstName(),userRequestDto.getLastName(),userRequestDto.getPhoneNumber(), userRequestDto.getCompanyId());
        UserResponseDto createdUser = userService.create(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Changed user by ID: {} to name: {} {} with phone number: {} for company by Id: {}",
                userId,userRequestDto.getFirstName(),userRequestDto.getLastName(),userRequestDto.getPhoneNumber(), userRequestDto.getCompanyId());
        UserResponseDto updatedUser = userService.updateUser(userId, userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Deleted user by ID: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
