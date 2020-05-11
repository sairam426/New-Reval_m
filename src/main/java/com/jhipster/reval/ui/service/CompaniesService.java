package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Companies;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Companies}.
 */
public interface CompaniesService {

    /**
     * Save a companies.
     *
     * @param companies the entity to save.
     * @return the persisted entity.
     */
    Companies save(Companies companies);

    /**
     * Get all the companies.
     *
     * @return the list of entities.
     */
    List<Companies> findAll();

    /**
     * Get the "id" companies.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Companies> findOne(Long id);

    /**
     * Delete the "id" companies.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
