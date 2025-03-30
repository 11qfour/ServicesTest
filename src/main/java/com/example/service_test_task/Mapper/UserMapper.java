package com.example.service_test_task.Mapper;

import com.example.service_test_task.DTO.CompanyRequestDto;
import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.Entity.Company;
import com.example.service_test_task.Entity.User;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);

    @Mapping(target = "company", ignore = true) // Company заполняется отдельно
    UserResponseDto toDto(User user);
    List<UserResponseDto> toDtoList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserRequestDto dto, @MappingTarget User user);
}
