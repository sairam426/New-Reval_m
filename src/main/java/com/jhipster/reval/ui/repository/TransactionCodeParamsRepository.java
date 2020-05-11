package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.TransactionCodeParams;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransactionCodeParams entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionCodeParamsRepository extends JpaRepository<TransactionCodeParams, Long> {
}
