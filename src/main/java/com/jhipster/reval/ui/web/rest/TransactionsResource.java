package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Transactions;
import com.jhipster.reval.ui.service.TransactionsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.TransactionsCriteria;
import com.jhipster.reval.ui.service.TransactionsQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Transactions}.
 */
@RestController
@RequestMapping("/api")
public class TransactionsResource {

    private final Logger log = LoggerFactory.getLogger(TransactionsResource.class);

    private static final String ENTITY_NAME = "transactions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionsService transactionsService;

    private final TransactionsQueryService transactionsQueryService;

    public TransactionsResource(TransactionsService transactionsService, TransactionsQueryService transactionsQueryService) {
        this.transactionsService = transactionsService;
        this.transactionsQueryService = transactionsQueryService;
    }

    /**
     * {@code POST  /transactions} : Create a new transactions.
     *
     * @param transactions the transactions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactions, or with status {@code 400 (Bad Request)} if the transactions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transactions")
    public ResponseEntity<Transactions> createTransactions(@Valid @RequestBody Transactions transactions) throws URISyntaxException {
        log.debug("REST request to save Transactions : {}", transactions);
        if (transactions.getId() != null) {
            throw new BadRequestAlertException("A new transactions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transactions result = transactionsService.save(transactions);
        return ResponseEntity.created(new URI("/api/transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transactions} : Updates an existing transactions.
     *
     * @param transactions the transactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactions,
     * or with status {@code 400 (Bad Request)} if the transactions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transactions")
    public ResponseEntity<Transactions> updateTransactions(@Valid @RequestBody Transactions transactions) throws URISyntaxException {
        log.debug("REST request to update Transactions : {}", transactions);
        if (transactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transactions result = transactionsService.save(transactions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transactions} : get all the transactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactions in body.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions(TransactionsCriteria criteria) {
        log.debug("REST request to get Transactions by criteria: {}", criteria);
        List<Transactions> entityList = transactionsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /transactions/count} : count all the transactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/transactions/count")
    public ResponseEntity<Long> countTransactions(TransactionsCriteria criteria) {
        log.debug("REST request to count Transactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(transactionsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" transactions.
     *
     * @param id the id of the transactions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transactions> getTransactions(@PathVariable Long id) {
        log.debug("REST request to get Transactions : {}", id);
        Optional<Transactions> transactions = transactionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactions);
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" transactions.
     *
     * @param id the id of the transactions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransactions(@PathVariable Long id) {
        log.debug("REST request to delete Transactions : {}", id);
        transactionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
