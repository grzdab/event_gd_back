package com.event.representative.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepresentativeRepository extends CrudRepository<RepresentativeModel, UUID> {
}
