package com.event.appRole;

import com.event.appRole.roleDao.AppRoleModel;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    void updateRoleFromAppRole(AppRole appRole, @MappingTarget Optional<AppRoleModel> entity);
}
