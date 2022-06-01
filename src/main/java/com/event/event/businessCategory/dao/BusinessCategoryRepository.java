package com.event.event.businessCategory.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCategoryRepository extends CrudRepository<BusinessCategoryModel, Integer> {
}
