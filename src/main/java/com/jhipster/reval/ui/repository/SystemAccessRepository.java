package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.SystemAccess;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SystemAccess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemAccessRepository extends JpaRepository<SystemAccess, Long>, JpaSpecificationExecutor<SystemAccess> {
}
