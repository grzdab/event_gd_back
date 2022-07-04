package com.event.language.dao;

import com.event.language.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageModel, Integer> {
    Page<LanguageModel> findAll(Pageable pageable);
//    Iterable<LanguageModel> findAll(Sort sort);
}
