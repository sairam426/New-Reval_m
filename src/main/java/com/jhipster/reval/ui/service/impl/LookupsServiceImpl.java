package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.LookupsService;
import com.jhipster.reval.ui.domain.Lookups;
import com.jhipster.reval.ui.repository.LookupsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Lookups}.
 */
@Service
@Transactional
public class LookupsServiceImpl implements LookupsService {

    private final Logger log = LoggerFactory.getLogger(LookupsServiceImpl.class);

    private final LookupsRepository lookupsRepository;

    public LookupsServiceImpl(LookupsRepository lookupsRepository) {
        this.lookupsRepository = lookupsRepository;
    }

    /**
     * Save a lookups.
     *
     * @param lookups the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Lookups save(Lookups lookups) {
        log.debug("Request to save Lookups : {}", lookups);
        return lookupsRepository.save(lookups);
    }

    /**
     * Get all the lookups.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Lookups> findAll() {
        log.debug("Request to get all Lookups");
        return lookupsRepository.findAll();
    }

    /**
     * Get one lookups by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Lookups> findOne(Long id) {
        log.debug("Request to get Lookups : {}", id);
        return lookupsRepository.findById(id);
    }

    /**
     * Delete the lookups by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lookups : {}", id);
        lookupsRepository.deleteById(id);
    }
}
