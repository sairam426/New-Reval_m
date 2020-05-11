package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.TransactionDetailsService;
import com.jhipster.reval.ui.domain.TransactionDetails;
import com.jhipster.reval.ui.repository.TransactionDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionDetails}.
 */
@Service
@Transactional
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(TransactionDetailsServiceImpl.class);

    private final TransactionDetailsRepository transactionDetailsRepository;

    public TransactionDetailsServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    /**
     * Save a transactionDetails.
     *
     * @param transactionDetails the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransactionDetails save(TransactionDetails transactionDetails) {
        log.debug("Request to save TransactionDetails : {}", transactionDetails);
        return transactionDetailsRepository.save(transactionDetails);
    }

    /**
     * Get all the transactionDetails.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransactionDetails> findAll() {
        log.debug("Request to get all TransactionDetails");
        return transactionDetailsRepository.findAll();
    }

    /**
     * Get one transactionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionDetails> findOne(Long id) {
        log.debug("Request to get TransactionDetails : {}", id);
        return transactionDetailsRepository.findById(id);
    }

    /**
     * Delete the transactionDetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionDetails : {}", id);
        transactionDetailsRepository.deleteById(id);
    }
}
