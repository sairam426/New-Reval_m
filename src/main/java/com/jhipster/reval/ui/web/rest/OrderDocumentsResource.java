package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.OrderDocuments;
import com.jhipster.reval.ui.service.OrderDocumentsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.OrderDocumentsCriteria;
import com.jhipster.reval.ui.service.OrderDocumentsQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.OrderDocuments}.
 */
@RestController
@RequestMapping("/api")
public class OrderDocumentsResource {

    private final Logger log = LoggerFactory.getLogger(OrderDocumentsResource.class);

    private static final String ENTITY_NAME = "orderDocuments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderDocumentsService orderDocumentsService;

    private final OrderDocumentsQueryService orderDocumentsQueryService;

    public OrderDocumentsResource(OrderDocumentsService orderDocumentsService, OrderDocumentsQueryService orderDocumentsQueryService) {
        this.orderDocumentsService = orderDocumentsService;
        this.orderDocumentsQueryService = orderDocumentsQueryService;
    }

    /**
     * {@code POST  /order-documents} : Create a new orderDocuments.
     *
     * @param orderDocuments the orderDocuments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDocuments, or with status {@code 400 (Bad Request)} if the orderDocuments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-documents")
    public ResponseEntity<OrderDocuments> createOrderDocuments(@Valid @RequestBody OrderDocuments orderDocuments) throws URISyntaxException {
        log.debug("REST request to save OrderDocuments : {}", orderDocuments);
        if (orderDocuments.getId() != null) {
            throw new BadRequestAlertException("A new orderDocuments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderDocuments result = orderDocumentsService.save(orderDocuments);
        return ResponseEntity.created(new URI("/api/order-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-documents} : Updates an existing orderDocuments.
     *
     * @param orderDocuments the orderDocuments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDocuments,
     * or with status {@code 400 (Bad Request)} if the orderDocuments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderDocuments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-documents")
    public ResponseEntity<OrderDocuments> updateOrderDocuments(@Valid @RequestBody OrderDocuments orderDocuments) throws URISyntaxException {
        log.debug("REST request to update OrderDocuments : {}", orderDocuments);
        if (orderDocuments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderDocuments result = orderDocumentsService.save(orderDocuments);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderDocuments.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-documents} : get all the orderDocuments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderDocuments in body.
     */
    @GetMapping("/order-documents")
    public ResponseEntity<List<OrderDocuments>> getAllOrderDocuments(OrderDocumentsCriteria criteria) {
        log.debug("REST request to get OrderDocuments by criteria: {}", criteria);
        List<OrderDocuments> entityList = orderDocumentsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /order-documents/count} : count all the orderDocuments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/order-documents/count")
    public ResponseEntity<Long> countOrderDocuments(OrderDocumentsCriteria criteria) {
        log.debug("REST request to count OrderDocuments by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderDocumentsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /order-documents/:id} : get the "id" orderDocuments.
     *
     * @param id the id of the orderDocuments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDocuments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-documents/{id}")
    public ResponseEntity<OrderDocuments> getOrderDocuments(@PathVariable Long id) {
        log.debug("REST request to get OrderDocuments : {}", id);
        Optional<OrderDocuments> orderDocuments = orderDocumentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderDocuments);
    }

    /**
     * {@code DELETE  /order-documents/:id} : delete the "id" orderDocuments.
     *
     * @param id the id of the orderDocuments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-documents/{id}")
    public ResponseEntity<Void> deleteOrderDocuments(@PathVariable Long id) {
        log.debug("REST request to delete OrderDocuments : {}", id);
        orderDocumentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
