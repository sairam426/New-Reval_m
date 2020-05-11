package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.OrganizationsService;
import com.jhipster.reval.ui.domain.Organizations;
import com.jhipster.reval.ui.repository.OrganizationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Organizations}.
 */
@Service
@Transactional
public class OrganizationsServiceImpl implements OrganizationsService {

    private final Logger log = LoggerFactory.getLogger(OrganizationsServiceImpl.class);

    private final OrganizationsRepository organizationsRepository;

    public OrganizationsServiceImpl(OrganizationsRepository organizationsRepository) {
        this.organizationsRepository = organizationsRepository;
    }

    /**
     * Save a organizations.
     *
     * @param organizations the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Organizations save(Organizations organizations) {
        log.debug("Request to save Organizations : {}", organizations);
        return organizationsRepository.save(organizations);
    }

    /**
     * Get all the organizations.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Organizations> findAll() {
        log.debug("Request to get all Organizations");
        return organizationsRepository.findAll();
    }

    /**
     * Get one organizations by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Organizations> findOne(Long id) {
        log.debug("Request to get Organizations : {}", id);
        return organizationsRepository.findById(id);
    }

    /**
     * Delete the organizations by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organizations : {}", id);
        organizationsRepository.deleteById(id);
    }
}
