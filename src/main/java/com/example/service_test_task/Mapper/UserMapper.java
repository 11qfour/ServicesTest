package com.example.service_test_task.Mapper;

import com.example.service_test_task.DTO.UserRequestDto;
import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);

    @Mapping(target = "company", ignore = true) // Company заполняется отдельно
    UserResponseDto toDto(User user);
    List<UserResponseDto> toDtoList(List<User> users);
}
