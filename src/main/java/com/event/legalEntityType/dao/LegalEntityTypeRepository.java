package com.event.legalEntityType.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalEntityTypeRepository extends CrudRepository<LegalEntityTypeModel, String> {
}
