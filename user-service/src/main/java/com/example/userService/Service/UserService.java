package com.example.userService.Service;

import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.userService.Client.CompanyClient;
import com.example.userService.Entity.User;
import com.example.userService.Exception.ResourceNotFoundException;
import com.example.userService.Mapper.UserMapper;
import com.example.userService.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CompanyClient companyClient;

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserResponseDto userDto = userMapper.toDto(user);
        userDto.setCompany(companyClient.getCompanyById(userId));//make http request, although we could simply take the company from the user
        return userDto;
    }

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        userMapper.updateUserFromDto(userRequestDto,user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public UserResponseDto create(UserRequestDto userRequestDto){
        User user = userMapper.toEntity((userRequestDto));
        var newUser = userRepository.save(user);
        return userMapper.toDto(newUser);
    }
}
