package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Organizations;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Organizations}.
 */
public interface OrganizationsService {

    /**
     * Save a organizations.
     *
     * @param organizations the entity to save.
     * @return the persisted entity.
     */
    Organizations save(Organizations organizations);

    /**
     * Get all the organizations.
     *
     * @return the list of entities.
     */
    List<Organizations> findAll();

    /**
     * Get the "id" organizations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Organizations> findOne(Long id);

    /**
     * Delete the "id" organizations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
