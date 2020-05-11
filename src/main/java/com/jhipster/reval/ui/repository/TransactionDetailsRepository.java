package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.TransactionDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long>, JpaSpecificationExecutor<TransactionDetails> {
}
