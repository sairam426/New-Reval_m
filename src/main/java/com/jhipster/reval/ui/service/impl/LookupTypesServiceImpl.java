package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.LookupTypesService;
import com.jhipster.reval.ui.domain.LookupTypes;
import com.jhipster.reval.ui.repository.LookupTypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link LookupTypes}.
 */
@Service
@Transactional
public class LookupTypesServiceImpl implements LookupTypesService {

    private final Logger log = LoggerFactory.getLogger(LookupTypesServiceImpl.class);

    private final LookupTypesRepository lookupTypesRepository;

    public LookupTypesServiceImpl(LookupTypesRepository lookupTypesRepository) {
        this.lookupTypesRepository = lookupTypesRepository;
    }

    /**
     * Save a lookupTypes.
     *
     * @param lookupTypes the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LookupTypes save(LookupTypes lookupTypes) {
        log.debug("Request to save LookupTypes : {}", lookupTypes);
        return lookupTypesRepository.save(lookupTypes);
    }

    /**
     * Get all the lookupTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LookupTypes> findAll() {
        log.debug("Request to get all LookupTypes");
        return lookupTypesRepository.findAll();
    }

    /**
     * Get one lookupTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LookupTypes> findOne(Long id) {
        log.debug("Request to get LookupTypes : {}", id);
        return lookupTypesRepository.findById(id);
    }

    /**
     * Delete the lookupTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LookupTypes : {}", id);
        lookupTypesRepository.deleteById(id);
    }
}
