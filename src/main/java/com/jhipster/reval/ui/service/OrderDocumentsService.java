package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.OrderDocuments;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OrderDocuments}.
 */
public interface OrderDocumentsService {

    /**
     * Save a orderDocuments.
     *
     * @param orderDocuments the entity to save.
     * @return the persisted entity.
     */
    OrderDocuments save(OrderDocuments orderDocuments);

    /**
     * Get all the orderDocuments.
     *
     * @return the list of entities.
     */
    List<OrderDocuments> findAll();

    /**
     * Get the "id" orderDocuments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderDocuments> findOne(Long id);

    /**
     * Delete the "id" orderDocuments.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
