package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Lookups;
import com.jhipster.reval.ui.service.LookupsService;
import com.jhipster.reval.ui.web.rest.errors.BadRequestAlertException;
import com.jhipster.reval.ui.service.dto.LookupsCriteria;
import com.jhipster.reval.ui.service.LookupsQueryService;

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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Lookups}.
 */
@RestController
@RequestMapping("/api")
public class LookupsResource {

    private final Logger log = LoggerFactory.getLogger(LookupsResource.class);

    private static final String ENTITY_NAME = "lookups";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LookupsService lookupsService;

    private final LookupsQueryService lookupsQueryService;

    public LookupsResource(LookupsService lookupsService, LookupsQueryService lookupsQueryService) {
        this.lookupsService = lookupsService;
        this.lookupsQueryService = lookupsQueryService;
    }

    /**
     * {@code POST  /lookups} : Create a new lookups.
     *
     * @param lookups the lookups to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lookups, or with status {@code 400 (Bad Request)} if the lookups has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lookups")
    public ResponseEntity<Lookups> createLookups(@Valid @RequestBody Lookups lookups) throws URISyntaxException {
        log.debug("REST request to save Lookups : {}", lookups);
        if (lookups.getId() != null) {
            throw new BadRequestAlertException("A new lookups cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Lookups result = lookupsService.save(lookups);
        return ResponseEntity.created(new URI("/api/lookups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lookups} : Updates an existing lookups.
     *
     * @param lookups the lookups to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lookups,
     * or with status {@code 400 (Bad Request)} if the lookups is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lookups couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lookups")
    public ResponseEntity<Lookups> updateLookups(@Valid @RequestBody Lookups lookups) throws URISyntaxException {
        log.debug("REST request to update Lookups : {}", lookups);
        if (lookups.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Lookups result = lookupsService.save(lookups);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lookups.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lookups} : get all the lookups.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lookups in body.
     */
    @GetMapping("/lookups")
    public ResponseEntity<List<Lookups>> getAllLookups(LookupsCriteria criteria) {
        log.debug("REST request to get Lookups by criteria: {}", criteria);
        List<Lookups> entityList = lookupsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /lookups/count} : count all the lookups.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/lookups/count")
    public ResponseEntity<Long> countLookups(LookupsCriteria criteria) {
        log.debug("REST request to count Lookups by criteria: {}", criteria);
        return ResponseEntity.ok().body(lookupsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /lookups/:id} : get the "id" lookups.
     *
     * @param id the id of the lookups to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lookups, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lookups/{id}")
    public ResponseEntity<Lookups> getLookups(@PathVariable Long id) {
        log.debug("REST request to get Lookups : {}", id);
        Optional<Lookups> lookups = lookupsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lookups);
    }

    /**
     * {@code DELETE  /lookups/:id} : delete the "id" lookups.
     *
     * @param id the id of the lookups to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lookups/{id}")
    public ResponseEntity<Void> deleteLookups(@PathVariable Long id) {
        log.debug("REST request to delete Lookups : {}", id);
        lookupsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
