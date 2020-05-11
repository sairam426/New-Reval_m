package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Organizations;
import com.jhipster.reval.ui.service.OrganizationsService;
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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Organizations}.
 */
@RestController
@RequestMapping("/api")
public class OrganizationsResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationsResource.class);

    private static final String ENTITY_NAME = "organizations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganizationsService organizationsService;

    public OrganizationsResource(OrganizationsService organizationsService) {
        this.organizationsService = organizationsService;
    }

    /**
     * {@code POST  /organizations} : Create a new organizations.
     *
     * @param organizations the organizations to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organizations, or with status {@code 400 (Bad Request)} if the organizations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organizations")
    public ResponseEntity<Organizations> createOrganizations(@Valid @RequestBody Organizations organizations) throws URISyntaxException {
        log.debug("REST request to save Organizations : {}", organizations);
        if (organizations.getId() != null) {
            throw new BadRequestAlertException("A new organizations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Organizations result = organizationsService.save(organizations);
        return ResponseEntity.created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organizations} : Updates an existing organizations.
     *
     * @param organizations the organizations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organizations,
     * or with status {@code 400 (Bad Request)} if the organizations is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organizations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organizations")
    public ResponseEntity<Organizations> updateOrganizations(@Valid @RequestBody Organizations organizations) throws URISyntaxException {
        log.debug("REST request to update Organizations : {}", organizations);
        if (organizations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Organizations result = organizationsService.save(organizations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organizations.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organizations} : get all the organizations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organizations in body.
     */
    @GetMapping("/organizations")
    public List<Organizations> getAllOrganizations() {
        log.debug("REST request to get all Organizations");
        return organizationsService.findAll();
    }

    /**
     * {@code GET  /organizations/:id} : get the "id" organizations.
     *
     * @param id the id of the organizations to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizations, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organizations/{id}")
    public ResponseEntity<Organizations> getOrganizations(@PathVariable Long id) {
        log.debug("REST request to get Organizations : {}", id);
        Optional<Organizations> organizations = organizationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizations);
    }

    /**
     * {@code DELETE  /organizations/:id} : delete the "id" organizations.
     *
     * @param id the id of the organizations to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<Void> deleteOrganizations(@PathVariable Long id) {
        log.debug("REST request to delete Organizations : {}", id);
        organizationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
