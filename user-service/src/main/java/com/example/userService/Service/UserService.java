package com.example.userService.Service;

import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.DTO.UserResponseFromCompanyDto;
import com.example.userService.Client.CompanyClient;
import com.example.userService.Entity.User;
import com.example.userService.Exception.ResourceNotFoundException;
import com.example.userService.Mapper.UserMapper;
import com.example.userService.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


public interface UserService {
    UserResponseDto getUserById(Long userId);
    List<UserResponseFromCompanyDto> getUsersByCompany(Long companyId, int page, int size);

    List<UserResponseDto> getAllUsers(int page, int size);
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    void deleteUser(Long userId);
    UserResponseDto create(UserRequestDto userRequestDto);

}
