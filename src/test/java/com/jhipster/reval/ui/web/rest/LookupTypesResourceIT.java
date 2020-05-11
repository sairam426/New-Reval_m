package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.LookupTypes;
import com.jhipster.reval.ui.repository.LookupTypesRepository;
import com.jhipster.reval.ui.service.LookupTypesService;
import com.jhipster.reval.ui.service.dto.LookupTypesCriteria;
import com.jhipster.reval.ui.service.LookupTypesQueryService;

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
 * Integration tests for the {@link LookupTypesResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LookupTypesResourceIT {

    private static final String DEFAULT_LKT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LKT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LKT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_LKT_DESC = "BBBBBBBBBB";

    @Autowired
    private LookupTypesRepository lookupTypesRepository;

    @Autowired
    private LookupTypesService lookupTypesService;

    @Autowired
    private LookupTypesQueryService lookupTypesQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLookupTypesMockMvc;

    private LookupTypes lookupTypes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LookupTypes createEntity(EntityManager em) {
        LookupTypes lookupTypes = new LookupTypes()
            .lktType(DEFAULT_LKT_TYPE)
            .lktDesc(DEFAULT_LKT_DESC);
        return lookupTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LookupTypes createUpdatedEntity(EntityManager em) {
        LookupTypes lookupTypes = new LookupTypes()
            .lktType(UPDATED_LKT_TYPE)
            .lktDesc(UPDATED_LKT_DESC);
        return lookupTypes;
    }

    @BeforeEach
    public void initTest() {
        lookupTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createLookupTypes() throws Exception {
        int databaseSizeBeforeCreate = lookupTypesRepository.findAll().size();

        // Create the LookupTypes
        restLookupTypesMockMvc.perform(post("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookupTypes)))
            .andExpect(status().isCreated());

        // Validate the LookupTypes in the database
        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeCreate + 1);
        LookupTypes testLookupTypes = lookupTypesList.get(lookupTypesList.size() - 1);
        assertThat(testLookupTypes.getLktType()).isEqualTo(DEFAULT_LKT_TYPE);
        assertThat(testLookupTypes.getLktDesc()).isEqualTo(DEFAULT_LKT_DESC);
    }

    @Test
    @Transactional
    public void createLookupTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lookupTypesRepository.findAll().size();

        // Create the LookupTypes with an existing ID
        lookupTypes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLookupTypesMockMvc.perform(post("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookupTypes)))
            .andExpect(status().isBadRequest());

        // Validate the LookupTypes in the database
        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLktTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupTypesRepository.findAll().size();
        // set the field null
        lookupTypes.setLktType(null);

        // Create the LookupTypes, which fails.

        restLookupTypesMockMvc.perform(post("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookupTypes)))
            .andExpect(status().isBadRequest());

        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLktDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupTypesRepository.findAll().size();
        // set the field null
        lookupTypes.setLktDesc(null);

        // Create the LookupTypes, which fails.

        restLookupTypesMockMvc.perform(post("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookupTypes)))
            .andExpect(status().isBadRequest());

        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLookupTypes() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList
        restLookupTypesMockMvc.perform(get("/api/lookup-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookupTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].lktType").value(hasItem(DEFAULT_LKT_TYPE)))
            .andExpect(jsonPath("$.[*].lktDesc").value(hasItem(DEFAULT_LKT_DESC)));
    }
    
    @Test
    @Transactional
    public void getLookupTypes() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get the lookupTypes
        restLookupTypesMockMvc.perform(get("/api/lookup-types/{id}", lookupTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lookupTypes.getId().intValue()))
            .andExpect(jsonPath("$.lktType").value(DEFAULT_LKT_TYPE))
            .andExpect(jsonPath("$.lktDesc").value(DEFAULT_LKT_DESC));
    }


    @Test
    @Transactional
    public void getLookupTypesByIdFiltering() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        Long id = lookupTypes.getId();

        defaultLookupTypesShouldBeFound("id.equals=" + id);
        defaultLookupTypesShouldNotBeFound("id.notEquals=" + id);

        defaultLookupTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLookupTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultLookupTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLookupTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLookupTypesByLktTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType equals to DEFAULT_LKT_TYPE
        defaultLookupTypesShouldBeFound("lktType.equals=" + DEFAULT_LKT_TYPE);

        // Get all the lookupTypesList where lktType equals to UPDATED_LKT_TYPE
        defaultLookupTypesShouldNotBeFound("lktType.equals=" + UPDATED_LKT_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType not equals to DEFAULT_LKT_TYPE
        defaultLookupTypesShouldNotBeFound("lktType.notEquals=" + DEFAULT_LKT_TYPE);

        // Get all the lookupTypesList where lktType not equals to UPDATED_LKT_TYPE
        defaultLookupTypesShouldBeFound("lktType.notEquals=" + UPDATED_LKT_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktTypeIsInShouldWork() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType in DEFAULT_LKT_TYPE or UPDATED_LKT_TYPE
        defaultLookupTypesShouldBeFound("lktType.in=" + DEFAULT_LKT_TYPE + "," + UPDATED_LKT_TYPE);

        // Get all the lookupTypesList where lktType equals to UPDATED_LKT_TYPE
        defaultLookupTypesShouldNotBeFound("lktType.in=" + UPDATED_LKT_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType is not null
        defaultLookupTypesShouldBeFound("lktType.specified=true");

        // Get all the lookupTypesList where lktType is null
        defaultLookupTypesShouldNotBeFound("lktType.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupTypesByLktTypeContainsSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType contains DEFAULT_LKT_TYPE
        defaultLookupTypesShouldBeFound("lktType.contains=" + DEFAULT_LKT_TYPE);

        // Get all the lookupTypesList where lktType contains UPDATED_LKT_TYPE
        defaultLookupTypesShouldNotBeFound("lktType.contains=" + UPDATED_LKT_TYPE);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktTypeNotContainsSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktType does not contain DEFAULT_LKT_TYPE
        defaultLookupTypesShouldNotBeFound("lktType.doesNotContain=" + DEFAULT_LKT_TYPE);

        // Get all the lookupTypesList where lktType does not contain UPDATED_LKT_TYPE
        defaultLookupTypesShouldBeFound("lktType.doesNotContain=" + UPDATED_LKT_TYPE);
    }


    @Test
    @Transactional
    public void getAllLookupTypesByLktDescIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc equals to DEFAULT_LKT_DESC
        defaultLookupTypesShouldBeFound("lktDesc.equals=" + DEFAULT_LKT_DESC);

        // Get all the lookupTypesList where lktDesc equals to UPDATED_LKT_DESC
        defaultLookupTypesShouldNotBeFound("lktDesc.equals=" + UPDATED_LKT_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktDescIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc not equals to DEFAULT_LKT_DESC
        defaultLookupTypesShouldNotBeFound("lktDesc.notEquals=" + DEFAULT_LKT_DESC);

        // Get all the lookupTypesList where lktDesc not equals to UPDATED_LKT_DESC
        defaultLookupTypesShouldBeFound("lktDesc.notEquals=" + UPDATED_LKT_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktDescIsInShouldWork() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc in DEFAULT_LKT_DESC or UPDATED_LKT_DESC
        defaultLookupTypesShouldBeFound("lktDesc.in=" + DEFAULT_LKT_DESC + "," + UPDATED_LKT_DESC);

        // Get all the lookupTypesList where lktDesc equals to UPDATED_LKT_DESC
        defaultLookupTypesShouldNotBeFound("lktDesc.in=" + UPDATED_LKT_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc is not null
        defaultLookupTypesShouldBeFound("lktDesc.specified=true");

        // Get all the lookupTypesList where lktDesc is null
        defaultLookupTypesShouldNotBeFound("lktDesc.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupTypesByLktDescContainsSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc contains DEFAULT_LKT_DESC
        defaultLookupTypesShouldBeFound("lktDesc.contains=" + DEFAULT_LKT_DESC);

        // Get all the lookupTypesList where lktDesc contains UPDATED_LKT_DESC
        defaultLookupTypesShouldNotBeFound("lktDesc.contains=" + UPDATED_LKT_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupTypesByLktDescNotContainsSomething() throws Exception {
        // Initialize the database
        lookupTypesRepository.saveAndFlush(lookupTypes);

        // Get all the lookupTypesList where lktDesc does not contain DEFAULT_LKT_DESC
        defaultLookupTypesShouldNotBeFound("lktDesc.doesNotContain=" + DEFAULT_LKT_DESC);

        // Get all the lookupTypesList where lktDesc does not contain UPDATED_LKT_DESC
        defaultLookupTypesShouldBeFound("lktDesc.doesNotContain=" + UPDATED_LKT_DESC);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLookupTypesShouldBeFound(String filter) throws Exception {
        restLookupTypesMockMvc.perform(get("/api/lookup-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookupTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].lktType").value(hasItem(DEFAULT_LKT_TYPE)))
            .andExpect(jsonPath("$.[*].lktDesc").value(hasItem(DEFAULT_LKT_DESC)));

        // Check, that the count call also returns 1
        restLookupTypesMockMvc.perform(get("/api/lookup-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLookupTypesShouldNotBeFound(String filter) throws Exception {
        restLookupTypesMockMvc.perform(get("/api/lookup-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLookupTypesMockMvc.perform(get("/api/lookup-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLookupTypes() throws Exception {
        // Get the lookupTypes
        restLookupTypesMockMvc.perform(get("/api/lookup-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLookupTypes() throws Exception {
        // Initialize the database
        lookupTypesService.save(lookupTypes);

        int databaseSizeBeforeUpdate = lookupTypesRepository.findAll().size();

        // Update the lookupTypes
        LookupTypes updatedLookupTypes = lookupTypesRepository.findById(lookupTypes.getId()).get();
        // Disconnect from session so that the updates on updatedLookupTypes are not directly saved in db
        em.detach(updatedLookupTypes);
        updatedLookupTypes
            .lktType(UPDATED_LKT_TYPE)
            .lktDesc(UPDATED_LKT_DESC);

        restLookupTypesMockMvc.perform(put("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLookupTypes)))
            .andExpect(status().isOk());

        // Validate the LookupTypes in the database
        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeUpdate);
        LookupTypes testLookupTypes = lookupTypesList.get(lookupTypesList.size() - 1);
        assertThat(testLookupTypes.getLktType()).isEqualTo(UPDATED_LKT_TYPE);
        assertThat(testLookupTypes.getLktDesc()).isEqualTo(UPDATED_LKT_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingLookupTypes() throws Exception {
        int databaseSizeBeforeUpdate = lookupTypesRepository.findAll().size();

        // Create the LookupTypes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLookupTypesMockMvc.perform(put("/api/lookup-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookupTypes)))
            .andExpect(status().isBadRequest());

        // Validate the LookupTypes in the database
        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLookupTypes() throws Exception {
        // Initialize the database
        lookupTypesService.save(lookupTypes);

        int databaseSizeBeforeDelete = lookupTypesRepository.findAll().size();

        // Delete the lookupTypes
        restLookupTypesMockMvc.perform(delete("/api/lookup-types/{id}", lookupTypes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LookupTypes> lookupTypesList = lookupTypesRepository.findAll();
        assertThat(lookupTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
