package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Transactions;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Transactions}.
 */
public interface TransactionsService {

    /**
     * Save a transactions.
     *
     * @param transactions the entity to save.
     * @return the persisted entity.
     */
    Transactions save(Transactions transactions);

    /**
     * Get all the transactions.
     *
     * @return the list of entities.
     */
    List<Transactions> findAll();

    /**
     * Get the "id" transactions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Transactions> findOne(Long id);

    /**
     * Delete the "id" transactions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
