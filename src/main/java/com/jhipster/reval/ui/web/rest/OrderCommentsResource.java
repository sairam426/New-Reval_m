package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.OrderComments;
import com.jhipster.reval.ui.service.OrderCommentsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.OrderCommentsCriteria;
import com.jhipster.reval.ui.service.OrderCommentsQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jhipster.reval.ui.domain.OrderComments}.
 */
@RestController
@RequestMapping("/api")
public class OrderCommentsResource {

    private final Logger log = LoggerFactory.getLogger(OrderCommentsResource.class);

    private static final String ENTITY_NAME = "orderComments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderCommentsService orderCommentsService;

    private final OrderCommentsQueryService orderCommentsQueryService;

    public OrderCommentsResource(OrderCommentsService orderCommentsService, OrderCommentsQueryService orderCommentsQueryService) {
        this.orderCommentsService = orderCommentsService;
        this.orderCommentsQueryService = orderCommentsQueryService;
    }

    /**
     * {@code POST  /order-comments} : Create a new orderComments.
     *
     * @param orderComments the orderComments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderComments, or with status {@code 400 (Bad Request)} if the orderComments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-comments")
    public ResponseEntity<OrderComments> createOrderComments(@Valid @RequestBody OrderComments orderComments) throws URISyntaxException {
        log.debug("REST request to save OrderComments : {}", orderComments);
        if (orderComments.getId() != null) {
            throw new BadRequestAlertException("A new orderComments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderComments result = orderCommentsService.save(orderComments);
        return ResponseEntity.created(new URI("/api/order-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-comments} : Updates an existing orderComments.
     *
     * @param orderComments the orderComments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderComments,
     * or with status {@code 400 (Bad Request)} if the orderComments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderComments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-comments")
    public ResponseEntity<OrderComments> updateOrderComments(@Valid @RequestBody OrderComments orderComments) throws URISyntaxException {
        log.debug("REST request to update OrderComments : {}", orderComments);
        if (orderComments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderComments result = orderCommentsService.save(orderComments);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderComments.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-comments} : get all the orderComments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderComments in body.
     */
    @GetMapping("/order-comments")
    public ResponseEntity<List<OrderComments>> getAllOrderComments(OrderCommentsCriteria criteria) {
        log.debug("REST request to get OrderComments by criteria: {}", criteria);
        List<OrderComments> entityList = orderCommentsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /order-comments/count} : count all the orderComments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/order-comments/count")
    public ResponseEntity<Long> countOrderComments(OrderCommentsCriteria criteria) {
        log.debug("REST request to count OrderComments by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderCommentsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /order-comments/:id} : get the "id" orderComments.
     *
     * @param id the id of the orderComments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderComments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-comments/{id}")
    public ResponseEntity<OrderComments> getOrderComments(@PathVariable Long id) {
        log.debug("REST request to get OrderComments : {}", id);
        Optional<OrderComments> orderComments = orderCommentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderComments);
    }

    /**
     * {@code DELETE  /order-comments/:id} : delete the "id" orderComments.
     *
     * @param id the id of the orderComments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-comments/{id}")
    public ResponseEntity<Void> deleteOrderComments(@PathVariable Long id) {
        log.debug("REST request to delete OrderComments : {}", id);
        orderCommentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
