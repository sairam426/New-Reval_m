package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.SystemAccess;
import com.jhipster.reval.ui.repository.SystemAccessRepository;
import com.jhipster.reval.ui.service.SystemAccessService;
import com.jhipster.reval.ui.service.dto.SystemAccessCriteria;
import com.jhipster.reval.ui.service.SystemAccessQueryService;

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
 * Integration tests for the {@link SystemAccessResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SystemAccessResourceIT {

    private static final String DEFAULT_SAC_ACCESS_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SAC_ACCESS_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_SAC_ACCESS_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_SAC_ACCESS_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_SAC_ACCESS_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_SAC_ACCESS_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SAC_ALLOWED_IND = false;
    private static final Boolean UPDATED_SAC_ALLOWED_IND = true;

    @Autowired
    private SystemAccessRepository systemAccessRepository;

    @Autowired
    private SystemAccessService systemAccessService;

    @Autowired
    private SystemAccessQueryService systemAccessQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSystemAccessMockMvc;

    private SystemAccess systemAccess;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemAccess createEntity(EntityManager em) {
        SystemAccess systemAccess = new SystemAccess()
            .sacAccessKey(DEFAULT_SAC_ACCESS_KEY)
            .sacAccessTypeCd(DEFAULT_SAC_ACCESS_TYPE_CD)
            .sacAccessValue(DEFAULT_SAC_ACCESS_VALUE)
            .sacAllowedInd(DEFAULT_SAC_ALLOWED_IND);
        return systemAccess;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemAccess createUpdatedEntity(EntityManager em) {
        SystemAccess systemAccess = new SystemAccess()
            .sacAccessKey(UPDATED_SAC_ACCESS_KEY)
            .sacAccessTypeCd(UPDATED_SAC_ACCESS_TYPE_CD)
            .sacAccessValue(UPDATED_SAC_ACCESS_VALUE)
            .sacAllowedInd(UPDATED_SAC_ALLOWED_IND);
        return systemAccess;
    }

    @BeforeEach
    public void initTest() {
        systemAccess = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemAccess() throws Exception {
        int databaseSizeBeforeCreate = systemAccessRepository.findAll().size();

        // Create the SystemAccess
        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isCreated());

        // Validate the SystemAccess in the database
        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeCreate + 1);
        SystemAccess testSystemAccess = systemAccessList.get(systemAccessList.size() - 1);
        assertThat(testSystemAccess.getSacAccessKey()).isEqualTo(DEFAULT_SAC_ACCESS_KEY);
        assertThat(testSystemAccess.getSacAccessTypeCd()).isEqualTo(DEFAULT_SAC_ACCESS_TYPE_CD);
        assertThat(testSystemAccess.getSacAccessValue()).isEqualTo(DEFAULT_SAC_ACCESS_VALUE);
        assertThat(testSystemAccess.isSacAllowedInd()).isEqualTo(DEFAULT_SAC_ALLOWED_IND);
    }

    @Test
    @Transactional
    public void createSystemAccessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemAccessRepository.findAll().size();

        // Create the SystemAccess with an existing ID
        systemAccess.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        // Validate the SystemAccess in the database
        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSacAccessKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemAccessRepository.findAll().size();
        // set the field null
        systemAccess.setSacAccessKey(null);

        // Create the SystemAccess, which fails.

        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSacAccessTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemAccessRepository.findAll().size();
        // set the field null
        systemAccess.setSacAccessTypeCd(null);

        // Create the SystemAccess, which fails.

        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSacAccessValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemAccessRepository.findAll().size();
        // set the field null
        systemAccess.setSacAccessValue(null);

        // Create the SystemAccess, which fails.

        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSacAllowedIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemAccessRepository.findAll().size();
        // set the field null
        systemAccess.setSacAllowedInd(null);

        // Create the SystemAccess, which fails.

        restSystemAccessMockMvc.perform(post("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemAccesses() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList
        restSystemAccessMockMvc.perform(get("/api/system-accesses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemAccess.getId().intValue())))
            .andExpect(jsonPath("$.[*].sacAccessKey").value(hasItem(DEFAULT_SAC_ACCESS_KEY)))
            .andExpect(jsonPath("$.[*].sacAccessTypeCd").value(hasItem(DEFAULT_SAC_ACCESS_TYPE_CD)))
            .andExpect(jsonPath("$.[*].sacAccessValue").value(hasItem(DEFAULT_SAC_ACCESS_VALUE)))
            .andExpect(jsonPath("$.[*].sacAllowedInd").value(hasItem(DEFAULT_SAC_ALLOWED_IND.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSystemAccess() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get the systemAccess
        restSystemAccessMockMvc.perform(get("/api/system-accesses/{id}", systemAccess.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(systemAccess.getId().intValue()))
            .andExpect(jsonPath("$.sacAccessKey").value(DEFAULT_SAC_ACCESS_KEY))
            .andExpect(jsonPath("$.sacAccessTypeCd").value(DEFAULT_SAC_ACCESS_TYPE_CD))
            .andExpect(jsonPath("$.sacAccessValue").value(DEFAULT_SAC_ACCESS_VALUE))
            .andExpect(jsonPath("$.sacAllowedInd").value(DEFAULT_SAC_ALLOWED_IND.booleanValue()));
    }


    @Test
    @Transactional
    public void getSystemAccessesByIdFiltering() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        Long id = systemAccess.getId();

        defaultSystemAccessShouldBeFound("id.equals=" + id);
        defaultSystemAccessShouldNotBeFound("id.notEquals=" + id);

        defaultSystemAccessShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSystemAccessShouldNotBeFound("id.greaterThan=" + id);

        defaultSystemAccessShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSystemAccessShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey equals to DEFAULT_SAC_ACCESS_KEY
        defaultSystemAccessShouldBeFound("sacAccessKey.equals=" + DEFAULT_SAC_ACCESS_KEY);

        // Get all the systemAccessList where sacAccessKey equals to UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldNotBeFound("sacAccessKey.equals=" + UPDATED_SAC_ACCESS_KEY);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey not equals to DEFAULT_SAC_ACCESS_KEY
        defaultSystemAccessShouldNotBeFound("sacAccessKey.notEquals=" + DEFAULT_SAC_ACCESS_KEY);

        // Get all the systemAccessList where sacAccessKey not equals to UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldBeFound("sacAccessKey.notEquals=" + UPDATED_SAC_ACCESS_KEY);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyIsInShouldWork() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey in DEFAULT_SAC_ACCESS_KEY or UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldBeFound("sacAccessKey.in=" + DEFAULT_SAC_ACCESS_KEY + "," + UPDATED_SAC_ACCESS_KEY);

        // Get all the systemAccessList where sacAccessKey equals to UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldNotBeFound("sacAccessKey.in=" + UPDATED_SAC_ACCESS_KEY);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey is not null
        defaultSystemAccessShouldBeFound("sacAccessKey.specified=true");

        // Get all the systemAccessList where sacAccessKey is null
        defaultSystemAccessShouldNotBeFound("sacAccessKey.specified=false");
    }
                @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey contains DEFAULT_SAC_ACCESS_KEY
        defaultSystemAccessShouldBeFound("sacAccessKey.contains=" + DEFAULT_SAC_ACCESS_KEY);

        // Get all the systemAccessList where sacAccessKey contains UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldNotBeFound("sacAccessKey.contains=" + UPDATED_SAC_ACCESS_KEY);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessKeyNotContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessKey does not contain DEFAULT_SAC_ACCESS_KEY
        defaultSystemAccessShouldNotBeFound("sacAccessKey.doesNotContain=" + DEFAULT_SAC_ACCESS_KEY);

        // Get all the systemAccessList where sacAccessKey does not contain UPDATED_SAC_ACCESS_KEY
        defaultSystemAccessShouldBeFound("sacAccessKey.doesNotContain=" + UPDATED_SAC_ACCESS_KEY);
    }


    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd equals to DEFAULT_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.equals=" + DEFAULT_SAC_ACCESS_TYPE_CD);

        // Get all the systemAccessList where sacAccessTypeCd equals to UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.equals=" + UPDATED_SAC_ACCESS_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd not equals to DEFAULT_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.notEquals=" + DEFAULT_SAC_ACCESS_TYPE_CD);

        // Get all the systemAccessList where sacAccessTypeCd not equals to UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.notEquals=" + UPDATED_SAC_ACCESS_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd in DEFAULT_SAC_ACCESS_TYPE_CD or UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.in=" + DEFAULT_SAC_ACCESS_TYPE_CD + "," + UPDATED_SAC_ACCESS_TYPE_CD);

        // Get all the systemAccessList where sacAccessTypeCd equals to UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.in=" + UPDATED_SAC_ACCESS_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd is not null
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.specified=true");

        // Get all the systemAccessList where sacAccessTypeCd is null
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd contains DEFAULT_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.contains=" + DEFAULT_SAC_ACCESS_TYPE_CD);

        // Get all the systemAccessList where sacAccessTypeCd contains UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.contains=" + UPDATED_SAC_ACCESS_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessTypeCd does not contain DEFAULT_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldNotBeFound("sacAccessTypeCd.doesNotContain=" + DEFAULT_SAC_ACCESS_TYPE_CD);

        // Get all the systemAccessList where sacAccessTypeCd does not contain UPDATED_SAC_ACCESS_TYPE_CD
        defaultSystemAccessShouldBeFound("sacAccessTypeCd.doesNotContain=" + UPDATED_SAC_ACCESS_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueIsEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue equals to DEFAULT_SAC_ACCESS_VALUE
        defaultSystemAccessShouldBeFound("sacAccessValue.equals=" + DEFAULT_SAC_ACCESS_VALUE);

        // Get all the systemAccessList where sacAccessValue equals to UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldNotBeFound("sacAccessValue.equals=" + UPDATED_SAC_ACCESS_VALUE);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue not equals to DEFAULT_SAC_ACCESS_VALUE
        defaultSystemAccessShouldNotBeFound("sacAccessValue.notEquals=" + DEFAULT_SAC_ACCESS_VALUE);

        // Get all the systemAccessList where sacAccessValue not equals to UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldBeFound("sacAccessValue.notEquals=" + UPDATED_SAC_ACCESS_VALUE);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueIsInShouldWork() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue in DEFAULT_SAC_ACCESS_VALUE or UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldBeFound("sacAccessValue.in=" + DEFAULT_SAC_ACCESS_VALUE + "," + UPDATED_SAC_ACCESS_VALUE);

        // Get all the systemAccessList where sacAccessValue equals to UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldNotBeFound("sacAccessValue.in=" + UPDATED_SAC_ACCESS_VALUE);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue is not null
        defaultSystemAccessShouldBeFound("sacAccessValue.specified=true");

        // Get all the systemAccessList where sacAccessValue is null
        defaultSystemAccessShouldNotBeFound("sacAccessValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue contains DEFAULT_SAC_ACCESS_VALUE
        defaultSystemAccessShouldBeFound("sacAccessValue.contains=" + DEFAULT_SAC_ACCESS_VALUE);

        // Get all the systemAccessList where sacAccessValue contains UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldNotBeFound("sacAccessValue.contains=" + UPDATED_SAC_ACCESS_VALUE);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAccessValueNotContainsSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAccessValue does not contain DEFAULT_SAC_ACCESS_VALUE
        defaultSystemAccessShouldNotBeFound("sacAccessValue.doesNotContain=" + DEFAULT_SAC_ACCESS_VALUE);

        // Get all the systemAccessList where sacAccessValue does not contain UPDATED_SAC_ACCESS_VALUE
        defaultSystemAccessShouldBeFound("sacAccessValue.doesNotContain=" + UPDATED_SAC_ACCESS_VALUE);
    }


    @Test
    @Transactional
    public void getAllSystemAccessesBySacAllowedIndIsEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAllowedInd equals to DEFAULT_SAC_ALLOWED_IND
        defaultSystemAccessShouldBeFound("sacAllowedInd.equals=" + DEFAULT_SAC_ALLOWED_IND);

        // Get all the systemAccessList where sacAllowedInd equals to UPDATED_SAC_ALLOWED_IND
        defaultSystemAccessShouldNotBeFound("sacAllowedInd.equals=" + UPDATED_SAC_ALLOWED_IND);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAllowedIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAllowedInd not equals to DEFAULT_SAC_ALLOWED_IND
        defaultSystemAccessShouldNotBeFound("sacAllowedInd.notEquals=" + DEFAULT_SAC_ALLOWED_IND);

        // Get all the systemAccessList where sacAllowedInd not equals to UPDATED_SAC_ALLOWED_IND
        defaultSystemAccessShouldBeFound("sacAllowedInd.notEquals=" + UPDATED_SAC_ALLOWED_IND);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAllowedIndIsInShouldWork() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAllowedInd in DEFAULT_SAC_ALLOWED_IND or UPDATED_SAC_ALLOWED_IND
        defaultSystemAccessShouldBeFound("sacAllowedInd.in=" + DEFAULT_SAC_ALLOWED_IND + "," + UPDATED_SAC_ALLOWED_IND);

        // Get all the systemAccessList where sacAllowedInd equals to UPDATED_SAC_ALLOWED_IND
        defaultSystemAccessShouldNotBeFound("sacAllowedInd.in=" + UPDATED_SAC_ALLOWED_IND);
    }

    @Test
    @Transactional
    public void getAllSystemAccessesBySacAllowedIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        systemAccessRepository.saveAndFlush(systemAccess);

        // Get all the systemAccessList where sacAllowedInd is not null
        defaultSystemAccessShouldBeFound("sacAllowedInd.specified=true");

        // Get all the systemAccessList where sacAllowedInd is null
        defaultSystemAccessShouldNotBeFound("sacAllowedInd.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSystemAccessShouldBeFound(String filter) throws Exception {
        restSystemAccessMockMvc.perform(get("/api/system-accesses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemAccess.getId().intValue())))
            .andExpect(jsonPath("$.[*].sacAccessKey").value(hasItem(DEFAULT_SAC_ACCESS_KEY)))
            .andExpect(jsonPath("$.[*].sacAccessTypeCd").value(hasItem(DEFAULT_SAC_ACCESS_TYPE_CD)))
            .andExpect(jsonPath("$.[*].sacAccessValue").value(hasItem(DEFAULT_SAC_ACCESS_VALUE)))
            .andExpect(jsonPath("$.[*].sacAllowedInd").value(hasItem(DEFAULT_SAC_ALLOWED_IND.booleanValue())));

        // Check, that the count call also returns 1
        restSystemAccessMockMvc.perform(get("/api/system-accesses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSystemAccessShouldNotBeFound(String filter) throws Exception {
        restSystemAccessMockMvc.perform(get("/api/system-accesses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSystemAccessMockMvc.perform(get("/api/system-accesses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSystemAccess() throws Exception {
        // Get the systemAccess
        restSystemAccessMockMvc.perform(get("/api/system-accesses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemAccess() throws Exception {
        // Initialize the database
        systemAccessService.save(systemAccess);

        int databaseSizeBeforeUpdate = systemAccessRepository.findAll().size();

        // Update the systemAccess
        SystemAccess updatedSystemAccess = systemAccessRepository.findById(systemAccess.getId()).get();
        // Disconnect from session so that the updates on updatedSystemAccess are not directly saved in db
        em.detach(updatedSystemAccess);
        updatedSystemAccess
            .sacAccessKey(UPDATED_SAC_ACCESS_KEY)
            .sacAccessTypeCd(UPDATED_SAC_ACCESS_TYPE_CD)
            .sacAccessValue(UPDATED_SAC_ACCESS_VALUE)
            .sacAllowedInd(UPDATED_SAC_ALLOWED_IND);

        restSystemAccessMockMvc.perform(put("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSystemAccess)))
            .andExpect(status().isOk());

        // Validate the SystemAccess in the database
        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeUpdate);
        SystemAccess testSystemAccess = systemAccessList.get(systemAccessList.size() - 1);
        assertThat(testSystemAccess.getSacAccessKey()).isEqualTo(UPDATED_SAC_ACCESS_KEY);
        assertThat(testSystemAccess.getSacAccessTypeCd()).isEqualTo(UPDATED_SAC_ACCESS_TYPE_CD);
        assertThat(testSystemAccess.getSacAccessValue()).isEqualTo(UPDATED_SAC_ACCESS_VALUE);
        assertThat(testSystemAccess.isSacAllowedInd()).isEqualTo(UPDATED_SAC_ALLOWED_IND);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemAccess() throws Exception {
        int databaseSizeBeforeUpdate = systemAccessRepository.findAll().size();

        // Create the SystemAccess

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemAccessMockMvc.perform(put("/api/system-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemAccess)))
            .andExpect(status().isBadRequest());

        // Validate the SystemAccess in the database
        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemAccess() throws Exception {
        // Initialize the database
        systemAccessService.save(systemAccess);

        int databaseSizeBeforeDelete = systemAccessRepository.findAll().size();

        // Delete the systemAccess
        restSystemAccessMockMvc.perform(delete("/api/system-accesses/{id}", systemAccess.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SystemAccess> systemAccessList = systemAccessRepository.findAll();
        assertThat(systemAccessList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
