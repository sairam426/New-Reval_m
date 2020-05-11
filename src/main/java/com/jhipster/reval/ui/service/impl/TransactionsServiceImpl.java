package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.TransactionsService;
import com.jhipster.reval.ui.domain.Transactions;
import com.jhipster.reval.ui.repository.TransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Transactions}.
 */
@Service
@Transactional
public class TransactionsServiceImpl implements TransactionsService {

    private final Logger log = LoggerFactory.getLogger(TransactionsServiceImpl.class);

    private final TransactionsRepository transactionsRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * Save a transactions.
     *
     * @param transactions the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Transactions save(Transactions transactions) {
        log.debug("Request to save Transactions : {}", transactions);
        return transactionsRepository.save(transactions);
    }

    /**
     * Get all the transactions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Transactions> findAll() {
        log.debug("Request to get all Transactions");
        return transactionsRepository.findAll();
    }

    /**
     * Get one transactions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Transactions> findOne(Long id) {
        log.debug("Request to get Transactions : {}", id);
        return transactionsRepository.findById(id);
    }

    /**
     * Delete the transactions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transactions : {}", id);
        transactionsRepository.deleteById(id);
    }
}
