package com.event.client.dao;

import com.event.representative.dao.RepresentativeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, UUID> {
    List<RepresentativeModel> findAllByClientId(String clientId);
}
