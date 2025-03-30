package com.example.service_test_task.Service;

import com.example.service_test_task.Client.CompanyClient;
import com.example.service_test_task.DTO.CompanyResponseDto;
import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.Entity.User;
import com.example.service_test_task.Exception.ResourceNotFoundException;
import com.example.service_test_task.Mapper.UserMapper;
import com.example.service_test_task.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository USER_REPOSITORY;
    private final UserMapper USER_MAPPER;

    private final CompanyClient COMPANY_CLIENT;

    public UserResponseDto getUserById(Long userId) {
        User user = USER_REPOSITORY.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserResponseDto userDto = USER_MAPPER.toDto(user);
        userDto.setCompany(COMPANY_CLIENT.getCompanyById(userId));
        return userDto;
    }

    public List<UserResponseDto> getAllUsers(){
        return USER_REPOSITORY.findAll().stream().map(USER_MAPPER::toDto).toList();
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto){
        User user = USER_REPOSITORY.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        USER_MAPPER.updateUserFromDto(userRequestDto,user);
        User updatedUser = USER_REPOSITORY.save(user);
        return USER_MAPPER.toDto(updatedUser);
    }

    public void deleteUser(Long userId){
        if (!USER_REPOSITORY.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }
        USER_REPOSITORY.deleteById(userId);
    }

    public UserResponseDto create(UserRequestDto userRequestDto){
        User user = USER_MAPPER.toEntity((userRequestDto));
        var newUser = USER_REPOSITORY.save(user);
        return USER_MAPPER.toDto(newUser);
    }
}
