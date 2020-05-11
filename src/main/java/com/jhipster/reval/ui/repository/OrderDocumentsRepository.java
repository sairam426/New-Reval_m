package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.OrderDocuments;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrderDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderDocumentsRepository extends JpaRepository<OrderDocuments, Long>, JpaSpecificationExecutor<OrderDocuments> {
}
