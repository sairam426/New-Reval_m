package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.SystemAccess;
import com.jhipster.reval.ui.service.SystemAccessService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.SystemAccessCriteria;
import com.jhipster.reval.ui.service.SystemAccessQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.SystemAccess}.
 */
@RestController
@RequestMapping("/api")
public class SystemAccessResource {

    private final Logger log = LoggerFactory.getLogger(SystemAccessResource.class);

    private static final String ENTITY_NAME = "systemAccess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SystemAccessService systemAccessService;

    private final SystemAccessQueryService systemAccessQueryService;

    public SystemAccessResource(SystemAccessService systemAccessService, SystemAccessQueryService systemAccessQueryService) {
        this.systemAccessService = systemAccessService;
        this.systemAccessQueryService = systemAccessQueryService;
    }

    /**
     * {@code POST  /system-accesses} : Create a new systemAccess.
     *
     * @param systemAccess the systemAccess to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new systemAccess, or with status {@code 400 (Bad Request)} if the systemAccess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/system-accesses")
    public ResponseEntity<SystemAccess> createSystemAccess(@Valid @RequestBody SystemAccess systemAccess) throws URISyntaxException {
        log.debug("REST request to save SystemAccess : {}", systemAccess);
        if (systemAccess.getId() != null) {
            throw new BadRequestAlertException("A new systemAccess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemAccess result = systemAccessService.save(systemAccess);
        return ResponseEntity.created(new URI("/api/system-accesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /system-accesses} : Updates an existing systemAccess.
     *
     * @param systemAccess the systemAccess to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemAccess,
     * or with status {@code 400 (Bad Request)} if the systemAccess is not valid,
     * or with status {@code 500 (Internal Server Error)} if the systemAccess couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/system-accesses")
    public ResponseEntity<SystemAccess> updateSystemAccess(@Valid @RequestBody SystemAccess systemAccess) throws URISyntaxException {
        log.debug("REST request to update SystemAccess : {}", systemAccess);
        if (systemAccess.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemAccess result = systemAccessService.save(systemAccess);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemAccess.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /system-accesses} : get all the systemAccesses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of systemAccesses in body.
     */
    @GetMapping("/system-accesses")
    public ResponseEntity<List<SystemAccess>> getAllSystemAccesses(SystemAccessCriteria criteria) {
        log.debug("REST request to get SystemAccesses by criteria: {}", criteria);
        List<SystemAccess> entityList = systemAccessQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /system-accesses/count} : count all the systemAccesses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/system-accesses/count")
    public ResponseEntity<Long> countSystemAccesses(SystemAccessCriteria criteria) {
        log.debug("REST request to count SystemAccesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(systemAccessQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /system-accesses/:id} : get the "id" systemAccess.
     *
     * @param id the id of the systemAccess to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the systemAccess, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/system-accesses/{id}")
    public ResponseEntity<SystemAccess> getSystemAccess(@PathVariable Long id) {
        log.debug("REST request to get SystemAccess : {}", id);
        Optional<SystemAccess> systemAccess = systemAccessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemAccess);
    }

    /**
     * {@code DELETE  /system-accesses/:id} : delete the "id" systemAccess.
     *
     * @param id the id of the systemAccess to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/system-accesses/{id}")
    public ResponseEntity<Void> deleteSystemAccess(@PathVariable Long id) {
        log.debug("REST request to delete SystemAccess : {}", id);
        systemAccessService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
