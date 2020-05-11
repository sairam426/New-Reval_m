package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Organizations;
import com.jhipster.reval.ui.repository.OrganizationsRepository;
import com.jhipster.reval.ui.service.OrganizationsService;

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
 * Integration tests for the {@link OrganizationsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrganizationsResourceIT {

    private static final String DEFAULT_ORG_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORG_STATE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ZIP = "BBBBBBBBBB";

    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Autowired
    private OrganizationsService organizationsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganizationsMockMvc;

    private Organizations organizations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizations createEntity(EntityManager em) {
        Organizations organizations = new Organizations()
            .orgNbr(DEFAULT_ORG_NBR)
            .orgName(DEFAULT_ORG_NAME)
            .orgShortName(DEFAULT_ORG_SHORT_NAME)
            .orgAddress1(DEFAULT_ORG_ADDRESS_1)
            .orgAddress2(DEFAULT_ORG_ADDRESS_2)
            .orgCity(DEFAULT_ORG_CITY)
            .orgStateCd(DEFAULT_ORG_STATE_CD)
            .orgZip(DEFAULT_ORG_ZIP);
        return organizations;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizations createUpdatedEntity(EntityManager em) {
        Organizations organizations = new Organizations()
            .orgNbr(UPDATED_ORG_NBR)
            .orgName(UPDATED_ORG_NAME)
            .orgShortName(UPDATED_ORG_SHORT_NAME)
            .orgAddress1(UPDATED_ORG_ADDRESS_1)
            .orgAddress2(UPDATED_ORG_ADDRESS_2)
            .orgCity(UPDATED_ORG_CITY)
            .orgStateCd(UPDATED_ORG_STATE_CD)
            .orgZip(UPDATED_ORG_ZIP);
        return organizations;
    }

    @BeforeEach
    public void initTest() {
        organizations = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganizations() throws Exception {
        int databaseSizeBeforeCreate = organizationsRepository.findAll().size();

        // Create the Organizations
        restOrganizationsMockMvc.perform(post("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isCreated());

        // Validate the Organizations in the database
        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeCreate + 1);
        Organizations testOrganizations = organizationsList.get(organizationsList.size() - 1);
        assertThat(testOrganizations.getOrgNbr()).isEqualTo(DEFAULT_ORG_NBR);
        assertThat(testOrganizations.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testOrganizations.getOrgShortName()).isEqualTo(DEFAULT_ORG_SHORT_NAME);
        assertThat(testOrganizations.getOrgAddress1()).isEqualTo(DEFAULT_ORG_ADDRESS_1);
        assertThat(testOrganizations.getOrgAddress2()).isEqualTo(DEFAULT_ORG_ADDRESS_2);
        assertThat(testOrganizations.getOrgCity()).isEqualTo(DEFAULT_ORG_CITY);
        assertThat(testOrganizations.getOrgStateCd()).isEqualTo(DEFAULT_ORG_STATE_CD);
        assertThat(testOrganizations.getOrgZip()).isEqualTo(DEFAULT_ORG_ZIP);
    }

    @Test
    @Transactional
    public void createOrganizationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationsRepository.findAll().size();

        // Create the Organizations with an existing ID
        organizations.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationsMockMvc.perform(post("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isBadRequest());

        // Validate the Organizations in the database
        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationsRepository.findAll().size();
        // set the field null
        organizations.setOrgNbr(null);

        // Create the Organizations, which fails.

        restOrganizationsMockMvc.perform(post("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isBadRequest());

        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationsRepository.findAll().size();
        // set the field null
        organizations.setOrgName(null);

        // Create the Organizations, which fails.

        restOrganizationsMockMvc.perform(post("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isBadRequest());

        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrgShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationsRepository.findAll().size();
        // set the field null
        organizations.setOrgShortName(null);

        // Create the Organizations, which fails.

        restOrganizationsMockMvc.perform(post("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isBadRequest());

        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganizations() throws Exception {
        // Initialize the database
        organizationsRepository.saveAndFlush(organizations);

        // Get all the organizationsList
        restOrganizationsMockMvc.perform(get("/api/organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizations.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgNbr").value(hasItem(DEFAULT_ORG_NBR)))
            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
            .andExpect(jsonPath("$.[*].orgShortName").value(hasItem(DEFAULT_ORG_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].orgAddress1").value(hasItem(DEFAULT_ORG_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].orgAddress2").value(hasItem(DEFAULT_ORG_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].orgCity").value(hasItem(DEFAULT_ORG_CITY)))
            .andExpect(jsonPath("$.[*].orgStateCd").value(hasItem(DEFAULT_ORG_STATE_CD)))
            .andExpect(jsonPath("$.[*].orgZip").value(hasItem(DEFAULT_ORG_ZIP)));
    }
    
    @Test
    @Transactional
    public void getOrganizations() throws Exception {
        // Initialize the database
        organizationsRepository.saveAndFlush(organizations);

        // Get the organizations
        restOrganizationsMockMvc.perform(get("/api/organizations/{id}", organizations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organizations.getId().intValue()))
            .andExpect(jsonPath("$.orgNbr").value(DEFAULT_ORG_NBR))
            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME))
            .andExpect(jsonPath("$.orgShortName").value(DEFAULT_ORG_SHORT_NAME))
            .andExpect(jsonPath("$.orgAddress1").value(DEFAULT_ORG_ADDRESS_1))
            .andExpect(jsonPath("$.orgAddress2").value(DEFAULT_ORG_ADDRESS_2))
            .andExpect(jsonPath("$.orgCity").value(DEFAULT_ORG_CITY))
            .andExpect(jsonPath("$.orgStateCd").value(DEFAULT_ORG_STATE_CD))
            .andExpect(jsonPath("$.orgZip").value(DEFAULT_ORG_ZIP));
    }

    @Test
    @Transactional
    public void getNonExistingOrganizations() throws Exception {
        // Get the organizations
        restOrganizationsMockMvc.perform(get("/api/organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizations() throws Exception {
        // Initialize the database
        organizationsService.save(organizations);

        int databaseSizeBeforeUpdate = organizationsRepository.findAll().size();

        // Update the organizations
        Organizations updatedOrganizations = organizationsRepository.findById(organizations.getId()).get();
        // Disconnect from session so that the updates on updatedOrganizations are not directly saved in db
        em.detach(updatedOrganizations);
        updatedOrganizations
            .orgNbr(UPDATED_ORG_NBR)
            .orgName(UPDATED_ORG_NAME)
            .orgShortName(UPDATED_ORG_SHORT_NAME)
            .orgAddress1(UPDATED_ORG_ADDRESS_1)
            .orgAddress2(UPDATED_ORG_ADDRESS_2)
            .orgCity(UPDATED_ORG_CITY)
            .orgStateCd(UPDATED_ORG_STATE_CD)
            .orgZip(UPDATED_ORG_ZIP);

        restOrganizationsMockMvc.perform(put("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrganizations)))
            .andExpect(status().isOk());

        // Validate the Organizations in the database
        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeUpdate);
        Organizations testOrganizations = organizationsList.get(organizationsList.size() - 1);
        assertThat(testOrganizations.getOrgNbr()).isEqualTo(UPDATED_ORG_NBR);
        assertThat(testOrganizations.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testOrganizations.getOrgShortName()).isEqualTo(UPDATED_ORG_SHORT_NAME);
        assertThat(testOrganizations.getOrgAddress1()).isEqualTo(UPDATED_ORG_ADDRESS_1);
        assertThat(testOrganizations.getOrgAddress2()).isEqualTo(UPDATED_ORG_ADDRESS_2);
        assertThat(testOrganizations.getOrgCity()).isEqualTo(UPDATED_ORG_CITY);
        assertThat(testOrganizations.getOrgStateCd()).isEqualTo(UPDATED_ORG_STATE_CD);
        assertThat(testOrganizations.getOrgZip()).isEqualTo(UPDATED_ORG_ZIP);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganizations() throws Exception {
        int databaseSizeBeforeUpdate = organizationsRepository.findAll().size();

        // Create the Organizations

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizationsMockMvc.perform(put("/api/organizations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizations)))
            .andExpect(status().isBadRequest());

        // Validate the Organizations in the database
        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganizations() throws Exception {
        // Initialize the database
        organizationsService.save(organizations);

        int databaseSizeBeforeDelete = organizationsRepository.findAll().size();

        // Delete the organizations
        restOrganizationsMockMvc.perform(delete("/api/organizations/{id}", organizations.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organizations> organizationsList = organizationsRepository.findAll();
        assertThat(organizationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
