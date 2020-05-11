package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Branches;
import com.jhipster.reval.ui.service.BranchesService;
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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Branches}.
 */
@RestController
@RequestMapping("/api")
public class BranchesResource {

    private final Logger log = LoggerFactory.getLogger(BranchesResource.class);

    private static final String ENTITY_NAME = "branches";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchesService branchesService;

    public BranchesResource(BranchesService branchesService) {
        this.branchesService = branchesService;
    }

    /**
     * {@code POST  /branches} : Create a new branches.
     *
     * @param branches the branches to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branches, or with status {@code 400 (Bad Request)} if the branches has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branches")
    public ResponseEntity<Branches> createBranches(@Valid @RequestBody Branches branches) throws URISyntaxException {
        log.debug("REST request to save Branches : {}", branches);
        if (branches.getId() != null) {
            throw new BadRequestAlertException("A new branches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Branches result = branchesService.save(branches);
        return ResponseEntity.created(new URI("/api/branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branches} : Updates an existing branches.
     *
     * @param branches the branches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branches,
     * or with status {@code 400 (Bad Request)} if the branches is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branches")
    public ResponseEntity<Branches> updateBranches(@Valid @RequestBody Branches branches) throws URISyntaxException {
        log.debug("REST request to update Branches : {}", branches);
        if (branches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Branches result = branchesService.save(branches);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, branches.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /branches} : get all the branches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branches in body.
     */
    @GetMapping("/branches")
    public List<Branches> getAllBranches() {
        log.debug("REST request to get all Branches");
        return branchesService.findAll();
    }

    /**
     * {@code GET  /branches/:id} : get the "id" branches.
     *
     * @param id the id of the branches to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branches, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branches/{id}")
    public ResponseEntity<Branches> getBranches(@PathVariable Long id) {
        log.debug("REST request to get Branches : {}", id);
        Optional<Branches> branches = branchesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branches);
    }

    /**
     * {@code DELETE  /branches/:id} : delete the "id" branches.
     *
     * @param id the id of the branches to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branches/{id}")
    public ResponseEntity<Void> deleteBranches(@PathVariable Long id) {
        log.debug("REST request to delete Branches : {}", id);
        branchesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
