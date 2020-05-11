package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.SystemAccess;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SystemAccess}.
 */
public interface SystemAccessService {

    /**
     * Save a systemAccess.
     *
     * @param systemAccess the entity to save.
     * @return the persisted entity.
     */
    SystemAccess save(SystemAccess systemAccess);

    /**
     * Get all the systemAccesses.
     *
     * @return the list of entities.
     */
    List<SystemAccess> findAll();

    /**
     * Get the "id" systemAccess.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SystemAccess> findOne(Long id);

    /**
     * Delete the "id" systemAccess.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
