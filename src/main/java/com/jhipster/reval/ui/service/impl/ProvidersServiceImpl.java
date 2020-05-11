package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.ProvidersService;
import com.jhipster.reval.ui.domain.Providers;
import com.jhipster.reval.ui.repository.ProvidersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Providers}.
 */
@Service
@Transactional
public class ProvidersServiceImpl implements ProvidersService {

    private final Logger log = LoggerFactory.getLogger(ProvidersServiceImpl.class);

    private final ProvidersRepository providersRepository;

    public ProvidersServiceImpl(ProvidersRepository providersRepository) {
        this.providersRepository = providersRepository;
    }

    /**
     * Save a providers.
     *
     * @param providers the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Providers save(Providers providers) {
        log.debug("Request to save Providers : {}", providers);
        return providersRepository.save(providers);
    }

    /**
     * Get all the providers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Providers> findAll() {
        log.debug("Request to get all Providers");
        return providersRepository.findAll();
    }

    /**
     * Get one providers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Providers> findOne(Long id) {
        log.debug("Request to get Providers : {}", id);
        return providersRepository.findById(id);
    }

    /**
     * Delete the providers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Providers : {}", id);
        providersRepository.deleteById(id);
    }
}
