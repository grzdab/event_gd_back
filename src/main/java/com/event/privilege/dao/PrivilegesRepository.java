package com.event.privilege.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository extends CrudRepository<PrivilegesModel, String> {
}
