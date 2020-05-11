package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.OrderDocuments;
import com.jhipster.reval.ui.domain.Orders;
import com.jhipster.reval.ui.repository.OrderDocumentsRepository;
import com.jhipster.reval.ui.service.OrderDocumentsService;
import com.jhipster.reval.ui.service.dto.OrderDocumentsCriteria;
import com.jhipster.reval.ui.service.OrderDocumentsQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrderDocumentsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrderDocumentsResourceIT {

    private static final String DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ODO_DOCUMENT_MIME_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ODO_DOCUMENT_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ODO_DOCUMENT_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ODO_EXTERNAL_STORAGE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_ODO_EXTERNAL_STORAGE_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ODO_DOCUMENT_DUE_TO_CD = "AAAAAAAAAA";
    private static final String UPDATED_ODO_DOCUMENT_DUE_TO_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ODO_DOCUMENT_DUE_FROM_CD = "AAAAAAAAAA";
    private static final String UPDATED_ODO_DOCUMENT_DUE_FROM_CD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ODO_DOCUMENT_DUE_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ODO_DOCUMENT_DUE_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ODO_DOCUMENT_DUE_DT = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_ODO_DOCUMENT_RCVD_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ODO_DOCUMENT_RCVD_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ODO_DOCUMENT_RCVD_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ODO_DOCUMENT_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ODO_DOCUMENT_STATUS_CD = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ODO_DOCUMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ODO_DOCUMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ODO_DOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ODO_DOCUMENT_CONTENT_TYPE = "image/png";

    @Autowired
    private OrderDocumentsRepository orderDocumentsRepository;

    @Autowired
    private OrderDocumentsService orderDocumentsService;

    @Autowired
    private OrderDocumentsQueryService orderDocumentsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderDocumentsMockMvc;

    private OrderDocuments orderDocuments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderDocuments createEntity(EntityManager em) {
        OrderDocuments orderDocuments = new OrderDocuments()
            .odoDocumentMimeTypeCd(DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD)
            .odoDocumentTypeCd(DEFAULT_ODO_DOCUMENT_TYPE_CD)
            .odoExternalStorageLink(DEFAULT_ODO_EXTERNAL_STORAGE_LINK)
            .odoExternalStorageFileName(DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME)
            .odoDocumentDueToCd(DEFAULT_ODO_DOCUMENT_DUE_TO_CD)
            .odoDocumentDueFromCd(DEFAULT_ODO_DOCUMENT_DUE_FROM_CD)
            .odoDocumentDueDt(DEFAULT_ODO_DOCUMENT_DUE_DT)
            .odoDocumentRcvdDt(DEFAULT_ODO_DOCUMENT_RCVD_DT)
            .odoDocumentStatusCd(DEFAULT_ODO_DOCUMENT_STATUS_CD)
            .odoDocument(DEFAULT_ODO_DOCUMENT)
            .odoDocumentContentType(DEFAULT_ODO_DOCUMENT_CONTENT_TYPE);
        return orderDocuments;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderDocuments createUpdatedEntity(EntityManager em) {
        OrderDocuments orderDocuments = new OrderDocuments()
            .odoDocumentMimeTypeCd(UPDATED_ODO_DOCUMENT_MIME_TYPE_CD)
            .odoDocumentTypeCd(UPDATED_ODO_DOCUMENT_TYPE_CD)
            .odoExternalStorageLink(UPDATED_ODO_EXTERNAL_STORAGE_LINK)
            .odoExternalStorageFileName(UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME)
            .odoDocumentDueToCd(UPDATED_ODO_DOCUMENT_DUE_TO_CD)
            .odoDocumentDueFromCd(UPDATED_ODO_DOCUMENT_DUE_FROM_CD)
            .odoDocumentDueDt(UPDATED_ODO_DOCUMENT_DUE_DT)
            .odoDocumentRcvdDt(UPDATED_ODO_DOCUMENT_RCVD_DT)
            .odoDocumentStatusCd(UPDATED_ODO_DOCUMENT_STATUS_CD)
            .odoDocument(UPDATED_ODO_DOCUMENT)
            .odoDocumentContentType(UPDATED_ODO_DOCUMENT_CONTENT_TYPE);
        return orderDocuments;
    }

    @BeforeEach
    public void initTest() {
        orderDocuments = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderDocuments() throws Exception {
        int databaseSizeBeforeCreate = orderDocumentsRepository.findAll().size();

        // Create the OrderDocuments
        restOrderDocumentsMockMvc.perform(post("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isCreated());

        // Validate the OrderDocuments in the database
        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeCreate + 1);
        OrderDocuments testOrderDocuments = orderDocumentsList.get(orderDocumentsList.size() - 1);
        assertThat(testOrderDocuments.getOdoDocumentMimeTypeCd()).isEqualTo(DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD);
        assertThat(testOrderDocuments.getOdoDocumentTypeCd()).isEqualTo(DEFAULT_ODO_DOCUMENT_TYPE_CD);
        assertThat(testOrderDocuments.getOdoExternalStorageLink()).isEqualTo(DEFAULT_ODO_EXTERNAL_STORAGE_LINK);
        assertThat(testOrderDocuments.getOdoExternalStorageFileName()).isEqualTo(DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME);
        assertThat(testOrderDocuments.getOdoDocumentDueToCd()).isEqualTo(DEFAULT_ODO_DOCUMENT_DUE_TO_CD);
        assertThat(testOrderDocuments.getOdoDocumentDueFromCd()).isEqualTo(DEFAULT_ODO_DOCUMENT_DUE_FROM_CD);
        assertThat(testOrderDocuments.getOdoDocumentDueDt()).isEqualTo(DEFAULT_ODO_DOCUMENT_DUE_DT);
        assertThat(testOrderDocuments.getOdoDocumentRcvdDt()).isEqualTo(DEFAULT_ODO_DOCUMENT_RCVD_DT);
        assertThat(testOrderDocuments.getOdoDocumentStatusCd()).isEqualTo(DEFAULT_ODO_DOCUMENT_STATUS_CD);
        assertThat(testOrderDocuments.getOdoDocument()).isEqualTo(DEFAULT_ODO_DOCUMENT);
        assertThat(testOrderDocuments.getOdoDocumentContentType()).isEqualTo(DEFAULT_ODO_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createOrderDocumentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderDocumentsRepository.findAll().size();

        // Create the OrderDocuments with an existing ID
        orderDocuments.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderDocumentsMockMvc.perform(post("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isBadRequest());

        // Validate the OrderDocuments in the database
        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOdoDocumentMimeTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDocumentsRepository.findAll().size();
        // set the field null
        orderDocuments.setOdoDocumentMimeTypeCd(null);

        // Create the OrderDocuments, which fails.

        restOrderDocumentsMockMvc.perform(post("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isBadRequest());

        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOdoDocumentTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDocumentsRepository.findAll().size();
        // set the field null
        orderDocuments.setOdoDocumentTypeCd(null);

        // Create the OrderDocuments, which fails.

        restOrderDocumentsMockMvc.perform(post("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isBadRequest());

        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOdoDocumentStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderDocumentsRepository.findAll().size();
        // set the field null
        orderDocuments.setOdoDocumentStatusCd(null);

        // Create the OrderDocuments, which fails.

        restOrderDocumentsMockMvc.perform(post("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isBadRequest());

        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrderDocuments() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList
        restOrderDocumentsMockMvc.perform(get("/api/order-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderDocuments.getId().intValue())))
            .andExpect(jsonPath("$.[*].odoDocumentMimeTypeCd").value(hasItem(DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentTypeCd").value(hasItem(DEFAULT_ODO_DOCUMENT_TYPE_CD)))
            .andExpect(jsonPath("$.[*].odoExternalStorageLink").value(hasItem(DEFAULT_ODO_EXTERNAL_STORAGE_LINK)))
            .andExpect(jsonPath("$.[*].odoExternalStorageFileName").value(hasItem(DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME)))
            .andExpect(jsonPath("$.[*].odoDocumentDueToCd").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_TO_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentDueFromCd").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_FROM_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentDueDt").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_DT.toString())))
            .andExpect(jsonPath("$.[*].odoDocumentRcvdDt").value(hasItem(DEFAULT_ODO_DOCUMENT_RCVD_DT.toString())))
            .andExpect(jsonPath("$.[*].odoDocumentStatusCd").value(hasItem(DEFAULT_ODO_DOCUMENT_STATUS_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentContentType").value(hasItem(DEFAULT_ODO_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].odoDocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_ODO_DOCUMENT))));
    }
    
    @Test
    @Transactional
    public void getOrderDocuments() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get the orderDocuments
        restOrderDocumentsMockMvc.perform(get("/api/order-documents/{id}", orderDocuments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderDocuments.getId().intValue()))
            .andExpect(jsonPath("$.odoDocumentMimeTypeCd").value(DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD))
            .andExpect(jsonPath("$.odoDocumentTypeCd").value(DEFAULT_ODO_DOCUMENT_TYPE_CD))
            .andExpect(jsonPath("$.odoExternalStorageLink").value(DEFAULT_ODO_EXTERNAL_STORAGE_LINK))
            .andExpect(jsonPath("$.odoExternalStorageFileName").value(DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME))
            .andExpect(jsonPath("$.odoDocumentDueToCd").value(DEFAULT_ODO_DOCUMENT_DUE_TO_CD))
            .andExpect(jsonPath("$.odoDocumentDueFromCd").value(DEFAULT_ODO_DOCUMENT_DUE_FROM_CD))
            .andExpect(jsonPath("$.odoDocumentDueDt").value(DEFAULT_ODO_DOCUMENT_DUE_DT.toString()))
            .andExpect(jsonPath("$.odoDocumentRcvdDt").value(DEFAULT_ODO_DOCUMENT_RCVD_DT.toString()))
            .andExpect(jsonPath("$.odoDocumentStatusCd").value(DEFAULT_ODO_DOCUMENT_STATUS_CD))
            .andExpect(jsonPath("$.odoDocumentContentType").value(DEFAULT_ODO_DOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.odoDocument").value(Base64Utils.encodeToString(DEFAULT_ODO_DOCUMENT)));
    }


    @Test
    @Transactional
    public void getOrderDocumentsByIdFiltering() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        Long id = orderDocuments.getId();

        defaultOrderDocumentsShouldBeFound("id.equals=" + id);
        defaultOrderDocumentsShouldNotBeFound("id.notEquals=" + id);

        defaultOrderDocumentsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrderDocumentsShouldNotBeFound("id.greaterThan=" + id);

        defaultOrderDocumentsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrderDocumentsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd equals to DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.equals=" + DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd equals to UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.equals=" + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd not equals to DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.notEquals=" + DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd not equals to UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.notEquals=" + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd in DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD or UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.in=" + DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD + "," + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd equals to UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.in=" + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.specified=true");

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd contains DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.contains=" + DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd contains UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.contains=" + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentMimeTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd does not contain DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentMimeTypeCd.doesNotContain=" + DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentMimeTypeCd does not contain UPDATED_ODO_DOCUMENT_MIME_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentMimeTypeCd.doesNotContain=" + UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd equals to DEFAULT_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.equals=" + DEFAULT_ODO_DOCUMENT_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentTypeCd equals to UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.equals=" + UPDATED_ODO_DOCUMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd not equals to DEFAULT_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.notEquals=" + DEFAULT_ODO_DOCUMENT_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentTypeCd not equals to UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.notEquals=" + UPDATED_ODO_DOCUMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd in DEFAULT_ODO_DOCUMENT_TYPE_CD or UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.in=" + DEFAULT_ODO_DOCUMENT_TYPE_CD + "," + UPDATED_ODO_DOCUMENT_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentTypeCd equals to UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.in=" + UPDATED_ODO_DOCUMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.specified=true");

        // Get all the orderDocumentsList where odoDocumentTypeCd is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd contains DEFAULT_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.contains=" + DEFAULT_ODO_DOCUMENT_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentTypeCd contains UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.contains=" + UPDATED_ODO_DOCUMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentTypeCd does not contain DEFAULT_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentTypeCd.doesNotContain=" + DEFAULT_ODO_DOCUMENT_TYPE_CD);

        // Get all the orderDocumentsList where odoDocumentTypeCd does not contain UPDATED_ODO_DOCUMENT_TYPE_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentTypeCd.doesNotContain=" + UPDATED_ODO_DOCUMENT_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink equals to DEFAULT_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.equals=" + DEFAULT_ODO_EXTERNAL_STORAGE_LINK);

        // Get all the orderDocumentsList where odoExternalStorageLink equals to UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.equals=" + UPDATED_ODO_EXTERNAL_STORAGE_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink not equals to DEFAULT_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.notEquals=" + DEFAULT_ODO_EXTERNAL_STORAGE_LINK);

        // Get all the orderDocumentsList where odoExternalStorageLink not equals to UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.notEquals=" + UPDATED_ODO_EXTERNAL_STORAGE_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink in DEFAULT_ODO_EXTERNAL_STORAGE_LINK or UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.in=" + DEFAULT_ODO_EXTERNAL_STORAGE_LINK + "," + UPDATED_ODO_EXTERNAL_STORAGE_LINK);

        // Get all the orderDocumentsList where odoExternalStorageLink equals to UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.in=" + UPDATED_ODO_EXTERNAL_STORAGE_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink is not null
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.specified=true");

        // Get all the orderDocumentsList where odoExternalStorageLink is null
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink contains DEFAULT_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.contains=" + DEFAULT_ODO_EXTERNAL_STORAGE_LINK);

        // Get all the orderDocumentsList where odoExternalStorageLink contains UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.contains=" + UPDATED_ODO_EXTERNAL_STORAGE_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageLinkNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageLink does not contain DEFAULT_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageLink.doesNotContain=" + DEFAULT_ODO_EXTERNAL_STORAGE_LINK);

        // Get all the orderDocumentsList where odoExternalStorageLink does not contain UPDATED_ODO_EXTERNAL_STORAGE_LINK
        defaultOrderDocumentsShouldBeFound("odoExternalStorageLink.doesNotContain=" + UPDATED_ODO_EXTERNAL_STORAGE_LINK);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName equals to DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.equals=" + DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME);

        // Get all the orderDocumentsList where odoExternalStorageFileName equals to UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.equals=" + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName not equals to DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.notEquals=" + DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME);

        // Get all the orderDocumentsList where odoExternalStorageFileName not equals to UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.notEquals=" + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName in DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME or UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.in=" + DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME + "," + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);

        // Get all the orderDocumentsList where odoExternalStorageFileName equals to UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.in=" + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName is not null
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.specified=true");

        // Get all the orderDocumentsList where odoExternalStorageFileName is null
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName contains DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.contains=" + DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME);

        // Get all the orderDocumentsList where odoExternalStorageFileName contains UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.contains=" + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoExternalStorageFileNameNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoExternalStorageFileName does not contain DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldNotBeFound("odoExternalStorageFileName.doesNotContain=" + DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME);

        // Get all the orderDocumentsList where odoExternalStorageFileName does not contain UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME
        defaultOrderDocumentsShouldBeFound("odoExternalStorageFileName.doesNotContain=" + UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd equals to DEFAULT_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.equals=" + DEFAULT_ODO_DOCUMENT_DUE_TO_CD);

        // Get all the orderDocumentsList where odoDocumentDueToCd equals to UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.equals=" + UPDATED_ODO_DOCUMENT_DUE_TO_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd not equals to DEFAULT_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.notEquals=" + DEFAULT_ODO_DOCUMENT_DUE_TO_CD);

        // Get all the orderDocumentsList where odoDocumentDueToCd not equals to UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.notEquals=" + UPDATED_ODO_DOCUMENT_DUE_TO_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd in DEFAULT_ODO_DOCUMENT_DUE_TO_CD or UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.in=" + DEFAULT_ODO_DOCUMENT_DUE_TO_CD + "," + UPDATED_ODO_DOCUMENT_DUE_TO_CD);

        // Get all the orderDocumentsList where odoDocumentDueToCd equals to UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.in=" + UPDATED_ODO_DOCUMENT_DUE_TO_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.specified=true");

        // Get all the orderDocumentsList where odoDocumentDueToCd is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd contains DEFAULT_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.contains=" + DEFAULT_ODO_DOCUMENT_DUE_TO_CD);

        // Get all the orderDocumentsList where odoDocumentDueToCd contains UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.contains=" + UPDATED_ODO_DOCUMENT_DUE_TO_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueToCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueToCd does not contain DEFAULT_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueToCd.doesNotContain=" + DEFAULT_ODO_DOCUMENT_DUE_TO_CD);

        // Get all the orderDocumentsList where odoDocumentDueToCd does not contain UPDATED_ODO_DOCUMENT_DUE_TO_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueToCd.doesNotContain=" + UPDATED_ODO_DOCUMENT_DUE_TO_CD);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd equals to DEFAULT_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.equals=" + DEFAULT_ODO_DOCUMENT_DUE_FROM_CD);

        // Get all the orderDocumentsList where odoDocumentDueFromCd equals to UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.equals=" + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd not equals to DEFAULT_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.notEquals=" + DEFAULT_ODO_DOCUMENT_DUE_FROM_CD);

        // Get all the orderDocumentsList where odoDocumentDueFromCd not equals to UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.notEquals=" + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd in DEFAULT_ODO_DOCUMENT_DUE_FROM_CD or UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.in=" + DEFAULT_ODO_DOCUMENT_DUE_FROM_CD + "," + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);

        // Get all the orderDocumentsList where odoDocumentDueFromCd equals to UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.in=" + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.specified=true");

        // Get all the orderDocumentsList where odoDocumentDueFromCd is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd contains DEFAULT_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.contains=" + DEFAULT_ODO_DOCUMENT_DUE_FROM_CD);

        // Get all the orderDocumentsList where odoDocumentDueFromCd contains UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.contains=" + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueFromCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueFromCd does not contain DEFAULT_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueFromCd.doesNotContain=" + DEFAULT_ODO_DOCUMENT_DUE_FROM_CD);

        // Get all the orderDocumentsList where odoDocumentDueFromCd does not contain UPDATED_ODO_DOCUMENT_DUE_FROM_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentDueFromCd.doesNotContain=" + UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt equals to DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.equals=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt equals to UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.equals=" + UPDATED_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt not equals to DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.notEquals=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt not equals to UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.notEquals=" + UPDATED_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt in DEFAULT_ODO_DOCUMENT_DUE_DT or UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.in=" + DEFAULT_ODO_DOCUMENT_DUE_DT + "," + UPDATED_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt equals to UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.in=" + UPDATED_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.specified=true");

        // Get all the orderDocumentsList where odoDocumentDueDt is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt is greater than or equal to DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.greaterThanOrEqual=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt is greater than or equal to UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.greaterThanOrEqual=" + UPDATED_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt is less than or equal to DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.lessThanOrEqual=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt is less than or equal to SMALLER_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.lessThanOrEqual=" + SMALLER_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsLessThanSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt is less than DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.lessThan=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt is less than UPDATED_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.lessThan=" + UPDATED_ODO_DOCUMENT_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentDueDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentDueDt is greater than DEFAULT_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentDueDt.greaterThan=" + DEFAULT_ODO_DOCUMENT_DUE_DT);

        // Get all the orderDocumentsList where odoDocumentDueDt is greater than SMALLER_ODO_DOCUMENT_DUE_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentDueDt.greaterThan=" + SMALLER_ODO_DOCUMENT_DUE_DT);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt equals to DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.equals=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt equals to UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.equals=" + UPDATED_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt not equals to DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.notEquals=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt not equals to UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.notEquals=" + UPDATED_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt in DEFAULT_ODO_DOCUMENT_RCVD_DT or UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.in=" + DEFAULT_ODO_DOCUMENT_RCVD_DT + "," + UPDATED_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt equals to UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.in=" + UPDATED_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.specified=true");

        // Get all the orderDocumentsList where odoDocumentRcvdDt is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is greater than or equal to DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.greaterThanOrEqual=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is greater than or equal to UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.greaterThanOrEqual=" + UPDATED_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is less than or equal to DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.lessThanOrEqual=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is less than or equal to SMALLER_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.lessThanOrEqual=" + SMALLER_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsLessThanSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is less than DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.lessThan=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is less than UPDATED_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.lessThan=" + UPDATED_ODO_DOCUMENT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentRcvdDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is greater than DEFAULT_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldNotBeFound("odoDocumentRcvdDt.greaterThan=" + DEFAULT_ODO_DOCUMENT_RCVD_DT);

        // Get all the orderDocumentsList where odoDocumentRcvdDt is greater than SMALLER_ODO_DOCUMENT_RCVD_DT
        defaultOrderDocumentsShouldBeFound("odoDocumentRcvdDt.greaterThan=" + SMALLER_ODO_DOCUMENT_RCVD_DT);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd equals to DEFAULT_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.equals=" + DEFAULT_ODO_DOCUMENT_STATUS_CD);

        // Get all the orderDocumentsList where odoDocumentStatusCd equals to UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.equals=" + UPDATED_ODO_DOCUMENT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd not equals to DEFAULT_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.notEquals=" + DEFAULT_ODO_DOCUMENT_STATUS_CD);

        // Get all the orderDocumentsList where odoDocumentStatusCd not equals to UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.notEquals=" + UPDATED_ODO_DOCUMENT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd in DEFAULT_ODO_DOCUMENT_STATUS_CD or UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.in=" + DEFAULT_ODO_DOCUMENT_STATUS_CD + "," + UPDATED_ODO_DOCUMENT_STATUS_CD);

        // Get all the orderDocumentsList where odoDocumentStatusCd equals to UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.in=" + UPDATED_ODO_DOCUMENT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd is not null
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.specified=true");

        // Get all the orderDocumentsList where odoDocumentStatusCd is null
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd contains DEFAULT_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.contains=" + DEFAULT_ODO_DOCUMENT_STATUS_CD);

        // Get all the orderDocumentsList where odoDocumentStatusCd contains UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.contains=" + UPDATED_ODO_DOCUMENT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrderDocumentsByOdoDocumentStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);

        // Get all the orderDocumentsList where odoDocumentStatusCd does not contain DEFAULT_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldNotBeFound("odoDocumentStatusCd.doesNotContain=" + DEFAULT_ODO_DOCUMENT_STATUS_CD);

        // Get all the orderDocumentsList where odoDocumentStatusCd does not contain UPDATED_ODO_DOCUMENT_STATUS_CD
        defaultOrderDocumentsShouldBeFound("odoDocumentStatusCd.doesNotContain=" + UPDATED_ODO_DOCUMENT_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrderDocumentsByOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        orderDocumentsRepository.saveAndFlush(orderDocuments);
        Orders order = OrdersResourceIT.createEntity(em);
        em.persist(order);
        em.flush();
        orderDocuments.setOrder(order);
        orderDocumentsRepository.saveAndFlush(orderDocuments);
        Long orderId = order.getId();

        // Get all the orderDocumentsList where order equals to orderId
        defaultOrderDocumentsShouldBeFound("orderId.equals=" + orderId);

        // Get all the orderDocumentsList where order equals to orderId + 1
        defaultOrderDocumentsShouldNotBeFound("orderId.equals=" + (orderId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrderDocumentsShouldBeFound(String filter) throws Exception {
        restOrderDocumentsMockMvc.perform(get("/api/order-documents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderDocuments.getId().intValue())))
            .andExpect(jsonPath("$.[*].odoDocumentMimeTypeCd").value(hasItem(DEFAULT_ODO_DOCUMENT_MIME_TYPE_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentTypeCd").value(hasItem(DEFAULT_ODO_DOCUMENT_TYPE_CD)))
            .andExpect(jsonPath("$.[*].odoExternalStorageLink").value(hasItem(DEFAULT_ODO_EXTERNAL_STORAGE_LINK)))
            .andExpect(jsonPath("$.[*].odoExternalStorageFileName").value(hasItem(DEFAULT_ODO_EXTERNAL_STORAGE_FILE_NAME)))
            .andExpect(jsonPath("$.[*].odoDocumentDueToCd").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_TO_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentDueFromCd").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_FROM_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentDueDt").value(hasItem(DEFAULT_ODO_DOCUMENT_DUE_DT.toString())))
            .andExpect(jsonPath("$.[*].odoDocumentRcvdDt").value(hasItem(DEFAULT_ODO_DOCUMENT_RCVD_DT.toString())))
            .andExpect(jsonPath("$.[*].odoDocumentStatusCd").value(hasItem(DEFAULT_ODO_DOCUMENT_STATUS_CD)))
            .andExpect(jsonPath("$.[*].odoDocumentContentType").value(hasItem(DEFAULT_ODO_DOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].odoDocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_ODO_DOCUMENT))));

        // Check, that the count call also returns 1
        restOrderDocumentsMockMvc.perform(get("/api/order-documents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrderDocumentsShouldNotBeFound(String filter) throws Exception {
        restOrderDocumentsMockMvc.perform(get("/api/order-documents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrderDocumentsMockMvc.perform(get("/api/order-documents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrderDocuments() throws Exception {
        // Get the orderDocuments
        restOrderDocumentsMockMvc.perform(get("/api/order-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderDocuments() throws Exception {
        // Initialize the database
        orderDocumentsService.save(orderDocuments);

        int databaseSizeBeforeUpdate = orderDocumentsRepository.findAll().size();

        // Update the orderDocuments
        OrderDocuments updatedOrderDocuments = orderDocumentsRepository.findById(orderDocuments.getId()).get();
        // Disconnect from session so that the updates on updatedOrderDocuments are not directly saved in db
        em.detach(updatedOrderDocuments);
        updatedOrderDocuments
            .odoDocumentMimeTypeCd(UPDATED_ODO_DOCUMENT_MIME_TYPE_CD)
            .odoDocumentTypeCd(UPDATED_ODO_DOCUMENT_TYPE_CD)
            .odoExternalStorageLink(UPDATED_ODO_EXTERNAL_STORAGE_LINK)
            .odoExternalStorageFileName(UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME)
            .odoDocumentDueToCd(UPDATED_ODO_DOCUMENT_DUE_TO_CD)
            .odoDocumentDueFromCd(UPDATED_ODO_DOCUMENT_DUE_FROM_CD)
            .odoDocumentDueDt(UPDATED_ODO_DOCUMENT_DUE_DT)
            .odoDocumentRcvdDt(UPDATED_ODO_DOCUMENT_RCVD_DT)
            .odoDocumentStatusCd(UPDATED_ODO_DOCUMENT_STATUS_CD)
            .odoDocument(UPDATED_ODO_DOCUMENT)
            .odoDocumentContentType(UPDATED_ODO_DOCUMENT_CONTENT_TYPE);

        restOrderDocumentsMockMvc.perform(put("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderDocuments)))
            .andExpect(status().isOk());

        // Validate the OrderDocuments in the database
        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeUpdate);
        OrderDocuments testOrderDocuments = orderDocumentsList.get(orderDocumentsList.size() - 1);
        assertThat(testOrderDocuments.getOdoDocumentMimeTypeCd()).isEqualTo(UPDATED_ODO_DOCUMENT_MIME_TYPE_CD);
        assertThat(testOrderDocuments.getOdoDocumentTypeCd()).isEqualTo(UPDATED_ODO_DOCUMENT_TYPE_CD);
        assertThat(testOrderDocuments.getOdoExternalStorageLink()).isEqualTo(UPDATED_ODO_EXTERNAL_STORAGE_LINK);
        assertThat(testOrderDocuments.getOdoExternalStorageFileName()).isEqualTo(UPDATED_ODO_EXTERNAL_STORAGE_FILE_NAME);
        assertThat(testOrderDocuments.getOdoDocumentDueToCd()).isEqualTo(UPDATED_ODO_DOCUMENT_DUE_TO_CD);
        assertThat(testOrderDocuments.getOdoDocumentDueFromCd()).isEqualTo(UPDATED_ODO_DOCUMENT_DUE_FROM_CD);
        assertThat(testOrderDocuments.getOdoDocumentDueDt()).isEqualTo(UPDATED_ODO_DOCUMENT_DUE_DT);
        assertThat(testOrderDocuments.getOdoDocumentRcvdDt()).isEqualTo(UPDATED_ODO_DOCUMENT_RCVD_DT);
        assertThat(testOrderDocuments.getOdoDocumentStatusCd()).isEqualTo(UPDATED_ODO_DOCUMENT_STATUS_CD);
        assertThat(testOrderDocuments.getOdoDocument()).isEqualTo(UPDATED_ODO_DOCUMENT);
        assertThat(testOrderDocuments.getOdoDocumentContentType()).isEqualTo(UPDATED_ODO_DOCUMENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderDocuments() throws Exception {
        int databaseSizeBeforeUpdate = orderDocumentsRepository.findAll().size();

        // Create the OrderDocuments

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderDocumentsMockMvc.perform(put("/api/order-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDocuments)))
            .andExpect(status().isBadRequest());

        // Validate the OrderDocuments in the database
        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderDocuments() throws Exception {
        // Initialize the database
        orderDocumentsService.save(orderDocuments);

        int databaseSizeBeforeDelete = orderDocumentsRepository.findAll().size();

        // Delete the orderDocuments
        restOrderDocumentsMockMvc.perform(delete("/api/order-documents/{id}", orderDocuments.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderDocuments> orderDocumentsList = orderDocumentsRepository.findAll();
        assertThat(orderDocumentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
