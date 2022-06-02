package com.event.admin;

import com.event.admin.dao.UserModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    void updateUserFromUser(User user, @MappingTarget Optional<UserModel> entity);

}
