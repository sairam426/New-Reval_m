package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Providers;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Providers}.
 */
public interface ProvidersService {

    /**
     * Save a providers.
     *
     * @param providers the entity to save.
     * @return the persisted entity.
     */
    Providers save(Providers providers);

    /**
     * Get all the providers.
     *
     * @return the list of entities.
     */
    List<Providers> findAll();

    /**
     * Get the "id" providers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Providers> findOne(Long id);

    /**
     * Delete the "id" providers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
