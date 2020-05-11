package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.TransactionCodeParams;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TransactionCodeParams}.
 */
public interface TransactionCodeParamsService {

    /**
     * Save a transactionCodeParams.
     *
     * @param transactionCodeParams the entity to save.
     * @return the persisted entity.
     */
    TransactionCodeParams save(TransactionCodeParams transactionCodeParams);

    /**
     * Get all the transactionCodeParams.
     *
     * @return the list of entities.
     */
    List<TransactionCodeParams> findAll();

    /**
     * Get the "id" transactionCodeParams.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionCodeParams> findOne(Long id);

    /**
     * Delete the "id" transactionCodeParams.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
