package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.BranchesService;
import com.jhipster.reval.ui.domain.Branches;
import com.jhipster.reval.ui.repository.BranchesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Branches}.
 */
@Service
@Transactional
public class BranchesServiceImpl implements BranchesService {

    private final Logger log = LoggerFactory.getLogger(BranchesServiceImpl.class);

    private final BranchesRepository branchesRepository;

    public BranchesServiceImpl(BranchesRepository branchesRepository) {
        this.branchesRepository = branchesRepository;
    }

    /**
     * Save a branches.
     *
     * @param branches the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Branches save(Branches branches) {
        log.debug("Request to save Branches : {}", branches);
        return branchesRepository.save(branches);
    }

    /**
     * Get all the branches.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Branches> findAll() {
        log.debug("Request to get all Branches");
        return branchesRepository.findAll();
    }

    /**
     * Get one branches by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Branches> findOne(Long id) {
        log.debug("Request to get Branches : {}", id);
        return branchesRepository.findById(id);
    }

    /**
     * Delete the branches by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Branches : {}", id);
        branchesRepository.deleteById(id);
    }
}
