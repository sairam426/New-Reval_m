package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.SystemAccessService;
import com.jhipster.reval.ui.domain.SystemAccess;
import com.jhipster.reval.ui.repository.SystemAccessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SystemAccess}.
 */
@Service
@Transactional
public class SystemAccessServiceImpl implements SystemAccessService {

    private final Logger log = LoggerFactory.getLogger(SystemAccessServiceImpl.class);

    private final SystemAccessRepository systemAccessRepository;

    public SystemAccessServiceImpl(SystemAccessRepository systemAccessRepository) {
        this.systemAccessRepository = systemAccessRepository;
    }

    /**
     * Save a systemAccess.
     *
     * @param systemAccess the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SystemAccess save(SystemAccess systemAccess) {
        log.debug("Request to save SystemAccess : {}", systemAccess);
        return systemAccessRepository.save(systemAccess);
    }

    /**
     * Get all the systemAccesses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SystemAccess> findAll() {
        log.debug("Request to get all SystemAccesses");
        return systemAccessRepository.findAll();
    }

    /**
     * Get one systemAccess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemAccess> findOne(Long id) {
        log.debug("Request to get SystemAccess : {}", id);
        return systemAccessRepository.findById(id);
    }

    /**
     * Delete the systemAccess by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemAccess : {}", id);
        systemAccessRepository.deleteById(id);
    }
}
