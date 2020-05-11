package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.OrderComments;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrderComments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderCommentsRepository extends JpaRepository<OrderComments, Long>, JpaSpecificationExecutor<OrderComments> {
}
