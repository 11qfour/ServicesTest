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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CompanyClient companyClient;

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserResponseDto userDto = userMapper.toDto(user);
        try {
            userDto.setCompany(companyClient.getCompanyById(userId));//make http request, although we could simply take the company from the user
        } catch (Exception e) {
            log.error("Failed to fetch company for user {}: {}",userId, e.getMessage());
            new ResourceNotFoundException("Company not found or service unavailable");
        }
        return userDto;
    }

    public List<UserResponseFromCompanyDto> getUsersByCompany(Long companyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = userRepository.findByCompanyId(companyId, pageable).getContent();
        List<UserResponseFromCompanyDto> result=userMapper.toDtoFromCompanyList(users);
        log.info("Returning {} users by companyId: {}", result.size(), companyId);
        return result;
    }

    public List<UserResponseDto> getAllUsers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = userRepository.findAll(pageable).getContent();
        List<UserResponseDto> result = userMapper.toDtoList(users);

        for (UserResponseDto dto : result) {
            try {
                if (dto.getCompany() == null && dto.getId() != null) {
                    dto.setCompany(companyClient.getCompanyById(dto.getId()));
                }
            } catch (Exception e) {
                log.error("Failed to fetch company for user {}", dto.getId(), e);
            }
        }
        log.info("Returning {} users", result.size());
        return result;
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto){ //if any field's is empty it willnot be changed
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        userMapper.updateUserFromDto(userRequestDto,user);
        User updatedUser = userRepository.save(user);
        UserResponseDto response = userMapper.toDto(updatedUser);
        log.info("Updated user id={}, new data: {}", userId, response);
        return response;
    }

    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }
        log.info("Deleted user id={}", userId);
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
        UserResponseDto response = userMapper.toDto(newUser);
        log.info("Created new user with id={}, name={} by companyId: {}", response.getId(), response.getFirstName(), userRequestDto.getCompanyId());
        return response;
    }
}
