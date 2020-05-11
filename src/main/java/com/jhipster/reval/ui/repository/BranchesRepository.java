package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Branches;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Branches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long> {
}
