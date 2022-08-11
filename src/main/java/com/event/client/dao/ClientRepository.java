package com.event.client.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, UUID> {
    List<ClientModel> findAllByTaxInfo(int taxInfoId);
    List<ClientModel> findAllByClientType(int clientTypeId);
}
