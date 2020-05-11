package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Companies;
import com.jhipster.reval.ui.repository.CompaniesRepository;
import com.jhipster.reval.ui.service.CompaniesService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CompaniesResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CompaniesResourceIT {

    private static final String DEFAULT_CMP_NBR = "AAAAAAAAAA";
    private static final String UPDATED_CMP_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CMP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_SERVICE_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_CMP_SERVICE_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CMP_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_CMP_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_CMP_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_CMP_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_CMP_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CMP_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_CMP_STATE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_CMP_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_CMP_ZIP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CMP_ENABLED_IND = false;
    private static final Boolean UPDATED_CMP_ENABLED_IND = true;

    @Autowired
    private CompaniesRepository companiesRepository;

    @Autowired
    private CompaniesService companiesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompaniesMockMvc;

    private Companies companies;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Companies createEntity(EntityManager em) {
        Companies companies = new Companies()
            .cmpNbr(DEFAULT_CMP_NBR)
            .cmpName(DEFAULT_CMP_NAME)
            .cmpServiceTypeCd(DEFAULT_CMP_SERVICE_TYPE_CD)
            .cmpShortName(DEFAULT_CMP_SHORT_NAME)
            .cmpStatusCd(DEFAULT_CMP_STATUS_CD)
            .cmpTypeCd(DEFAULT_CMP_TYPE_CD)
            .cmpAddress1(DEFAULT_CMP_ADDRESS_1)
            .cmpAddress2(DEFAULT_CMP_ADDRESS_2)
            .cmpCity(DEFAULT_CMP_CITY)
            .cmpStateCd(DEFAULT_CMP_STATE_CD)
            .cmpZip(DEFAULT_CMP_ZIP)
            .cmpEnabledInd(DEFAULT_CMP_ENABLED_IND);
        return companies;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Companies createUpdatedEntity(EntityManager em) {
        Companies companies = new Companies()
            .cmpNbr(UPDATED_CMP_NBR)
            .cmpName(UPDATED_CMP_NAME)
            .cmpServiceTypeCd(UPDATED_CMP_SERVICE_TYPE_CD)
            .cmpShortName(UPDATED_CMP_SHORT_NAME)
            .cmpStatusCd(UPDATED_CMP_STATUS_CD)
            .cmpTypeCd(UPDATED_CMP_TYPE_CD)
            .cmpAddress1(UPDATED_CMP_ADDRESS_1)
            .cmpAddress2(UPDATED_CMP_ADDRESS_2)
            .cmpCity(UPDATED_CMP_CITY)
            .cmpStateCd(UPDATED_CMP_STATE_CD)
            .cmpZip(UPDATED_CMP_ZIP)
            .cmpEnabledInd(UPDATED_CMP_ENABLED_IND);
        return companies;
    }

    @BeforeEach
    public void initTest() {
        companies = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanies() throws Exception {
        int databaseSizeBeforeCreate = companiesRepository.findAll().size();

        // Create the Companies
        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isCreated());

        // Validate the Companies in the database
        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeCreate + 1);
        Companies testCompanies = companiesList.get(companiesList.size() - 1);
        assertThat(testCompanies.getCmpNbr()).isEqualTo(DEFAULT_CMP_NBR);
        assertThat(testCompanies.getCmpName()).isEqualTo(DEFAULT_CMP_NAME);
        assertThat(testCompanies.getCmpServiceTypeCd()).isEqualTo(DEFAULT_CMP_SERVICE_TYPE_CD);
        assertThat(testCompanies.getCmpShortName()).isEqualTo(DEFAULT_CMP_SHORT_NAME);
        assertThat(testCompanies.getCmpStatusCd()).isEqualTo(DEFAULT_CMP_STATUS_CD);
        assertThat(testCompanies.getCmpTypeCd()).isEqualTo(DEFAULT_CMP_TYPE_CD);
        assertThat(testCompanies.getCmpAddress1()).isEqualTo(DEFAULT_CMP_ADDRESS_1);
        assertThat(testCompanies.getCmpAddress2()).isEqualTo(DEFAULT_CMP_ADDRESS_2);
        assertThat(testCompanies.getCmpCity()).isEqualTo(DEFAULT_CMP_CITY);
        assertThat(testCompanies.getCmpStateCd()).isEqualTo(DEFAULT_CMP_STATE_CD);
        assertThat(testCompanies.getCmpZip()).isEqualTo(DEFAULT_CMP_ZIP);
        assertThat(testCompanies.isCmpEnabledInd()).isEqualTo(DEFAULT_CMP_ENABLED_IND);
    }

    @Test
    @Transactional
    public void createCompaniesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companiesRepository.findAll().size();

        // Create the Companies with an existing ID
        companies.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        // Validate the Companies in the database
        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCmpNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpNbr(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpName(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpShortName(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpStatusCd(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpTypeCd(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpAddress1IsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpAddress1(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpAddress2IsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpAddress2(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpCity(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpStateCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpStateCd(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpZipIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpZip(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCmpEnabledIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = companiesRepository.findAll().size();
        // set the field null
        companies.setCmpEnabledInd(null);

        // Create the Companies, which fails.

        restCompaniesMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompanies() throws Exception {
        // Initialize the database
        companiesRepository.saveAndFlush(companies);

        // Get all the companiesList
        restCompaniesMockMvc.perform(get("/api/companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companies.getId().intValue())))
            .andExpect(jsonPath("$.[*].cmpNbr").value(hasItem(DEFAULT_CMP_NBR)))
            .andExpect(jsonPath("$.[*].cmpName").value(hasItem(DEFAULT_CMP_NAME)))
            .andExpect(jsonPath("$.[*].cmpServiceTypeCd").value(hasItem(DEFAULT_CMP_SERVICE_TYPE_CD)))
            .andExpect(jsonPath("$.[*].cmpShortName").value(hasItem(DEFAULT_CMP_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].cmpStatusCd").value(hasItem(DEFAULT_CMP_STATUS_CD)))
            .andExpect(jsonPath("$.[*].cmpTypeCd").value(hasItem(DEFAULT_CMP_TYPE_CD)))
            .andExpect(jsonPath("$.[*].cmpAddress1").value(hasItem(DEFAULT_CMP_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].cmpAddress2").value(hasItem(DEFAULT_CMP_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].cmpCity").value(hasItem(DEFAULT_CMP_CITY)))
            .andExpect(jsonPath("$.[*].cmpStateCd").value(hasItem(DEFAULT_CMP_STATE_CD)))
            .andExpect(jsonPath("$.[*].cmpZip").value(hasItem(DEFAULT_CMP_ZIP)))
            .andExpect(jsonPath("$.[*].cmpEnabledInd").value(hasItem(DEFAULT_CMP_ENABLED_IND.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCompanies() throws Exception {
        // Initialize the database
        companiesRepository.saveAndFlush(companies);

        // Get the companies
        restCompaniesMockMvc.perform(get("/api/companies/{id}", companies.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(companies.getId().intValue()))
            .andExpect(jsonPath("$.cmpNbr").value(DEFAULT_CMP_NBR))
            .andExpect(jsonPath("$.cmpName").value(DEFAULT_CMP_NAME))
            .andExpect(jsonPath("$.cmpServiceTypeCd").value(DEFAULT_CMP_SERVICE_TYPE_CD))
            .andExpect(jsonPath("$.cmpShortName").value(DEFAULT_CMP_SHORT_NAME))
            .andExpect(jsonPath("$.cmpStatusCd").value(DEFAULT_CMP_STATUS_CD))
            .andExpect(jsonPath("$.cmpTypeCd").value(DEFAULT_CMP_TYPE_CD))
            .andExpect(jsonPath("$.cmpAddress1").value(DEFAULT_CMP_ADDRESS_1))
            .andExpect(jsonPath("$.cmpAddress2").value(DEFAULT_CMP_ADDRESS_2))
            .andExpect(jsonPath("$.cmpCity").value(DEFAULT_CMP_CITY))
            .andExpect(jsonPath("$.cmpStateCd").value(DEFAULT_CMP_STATE_CD))
            .andExpect(jsonPath("$.cmpZip").value(DEFAULT_CMP_ZIP))
            .andExpect(jsonPath("$.cmpEnabledInd").value(DEFAULT_CMP_ENABLED_IND.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCompanies() throws Exception {
        // Get the companies
        restCompaniesMockMvc.perform(get("/api/companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanies() throws Exception {
        // Initialize the database
        companiesService.save(companies);

        int databaseSizeBeforeUpdate = companiesRepository.findAll().size();

        // Update the companies
        Companies updatedCompanies = companiesRepository.findById(companies.getId()).get();
        // Disconnect from session so that the updates on updatedCompanies are not directly saved in db
        em.detach(updatedCompanies);
        updatedCompanies
            .cmpNbr(UPDATED_CMP_NBR)
            .cmpName(UPDATED_CMP_NAME)
            .cmpServiceTypeCd(UPDATED_CMP_SERVICE_TYPE_CD)
            .cmpShortName(UPDATED_CMP_SHORT_NAME)
            .cmpStatusCd(UPDATED_CMP_STATUS_CD)
            .cmpTypeCd(UPDATED_CMP_TYPE_CD)
            .cmpAddress1(UPDATED_CMP_ADDRESS_1)
            .cmpAddress2(UPDATED_CMP_ADDRESS_2)
            .cmpCity(UPDATED_CMP_CITY)
            .cmpStateCd(UPDATED_CMP_STATE_CD)
            .cmpZip(UPDATED_CMP_ZIP)
            .cmpEnabledInd(UPDATED_CMP_ENABLED_IND);

        restCompaniesMockMvc.perform(put("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompanies)))
            .andExpect(status().isOk());

        // Validate the Companies in the database
        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeUpdate);
        Companies testCompanies = companiesList.get(companiesList.size() - 1);
        assertThat(testCompanies.getCmpNbr()).isEqualTo(UPDATED_CMP_NBR);
        assertThat(testCompanies.getCmpName()).isEqualTo(UPDATED_CMP_NAME);
        assertThat(testCompanies.getCmpServiceTypeCd()).isEqualTo(UPDATED_CMP_SERVICE_TYPE_CD);
        assertThat(testCompanies.getCmpShortName()).isEqualTo(UPDATED_CMP_SHORT_NAME);
        assertThat(testCompanies.getCmpStatusCd()).isEqualTo(UPDATED_CMP_STATUS_CD);
        assertThat(testCompanies.getCmpTypeCd()).isEqualTo(UPDATED_CMP_TYPE_CD);
        assertThat(testCompanies.getCmpAddress1()).isEqualTo(UPDATED_CMP_ADDRESS_1);
        assertThat(testCompanies.getCmpAddress2()).isEqualTo(UPDATED_CMP_ADDRESS_2);
        assertThat(testCompanies.getCmpCity()).isEqualTo(UPDATED_CMP_CITY);
        assertThat(testCompanies.getCmpStateCd()).isEqualTo(UPDATED_CMP_STATE_CD);
        assertThat(testCompanies.getCmpZip()).isEqualTo(UPDATED_CMP_ZIP);
        assertThat(testCompanies.isCmpEnabledInd()).isEqualTo(UPDATED_CMP_ENABLED_IND);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanies() throws Exception {
        int databaseSizeBeforeUpdate = companiesRepository.findAll().size();

        // Create the Companies

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompaniesMockMvc.perform(put("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companies)))
            .andExpect(status().isBadRequest());

        // Validate the Companies in the database
        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompanies() throws Exception {
        // Initialize the database
        companiesService.save(companies);

        int databaseSizeBeforeDelete = companiesRepository.findAll().size();

        // Delete the companies
        restCompaniesMockMvc.perform(delete("/api/companies/{id}", companies.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Companies> companiesList = companiesRepository.findAll();
        assertThat(companiesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
