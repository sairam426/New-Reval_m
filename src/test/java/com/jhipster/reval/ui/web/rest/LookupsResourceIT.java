package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Lookups;
import com.jhipster.reval.ui.domain.LookupTypes;
import com.jhipster.reval.ui.repository.LookupsRepository;
import com.jhipster.reval.ui.service.LookupsService;
import com.jhipster.reval.ui.service.dto.LookupsCriteria;
import com.jhipster.reval.ui.service.LookupsQueryService;

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
 * Integration tests for the {@link LookupsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LookupsResourceIT {

    private static final String DEFAULT_LKC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LKC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LKC_SUB_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LKC_SUB_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LKC_SORT = "AAAAAAAAAA";
    private static final String UPDATED_LKC_SORT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LKC_ENABLED_IND = false;
    private static final Boolean UPDATED_LKC_ENABLED_IND = true;

    private static final String DEFAULT_LKC_DESC = "AAAAAAAAAA";
    private static final String UPDATED_LKC_DESC = "BBBBBBBBBB";

    @Autowired
    private LookupsRepository lookupsRepository;

    @Autowired
    private LookupsService lookupsService;

    @Autowired
    private LookupsQueryService lookupsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLookupsMockMvc;

    private Lookups lookups;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lookups createEntity(EntityManager em) {
        Lookups lookups = new Lookups()
            .lkcCode(DEFAULT_LKC_CODE)
            .lkcSubCode(DEFAULT_LKC_SUB_CODE)
            .lkcSort(DEFAULT_LKC_SORT)
            .lkcEnabledInd(DEFAULT_LKC_ENABLED_IND)
            .lkcDesc(DEFAULT_LKC_DESC);
        return lookups;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lookups createUpdatedEntity(EntityManager em) {
        Lookups lookups = new Lookups()
            .lkcCode(UPDATED_LKC_CODE)
            .lkcSubCode(UPDATED_LKC_SUB_CODE)
            .lkcSort(UPDATED_LKC_SORT)
            .lkcEnabledInd(UPDATED_LKC_ENABLED_IND)
            .lkcDesc(UPDATED_LKC_DESC);
        return lookups;
    }

    @BeforeEach
    public void initTest() {
        lookups = createEntity(em);
    }

    @Test
    @Transactional
    public void createLookups() throws Exception {
        int databaseSizeBeforeCreate = lookupsRepository.findAll().size();

        // Create the Lookups
        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isCreated());

        // Validate the Lookups in the database
        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeCreate + 1);
        Lookups testLookups = lookupsList.get(lookupsList.size() - 1);
        assertThat(testLookups.getLkcCode()).isEqualTo(DEFAULT_LKC_CODE);
        assertThat(testLookups.getLkcSubCode()).isEqualTo(DEFAULT_LKC_SUB_CODE);
        assertThat(testLookups.getLkcSort()).isEqualTo(DEFAULT_LKC_SORT);
        assertThat(testLookups.isLkcEnabledInd()).isEqualTo(DEFAULT_LKC_ENABLED_IND);
        assertThat(testLookups.getLkcDesc()).isEqualTo(DEFAULT_LKC_DESC);
    }

    @Test
    @Transactional
    public void createLookupsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lookupsRepository.findAll().size();

        // Create the Lookups with an existing ID
        lookups.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        // Validate the Lookups in the database
        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLkcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupsRepository.findAll().size();
        // set the field null
        lookups.setLkcCode(null);

        // Create the Lookups, which fails.

        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLkcSubCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupsRepository.findAll().size();
        // set the field null
        lookups.setLkcSubCode(null);

        // Create the Lookups, which fails.

        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLkcSortIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupsRepository.findAll().size();
        // set the field null
        lookups.setLkcSort(null);

        // Create the Lookups, which fails.

        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLkcEnabledIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = lookupsRepository.findAll().size();
        // set the field null
        lookups.setLkcEnabledInd(null);

        // Create the Lookups, which fails.

        restLookupsMockMvc.perform(post("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLookups() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList
        restLookupsMockMvc.perform(get("/api/lookups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookups.getId().intValue())))
            .andExpect(jsonPath("$.[*].lkcCode").value(hasItem(DEFAULT_LKC_CODE)))
            .andExpect(jsonPath("$.[*].lkcSubCode").value(hasItem(DEFAULT_LKC_SUB_CODE)))
            .andExpect(jsonPath("$.[*].lkcSort").value(hasItem(DEFAULT_LKC_SORT)))
            .andExpect(jsonPath("$.[*].lkcEnabledInd").value(hasItem(DEFAULT_LKC_ENABLED_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].lkcDesc").value(hasItem(DEFAULT_LKC_DESC)));
    }
    
    @Test
    @Transactional
    public void getLookups() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get the lookups
        restLookupsMockMvc.perform(get("/api/lookups/{id}", lookups.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lookups.getId().intValue()))
            .andExpect(jsonPath("$.lkcCode").value(DEFAULT_LKC_CODE))
            .andExpect(jsonPath("$.lkcSubCode").value(DEFAULT_LKC_SUB_CODE))
            .andExpect(jsonPath("$.lkcSort").value(DEFAULT_LKC_SORT))
            .andExpect(jsonPath("$.lkcEnabledInd").value(DEFAULT_LKC_ENABLED_IND.booleanValue()))
            .andExpect(jsonPath("$.lkcDesc").value(DEFAULT_LKC_DESC));
    }


    @Test
    @Transactional
    public void getLookupsByIdFiltering() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        Long id = lookups.getId();

        defaultLookupsShouldBeFound("id.equals=" + id);
        defaultLookupsShouldNotBeFound("id.notEquals=" + id);

        defaultLookupsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLookupsShouldNotBeFound("id.greaterThan=" + id);

        defaultLookupsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLookupsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLookupsByLkcCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode equals to DEFAULT_LKC_CODE
        defaultLookupsShouldBeFound("lkcCode.equals=" + DEFAULT_LKC_CODE);

        // Get all the lookupsList where lkcCode equals to UPDATED_LKC_CODE
        defaultLookupsShouldNotBeFound("lkcCode.equals=" + UPDATED_LKC_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode not equals to DEFAULT_LKC_CODE
        defaultLookupsShouldNotBeFound("lkcCode.notEquals=" + DEFAULT_LKC_CODE);

        // Get all the lookupsList where lkcCode not equals to UPDATED_LKC_CODE
        defaultLookupsShouldBeFound("lkcCode.notEquals=" + UPDATED_LKC_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcCodeIsInShouldWork() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode in DEFAULT_LKC_CODE or UPDATED_LKC_CODE
        defaultLookupsShouldBeFound("lkcCode.in=" + DEFAULT_LKC_CODE + "," + UPDATED_LKC_CODE);

        // Get all the lookupsList where lkcCode equals to UPDATED_LKC_CODE
        defaultLookupsShouldNotBeFound("lkcCode.in=" + UPDATED_LKC_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode is not null
        defaultLookupsShouldBeFound("lkcCode.specified=true");

        // Get all the lookupsList where lkcCode is null
        defaultLookupsShouldNotBeFound("lkcCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLkcCodeContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode contains DEFAULT_LKC_CODE
        defaultLookupsShouldBeFound("lkcCode.contains=" + DEFAULT_LKC_CODE);

        // Get all the lookupsList where lkcCode contains UPDATED_LKC_CODE
        defaultLookupsShouldNotBeFound("lkcCode.contains=" + UPDATED_LKC_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcCodeNotContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcCode does not contain DEFAULT_LKC_CODE
        defaultLookupsShouldNotBeFound("lkcCode.doesNotContain=" + DEFAULT_LKC_CODE);

        // Get all the lookupsList where lkcCode does not contain UPDATED_LKC_CODE
        defaultLookupsShouldBeFound("lkcCode.doesNotContain=" + UPDATED_LKC_CODE);
    }


    @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode equals to DEFAULT_LKC_SUB_CODE
        defaultLookupsShouldBeFound("lkcSubCode.equals=" + DEFAULT_LKC_SUB_CODE);

        // Get all the lookupsList where lkcSubCode equals to UPDATED_LKC_SUB_CODE
        defaultLookupsShouldNotBeFound("lkcSubCode.equals=" + UPDATED_LKC_SUB_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode not equals to DEFAULT_LKC_SUB_CODE
        defaultLookupsShouldNotBeFound("lkcSubCode.notEquals=" + DEFAULT_LKC_SUB_CODE);

        // Get all the lookupsList where lkcSubCode not equals to UPDATED_LKC_SUB_CODE
        defaultLookupsShouldBeFound("lkcSubCode.notEquals=" + UPDATED_LKC_SUB_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeIsInShouldWork() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode in DEFAULT_LKC_SUB_CODE or UPDATED_LKC_SUB_CODE
        defaultLookupsShouldBeFound("lkcSubCode.in=" + DEFAULT_LKC_SUB_CODE + "," + UPDATED_LKC_SUB_CODE);

        // Get all the lookupsList where lkcSubCode equals to UPDATED_LKC_SUB_CODE
        defaultLookupsShouldNotBeFound("lkcSubCode.in=" + UPDATED_LKC_SUB_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode is not null
        defaultLookupsShouldBeFound("lkcSubCode.specified=true");

        // Get all the lookupsList where lkcSubCode is null
        defaultLookupsShouldNotBeFound("lkcSubCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode contains DEFAULT_LKC_SUB_CODE
        defaultLookupsShouldBeFound("lkcSubCode.contains=" + DEFAULT_LKC_SUB_CODE);

        // Get all the lookupsList where lkcSubCode contains UPDATED_LKC_SUB_CODE
        defaultLookupsShouldNotBeFound("lkcSubCode.contains=" + UPDATED_LKC_SUB_CODE);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSubCodeNotContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSubCode does not contain DEFAULT_LKC_SUB_CODE
        defaultLookupsShouldNotBeFound("lkcSubCode.doesNotContain=" + DEFAULT_LKC_SUB_CODE);

        // Get all the lookupsList where lkcSubCode does not contain UPDATED_LKC_SUB_CODE
        defaultLookupsShouldBeFound("lkcSubCode.doesNotContain=" + UPDATED_LKC_SUB_CODE);
    }


    @Test
    @Transactional
    public void getAllLookupsByLkcSortIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort equals to DEFAULT_LKC_SORT
        defaultLookupsShouldBeFound("lkcSort.equals=" + DEFAULT_LKC_SORT);

        // Get all the lookupsList where lkcSort equals to UPDATED_LKC_SORT
        defaultLookupsShouldNotBeFound("lkcSort.equals=" + UPDATED_LKC_SORT);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSortIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort not equals to DEFAULT_LKC_SORT
        defaultLookupsShouldNotBeFound("lkcSort.notEquals=" + DEFAULT_LKC_SORT);

        // Get all the lookupsList where lkcSort not equals to UPDATED_LKC_SORT
        defaultLookupsShouldBeFound("lkcSort.notEquals=" + UPDATED_LKC_SORT);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSortIsInShouldWork() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort in DEFAULT_LKC_SORT or UPDATED_LKC_SORT
        defaultLookupsShouldBeFound("lkcSort.in=" + DEFAULT_LKC_SORT + "," + UPDATED_LKC_SORT);

        // Get all the lookupsList where lkcSort equals to UPDATED_LKC_SORT
        defaultLookupsShouldNotBeFound("lkcSort.in=" + UPDATED_LKC_SORT);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort is not null
        defaultLookupsShouldBeFound("lkcSort.specified=true");

        // Get all the lookupsList where lkcSort is null
        defaultLookupsShouldNotBeFound("lkcSort.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLkcSortContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort contains DEFAULT_LKC_SORT
        defaultLookupsShouldBeFound("lkcSort.contains=" + DEFAULT_LKC_SORT);

        // Get all the lookupsList where lkcSort contains UPDATED_LKC_SORT
        defaultLookupsShouldNotBeFound("lkcSort.contains=" + UPDATED_LKC_SORT);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcSortNotContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcSort does not contain DEFAULT_LKC_SORT
        defaultLookupsShouldNotBeFound("lkcSort.doesNotContain=" + DEFAULT_LKC_SORT);

        // Get all the lookupsList where lkcSort does not contain UPDATED_LKC_SORT
        defaultLookupsShouldBeFound("lkcSort.doesNotContain=" + UPDATED_LKC_SORT);
    }


    @Test
    @Transactional
    public void getAllLookupsByLkcEnabledIndIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcEnabledInd equals to DEFAULT_LKC_ENABLED_IND
        defaultLookupsShouldBeFound("lkcEnabledInd.equals=" + DEFAULT_LKC_ENABLED_IND);

        // Get all the lookupsList where lkcEnabledInd equals to UPDATED_LKC_ENABLED_IND
        defaultLookupsShouldNotBeFound("lkcEnabledInd.equals=" + UPDATED_LKC_ENABLED_IND);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcEnabledIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcEnabledInd not equals to DEFAULT_LKC_ENABLED_IND
        defaultLookupsShouldNotBeFound("lkcEnabledInd.notEquals=" + DEFAULT_LKC_ENABLED_IND);

        // Get all the lookupsList where lkcEnabledInd not equals to UPDATED_LKC_ENABLED_IND
        defaultLookupsShouldBeFound("lkcEnabledInd.notEquals=" + UPDATED_LKC_ENABLED_IND);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcEnabledIndIsInShouldWork() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcEnabledInd in DEFAULT_LKC_ENABLED_IND or UPDATED_LKC_ENABLED_IND
        defaultLookupsShouldBeFound("lkcEnabledInd.in=" + DEFAULT_LKC_ENABLED_IND + "," + UPDATED_LKC_ENABLED_IND);

        // Get all the lookupsList where lkcEnabledInd equals to UPDATED_LKC_ENABLED_IND
        defaultLookupsShouldNotBeFound("lkcEnabledInd.in=" + UPDATED_LKC_ENABLED_IND);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcEnabledIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcEnabledInd is not null
        defaultLookupsShouldBeFound("lkcEnabledInd.specified=true");

        // Get all the lookupsList where lkcEnabledInd is null
        defaultLookupsShouldNotBeFound("lkcEnabledInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcDescIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc equals to DEFAULT_LKC_DESC
        defaultLookupsShouldBeFound("lkcDesc.equals=" + DEFAULT_LKC_DESC);

        // Get all the lookupsList where lkcDesc equals to UPDATED_LKC_DESC
        defaultLookupsShouldNotBeFound("lkcDesc.equals=" + UPDATED_LKC_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcDescIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc not equals to DEFAULT_LKC_DESC
        defaultLookupsShouldNotBeFound("lkcDesc.notEquals=" + DEFAULT_LKC_DESC);

        // Get all the lookupsList where lkcDesc not equals to UPDATED_LKC_DESC
        defaultLookupsShouldBeFound("lkcDesc.notEquals=" + UPDATED_LKC_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcDescIsInShouldWork() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc in DEFAULT_LKC_DESC or UPDATED_LKC_DESC
        defaultLookupsShouldBeFound("lkcDesc.in=" + DEFAULT_LKC_DESC + "," + UPDATED_LKC_DESC);

        // Get all the lookupsList where lkcDesc equals to UPDATED_LKC_DESC
        defaultLookupsShouldNotBeFound("lkcDesc.in=" + UPDATED_LKC_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc is not null
        defaultLookupsShouldBeFound("lkcDesc.specified=true");

        // Get all the lookupsList where lkcDesc is null
        defaultLookupsShouldNotBeFound("lkcDesc.specified=false");
    }
                @Test
    @Transactional
    public void getAllLookupsByLkcDescContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc contains DEFAULT_LKC_DESC
        defaultLookupsShouldBeFound("lkcDesc.contains=" + DEFAULT_LKC_DESC);

        // Get all the lookupsList where lkcDesc contains UPDATED_LKC_DESC
        defaultLookupsShouldNotBeFound("lkcDesc.contains=" + UPDATED_LKC_DESC);
    }

    @Test
    @Transactional
    public void getAllLookupsByLkcDescNotContainsSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);

        // Get all the lookupsList where lkcDesc does not contain DEFAULT_LKC_DESC
        defaultLookupsShouldNotBeFound("lkcDesc.doesNotContain=" + DEFAULT_LKC_DESC);

        // Get all the lookupsList where lkcDesc does not contain UPDATED_LKC_DESC
        defaultLookupsShouldBeFound("lkcDesc.doesNotContain=" + UPDATED_LKC_DESC);
    }


    @Test
    @Transactional
    public void getAllLookupsByLookUpTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        lookupsRepository.saveAndFlush(lookups);
        LookupTypes lookUpType = LookupTypesResourceIT.createEntity(em);
        em.persist(lookUpType);
        em.flush();
        lookups.setLookUpType(lookUpType);
        lookupsRepository.saveAndFlush(lookups);
        Long lookUpTypeId = lookUpType.getId();

        // Get all the lookupsList where lookUpType equals to lookUpTypeId
        defaultLookupsShouldBeFound("lookUpTypeId.equals=" + lookUpTypeId);

        // Get all the lookupsList where lookUpType equals to lookUpTypeId + 1
        defaultLookupsShouldNotBeFound("lookUpTypeId.equals=" + (lookUpTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLookupsShouldBeFound(String filter) throws Exception {
        restLookupsMockMvc.perform(get("/api/lookups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lookups.getId().intValue())))
            .andExpect(jsonPath("$.[*].lkcCode").value(hasItem(DEFAULT_LKC_CODE)))
            .andExpect(jsonPath("$.[*].lkcSubCode").value(hasItem(DEFAULT_LKC_SUB_CODE)))
            .andExpect(jsonPath("$.[*].lkcSort").value(hasItem(DEFAULT_LKC_SORT)))
            .andExpect(jsonPath("$.[*].lkcEnabledInd").value(hasItem(DEFAULT_LKC_ENABLED_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].lkcDesc").value(hasItem(DEFAULT_LKC_DESC)));

        // Check, that the count call also returns 1
        restLookupsMockMvc.perform(get("/api/lookups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLookupsShouldNotBeFound(String filter) throws Exception {
        restLookupsMockMvc.perform(get("/api/lookups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLookupsMockMvc.perform(get("/api/lookups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLookups() throws Exception {
        // Get the lookups
        restLookupsMockMvc.perform(get("/api/lookups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLookups() throws Exception {
        // Initialize the database
        lookupsService.save(lookups);

        int databaseSizeBeforeUpdate = lookupsRepository.findAll().size();

        // Update the lookups
        Lookups updatedLookups = lookupsRepository.findById(lookups.getId()).get();
        // Disconnect from session so that the updates on updatedLookups are not directly saved in db
        em.detach(updatedLookups);
        updatedLookups
            .lkcCode(UPDATED_LKC_CODE)
            .lkcSubCode(UPDATED_LKC_SUB_CODE)
            .lkcSort(UPDATED_LKC_SORT)
            .lkcEnabledInd(UPDATED_LKC_ENABLED_IND)
            .lkcDesc(UPDATED_LKC_DESC);

        restLookupsMockMvc.perform(put("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLookups)))
            .andExpect(status().isOk());

        // Validate the Lookups in the database
        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeUpdate);
        Lookups testLookups = lookupsList.get(lookupsList.size() - 1);
        assertThat(testLookups.getLkcCode()).isEqualTo(UPDATED_LKC_CODE);
        assertThat(testLookups.getLkcSubCode()).isEqualTo(UPDATED_LKC_SUB_CODE);
        assertThat(testLookups.getLkcSort()).isEqualTo(UPDATED_LKC_SORT);
        assertThat(testLookups.isLkcEnabledInd()).isEqualTo(UPDATED_LKC_ENABLED_IND);
        assertThat(testLookups.getLkcDesc()).isEqualTo(UPDATED_LKC_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingLookups() throws Exception {
        int databaseSizeBeforeUpdate = lookupsRepository.findAll().size();

        // Create the Lookups

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLookupsMockMvc.perform(put("/api/lookups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lookups)))
            .andExpect(status().isBadRequest());

        // Validate the Lookups in the database
        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLookups() throws Exception {
        // Initialize the database
        lookupsService.save(lookups);

        int databaseSizeBeforeDelete = lookupsRepository.findAll().size();

        // Delete the lookups
        restLookupsMockMvc.perform(delete("/api/lookups/{id}", lookups.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lookups> lookupsList = lookupsRepository.findAll();
        assertThat(lookupsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
