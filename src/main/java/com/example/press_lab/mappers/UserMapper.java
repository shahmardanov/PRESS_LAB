package com.example.press_lab.mappers;

import com.example.press_lab.entity.User;
import com.example.press_lab.request.user.UserCreateRequest;
import com.example.press_lab.response.user.UserCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User createRequestToEntity (UserCreateRequest userCreateRequest);
//    User deleteRequestToEntity(UserDeleteRequest userDeleteRequest);

    UserCreateResponse entityToResponseCreate(User user);

//    UserReadResponse entityToResponseRead(User user);
//    UserUpdateResponse entityToResponseUpdate(User user);

//    User updateRequestToEntity(UserUpdateRequest userUpdateRequest);



}
