package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Organizations;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Organizations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Long> {
}
