package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Lookups;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lookups entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LookupsRepository extends JpaRepository<Lookups, Long>, JpaSpecificationExecutor<Lookups> {
}
