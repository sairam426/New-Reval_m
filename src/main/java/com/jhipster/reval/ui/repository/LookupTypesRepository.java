package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.LookupTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LookupTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LookupTypesRepository extends JpaRepository<LookupTypes, Long>, JpaSpecificationExecutor<LookupTypes> {
}
