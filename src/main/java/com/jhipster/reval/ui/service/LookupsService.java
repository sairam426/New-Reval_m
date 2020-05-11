package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Lookups;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Lookups}.
 */
public interface LookupsService {

    /**
     * Save a lookups.
     *
     * @param lookups the entity to save.
     * @return the persisted entity.
     */
    Lookups save(Lookups lookups);

    /**
     * Get all the lookups.
     *
     * @return the list of entities.
     */
    List<Lookups> findAll();

    /**
     * Get the "id" lookups.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Lookups> findOne(Long id);

    /**
     * Delete the "id" lookups.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
