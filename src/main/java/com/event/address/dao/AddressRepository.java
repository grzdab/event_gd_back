package com.event.address.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressModel, Integer> {
    List<AddressModel> findAllByClientId(String clientId);
}
