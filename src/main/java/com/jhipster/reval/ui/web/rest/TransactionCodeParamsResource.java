package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.TransactionCodeParams;
import com.jhipster.reval.ui.service.TransactionCodeParamsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.TransactionCodeParams}.
 */
@RestController
@RequestMapping("/api")
public class TransactionCodeParamsResource {

    private final Logger log = LoggerFactory.getLogger(TransactionCodeParamsResource.class);

    private static final String ENTITY_NAME = "transactionCodeParams";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionCodeParamsService transactionCodeParamsService;

    public TransactionCodeParamsResource(TransactionCodeParamsService transactionCodeParamsService) {
        this.transactionCodeParamsService = transactionCodeParamsService;
    }

    /**
     * {@code POST  /transaction-code-params} : Create a new transactionCodeParams.
     *
     * @param transactionCodeParams the transactionCodeParams to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionCodeParams, or with status {@code 400 (Bad Request)} if the transactionCodeParams has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaction-code-params")
    public ResponseEntity<TransactionCodeParams> createTransactionCodeParams(@Valid @RequestBody TransactionCodeParams transactionCodeParams) throws URISyntaxException {
        log.debug("REST request to save TransactionCodeParams : {}", transactionCodeParams);
        if (transactionCodeParams.getId() != null) {
            throw new BadRequestAlertException("A new transactionCodeParams cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionCodeParams result = transactionCodeParamsService.save(transactionCodeParams);
        return ResponseEntity.created(new URI("/api/transaction-code-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaction-code-params} : Updates an existing transactionCodeParams.
     *
     * @param transactionCodeParams the transactionCodeParams to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionCodeParams,
     * or with status {@code 400 (Bad Request)} if the transactionCodeParams is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionCodeParams couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaction-code-params")
    public ResponseEntity<TransactionCodeParams> updateTransactionCodeParams(@Valid @RequestBody TransactionCodeParams transactionCodeParams) throws URISyntaxException {
        log.debug("REST request to update TransactionCodeParams : {}", transactionCodeParams);
        if (transactionCodeParams.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionCodeParams result = transactionCodeParamsService.save(transactionCodeParams);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionCodeParams.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transaction-code-params} : get all the transactionCodeParams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionCodeParams in body.
     */
    @GetMapping("/transaction-code-params")
    public List<TransactionCodeParams> getAllTransactionCodeParams() {
        log.debug("REST request to get all TransactionCodeParams");
        return transactionCodeParamsService.findAll();
    }

    /**
     * {@code GET  /transaction-code-params/:id} : get the "id" transactionCodeParams.
     *
     * @param id the id of the transactionCodeParams to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionCodeParams, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaction-code-params/{id}")
    public ResponseEntity<TransactionCodeParams> getTransactionCodeParams(@PathVariable Long id) {
        log.debug("REST request to get TransactionCodeParams : {}", id);
        Optional<TransactionCodeParams> transactionCodeParams = transactionCodeParamsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionCodeParams);
    }

    /**
     * {@code DELETE  /transaction-code-params/:id} : delete the "id" transactionCodeParams.
     *
     * @param id the id of the transactionCodeParams to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaction-code-params/{id}")
    public ResponseEntity<Void> deleteTransactionCodeParams(@PathVariable Long id) {
        log.debug("REST request to delete TransactionCodeParams : {}", id);
        transactionCodeParamsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
