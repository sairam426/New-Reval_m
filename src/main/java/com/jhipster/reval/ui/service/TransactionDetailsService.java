package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.TransactionDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TransactionDetails}.
 */
public interface TransactionDetailsService {

    /**
     * Save a transactionDetails.
     *
     * @param transactionDetails the entity to save.
     * @return the persisted entity.
     */
    TransactionDetails save(TransactionDetails transactionDetails);

    /**
     * Get all the transactionDetails.
     *
     * @return the list of entities.
     */
    List<TransactionDetails> findAll();

    /**
     * Get the "id" transactionDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionDetails> findOne(Long id);

    /**
     * Delete the "id" transactionDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
