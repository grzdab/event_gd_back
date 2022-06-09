package com.event.companyData.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyDataRepository extends CrudRepository<CompanyDataModel, UUID> {
}
