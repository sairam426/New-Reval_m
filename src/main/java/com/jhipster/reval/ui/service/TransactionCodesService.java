package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.TransactionCodes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TransactionCodes}.
 */
public interface TransactionCodesService {

    /**
     * Save a transactionCodes.
     *
     * @param transactionCodes the entity to save.
     * @return the persisted entity.
     */
    TransactionCodes save(TransactionCodes transactionCodes);

    /**
     * Get all the transactionCodes.
     *
     * @return the list of entities.
     */
    List<TransactionCodes> findAll();

    /**
     * Get the "id" transactionCodes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionCodes> findOne(Long id);

    /**
     * Delete the "id" transactionCodes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
