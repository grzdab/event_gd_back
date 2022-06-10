package com.event.representative.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepresentativeRepository extends CrudRepository<RepresentativeModel, Integer> {
    List<RepresentativeModel> findAllByClientId(String clientId);
}
