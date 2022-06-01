package com.event.event.businessBranch.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessBranchRepository extends CrudRepository<BusinessBranchModel, Integer> {
}
