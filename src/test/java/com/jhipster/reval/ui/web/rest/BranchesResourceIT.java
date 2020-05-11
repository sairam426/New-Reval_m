package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Branches;
import com.jhipster.reval.ui.repository.BranchesRepository;
import com.jhipster.reval.ui.service.BranchesService;

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
 * Integration tests for the {@link BranchesResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BranchesResourceIT {

    private static final String DEFAULT_BRN_NBR = "AAAAAAAAAA";
    private static final String UPDATED_BRN_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRN_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_BRN_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRN_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BRN_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_BRN_STATE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_BRN_ZIP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BRN_ENABLED_IND = false;
    private static final Boolean UPDATED_BRN_ENABLED_IND = true;

    private static final String DEFAULT_BRN_REGIOIN_CD = "AAAAAAAAAA";
    private static final String UPDATED_BRN_REGIOIN_CD = "BBBBBBBBBB";

    private static final String DEFAULT_BRN_SERVICE_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_BRN_SERVICE_TYPE_CD = "BBBBBBBBBB";

    @Autowired
    private BranchesRepository branchesRepository;

    @Autowired
    private BranchesService branchesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchesMockMvc;

    private Branches branches;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branches createEntity(EntityManager em) {
        Branches branches = new Branches()
            .brnNbr(DEFAULT_BRN_NBR)
            .brnName(DEFAULT_BRN_NAME)
            .brnCode(DEFAULT_BRN_CODE)
            .brnShortName(DEFAULT_BRN_SHORT_NAME)
            .brnAddress1(DEFAULT_BRN_ADDRESS_1)
            .brnAddress2(DEFAULT_BRN_ADDRESS_2)
            .brnCity(DEFAULT_BRN_CITY)
            .brnStateCd(DEFAULT_BRN_STATE_CD)
            .brnZip(DEFAULT_BRN_ZIP)
            .brnEnabledInd(DEFAULT_BRN_ENABLED_IND)
            .brnRegioinCd(DEFAULT_BRN_REGIOIN_CD)
            .brnServiceTypeCd(DEFAULT_BRN_SERVICE_TYPE_CD);
        return branches;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branches createUpdatedEntity(EntityManager em) {
        Branches branches = new Branches()
            .brnNbr(UPDATED_BRN_NBR)
            .brnName(UPDATED_BRN_NAME)
            .brnCode(UPDATED_BRN_CODE)
            .brnShortName(UPDATED_BRN_SHORT_NAME)
            .brnAddress1(UPDATED_BRN_ADDRESS_1)
            .brnAddress2(UPDATED_BRN_ADDRESS_2)
            .brnCity(UPDATED_BRN_CITY)
            .brnStateCd(UPDATED_BRN_STATE_CD)
            .brnZip(UPDATED_BRN_ZIP)
            .brnEnabledInd(UPDATED_BRN_ENABLED_IND)
            .brnRegioinCd(UPDATED_BRN_REGIOIN_CD)
            .brnServiceTypeCd(UPDATED_BRN_SERVICE_TYPE_CD);
        return branches;
    }

    @BeforeEach
    public void initTest() {
        branches = createEntity(em);
    }

    @Test
    @Transactional
    public void createBranches() throws Exception {
        int databaseSizeBeforeCreate = branchesRepository.findAll().size();

        // Create the Branches
        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isCreated());

        // Validate the Branches in the database
        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeCreate + 1);
        Branches testBranches = branchesList.get(branchesList.size() - 1);
        assertThat(testBranches.getBrnNbr()).isEqualTo(DEFAULT_BRN_NBR);
        assertThat(testBranches.getBrnName()).isEqualTo(DEFAULT_BRN_NAME);
        assertThat(testBranches.getBrnCode()).isEqualTo(DEFAULT_BRN_CODE);
        assertThat(testBranches.getBrnShortName()).isEqualTo(DEFAULT_BRN_SHORT_NAME);
        assertThat(testBranches.getBrnAddress1()).isEqualTo(DEFAULT_BRN_ADDRESS_1);
        assertThat(testBranches.getBrnAddress2()).isEqualTo(DEFAULT_BRN_ADDRESS_2);
        assertThat(testBranches.getBrnCity()).isEqualTo(DEFAULT_BRN_CITY);
        assertThat(testBranches.getBrnStateCd()).isEqualTo(DEFAULT_BRN_STATE_CD);
        assertThat(testBranches.getBrnZip()).isEqualTo(DEFAULT_BRN_ZIP);
        assertThat(testBranches.isBrnEnabledInd()).isEqualTo(DEFAULT_BRN_ENABLED_IND);
        assertThat(testBranches.getBrnRegioinCd()).isEqualTo(DEFAULT_BRN_REGIOIN_CD);
        assertThat(testBranches.getBrnServiceTypeCd()).isEqualTo(DEFAULT_BRN_SERVICE_TYPE_CD);
    }

    @Test
    @Transactional
    public void createBranchesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = branchesRepository.findAll().size();

        // Create the Branches with an existing ID
        branches.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBrnNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnNbr(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnName(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnCode(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnShortName(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnAddress1IsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnAddress1(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnAddress2IsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnAddress2(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnCity(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnStateCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnStateCd(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnZipIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnZip(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnEnabledIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnEnabledInd(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBrnRegioinCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchesRepository.findAll().size();
        // set the field null
        branches.setBrnRegioinCd(null);

        // Create the Branches, which fails.

        restBranchesMockMvc.perform(post("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBranches() throws Exception {
        // Initialize the database
        branchesRepository.saveAndFlush(branches);

        // Get all the branchesList
        restBranchesMockMvc.perform(get("/api/branches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branches.getId().intValue())))
            .andExpect(jsonPath("$.[*].brnNbr").value(hasItem(DEFAULT_BRN_NBR)))
            .andExpect(jsonPath("$.[*].brnName").value(hasItem(DEFAULT_BRN_NAME)))
            .andExpect(jsonPath("$.[*].brnCode").value(hasItem(DEFAULT_BRN_CODE)))
            .andExpect(jsonPath("$.[*].brnShortName").value(hasItem(DEFAULT_BRN_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].brnAddress1").value(hasItem(DEFAULT_BRN_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].brnAddress2").value(hasItem(DEFAULT_BRN_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].brnCity").value(hasItem(DEFAULT_BRN_CITY)))
            .andExpect(jsonPath("$.[*].brnStateCd").value(hasItem(DEFAULT_BRN_STATE_CD)))
            .andExpect(jsonPath("$.[*].brnZip").value(hasItem(DEFAULT_BRN_ZIP)))
            .andExpect(jsonPath("$.[*].brnEnabledInd").value(hasItem(DEFAULT_BRN_ENABLED_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].brnRegioinCd").value(hasItem(DEFAULT_BRN_REGIOIN_CD)))
            .andExpect(jsonPath("$.[*].brnServiceTypeCd").value(hasItem(DEFAULT_BRN_SERVICE_TYPE_CD)));
    }
    
    @Test
    @Transactional
    public void getBranches() throws Exception {
        // Initialize the database
        branchesRepository.saveAndFlush(branches);

        // Get the branches
        restBranchesMockMvc.perform(get("/api/branches/{id}", branches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branches.getId().intValue()))
            .andExpect(jsonPath("$.brnNbr").value(DEFAULT_BRN_NBR))
            .andExpect(jsonPath("$.brnName").value(DEFAULT_BRN_NAME))
            .andExpect(jsonPath("$.brnCode").value(DEFAULT_BRN_CODE))
            .andExpect(jsonPath("$.brnShortName").value(DEFAULT_BRN_SHORT_NAME))
            .andExpect(jsonPath("$.brnAddress1").value(DEFAULT_BRN_ADDRESS_1))
            .andExpect(jsonPath("$.brnAddress2").value(DEFAULT_BRN_ADDRESS_2))
            .andExpect(jsonPath("$.brnCity").value(DEFAULT_BRN_CITY))
            .andExpect(jsonPath("$.brnStateCd").value(DEFAULT_BRN_STATE_CD))
            .andExpect(jsonPath("$.brnZip").value(DEFAULT_BRN_ZIP))
            .andExpect(jsonPath("$.brnEnabledInd").value(DEFAULT_BRN_ENABLED_IND.booleanValue()))
            .andExpect(jsonPath("$.brnRegioinCd").value(DEFAULT_BRN_REGIOIN_CD))
            .andExpect(jsonPath("$.brnServiceTypeCd").value(DEFAULT_BRN_SERVICE_TYPE_CD));
    }

    @Test
    @Transactional
    public void getNonExistingBranches() throws Exception {
        // Get the branches
        restBranchesMockMvc.perform(get("/api/branches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBranches() throws Exception {
        // Initialize the database
        branchesService.save(branches);

        int databaseSizeBeforeUpdate = branchesRepository.findAll().size();

        // Update the branches
        Branches updatedBranches = branchesRepository.findById(branches.getId()).get();
        // Disconnect from session so that the updates on updatedBranches are not directly saved in db
        em.detach(updatedBranches);
        updatedBranches
            .brnNbr(UPDATED_BRN_NBR)
            .brnName(UPDATED_BRN_NAME)
            .brnCode(UPDATED_BRN_CODE)
            .brnShortName(UPDATED_BRN_SHORT_NAME)
            .brnAddress1(UPDATED_BRN_ADDRESS_1)
            .brnAddress2(UPDATED_BRN_ADDRESS_2)
            .brnCity(UPDATED_BRN_CITY)
            .brnStateCd(UPDATED_BRN_STATE_CD)
            .brnZip(UPDATED_BRN_ZIP)
            .brnEnabledInd(UPDATED_BRN_ENABLED_IND)
            .brnRegioinCd(UPDATED_BRN_REGIOIN_CD)
            .brnServiceTypeCd(UPDATED_BRN_SERVICE_TYPE_CD);

        restBranchesMockMvc.perform(put("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBranches)))
            .andExpect(status().isOk());

        // Validate the Branches in the database
        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeUpdate);
        Branches testBranches = branchesList.get(branchesList.size() - 1);
        assertThat(testBranches.getBrnNbr()).isEqualTo(UPDATED_BRN_NBR);
        assertThat(testBranches.getBrnName()).isEqualTo(UPDATED_BRN_NAME);
        assertThat(testBranches.getBrnCode()).isEqualTo(UPDATED_BRN_CODE);
        assertThat(testBranches.getBrnShortName()).isEqualTo(UPDATED_BRN_SHORT_NAME);
        assertThat(testBranches.getBrnAddress1()).isEqualTo(UPDATED_BRN_ADDRESS_1);
        assertThat(testBranches.getBrnAddress2()).isEqualTo(UPDATED_BRN_ADDRESS_2);
        assertThat(testBranches.getBrnCity()).isEqualTo(UPDATED_BRN_CITY);
        assertThat(testBranches.getBrnStateCd()).isEqualTo(UPDATED_BRN_STATE_CD);
        assertThat(testBranches.getBrnZip()).isEqualTo(UPDATED_BRN_ZIP);
        assertThat(testBranches.isBrnEnabledInd()).isEqualTo(UPDATED_BRN_ENABLED_IND);
        assertThat(testBranches.getBrnRegioinCd()).isEqualTo(UPDATED_BRN_REGIOIN_CD);
        assertThat(testBranches.getBrnServiceTypeCd()).isEqualTo(UPDATED_BRN_SERVICE_TYPE_CD);
    }

    @Test
    @Transactional
    public void updateNonExistingBranches() throws Exception {
        int databaseSizeBeforeUpdate = branchesRepository.findAll().size();

        // Create the Branches

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchesMockMvc.perform(put("/api/branches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(branches)))
            .andExpect(status().isBadRequest());

        // Validate the Branches in the database
        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBranches() throws Exception {
        // Initialize the database
        branchesService.save(branches);

        int databaseSizeBeforeDelete = branchesRepository.findAll().size();

        // Delete the branches
        restBranchesMockMvc.perform(delete("/api/branches/{id}", branches.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Branches> branchesList = branchesRepository.findAll();
        assertThat(branchesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
