package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.OrderDocumentsService;
import com.jhipster.reval.ui.domain.OrderDocuments;
import com.jhipster.reval.ui.repository.OrderDocumentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderDocuments}.
 */
@Service
@Transactional
public class OrderDocumentsServiceImpl implements OrderDocumentsService {

    private final Logger log = LoggerFactory.getLogger(OrderDocumentsServiceImpl.class);

    private final OrderDocumentsRepository orderDocumentsRepository;

    public OrderDocumentsServiceImpl(OrderDocumentsRepository orderDocumentsRepository) {
        this.orderDocumentsRepository = orderDocumentsRepository;
    }

    /**
     * Save a orderDocuments.
     *
     * @param orderDocuments the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderDocuments save(OrderDocuments orderDocuments) {
        log.debug("Request to save OrderDocuments : {}", orderDocuments);
        return orderDocumentsRepository.save(orderDocuments);
    }

    /**
     * Get all the orderDocuments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderDocuments> findAll() {
        log.debug("Request to get all OrderDocuments");
        return orderDocumentsRepository.findAll();
    }

    /**
     * Get one orderDocuments by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDocuments> findOne(Long id) {
        log.debug("Request to get OrderDocuments : {}", id);
        return orderDocumentsRepository.findById(id);
    }

    /**
     * Delete the orderDocuments by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderDocuments : {}", id);
        orderDocumentsRepository.deleteById(id);
    }
}
