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

    public List<UserResponseFromCompanyDto> getUsersByCompany(Long companyId) {
        return userRepository.findByCompanyId(companyId).stream()
                .map(userMapper::toDtoFromCompany)  // маппер из User -> UserResponseFromCompanyDto
                .toList();
    }

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponseDto dto = userMapper.toDto(user);
                    if (user.getCompanyId() != null) {
                        dto.setCompany(companyClient.getCompanyById(user.getCompanyId()));
                    }
                    return dto;
                })
                .toList();
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto){ //if any field's is empty it willnot be changed
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
        if (userRequestDto.getCompanyId() != null) {
            try {
                companyClient.getCompanyById(userRequestDto.getCompanyId());
            } catch (Exception ex) {
                throw new ResourceNotFoundException("Company does not exist");
            }
        }
        else{
            throw new ResourceNotFoundException("Company's Id isEmpty");
        }

        if((userRequestDto.getLastName()==null)||(userRequestDto.getFirstName()==null)||(userRequestDto.getPhoneNumber())==null){
            throw new ResourceNotFoundException("Any field's will be empty");
        }

        User user = userMapper.toEntity((userRequestDto));
        var newUser = userRepository.save(user);
        return userMapper.toDto(newUser);
    }
}
