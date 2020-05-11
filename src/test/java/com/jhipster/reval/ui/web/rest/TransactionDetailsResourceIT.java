package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.TransactionDetails;
import com.jhipster.reval.ui.domain.Transactions;
import com.jhipster.reval.ui.repository.TransactionDetailsRepository;
import com.jhipster.reval.ui.service.TransactionDetailsService;
import com.jhipster.reval.ui.service.dto.TransactionDetailsCriteria;
import com.jhipster.reval.ui.service.TransactionDetailsQueryService;

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
 * Integration tests for the {@link TransactionDetailsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TransactionDetailsResourceIT {

    private static final String DEFAULT_TXD_PRM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TXD_PRM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TXD_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_TXD_VALUE = "BBBBBBBBBB";

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @Autowired
    private TransactionDetailsQueryService transactionDetailsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionDetailsMockMvc;

    private TransactionDetails transactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionDetails createEntity(EntityManager em) {
        TransactionDetails transactionDetails = new TransactionDetails()
            .txdPrmCode(DEFAULT_TXD_PRM_CODE)
            .txdValue(DEFAULT_TXD_VALUE);
        return transactionDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionDetails createUpdatedEntity(EntityManager em) {
        TransactionDetails transactionDetails = new TransactionDetails()
            .txdPrmCode(UPDATED_TXD_PRM_CODE)
            .txdValue(UPDATED_TXD_VALUE);
        return transactionDetails;
    }

    @BeforeEach
    public void initTest() {
        transactionDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = transactionDetailsRepository.findAll().size();

        // Create the TransactionDetails
        restTransactionDetailsMockMvc.perform(post("/api/transaction-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionDetails)))
            .andExpect(status().isCreated());

        // Validate the TransactionDetails in the database
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionDetails testTransactionDetails = transactionDetailsList.get(transactionDetailsList.size() - 1);
        assertThat(testTransactionDetails.getTxdPrmCode()).isEqualTo(DEFAULT_TXD_PRM_CODE);
        assertThat(testTransactionDetails.getTxdValue()).isEqualTo(DEFAULT_TXD_VALUE);
    }

    @Test
    @Transactional
    public void createTransactionDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionDetailsRepository.findAll().size();

        // Create the TransactionDetails with an existing ID
        transactionDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionDetailsMockMvc.perform(post("/api/transaction-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionDetails)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionDetails in the database
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTxdPrmCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionDetailsRepository.findAll().size();
        // set the field null
        transactionDetails.setTxdPrmCode(null);

        // Create the TransactionDetails, which fails.

        restTransactionDetailsMockMvc.perform(post("/api/transaction-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionDetails)))
            .andExpect(status().isBadRequest());

        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionDetails() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].txdPrmCode").value(hasItem(DEFAULT_TXD_PRM_CODE)))
            .andExpect(jsonPath("$.[*].txdValue").value(hasItem(DEFAULT_TXD_VALUE)));
    }
    
    @Test
    @Transactional
    public void getTransactionDetails() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get the transactionDetails
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details/{id}", transactionDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionDetails.getId().intValue()))
            .andExpect(jsonPath("$.txdPrmCode").value(DEFAULT_TXD_PRM_CODE))
            .andExpect(jsonPath("$.txdValue").value(DEFAULT_TXD_VALUE));
    }


    @Test
    @Transactional
    public void getTransactionDetailsByIdFiltering() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        Long id = transactionDetails.getId();

        defaultTransactionDetailsShouldBeFound("id.equals=" + id);
        defaultTransactionDetailsShouldNotBeFound("id.notEquals=" + id);

        defaultTransactionDetailsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTransactionDetailsShouldNotBeFound("id.greaterThan=" + id);

        defaultTransactionDetailsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTransactionDetailsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode equals to DEFAULT_TXD_PRM_CODE
        defaultTransactionDetailsShouldBeFound("txdPrmCode.equals=" + DEFAULT_TXD_PRM_CODE);

        // Get all the transactionDetailsList where txdPrmCode equals to UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.equals=" + UPDATED_TXD_PRM_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode not equals to DEFAULT_TXD_PRM_CODE
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.notEquals=" + DEFAULT_TXD_PRM_CODE);

        // Get all the transactionDetailsList where txdPrmCode not equals to UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldBeFound("txdPrmCode.notEquals=" + UPDATED_TXD_PRM_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode in DEFAULT_TXD_PRM_CODE or UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldBeFound("txdPrmCode.in=" + DEFAULT_TXD_PRM_CODE + "," + UPDATED_TXD_PRM_CODE);

        // Get all the transactionDetailsList where txdPrmCode equals to UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.in=" + UPDATED_TXD_PRM_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode is not null
        defaultTransactionDetailsShouldBeFound("txdPrmCode.specified=true");

        // Get all the transactionDetailsList where txdPrmCode is null
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeContainsSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode contains DEFAULT_TXD_PRM_CODE
        defaultTransactionDetailsShouldBeFound("txdPrmCode.contains=" + DEFAULT_TXD_PRM_CODE);

        // Get all the transactionDetailsList where txdPrmCode contains UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.contains=" + UPDATED_TXD_PRM_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdPrmCodeNotContainsSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdPrmCode does not contain DEFAULT_TXD_PRM_CODE
        defaultTransactionDetailsShouldNotBeFound("txdPrmCode.doesNotContain=" + DEFAULT_TXD_PRM_CODE);

        // Get all the transactionDetailsList where txdPrmCode does not contain UPDATED_TXD_PRM_CODE
        defaultTransactionDetailsShouldBeFound("txdPrmCode.doesNotContain=" + UPDATED_TXD_PRM_CODE);
    }


    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue equals to DEFAULT_TXD_VALUE
        defaultTransactionDetailsShouldBeFound("txdValue.equals=" + DEFAULT_TXD_VALUE);

        // Get all the transactionDetailsList where txdValue equals to UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldNotBeFound("txdValue.equals=" + UPDATED_TXD_VALUE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue not equals to DEFAULT_TXD_VALUE
        defaultTransactionDetailsShouldNotBeFound("txdValue.notEquals=" + DEFAULT_TXD_VALUE);

        // Get all the transactionDetailsList where txdValue not equals to UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldBeFound("txdValue.notEquals=" + UPDATED_TXD_VALUE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueIsInShouldWork() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue in DEFAULT_TXD_VALUE or UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldBeFound("txdValue.in=" + DEFAULT_TXD_VALUE + "," + UPDATED_TXD_VALUE);

        // Get all the transactionDetailsList where txdValue equals to UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldNotBeFound("txdValue.in=" + UPDATED_TXD_VALUE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue is not null
        defaultTransactionDetailsShouldBeFound("txdValue.specified=true");

        // Get all the transactionDetailsList where txdValue is null
        defaultTransactionDetailsShouldNotBeFound("txdValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueContainsSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue contains DEFAULT_TXD_VALUE
        defaultTransactionDetailsShouldBeFound("txdValue.contains=" + DEFAULT_TXD_VALUE);

        // Get all the transactionDetailsList where txdValue contains UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldNotBeFound("txdValue.contains=" + UPDATED_TXD_VALUE);
    }

    @Test
    @Transactional
    public void getAllTransactionDetailsByTxdValueNotContainsSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);

        // Get all the transactionDetailsList where txdValue does not contain DEFAULT_TXD_VALUE
        defaultTransactionDetailsShouldNotBeFound("txdValue.doesNotContain=" + DEFAULT_TXD_VALUE);

        // Get all the transactionDetailsList where txdValue does not contain UPDATED_TXD_VALUE
        defaultTransactionDetailsShouldBeFound("txdValue.doesNotContain=" + UPDATED_TXD_VALUE);
    }


    @Test
    @Transactional
    public void getAllTransactionDetailsByTransactionIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionDetailsRepository.saveAndFlush(transactionDetails);
        Transactions transaction = TransactionsResourceIT.createEntity(em);
        em.persist(transaction);
        em.flush();
        transactionDetails.setTransaction(transaction);
        transactionDetailsRepository.saveAndFlush(transactionDetails);
        Long transactionId = transaction.getId();

        // Get all the transactionDetailsList where transaction equals to transactionId
        defaultTransactionDetailsShouldBeFound("transactionId.equals=" + transactionId);

        // Get all the transactionDetailsList where transaction equals to transactionId + 1
        defaultTransactionDetailsShouldNotBeFound("transactionId.equals=" + (transactionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTransactionDetailsShouldBeFound(String filter) throws Exception {
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].txdPrmCode").value(hasItem(DEFAULT_TXD_PRM_CODE)))
            .andExpect(jsonPath("$.[*].txdValue").value(hasItem(DEFAULT_TXD_VALUE)));

        // Check, that the count call also returns 1
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTransactionDetailsShouldNotBeFound(String filter) throws Exception {
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTransactionDetails() throws Exception {
        // Get the transactionDetails
        restTransactionDetailsMockMvc.perform(get("/api/transaction-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionDetails() throws Exception {
        // Initialize the database
        transactionDetailsService.save(transactionDetails);

        int databaseSizeBeforeUpdate = transactionDetailsRepository.findAll().size();

        // Update the transactionDetails
        TransactionDetails updatedTransactionDetails = transactionDetailsRepository.findById(transactionDetails.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionDetails are not directly saved in db
        em.detach(updatedTransactionDetails);
        updatedTransactionDetails
            .txdPrmCode(UPDATED_TXD_PRM_CODE)
            .txdValue(UPDATED_TXD_VALUE);

        restTransactionDetailsMockMvc.perform(put("/api/transaction-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactionDetails)))
            .andExpect(status().isOk());

        // Validate the TransactionDetails in the database
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        TransactionDetails testTransactionDetails = transactionDetailsList.get(transactionDetailsList.size() - 1);
        assertThat(testTransactionDetails.getTxdPrmCode()).isEqualTo(UPDATED_TXD_PRM_CODE);
        assertThat(testTransactionDetails.getTxdValue()).isEqualTo(UPDATED_TXD_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = transactionDetailsRepository.findAll().size();

        // Create the TransactionDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionDetailsMockMvc.perform(put("/api/transaction-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionDetails)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionDetails in the database
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionDetails() throws Exception {
        // Initialize the database
        transactionDetailsService.save(transactionDetails);

        int databaseSizeBeforeDelete = transactionDetailsRepository.findAll().size();

        // Delete the transactionDetails
        restTransactionDetailsMockMvc.perform(delete("/api/transaction-details/{id}", transactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository.findAll();
        assertThat(transactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
