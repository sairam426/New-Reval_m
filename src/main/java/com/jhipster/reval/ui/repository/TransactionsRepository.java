package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Transactions;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>, JpaSpecificationExecutor<Transactions> {
}
