package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.TransactionCodes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransactionCodes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionCodesRepository extends JpaRepository<TransactionCodes, Long> {
}
