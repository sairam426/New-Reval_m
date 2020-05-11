package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.TransactionCodes;
import com.jhipster.reval.ui.service.TransactionCodesService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jhipster.reval.ui.domain.TransactionCodes}.
 */
@RestController
@RequestMapping("/api")
public class TransactionCodesResource {

    private final Logger log = LoggerFactory.getLogger(TransactionCodesResource.class);

    private static final String ENTITY_NAME = "transactionCodes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionCodesService transactionCodesService;

    public TransactionCodesResource(TransactionCodesService transactionCodesService) {
        this.transactionCodesService = transactionCodesService;
    }

    /**
     * {@code POST  /transaction-codes} : Create a new transactionCodes.
     *
     * @param transactionCodes the transactionCodes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionCodes, or with status {@code 400 (Bad Request)} if the transactionCodes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaction-codes")
    public ResponseEntity<TransactionCodes> createTransactionCodes(@RequestBody TransactionCodes transactionCodes) throws URISyntaxException {
        log.debug("REST request to save TransactionCodes : {}", transactionCodes);
        if (transactionCodes.getId() != null) {
            throw new BadRequestAlertException("A new transactionCodes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionCodes result = transactionCodesService.save(transactionCodes);
        return ResponseEntity.created(new URI("/api/transaction-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaction-codes} : Updates an existing transactionCodes.
     *
     * @param transactionCodes the transactionCodes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionCodes,
     * or with status {@code 400 (Bad Request)} if the transactionCodes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionCodes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaction-codes")
    public ResponseEntity<TransactionCodes> updateTransactionCodes(@RequestBody TransactionCodes transactionCodes) throws URISyntaxException {
        log.debug("REST request to update TransactionCodes : {}", transactionCodes);
        if (transactionCodes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionCodes result = transactionCodesService.save(transactionCodes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionCodes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transaction-codes} : get all the transactionCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionCodes in body.
     */
    @GetMapping("/transaction-codes")
    public List<TransactionCodes> getAllTransactionCodes() {
        log.debug("REST request to get all TransactionCodes");
        return transactionCodesService.findAll();
    }

    /**
     * {@code GET  /transaction-codes/:id} : get the "id" transactionCodes.
     *
     * @param id the id of the transactionCodes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionCodes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaction-codes/{id}")
    public ResponseEntity<TransactionCodes> getTransactionCodes(@PathVariable Long id) {
        log.debug("REST request to get TransactionCodes : {}", id);
        Optional<TransactionCodes> transactionCodes = transactionCodesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionCodes);
    }

    /**
     * {@code DELETE  /transaction-codes/:id} : delete the "id" transactionCodes.
     *
     * @param id the id of the transactionCodes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaction-codes/{id}")
    public ResponseEntity<Void> deleteTransactionCodes(@PathVariable Long id) {
        log.debug("REST request to delete TransactionCodes : {}", id);
        transactionCodesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
