package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.CompaniesService;
import com.jhipster.reval.ui.domain.Companies;
import com.jhipster.reval.ui.repository.CompaniesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Companies}.
 */
@Service
@Transactional
public class CompaniesServiceImpl implements CompaniesService {

    private final Logger log = LoggerFactory.getLogger(CompaniesServiceImpl.class);

    private final CompaniesRepository companiesRepository;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    /**
     * Save a companies.
     *
     * @param companies the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Companies save(Companies companies) {
        log.debug("Request to save Companies : {}", companies);
        return companiesRepository.save(companies);
    }

    /**
     * Get all the companies.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Companies> findAll() {
        log.debug("Request to get all Companies");
        return companiesRepository.findAll();
    }

    /**
     * Get one companies by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Companies> findOne(Long id) {
        log.debug("Request to get Companies : {}", id);
        return companiesRepository.findById(id);
    }

    /**
     * Delete the companies by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Companies : {}", id);
        companiesRepository.deleteById(id);
    }
}
