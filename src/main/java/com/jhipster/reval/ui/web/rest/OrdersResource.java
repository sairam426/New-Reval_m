package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Orders;
import com.jhipster.reval.ui.service.OrdersService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.OrdersCriteria;
import com.jhipster.reval.ui.service.OrdersQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Orders}.
 */
@RestController
@RequestMapping("/api")
public class OrdersResource {

    private final Logger log = LoggerFactory.getLogger(OrdersResource.class);

    private static final String ENTITY_NAME = "orders";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdersService ordersService;

    private final OrdersQueryService ordersQueryService;

    public OrdersResource(OrdersService ordersService, OrdersQueryService ordersQueryService) {
        this.ordersService = ordersService;
        this.ordersQueryService = ordersQueryService;
    }

    /**
     * {@code POST  /orders} : Create a new orders.
     *
     * @param orders the orders to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orders, or with status {@code 400 (Bad Request)} if the orders has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders")
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to save Orders : {}", orders);
        if (orders.getId() != null) {
            throw new BadRequestAlertException("A new orders cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /orders} : Updates an existing orders.
     *
     * @param orders the orders to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orders,
     * or with status {@code 400 (Bad Request)} if the orders is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orders couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders")
    public ResponseEntity<Orders> updateOrders(@Valid @RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to update Orders : {}", orders);
        if (orders.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orders.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(OrdersCriteria criteria) {
        log.debug("REST request to get Orders by criteria: {}", criteria);
        List<Orders> entityList = ordersQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /orders/count} : count all the orders.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/orders/count")
    public ResponseEntity<Long> countOrders(OrdersCriteria criteria) {
        log.debug("REST request to count Orders by criteria: {}", criteria);
        return ResponseEntity.ok().body(ordersQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /orders/:id} : get the "id" orders.
     *
     * @param id the id of the orders to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orders, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrders(@PathVariable Long id) {
        log.debug("REST request to get Orders : {}", id);
        Optional<Orders> orders = ordersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orders);
    }

    /**
     * {@code DELETE  /orders/:id} : delete the "id" orders.
     *
     * @param id the id of the orders to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
        log.debug("REST request to delete Orders : {}", id);
        ordersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
