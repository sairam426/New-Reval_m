package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.domain.Companies;
import com.jhipster.reval.ui.service.CompaniesService;
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
 * REST controller for managing {@link com.jhipster.reval.ui.domain.Companies}.
 */
@RestController
@RequestMapping("/api")
public class CompaniesResource {

    private final Logger log = LoggerFactory.getLogger(CompaniesResource.class);

    private static final String ENTITY_NAME = "companies";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompaniesService companiesService;

    public CompaniesResource(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    /**
     * {@code POST  /companies} : Create a new companies.
     *
     * @param companies the companies to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new companies, or with status {@code 400 (Bad Request)} if the companies has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/companies")
    public ResponseEntity<Companies> createCompanies(@Valid @RequestBody Companies companies) throws URISyntaxException {
        log.debug("REST request to save Companies : {}", companies);
        if (companies.getId() != null) {
            throw new BadRequestAlertException("A new companies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Companies result = companiesService.save(companies);
        return ResponseEntity.created(new URI("/api/companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /companies} : Updates an existing companies.
     *
     * @param companies the companies to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companies,
     * or with status {@code 400 (Bad Request)} if the companies is not valid,
     * or with status {@code 500 (Internal Server Error)} if the companies couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/companies")
    public ResponseEntity<Companies> updateCompanies(@Valid @RequestBody Companies companies) throws URISyntaxException {
        log.debug("REST request to update Companies : {}", companies);
        if (companies.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Companies result = companiesService.save(companies);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, companies.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /companies} : get all the companies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of companies in body.
     */
    @GetMapping("/companies")
    public List<Companies> getAllCompanies() {
        log.debug("REST request to get all Companies");
        return companiesService.findAll();
    }

    /**
     * {@code GET  /companies/:id} : get the "id" companies.
     *
     * @param id the id of the companies to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the companies, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/companies/{id}")
    public ResponseEntity<Companies> getCompanies(@PathVariable Long id) {
        log.debug("REST request to get Companies : {}", id);
        Optional<Companies> companies = companiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(companies);
    }

    /**
     * {@code DELETE  /companies/:id} : delete the "id" companies.
     *
     * @param id the id of the companies to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompanies(@PathVariable Long id) {
        log.debug("REST request to delete Companies : {}", id);
        companiesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
