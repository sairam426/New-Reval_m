package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.TransactionCodesService;
import com.jhipster.reval.ui.domain.TransactionCodes;
import com.jhipster.reval.ui.repository.TransactionCodesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionCodes}.
 */
@Service
@Transactional
public class TransactionCodesServiceImpl implements TransactionCodesService {

    private final Logger log = LoggerFactory.getLogger(TransactionCodesServiceImpl.class);

    private final TransactionCodesRepository transactionCodesRepository;

    public TransactionCodesServiceImpl(TransactionCodesRepository transactionCodesRepository) {
        this.transactionCodesRepository = transactionCodesRepository;
    }

    /**
     * Save a transactionCodes.
     *
     * @param transactionCodes the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransactionCodes save(TransactionCodes transactionCodes) {
        log.debug("Request to save TransactionCodes : {}", transactionCodes);
        return transactionCodesRepository.save(transactionCodes);
    }

    /**
     * Get all the transactionCodes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransactionCodes> findAll() {
        log.debug("Request to get all TransactionCodes");
        return transactionCodesRepository.findAll();
    }

    /**
     * Get one transactionCodes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionCodes> findOne(Long id) {
        log.debug("Request to get TransactionCodes : {}", id);
        return transactionCodesRepository.findById(id);
    }

    /**
     * Delete the transactionCodes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionCodes : {}", id);
        transactionCodesRepository.deleteById(id);
    }
}
