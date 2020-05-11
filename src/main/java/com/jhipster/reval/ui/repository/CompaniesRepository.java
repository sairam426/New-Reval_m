package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Companies;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Companies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {
}
