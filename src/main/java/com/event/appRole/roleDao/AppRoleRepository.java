package com.event.appRole.roleDao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRoleModel, UUID>{
}
