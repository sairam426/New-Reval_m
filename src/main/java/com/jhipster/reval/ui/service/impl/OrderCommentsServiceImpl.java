package com.jhipster.reval.ui.service.impl;

import com.jhipster.reval.ui.service.OrderCommentsService;
import com.jhipster.reval.ui.domain.OrderComments;
import com.jhipster.reval.ui.repository.OrderCommentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderComments}.
 */
@Service
@Transactional
public class OrderCommentsServiceImpl implements OrderCommentsService {

    private final Logger log = LoggerFactory.getLogger(OrderCommentsServiceImpl.class);

    private final OrderCommentsRepository orderCommentsRepository;

    public OrderCommentsServiceImpl(OrderCommentsRepository orderCommentsRepository) {
        this.orderCommentsRepository = orderCommentsRepository;
    }

    /**
     * Save a orderComments.
     *
     * @param orderComments the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderComments save(OrderComments orderComments) {
        log.debug("Request to save OrderComments : {}", orderComments);
        return orderCommentsRepository.save(orderComments);
    }

    /**
     * Get all the orderComments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrderComments> findAll() {
        log.debug("Request to get all OrderComments");
        return orderCommentsRepository.findAll();
    }

    /**
     * Get one orderComments by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderComments> findOne(Long id) {
        log.debug("Request to get OrderComments : {}", id);
        return orderCommentsRepository.findById(id);
    }

    /**
     * Delete the orderComments by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderComments : {}", id);
        orderCommentsRepository.deleteById(id);
    }
}
