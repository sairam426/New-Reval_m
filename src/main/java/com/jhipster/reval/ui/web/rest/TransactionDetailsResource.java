package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.TransactionDetails;
import com.jhipster.reval.ui.service.TransactionDetailsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.TransactionDetailsCriteria;
import com.jhipster.reval.ui.service.TransactionDetailsQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.TransactionDetails}.
 */
@RestController
@RequestMapping("/api")
public class TransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TransactionDetailsResource.class);

    private static final String ENTITY_NAME = "transactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionDetailsService transactionDetailsService;

    private final TransactionDetailsQueryService transactionDetailsQueryService;

    public TransactionDetailsResource(TransactionDetailsService transactionDetailsService, TransactionDetailsQueryService transactionDetailsQueryService) {
        this.transactionDetailsService = transactionDetailsService;
        this.transactionDetailsQueryService = transactionDetailsQueryService;
    }

    /**
     * {@code POST  /transaction-details} : Create a new transactionDetails.
     *
     * @param transactionDetails the transactionDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionDetails, or with status {@code 400 (Bad Request)} if the transactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaction-details")
    public ResponseEntity<TransactionDetails> createTransactionDetails(@Valid @RequestBody TransactionDetails transactionDetails) throws URISyntaxException {
        log.debug("REST request to save TransactionDetails : {}", transactionDetails);
        if (transactionDetails.getId() != null) {
            throw new BadRequestAlertException("A new transactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionDetails result = transactionDetailsService.save(transactionDetails);
        return ResponseEntity.created(new URI("/api/transaction-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaction-details} : Updates an existing transactionDetails.
     *
     * @param transactionDetails the transactionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionDetails,
     * or with status {@code 400 (Bad Request)} if the transactionDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaction-details")
    public ResponseEntity<TransactionDetails> updateTransactionDetails(@Valid @RequestBody TransactionDetails transactionDetails) throws URISyntaxException {
        log.debug("REST request to update TransactionDetails : {}", transactionDetails);
        if (transactionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionDetails result = transactionDetailsService.save(transactionDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transaction-details} : get all the transactionDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionDetails in body.
     */
    @GetMapping("/transaction-details")
    public ResponseEntity<List<TransactionDetails>> getAllTransactionDetails(TransactionDetailsCriteria criteria) {
        log.debug("REST request to get TransactionDetails by criteria: {}", criteria);
        List<TransactionDetails> entityList = transactionDetailsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /transaction-details/count} : count all the transactionDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/transaction-details/count")
    public ResponseEntity<Long> countTransactionDetails(TransactionDetailsCriteria criteria) {
        log.debug("REST request to count TransactionDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(transactionDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /transaction-details/:id} : get the "id" transactionDetails.
     *
     * @param id the id of the transactionDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaction-details/{id}")
    public ResponseEntity<TransactionDetails> getTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to get TransactionDetails : {}", id);
        Optional<TransactionDetails> transactionDetails = transactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionDetails);
    }

    /**
     * {@code DELETE  /transaction-details/:id} : delete the "id" transactionDetails.
     *
     * @param id the id of the transactionDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaction-details/{id}")
    public ResponseEntity<Void> deleteTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to delete TransactionDetails : {}", id);
        transactionDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
