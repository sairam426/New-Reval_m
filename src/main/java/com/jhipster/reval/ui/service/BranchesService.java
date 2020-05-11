package com.jhipster.reval.ui.service;

import com.jhipster.reval.ui.domain.Branches;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Branches}.
 */
public interface BranchesService {

    /**
     * Save a branches.
     *
     * @param branches the entity to save.
     * @return the persisted entity.
     */
    Branches save(Branches branches);

    /**
     * Get all the branches.
     *
     * @return the list of entities.
     */
    List<Branches> findAll();

    /**
     * Get the "id" branches.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Branches> findOne(Long id);

    /**
     * Delete the "id" branches.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
