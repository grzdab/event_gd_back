package com.event.event.clientType.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends CrudRepository<ClientTypeModel, Integer> {
}
