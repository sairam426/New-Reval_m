package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.TransactionCodes;
import com.jhipster.reval.ui.repository.TransactionCodesRepository;
import com.jhipster.reval.ui.service.TransactionCodesService;

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
 * Integration tests for the {@link TransactionCodesResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TransactionCodesResourceIT {

    private static final String DEFAULT_TCD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TCD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TCD_ENTITY_GROUP_CD = "AAAAAAAAAA";
    private static final String UPDATED_TCD_ENTITY_GROUP_CD = "BBBBBBBBBB";

    private static final String DEFAULT_TCD_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TCD_DESC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TCD_ENABLED_IND = false;
    private static final Boolean UPDATED_TCD_ENABLED_IND = true;

    @Autowired
    private TransactionCodesRepository transactionCodesRepository;

    @Autowired
    private TransactionCodesService transactionCodesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionCodesMockMvc;

    private TransactionCodes transactionCodes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionCodes createEntity(EntityManager em) {
        TransactionCodes transactionCodes = new TransactionCodes()
            .tcdCode(DEFAULT_TCD_CODE)
            .tcdEntityGroupCd(DEFAULT_TCD_ENTITY_GROUP_CD)
            .tcdDesc(DEFAULT_TCD_DESC)
            .tcdEnabledInd(DEFAULT_TCD_ENABLED_IND);
        return transactionCodes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionCodes createUpdatedEntity(EntityManager em) {
        TransactionCodes transactionCodes = new TransactionCodes()
            .tcdCode(UPDATED_TCD_CODE)
            .tcdEntityGroupCd(UPDATED_TCD_ENTITY_GROUP_CD)
            .tcdDesc(UPDATED_TCD_DESC)
            .tcdEnabledInd(UPDATED_TCD_ENABLED_IND);
        return transactionCodes;
    }

    @BeforeEach
    public void initTest() {
        transactionCodes = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionCodes() throws Exception {
        int databaseSizeBeforeCreate = transactionCodesRepository.findAll().size();

        // Create the TransactionCodes
        restTransactionCodesMockMvc.perform(post("/api/transaction-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodes)))
            .andExpect(status().isCreated());

        // Validate the TransactionCodes in the database
        List<TransactionCodes> transactionCodesList = transactionCodesRepository.findAll();
        assertThat(transactionCodesList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionCodes testTransactionCodes = transactionCodesList.get(transactionCodesList.size() - 1);
        assertThat(testTransactionCodes.getTcdCode()).isEqualTo(DEFAULT_TCD_CODE);
        assertThat(testTransactionCodes.getTcdEntityGroupCd()).isEqualTo(DEFAULT_TCD_ENTITY_GROUP_CD);
        assertThat(testTransactionCodes.getTcdDesc()).isEqualTo(DEFAULT_TCD_DESC);
        assertThat(testTransactionCodes.isTcdEnabledInd()).isEqualTo(DEFAULT_TCD_ENABLED_IND);
    }

    @Test
    @Transactional
    public void createTransactionCodesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionCodesRepository.findAll().size();

        // Create the TransactionCodes with an existing ID
        transactionCodes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionCodesMockMvc.perform(post("/api/transaction-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodes)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionCodes in the database
        List<TransactionCodes> transactionCodesList = transactionCodesRepository.findAll();
        assertThat(transactionCodesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransactionCodes() throws Exception {
        // Initialize the database
        transactionCodesRepository.saveAndFlush(transactionCodes);

        // Get all the transactionCodesList
        restTransactionCodesMockMvc.perform(get("/api/transaction-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionCodes.getId().intValue())))
            .andExpect(jsonPath("$.[*].tcdCode").value(hasItem(DEFAULT_TCD_CODE)))
            .andExpect(jsonPath("$.[*].tcdEntityGroupCd").value(hasItem(DEFAULT_TCD_ENTITY_GROUP_CD)))
            .andExpect(jsonPath("$.[*].tcdDesc").value(hasItem(DEFAULT_TCD_DESC)))
            .andExpect(jsonPath("$.[*].tcdEnabledInd").value(hasItem(DEFAULT_TCD_ENABLED_IND.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTransactionCodes() throws Exception {
        // Initialize the database
        transactionCodesRepository.saveAndFlush(transactionCodes);

        // Get the transactionCodes
        restTransactionCodesMockMvc.perform(get("/api/transaction-codes/{id}", transactionCodes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionCodes.getId().intValue()))
            .andExpect(jsonPath("$.tcdCode").value(DEFAULT_TCD_CODE))
            .andExpect(jsonPath("$.tcdEntityGroupCd").value(DEFAULT_TCD_ENTITY_GROUP_CD))
            .andExpect(jsonPath("$.tcdDesc").value(DEFAULT_TCD_DESC))
            .andExpect(jsonPath("$.tcdEnabledInd").value(DEFAULT_TCD_ENABLED_IND.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTransactionCodes() throws Exception {
        // Get the transactionCodes
        restTransactionCodesMockMvc.perform(get("/api/transaction-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionCodes() throws Exception {
        // Initialize the database
        transactionCodesService.save(transactionCodes);

        int databaseSizeBeforeUpdate = transactionCodesRepository.findAll().size();

        // Update the transactionCodes
        TransactionCodes updatedTransactionCodes = transactionCodesRepository.findById(transactionCodes.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionCodes are not directly saved in db
        em.detach(updatedTransactionCodes);
        updatedTransactionCodes
            .tcdCode(UPDATED_TCD_CODE)
            .tcdEntityGroupCd(UPDATED_TCD_ENTITY_GROUP_CD)
            .tcdDesc(UPDATED_TCD_DESC)
            .tcdEnabledInd(UPDATED_TCD_ENABLED_IND);

        restTransactionCodesMockMvc.perform(put("/api/transaction-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactionCodes)))
            .andExpect(status().isOk());

        // Validate the TransactionCodes in the database
        List<TransactionCodes> transactionCodesList = transactionCodesRepository.findAll();
        assertThat(transactionCodesList).hasSize(databaseSizeBeforeUpdate);
        TransactionCodes testTransactionCodes = transactionCodesList.get(transactionCodesList.size() - 1);
        assertThat(testTransactionCodes.getTcdCode()).isEqualTo(UPDATED_TCD_CODE);
        assertThat(testTransactionCodes.getTcdEntityGroupCd()).isEqualTo(UPDATED_TCD_ENTITY_GROUP_CD);
        assertThat(testTransactionCodes.getTcdDesc()).isEqualTo(UPDATED_TCD_DESC);
        assertThat(testTransactionCodes.isTcdEnabledInd()).isEqualTo(UPDATED_TCD_ENABLED_IND);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionCodes() throws Exception {
        int databaseSizeBeforeUpdate = transactionCodesRepository.findAll().size();

        // Create the TransactionCodes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionCodesMockMvc.perform(put("/api/transaction-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodes)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionCodes in the database
        List<TransactionCodes> transactionCodesList = transactionCodesRepository.findAll();
        assertThat(transactionCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionCodes() throws Exception {
        // Initialize the database
        transactionCodesService.save(transactionCodes);

        int databaseSizeBeforeDelete = transactionCodesRepository.findAll().size();

        // Delete the transactionCodes
        restTransactionCodesMockMvc.perform(delete("/api/transaction-codes/{id}", transactionCodes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionCodes> transactionCodesList = transactionCodesRepository.findAll();
        assertThat(transactionCodesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
