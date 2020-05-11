package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.TransactionCodeParamsService;
import com.jhipster.reval.ui.domain.TransactionCodeParams;
import com.jhipster.reval.ui.repository.TransactionCodeParamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionCodeParams}.
 */
@Service
@Transactional
public class TransactionCodeParamsServiceImpl implements TransactionCodeParamsService {

    private final Logger log = LoggerFactory.getLogger(TransactionCodeParamsServiceImpl.class);

    private final TransactionCodeParamsRepository transactionCodeParamsRepository;

    public TransactionCodeParamsServiceImpl(TransactionCodeParamsRepository transactionCodeParamsRepository) {
        this.transactionCodeParamsRepository = transactionCodeParamsRepository;
    }

    /**
     * Save a transactionCodeParams.
     *
     * @param transactionCodeParams the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransactionCodeParams save(TransactionCodeParams transactionCodeParams) {
        log.debug("Request to save TransactionCodeParams : {}", transactionCodeParams);
        return transactionCodeParamsRepository.save(transactionCodeParams);
    }

    /**
     * Get all the transactionCodeParams.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransactionCodeParams> findAll() {
        log.debug("Request to get all TransactionCodeParams");
        return transactionCodeParamsRepository.findAll();
    }

    /**
     * Get one transactionCodeParams by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionCodeParams> findOne(Long id) {
        log.debug("Request to get TransactionCodeParams : {}", id);
        return transactionCodeParamsRepository.findById(id);
    }

    /**
     * Delete the transactionCodeParams by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionCodeParams : {}", id);
        transactionCodeParamsRepository.deleteById(id);
    }
}
