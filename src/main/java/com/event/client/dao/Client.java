package com.event.client.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Client extends CrudRepository<ClientModel, UUID> {
}
