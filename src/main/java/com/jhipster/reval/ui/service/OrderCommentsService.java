package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.OrderComments;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OrderComments}.
 */
public interface OrderCommentsService {

    /**
     * Save a orderComments.
     *
     * @param orderComments the entity to save.
     * @return the persisted entity.
     */
    OrderComments save(OrderComments orderComments);

    /**
     * Get all the orderComments.
     *
     * @return the list of entities.
     */
    List<OrderComments> findAll();

    /**
     * Get the "id" orderComments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderComments> findOne(Long id);

    /**
     * Delete the "id" orderComments.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
