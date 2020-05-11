package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.LookupTypes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link LookupTypes}.
 */
public interface LookupTypesService {

    /**
     * Save a lookupTypes.
     *
     * @param lookupTypes the entity to save.
     * @return the persisted entity.
     */
    LookupTypes save(LookupTypes lookupTypes);

    /**
     * Get all the lookupTypes.
     *
     * @return the list of entities.
     */
    List<LookupTypes> findAll();

    /**
     * Get the "id" lookupTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LookupTypes> findOne(Long id);

    /**
     * Delete the "id" lookupTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
