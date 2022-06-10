package com.event.eventStatus.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStatusRepository extends CrudRepository<EventStatusModel, String> {
}
