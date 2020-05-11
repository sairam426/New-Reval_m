package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Orders;
import com.jhipster.reval.ui.domain.OrderComments;
import com.jhipster.reval.ui.domain.OrderDocuments;
import com.jhipster.reval.ui.repository.OrdersRepository;
import com.jhipster.reval.ui.service.OrdersService;
import com.jhipster.reval.ui.service.dto.OrdersCriteria;
import com.jhipster.reval.ui.service.OrdersQueryService;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrdersResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrdersResourceIT {

    private static final String DEFAULT_ORD_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORD_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_STAGE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_STAGE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_ORG_ORDER_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_ORG_ORDER_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CMP_ORDER_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CMP_ORDER_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PRO_ORDER_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PRO_ORDER_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CMP_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CMP_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_BRN_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_BRN_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PRO_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PRO_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PRODUCT_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORD_ORIGINAL_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_ORIGINAL_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_ORIGINAL_VALUE_AMT = new BigDecimal(1 - 1);

    private static final String DEFAULT_ORD_PARENT_TRACKING_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PARENT_TRACKING_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_USER_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_USER_NBR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORD_ORDER_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_ORDER_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_ORDER_DT = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_ORD_RUSH_REQUEST_DUE_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_RUSH_REQUEST_DUE_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_RUSH_REQUEST_DUE_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_ORG_INSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_ORD_ORG_INSTRUCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_ORD_ORG_OTHER_INSTRUCTIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_MULTI_ORDER_IND = false;
    private static final Boolean UPDATED_ORD_MULTI_ORDER_IND = true;

    private static final LocalDate DEFAULT_ORD_ORG_DUEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_ORG_DUEDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_ORG_DUEDATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_ORD_PRO_DUEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_PRO_DUEDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_PRO_DUEDATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND = false;
    private static final Boolean UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND = true;

    private static final String DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND = false;
    private static final Boolean UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND = true;

    private static final String DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_UCDP_FINAL_SUBMISSION_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_LOAN_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORDLOAN_OLD_LOAN_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORDLOAN_OLD_LOAN_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_LOAN_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_PROGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_LOAN_PURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_PURPOSE = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_LOAN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORD_LOAN_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_LOAN_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_LOAN_AMT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_LOAN_QUALIFYING_VALUE_AMT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_ORD_LOAN_SALES_PRICE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_LOAN_SALES_PRICE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_LOAN_SALES_PRICE_AMT = new BigDecimal(1 - 1);

    private static final String DEFAULT_ORD_LOAN_BORROWER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_BORROWER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_BORROWER_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND = false;
    private static final Boolean UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND = true;

    private static final String DEFAULT_ORD_LOAN_FHA_CASE_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_FHA_CASE_NBR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND = false;
    private static final Boolean UPDATED_ORD_LOAN_DEED_RESTRICTION_IND = true;

    private static final LocalDate DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_LOAN_ESTIMATED_CLOSE_DT = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_ORD_LOAN_HPML_IND = false;
    private static final Boolean UPDATED_ORD_LOAN_HPML_IND = true;

    private static final Boolean DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND = false;
    private static final Boolean UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND = true;

    private static final String DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND = false;
    private static final Boolean UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND = true;

    private static final LocalDate DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_PROPERTY_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_STATE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_LATITUE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_LATITUE = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_LONGITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_PROPERTY_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_PROPERTY_PROJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_1_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_1_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_1_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_1_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORD_CONTACT_1_WORK_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_1_WORK_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_1_WORK_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_1_HOME_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_1_HOME_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_1_HOME_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_1_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_1_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_1_CELL_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_1_OTHER_CELL_PHONE = 1L - 1L;

    private static final String DEFAULT_ORD_CONTACT_1_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_1_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_1_OTHER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_1_OTHER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_2_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_2_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_2_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_2_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORD_CONTACT_2_WORK_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_2_WORK_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_2_WORK_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_2_HOME_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_2_HOME_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_2_HOME_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_2_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_2_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_2_CELL_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_2_OTHER_CELL_PHONE = 1L - 1L;

    private static final String DEFAULT_ORD_CONTACT_2_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_2_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_2_OTHER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_2_OTHER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_3_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_3_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_3_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_3_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ORD_CONTACT_3_WORK_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_3_WORK_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_3_WORK_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_3_HOME_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_3_HOME_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_3_HOME_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_3_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_3_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_3_CELL_PHONE = 1L - 1L;

    private static final Long DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE = 1L;
    private static final Long UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE = 2L;
    private static final Long SMALLER_ORD_CONTACT_3_OTHER_CELL_PHONE = 1L - 1L;

    private static final String DEFAULT_ORD_CONTACT_3_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_3_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_CONTACT_3_OTHER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ORD_CONTACT_3_OTHER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RR_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_REQUEST_RR_XML_FORM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_XML_FORM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_PDF_FORM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_REQUEST_RR_REPORT_VALUE_AMT = new BigDecimal(1 - 1);

    private static final String DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RRVENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RRVENDOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_ORD_VALUATION_COMPLETED_PRODUCT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_VALUATION_DUE_TO_CLIENT_DT = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_VALUATION_REPORT_RECIVED_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_VALUATION_REPORT_RECIVED_DT = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_ORD_VALUATION_COMPLETION_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_VALUATION_COMPLETION_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_VALUATION_COMPLETION_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_VALUATION_REPORT_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_VALUATION_REPORT_STATUS_CD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_VALUATION_APPRAISED_VALUE_AMT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_VALUATION_BPO_MARKET_VALUE_AMT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_VALUATION_BPO_AS_IS_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_VALUATION_BPO_AS_IS_AMT = new BigDecimal(1 - 1);

    private static final BigDecimal DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT = new BigDecimal(1 - 1);

    private static final String DEFAULT_ORD_VALUATION_RED_FLAG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_VALUATION_RED_FLAG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_VALUATION_RED_FLAG_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ORD_VALUATION_RED_FLAG_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID = 1L;
    private static final Long UPDATED_ORD_VALUATION_FINAL_REVIEW_ID = 2L;
    private static final Long SMALLER_ORD_VALUATION_FINAL_REVIEW_ID = 1L - 1L;

    private static final BigDecimal DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_REVIEW_RECOMMENDED_VALUE_AMT = new BigDecimal(1 - 1);

    private static final LocalDate DEFAULT_ORD_REVIEW_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORD_REVIEW_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ORD_REVIEW_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REVIEW_REVIEW_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REVIEW_REVIEW_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_ORD_REVIEW_FORM_USED = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REVIEW_FORM_USED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND = false;
    private static final Boolean UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND = true;

    private static final BigDecimal DEFAULT_ORD_REVIEW_CURED_VALUE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORD_REVIEW_CURED_VALUE_AMT = new BigDecimal(2);
    private static final BigDecimal SMALLER_ORD_REVIEW_CURED_VALUE_AMT = new BigDecimal(1 - 1);

    private static final String DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD = "AAAAAAAAAA";
    private static final String UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD = "BBBBBBBBBB";

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersQueryService ordersQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrdersMockMvc;

    private Orders orders;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orders createEntity(EntityManager em) {
        Orders orders = new Orders()
            .ordNumber(DEFAULT_ORD_NUMBER)
            .ordStageCd(DEFAULT_ORD_STAGE_CD)
            .ordOrgOrderStatusCd(DEFAULT_ORD_ORG_ORDER_STATUS_CD)
            .ordCmpOrderStatusCd(DEFAULT_ORD_CMP_ORDER_STATUS_CD)
            .ordProOrderStatusCd(DEFAULT_ORD_PRO_ORDER_STATUS_CD)
            .ordCmpNbr(DEFAULT_ORD_CMP_NBR)
            .ordBrnNbr(DEFAULT_ORD_BRN_NBR)
            .ordProNbr(DEFAULT_ORD_PRO_NBR)
            .ordProductCode(DEFAULT_ORD_PRODUCT_CODE)
            .ordOriginalValueAmt(DEFAULT_ORD_ORIGINAL_VALUE_AMT)
            .ordParentTrackingNbr(DEFAULT_ORD_PARENT_TRACKING_NBR)
            .ordUserNbr(DEFAULT_ORD_USER_NBR)
            .ordOrderDt(DEFAULT_ORD_ORDER_DT)
            .ordRushRequestDueDt(DEFAULT_ORD_RUSH_REQUEST_DUE_DT)
            .ordOrgInstructions(DEFAULT_ORD_ORG_INSTRUCTIONS)
            .ordOrgOtherInstructions(DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS)
            .ordMultiOrderInd(DEFAULT_ORD_MULTI_ORDER_IND)
            .ordOrgDuedate(DEFAULT_ORD_ORG_DUEDATE)
            .ordProDuedate(DEFAULT_ORD_PRO_DUEDATE)
            .ordUcdpDataFnmaSubmitToUcdpInd(DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFnmaServicerNbr(DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR)
            .ordUcdpDataFhlmcSubmitToUcdpInd(DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFhlmcIdentificationNbr(DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR)
            .ordUcdpDataUcdpBussinessUnitNbr(DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR)
            .ordUcdpSdFnamaDocumentId(DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID)
            .ordUcdpSdFnmaSubmissionStatusId(DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID)
            .ordUcdpSdFhlmcDocumentFileCd(DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD)
            .ordUcdpSdEadSubmissionStatusCd(DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD)
            .ordUcdpFinalSubmissionDt(DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT)
            .ordLoanNbr(DEFAULT_ORD_LOAN_NBR)
            .ordloanOldLoanNbr(DEFAULT_ORDLOAN_OLD_LOAN_NBR)
            .ordLoanProgram(DEFAULT_ORD_LOAN_PROGRAM)
            .ordLoanPurpose(DEFAULT_ORD_LOAN_PURPOSE)
            .ordLoanType(DEFAULT_ORD_LOAN_TYPE)
            .ordLoanAmt(DEFAULT_ORD_LOAN_AMT)
            .ordLoanQualifyingValueAmt(DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT)
            .ordLoanSalesPriceAmt(DEFAULT_ORD_LOAN_SALES_PRICE_AMT)
            .ordLoanBorrowerLastName(DEFAULT_ORD_LOAN_BORROWER_LAST_NAME)
            .ordLoanBorrowerFirstName(DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME)
            .ordLoanBorrowerMiddleName(DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME)
            .ordLoanBorrowerIsCoBorrowerInd(DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND)
            .ordLoanFhaCaseNbr(DEFAULT_ORD_LOAN_FHA_CASE_NBR)
            .ordLoanDeedRestrictionInd(DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND)
            .ordLoanEstimatedCloseDt(DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT)
            .ordLoanHpmlInd(DEFAULT_ORD_LOAN_HPML_IND)
            .ordLoanIsNewConstructionInd(DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND)
            .ordLoanCustomerSegmentCode(DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE)
            .ordLoanNonSubjectPropertyInd(DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND)
            .ordLoanBorrowerRequestedCloseDt(DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT)
            .ordPropertyTypeCd(DEFAULT_ORD_PROPERTY_TYPE_CD)
            .ordPropertyAddress1(DEFAULT_ORD_PROPERTY_ADDRESS_1)
            .ordPropertyAddress2(DEFAULT_ORD_PROPERTY_ADDRESS_2)
            .ordPropertyCity(DEFAULT_ORD_PROPERTY_CITY)
            .ordPropertyStateCd(DEFAULT_ORD_PROPERTY_STATE_CD)
            .ordPropertyZip(DEFAULT_ORD_PROPERTY_ZIP)
            .ordPropertyCounty(DEFAULT_ORD_PROPERTY_COUNTY)
            .ordPropertyLatitue(DEFAULT_ORD_PROPERTY_LATITUE)
            .ordPropertyLongitude(DEFAULT_ORD_PROPERTY_LONGITUDE)
            .ordPropertyProjectName(DEFAULT_ORD_PROPERTY_PROJECT_NAME)
            .ordContact1TypeCd(DEFAULT_ORD_CONTACT_1_TYPE_CD)
            .ordContact1Name(DEFAULT_ORD_CONTACT_1_NAME)
            .ordContact1WorkPhone(DEFAULT_ORD_CONTACT_1_WORK_PHONE)
            .ordContact1HomePhone(DEFAULT_ORD_CONTACT_1_HOME_PHONE)
            .ordContact1CellPhone(DEFAULT_ORD_CONTACT_1_CELL_PHONE)
            .ordContact1OtherCellPhone(DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE)
            .ordContact1Email(DEFAULT_ORD_CONTACT_1_EMAIL)
            .ordContact1OtherEmail(DEFAULT_ORD_CONTACT_1_OTHER_EMAIL)
            .ordContact2TypeCd(DEFAULT_ORD_CONTACT_2_TYPE_CD)
            .ordContact2Name(DEFAULT_ORD_CONTACT_2_NAME)
            .ordContact2WorkPhone(DEFAULT_ORD_CONTACT_2_WORK_PHONE)
            .ordContact2HomePhone(DEFAULT_ORD_CONTACT_2_HOME_PHONE)
            .ordContact2CellPhone(DEFAULT_ORD_CONTACT_2_CELL_PHONE)
            .ordContact2OtherCellPhone(DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE)
            .ordContact2Email(DEFAULT_ORD_CONTACT_2_EMAIL)
            .ordContact2OtherEmail(DEFAULT_ORD_CONTACT_2_OTHER_EMAIL)
            .ordContact3TypeCd(DEFAULT_ORD_CONTACT_3_TYPE_CD)
            .ordContact3Name(DEFAULT_ORD_CONTACT_3_NAME)
            .ordContact3WorkPhone(DEFAULT_ORD_CONTACT_3_WORK_PHONE)
            .ordContact3HomePhone(DEFAULT_ORD_CONTACT_3_HOME_PHONE)
            .ordContact3CellPhone(DEFAULT_ORD_CONTACT_3_CELL_PHONE)
            .ordContact3OtherCellPhone(DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE)
            .ordContact3Email(DEFAULT_ORD_CONTACT_3_EMAIL)
            .ordContact3OtherEmail(DEFAULT_ORD_CONTACT_3_OTHER_EMAIL)
            .ordRequestRrStatusCd(DEFAULT_ORD_REQUEST_RR_STATUS_CD)
            .ordRequestRrVendorAppraisalId(DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID)
            .ordRequestRrvendorAppraisalDraftRcvdDt(DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT)
            .ordRequestRrXmlFormId(DEFAULT_ORD_REQUEST_RR_XML_FORM_ID)
            .ordRequestRrPdfFormId(DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID)
            .ordRequestRrApprFileDataSourcCd(DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD)
            .ordRequestRrReportValueAmt(DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT)
            .ordRequestRrAppraisalCompanyName(DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME)
            .ordRequestRrvendorName(DEFAULT_ORD_REQUEST_RRVENDOR_NAME)
            .ordRequestRrvendorLicenseNbr(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR)
            .ordRequestRrvendorLicenseStateCd(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD)
            .ordValuationCompletedProduct(DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT)
            .ordValuationDueToClientDt(DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT)
            .ordValuationReportRecivedDt(DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT)
            .ordValuationCompletionDt(DEFAULT_ORD_VALUATION_COMPLETION_DT)
            .ordValuationReportStatusCd(DEFAULT_ORD_VALUATION_REPORT_STATUS_CD)
            .ordValuationAppraisedValueAmt(DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT)
            .ordValuationBpoMarketValueAmt(DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT)
            .ordValuationBpoAsIsAmt(DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT)
            .ordValuationBpoAsRepairedValueAmt(DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT)
            .ordValuationRedFlagCode(DEFAULT_ORD_VALUATION_RED_FLAG_CODE)
            .ordValuationRedFlagDesc(DEFAULT_ORD_VALUATION_RED_FLAG_DESC)
            .ordValuationAmcAppraisalId(DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID)
            .ordValuationFinalReviewId(DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID)
            .ordReviewRecommendedValueAmt(DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT)
            .ordReviewDt(DEFAULT_ORD_REVIEW_DT)
            .ordReviewRecommendedActionCd(DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD)
            .ordReviewReviewerDecisionCd(DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD)
            .ordReviewReviewScore(DEFAULT_ORD_REVIEW_REVIEW_SCORE)
            .ordReviewFormUsed(DEFAULT_ORD_REVIEW_FORM_USED)
            .ordReviewProviderOnWatchListInd(DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND)
            .ordReviewCuredValueAmt(DEFAULT_ORD_REVIEW_CURED_VALUE_AMT)
            .ordReviewFinalRecommendedActionCd(DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
        return orders;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orders createUpdatedEntity(EntityManager em) {
        Orders orders = new Orders()
            .ordNumber(UPDATED_ORD_NUMBER)
            .ordStageCd(UPDATED_ORD_STAGE_CD)
            .ordOrgOrderStatusCd(UPDATED_ORD_ORG_ORDER_STATUS_CD)
            .ordCmpOrderStatusCd(UPDATED_ORD_CMP_ORDER_STATUS_CD)
            .ordProOrderStatusCd(UPDATED_ORD_PRO_ORDER_STATUS_CD)
            .ordCmpNbr(UPDATED_ORD_CMP_NBR)
            .ordBrnNbr(UPDATED_ORD_BRN_NBR)
            .ordProNbr(UPDATED_ORD_PRO_NBR)
            .ordProductCode(UPDATED_ORD_PRODUCT_CODE)
            .ordOriginalValueAmt(UPDATED_ORD_ORIGINAL_VALUE_AMT)
            .ordParentTrackingNbr(UPDATED_ORD_PARENT_TRACKING_NBR)
            .ordUserNbr(UPDATED_ORD_USER_NBR)
            .ordOrderDt(UPDATED_ORD_ORDER_DT)
            .ordRushRequestDueDt(UPDATED_ORD_RUSH_REQUEST_DUE_DT)
            .ordOrgInstructions(UPDATED_ORD_ORG_INSTRUCTIONS)
            .ordOrgOtherInstructions(UPDATED_ORD_ORG_OTHER_INSTRUCTIONS)
            .ordMultiOrderInd(UPDATED_ORD_MULTI_ORDER_IND)
            .ordOrgDuedate(UPDATED_ORD_ORG_DUEDATE)
            .ordProDuedate(UPDATED_ORD_PRO_DUEDATE)
            .ordUcdpDataFnmaSubmitToUcdpInd(UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFnmaServicerNbr(UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR)
            .ordUcdpDataFhlmcSubmitToUcdpInd(UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFhlmcIdentificationNbr(UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR)
            .ordUcdpDataUcdpBussinessUnitNbr(UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR)
            .ordUcdpSdFnamaDocumentId(UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID)
            .ordUcdpSdFnmaSubmissionStatusId(UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID)
            .ordUcdpSdFhlmcDocumentFileCd(UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD)
            .ordUcdpSdEadSubmissionStatusCd(UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD)
            .ordUcdpFinalSubmissionDt(UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT)
            .ordLoanNbr(UPDATED_ORD_LOAN_NBR)
            .ordloanOldLoanNbr(UPDATED_ORDLOAN_OLD_LOAN_NBR)
            .ordLoanProgram(UPDATED_ORD_LOAN_PROGRAM)
            .ordLoanPurpose(UPDATED_ORD_LOAN_PURPOSE)
            .ordLoanType(UPDATED_ORD_LOAN_TYPE)
            .ordLoanAmt(UPDATED_ORD_LOAN_AMT)
            .ordLoanQualifyingValueAmt(UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT)
            .ordLoanSalesPriceAmt(UPDATED_ORD_LOAN_SALES_PRICE_AMT)
            .ordLoanBorrowerLastName(UPDATED_ORD_LOAN_BORROWER_LAST_NAME)
            .ordLoanBorrowerFirstName(UPDATED_ORD_LOAN_BORROWER_FIRST_NAME)
            .ordLoanBorrowerMiddleName(UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME)
            .ordLoanBorrowerIsCoBorrowerInd(UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND)
            .ordLoanFhaCaseNbr(UPDATED_ORD_LOAN_FHA_CASE_NBR)
            .ordLoanDeedRestrictionInd(UPDATED_ORD_LOAN_DEED_RESTRICTION_IND)
            .ordLoanEstimatedCloseDt(UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT)
            .ordLoanHpmlInd(UPDATED_ORD_LOAN_HPML_IND)
            .ordLoanIsNewConstructionInd(UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND)
            .ordLoanCustomerSegmentCode(UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE)
            .ordLoanNonSubjectPropertyInd(UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND)
            .ordLoanBorrowerRequestedCloseDt(UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT)
            .ordPropertyTypeCd(UPDATED_ORD_PROPERTY_TYPE_CD)
            .ordPropertyAddress1(UPDATED_ORD_PROPERTY_ADDRESS_1)
            .ordPropertyAddress2(UPDATED_ORD_PROPERTY_ADDRESS_2)
            .ordPropertyCity(UPDATED_ORD_PROPERTY_CITY)
            .ordPropertyStateCd(UPDATED_ORD_PROPERTY_STATE_CD)
            .ordPropertyZip(UPDATED_ORD_PROPERTY_ZIP)
            .ordPropertyCounty(UPDATED_ORD_PROPERTY_COUNTY)
            .ordPropertyLatitue(UPDATED_ORD_PROPERTY_LATITUE)
            .ordPropertyLongitude(UPDATED_ORD_PROPERTY_LONGITUDE)
            .ordPropertyProjectName(UPDATED_ORD_PROPERTY_PROJECT_NAME)
            .ordContact1TypeCd(UPDATED_ORD_CONTACT_1_TYPE_CD)
            .ordContact1Name(UPDATED_ORD_CONTACT_1_NAME)
            .ordContact1WorkPhone(UPDATED_ORD_CONTACT_1_WORK_PHONE)
            .ordContact1HomePhone(UPDATED_ORD_CONTACT_1_HOME_PHONE)
            .ordContact1CellPhone(UPDATED_ORD_CONTACT_1_CELL_PHONE)
            .ordContact1OtherCellPhone(UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE)
            .ordContact1Email(UPDATED_ORD_CONTACT_1_EMAIL)
            .ordContact1OtherEmail(UPDATED_ORD_CONTACT_1_OTHER_EMAIL)
            .ordContact2TypeCd(UPDATED_ORD_CONTACT_2_TYPE_CD)
            .ordContact2Name(UPDATED_ORD_CONTACT_2_NAME)
            .ordContact2WorkPhone(UPDATED_ORD_CONTACT_2_WORK_PHONE)
            .ordContact2HomePhone(UPDATED_ORD_CONTACT_2_HOME_PHONE)
            .ordContact2CellPhone(UPDATED_ORD_CONTACT_2_CELL_PHONE)
            .ordContact2OtherCellPhone(UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE)
            .ordContact2Email(UPDATED_ORD_CONTACT_2_EMAIL)
            .ordContact2OtherEmail(UPDATED_ORD_CONTACT_2_OTHER_EMAIL)
            .ordContact3TypeCd(UPDATED_ORD_CONTACT_3_TYPE_CD)
            .ordContact3Name(UPDATED_ORD_CONTACT_3_NAME)
            .ordContact3WorkPhone(UPDATED_ORD_CONTACT_3_WORK_PHONE)
            .ordContact3HomePhone(UPDATED_ORD_CONTACT_3_HOME_PHONE)
            .ordContact3CellPhone(UPDATED_ORD_CONTACT_3_CELL_PHONE)
            .ordContact3OtherCellPhone(UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE)
            .ordContact3Email(UPDATED_ORD_CONTACT_3_EMAIL)
            .ordContact3OtherEmail(UPDATED_ORD_CONTACT_3_OTHER_EMAIL)
            .ordRequestRrStatusCd(UPDATED_ORD_REQUEST_RR_STATUS_CD)
            .ordRequestRrVendorAppraisalId(UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID)
            .ordRequestRrvendorAppraisalDraftRcvdDt(UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT)
            .ordRequestRrXmlFormId(UPDATED_ORD_REQUEST_RR_XML_FORM_ID)
            .ordRequestRrPdfFormId(UPDATED_ORD_REQUEST_RR_PDF_FORM_ID)
            .ordRequestRrApprFileDataSourcCd(UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD)
            .ordRequestRrReportValueAmt(UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT)
            .ordRequestRrAppraisalCompanyName(UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME)
            .ordRequestRrvendorName(UPDATED_ORD_REQUEST_RRVENDOR_NAME)
            .ordRequestRrvendorLicenseNbr(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR)
            .ordRequestRrvendorLicenseStateCd(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD)
            .ordValuationCompletedProduct(UPDATED_ORD_VALUATION_COMPLETED_PRODUCT)
            .ordValuationDueToClientDt(UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT)
            .ordValuationReportRecivedDt(UPDATED_ORD_VALUATION_REPORT_RECIVED_DT)
            .ordValuationCompletionDt(UPDATED_ORD_VALUATION_COMPLETION_DT)
            .ordValuationReportStatusCd(UPDATED_ORD_VALUATION_REPORT_STATUS_CD)
            .ordValuationAppraisedValueAmt(UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT)
            .ordValuationBpoMarketValueAmt(UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT)
            .ordValuationBpoAsIsAmt(UPDATED_ORD_VALUATION_BPO_AS_IS_AMT)
            .ordValuationBpoAsRepairedValueAmt(UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT)
            .ordValuationRedFlagCode(UPDATED_ORD_VALUATION_RED_FLAG_CODE)
            .ordValuationRedFlagDesc(UPDATED_ORD_VALUATION_RED_FLAG_DESC)
            .ordValuationAmcAppraisalId(UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID)
            .ordValuationFinalReviewId(UPDATED_ORD_VALUATION_FINAL_REVIEW_ID)
            .ordReviewRecommendedValueAmt(UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT)
            .ordReviewDt(UPDATED_ORD_REVIEW_DT)
            .ordReviewRecommendedActionCd(UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD)
            .ordReviewReviewerDecisionCd(UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD)
            .ordReviewReviewScore(UPDATED_ORD_REVIEW_REVIEW_SCORE)
            .ordReviewFormUsed(UPDATED_ORD_REVIEW_FORM_USED)
            .ordReviewProviderOnWatchListInd(UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND)
            .ordReviewCuredValueAmt(UPDATED_ORD_REVIEW_CURED_VALUE_AMT)
            .ordReviewFinalRecommendedActionCd(UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
        return orders;
    }

    @BeforeEach
    public void initTest() {
        orders = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrders() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isCreated());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate + 1);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getOrdNumber()).isEqualTo(DEFAULT_ORD_NUMBER);
        assertThat(testOrders.getOrdStageCd()).isEqualTo(DEFAULT_ORD_STAGE_CD);
        assertThat(testOrders.getOrdOrgOrderStatusCd()).isEqualTo(DEFAULT_ORD_ORG_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdCmpOrderStatusCd()).isEqualTo(DEFAULT_ORD_CMP_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdProOrderStatusCd()).isEqualTo(DEFAULT_ORD_PRO_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdCmpNbr()).isEqualTo(DEFAULT_ORD_CMP_NBR);
        assertThat(testOrders.getOrdBrnNbr()).isEqualTo(DEFAULT_ORD_BRN_NBR);
        assertThat(testOrders.getOrdProNbr()).isEqualTo(DEFAULT_ORD_PRO_NBR);
        assertThat(testOrders.getOrdProductCode()).isEqualTo(DEFAULT_ORD_PRODUCT_CODE);
        assertThat(testOrders.getOrdOriginalValueAmt()).isEqualTo(DEFAULT_ORD_ORIGINAL_VALUE_AMT);
        assertThat(testOrders.getOrdParentTrackingNbr()).isEqualTo(DEFAULT_ORD_PARENT_TRACKING_NBR);
        assertThat(testOrders.getOrdUserNbr()).isEqualTo(DEFAULT_ORD_USER_NBR);
        assertThat(testOrders.getOrdOrderDt()).isEqualTo(DEFAULT_ORD_ORDER_DT);
        assertThat(testOrders.getOrdRushRequestDueDt()).isEqualTo(DEFAULT_ORD_RUSH_REQUEST_DUE_DT);
        assertThat(testOrders.getOrdOrgInstructions()).isEqualTo(DEFAULT_ORD_ORG_INSTRUCTIONS);
        assertThat(testOrders.getOrdOrgOtherInstructions()).isEqualTo(DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS);
        assertThat(testOrders.isOrdMultiOrderInd()).isEqualTo(DEFAULT_ORD_MULTI_ORDER_IND);
        assertThat(testOrders.getOrdOrgDuedate()).isEqualTo(DEFAULT_ORD_ORG_DUEDATE);
        assertThat(testOrders.getOrdProDuedate()).isEqualTo(DEFAULT_ORD_PRO_DUEDATE);
        assertThat(testOrders.isOrdUcdpDataFnmaSubmitToUcdpInd()).isEqualTo(DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);
        assertThat(testOrders.getOrdUcdpDataFnmaServicerNbr()).isEqualTo(DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
        assertThat(testOrders.isOrdUcdpDataFhlmcSubmitToUcdpInd()).isEqualTo(DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);
        assertThat(testOrders.getOrdUcdpDataFhlmcIdentificationNbr()).isEqualTo(DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
        assertThat(testOrders.getOrdUcdpDataUcdpBussinessUnitNbr()).isEqualTo(DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
        assertThat(testOrders.getOrdUcdpSdFnamaDocumentId()).isEqualTo(DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
        assertThat(testOrders.getOrdUcdpSdFnmaSubmissionStatusId()).isEqualTo(DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
        assertThat(testOrders.getOrdUcdpSdFhlmcDocumentFileCd()).isEqualTo(DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
        assertThat(testOrders.getOrdUcdpSdEadSubmissionStatusCd()).isEqualTo(DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
        assertThat(testOrders.getOrdUcdpFinalSubmissionDt()).isEqualTo(DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);
        assertThat(testOrders.getOrdLoanNbr()).isEqualTo(DEFAULT_ORD_LOAN_NBR);
        assertThat(testOrders.getOrdloanOldLoanNbr()).isEqualTo(DEFAULT_ORDLOAN_OLD_LOAN_NBR);
        assertThat(testOrders.getOrdLoanProgram()).isEqualTo(DEFAULT_ORD_LOAN_PROGRAM);
        assertThat(testOrders.getOrdLoanPurpose()).isEqualTo(DEFAULT_ORD_LOAN_PURPOSE);
        assertThat(testOrders.getOrdLoanType()).isEqualTo(DEFAULT_ORD_LOAN_TYPE);
        assertThat(testOrders.getOrdLoanAmt()).isEqualTo(DEFAULT_ORD_LOAN_AMT);
        assertThat(testOrders.getOrdLoanQualifyingValueAmt()).isEqualTo(DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);
        assertThat(testOrders.getOrdLoanSalesPriceAmt()).isEqualTo(DEFAULT_ORD_LOAN_SALES_PRICE_AMT);
        assertThat(testOrders.getOrdLoanBorrowerLastName()).isEqualTo(DEFAULT_ORD_LOAN_BORROWER_LAST_NAME);
        assertThat(testOrders.getOrdLoanBorrowerFirstName()).isEqualTo(DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME);
        assertThat(testOrders.getOrdLoanBorrowerMiddleName()).isEqualTo(DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME);
        assertThat(testOrders.isOrdLoanBorrowerIsCoBorrowerInd()).isEqualTo(DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);
        assertThat(testOrders.getOrdLoanFhaCaseNbr()).isEqualTo(DEFAULT_ORD_LOAN_FHA_CASE_NBR);
        assertThat(testOrders.isOrdLoanDeedRestrictionInd()).isEqualTo(DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND);
        assertThat(testOrders.getOrdLoanEstimatedCloseDt()).isEqualTo(DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);
        assertThat(testOrders.isOrdLoanHpmlInd()).isEqualTo(DEFAULT_ORD_LOAN_HPML_IND);
        assertThat(testOrders.isOrdLoanIsNewConstructionInd()).isEqualTo(DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);
        assertThat(testOrders.getOrdLoanCustomerSegmentCode()).isEqualTo(DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
        assertThat(testOrders.isOrdLoanNonSubjectPropertyInd()).isEqualTo(DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);
        assertThat(testOrders.getOrdLoanBorrowerRequestedCloseDt()).isEqualTo(DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
        assertThat(testOrders.getOrdPropertyTypeCd()).isEqualTo(DEFAULT_ORD_PROPERTY_TYPE_CD);
        assertThat(testOrders.getOrdPropertyAddress1()).isEqualTo(DEFAULT_ORD_PROPERTY_ADDRESS_1);
        assertThat(testOrders.getOrdPropertyAddress2()).isEqualTo(DEFAULT_ORD_PROPERTY_ADDRESS_2);
        assertThat(testOrders.getOrdPropertyCity()).isEqualTo(DEFAULT_ORD_PROPERTY_CITY);
        assertThat(testOrders.getOrdPropertyStateCd()).isEqualTo(DEFAULT_ORD_PROPERTY_STATE_CD);
        assertThat(testOrders.getOrdPropertyZip()).isEqualTo(DEFAULT_ORD_PROPERTY_ZIP);
        assertThat(testOrders.getOrdPropertyCounty()).isEqualTo(DEFAULT_ORD_PROPERTY_COUNTY);
        assertThat(testOrders.getOrdPropertyLatitue()).isEqualTo(DEFAULT_ORD_PROPERTY_LATITUE);
        assertThat(testOrders.getOrdPropertyLongitude()).isEqualTo(DEFAULT_ORD_PROPERTY_LONGITUDE);
        assertThat(testOrders.getOrdPropertyProjectName()).isEqualTo(DEFAULT_ORD_PROPERTY_PROJECT_NAME);
        assertThat(testOrders.getOrdContact1TypeCd()).isEqualTo(DEFAULT_ORD_CONTACT_1_TYPE_CD);
        assertThat(testOrders.getOrdContact1Name()).isEqualTo(DEFAULT_ORD_CONTACT_1_NAME);
        assertThat(testOrders.getOrdContact1WorkPhone()).isEqualTo(DEFAULT_ORD_CONTACT_1_WORK_PHONE);
        assertThat(testOrders.getOrdContact1HomePhone()).isEqualTo(DEFAULT_ORD_CONTACT_1_HOME_PHONE);
        assertThat(testOrders.getOrdContact1CellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_1_CELL_PHONE);
        assertThat(testOrders.getOrdContact1OtherCellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact1Email()).isEqualTo(DEFAULT_ORD_CONTACT_1_EMAIL);
        assertThat(testOrders.getOrdContact1OtherEmail()).isEqualTo(DEFAULT_ORD_CONTACT_1_OTHER_EMAIL);
        assertThat(testOrders.getOrdContact2TypeCd()).isEqualTo(DEFAULT_ORD_CONTACT_2_TYPE_CD);
        assertThat(testOrders.getOrdContact2Name()).isEqualTo(DEFAULT_ORD_CONTACT_2_NAME);
        assertThat(testOrders.getOrdContact2WorkPhone()).isEqualTo(DEFAULT_ORD_CONTACT_2_WORK_PHONE);
        assertThat(testOrders.getOrdContact2HomePhone()).isEqualTo(DEFAULT_ORD_CONTACT_2_HOME_PHONE);
        assertThat(testOrders.getOrdContact2CellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_2_CELL_PHONE);
        assertThat(testOrders.getOrdContact2OtherCellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact2Email()).isEqualTo(DEFAULT_ORD_CONTACT_2_EMAIL);
        assertThat(testOrders.getOrdContact2OtherEmail()).isEqualTo(DEFAULT_ORD_CONTACT_2_OTHER_EMAIL);
        assertThat(testOrders.getOrdContact3TypeCd()).isEqualTo(DEFAULT_ORD_CONTACT_3_TYPE_CD);
        assertThat(testOrders.getOrdContact3Name()).isEqualTo(DEFAULT_ORD_CONTACT_3_NAME);
        assertThat(testOrders.getOrdContact3WorkPhone()).isEqualTo(DEFAULT_ORD_CONTACT_3_WORK_PHONE);
        assertThat(testOrders.getOrdContact3HomePhone()).isEqualTo(DEFAULT_ORD_CONTACT_3_HOME_PHONE);
        assertThat(testOrders.getOrdContact3CellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_3_CELL_PHONE);
        assertThat(testOrders.getOrdContact3OtherCellPhone()).isEqualTo(DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact3Email()).isEqualTo(DEFAULT_ORD_CONTACT_3_EMAIL);
        assertThat(testOrders.getOrdContact3OtherEmail()).isEqualTo(DEFAULT_ORD_CONTACT_3_OTHER_EMAIL);
        assertThat(testOrders.getOrdRequestRrStatusCd()).isEqualTo(DEFAULT_ORD_REQUEST_RR_STATUS_CD);
        assertThat(testOrders.getOrdRequestRrVendorAppraisalId()).isEqualTo(DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
        assertThat(testOrders.getOrdRequestRrvendorAppraisalDraftRcvdDt()).isEqualTo(DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
        assertThat(testOrders.getOrdRequestRrXmlFormId()).isEqualTo(DEFAULT_ORD_REQUEST_RR_XML_FORM_ID);
        assertThat(testOrders.getOrdRequestRrPdfFormId()).isEqualTo(DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID);
        assertThat(testOrders.getOrdRequestRrApprFileDataSourcCd()).isEqualTo(DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
        assertThat(testOrders.getOrdRequestRrReportValueAmt()).isEqualTo(DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);
        assertThat(testOrders.getOrdRequestRrAppraisalCompanyName()).isEqualTo(DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
        assertThat(testOrders.getOrdRequestRrvendorName()).isEqualTo(DEFAULT_ORD_REQUEST_RRVENDOR_NAME);
        assertThat(testOrders.getOrdRequestRrvendorLicenseNbr()).isEqualTo(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
        assertThat(testOrders.getOrdRequestRrvendorLicenseStateCd()).isEqualTo(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
        assertThat(testOrders.getOrdValuationCompletedProduct()).isEqualTo(DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT);
        assertThat(testOrders.getOrdValuationDueToClientDt()).isEqualTo(DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);
        assertThat(testOrders.getOrdValuationReportRecivedDt()).isEqualTo(DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);
        assertThat(testOrders.getOrdValuationCompletionDt()).isEqualTo(DEFAULT_ORD_VALUATION_COMPLETION_DT);
        assertThat(testOrders.getOrdValuationReportStatusCd()).isEqualTo(DEFAULT_ORD_VALUATION_REPORT_STATUS_CD);
        assertThat(testOrders.getOrdValuationAppraisedValueAmt()).isEqualTo(DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);
        assertThat(testOrders.getOrdValuationBpoMarketValueAmt()).isEqualTo(DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
        assertThat(testOrders.getOrdValuationBpoAsIsAmt()).isEqualTo(DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);
        assertThat(testOrders.getOrdValuationBpoAsRepairedValueAmt()).isEqualTo(DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
        assertThat(testOrders.getOrdValuationRedFlagCode()).isEqualTo(DEFAULT_ORD_VALUATION_RED_FLAG_CODE);
        assertThat(testOrders.getOrdValuationRedFlagDesc()).isEqualTo(DEFAULT_ORD_VALUATION_RED_FLAG_DESC);
        assertThat(testOrders.getOrdValuationAmcAppraisalId()).isEqualTo(DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID);
        assertThat(testOrders.getOrdValuationFinalReviewId()).isEqualTo(DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);
        assertThat(testOrders.getOrdReviewRecommendedValueAmt()).isEqualTo(DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
        assertThat(testOrders.getOrdReviewDt()).isEqualTo(DEFAULT_ORD_REVIEW_DT);
        assertThat(testOrders.getOrdReviewRecommendedActionCd()).isEqualTo(DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD);
        assertThat(testOrders.getOrdReviewReviewerDecisionCd()).isEqualTo(DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD);
        assertThat(testOrders.getOrdReviewReviewScore()).isEqualTo(DEFAULT_ORD_REVIEW_REVIEW_SCORE);
        assertThat(testOrders.getOrdReviewFormUsed()).isEqualTo(DEFAULT_ORD_REVIEW_FORM_USED);
        assertThat(testOrders.isOrdReviewProviderOnWatchListInd()).isEqualTo(DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);
        assertThat(testOrders.getOrdReviewCuredValueAmt()).isEqualTo(DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);
        assertThat(testOrders.getOrdReviewFinalRecommendedActionCd()).isEqualTo(DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void createOrdersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders with an existing ID
        orders.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdNumber(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdStageCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdStageCd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdOrgOrderStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdOrgOrderStatusCd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdCmpOrderStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdCmpOrderStatusCd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdProOrderStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdProOrderStatusCd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdOriginalValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdOriginalValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdOrderDtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdOrderDt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdMultiOrderIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdMultiOrderInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdUcdpDataFnmaSubmitToUcdpIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdUcdpDataFnmaSubmitToUcdpInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdUcdpDataFhlmcSubmitToUcdpIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdUcdpDataFhlmcSubmitToUcdpInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanQualifyingValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanQualifyingValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanSalesPriceAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanSalesPriceAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanBorrowerIsCoBorrowerIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanBorrowerIsCoBorrowerInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanDeedRestrictionIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanDeedRestrictionInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanHpmlIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanHpmlInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanIsNewConstructionIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanIsNewConstructionInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdLoanNonSubjectPropertyIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdLoanNonSubjectPropertyInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdRequestRrReportValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdRequestRrReportValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdValuationAppraisedValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdValuationAppraisedValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdValuationBpoMarketValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdValuationBpoMarketValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdValuationBpoAsIsAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdValuationBpoAsIsAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdValuationBpoAsRepairedValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdValuationBpoAsRepairedValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdReviewRecommendedValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdReviewRecommendedValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdReviewProviderOnWatchListIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdReviewProviderOnWatchListInd(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrdReviewCuredValueAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordersRepository.findAll().size();
        // set the field null
        orders.setOrdReviewCuredValueAmt(null);

        // Create the Orders, which fails.

        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList
        restOrdersMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orders.getId().intValue())))
            .andExpect(jsonPath("$.[*].ordNumber").value(hasItem(DEFAULT_ORD_NUMBER)))
            .andExpect(jsonPath("$.[*].ordStageCd").value(hasItem(DEFAULT_ORD_STAGE_CD)))
            .andExpect(jsonPath("$.[*].ordOrgOrderStatusCd").value(hasItem(DEFAULT_ORD_ORG_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordCmpOrderStatusCd").value(hasItem(DEFAULT_ORD_CMP_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordProOrderStatusCd").value(hasItem(DEFAULT_ORD_PRO_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordCmpNbr").value(hasItem(DEFAULT_ORD_CMP_NBR)))
            .andExpect(jsonPath("$.[*].ordBrnNbr").value(hasItem(DEFAULT_ORD_BRN_NBR)))
            .andExpect(jsonPath("$.[*].ordProNbr").value(hasItem(DEFAULT_ORD_PRO_NBR)))
            .andExpect(jsonPath("$.[*].ordProductCode").value(hasItem(DEFAULT_ORD_PRODUCT_CODE)))
            .andExpect(jsonPath("$.[*].ordOriginalValueAmt").value(hasItem(DEFAULT_ORD_ORIGINAL_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordParentTrackingNbr").value(hasItem(DEFAULT_ORD_PARENT_TRACKING_NBR)))
            .andExpect(jsonPath("$.[*].ordUserNbr").value(hasItem(DEFAULT_ORD_USER_NBR)))
            .andExpect(jsonPath("$.[*].ordOrderDt").value(hasItem(DEFAULT_ORD_ORDER_DT.toString())))
            .andExpect(jsonPath("$.[*].ordRushRequestDueDt").value(hasItem(DEFAULT_ORD_RUSH_REQUEST_DUE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordOrgInstructions").value(hasItem(DEFAULT_ORD_ORG_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].ordOrgOtherInstructions").value(hasItem(DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].ordMultiOrderInd").value(hasItem(DEFAULT_ORD_MULTI_ORDER_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordOrgDuedate").value(hasItem(DEFAULT_ORD_ORG_DUEDATE.toString())))
            .andExpect(jsonPath("$.[*].ordProDuedate").value(hasItem(DEFAULT_ORD_PRO_DUEDATE.toString())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFnmaSubmitToUcdpInd").value(hasItem(DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFnmaServicerNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpDataFhlmcSubmitToUcdpInd").value(hasItem(DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFhlmcIdentificationNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpDataUcdpBussinessUnitNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFnamaDocumentId").value(hasItem(DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFnmaSubmissionStatusId").value(hasItem(DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFhlmcDocumentFileCd").value(hasItem(DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD)))
            .andExpect(jsonPath("$.[*].ordUcdpSdEadSubmissionStatusCd").value(hasItem(DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordUcdpFinalSubmissionDt").value(hasItem(DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT.toString())))
            .andExpect(jsonPath("$.[*].ordLoanNbr").value(hasItem(DEFAULT_ORD_LOAN_NBR)))
            .andExpect(jsonPath("$.[*].ordloanOldLoanNbr").value(hasItem(DEFAULT_ORDLOAN_OLD_LOAN_NBR)))
            .andExpect(jsonPath("$.[*].ordLoanProgram").value(hasItem(DEFAULT_ORD_LOAN_PROGRAM)))
            .andExpect(jsonPath("$.[*].ordLoanPurpose").value(hasItem(DEFAULT_ORD_LOAN_PURPOSE)))
            .andExpect(jsonPath("$.[*].ordLoanType").value(hasItem(DEFAULT_ORD_LOAN_TYPE)))
            .andExpect(jsonPath("$.[*].ordLoanAmt").value(hasItem(DEFAULT_ORD_LOAN_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanQualifyingValueAmt").value(hasItem(DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanSalesPriceAmt").value(hasItem(DEFAULT_ORD_LOAN_SALES_PRICE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerLastName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerFirstName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerMiddleName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerIsCoBorrowerInd").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanFhaCaseNbr").value(hasItem(DEFAULT_ORD_LOAN_FHA_CASE_NBR)))
            .andExpect(jsonPath("$.[*].ordLoanDeedRestrictionInd").value(hasItem(DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanEstimatedCloseDt").value(hasItem(DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordLoanHpmlInd").value(hasItem(DEFAULT_ORD_LOAN_HPML_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanIsNewConstructionInd").value(hasItem(DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanCustomerSegmentCode").value(hasItem(DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE)))
            .andExpect(jsonPath("$.[*].ordLoanNonSubjectPropertyInd").value(hasItem(DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerRequestedCloseDt").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordPropertyTypeCd").value(hasItem(DEFAULT_ORD_PROPERTY_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordPropertyAddress1").value(hasItem(DEFAULT_ORD_PROPERTY_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].ordPropertyAddress2").value(hasItem(DEFAULT_ORD_PROPERTY_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].ordPropertyCity").value(hasItem(DEFAULT_ORD_PROPERTY_CITY)))
            .andExpect(jsonPath("$.[*].ordPropertyStateCd").value(hasItem(DEFAULT_ORD_PROPERTY_STATE_CD)))
            .andExpect(jsonPath("$.[*].ordPropertyZip").value(hasItem(DEFAULT_ORD_PROPERTY_ZIP)))
            .andExpect(jsonPath("$.[*].ordPropertyCounty").value(hasItem(DEFAULT_ORD_PROPERTY_COUNTY)))
            .andExpect(jsonPath("$.[*].ordPropertyLatitue").value(hasItem(DEFAULT_ORD_PROPERTY_LATITUE)))
            .andExpect(jsonPath("$.[*].ordPropertyLongitude").value(hasItem(DEFAULT_ORD_PROPERTY_LONGITUDE)))
            .andExpect(jsonPath("$.[*].ordPropertyProjectName").value(hasItem(DEFAULT_ORD_PROPERTY_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].ordContact1TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_1_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact1Name").value(hasItem(DEFAULT_ORD_CONTACT_1_NAME)))
            .andExpect(jsonPath("$.[*].ordContact1WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_1_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1Email").value(hasItem(DEFAULT_ORD_CONTACT_1_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact1OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_1_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact2TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_2_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact2Name").value(hasItem(DEFAULT_ORD_CONTACT_2_NAME)))
            .andExpect(jsonPath("$.[*].ordContact2WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_2_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2Email").value(hasItem(DEFAULT_ORD_CONTACT_2_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact2OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_2_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact3TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_3_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact3Name").value(hasItem(DEFAULT_ORD_CONTACT_3_NAME)))
            .andExpect(jsonPath("$.[*].ordContact3WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_3_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3Email").value(hasItem(DEFAULT_ORD_CONTACT_3_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact3OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_3_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordRequestRrStatusCd").value(hasItem(DEFAULT_ORD_REQUEST_RR_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordRequestRrVendorAppraisalId").value(hasItem(DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorAppraisalDraftRcvdDt").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT.toString())))
            .andExpect(jsonPath("$.[*].ordRequestRrXmlFormId").value(hasItem(DEFAULT_ORD_REQUEST_RR_XML_FORM_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrPdfFormId").value(hasItem(DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrApprFileDataSourcCd").value(hasItem(DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD)))
            .andExpect(jsonPath("$.[*].ordRequestRrReportValueAmt").value(hasItem(DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordRequestRrAppraisalCompanyName").value(hasItem(DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorName").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_NAME)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorLicenseNbr").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorLicenseStateCd").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD)))
            .andExpect(jsonPath("$.[*].ordValuationCompletedProduct").value(hasItem(DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT)))
            .andExpect(jsonPath("$.[*].ordValuationDueToClientDt").value(hasItem(DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationReportRecivedDt").value(hasItem(DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationCompletionDt").value(hasItem(DEFAULT_ORD_VALUATION_COMPLETION_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationReportStatusCd").value(hasItem(DEFAULT_ORD_VALUATION_REPORT_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordValuationAppraisedValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoMarketValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoAsIsAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoAsRepairedValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationRedFlagCode").value(hasItem(DEFAULT_ORD_VALUATION_RED_FLAG_CODE)))
            .andExpect(jsonPath("$.[*].ordValuationRedFlagDesc").value(hasItem(DEFAULT_ORD_VALUATION_RED_FLAG_DESC)))
            .andExpect(jsonPath("$.[*].ordValuationAmcAppraisalId").value(hasItem(DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID)))
            .andExpect(jsonPath("$.[*].ordValuationFinalReviewId").value(hasItem(DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewRecommendedValueAmt").value(hasItem(DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewDt").value(hasItem(DEFAULT_ORD_REVIEW_DT.toString())))
            .andExpect(jsonPath("$.[*].ordReviewRecommendedActionCd").value(hasItem(DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD)))
            .andExpect(jsonPath("$.[*].ordReviewReviewerDecisionCd").value(hasItem(DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD)))
            .andExpect(jsonPath("$.[*].ordReviewReviewScore").value(hasItem(DEFAULT_ORD_REVIEW_REVIEW_SCORE)))
            .andExpect(jsonPath("$.[*].ordReviewFormUsed").value(hasItem(DEFAULT_ORD_REVIEW_FORM_USED)))
            .andExpect(jsonPath("$.[*].ordReviewProviderOnWatchListInd").value(hasItem(DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordReviewCuredValueAmt").value(hasItem(DEFAULT_ORD_REVIEW_CURED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewFinalRecommendedActionCd").value(hasItem(DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD)));
    }
    
    @Test
    @Transactional
    public void getOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", orders.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orders.getId().intValue()))
            .andExpect(jsonPath("$.ordNumber").value(DEFAULT_ORD_NUMBER))
            .andExpect(jsonPath("$.ordStageCd").value(DEFAULT_ORD_STAGE_CD))
            .andExpect(jsonPath("$.ordOrgOrderStatusCd").value(DEFAULT_ORD_ORG_ORDER_STATUS_CD))
            .andExpect(jsonPath("$.ordCmpOrderStatusCd").value(DEFAULT_ORD_CMP_ORDER_STATUS_CD))
            .andExpect(jsonPath("$.ordProOrderStatusCd").value(DEFAULT_ORD_PRO_ORDER_STATUS_CD))
            .andExpect(jsonPath("$.ordCmpNbr").value(DEFAULT_ORD_CMP_NBR))
            .andExpect(jsonPath("$.ordBrnNbr").value(DEFAULT_ORD_BRN_NBR))
            .andExpect(jsonPath("$.ordProNbr").value(DEFAULT_ORD_PRO_NBR))
            .andExpect(jsonPath("$.ordProductCode").value(DEFAULT_ORD_PRODUCT_CODE))
            .andExpect(jsonPath("$.ordOriginalValueAmt").value(DEFAULT_ORD_ORIGINAL_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordParentTrackingNbr").value(DEFAULT_ORD_PARENT_TRACKING_NBR))
            .andExpect(jsonPath("$.ordUserNbr").value(DEFAULT_ORD_USER_NBR))
            .andExpect(jsonPath("$.ordOrderDt").value(DEFAULT_ORD_ORDER_DT.toString()))
            .andExpect(jsonPath("$.ordRushRequestDueDt").value(DEFAULT_ORD_RUSH_REQUEST_DUE_DT.toString()))
            .andExpect(jsonPath("$.ordOrgInstructions").value(DEFAULT_ORD_ORG_INSTRUCTIONS))
            .andExpect(jsonPath("$.ordOrgOtherInstructions").value(DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS))
            .andExpect(jsonPath("$.ordMultiOrderInd").value(DEFAULT_ORD_MULTI_ORDER_IND.booleanValue()))
            .andExpect(jsonPath("$.ordOrgDuedate").value(DEFAULT_ORD_ORG_DUEDATE.toString()))
            .andExpect(jsonPath("$.ordProDuedate").value(DEFAULT_ORD_PRO_DUEDATE.toString()))
            .andExpect(jsonPath("$.ordUcdpDataFnmaSubmitToUcdpInd").value(DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND.booleanValue()))
            .andExpect(jsonPath("$.ordUcdpDataFnmaServicerNbr").value(DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR))
            .andExpect(jsonPath("$.ordUcdpDataFhlmcSubmitToUcdpInd").value(DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND.booleanValue()))
            .andExpect(jsonPath("$.ordUcdpDataFhlmcIdentificationNbr").value(DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR))
            .andExpect(jsonPath("$.ordUcdpDataUcdpBussinessUnitNbr").value(DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR))
            .andExpect(jsonPath("$.ordUcdpSdFnamaDocumentId").value(DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID))
            .andExpect(jsonPath("$.ordUcdpSdFnmaSubmissionStatusId").value(DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID))
            .andExpect(jsonPath("$.ordUcdpSdFhlmcDocumentFileCd").value(DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD))
            .andExpect(jsonPath("$.ordUcdpSdEadSubmissionStatusCd").value(DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD))
            .andExpect(jsonPath("$.ordUcdpFinalSubmissionDt").value(DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT.toString()))
            .andExpect(jsonPath("$.ordLoanNbr").value(DEFAULT_ORD_LOAN_NBR))
            .andExpect(jsonPath("$.ordloanOldLoanNbr").value(DEFAULT_ORDLOAN_OLD_LOAN_NBR))
            .andExpect(jsonPath("$.ordLoanProgram").value(DEFAULT_ORD_LOAN_PROGRAM))
            .andExpect(jsonPath("$.ordLoanPurpose").value(DEFAULT_ORD_LOAN_PURPOSE))
            .andExpect(jsonPath("$.ordLoanType").value(DEFAULT_ORD_LOAN_TYPE))
            .andExpect(jsonPath("$.ordLoanAmt").value(DEFAULT_ORD_LOAN_AMT.intValue()))
            .andExpect(jsonPath("$.ordLoanQualifyingValueAmt").value(DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordLoanSalesPriceAmt").value(DEFAULT_ORD_LOAN_SALES_PRICE_AMT.intValue()))
            .andExpect(jsonPath("$.ordLoanBorrowerLastName").value(DEFAULT_ORD_LOAN_BORROWER_LAST_NAME))
            .andExpect(jsonPath("$.ordLoanBorrowerFirstName").value(DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME))
            .andExpect(jsonPath("$.ordLoanBorrowerMiddleName").value(DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME))
            .andExpect(jsonPath("$.ordLoanBorrowerIsCoBorrowerInd").value(DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND.booleanValue()))
            .andExpect(jsonPath("$.ordLoanFhaCaseNbr").value(DEFAULT_ORD_LOAN_FHA_CASE_NBR))
            .andExpect(jsonPath("$.ordLoanDeedRestrictionInd").value(DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND.booleanValue()))
            .andExpect(jsonPath("$.ordLoanEstimatedCloseDt").value(DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT.toString()))
            .andExpect(jsonPath("$.ordLoanHpmlInd").value(DEFAULT_ORD_LOAN_HPML_IND.booleanValue()))
            .andExpect(jsonPath("$.ordLoanIsNewConstructionInd").value(DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND.booleanValue()))
            .andExpect(jsonPath("$.ordLoanCustomerSegmentCode").value(DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE))
            .andExpect(jsonPath("$.ordLoanNonSubjectPropertyInd").value(DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND.booleanValue()))
            .andExpect(jsonPath("$.ordLoanBorrowerRequestedCloseDt").value(DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT.toString()))
            .andExpect(jsonPath("$.ordPropertyTypeCd").value(DEFAULT_ORD_PROPERTY_TYPE_CD))
            .andExpect(jsonPath("$.ordPropertyAddress1").value(DEFAULT_ORD_PROPERTY_ADDRESS_1))
            .andExpect(jsonPath("$.ordPropertyAddress2").value(DEFAULT_ORD_PROPERTY_ADDRESS_2))
            .andExpect(jsonPath("$.ordPropertyCity").value(DEFAULT_ORD_PROPERTY_CITY))
            .andExpect(jsonPath("$.ordPropertyStateCd").value(DEFAULT_ORD_PROPERTY_STATE_CD))
            .andExpect(jsonPath("$.ordPropertyZip").value(DEFAULT_ORD_PROPERTY_ZIP))
            .andExpect(jsonPath("$.ordPropertyCounty").value(DEFAULT_ORD_PROPERTY_COUNTY))
            .andExpect(jsonPath("$.ordPropertyLatitue").value(DEFAULT_ORD_PROPERTY_LATITUE))
            .andExpect(jsonPath("$.ordPropertyLongitude").value(DEFAULT_ORD_PROPERTY_LONGITUDE))
            .andExpect(jsonPath("$.ordPropertyProjectName").value(DEFAULT_ORD_PROPERTY_PROJECT_NAME))
            .andExpect(jsonPath("$.ordContact1TypeCd").value(DEFAULT_ORD_CONTACT_1_TYPE_CD))
            .andExpect(jsonPath("$.ordContact1Name").value(DEFAULT_ORD_CONTACT_1_NAME))
            .andExpect(jsonPath("$.ordContact1WorkPhone").value(DEFAULT_ORD_CONTACT_1_WORK_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact1HomePhone").value(DEFAULT_ORD_CONTACT_1_HOME_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact1CellPhone").value(DEFAULT_ORD_CONTACT_1_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact1OtherCellPhone").value(DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact1Email").value(DEFAULT_ORD_CONTACT_1_EMAIL))
            .andExpect(jsonPath("$.ordContact1OtherEmail").value(DEFAULT_ORD_CONTACT_1_OTHER_EMAIL))
            .andExpect(jsonPath("$.ordContact2TypeCd").value(DEFAULT_ORD_CONTACT_2_TYPE_CD))
            .andExpect(jsonPath("$.ordContact2Name").value(DEFAULT_ORD_CONTACT_2_NAME))
            .andExpect(jsonPath("$.ordContact2WorkPhone").value(DEFAULT_ORD_CONTACT_2_WORK_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact2HomePhone").value(DEFAULT_ORD_CONTACT_2_HOME_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact2CellPhone").value(DEFAULT_ORD_CONTACT_2_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact2OtherCellPhone").value(DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact2Email").value(DEFAULT_ORD_CONTACT_2_EMAIL))
            .andExpect(jsonPath("$.ordContact2OtherEmail").value(DEFAULT_ORD_CONTACT_2_OTHER_EMAIL))
            .andExpect(jsonPath("$.ordContact3TypeCd").value(DEFAULT_ORD_CONTACT_3_TYPE_CD))
            .andExpect(jsonPath("$.ordContact3Name").value(DEFAULT_ORD_CONTACT_3_NAME))
            .andExpect(jsonPath("$.ordContact3WorkPhone").value(DEFAULT_ORD_CONTACT_3_WORK_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact3HomePhone").value(DEFAULT_ORD_CONTACT_3_HOME_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact3CellPhone").value(DEFAULT_ORD_CONTACT_3_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact3OtherCellPhone").value(DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE.intValue()))
            .andExpect(jsonPath("$.ordContact3Email").value(DEFAULT_ORD_CONTACT_3_EMAIL))
            .andExpect(jsonPath("$.ordContact3OtherEmail").value(DEFAULT_ORD_CONTACT_3_OTHER_EMAIL))
            .andExpect(jsonPath("$.ordRequestRrStatusCd").value(DEFAULT_ORD_REQUEST_RR_STATUS_CD))
            .andExpect(jsonPath("$.ordRequestRrVendorAppraisalId").value(DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID))
            .andExpect(jsonPath("$.ordRequestRrvendorAppraisalDraftRcvdDt").value(DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT.toString()))
            .andExpect(jsonPath("$.ordRequestRrXmlFormId").value(DEFAULT_ORD_REQUEST_RR_XML_FORM_ID))
            .andExpect(jsonPath("$.ordRequestRrPdfFormId").value(DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID))
            .andExpect(jsonPath("$.ordRequestRrApprFileDataSourcCd").value(DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD))
            .andExpect(jsonPath("$.ordRequestRrReportValueAmt").value(DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordRequestRrAppraisalCompanyName").value(DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME))
            .andExpect(jsonPath("$.ordRequestRrvendorName").value(DEFAULT_ORD_REQUEST_RRVENDOR_NAME))
            .andExpect(jsonPath("$.ordRequestRrvendorLicenseNbr").value(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR))
            .andExpect(jsonPath("$.ordRequestRrvendorLicenseStateCd").value(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD))
            .andExpect(jsonPath("$.ordValuationCompletedProduct").value(DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT))
            .andExpect(jsonPath("$.ordValuationDueToClientDt").value(DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT.toString()))
            .andExpect(jsonPath("$.ordValuationReportRecivedDt").value(DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT.toString()))
            .andExpect(jsonPath("$.ordValuationCompletionDt").value(DEFAULT_ORD_VALUATION_COMPLETION_DT.toString()))
            .andExpect(jsonPath("$.ordValuationReportStatusCd").value(DEFAULT_ORD_VALUATION_REPORT_STATUS_CD))
            .andExpect(jsonPath("$.ordValuationAppraisedValueAmt").value(DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordValuationBpoMarketValueAmt").value(DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordValuationBpoAsIsAmt").value(DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT.intValue()))
            .andExpect(jsonPath("$.ordValuationBpoAsRepairedValueAmt").value(DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordValuationRedFlagCode").value(DEFAULT_ORD_VALUATION_RED_FLAG_CODE))
            .andExpect(jsonPath("$.ordValuationRedFlagDesc").value(DEFAULT_ORD_VALUATION_RED_FLAG_DESC))
            .andExpect(jsonPath("$.ordValuationAmcAppraisalId").value(DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID))
            .andExpect(jsonPath("$.ordValuationFinalReviewId").value(DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID.intValue()))
            .andExpect(jsonPath("$.ordReviewRecommendedValueAmt").value(DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordReviewDt").value(DEFAULT_ORD_REVIEW_DT.toString()))
            .andExpect(jsonPath("$.ordReviewRecommendedActionCd").value(DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD))
            .andExpect(jsonPath("$.ordReviewReviewerDecisionCd").value(DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD))
            .andExpect(jsonPath("$.ordReviewReviewScore").value(DEFAULT_ORD_REVIEW_REVIEW_SCORE))
            .andExpect(jsonPath("$.ordReviewFormUsed").value(DEFAULT_ORD_REVIEW_FORM_USED))
            .andExpect(jsonPath("$.ordReviewProviderOnWatchListInd").value(DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND.booleanValue()))
            .andExpect(jsonPath("$.ordReviewCuredValueAmt").value(DEFAULT_ORD_REVIEW_CURED_VALUE_AMT.intValue()))
            .andExpect(jsonPath("$.ordReviewFinalRecommendedActionCd").value(DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD));
    }


    @Test
    @Transactional
    public void getOrdersByIdFiltering() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        Long id = orders.getId();

        defaultOrdersShouldBeFound("id.equals=" + id);
        defaultOrdersShouldNotBeFound("id.notEquals=" + id);

        defaultOrdersShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrdersShouldNotBeFound("id.greaterThan=" + id);

        defaultOrdersShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrdersShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber equals to DEFAULT_ORD_NUMBER
        defaultOrdersShouldBeFound("ordNumber.equals=" + DEFAULT_ORD_NUMBER);

        // Get all the ordersList where ordNumber equals to UPDATED_ORD_NUMBER
        defaultOrdersShouldNotBeFound("ordNumber.equals=" + UPDATED_ORD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber not equals to DEFAULT_ORD_NUMBER
        defaultOrdersShouldNotBeFound("ordNumber.notEquals=" + DEFAULT_ORD_NUMBER);

        // Get all the ordersList where ordNumber not equals to UPDATED_ORD_NUMBER
        defaultOrdersShouldBeFound("ordNumber.notEquals=" + UPDATED_ORD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdNumberIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber in DEFAULT_ORD_NUMBER or UPDATED_ORD_NUMBER
        defaultOrdersShouldBeFound("ordNumber.in=" + DEFAULT_ORD_NUMBER + "," + UPDATED_ORD_NUMBER);

        // Get all the ordersList where ordNumber equals to UPDATED_ORD_NUMBER
        defaultOrdersShouldNotBeFound("ordNumber.in=" + UPDATED_ORD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber is not null
        defaultOrdersShouldBeFound("ordNumber.specified=true");

        // Get all the ordersList where ordNumber is null
        defaultOrdersShouldNotBeFound("ordNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdNumberContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber contains DEFAULT_ORD_NUMBER
        defaultOrdersShouldBeFound("ordNumber.contains=" + DEFAULT_ORD_NUMBER);

        // Get all the ordersList where ordNumber contains UPDATED_ORD_NUMBER
        defaultOrdersShouldNotBeFound("ordNumber.contains=" + UPDATED_ORD_NUMBER);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdNumberNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordNumber does not contain DEFAULT_ORD_NUMBER
        defaultOrdersShouldNotBeFound("ordNumber.doesNotContain=" + DEFAULT_ORD_NUMBER);

        // Get all the ordersList where ordNumber does not contain UPDATED_ORD_NUMBER
        defaultOrdersShouldBeFound("ordNumber.doesNotContain=" + UPDATED_ORD_NUMBER);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdStageCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd equals to DEFAULT_ORD_STAGE_CD
        defaultOrdersShouldBeFound("ordStageCd.equals=" + DEFAULT_ORD_STAGE_CD);

        // Get all the ordersList where ordStageCd equals to UPDATED_ORD_STAGE_CD
        defaultOrdersShouldNotBeFound("ordStageCd.equals=" + UPDATED_ORD_STAGE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdStageCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd not equals to DEFAULT_ORD_STAGE_CD
        defaultOrdersShouldNotBeFound("ordStageCd.notEquals=" + DEFAULT_ORD_STAGE_CD);

        // Get all the ordersList where ordStageCd not equals to UPDATED_ORD_STAGE_CD
        defaultOrdersShouldBeFound("ordStageCd.notEquals=" + UPDATED_ORD_STAGE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdStageCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd in DEFAULT_ORD_STAGE_CD or UPDATED_ORD_STAGE_CD
        defaultOrdersShouldBeFound("ordStageCd.in=" + DEFAULT_ORD_STAGE_CD + "," + UPDATED_ORD_STAGE_CD);

        // Get all the ordersList where ordStageCd equals to UPDATED_ORD_STAGE_CD
        defaultOrdersShouldNotBeFound("ordStageCd.in=" + UPDATED_ORD_STAGE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdStageCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd is not null
        defaultOrdersShouldBeFound("ordStageCd.specified=true");

        // Get all the ordersList where ordStageCd is null
        defaultOrdersShouldNotBeFound("ordStageCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdStageCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd contains DEFAULT_ORD_STAGE_CD
        defaultOrdersShouldBeFound("ordStageCd.contains=" + DEFAULT_ORD_STAGE_CD);

        // Get all the ordersList where ordStageCd contains UPDATED_ORD_STAGE_CD
        defaultOrdersShouldNotBeFound("ordStageCd.contains=" + UPDATED_ORD_STAGE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdStageCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordStageCd does not contain DEFAULT_ORD_STAGE_CD
        defaultOrdersShouldNotBeFound("ordStageCd.doesNotContain=" + DEFAULT_ORD_STAGE_CD);

        // Get all the ordersList where ordStageCd does not contain UPDATED_ORD_STAGE_CD
        defaultOrdersShouldBeFound("ordStageCd.doesNotContain=" + UPDATED_ORD_STAGE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd equals to DEFAULT_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.equals=" + DEFAULT_ORD_ORG_ORDER_STATUS_CD);

        // Get all the ordersList where ordOrgOrderStatusCd equals to UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.equals=" + UPDATED_ORD_ORG_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd not equals to DEFAULT_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.notEquals=" + DEFAULT_ORD_ORG_ORDER_STATUS_CD);

        // Get all the ordersList where ordOrgOrderStatusCd not equals to UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.notEquals=" + UPDATED_ORD_ORG_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd in DEFAULT_ORD_ORG_ORDER_STATUS_CD or UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.in=" + DEFAULT_ORD_ORG_ORDER_STATUS_CD + "," + UPDATED_ORD_ORG_ORDER_STATUS_CD);

        // Get all the ordersList where ordOrgOrderStatusCd equals to UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.in=" + UPDATED_ORD_ORG_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd is not null
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.specified=true");

        // Get all the ordersList where ordOrgOrderStatusCd is null
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd contains DEFAULT_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.contains=" + DEFAULT_ORD_ORG_ORDER_STATUS_CD);

        // Get all the ordersList where ordOrgOrderStatusCd contains UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.contains=" + UPDATED_ORD_ORG_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOrderStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOrderStatusCd does not contain DEFAULT_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordOrgOrderStatusCd.doesNotContain=" + DEFAULT_ORD_ORG_ORDER_STATUS_CD);

        // Get all the ordersList where ordOrgOrderStatusCd does not contain UPDATED_ORD_ORG_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordOrgOrderStatusCd.doesNotContain=" + UPDATED_ORD_ORG_ORDER_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd equals to DEFAULT_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.equals=" + DEFAULT_ORD_CMP_ORDER_STATUS_CD);

        // Get all the ordersList where ordCmpOrderStatusCd equals to UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.equals=" + UPDATED_ORD_CMP_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd not equals to DEFAULT_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.notEquals=" + DEFAULT_ORD_CMP_ORDER_STATUS_CD);

        // Get all the ordersList where ordCmpOrderStatusCd not equals to UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.notEquals=" + UPDATED_ORD_CMP_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd in DEFAULT_ORD_CMP_ORDER_STATUS_CD or UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.in=" + DEFAULT_ORD_CMP_ORDER_STATUS_CD + "," + UPDATED_ORD_CMP_ORDER_STATUS_CD);

        // Get all the ordersList where ordCmpOrderStatusCd equals to UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.in=" + UPDATED_ORD_CMP_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd is not null
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.specified=true");

        // Get all the ordersList where ordCmpOrderStatusCd is null
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd contains DEFAULT_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.contains=" + DEFAULT_ORD_CMP_ORDER_STATUS_CD);

        // Get all the ordersList where ordCmpOrderStatusCd contains UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.contains=" + UPDATED_ORD_CMP_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpOrderStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpOrderStatusCd does not contain DEFAULT_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordCmpOrderStatusCd.doesNotContain=" + DEFAULT_ORD_CMP_ORDER_STATUS_CD);

        // Get all the ordersList where ordCmpOrderStatusCd does not contain UPDATED_ORD_CMP_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordCmpOrderStatusCd.doesNotContain=" + UPDATED_ORD_CMP_ORDER_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd equals to DEFAULT_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordProOrderStatusCd.equals=" + DEFAULT_ORD_PRO_ORDER_STATUS_CD);

        // Get all the ordersList where ordProOrderStatusCd equals to UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.equals=" + UPDATED_ORD_PRO_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd not equals to DEFAULT_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.notEquals=" + DEFAULT_ORD_PRO_ORDER_STATUS_CD);

        // Get all the ordersList where ordProOrderStatusCd not equals to UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordProOrderStatusCd.notEquals=" + UPDATED_ORD_PRO_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd in DEFAULT_ORD_PRO_ORDER_STATUS_CD or UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordProOrderStatusCd.in=" + DEFAULT_ORD_PRO_ORDER_STATUS_CD + "," + UPDATED_ORD_PRO_ORDER_STATUS_CD);

        // Get all the ordersList where ordProOrderStatusCd equals to UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.in=" + UPDATED_ORD_PRO_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd is not null
        defaultOrdersShouldBeFound("ordProOrderStatusCd.specified=true");

        // Get all the ordersList where ordProOrderStatusCd is null
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd contains DEFAULT_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordProOrderStatusCd.contains=" + DEFAULT_ORD_PRO_ORDER_STATUS_CD);

        // Get all the ordersList where ordProOrderStatusCd contains UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.contains=" + UPDATED_ORD_PRO_ORDER_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProOrderStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProOrderStatusCd does not contain DEFAULT_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldNotBeFound("ordProOrderStatusCd.doesNotContain=" + DEFAULT_ORD_PRO_ORDER_STATUS_CD);

        // Get all the ordersList where ordProOrderStatusCd does not contain UPDATED_ORD_PRO_ORDER_STATUS_CD
        defaultOrdersShouldBeFound("ordProOrderStatusCd.doesNotContain=" + UPDATED_ORD_PRO_ORDER_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr equals to DEFAULT_ORD_CMP_NBR
        defaultOrdersShouldBeFound("ordCmpNbr.equals=" + DEFAULT_ORD_CMP_NBR);

        // Get all the ordersList where ordCmpNbr equals to UPDATED_ORD_CMP_NBR
        defaultOrdersShouldNotBeFound("ordCmpNbr.equals=" + UPDATED_ORD_CMP_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr not equals to DEFAULT_ORD_CMP_NBR
        defaultOrdersShouldNotBeFound("ordCmpNbr.notEquals=" + DEFAULT_ORD_CMP_NBR);

        // Get all the ordersList where ordCmpNbr not equals to UPDATED_ORD_CMP_NBR
        defaultOrdersShouldBeFound("ordCmpNbr.notEquals=" + UPDATED_ORD_CMP_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr in DEFAULT_ORD_CMP_NBR or UPDATED_ORD_CMP_NBR
        defaultOrdersShouldBeFound("ordCmpNbr.in=" + DEFAULT_ORD_CMP_NBR + "," + UPDATED_ORD_CMP_NBR);

        // Get all the ordersList where ordCmpNbr equals to UPDATED_ORD_CMP_NBR
        defaultOrdersShouldNotBeFound("ordCmpNbr.in=" + UPDATED_ORD_CMP_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr is not null
        defaultOrdersShouldBeFound("ordCmpNbr.specified=true");

        // Get all the ordersList where ordCmpNbr is null
        defaultOrdersShouldNotBeFound("ordCmpNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr contains DEFAULT_ORD_CMP_NBR
        defaultOrdersShouldBeFound("ordCmpNbr.contains=" + DEFAULT_ORD_CMP_NBR);

        // Get all the ordersList where ordCmpNbr contains UPDATED_ORD_CMP_NBR
        defaultOrdersShouldNotBeFound("ordCmpNbr.contains=" + UPDATED_ORD_CMP_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdCmpNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordCmpNbr does not contain DEFAULT_ORD_CMP_NBR
        defaultOrdersShouldNotBeFound("ordCmpNbr.doesNotContain=" + DEFAULT_ORD_CMP_NBR);

        // Get all the ordersList where ordCmpNbr does not contain UPDATED_ORD_CMP_NBR
        defaultOrdersShouldBeFound("ordCmpNbr.doesNotContain=" + UPDATED_ORD_CMP_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr equals to DEFAULT_ORD_BRN_NBR
        defaultOrdersShouldBeFound("ordBrnNbr.equals=" + DEFAULT_ORD_BRN_NBR);

        // Get all the ordersList where ordBrnNbr equals to UPDATED_ORD_BRN_NBR
        defaultOrdersShouldNotBeFound("ordBrnNbr.equals=" + UPDATED_ORD_BRN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr not equals to DEFAULT_ORD_BRN_NBR
        defaultOrdersShouldNotBeFound("ordBrnNbr.notEquals=" + DEFAULT_ORD_BRN_NBR);

        // Get all the ordersList where ordBrnNbr not equals to UPDATED_ORD_BRN_NBR
        defaultOrdersShouldBeFound("ordBrnNbr.notEquals=" + UPDATED_ORD_BRN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr in DEFAULT_ORD_BRN_NBR or UPDATED_ORD_BRN_NBR
        defaultOrdersShouldBeFound("ordBrnNbr.in=" + DEFAULT_ORD_BRN_NBR + "," + UPDATED_ORD_BRN_NBR);

        // Get all the ordersList where ordBrnNbr equals to UPDATED_ORD_BRN_NBR
        defaultOrdersShouldNotBeFound("ordBrnNbr.in=" + UPDATED_ORD_BRN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr is not null
        defaultOrdersShouldBeFound("ordBrnNbr.specified=true");

        // Get all the ordersList where ordBrnNbr is null
        defaultOrdersShouldNotBeFound("ordBrnNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr contains DEFAULT_ORD_BRN_NBR
        defaultOrdersShouldBeFound("ordBrnNbr.contains=" + DEFAULT_ORD_BRN_NBR);

        // Get all the ordersList where ordBrnNbr contains UPDATED_ORD_BRN_NBR
        defaultOrdersShouldNotBeFound("ordBrnNbr.contains=" + UPDATED_ORD_BRN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdBrnNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordBrnNbr does not contain DEFAULT_ORD_BRN_NBR
        defaultOrdersShouldNotBeFound("ordBrnNbr.doesNotContain=" + DEFAULT_ORD_BRN_NBR);

        // Get all the ordersList where ordBrnNbr does not contain UPDATED_ORD_BRN_NBR
        defaultOrdersShouldBeFound("ordBrnNbr.doesNotContain=" + UPDATED_ORD_BRN_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdProNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr equals to DEFAULT_ORD_PRO_NBR
        defaultOrdersShouldBeFound("ordProNbr.equals=" + DEFAULT_ORD_PRO_NBR);

        // Get all the ordersList where ordProNbr equals to UPDATED_ORD_PRO_NBR
        defaultOrdersShouldNotBeFound("ordProNbr.equals=" + UPDATED_ORD_PRO_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr not equals to DEFAULT_ORD_PRO_NBR
        defaultOrdersShouldNotBeFound("ordProNbr.notEquals=" + DEFAULT_ORD_PRO_NBR);

        // Get all the ordersList where ordProNbr not equals to UPDATED_ORD_PRO_NBR
        defaultOrdersShouldBeFound("ordProNbr.notEquals=" + UPDATED_ORD_PRO_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr in DEFAULT_ORD_PRO_NBR or UPDATED_ORD_PRO_NBR
        defaultOrdersShouldBeFound("ordProNbr.in=" + DEFAULT_ORD_PRO_NBR + "," + UPDATED_ORD_PRO_NBR);

        // Get all the ordersList where ordProNbr equals to UPDATED_ORD_PRO_NBR
        defaultOrdersShouldNotBeFound("ordProNbr.in=" + UPDATED_ORD_PRO_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr is not null
        defaultOrdersShouldBeFound("ordProNbr.specified=true");

        // Get all the ordersList where ordProNbr is null
        defaultOrdersShouldNotBeFound("ordProNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdProNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr contains DEFAULT_ORD_PRO_NBR
        defaultOrdersShouldBeFound("ordProNbr.contains=" + DEFAULT_ORD_PRO_NBR);

        // Get all the ordersList where ordProNbr contains UPDATED_ORD_PRO_NBR
        defaultOrdersShouldNotBeFound("ordProNbr.contains=" + UPDATED_ORD_PRO_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProNbr does not contain DEFAULT_ORD_PRO_NBR
        defaultOrdersShouldNotBeFound("ordProNbr.doesNotContain=" + DEFAULT_ORD_PRO_NBR);

        // Get all the ordersList where ordProNbr does not contain UPDATED_ORD_PRO_NBR
        defaultOrdersShouldBeFound("ordProNbr.doesNotContain=" + UPDATED_ORD_PRO_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode equals to DEFAULT_ORD_PRODUCT_CODE
        defaultOrdersShouldBeFound("ordProductCode.equals=" + DEFAULT_ORD_PRODUCT_CODE);

        // Get all the ordersList where ordProductCode equals to UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldNotBeFound("ordProductCode.equals=" + UPDATED_ORD_PRODUCT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode not equals to DEFAULT_ORD_PRODUCT_CODE
        defaultOrdersShouldNotBeFound("ordProductCode.notEquals=" + DEFAULT_ORD_PRODUCT_CODE);

        // Get all the ordersList where ordProductCode not equals to UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldBeFound("ordProductCode.notEquals=" + UPDATED_ORD_PRODUCT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode in DEFAULT_ORD_PRODUCT_CODE or UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldBeFound("ordProductCode.in=" + DEFAULT_ORD_PRODUCT_CODE + "," + UPDATED_ORD_PRODUCT_CODE);

        // Get all the ordersList where ordProductCode equals to UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldNotBeFound("ordProductCode.in=" + UPDATED_ORD_PRODUCT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode is not null
        defaultOrdersShouldBeFound("ordProductCode.specified=true");

        // Get all the ordersList where ordProductCode is null
        defaultOrdersShouldNotBeFound("ordProductCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode contains DEFAULT_ORD_PRODUCT_CODE
        defaultOrdersShouldBeFound("ordProductCode.contains=" + DEFAULT_ORD_PRODUCT_CODE);

        // Get all the ordersList where ordProductCode contains UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldNotBeFound("ordProductCode.contains=" + UPDATED_ORD_PRODUCT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProductCodeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProductCode does not contain DEFAULT_ORD_PRODUCT_CODE
        defaultOrdersShouldNotBeFound("ordProductCode.doesNotContain=" + DEFAULT_ORD_PRODUCT_CODE);

        // Get all the ordersList where ordProductCode does not contain UPDATED_ORD_PRODUCT_CODE
        defaultOrdersShouldBeFound("ordProductCode.doesNotContain=" + UPDATED_ORD_PRODUCT_CODE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt equals to DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.equals=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt equals to UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.equals=" + UPDATED_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt not equals to DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.notEquals=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt not equals to UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.notEquals=" + UPDATED_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt in DEFAULT_ORD_ORIGINAL_VALUE_AMT or UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.in=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT + "," + UPDATED_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt equals to UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.in=" + UPDATED_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt is not null
        defaultOrdersShouldBeFound("ordOriginalValueAmt.specified=true");

        // Get all the ordersList where ordOriginalValueAmt is null
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt is greater than or equal to DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt is greater than or equal to UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.greaterThanOrEqual=" + UPDATED_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt is less than or equal to DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.lessThanOrEqual=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt is less than or equal to SMALLER_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.lessThanOrEqual=" + SMALLER_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt is less than DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.lessThan=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt is less than UPDATED_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.lessThan=" + UPDATED_ORD_ORIGINAL_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOriginalValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOriginalValueAmt is greater than DEFAULT_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordOriginalValueAmt.greaterThan=" + DEFAULT_ORD_ORIGINAL_VALUE_AMT);

        // Get all the ordersList where ordOriginalValueAmt is greater than SMALLER_ORD_ORIGINAL_VALUE_AMT
        defaultOrdersShouldBeFound("ordOriginalValueAmt.greaterThan=" + SMALLER_ORD_ORIGINAL_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr equals to DEFAULT_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldBeFound("ordParentTrackingNbr.equals=" + DEFAULT_ORD_PARENT_TRACKING_NBR);

        // Get all the ordersList where ordParentTrackingNbr equals to UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.equals=" + UPDATED_ORD_PARENT_TRACKING_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr not equals to DEFAULT_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.notEquals=" + DEFAULT_ORD_PARENT_TRACKING_NBR);

        // Get all the ordersList where ordParentTrackingNbr not equals to UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldBeFound("ordParentTrackingNbr.notEquals=" + UPDATED_ORD_PARENT_TRACKING_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr in DEFAULT_ORD_PARENT_TRACKING_NBR or UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldBeFound("ordParentTrackingNbr.in=" + DEFAULT_ORD_PARENT_TRACKING_NBR + "," + UPDATED_ORD_PARENT_TRACKING_NBR);

        // Get all the ordersList where ordParentTrackingNbr equals to UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.in=" + UPDATED_ORD_PARENT_TRACKING_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr is not null
        defaultOrdersShouldBeFound("ordParentTrackingNbr.specified=true");

        // Get all the ordersList where ordParentTrackingNbr is null
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr contains DEFAULT_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldBeFound("ordParentTrackingNbr.contains=" + DEFAULT_ORD_PARENT_TRACKING_NBR);

        // Get all the ordersList where ordParentTrackingNbr contains UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.contains=" + UPDATED_ORD_PARENT_TRACKING_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdParentTrackingNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordParentTrackingNbr does not contain DEFAULT_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldNotBeFound("ordParentTrackingNbr.doesNotContain=" + DEFAULT_ORD_PARENT_TRACKING_NBR);

        // Get all the ordersList where ordParentTrackingNbr does not contain UPDATED_ORD_PARENT_TRACKING_NBR
        defaultOrdersShouldBeFound("ordParentTrackingNbr.doesNotContain=" + UPDATED_ORD_PARENT_TRACKING_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr equals to DEFAULT_ORD_USER_NBR
        defaultOrdersShouldBeFound("ordUserNbr.equals=" + DEFAULT_ORD_USER_NBR);

        // Get all the ordersList where ordUserNbr equals to UPDATED_ORD_USER_NBR
        defaultOrdersShouldNotBeFound("ordUserNbr.equals=" + UPDATED_ORD_USER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr not equals to DEFAULT_ORD_USER_NBR
        defaultOrdersShouldNotBeFound("ordUserNbr.notEquals=" + DEFAULT_ORD_USER_NBR);

        // Get all the ordersList where ordUserNbr not equals to UPDATED_ORD_USER_NBR
        defaultOrdersShouldBeFound("ordUserNbr.notEquals=" + UPDATED_ORD_USER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr in DEFAULT_ORD_USER_NBR or UPDATED_ORD_USER_NBR
        defaultOrdersShouldBeFound("ordUserNbr.in=" + DEFAULT_ORD_USER_NBR + "," + UPDATED_ORD_USER_NBR);

        // Get all the ordersList where ordUserNbr equals to UPDATED_ORD_USER_NBR
        defaultOrdersShouldNotBeFound("ordUserNbr.in=" + UPDATED_ORD_USER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr is not null
        defaultOrdersShouldBeFound("ordUserNbr.specified=true");

        // Get all the ordersList where ordUserNbr is null
        defaultOrdersShouldNotBeFound("ordUserNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr contains DEFAULT_ORD_USER_NBR
        defaultOrdersShouldBeFound("ordUserNbr.contains=" + DEFAULT_ORD_USER_NBR);

        // Get all the ordersList where ordUserNbr contains UPDATED_ORD_USER_NBR
        defaultOrdersShouldNotBeFound("ordUserNbr.contains=" + UPDATED_ORD_USER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUserNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUserNbr does not contain DEFAULT_ORD_USER_NBR
        defaultOrdersShouldNotBeFound("ordUserNbr.doesNotContain=" + DEFAULT_ORD_USER_NBR);

        // Get all the ordersList where ordUserNbr does not contain UPDATED_ORD_USER_NBR
        defaultOrdersShouldBeFound("ordUserNbr.doesNotContain=" + UPDATED_ORD_USER_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt equals to DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.equals=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt equals to UPDATED_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.equals=" + UPDATED_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt not equals to DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.notEquals=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt not equals to UPDATED_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.notEquals=" + UPDATED_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt in DEFAULT_ORD_ORDER_DT or UPDATED_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.in=" + DEFAULT_ORD_ORDER_DT + "," + UPDATED_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt equals to UPDATED_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.in=" + UPDATED_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt is not null
        defaultOrdersShouldBeFound("ordOrderDt.specified=true");

        // Get all the ordersList where ordOrderDt is null
        defaultOrdersShouldNotBeFound("ordOrderDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt is greater than or equal to DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.greaterThanOrEqual=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt is greater than or equal to UPDATED_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.greaterThanOrEqual=" + UPDATED_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt is less than or equal to DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.lessThanOrEqual=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt is less than or equal to SMALLER_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.lessThanOrEqual=" + SMALLER_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt is less than DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.lessThan=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt is less than UPDATED_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.lessThan=" + UPDATED_ORD_ORDER_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrderDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrderDt is greater than DEFAULT_ORD_ORDER_DT
        defaultOrdersShouldNotBeFound("ordOrderDt.greaterThan=" + DEFAULT_ORD_ORDER_DT);

        // Get all the ordersList where ordOrderDt is greater than SMALLER_ORD_ORDER_DT
        defaultOrdersShouldBeFound("ordOrderDt.greaterThan=" + SMALLER_ORD_ORDER_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt equals to DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.equals=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt equals to UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.equals=" + UPDATED_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt not equals to DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.notEquals=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt not equals to UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.notEquals=" + UPDATED_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt in DEFAULT_ORD_RUSH_REQUEST_DUE_DT or UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.in=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT + "," + UPDATED_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt equals to UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.in=" + UPDATED_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt is not null
        defaultOrdersShouldBeFound("ordRushRequestDueDt.specified=true");

        // Get all the ordersList where ordRushRequestDueDt is null
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt is greater than or equal to DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.greaterThanOrEqual=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt is greater than or equal to UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.greaterThanOrEqual=" + UPDATED_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt is less than or equal to DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.lessThanOrEqual=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt is less than or equal to SMALLER_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.lessThanOrEqual=" + SMALLER_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt is less than DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.lessThan=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt is less than UPDATED_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.lessThan=" + UPDATED_ORD_RUSH_REQUEST_DUE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRushRequestDueDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRushRequestDueDt is greater than DEFAULT_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldNotBeFound("ordRushRequestDueDt.greaterThan=" + DEFAULT_ORD_RUSH_REQUEST_DUE_DT);

        // Get all the ordersList where ordRushRequestDueDt is greater than SMALLER_ORD_RUSH_REQUEST_DUE_DT
        defaultOrdersShouldBeFound("ordRushRequestDueDt.greaterThan=" + SMALLER_ORD_RUSH_REQUEST_DUE_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions equals to DEFAULT_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgInstructions.equals=" + DEFAULT_ORD_ORG_INSTRUCTIONS);

        // Get all the ordersList where ordOrgInstructions equals to UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgInstructions.equals=" + UPDATED_ORD_ORG_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions not equals to DEFAULT_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgInstructions.notEquals=" + DEFAULT_ORD_ORG_INSTRUCTIONS);

        // Get all the ordersList where ordOrgInstructions not equals to UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgInstructions.notEquals=" + UPDATED_ORD_ORG_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions in DEFAULT_ORD_ORG_INSTRUCTIONS or UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgInstructions.in=" + DEFAULT_ORD_ORG_INSTRUCTIONS + "," + UPDATED_ORD_ORG_INSTRUCTIONS);

        // Get all the ordersList where ordOrgInstructions equals to UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgInstructions.in=" + UPDATED_ORD_ORG_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions is not null
        defaultOrdersShouldBeFound("ordOrgInstructions.specified=true");

        // Get all the ordersList where ordOrgInstructions is null
        defaultOrdersShouldNotBeFound("ordOrgInstructions.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions contains DEFAULT_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgInstructions.contains=" + DEFAULT_ORD_ORG_INSTRUCTIONS);

        // Get all the ordersList where ordOrgInstructions contains UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgInstructions.contains=" + UPDATED_ORD_ORG_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgInstructionsNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgInstructions does not contain DEFAULT_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgInstructions.doesNotContain=" + DEFAULT_ORD_ORG_INSTRUCTIONS);

        // Get all the ordersList where ordOrgInstructions does not contain UPDATED_ORD_ORG_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgInstructions.doesNotContain=" + UPDATED_ORD_ORG_INSTRUCTIONS);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions equals to DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.equals=" + DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS);

        // Get all the ordersList where ordOrgOtherInstructions equals to UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.equals=" + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions not equals to DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.notEquals=" + DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS);

        // Get all the ordersList where ordOrgOtherInstructions not equals to UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.notEquals=" + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions in DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS or UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.in=" + DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS + "," + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);

        // Get all the ordersList where ordOrgOtherInstructions equals to UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.in=" + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions is not null
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.specified=true");

        // Get all the ordersList where ordOrgOtherInstructions is null
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions contains DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.contains=" + DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS);

        // Get all the ordersList where ordOrgOtherInstructions contains UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.contains=" + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgOtherInstructionsNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgOtherInstructions does not contain DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldNotBeFound("ordOrgOtherInstructions.doesNotContain=" + DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS);

        // Get all the ordersList where ordOrgOtherInstructions does not contain UPDATED_ORD_ORG_OTHER_INSTRUCTIONS
        defaultOrdersShouldBeFound("ordOrgOtherInstructions.doesNotContain=" + UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdMultiOrderIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordMultiOrderInd equals to DEFAULT_ORD_MULTI_ORDER_IND
        defaultOrdersShouldBeFound("ordMultiOrderInd.equals=" + DEFAULT_ORD_MULTI_ORDER_IND);

        // Get all the ordersList where ordMultiOrderInd equals to UPDATED_ORD_MULTI_ORDER_IND
        defaultOrdersShouldNotBeFound("ordMultiOrderInd.equals=" + UPDATED_ORD_MULTI_ORDER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdMultiOrderIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordMultiOrderInd not equals to DEFAULT_ORD_MULTI_ORDER_IND
        defaultOrdersShouldNotBeFound("ordMultiOrderInd.notEquals=" + DEFAULT_ORD_MULTI_ORDER_IND);

        // Get all the ordersList where ordMultiOrderInd not equals to UPDATED_ORD_MULTI_ORDER_IND
        defaultOrdersShouldBeFound("ordMultiOrderInd.notEquals=" + UPDATED_ORD_MULTI_ORDER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdMultiOrderIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordMultiOrderInd in DEFAULT_ORD_MULTI_ORDER_IND or UPDATED_ORD_MULTI_ORDER_IND
        defaultOrdersShouldBeFound("ordMultiOrderInd.in=" + DEFAULT_ORD_MULTI_ORDER_IND + "," + UPDATED_ORD_MULTI_ORDER_IND);

        // Get all the ordersList where ordMultiOrderInd equals to UPDATED_ORD_MULTI_ORDER_IND
        defaultOrdersShouldNotBeFound("ordMultiOrderInd.in=" + UPDATED_ORD_MULTI_ORDER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdMultiOrderIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordMultiOrderInd is not null
        defaultOrdersShouldBeFound("ordMultiOrderInd.specified=true");

        // Get all the ordersList where ordMultiOrderInd is null
        defaultOrdersShouldNotBeFound("ordMultiOrderInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate equals to DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.equals=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate equals to UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.equals=" + UPDATED_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate not equals to DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.notEquals=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate not equals to UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.notEquals=" + UPDATED_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate in DEFAULT_ORD_ORG_DUEDATE or UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.in=" + DEFAULT_ORD_ORG_DUEDATE + "," + UPDATED_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate equals to UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.in=" + UPDATED_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate is not null
        defaultOrdersShouldBeFound("ordOrgDuedate.specified=true");

        // Get all the ordersList where ordOrgDuedate is null
        defaultOrdersShouldNotBeFound("ordOrgDuedate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate is greater than or equal to DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.greaterThanOrEqual=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate is greater than or equal to UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.greaterThanOrEqual=" + UPDATED_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate is less than or equal to DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.lessThanOrEqual=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate is less than or equal to SMALLER_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.lessThanOrEqual=" + SMALLER_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate is less than DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.lessThan=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate is less than UPDATED_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.lessThan=" + UPDATED_ORD_ORG_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdOrgDuedateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordOrgDuedate is greater than DEFAULT_ORD_ORG_DUEDATE
        defaultOrdersShouldNotBeFound("ordOrgDuedate.greaterThan=" + DEFAULT_ORD_ORG_DUEDATE);

        // Get all the ordersList where ordOrgDuedate is greater than SMALLER_ORD_ORG_DUEDATE
        defaultOrdersShouldBeFound("ordOrgDuedate.greaterThan=" + SMALLER_ORD_ORG_DUEDATE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate equals to DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.equals=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate equals to UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.equals=" + UPDATED_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate not equals to DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.notEquals=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate not equals to UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.notEquals=" + UPDATED_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate in DEFAULT_ORD_PRO_DUEDATE or UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.in=" + DEFAULT_ORD_PRO_DUEDATE + "," + UPDATED_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate equals to UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.in=" + UPDATED_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate is not null
        defaultOrdersShouldBeFound("ordProDuedate.specified=true");

        // Get all the ordersList where ordProDuedate is null
        defaultOrdersShouldNotBeFound("ordProDuedate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate is greater than or equal to DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.greaterThanOrEqual=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate is greater than or equal to UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.greaterThanOrEqual=" + UPDATED_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate is less than or equal to DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.lessThanOrEqual=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate is less than or equal to SMALLER_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.lessThanOrEqual=" + SMALLER_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate is less than DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.lessThan=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate is less than UPDATED_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.lessThan=" + UPDATED_ORD_PRO_DUEDATE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdProDuedateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordProDuedate is greater than DEFAULT_ORD_PRO_DUEDATE
        defaultOrdersShouldNotBeFound("ordProDuedate.greaterThan=" + DEFAULT_ORD_PRO_DUEDATE);

        // Get all the ordersList where ordProDuedate is greater than SMALLER_ORD_PRO_DUEDATE
        defaultOrdersShouldBeFound("ordProDuedate.greaterThan=" + SMALLER_ORD_PRO_DUEDATE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaSubmitToUcdpIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd equals to DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFnmaSubmitToUcdpInd.equals=" + DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd equals to UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaSubmitToUcdpInd.equals=" + UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaSubmitToUcdpIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd not equals to DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaSubmitToUcdpInd.notEquals=" + DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd not equals to UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFnmaSubmitToUcdpInd.notEquals=" + UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaSubmitToUcdpIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd in DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND or UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFnmaSubmitToUcdpInd.in=" + DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND + "," + UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd equals to UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaSubmitToUcdpInd.in=" + UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaSubmitToUcdpIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd is not null
        defaultOrdersShouldBeFound("ordUcdpDataFnmaSubmitToUcdpInd.specified=true");

        // Get all the ordersList where ordUcdpDataFnmaSubmitToUcdpInd is null
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaSubmitToUcdpInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr equals to DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.equals=" + DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr equals to UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.equals=" + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr not equals to DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.notEquals=" + DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr not equals to UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.notEquals=" + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr in DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR or UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.in=" + DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR + "," + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr equals to UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.in=" + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr is not null
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.specified=true");

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr is null
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr contains DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.contains=" + DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr contains UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.contains=" + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFnmaServicerNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr does not contain DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFnmaServicerNbr.doesNotContain=" + DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR);

        // Get all the ordersList where ordUcdpDataFnmaServicerNbr does not contain UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFnmaServicerNbr.doesNotContain=" + UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcSubmitToUcdpIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd equals to DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.equals=" + DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd equals to UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.equals=" + UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcSubmitToUcdpIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd not equals to DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.notEquals=" + DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd not equals to UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.notEquals=" + UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcSubmitToUcdpIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd in DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND or UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.in=" + DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND + "," + UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd equals to UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.in=" + UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcSubmitToUcdpIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd is not null
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.specified=true");

        // Get all the ordersList where ordUcdpDataFhlmcSubmitToUcdpInd is null
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcSubmitToUcdpInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr equals to DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.equals=" + DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr equals to UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.equals=" + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr not equals to DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.notEquals=" + DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr not equals to UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.notEquals=" + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr in DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR or UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.in=" + DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR + "," + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr equals to UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.in=" + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr is not null
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.specified=true");

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr is null
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr contains DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.contains=" + DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr contains UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.contains=" + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataFhlmcIdentificationNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr does not contain DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataFhlmcIdentificationNbr.doesNotContain=" + DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);

        // Get all the ordersList where ordUcdpDataFhlmcIdentificationNbr does not contain UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR
        defaultOrdersShouldBeFound("ordUcdpDataFhlmcIdentificationNbr.doesNotContain=" + UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr equals to DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.equals=" + DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr equals to UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.equals=" + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr not equals to DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.notEquals=" + DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr not equals to UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.notEquals=" + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr in DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR or UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.in=" + DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR + "," + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr equals to UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.in=" + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr is not null
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.specified=true");

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr is null
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr contains DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.contains=" + DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr contains UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.contains=" + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpDataUcdpBussinessUnitNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr does not contain DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldNotBeFound("ordUcdpDataUcdpBussinessUnitNbr.doesNotContain=" + DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);

        // Get all the ordersList where ordUcdpDataUcdpBussinessUnitNbr does not contain UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR
        defaultOrdersShouldBeFound("ordUcdpDataUcdpBussinessUnitNbr.doesNotContain=" + UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId equals to DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.equals=" + DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId equals to UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.equals=" + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId not equals to DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.notEquals=" + DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId not equals to UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.notEquals=" + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId in DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID or UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.in=" + DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID + "," + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId equals to UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.in=" + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId is not null
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.specified=true");

        // Get all the ordersList where ordUcdpSdFnamaDocumentId is null
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId contains DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.contains=" + DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId contains UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.contains=" + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnamaDocumentIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId does not contain DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnamaDocumentId.doesNotContain=" + DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);

        // Get all the ordersList where ordUcdpSdFnamaDocumentId does not contain UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnamaDocumentId.doesNotContain=" + UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId equals to DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.equals=" + DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId equals to UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.equals=" + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId not equals to DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.notEquals=" + DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId not equals to UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.notEquals=" + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId in DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID or UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.in=" + DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID + "," + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId equals to UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.in=" + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId is not null
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.specified=true");

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId is null
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId contains DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.contains=" + DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId contains UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.contains=" + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFnmaSubmissionStatusIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId does not contain DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldNotBeFound("ordUcdpSdFnmaSubmissionStatusId.doesNotContain=" + DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);

        // Get all the ordersList where ordUcdpSdFnmaSubmissionStatusId does not contain UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID
        defaultOrdersShouldBeFound("ordUcdpSdFnmaSubmissionStatusId.doesNotContain=" + UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd equals to DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.equals=" + DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd equals to UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.equals=" + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd not equals to DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.notEquals=" + DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd not equals to UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.notEquals=" + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd in DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD or UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.in=" + DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD + "," + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd equals to UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.in=" + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd is not null
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.specified=true");

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd is null
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd contains DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.contains=" + DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd contains UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.contains=" + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdFhlmcDocumentFileCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd does not contain DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdFhlmcDocumentFileCd.doesNotContain=" + DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);

        // Get all the ordersList where ordUcdpSdFhlmcDocumentFileCd does not contain UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD
        defaultOrdersShouldBeFound("ordUcdpSdFhlmcDocumentFileCd.doesNotContain=" + UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd equals to DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.equals=" + DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd equals to UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.equals=" + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd not equals to DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.notEquals=" + DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd not equals to UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.notEquals=" + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd in DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD or UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.in=" + DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD + "," + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd equals to UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.in=" + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd is not null
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.specified=true");

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd is null
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd contains DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.contains=" + DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd contains UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.contains=" + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpSdEadSubmissionStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd does not contain DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldNotBeFound("ordUcdpSdEadSubmissionStatusCd.doesNotContain=" + DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);

        // Get all the ordersList where ordUcdpSdEadSubmissionStatusCd does not contain UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD
        defaultOrdersShouldBeFound("ordUcdpSdEadSubmissionStatusCd.doesNotContain=" + UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt equals to DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.equals=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt equals to UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.equals=" + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt not equals to DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.notEquals=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt not equals to UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.notEquals=" + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt in DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT or UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.in=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT + "," + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt equals to UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.in=" + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is not null
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.specified=true");

        // Get all the ordersList where ordUcdpFinalSubmissionDt is null
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is greater than or equal to DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.greaterThanOrEqual=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is greater than or equal to UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.greaterThanOrEqual=" + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is less than or equal to DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.lessThanOrEqual=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is less than or equal to SMALLER_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.lessThanOrEqual=" + SMALLER_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is less than DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.lessThan=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is less than UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.lessThan=" + UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdUcdpFinalSubmissionDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is greater than DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldNotBeFound("ordUcdpFinalSubmissionDt.greaterThan=" + DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT);

        // Get all the ordersList where ordUcdpFinalSubmissionDt is greater than SMALLER_ORD_UCDP_FINAL_SUBMISSION_DT
        defaultOrdersShouldBeFound("ordUcdpFinalSubmissionDt.greaterThan=" + SMALLER_ORD_UCDP_FINAL_SUBMISSION_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr equals to DEFAULT_ORD_LOAN_NBR
        defaultOrdersShouldBeFound("ordLoanNbr.equals=" + DEFAULT_ORD_LOAN_NBR);

        // Get all the ordersList where ordLoanNbr equals to UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordLoanNbr.equals=" + UPDATED_ORD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr not equals to DEFAULT_ORD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordLoanNbr.notEquals=" + DEFAULT_ORD_LOAN_NBR);

        // Get all the ordersList where ordLoanNbr not equals to UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldBeFound("ordLoanNbr.notEquals=" + UPDATED_ORD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr in DEFAULT_ORD_LOAN_NBR or UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldBeFound("ordLoanNbr.in=" + DEFAULT_ORD_LOAN_NBR + "," + UPDATED_ORD_LOAN_NBR);

        // Get all the ordersList where ordLoanNbr equals to UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordLoanNbr.in=" + UPDATED_ORD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr is not null
        defaultOrdersShouldBeFound("ordLoanNbr.specified=true");

        // Get all the ordersList where ordLoanNbr is null
        defaultOrdersShouldNotBeFound("ordLoanNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr contains DEFAULT_ORD_LOAN_NBR
        defaultOrdersShouldBeFound("ordLoanNbr.contains=" + DEFAULT_ORD_LOAN_NBR);

        // Get all the ordersList where ordLoanNbr contains UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordLoanNbr.contains=" + UPDATED_ORD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNbr does not contain DEFAULT_ORD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordLoanNbr.doesNotContain=" + DEFAULT_ORD_LOAN_NBR);

        // Get all the ordersList where ordLoanNbr does not contain UPDATED_ORD_LOAN_NBR
        defaultOrdersShouldBeFound("ordLoanNbr.doesNotContain=" + UPDATED_ORD_LOAN_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr equals to DEFAULT_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.equals=" + DEFAULT_ORDLOAN_OLD_LOAN_NBR);

        // Get all the ordersList where ordloanOldLoanNbr equals to UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.equals=" + UPDATED_ORDLOAN_OLD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr not equals to DEFAULT_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.notEquals=" + DEFAULT_ORDLOAN_OLD_LOAN_NBR);

        // Get all the ordersList where ordloanOldLoanNbr not equals to UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.notEquals=" + UPDATED_ORDLOAN_OLD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr in DEFAULT_ORDLOAN_OLD_LOAN_NBR or UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.in=" + DEFAULT_ORDLOAN_OLD_LOAN_NBR + "," + UPDATED_ORDLOAN_OLD_LOAN_NBR);

        // Get all the ordersList where ordloanOldLoanNbr equals to UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.in=" + UPDATED_ORDLOAN_OLD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr is not null
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.specified=true");

        // Get all the ordersList where ordloanOldLoanNbr is null
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr contains DEFAULT_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.contains=" + DEFAULT_ORDLOAN_OLD_LOAN_NBR);

        // Get all the ordersList where ordloanOldLoanNbr contains UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.contains=" + UPDATED_ORDLOAN_OLD_LOAN_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdloanOldLoanNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordloanOldLoanNbr does not contain DEFAULT_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldNotBeFound("ordloanOldLoanNbr.doesNotContain=" + DEFAULT_ORDLOAN_OLD_LOAN_NBR);

        // Get all the ordersList where ordloanOldLoanNbr does not contain UPDATED_ORDLOAN_OLD_LOAN_NBR
        defaultOrdersShouldBeFound("ordloanOldLoanNbr.doesNotContain=" + UPDATED_ORDLOAN_OLD_LOAN_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram equals to DEFAULT_ORD_LOAN_PROGRAM
        defaultOrdersShouldBeFound("ordLoanProgram.equals=" + DEFAULT_ORD_LOAN_PROGRAM);

        // Get all the ordersList where ordLoanProgram equals to UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldNotBeFound("ordLoanProgram.equals=" + UPDATED_ORD_LOAN_PROGRAM);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram not equals to DEFAULT_ORD_LOAN_PROGRAM
        defaultOrdersShouldNotBeFound("ordLoanProgram.notEquals=" + DEFAULT_ORD_LOAN_PROGRAM);

        // Get all the ordersList where ordLoanProgram not equals to UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldBeFound("ordLoanProgram.notEquals=" + UPDATED_ORD_LOAN_PROGRAM);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram in DEFAULT_ORD_LOAN_PROGRAM or UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldBeFound("ordLoanProgram.in=" + DEFAULT_ORD_LOAN_PROGRAM + "," + UPDATED_ORD_LOAN_PROGRAM);

        // Get all the ordersList where ordLoanProgram equals to UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldNotBeFound("ordLoanProgram.in=" + UPDATED_ORD_LOAN_PROGRAM);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram is not null
        defaultOrdersShouldBeFound("ordLoanProgram.specified=true");

        // Get all the ordersList where ordLoanProgram is null
        defaultOrdersShouldNotBeFound("ordLoanProgram.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram contains DEFAULT_ORD_LOAN_PROGRAM
        defaultOrdersShouldBeFound("ordLoanProgram.contains=" + DEFAULT_ORD_LOAN_PROGRAM);

        // Get all the ordersList where ordLoanProgram contains UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldNotBeFound("ordLoanProgram.contains=" + UPDATED_ORD_LOAN_PROGRAM);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanProgramNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanProgram does not contain DEFAULT_ORD_LOAN_PROGRAM
        defaultOrdersShouldNotBeFound("ordLoanProgram.doesNotContain=" + DEFAULT_ORD_LOAN_PROGRAM);

        // Get all the ordersList where ordLoanProgram does not contain UPDATED_ORD_LOAN_PROGRAM
        defaultOrdersShouldBeFound("ordLoanProgram.doesNotContain=" + UPDATED_ORD_LOAN_PROGRAM);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose equals to DEFAULT_ORD_LOAN_PURPOSE
        defaultOrdersShouldBeFound("ordLoanPurpose.equals=" + DEFAULT_ORD_LOAN_PURPOSE);

        // Get all the ordersList where ordLoanPurpose equals to UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldNotBeFound("ordLoanPurpose.equals=" + UPDATED_ORD_LOAN_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose not equals to DEFAULT_ORD_LOAN_PURPOSE
        defaultOrdersShouldNotBeFound("ordLoanPurpose.notEquals=" + DEFAULT_ORD_LOAN_PURPOSE);

        // Get all the ordersList where ordLoanPurpose not equals to UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldBeFound("ordLoanPurpose.notEquals=" + UPDATED_ORD_LOAN_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose in DEFAULT_ORD_LOAN_PURPOSE or UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldBeFound("ordLoanPurpose.in=" + DEFAULT_ORD_LOAN_PURPOSE + "," + UPDATED_ORD_LOAN_PURPOSE);

        // Get all the ordersList where ordLoanPurpose equals to UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldNotBeFound("ordLoanPurpose.in=" + UPDATED_ORD_LOAN_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose is not null
        defaultOrdersShouldBeFound("ordLoanPurpose.specified=true");

        // Get all the ordersList where ordLoanPurpose is null
        defaultOrdersShouldNotBeFound("ordLoanPurpose.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose contains DEFAULT_ORD_LOAN_PURPOSE
        defaultOrdersShouldBeFound("ordLoanPurpose.contains=" + DEFAULT_ORD_LOAN_PURPOSE);

        // Get all the ordersList where ordLoanPurpose contains UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldNotBeFound("ordLoanPurpose.contains=" + UPDATED_ORD_LOAN_PURPOSE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanPurposeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanPurpose does not contain DEFAULT_ORD_LOAN_PURPOSE
        defaultOrdersShouldNotBeFound("ordLoanPurpose.doesNotContain=" + DEFAULT_ORD_LOAN_PURPOSE);

        // Get all the ordersList where ordLoanPurpose does not contain UPDATED_ORD_LOAN_PURPOSE
        defaultOrdersShouldBeFound("ordLoanPurpose.doesNotContain=" + UPDATED_ORD_LOAN_PURPOSE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType equals to DEFAULT_ORD_LOAN_TYPE
        defaultOrdersShouldBeFound("ordLoanType.equals=" + DEFAULT_ORD_LOAN_TYPE);

        // Get all the ordersList where ordLoanType equals to UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldNotBeFound("ordLoanType.equals=" + UPDATED_ORD_LOAN_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType not equals to DEFAULT_ORD_LOAN_TYPE
        defaultOrdersShouldNotBeFound("ordLoanType.notEquals=" + DEFAULT_ORD_LOAN_TYPE);

        // Get all the ordersList where ordLoanType not equals to UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldBeFound("ordLoanType.notEquals=" + UPDATED_ORD_LOAN_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType in DEFAULT_ORD_LOAN_TYPE or UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldBeFound("ordLoanType.in=" + DEFAULT_ORD_LOAN_TYPE + "," + UPDATED_ORD_LOAN_TYPE);

        // Get all the ordersList where ordLoanType equals to UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldNotBeFound("ordLoanType.in=" + UPDATED_ORD_LOAN_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType is not null
        defaultOrdersShouldBeFound("ordLoanType.specified=true");

        // Get all the ordersList where ordLoanType is null
        defaultOrdersShouldNotBeFound("ordLoanType.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType contains DEFAULT_ORD_LOAN_TYPE
        defaultOrdersShouldBeFound("ordLoanType.contains=" + DEFAULT_ORD_LOAN_TYPE);

        // Get all the ordersList where ordLoanType contains UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldNotBeFound("ordLoanType.contains=" + UPDATED_ORD_LOAN_TYPE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanTypeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanType does not contain DEFAULT_ORD_LOAN_TYPE
        defaultOrdersShouldNotBeFound("ordLoanType.doesNotContain=" + DEFAULT_ORD_LOAN_TYPE);

        // Get all the ordersList where ordLoanType does not contain UPDATED_ORD_LOAN_TYPE
        defaultOrdersShouldBeFound("ordLoanType.doesNotContain=" + UPDATED_ORD_LOAN_TYPE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt equals to DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.equals=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt equals to UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.equals=" + UPDATED_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt not equals to DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.notEquals=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt not equals to UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.notEquals=" + UPDATED_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt in DEFAULT_ORD_LOAN_AMT or UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.in=" + DEFAULT_ORD_LOAN_AMT + "," + UPDATED_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt equals to UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.in=" + UPDATED_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt is not null
        defaultOrdersShouldBeFound("ordLoanAmt.specified=true");

        // Get all the ordersList where ordLoanAmt is null
        defaultOrdersShouldNotBeFound("ordLoanAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt is greater than or equal to DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.greaterThanOrEqual=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt is greater than or equal to UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.greaterThanOrEqual=" + UPDATED_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt is less than or equal to DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.lessThanOrEqual=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt is less than or equal to SMALLER_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.lessThanOrEqual=" + SMALLER_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt is less than DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.lessThan=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt is less than UPDATED_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.lessThan=" + UPDATED_ORD_LOAN_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanAmt is greater than DEFAULT_ORD_LOAN_AMT
        defaultOrdersShouldNotBeFound("ordLoanAmt.greaterThan=" + DEFAULT_ORD_LOAN_AMT);

        // Get all the ordersList where ordLoanAmt is greater than SMALLER_ORD_LOAN_AMT
        defaultOrdersShouldBeFound("ordLoanAmt.greaterThan=" + SMALLER_ORD_LOAN_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt equals to DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.equals=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt equals to UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.equals=" + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt not equals to DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.notEquals=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt not equals to UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.notEquals=" + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt in DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT or UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.in=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT + "," + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt equals to UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.in=" + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt is not null
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.specified=true");

        // Get all the ordersList where ordLoanQualifyingValueAmt is null
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt is greater than or equal to DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt is greater than or equal to UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.greaterThanOrEqual=" + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt is less than or equal to DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.lessThanOrEqual=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt is less than or equal to SMALLER_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.lessThanOrEqual=" + SMALLER_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt is less than DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.lessThan=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt is less than UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.lessThan=" + UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanQualifyingValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanQualifyingValueAmt is greater than DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordLoanQualifyingValueAmt.greaterThan=" + DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT);

        // Get all the ordersList where ordLoanQualifyingValueAmt is greater than SMALLER_ORD_LOAN_QUALIFYING_VALUE_AMT
        defaultOrdersShouldBeFound("ordLoanQualifyingValueAmt.greaterThan=" + SMALLER_ORD_LOAN_QUALIFYING_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt equals to DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.equals=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt equals to UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.equals=" + UPDATED_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt not equals to DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.notEquals=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt not equals to UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.notEquals=" + UPDATED_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt in DEFAULT_ORD_LOAN_SALES_PRICE_AMT or UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.in=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT + "," + UPDATED_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt equals to UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.in=" + UPDATED_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt is not null
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.specified=true");

        // Get all the ordersList where ordLoanSalesPriceAmt is null
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt is greater than or equal to DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.greaterThanOrEqual=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt is greater than or equal to UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.greaterThanOrEqual=" + UPDATED_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt is less than or equal to DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.lessThanOrEqual=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt is less than or equal to SMALLER_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.lessThanOrEqual=" + SMALLER_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt is less than DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.lessThan=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt is less than UPDATED_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.lessThan=" + UPDATED_ORD_LOAN_SALES_PRICE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanSalesPriceAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanSalesPriceAmt is greater than DEFAULT_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldNotBeFound("ordLoanSalesPriceAmt.greaterThan=" + DEFAULT_ORD_LOAN_SALES_PRICE_AMT);

        // Get all the ordersList where ordLoanSalesPriceAmt is greater than SMALLER_ORD_LOAN_SALES_PRICE_AMT
        defaultOrdersShouldBeFound("ordLoanSalesPriceAmt.greaterThan=" + SMALLER_ORD_LOAN_SALES_PRICE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName equals to DEFAULT_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.equals=" + DEFAULT_ORD_LOAN_BORROWER_LAST_NAME);

        // Get all the ordersList where ordLoanBorrowerLastName equals to UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.equals=" + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName not equals to DEFAULT_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.notEquals=" + DEFAULT_ORD_LOAN_BORROWER_LAST_NAME);

        // Get all the ordersList where ordLoanBorrowerLastName not equals to UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.notEquals=" + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName in DEFAULT_ORD_LOAN_BORROWER_LAST_NAME or UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.in=" + DEFAULT_ORD_LOAN_BORROWER_LAST_NAME + "," + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);

        // Get all the ordersList where ordLoanBorrowerLastName equals to UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.in=" + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName is not null
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.specified=true");

        // Get all the ordersList where ordLoanBorrowerLastName is null
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName contains DEFAULT_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.contains=" + DEFAULT_ORD_LOAN_BORROWER_LAST_NAME);

        // Get all the ordersList where ordLoanBorrowerLastName contains UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.contains=" + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerLastName does not contain DEFAULT_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerLastName.doesNotContain=" + DEFAULT_ORD_LOAN_BORROWER_LAST_NAME);

        // Get all the ordersList where ordLoanBorrowerLastName does not contain UPDATED_ORD_LOAN_BORROWER_LAST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerLastName.doesNotContain=" + UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName equals to DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.equals=" + DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME);

        // Get all the ordersList where ordLoanBorrowerFirstName equals to UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.equals=" + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName not equals to DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.notEquals=" + DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME);

        // Get all the ordersList where ordLoanBorrowerFirstName not equals to UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.notEquals=" + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName in DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME or UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.in=" + DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME + "," + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);

        // Get all the ordersList where ordLoanBorrowerFirstName equals to UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.in=" + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName is not null
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.specified=true");

        // Get all the ordersList where ordLoanBorrowerFirstName is null
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName contains DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.contains=" + DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME);

        // Get all the ordersList where ordLoanBorrowerFirstName contains UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.contains=" + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerFirstName does not contain DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerFirstName.doesNotContain=" + DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME);

        // Get all the ordersList where ordLoanBorrowerFirstName does not contain UPDATED_ORD_LOAN_BORROWER_FIRST_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerFirstName.doesNotContain=" + UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName equals to DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.equals=" + DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME);

        // Get all the ordersList where ordLoanBorrowerMiddleName equals to UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.equals=" + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName not equals to DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.notEquals=" + DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME);

        // Get all the ordersList where ordLoanBorrowerMiddleName not equals to UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.notEquals=" + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName in DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME or UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.in=" + DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME + "," + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);

        // Get all the ordersList where ordLoanBorrowerMiddleName equals to UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.in=" + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName is not null
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.specified=true");

        // Get all the ordersList where ordLoanBorrowerMiddleName is null
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName contains DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.contains=" + DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME);

        // Get all the ordersList where ordLoanBorrowerMiddleName contains UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.contains=" + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerMiddleNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerMiddleName does not contain DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldNotBeFound("ordLoanBorrowerMiddleName.doesNotContain=" + DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME);

        // Get all the ordersList where ordLoanBorrowerMiddleName does not contain UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME
        defaultOrdersShouldBeFound("ordLoanBorrowerMiddleName.doesNotContain=" + UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerIsCoBorrowerIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd equals to DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldBeFound("ordLoanBorrowerIsCoBorrowerInd.equals=" + DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd equals to UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldNotBeFound("ordLoanBorrowerIsCoBorrowerInd.equals=" + UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerIsCoBorrowerIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd not equals to DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldNotBeFound("ordLoanBorrowerIsCoBorrowerInd.notEquals=" + DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd not equals to UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldBeFound("ordLoanBorrowerIsCoBorrowerInd.notEquals=" + UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerIsCoBorrowerIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd in DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND or UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldBeFound("ordLoanBorrowerIsCoBorrowerInd.in=" + DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND + "," + UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd equals to UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND
        defaultOrdersShouldNotBeFound("ordLoanBorrowerIsCoBorrowerInd.in=" + UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerIsCoBorrowerIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd is not null
        defaultOrdersShouldBeFound("ordLoanBorrowerIsCoBorrowerInd.specified=true");

        // Get all the ordersList where ordLoanBorrowerIsCoBorrowerInd is null
        defaultOrdersShouldNotBeFound("ordLoanBorrowerIsCoBorrowerInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr equals to DEFAULT_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.equals=" + DEFAULT_ORD_LOAN_FHA_CASE_NBR);

        // Get all the ordersList where ordLoanFhaCaseNbr equals to UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.equals=" + UPDATED_ORD_LOAN_FHA_CASE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr not equals to DEFAULT_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.notEquals=" + DEFAULT_ORD_LOAN_FHA_CASE_NBR);

        // Get all the ordersList where ordLoanFhaCaseNbr not equals to UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.notEquals=" + UPDATED_ORD_LOAN_FHA_CASE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr in DEFAULT_ORD_LOAN_FHA_CASE_NBR or UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.in=" + DEFAULT_ORD_LOAN_FHA_CASE_NBR + "," + UPDATED_ORD_LOAN_FHA_CASE_NBR);

        // Get all the ordersList where ordLoanFhaCaseNbr equals to UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.in=" + UPDATED_ORD_LOAN_FHA_CASE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr is not null
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.specified=true");

        // Get all the ordersList where ordLoanFhaCaseNbr is null
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr contains DEFAULT_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.contains=" + DEFAULT_ORD_LOAN_FHA_CASE_NBR);

        // Get all the ordersList where ordLoanFhaCaseNbr contains UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.contains=" + UPDATED_ORD_LOAN_FHA_CASE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanFhaCaseNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanFhaCaseNbr does not contain DEFAULT_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldNotBeFound("ordLoanFhaCaseNbr.doesNotContain=" + DEFAULT_ORD_LOAN_FHA_CASE_NBR);

        // Get all the ordersList where ordLoanFhaCaseNbr does not contain UPDATED_ORD_LOAN_FHA_CASE_NBR
        defaultOrdersShouldBeFound("ordLoanFhaCaseNbr.doesNotContain=" + UPDATED_ORD_LOAN_FHA_CASE_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanDeedRestrictionIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanDeedRestrictionInd equals to DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldBeFound("ordLoanDeedRestrictionInd.equals=" + DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND);

        // Get all the ordersList where ordLoanDeedRestrictionInd equals to UPDATED_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldNotBeFound("ordLoanDeedRestrictionInd.equals=" + UPDATED_ORD_LOAN_DEED_RESTRICTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanDeedRestrictionIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanDeedRestrictionInd not equals to DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldNotBeFound("ordLoanDeedRestrictionInd.notEquals=" + DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND);

        // Get all the ordersList where ordLoanDeedRestrictionInd not equals to UPDATED_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldBeFound("ordLoanDeedRestrictionInd.notEquals=" + UPDATED_ORD_LOAN_DEED_RESTRICTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanDeedRestrictionIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanDeedRestrictionInd in DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND or UPDATED_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldBeFound("ordLoanDeedRestrictionInd.in=" + DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND + "," + UPDATED_ORD_LOAN_DEED_RESTRICTION_IND);

        // Get all the ordersList where ordLoanDeedRestrictionInd equals to UPDATED_ORD_LOAN_DEED_RESTRICTION_IND
        defaultOrdersShouldNotBeFound("ordLoanDeedRestrictionInd.in=" + UPDATED_ORD_LOAN_DEED_RESTRICTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanDeedRestrictionIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanDeedRestrictionInd is not null
        defaultOrdersShouldBeFound("ordLoanDeedRestrictionInd.specified=true");

        // Get all the ordersList where ordLoanDeedRestrictionInd is null
        defaultOrdersShouldNotBeFound("ordLoanDeedRestrictionInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt equals to DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.equals=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt equals to UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.equals=" + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt not equals to DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.notEquals=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt not equals to UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.notEquals=" + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt in DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT or UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.in=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT + "," + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt equals to UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.in=" + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt is not null
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.specified=true");

        // Get all the ordersList where ordLoanEstimatedCloseDt is null
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt is greater than or equal to DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.greaterThanOrEqual=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt is greater than or equal to UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.greaterThanOrEqual=" + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt is less than or equal to DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.lessThanOrEqual=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt is less than or equal to SMALLER_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.lessThanOrEqual=" + SMALLER_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt is less than DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.lessThan=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt is less than UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.lessThan=" + UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanEstimatedCloseDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanEstimatedCloseDt is greater than DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanEstimatedCloseDt.greaterThan=" + DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT);

        // Get all the ordersList where ordLoanEstimatedCloseDt is greater than SMALLER_ORD_LOAN_ESTIMATED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanEstimatedCloseDt.greaterThan=" + SMALLER_ORD_LOAN_ESTIMATED_CLOSE_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanHpmlIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanHpmlInd equals to DEFAULT_ORD_LOAN_HPML_IND
        defaultOrdersShouldBeFound("ordLoanHpmlInd.equals=" + DEFAULT_ORD_LOAN_HPML_IND);

        // Get all the ordersList where ordLoanHpmlInd equals to UPDATED_ORD_LOAN_HPML_IND
        defaultOrdersShouldNotBeFound("ordLoanHpmlInd.equals=" + UPDATED_ORD_LOAN_HPML_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanHpmlIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanHpmlInd not equals to DEFAULT_ORD_LOAN_HPML_IND
        defaultOrdersShouldNotBeFound("ordLoanHpmlInd.notEquals=" + DEFAULT_ORD_LOAN_HPML_IND);

        // Get all the ordersList where ordLoanHpmlInd not equals to UPDATED_ORD_LOAN_HPML_IND
        defaultOrdersShouldBeFound("ordLoanHpmlInd.notEquals=" + UPDATED_ORD_LOAN_HPML_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanHpmlIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanHpmlInd in DEFAULT_ORD_LOAN_HPML_IND or UPDATED_ORD_LOAN_HPML_IND
        defaultOrdersShouldBeFound("ordLoanHpmlInd.in=" + DEFAULT_ORD_LOAN_HPML_IND + "," + UPDATED_ORD_LOAN_HPML_IND);

        // Get all the ordersList where ordLoanHpmlInd equals to UPDATED_ORD_LOAN_HPML_IND
        defaultOrdersShouldNotBeFound("ordLoanHpmlInd.in=" + UPDATED_ORD_LOAN_HPML_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanHpmlIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanHpmlInd is not null
        defaultOrdersShouldBeFound("ordLoanHpmlInd.specified=true");

        // Get all the ordersList where ordLoanHpmlInd is null
        defaultOrdersShouldNotBeFound("ordLoanHpmlInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanIsNewConstructionIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanIsNewConstructionInd equals to DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldBeFound("ordLoanIsNewConstructionInd.equals=" + DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);

        // Get all the ordersList where ordLoanIsNewConstructionInd equals to UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldNotBeFound("ordLoanIsNewConstructionInd.equals=" + UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanIsNewConstructionIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanIsNewConstructionInd not equals to DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldNotBeFound("ordLoanIsNewConstructionInd.notEquals=" + DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);

        // Get all the ordersList where ordLoanIsNewConstructionInd not equals to UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldBeFound("ordLoanIsNewConstructionInd.notEquals=" + UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanIsNewConstructionIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanIsNewConstructionInd in DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND or UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldBeFound("ordLoanIsNewConstructionInd.in=" + DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND + "," + UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);

        // Get all the ordersList where ordLoanIsNewConstructionInd equals to UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND
        defaultOrdersShouldNotBeFound("ordLoanIsNewConstructionInd.in=" + UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanIsNewConstructionIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanIsNewConstructionInd is not null
        defaultOrdersShouldBeFound("ordLoanIsNewConstructionInd.specified=true");

        // Get all the ordersList where ordLoanIsNewConstructionInd is null
        defaultOrdersShouldNotBeFound("ordLoanIsNewConstructionInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode equals to DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.equals=" + DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE);

        // Get all the ordersList where ordLoanCustomerSegmentCode equals to UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.equals=" + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode not equals to DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.notEquals=" + DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE);

        // Get all the ordersList where ordLoanCustomerSegmentCode not equals to UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.notEquals=" + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode in DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE or UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.in=" + DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE + "," + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);

        // Get all the ordersList where ordLoanCustomerSegmentCode equals to UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.in=" + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode is not null
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.specified=true");

        // Get all the ordersList where ordLoanCustomerSegmentCode is null
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode contains DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.contains=" + DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE);

        // Get all the ordersList where ordLoanCustomerSegmentCode contains UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.contains=" + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanCustomerSegmentCodeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanCustomerSegmentCode does not contain DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldNotBeFound("ordLoanCustomerSegmentCode.doesNotContain=" + DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE);

        // Get all the ordersList where ordLoanCustomerSegmentCode does not contain UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE
        defaultOrdersShouldBeFound("ordLoanCustomerSegmentCode.doesNotContain=" + UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNonSubjectPropertyIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd equals to DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldBeFound("ordLoanNonSubjectPropertyInd.equals=" + DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd equals to UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldNotBeFound("ordLoanNonSubjectPropertyInd.equals=" + UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNonSubjectPropertyIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd not equals to DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldNotBeFound("ordLoanNonSubjectPropertyInd.notEquals=" + DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd not equals to UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldBeFound("ordLoanNonSubjectPropertyInd.notEquals=" + UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNonSubjectPropertyIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd in DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND or UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldBeFound("ordLoanNonSubjectPropertyInd.in=" + DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND + "," + UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd equals to UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND
        defaultOrdersShouldNotBeFound("ordLoanNonSubjectPropertyInd.in=" + UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanNonSubjectPropertyIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanNonSubjectPropertyInd is not null
        defaultOrdersShouldBeFound("ordLoanNonSubjectPropertyInd.specified=true");

        // Get all the ordersList where ordLoanNonSubjectPropertyInd is null
        defaultOrdersShouldNotBeFound("ordLoanNonSubjectPropertyInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt equals to DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.equals=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt equals to UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.equals=" + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt not equals to DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.notEquals=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt not equals to UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.notEquals=" + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt in DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT or UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.in=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT + "," + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt equals to UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.in=" + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is not null
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.specified=true");

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is null
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is greater than or equal to DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.greaterThanOrEqual=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is greater than or equal to UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.greaterThanOrEqual=" + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is less than or equal to DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.lessThanOrEqual=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is less than or equal to SMALLER_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.lessThanOrEqual=" + SMALLER_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is less than DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.lessThan=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is less than UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.lessThan=" + UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdLoanBorrowerRequestedCloseDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is greater than DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldNotBeFound("ordLoanBorrowerRequestedCloseDt.greaterThan=" + DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);

        // Get all the ordersList where ordLoanBorrowerRequestedCloseDt is greater than SMALLER_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT
        defaultOrdersShouldBeFound("ordLoanBorrowerRequestedCloseDt.greaterThan=" + SMALLER_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd equals to DEFAULT_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldBeFound("ordPropertyTypeCd.equals=" + DEFAULT_ORD_PROPERTY_TYPE_CD);

        // Get all the ordersList where ordPropertyTypeCd equals to UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.equals=" + UPDATED_ORD_PROPERTY_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd not equals to DEFAULT_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.notEquals=" + DEFAULT_ORD_PROPERTY_TYPE_CD);

        // Get all the ordersList where ordPropertyTypeCd not equals to UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldBeFound("ordPropertyTypeCd.notEquals=" + UPDATED_ORD_PROPERTY_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd in DEFAULT_ORD_PROPERTY_TYPE_CD or UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldBeFound("ordPropertyTypeCd.in=" + DEFAULT_ORD_PROPERTY_TYPE_CD + "," + UPDATED_ORD_PROPERTY_TYPE_CD);

        // Get all the ordersList where ordPropertyTypeCd equals to UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.in=" + UPDATED_ORD_PROPERTY_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd is not null
        defaultOrdersShouldBeFound("ordPropertyTypeCd.specified=true");

        // Get all the ordersList where ordPropertyTypeCd is null
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd contains DEFAULT_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldBeFound("ordPropertyTypeCd.contains=" + DEFAULT_ORD_PROPERTY_TYPE_CD);

        // Get all the ordersList where ordPropertyTypeCd contains UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.contains=" + UPDATED_ORD_PROPERTY_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyTypeCd does not contain DEFAULT_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldNotBeFound("ordPropertyTypeCd.doesNotContain=" + DEFAULT_ORD_PROPERTY_TYPE_CD);

        // Get all the ordersList where ordPropertyTypeCd does not contain UPDATED_ORD_PROPERTY_TYPE_CD
        defaultOrdersShouldBeFound("ordPropertyTypeCd.doesNotContain=" + UPDATED_ORD_PROPERTY_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1IsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 equals to DEFAULT_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldBeFound("ordPropertyAddress1.equals=" + DEFAULT_ORD_PROPERTY_ADDRESS_1);

        // Get all the ordersList where ordPropertyAddress1 equals to UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.equals=" + UPDATED_ORD_PROPERTY_ADDRESS_1);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 not equals to DEFAULT_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.notEquals=" + DEFAULT_ORD_PROPERTY_ADDRESS_1);

        // Get all the ordersList where ordPropertyAddress1 not equals to UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldBeFound("ordPropertyAddress1.notEquals=" + UPDATED_ORD_PROPERTY_ADDRESS_1);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1IsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 in DEFAULT_ORD_PROPERTY_ADDRESS_1 or UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldBeFound("ordPropertyAddress1.in=" + DEFAULT_ORD_PROPERTY_ADDRESS_1 + "," + UPDATED_ORD_PROPERTY_ADDRESS_1);

        // Get all the ordersList where ordPropertyAddress1 equals to UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.in=" + UPDATED_ORD_PROPERTY_ADDRESS_1);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1IsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 is not null
        defaultOrdersShouldBeFound("ordPropertyAddress1.specified=true");

        // Get all the ordersList where ordPropertyAddress1 is null
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1ContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 contains DEFAULT_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldBeFound("ordPropertyAddress1.contains=" + DEFAULT_ORD_PROPERTY_ADDRESS_1);

        // Get all the ordersList where ordPropertyAddress1 contains UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.contains=" + UPDATED_ORD_PROPERTY_ADDRESS_1);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress1NotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress1 does not contain DEFAULT_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldNotBeFound("ordPropertyAddress1.doesNotContain=" + DEFAULT_ORD_PROPERTY_ADDRESS_1);

        // Get all the ordersList where ordPropertyAddress1 does not contain UPDATED_ORD_PROPERTY_ADDRESS_1
        defaultOrdersShouldBeFound("ordPropertyAddress1.doesNotContain=" + UPDATED_ORD_PROPERTY_ADDRESS_1);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2IsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 equals to DEFAULT_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldBeFound("ordPropertyAddress2.equals=" + DEFAULT_ORD_PROPERTY_ADDRESS_2);

        // Get all the ordersList where ordPropertyAddress2 equals to UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.equals=" + UPDATED_ORD_PROPERTY_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 not equals to DEFAULT_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.notEquals=" + DEFAULT_ORD_PROPERTY_ADDRESS_2);

        // Get all the ordersList where ordPropertyAddress2 not equals to UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldBeFound("ordPropertyAddress2.notEquals=" + UPDATED_ORD_PROPERTY_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2IsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 in DEFAULT_ORD_PROPERTY_ADDRESS_2 or UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldBeFound("ordPropertyAddress2.in=" + DEFAULT_ORD_PROPERTY_ADDRESS_2 + "," + UPDATED_ORD_PROPERTY_ADDRESS_2);

        // Get all the ordersList where ordPropertyAddress2 equals to UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.in=" + UPDATED_ORD_PROPERTY_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2IsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 is not null
        defaultOrdersShouldBeFound("ordPropertyAddress2.specified=true");

        // Get all the ordersList where ordPropertyAddress2 is null
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2ContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 contains DEFAULT_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldBeFound("ordPropertyAddress2.contains=" + DEFAULT_ORD_PROPERTY_ADDRESS_2);

        // Get all the ordersList where ordPropertyAddress2 contains UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.contains=" + UPDATED_ORD_PROPERTY_ADDRESS_2);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyAddress2NotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyAddress2 does not contain DEFAULT_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldNotBeFound("ordPropertyAddress2.doesNotContain=" + DEFAULT_ORD_PROPERTY_ADDRESS_2);

        // Get all the ordersList where ordPropertyAddress2 does not contain UPDATED_ORD_PROPERTY_ADDRESS_2
        defaultOrdersShouldBeFound("ordPropertyAddress2.doesNotContain=" + UPDATED_ORD_PROPERTY_ADDRESS_2);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity equals to DEFAULT_ORD_PROPERTY_CITY
        defaultOrdersShouldBeFound("ordPropertyCity.equals=" + DEFAULT_ORD_PROPERTY_CITY);

        // Get all the ordersList where ordPropertyCity equals to UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldNotBeFound("ordPropertyCity.equals=" + UPDATED_ORD_PROPERTY_CITY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity not equals to DEFAULT_ORD_PROPERTY_CITY
        defaultOrdersShouldNotBeFound("ordPropertyCity.notEquals=" + DEFAULT_ORD_PROPERTY_CITY);

        // Get all the ordersList where ordPropertyCity not equals to UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldBeFound("ordPropertyCity.notEquals=" + UPDATED_ORD_PROPERTY_CITY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity in DEFAULT_ORD_PROPERTY_CITY or UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldBeFound("ordPropertyCity.in=" + DEFAULT_ORD_PROPERTY_CITY + "," + UPDATED_ORD_PROPERTY_CITY);

        // Get all the ordersList where ordPropertyCity equals to UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldNotBeFound("ordPropertyCity.in=" + UPDATED_ORD_PROPERTY_CITY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity is not null
        defaultOrdersShouldBeFound("ordPropertyCity.specified=true");

        // Get all the ordersList where ordPropertyCity is null
        defaultOrdersShouldNotBeFound("ordPropertyCity.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity contains DEFAULT_ORD_PROPERTY_CITY
        defaultOrdersShouldBeFound("ordPropertyCity.contains=" + DEFAULT_ORD_PROPERTY_CITY);

        // Get all the ordersList where ordPropertyCity contains UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldNotBeFound("ordPropertyCity.contains=" + UPDATED_ORD_PROPERTY_CITY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCityNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCity does not contain DEFAULT_ORD_PROPERTY_CITY
        defaultOrdersShouldNotBeFound("ordPropertyCity.doesNotContain=" + DEFAULT_ORD_PROPERTY_CITY);

        // Get all the ordersList where ordPropertyCity does not contain UPDATED_ORD_PROPERTY_CITY
        defaultOrdersShouldBeFound("ordPropertyCity.doesNotContain=" + UPDATED_ORD_PROPERTY_CITY);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd equals to DEFAULT_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldBeFound("ordPropertyStateCd.equals=" + DEFAULT_ORD_PROPERTY_STATE_CD);

        // Get all the ordersList where ordPropertyStateCd equals to UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.equals=" + UPDATED_ORD_PROPERTY_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd not equals to DEFAULT_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.notEquals=" + DEFAULT_ORD_PROPERTY_STATE_CD);

        // Get all the ordersList where ordPropertyStateCd not equals to UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldBeFound("ordPropertyStateCd.notEquals=" + UPDATED_ORD_PROPERTY_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd in DEFAULT_ORD_PROPERTY_STATE_CD or UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldBeFound("ordPropertyStateCd.in=" + DEFAULT_ORD_PROPERTY_STATE_CD + "," + UPDATED_ORD_PROPERTY_STATE_CD);

        // Get all the ordersList where ordPropertyStateCd equals to UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.in=" + UPDATED_ORD_PROPERTY_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd is not null
        defaultOrdersShouldBeFound("ordPropertyStateCd.specified=true");

        // Get all the ordersList where ordPropertyStateCd is null
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd contains DEFAULT_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldBeFound("ordPropertyStateCd.contains=" + DEFAULT_ORD_PROPERTY_STATE_CD);

        // Get all the ordersList where ordPropertyStateCd contains UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.contains=" + UPDATED_ORD_PROPERTY_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyStateCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyStateCd does not contain DEFAULT_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldNotBeFound("ordPropertyStateCd.doesNotContain=" + DEFAULT_ORD_PROPERTY_STATE_CD);

        // Get all the ordersList where ordPropertyStateCd does not contain UPDATED_ORD_PROPERTY_STATE_CD
        defaultOrdersShouldBeFound("ordPropertyStateCd.doesNotContain=" + UPDATED_ORD_PROPERTY_STATE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip equals to DEFAULT_ORD_PROPERTY_ZIP
        defaultOrdersShouldBeFound("ordPropertyZip.equals=" + DEFAULT_ORD_PROPERTY_ZIP);

        // Get all the ordersList where ordPropertyZip equals to UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldNotBeFound("ordPropertyZip.equals=" + UPDATED_ORD_PROPERTY_ZIP);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip not equals to DEFAULT_ORD_PROPERTY_ZIP
        defaultOrdersShouldNotBeFound("ordPropertyZip.notEquals=" + DEFAULT_ORD_PROPERTY_ZIP);

        // Get all the ordersList where ordPropertyZip not equals to UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldBeFound("ordPropertyZip.notEquals=" + UPDATED_ORD_PROPERTY_ZIP);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip in DEFAULT_ORD_PROPERTY_ZIP or UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldBeFound("ordPropertyZip.in=" + DEFAULT_ORD_PROPERTY_ZIP + "," + UPDATED_ORD_PROPERTY_ZIP);

        // Get all the ordersList where ordPropertyZip equals to UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldNotBeFound("ordPropertyZip.in=" + UPDATED_ORD_PROPERTY_ZIP);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip is not null
        defaultOrdersShouldBeFound("ordPropertyZip.specified=true");

        // Get all the ordersList where ordPropertyZip is null
        defaultOrdersShouldNotBeFound("ordPropertyZip.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip contains DEFAULT_ORD_PROPERTY_ZIP
        defaultOrdersShouldBeFound("ordPropertyZip.contains=" + DEFAULT_ORD_PROPERTY_ZIP);

        // Get all the ordersList where ordPropertyZip contains UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldNotBeFound("ordPropertyZip.contains=" + UPDATED_ORD_PROPERTY_ZIP);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyZipNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyZip does not contain DEFAULT_ORD_PROPERTY_ZIP
        defaultOrdersShouldNotBeFound("ordPropertyZip.doesNotContain=" + DEFAULT_ORD_PROPERTY_ZIP);

        // Get all the ordersList where ordPropertyZip does not contain UPDATED_ORD_PROPERTY_ZIP
        defaultOrdersShouldBeFound("ordPropertyZip.doesNotContain=" + UPDATED_ORD_PROPERTY_ZIP);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty equals to DEFAULT_ORD_PROPERTY_COUNTY
        defaultOrdersShouldBeFound("ordPropertyCounty.equals=" + DEFAULT_ORD_PROPERTY_COUNTY);

        // Get all the ordersList where ordPropertyCounty equals to UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldNotBeFound("ordPropertyCounty.equals=" + UPDATED_ORD_PROPERTY_COUNTY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty not equals to DEFAULT_ORD_PROPERTY_COUNTY
        defaultOrdersShouldNotBeFound("ordPropertyCounty.notEquals=" + DEFAULT_ORD_PROPERTY_COUNTY);

        // Get all the ordersList where ordPropertyCounty not equals to UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldBeFound("ordPropertyCounty.notEquals=" + UPDATED_ORD_PROPERTY_COUNTY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty in DEFAULT_ORD_PROPERTY_COUNTY or UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldBeFound("ordPropertyCounty.in=" + DEFAULT_ORD_PROPERTY_COUNTY + "," + UPDATED_ORD_PROPERTY_COUNTY);

        // Get all the ordersList where ordPropertyCounty equals to UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldNotBeFound("ordPropertyCounty.in=" + UPDATED_ORD_PROPERTY_COUNTY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty is not null
        defaultOrdersShouldBeFound("ordPropertyCounty.specified=true");

        // Get all the ordersList where ordPropertyCounty is null
        defaultOrdersShouldNotBeFound("ordPropertyCounty.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty contains DEFAULT_ORD_PROPERTY_COUNTY
        defaultOrdersShouldBeFound("ordPropertyCounty.contains=" + DEFAULT_ORD_PROPERTY_COUNTY);

        // Get all the ordersList where ordPropertyCounty contains UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldNotBeFound("ordPropertyCounty.contains=" + UPDATED_ORD_PROPERTY_COUNTY);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyCountyNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyCounty does not contain DEFAULT_ORD_PROPERTY_COUNTY
        defaultOrdersShouldNotBeFound("ordPropertyCounty.doesNotContain=" + DEFAULT_ORD_PROPERTY_COUNTY);

        // Get all the ordersList where ordPropertyCounty does not contain UPDATED_ORD_PROPERTY_COUNTY
        defaultOrdersShouldBeFound("ordPropertyCounty.doesNotContain=" + UPDATED_ORD_PROPERTY_COUNTY);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue equals to DEFAULT_ORD_PROPERTY_LATITUE
        defaultOrdersShouldBeFound("ordPropertyLatitue.equals=" + DEFAULT_ORD_PROPERTY_LATITUE);

        // Get all the ordersList where ordPropertyLatitue equals to UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.equals=" + UPDATED_ORD_PROPERTY_LATITUE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue not equals to DEFAULT_ORD_PROPERTY_LATITUE
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.notEquals=" + DEFAULT_ORD_PROPERTY_LATITUE);

        // Get all the ordersList where ordPropertyLatitue not equals to UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldBeFound("ordPropertyLatitue.notEquals=" + UPDATED_ORD_PROPERTY_LATITUE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue in DEFAULT_ORD_PROPERTY_LATITUE or UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldBeFound("ordPropertyLatitue.in=" + DEFAULT_ORD_PROPERTY_LATITUE + "," + UPDATED_ORD_PROPERTY_LATITUE);

        // Get all the ordersList where ordPropertyLatitue equals to UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.in=" + UPDATED_ORD_PROPERTY_LATITUE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue is not null
        defaultOrdersShouldBeFound("ordPropertyLatitue.specified=true");

        // Get all the ordersList where ordPropertyLatitue is null
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue contains DEFAULT_ORD_PROPERTY_LATITUE
        defaultOrdersShouldBeFound("ordPropertyLatitue.contains=" + DEFAULT_ORD_PROPERTY_LATITUE);

        // Get all the ordersList where ordPropertyLatitue contains UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.contains=" + UPDATED_ORD_PROPERTY_LATITUE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLatitueNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLatitue does not contain DEFAULT_ORD_PROPERTY_LATITUE
        defaultOrdersShouldNotBeFound("ordPropertyLatitue.doesNotContain=" + DEFAULT_ORD_PROPERTY_LATITUE);

        // Get all the ordersList where ordPropertyLatitue does not contain UPDATED_ORD_PROPERTY_LATITUE
        defaultOrdersShouldBeFound("ordPropertyLatitue.doesNotContain=" + UPDATED_ORD_PROPERTY_LATITUE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude equals to DEFAULT_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldBeFound("ordPropertyLongitude.equals=" + DEFAULT_ORD_PROPERTY_LONGITUDE);

        // Get all the ordersList where ordPropertyLongitude equals to UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.equals=" + UPDATED_ORD_PROPERTY_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude not equals to DEFAULT_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.notEquals=" + DEFAULT_ORD_PROPERTY_LONGITUDE);

        // Get all the ordersList where ordPropertyLongitude not equals to UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldBeFound("ordPropertyLongitude.notEquals=" + UPDATED_ORD_PROPERTY_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude in DEFAULT_ORD_PROPERTY_LONGITUDE or UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldBeFound("ordPropertyLongitude.in=" + DEFAULT_ORD_PROPERTY_LONGITUDE + "," + UPDATED_ORD_PROPERTY_LONGITUDE);

        // Get all the ordersList where ordPropertyLongitude equals to UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.in=" + UPDATED_ORD_PROPERTY_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude is not null
        defaultOrdersShouldBeFound("ordPropertyLongitude.specified=true");

        // Get all the ordersList where ordPropertyLongitude is null
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude contains DEFAULT_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldBeFound("ordPropertyLongitude.contains=" + DEFAULT_ORD_PROPERTY_LONGITUDE);

        // Get all the ordersList where ordPropertyLongitude contains UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.contains=" + UPDATED_ORD_PROPERTY_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyLongitudeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyLongitude does not contain DEFAULT_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldNotBeFound("ordPropertyLongitude.doesNotContain=" + DEFAULT_ORD_PROPERTY_LONGITUDE);

        // Get all the ordersList where ordPropertyLongitude does not contain UPDATED_ORD_PROPERTY_LONGITUDE
        defaultOrdersShouldBeFound("ordPropertyLongitude.doesNotContain=" + UPDATED_ORD_PROPERTY_LONGITUDE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName equals to DEFAULT_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldBeFound("ordPropertyProjectName.equals=" + DEFAULT_ORD_PROPERTY_PROJECT_NAME);

        // Get all the ordersList where ordPropertyProjectName equals to UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.equals=" + UPDATED_ORD_PROPERTY_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName not equals to DEFAULT_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.notEquals=" + DEFAULT_ORD_PROPERTY_PROJECT_NAME);

        // Get all the ordersList where ordPropertyProjectName not equals to UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldBeFound("ordPropertyProjectName.notEquals=" + UPDATED_ORD_PROPERTY_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName in DEFAULT_ORD_PROPERTY_PROJECT_NAME or UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldBeFound("ordPropertyProjectName.in=" + DEFAULT_ORD_PROPERTY_PROJECT_NAME + "," + UPDATED_ORD_PROPERTY_PROJECT_NAME);

        // Get all the ordersList where ordPropertyProjectName equals to UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.in=" + UPDATED_ORD_PROPERTY_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName is not null
        defaultOrdersShouldBeFound("ordPropertyProjectName.specified=true");

        // Get all the ordersList where ordPropertyProjectName is null
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName contains DEFAULT_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldBeFound("ordPropertyProjectName.contains=" + DEFAULT_ORD_PROPERTY_PROJECT_NAME);

        // Get all the ordersList where ordPropertyProjectName contains UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.contains=" + UPDATED_ORD_PROPERTY_PROJECT_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdPropertyProjectNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordPropertyProjectName does not contain DEFAULT_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldNotBeFound("ordPropertyProjectName.doesNotContain=" + DEFAULT_ORD_PROPERTY_PROJECT_NAME);

        // Get all the ordersList where ordPropertyProjectName does not contain UPDATED_ORD_PROPERTY_PROJECT_NAME
        defaultOrdersShouldBeFound("ordPropertyProjectName.doesNotContain=" + UPDATED_ORD_PROPERTY_PROJECT_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd equals to DEFAULT_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldBeFound("ordContact1TypeCd.equals=" + DEFAULT_ORD_CONTACT_1_TYPE_CD);

        // Get all the ordersList where ordContact1TypeCd equals to UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.equals=" + UPDATED_ORD_CONTACT_1_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd not equals to DEFAULT_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.notEquals=" + DEFAULT_ORD_CONTACT_1_TYPE_CD);

        // Get all the ordersList where ordContact1TypeCd not equals to UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldBeFound("ordContact1TypeCd.notEquals=" + UPDATED_ORD_CONTACT_1_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd in DEFAULT_ORD_CONTACT_1_TYPE_CD or UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldBeFound("ordContact1TypeCd.in=" + DEFAULT_ORD_CONTACT_1_TYPE_CD + "," + UPDATED_ORD_CONTACT_1_TYPE_CD);

        // Get all the ordersList where ordContact1TypeCd equals to UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.in=" + UPDATED_ORD_CONTACT_1_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd is not null
        defaultOrdersShouldBeFound("ordContact1TypeCd.specified=true");

        // Get all the ordersList where ordContact1TypeCd is null
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd contains DEFAULT_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldBeFound("ordContact1TypeCd.contains=" + DEFAULT_ORD_CONTACT_1_TYPE_CD);

        // Get all the ordersList where ordContact1TypeCd contains UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.contains=" + UPDATED_ORD_CONTACT_1_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1TypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1TypeCd does not contain DEFAULT_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact1TypeCd.doesNotContain=" + DEFAULT_ORD_CONTACT_1_TYPE_CD);

        // Get all the ordersList where ordContact1TypeCd does not contain UPDATED_ORD_CONTACT_1_TYPE_CD
        defaultOrdersShouldBeFound("ordContact1TypeCd.doesNotContain=" + UPDATED_ORD_CONTACT_1_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name equals to DEFAULT_ORD_CONTACT_1_NAME
        defaultOrdersShouldBeFound("ordContact1Name.equals=" + DEFAULT_ORD_CONTACT_1_NAME);

        // Get all the ordersList where ordContact1Name equals to UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldNotBeFound("ordContact1Name.equals=" + UPDATED_ORD_CONTACT_1_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name not equals to DEFAULT_ORD_CONTACT_1_NAME
        defaultOrdersShouldNotBeFound("ordContact1Name.notEquals=" + DEFAULT_ORD_CONTACT_1_NAME);

        // Get all the ordersList where ordContact1Name not equals to UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldBeFound("ordContact1Name.notEquals=" + UPDATED_ORD_CONTACT_1_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name in DEFAULT_ORD_CONTACT_1_NAME or UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldBeFound("ordContact1Name.in=" + DEFAULT_ORD_CONTACT_1_NAME + "," + UPDATED_ORD_CONTACT_1_NAME);

        // Get all the ordersList where ordContact1Name equals to UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldNotBeFound("ordContact1Name.in=" + UPDATED_ORD_CONTACT_1_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name is not null
        defaultOrdersShouldBeFound("ordContact1Name.specified=true");

        // Get all the ordersList where ordContact1Name is null
        defaultOrdersShouldNotBeFound("ordContact1Name.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name contains DEFAULT_ORD_CONTACT_1_NAME
        defaultOrdersShouldBeFound("ordContact1Name.contains=" + DEFAULT_ORD_CONTACT_1_NAME);

        // Get all the ordersList where ordContact1Name contains UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldNotBeFound("ordContact1Name.contains=" + UPDATED_ORD_CONTACT_1_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1NameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Name does not contain DEFAULT_ORD_CONTACT_1_NAME
        defaultOrdersShouldNotBeFound("ordContact1Name.doesNotContain=" + DEFAULT_ORD_CONTACT_1_NAME);

        // Get all the ordersList where ordContact1Name does not contain UPDATED_ORD_CONTACT_1_NAME
        defaultOrdersShouldBeFound("ordContact1Name.doesNotContain=" + UPDATED_ORD_CONTACT_1_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone equals to DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.equals=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone equals to UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.equals=" + UPDATED_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone not equals to DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.notEquals=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone not equals to UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.notEquals=" + UPDATED_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone in DEFAULT_ORD_CONTACT_1_WORK_PHONE or UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.in=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE + "," + UPDATED_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone equals to UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.in=" + UPDATED_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone is not null
        defaultOrdersShouldBeFound("ordContact1WorkPhone.specified=true");

        // Get all the ordersList where ordContact1WorkPhone is null
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone is greater than or equal to DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone is greater than or equal to UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone is less than or equal to DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone is less than or equal to SMALLER_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone is less than DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.lessThan=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone is less than UPDATED_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.lessThan=" + UPDATED_ORD_CONTACT_1_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1WorkPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1WorkPhone is greater than DEFAULT_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact1WorkPhone.greaterThan=" + DEFAULT_ORD_CONTACT_1_WORK_PHONE);

        // Get all the ordersList where ordContact1WorkPhone is greater than SMALLER_ORD_CONTACT_1_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact1WorkPhone.greaterThan=" + SMALLER_ORD_CONTACT_1_WORK_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone equals to DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.equals=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone equals to UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.equals=" + UPDATED_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone not equals to DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.notEquals=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone not equals to UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.notEquals=" + UPDATED_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone in DEFAULT_ORD_CONTACT_1_HOME_PHONE or UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.in=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE + "," + UPDATED_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone equals to UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.in=" + UPDATED_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone is not null
        defaultOrdersShouldBeFound("ordContact1HomePhone.specified=true");

        // Get all the ordersList where ordContact1HomePhone is null
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone is greater than or equal to DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone is greater than or equal to UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone is less than or equal to DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone is less than or equal to SMALLER_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone is less than DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.lessThan=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone is less than UPDATED_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.lessThan=" + UPDATED_ORD_CONTACT_1_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1HomePhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1HomePhone is greater than DEFAULT_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact1HomePhone.greaterThan=" + DEFAULT_ORD_CONTACT_1_HOME_PHONE);

        // Get all the ordersList where ordContact1HomePhone is greater than SMALLER_ORD_CONTACT_1_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact1HomePhone.greaterThan=" + SMALLER_ORD_CONTACT_1_HOME_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone equals to DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.equals=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone equals to UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.equals=" + UPDATED_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone not equals to DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.notEquals=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone not equals to UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.notEquals=" + UPDATED_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone in DEFAULT_ORD_CONTACT_1_CELL_PHONE or UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.in=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE + "," + UPDATED_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone equals to UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.in=" + UPDATED_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone is not null
        defaultOrdersShouldBeFound("ordContact1CellPhone.specified=true");

        // Get all the ordersList where ordContact1CellPhone is null
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone is greater than or equal to DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone is greater than or equal to UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone is less than or equal to DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone is less than or equal to SMALLER_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone is less than DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.lessThan=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone is less than UPDATED_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.lessThan=" + UPDATED_ORD_CONTACT_1_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1CellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1CellPhone is greater than DEFAULT_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1CellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_1_CELL_PHONE);

        // Get all the ordersList where ordContact1CellPhone is greater than SMALLER_ORD_CONTACT_1_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1CellPhone.greaterThan=" + SMALLER_ORD_CONTACT_1_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone equals to DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.equals=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone equals to UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.equals=" + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone not equals to DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.notEquals=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone not equals to UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.notEquals=" + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone in DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE or UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.in=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE + "," + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone equals to UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.in=" + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone is not null
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.specified=true");

        // Get all the ordersList where ordContact1OtherCellPhone is null
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone is greater than or equal to DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone is greater than or equal to UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone is less than or equal to DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone is less than or equal to SMALLER_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone is less than DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.lessThan=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone is less than UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.lessThan=" + UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherCellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherCellPhone is greater than DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact1OtherCellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact1OtherCellPhone is greater than SMALLER_ORD_CONTACT_1_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact1OtherCellPhone.greaterThan=" + SMALLER_ORD_CONTACT_1_OTHER_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email equals to DEFAULT_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldBeFound("ordContact1Email.equals=" + DEFAULT_ORD_CONTACT_1_EMAIL);

        // Get all the ordersList where ordContact1Email equals to UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1Email.equals=" + UPDATED_ORD_CONTACT_1_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email not equals to DEFAULT_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1Email.notEquals=" + DEFAULT_ORD_CONTACT_1_EMAIL);

        // Get all the ordersList where ordContact1Email not equals to UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldBeFound("ordContact1Email.notEquals=" + UPDATED_ORD_CONTACT_1_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email in DEFAULT_ORD_CONTACT_1_EMAIL or UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldBeFound("ordContact1Email.in=" + DEFAULT_ORD_CONTACT_1_EMAIL + "," + UPDATED_ORD_CONTACT_1_EMAIL);

        // Get all the ordersList where ordContact1Email equals to UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1Email.in=" + UPDATED_ORD_CONTACT_1_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email is not null
        defaultOrdersShouldBeFound("ordContact1Email.specified=true");

        // Get all the ordersList where ordContact1Email is null
        defaultOrdersShouldNotBeFound("ordContact1Email.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email contains DEFAULT_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldBeFound("ordContact1Email.contains=" + DEFAULT_ORD_CONTACT_1_EMAIL);

        // Get all the ordersList where ordContact1Email contains UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1Email.contains=" + UPDATED_ORD_CONTACT_1_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1EmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1Email does not contain DEFAULT_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1Email.doesNotContain=" + DEFAULT_ORD_CONTACT_1_EMAIL);

        // Get all the ordersList where ordContact1Email does not contain UPDATED_ORD_CONTACT_1_EMAIL
        defaultOrdersShouldBeFound("ordContact1Email.doesNotContain=" + UPDATED_ORD_CONTACT_1_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail equals to DEFAULT_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact1OtherEmail.equals=" + DEFAULT_ORD_CONTACT_1_OTHER_EMAIL);

        // Get all the ordersList where ordContact1OtherEmail equals to UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.equals=" + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail not equals to DEFAULT_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.notEquals=" + DEFAULT_ORD_CONTACT_1_OTHER_EMAIL);

        // Get all the ordersList where ordContact1OtherEmail not equals to UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact1OtherEmail.notEquals=" + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail in DEFAULT_ORD_CONTACT_1_OTHER_EMAIL or UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact1OtherEmail.in=" + DEFAULT_ORD_CONTACT_1_OTHER_EMAIL + "," + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);

        // Get all the ordersList where ordContact1OtherEmail equals to UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.in=" + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail is not null
        defaultOrdersShouldBeFound("ordContact1OtherEmail.specified=true");

        // Get all the ordersList where ordContact1OtherEmail is null
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail contains DEFAULT_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact1OtherEmail.contains=" + DEFAULT_ORD_CONTACT_1_OTHER_EMAIL);

        // Get all the ordersList where ordContact1OtherEmail contains UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.contains=" + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact1OtherEmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact1OtherEmail does not contain DEFAULT_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact1OtherEmail.doesNotContain=" + DEFAULT_ORD_CONTACT_1_OTHER_EMAIL);

        // Get all the ordersList where ordContact1OtherEmail does not contain UPDATED_ORD_CONTACT_1_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact1OtherEmail.doesNotContain=" + UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd equals to DEFAULT_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldBeFound("ordContact2TypeCd.equals=" + DEFAULT_ORD_CONTACT_2_TYPE_CD);

        // Get all the ordersList where ordContact2TypeCd equals to UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.equals=" + UPDATED_ORD_CONTACT_2_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd not equals to DEFAULT_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.notEquals=" + DEFAULT_ORD_CONTACT_2_TYPE_CD);

        // Get all the ordersList where ordContact2TypeCd not equals to UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldBeFound("ordContact2TypeCd.notEquals=" + UPDATED_ORD_CONTACT_2_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd in DEFAULT_ORD_CONTACT_2_TYPE_CD or UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldBeFound("ordContact2TypeCd.in=" + DEFAULT_ORD_CONTACT_2_TYPE_CD + "," + UPDATED_ORD_CONTACT_2_TYPE_CD);

        // Get all the ordersList where ordContact2TypeCd equals to UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.in=" + UPDATED_ORD_CONTACT_2_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd is not null
        defaultOrdersShouldBeFound("ordContact2TypeCd.specified=true");

        // Get all the ordersList where ordContact2TypeCd is null
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd contains DEFAULT_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldBeFound("ordContact2TypeCd.contains=" + DEFAULT_ORD_CONTACT_2_TYPE_CD);

        // Get all the ordersList where ordContact2TypeCd contains UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.contains=" + UPDATED_ORD_CONTACT_2_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2TypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2TypeCd does not contain DEFAULT_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact2TypeCd.doesNotContain=" + DEFAULT_ORD_CONTACT_2_TYPE_CD);

        // Get all the ordersList where ordContact2TypeCd does not contain UPDATED_ORD_CONTACT_2_TYPE_CD
        defaultOrdersShouldBeFound("ordContact2TypeCd.doesNotContain=" + UPDATED_ORD_CONTACT_2_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name equals to DEFAULT_ORD_CONTACT_2_NAME
        defaultOrdersShouldBeFound("ordContact2Name.equals=" + DEFAULT_ORD_CONTACT_2_NAME);

        // Get all the ordersList where ordContact2Name equals to UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldNotBeFound("ordContact2Name.equals=" + UPDATED_ORD_CONTACT_2_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name not equals to DEFAULT_ORD_CONTACT_2_NAME
        defaultOrdersShouldNotBeFound("ordContact2Name.notEquals=" + DEFAULT_ORD_CONTACT_2_NAME);

        // Get all the ordersList where ordContact2Name not equals to UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldBeFound("ordContact2Name.notEquals=" + UPDATED_ORD_CONTACT_2_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name in DEFAULT_ORD_CONTACT_2_NAME or UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldBeFound("ordContact2Name.in=" + DEFAULT_ORD_CONTACT_2_NAME + "," + UPDATED_ORD_CONTACT_2_NAME);

        // Get all the ordersList where ordContact2Name equals to UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldNotBeFound("ordContact2Name.in=" + UPDATED_ORD_CONTACT_2_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name is not null
        defaultOrdersShouldBeFound("ordContact2Name.specified=true");

        // Get all the ordersList where ordContact2Name is null
        defaultOrdersShouldNotBeFound("ordContact2Name.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name contains DEFAULT_ORD_CONTACT_2_NAME
        defaultOrdersShouldBeFound("ordContact2Name.contains=" + DEFAULT_ORD_CONTACT_2_NAME);

        // Get all the ordersList where ordContact2Name contains UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldNotBeFound("ordContact2Name.contains=" + UPDATED_ORD_CONTACT_2_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2NameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Name does not contain DEFAULT_ORD_CONTACT_2_NAME
        defaultOrdersShouldNotBeFound("ordContact2Name.doesNotContain=" + DEFAULT_ORD_CONTACT_2_NAME);

        // Get all the ordersList where ordContact2Name does not contain UPDATED_ORD_CONTACT_2_NAME
        defaultOrdersShouldBeFound("ordContact2Name.doesNotContain=" + UPDATED_ORD_CONTACT_2_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone equals to DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.equals=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone equals to UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.equals=" + UPDATED_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone not equals to DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.notEquals=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone not equals to UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.notEquals=" + UPDATED_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone in DEFAULT_ORD_CONTACT_2_WORK_PHONE or UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.in=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE + "," + UPDATED_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone equals to UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.in=" + UPDATED_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone is not null
        defaultOrdersShouldBeFound("ordContact2WorkPhone.specified=true");

        // Get all the ordersList where ordContact2WorkPhone is null
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone is greater than or equal to DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone is greater than or equal to UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone is less than or equal to DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone is less than or equal to SMALLER_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone is less than DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.lessThan=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone is less than UPDATED_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.lessThan=" + UPDATED_ORD_CONTACT_2_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2WorkPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2WorkPhone is greater than DEFAULT_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact2WorkPhone.greaterThan=" + DEFAULT_ORD_CONTACT_2_WORK_PHONE);

        // Get all the ordersList where ordContact2WorkPhone is greater than SMALLER_ORD_CONTACT_2_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact2WorkPhone.greaterThan=" + SMALLER_ORD_CONTACT_2_WORK_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone equals to DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.equals=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone equals to UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.equals=" + UPDATED_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone not equals to DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.notEquals=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone not equals to UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.notEquals=" + UPDATED_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone in DEFAULT_ORD_CONTACT_2_HOME_PHONE or UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.in=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE + "," + UPDATED_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone equals to UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.in=" + UPDATED_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone is not null
        defaultOrdersShouldBeFound("ordContact2HomePhone.specified=true");

        // Get all the ordersList where ordContact2HomePhone is null
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone is greater than or equal to DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone is greater than or equal to UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone is less than or equal to DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone is less than or equal to SMALLER_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone is less than DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.lessThan=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone is less than UPDATED_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.lessThan=" + UPDATED_ORD_CONTACT_2_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2HomePhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2HomePhone is greater than DEFAULT_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact2HomePhone.greaterThan=" + DEFAULT_ORD_CONTACT_2_HOME_PHONE);

        // Get all the ordersList where ordContact2HomePhone is greater than SMALLER_ORD_CONTACT_2_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact2HomePhone.greaterThan=" + SMALLER_ORD_CONTACT_2_HOME_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone equals to DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.equals=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone equals to UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.equals=" + UPDATED_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone not equals to DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.notEquals=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone not equals to UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.notEquals=" + UPDATED_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone in DEFAULT_ORD_CONTACT_2_CELL_PHONE or UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.in=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE + "," + UPDATED_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone equals to UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.in=" + UPDATED_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone is not null
        defaultOrdersShouldBeFound("ordContact2CellPhone.specified=true");

        // Get all the ordersList where ordContact2CellPhone is null
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone is greater than or equal to DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone is greater than or equal to UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone is less than or equal to DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone is less than or equal to SMALLER_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone is less than DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.lessThan=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone is less than UPDATED_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.lessThan=" + UPDATED_ORD_CONTACT_2_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2CellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2CellPhone is greater than DEFAULT_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2CellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_2_CELL_PHONE);

        // Get all the ordersList where ordContact2CellPhone is greater than SMALLER_ORD_CONTACT_2_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2CellPhone.greaterThan=" + SMALLER_ORD_CONTACT_2_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone equals to DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.equals=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone equals to UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.equals=" + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone not equals to DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.notEquals=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone not equals to UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.notEquals=" + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone in DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE or UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.in=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE + "," + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone equals to UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.in=" + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone is not null
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.specified=true");

        // Get all the ordersList where ordContact2OtherCellPhone is null
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone is greater than or equal to DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone is greater than or equal to UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone is less than or equal to DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone is less than or equal to SMALLER_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone is less than DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.lessThan=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone is less than UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.lessThan=" + UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherCellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherCellPhone is greater than DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact2OtherCellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact2OtherCellPhone is greater than SMALLER_ORD_CONTACT_2_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact2OtherCellPhone.greaterThan=" + SMALLER_ORD_CONTACT_2_OTHER_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email equals to DEFAULT_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldBeFound("ordContact2Email.equals=" + DEFAULT_ORD_CONTACT_2_EMAIL);

        // Get all the ordersList where ordContact2Email equals to UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2Email.equals=" + UPDATED_ORD_CONTACT_2_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email not equals to DEFAULT_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2Email.notEquals=" + DEFAULT_ORD_CONTACT_2_EMAIL);

        // Get all the ordersList where ordContact2Email not equals to UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldBeFound("ordContact2Email.notEquals=" + UPDATED_ORD_CONTACT_2_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email in DEFAULT_ORD_CONTACT_2_EMAIL or UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldBeFound("ordContact2Email.in=" + DEFAULT_ORD_CONTACT_2_EMAIL + "," + UPDATED_ORD_CONTACT_2_EMAIL);

        // Get all the ordersList where ordContact2Email equals to UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2Email.in=" + UPDATED_ORD_CONTACT_2_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email is not null
        defaultOrdersShouldBeFound("ordContact2Email.specified=true");

        // Get all the ordersList where ordContact2Email is null
        defaultOrdersShouldNotBeFound("ordContact2Email.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email contains DEFAULT_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldBeFound("ordContact2Email.contains=" + DEFAULT_ORD_CONTACT_2_EMAIL);

        // Get all the ordersList where ordContact2Email contains UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2Email.contains=" + UPDATED_ORD_CONTACT_2_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2EmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2Email does not contain DEFAULT_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2Email.doesNotContain=" + DEFAULT_ORD_CONTACT_2_EMAIL);

        // Get all the ordersList where ordContact2Email does not contain UPDATED_ORD_CONTACT_2_EMAIL
        defaultOrdersShouldBeFound("ordContact2Email.doesNotContain=" + UPDATED_ORD_CONTACT_2_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail equals to DEFAULT_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact2OtherEmail.equals=" + DEFAULT_ORD_CONTACT_2_OTHER_EMAIL);

        // Get all the ordersList where ordContact2OtherEmail equals to UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.equals=" + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail not equals to DEFAULT_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.notEquals=" + DEFAULT_ORD_CONTACT_2_OTHER_EMAIL);

        // Get all the ordersList where ordContact2OtherEmail not equals to UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact2OtherEmail.notEquals=" + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail in DEFAULT_ORD_CONTACT_2_OTHER_EMAIL or UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact2OtherEmail.in=" + DEFAULT_ORD_CONTACT_2_OTHER_EMAIL + "," + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);

        // Get all the ordersList where ordContact2OtherEmail equals to UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.in=" + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail is not null
        defaultOrdersShouldBeFound("ordContact2OtherEmail.specified=true");

        // Get all the ordersList where ordContact2OtherEmail is null
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail contains DEFAULT_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact2OtherEmail.contains=" + DEFAULT_ORD_CONTACT_2_OTHER_EMAIL);

        // Get all the ordersList where ordContact2OtherEmail contains UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.contains=" + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact2OtherEmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact2OtherEmail does not contain DEFAULT_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact2OtherEmail.doesNotContain=" + DEFAULT_ORD_CONTACT_2_OTHER_EMAIL);

        // Get all the ordersList where ordContact2OtherEmail does not contain UPDATED_ORD_CONTACT_2_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact2OtherEmail.doesNotContain=" + UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd equals to DEFAULT_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldBeFound("ordContact3TypeCd.equals=" + DEFAULT_ORD_CONTACT_3_TYPE_CD);

        // Get all the ordersList where ordContact3TypeCd equals to UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.equals=" + UPDATED_ORD_CONTACT_3_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd not equals to DEFAULT_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.notEquals=" + DEFAULT_ORD_CONTACT_3_TYPE_CD);

        // Get all the ordersList where ordContact3TypeCd not equals to UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldBeFound("ordContact3TypeCd.notEquals=" + UPDATED_ORD_CONTACT_3_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd in DEFAULT_ORD_CONTACT_3_TYPE_CD or UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldBeFound("ordContact3TypeCd.in=" + DEFAULT_ORD_CONTACT_3_TYPE_CD + "," + UPDATED_ORD_CONTACT_3_TYPE_CD);

        // Get all the ordersList where ordContact3TypeCd equals to UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.in=" + UPDATED_ORD_CONTACT_3_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd is not null
        defaultOrdersShouldBeFound("ordContact3TypeCd.specified=true");

        // Get all the ordersList where ordContact3TypeCd is null
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd contains DEFAULT_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldBeFound("ordContact3TypeCd.contains=" + DEFAULT_ORD_CONTACT_3_TYPE_CD);

        // Get all the ordersList where ordContact3TypeCd contains UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.contains=" + UPDATED_ORD_CONTACT_3_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3TypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3TypeCd does not contain DEFAULT_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldNotBeFound("ordContact3TypeCd.doesNotContain=" + DEFAULT_ORD_CONTACT_3_TYPE_CD);

        // Get all the ordersList where ordContact3TypeCd does not contain UPDATED_ORD_CONTACT_3_TYPE_CD
        defaultOrdersShouldBeFound("ordContact3TypeCd.doesNotContain=" + UPDATED_ORD_CONTACT_3_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name equals to DEFAULT_ORD_CONTACT_3_NAME
        defaultOrdersShouldBeFound("ordContact3Name.equals=" + DEFAULT_ORD_CONTACT_3_NAME);

        // Get all the ordersList where ordContact3Name equals to UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldNotBeFound("ordContact3Name.equals=" + UPDATED_ORD_CONTACT_3_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name not equals to DEFAULT_ORD_CONTACT_3_NAME
        defaultOrdersShouldNotBeFound("ordContact3Name.notEquals=" + DEFAULT_ORD_CONTACT_3_NAME);

        // Get all the ordersList where ordContact3Name not equals to UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldBeFound("ordContact3Name.notEquals=" + UPDATED_ORD_CONTACT_3_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name in DEFAULT_ORD_CONTACT_3_NAME or UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldBeFound("ordContact3Name.in=" + DEFAULT_ORD_CONTACT_3_NAME + "," + UPDATED_ORD_CONTACT_3_NAME);

        // Get all the ordersList where ordContact3Name equals to UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldNotBeFound("ordContact3Name.in=" + UPDATED_ORD_CONTACT_3_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name is not null
        defaultOrdersShouldBeFound("ordContact3Name.specified=true");

        // Get all the ordersList where ordContact3Name is null
        defaultOrdersShouldNotBeFound("ordContact3Name.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name contains DEFAULT_ORD_CONTACT_3_NAME
        defaultOrdersShouldBeFound("ordContact3Name.contains=" + DEFAULT_ORD_CONTACT_3_NAME);

        // Get all the ordersList where ordContact3Name contains UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldNotBeFound("ordContact3Name.contains=" + UPDATED_ORD_CONTACT_3_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3NameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Name does not contain DEFAULT_ORD_CONTACT_3_NAME
        defaultOrdersShouldNotBeFound("ordContact3Name.doesNotContain=" + DEFAULT_ORD_CONTACT_3_NAME);

        // Get all the ordersList where ordContact3Name does not contain UPDATED_ORD_CONTACT_3_NAME
        defaultOrdersShouldBeFound("ordContact3Name.doesNotContain=" + UPDATED_ORD_CONTACT_3_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone equals to DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.equals=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone equals to UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.equals=" + UPDATED_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone not equals to DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.notEquals=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone not equals to UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.notEquals=" + UPDATED_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone in DEFAULT_ORD_CONTACT_3_WORK_PHONE or UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.in=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE + "," + UPDATED_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone equals to UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.in=" + UPDATED_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone is not null
        defaultOrdersShouldBeFound("ordContact3WorkPhone.specified=true");

        // Get all the ordersList where ordContact3WorkPhone is null
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone is greater than or equal to DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone is greater than or equal to UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone is less than or equal to DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone is less than or equal to SMALLER_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone is less than DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.lessThan=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone is less than UPDATED_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.lessThan=" + UPDATED_ORD_CONTACT_3_WORK_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3WorkPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3WorkPhone is greater than DEFAULT_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldNotBeFound("ordContact3WorkPhone.greaterThan=" + DEFAULT_ORD_CONTACT_3_WORK_PHONE);

        // Get all the ordersList where ordContact3WorkPhone is greater than SMALLER_ORD_CONTACT_3_WORK_PHONE
        defaultOrdersShouldBeFound("ordContact3WorkPhone.greaterThan=" + SMALLER_ORD_CONTACT_3_WORK_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone equals to DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.equals=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone equals to UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.equals=" + UPDATED_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone not equals to DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.notEquals=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone not equals to UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.notEquals=" + UPDATED_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone in DEFAULT_ORD_CONTACT_3_HOME_PHONE or UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.in=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE + "," + UPDATED_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone equals to UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.in=" + UPDATED_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone is not null
        defaultOrdersShouldBeFound("ordContact3HomePhone.specified=true");

        // Get all the ordersList where ordContact3HomePhone is null
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone is greater than or equal to DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone is greater than or equal to UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone is less than or equal to DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone is less than or equal to SMALLER_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone is less than DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.lessThan=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone is less than UPDATED_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.lessThan=" + UPDATED_ORD_CONTACT_3_HOME_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3HomePhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3HomePhone is greater than DEFAULT_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldNotBeFound("ordContact3HomePhone.greaterThan=" + DEFAULT_ORD_CONTACT_3_HOME_PHONE);

        // Get all the ordersList where ordContact3HomePhone is greater than SMALLER_ORD_CONTACT_3_HOME_PHONE
        defaultOrdersShouldBeFound("ordContact3HomePhone.greaterThan=" + SMALLER_ORD_CONTACT_3_HOME_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone equals to DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.equals=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone equals to UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.equals=" + UPDATED_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone not equals to DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.notEquals=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone not equals to UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.notEquals=" + UPDATED_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone in DEFAULT_ORD_CONTACT_3_CELL_PHONE or UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.in=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE + "," + UPDATED_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone equals to UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.in=" + UPDATED_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone is not null
        defaultOrdersShouldBeFound("ordContact3CellPhone.specified=true");

        // Get all the ordersList where ordContact3CellPhone is null
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone is greater than or equal to DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone is greater than or equal to UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone is less than or equal to DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone is less than or equal to SMALLER_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone is less than DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.lessThan=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone is less than UPDATED_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.lessThan=" + UPDATED_ORD_CONTACT_3_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3CellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3CellPhone is greater than DEFAULT_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3CellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_3_CELL_PHONE);

        // Get all the ordersList where ordContact3CellPhone is greater than SMALLER_ORD_CONTACT_3_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3CellPhone.greaterThan=" + SMALLER_ORD_CONTACT_3_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone equals to DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.equals=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone equals to UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.equals=" + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone not equals to DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.notEquals=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone not equals to UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.notEquals=" + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone in DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE or UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.in=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE + "," + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone equals to UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.in=" + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone is not null
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.specified=true");

        // Get all the ordersList where ordContact3OtherCellPhone is null
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone is greater than or equal to DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.greaterThanOrEqual=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone is greater than or equal to UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.greaterThanOrEqual=" + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone is less than or equal to DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.lessThanOrEqual=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone is less than or equal to SMALLER_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.lessThanOrEqual=" + SMALLER_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone is less than DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.lessThan=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone is less than UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.lessThan=" + UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherCellPhoneIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherCellPhone is greater than DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldNotBeFound("ordContact3OtherCellPhone.greaterThan=" + DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE);

        // Get all the ordersList where ordContact3OtherCellPhone is greater than SMALLER_ORD_CONTACT_3_OTHER_CELL_PHONE
        defaultOrdersShouldBeFound("ordContact3OtherCellPhone.greaterThan=" + SMALLER_ORD_CONTACT_3_OTHER_CELL_PHONE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email equals to DEFAULT_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldBeFound("ordContact3Email.equals=" + DEFAULT_ORD_CONTACT_3_EMAIL);

        // Get all the ordersList where ordContact3Email equals to UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3Email.equals=" + UPDATED_ORD_CONTACT_3_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email not equals to DEFAULT_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3Email.notEquals=" + DEFAULT_ORD_CONTACT_3_EMAIL);

        // Get all the ordersList where ordContact3Email not equals to UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldBeFound("ordContact3Email.notEquals=" + UPDATED_ORD_CONTACT_3_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email in DEFAULT_ORD_CONTACT_3_EMAIL or UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldBeFound("ordContact3Email.in=" + DEFAULT_ORD_CONTACT_3_EMAIL + "," + UPDATED_ORD_CONTACT_3_EMAIL);

        // Get all the ordersList where ordContact3Email equals to UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3Email.in=" + UPDATED_ORD_CONTACT_3_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email is not null
        defaultOrdersShouldBeFound("ordContact3Email.specified=true");

        // Get all the ordersList where ordContact3Email is null
        defaultOrdersShouldNotBeFound("ordContact3Email.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email contains DEFAULT_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldBeFound("ordContact3Email.contains=" + DEFAULT_ORD_CONTACT_3_EMAIL);

        // Get all the ordersList where ordContact3Email contains UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3Email.contains=" + UPDATED_ORD_CONTACT_3_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3EmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3Email does not contain DEFAULT_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3Email.doesNotContain=" + DEFAULT_ORD_CONTACT_3_EMAIL);

        // Get all the ordersList where ordContact3Email does not contain UPDATED_ORD_CONTACT_3_EMAIL
        defaultOrdersShouldBeFound("ordContact3Email.doesNotContain=" + UPDATED_ORD_CONTACT_3_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail equals to DEFAULT_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact3OtherEmail.equals=" + DEFAULT_ORD_CONTACT_3_OTHER_EMAIL);

        // Get all the ordersList where ordContact3OtherEmail equals to UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.equals=" + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail not equals to DEFAULT_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.notEquals=" + DEFAULT_ORD_CONTACT_3_OTHER_EMAIL);

        // Get all the ordersList where ordContact3OtherEmail not equals to UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact3OtherEmail.notEquals=" + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail in DEFAULT_ORD_CONTACT_3_OTHER_EMAIL or UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact3OtherEmail.in=" + DEFAULT_ORD_CONTACT_3_OTHER_EMAIL + "," + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);

        // Get all the ordersList where ordContact3OtherEmail equals to UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.in=" + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail is not null
        defaultOrdersShouldBeFound("ordContact3OtherEmail.specified=true");

        // Get all the ordersList where ordContact3OtherEmail is null
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail contains DEFAULT_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact3OtherEmail.contains=" + DEFAULT_ORD_CONTACT_3_OTHER_EMAIL);

        // Get all the ordersList where ordContact3OtherEmail contains UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.contains=" + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdContact3OtherEmailNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordContact3OtherEmail does not contain DEFAULT_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldNotBeFound("ordContact3OtherEmail.doesNotContain=" + DEFAULT_ORD_CONTACT_3_OTHER_EMAIL);

        // Get all the ordersList where ordContact3OtherEmail does not contain UPDATED_ORD_CONTACT_3_OTHER_EMAIL
        defaultOrdersShouldBeFound("ordContact3OtherEmail.doesNotContain=" + UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd equals to DEFAULT_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.equals=" + DEFAULT_ORD_REQUEST_RR_STATUS_CD);

        // Get all the ordersList where ordRequestRrStatusCd equals to UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.equals=" + UPDATED_ORD_REQUEST_RR_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd not equals to DEFAULT_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.notEquals=" + DEFAULT_ORD_REQUEST_RR_STATUS_CD);

        // Get all the ordersList where ordRequestRrStatusCd not equals to UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.notEquals=" + UPDATED_ORD_REQUEST_RR_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd in DEFAULT_ORD_REQUEST_RR_STATUS_CD or UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.in=" + DEFAULT_ORD_REQUEST_RR_STATUS_CD + "," + UPDATED_ORD_REQUEST_RR_STATUS_CD);

        // Get all the ordersList where ordRequestRrStatusCd equals to UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.in=" + UPDATED_ORD_REQUEST_RR_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd is not null
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.specified=true");

        // Get all the ordersList where ordRequestRrStatusCd is null
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd contains DEFAULT_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.contains=" + DEFAULT_ORD_REQUEST_RR_STATUS_CD);

        // Get all the ordersList where ordRequestRrStatusCd contains UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.contains=" + UPDATED_ORD_REQUEST_RR_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrStatusCd does not contain DEFAULT_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldNotBeFound("ordRequestRrStatusCd.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_STATUS_CD);

        // Get all the ordersList where ordRequestRrStatusCd does not contain UPDATED_ORD_REQUEST_RR_STATUS_CD
        defaultOrdersShouldBeFound("ordRequestRrStatusCd.doesNotContain=" + UPDATED_ORD_REQUEST_RR_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId equals to DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.equals=" + DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);

        // Get all the ordersList where ordRequestRrVendorAppraisalId equals to UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.equals=" + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId not equals to DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.notEquals=" + DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);

        // Get all the ordersList where ordRequestRrVendorAppraisalId not equals to UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.notEquals=" + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId in DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID or UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.in=" + DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID + "," + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);

        // Get all the ordersList where ordRequestRrVendorAppraisalId equals to UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.in=" + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId is not null
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.specified=true");

        // Get all the ordersList where ordRequestRrVendorAppraisalId is null
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId contains DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.contains=" + DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);

        // Get all the ordersList where ordRequestRrVendorAppraisalId contains UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.contains=" + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrVendorAppraisalIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrVendorAppraisalId does not contain DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordRequestRrVendorAppraisalId.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);

        // Get all the ordersList where ordRequestRrVendorAppraisalId does not contain UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordRequestRrVendorAppraisalId.doesNotContain=" + UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt equals to DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.equals=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt equals to UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.equals=" + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt not equals to DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.notEquals=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt not equals to UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.notEquals=" + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt in DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT or UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.in=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT + "," + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt equals to UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.in=" + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is not null
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.specified=true");

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is null
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is greater than or equal to DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.greaterThanOrEqual=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is greater than or equal to UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.greaterThanOrEqual=" + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is less than or equal to DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.lessThanOrEqual=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is less than or equal to SMALLER_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.lessThanOrEqual=" + SMALLER_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is less than DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.lessThan=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is less than UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.lessThan=" + UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorAppraisalDraftRcvdDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is greater than DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldNotBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.greaterThan=" + DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);

        // Get all the ordersList where ordRequestRrvendorAppraisalDraftRcvdDt is greater than SMALLER_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT
        defaultOrdersShouldBeFound("ordRequestRrvendorAppraisalDraftRcvdDt.greaterThan=" + SMALLER_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId equals to DEFAULT_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.equals=" + DEFAULT_ORD_REQUEST_RR_XML_FORM_ID);

        // Get all the ordersList where ordRequestRrXmlFormId equals to UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.equals=" + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId not equals to DEFAULT_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.notEquals=" + DEFAULT_ORD_REQUEST_RR_XML_FORM_ID);

        // Get all the ordersList where ordRequestRrXmlFormId not equals to UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.notEquals=" + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId in DEFAULT_ORD_REQUEST_RR_XML_FORM_ID or UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.in=" + DEFAULT_ORD_REQUEST_RR_XML_FORM_ID + "," + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);

        // Get all the ordersList where ordRequestRrXmlFormId equals to UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.in=" + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId is not null
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.specified=true");

        // Get all the ordersList where ordRequestRrXmlFormId is null
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId contains DEFAULT_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.contains=" + DEFAULT_ORD_REQUEST_RR_XML_FORM_ID);

        // Get all the ordersList where ordRequestRrXmlFormId contains UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.contains=" + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrXmlFormIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrXmlFormId does not contain DEFAULT_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrXmlFormId.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_XML_FORM_ID);

        // Get all the ordersList where ordRequestRrXmlFormId does not contain UPDATED_ORD_REQUEST_RR_XML_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrXmlFormId.doesNotContain=" + UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId equals to DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.equals=" + DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID);

        // Get all the ordersList where ordRequestRrPdfFormId equals to UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.equals=" + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId not equals to DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.notEquals=" + DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID);

        // Get all the ordersList where ordRequestRrPdfFormId not equals to UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.notEquals=" + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId in DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID or UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.in=" + DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID + "," + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);

        // Get all the ordersList where ordRequestRrPdfFormId equals to UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.in=" + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId is not null
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.specified=true");

        // Get all the ordersList where ordRequestRrPdfFormId is null
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId contains DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.contains=" + DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID);

        // Get all the ordersList where ordRequestRrPdfFormId contains UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.contains=" + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrPdfFormIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrPdfFormId does not contain DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldNotBeFound("ordRequestRrPdfFormId.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID);

        // Get all the ordersList where ordRequestRrPdfFormId does not contain UPDATED_ORD_REQUEST_RR_PDF_FORM_ID
        defaultOrdersShouldBeFound("ordRequestRrPdfFormId.doesNotContain=" + UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd equals to DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.equals=" + DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd equals to UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.equals=" + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd not equals to DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.notEquals=" + DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd not equals to UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.notEquals=" + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd in DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD or UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.in=" + DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD + "," + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd equals to UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.in=" + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd is not null
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.specified=true");

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd is null
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd contains DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.contains=" + DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd contains UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.contains=" + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrApprFileDataSourcCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd does not contain DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldNotBeFound("ordRequestRrApprFileDataSourcCd.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);

        // Get all the ordersList where ordRequestRrApprFileDataSourcCd does not contain UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD
        defaultOrdersShouldBeFound("ordRequestRrApprFileDataSourcCd.doesNotContain=" + UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt equals to DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.equals=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt equals to UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.equals=" + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt not equals to DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.notEquals=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt not equals to UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.notEquals=" + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt in DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT or UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.in=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT + "," + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt equals to UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.in=" + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt is not null
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.specified=true");

        // Get all the ordersList where ordRequestRrReportValueAmt is null
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt is greater than or equal to DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt is greater than or equal to UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.greaterThanOrEqual=" + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt is less than or equal to DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.lessThanOrEqual=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt is less than or equal to SMALLER_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.lessThanOrEqual=" + SMALLER_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt is less than DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.lessThan=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt is less than UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.lessThan=" + UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrReportValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrReportValueAmt is greater than DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordRequestRrReportValueAmt.greaterThan=" + DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT);

        // Get all the ordersList where ordRequestRrReportValueAmt is greater than SMALLER_ORD_REQUEST_RR_REPORT_VALUE_AMT
        defaultOrdersShouldBeFound("ordRequestRrReportValueAmt.greaterThan=" + SMALLER_ORD_REQUEST_RR_REPORT_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName equals to DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.equals=" + DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName equals to UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.equals=" + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName not equals to DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.notEquals=" + DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName not equals to UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.notEquals=" + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName in DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME or UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.in=" + DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME + "," + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName equals to UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.in=" + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName is not null
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.specified=true");

        // Get all the ordersList where ordRequestRrAppraisalCompanyName is null
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName contains DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.contains=" + DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName contains UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.contains=" + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrAppraisalCompanyNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName does not contain DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrAppraisalCompanyName.doesNotContain=" + DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);

        // Get all the ordersList where ordRequestRrAppraisalCompanyName does not contain UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME
        defaultOrdersShouldBeFound("ordRequestRrAppraisalCompanyName.doesNotContain=" + UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName equals to DEFAULT_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldBeFound("ordRequestRrvendorName.equals=" + DEFAULT_ORD_REQUEST_RRVENDOR_NAME);

        // Get all the ordersList where ordRequestRrvendorName equals to UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.equals=" + UPDATED_ORD_REQUEST_RRVENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName not equals to DEFAULT_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.notEquals=" + DEFAULT_ORD_REQUEST_RRVENDOR_NAME);

        // Get all the ordersList where ordRequestRrvendorName not equals to UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldBeFound("ordRequestRrvendorName.notEquals=" + UPDATED_ORD_REQUEST_RRVENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName in DEFAULT_ORD_REQUEST_RRVENDOR_NAME or UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldBeFound("ordRequestRrvendorName.in=" + DEFAULT_ORD_REQUEST_RRVENDOR_NAME + "," + UPDATED_ORD_REQUEST_RRVENDOR_NAME);

        // Get all the ordersList where ordRequestRrvendorName equals to UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.in=" + UPDATED_ORD_REQUEST_RRVENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName is not null
        defaultOrdersShouldBeFound("ordRequestRrvendorName.specified=true");

        // Get all the ordersList where ordRequestRrvendorName is null
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName contains DEFAULT_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldBeFound("ordRequestRrvendorName.contains=" + DEFAULT_ORD_REQUEST_RRVENDOR_NAME);

        // Get all the ordersList where ordRequestRrvendorName contains UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.contains=" + UPDATED_ORD_REQUEST_RRVENDOR_NAME);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorNameNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorName does not contain DEFAULT_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldNotBeFound("ordRequestRrvendorName.doesNotContain=" + DEFAULT_ORD_REQUEST_RRVENDOR_NAME);

        // Get all the ordersList where ordRequestRrvendorName does not contain UPDATED_ORD_REQUEST_RRVENDOR_NAME
        defaultOrdersShouldBeFound("ordRequestRrvendorName.doesNotContain=" + UPDATED_ORD_REQUEST_RRVENDOR_NAME);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr equals to DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.equals=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.equals=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr not equals to DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.notEquals=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr not equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.notEquals=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr in DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR or UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.in=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR + "," + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.in=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr is not null
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.specified=true");

        // Get all the ordersList where ordRequestRrvendorLicenseNbr is null
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr contains DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.contains=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr contains UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.contains=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseNbrNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr does not contain DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseNbr.doesNotContain=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR);

        // Get all the ordersList where ordRequestRrvendorLicenseNbr does not contain UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseNbr.doesNotContain=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd equals to DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.equals=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.equals=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd not equals to DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.notEquals=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd not equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.notEquals=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd in DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD or UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.in=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD + "," + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd equals to UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.in=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd is not null
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.specified=true");

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd is null
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd contains DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.contains=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd contains UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.contains=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdRequestRrvendorLicenseStateCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd does not contain DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldNotBeFound("ordRequestRrvendorLicenseStateCd.doesNotContain=" + DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);

        // Get all the ordersList where ordRequestRrvendorLicenseStateCd does not contain UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD
        defaultOrdersShouldBeFound("ordRequestRrvendorLicenseStateCd.doesNotContain=" + UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct equals to DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.equals=" + DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT);

        // Get all the ordersList where ordValuationCompletedProduct equals to UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.equals=" + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct not equals to DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.notEquals=" + DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT);

        // Get all the ordersList where ordValuationCompletedProduct not equals to UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.notEquals=" + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct in DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT or UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.in=" + DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT + "," + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);

        // Get all the ordersList where ordValuationCompletedProduct equals to UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.in=" + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct is not null
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.specified=true");

        // Get all the ordersList where ordValuationCompletedProduct is null
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct contains DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.contains=" + DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT);

        // Get all the ordersList where ordValuationCompletedProduct contains UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.contains=" + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletedProductNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletedProduct does not contain DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldNotBeFound("ordValuationCompletedProduct.doesNotContain=" + DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT);

        // Get all the ordersList where ordValuationCompletedProduct does not contain UPDATED_ORD_VALUATION_COMPLETED_PRODUCT
        defaultOrdersShouldBeFound("ordValuationCompletedProduct.doesNotContain=" + UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt equals to DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.equals=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt equals to UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.equals=" + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt not equals to DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.notEquals=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt not equals to UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.notEquals=" + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt in DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT or UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.in=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT + "," + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt equals to UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.in=" + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt is not null
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.specified=true");

        // Get all the ordersList where ordValuationDueToClientDt is null
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt is greater than or equal to DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt is greater than or equal to UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt is less than or equal to DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt is less than or equal to SMALLER_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt is less than DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.lessThan=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt is less than UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.lessThan=" + UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationDueToClientDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationDueToClientDt is greater than DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldNotBeFound("ordValuationDueToClientDt.greaterThan=" + DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT);

        // Get all the ordersList where ordValuationDueToClientDt is greater than SMALLER_ORD_VALUATION_DUE_TO_CLIENT_DT
        defaultOrdersShouldBeFound("ordValuationDueToClientDt.greaterThan=" + SMALLER_ORD_VALUATION_DUE_TO_CLIENT_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt equals to DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.equals=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt equals to UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.equals=" + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt not equals to DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.notEquals=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt not equals to UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.notEquals=" + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt in DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT or UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.in=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT + "," + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt equals to UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.in=" + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt is not null
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.specified=true");

        // Get all the ordersList where ordValuationReportRecivedDt is null
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt is greater than or equal to DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt is greater than or equal to UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt is less than or equal to DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt is less than or equal to SMALLER_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt is less than DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.lessThan=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt is less than UPDATED_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.lessThan=" + UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportRecivedDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportRecivedDt is greater than DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldNotBeFound("ordValuationReportRecivedDt.greaterThan=" + DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT);

        // Get all the ordersList where ordValuationReportRecivedDt is greater than SMALLER_ORD_VALUATION_REPORT_RECIVED_DT
        defaultOrdersShouldBeFound("ordValuationReportRecivedDt.greaterThan=" + SMALLER_ORD_VALUATION_REPORT_RECIVED_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt equals to DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.equals=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt equals to UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.equals=" + UPDATED_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt not equals to DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.notEquals=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt not equals to UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.notEquals=" + UPDATED_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt in DEFAULT_ORD_VALUATION_COMPLETION_DT or UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.in=" + DEFAULT_ORD_VALUATION_COMPLETION_DT + "," + UPDATED_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt equals to UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.in=" + UPDATED_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt is not null
        defaultOrdersShouldBeFound("ordValuationCompletionDt.specified=true");

        // Get all the ordersList where ordValuationCompletionDt is null
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt is greater than or equal to DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt is greater than or equal to UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt is less than or equal to DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt is less than or equal to SMALLER_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt is less than DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.lessThan=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt is less than UPDATED_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.lessThan=" + UPDATED_ORD_VALUATION_COMPLETION_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationCompletionDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationCompletionDt is greater than DEFAULT_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldNotBeFound("ordValuationCompletionDt.greaterThan=" + DEFAULT_ORD_VALUATION_COMPLETION_DT);

        // Get all the ordersList where ordValuationCompletionDt is greater than SMALLER_ORD_VALUATION_COMPLETION_DT
        defaultOrdersShouldBeFound("ordValuationCompletionDt.greaterThan=" + SMALLER_ORD_VALUATION_COMPLETION_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd equals to DEFAULT_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.equals=" + DEFAULT_ORD_VALUATION_REPORT_STATUS_CD);

        // Get all the ordersList where ordValuationReportStatusCd equals to UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.equals=" + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd not equals to DEFAULT_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.notEquals=" + DEFAULT_ORD_VALUATION_REPORT_STATUS_CD);

        // Get all the ordersList where ordValuationReportStatusCd not equals to UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.notEquals=" + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd in DEFAULT_ORD_VALUATION_REPORT_STATUS_CD or UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.in=" + DEFAULT_ORD_VALUATION_REPORT_STATUS_CD + "," + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);

        // Get all the ordersList where ordValuationReportStatusCd equals to UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.in=" + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd is not null
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.specified=true");

        // Get all the ordersList where ordValuationReportStatusCd is null
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd contains DEFAULT_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.contains=" + DEFAULT_ORD_VALUATION_REPORT_STATUS_CD);

        // Get all the ordersList where ordValuationReportStatusCd contains UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.contains=" + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationReportStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationReportStatusCd does not contain DEFAULT_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldNotBeFound("ordValuationReportStatusCd.doesNotContain=" + DEFAULT_ORD_VALUATION_REPORT_STATUS_CD);

        // Get all the ordersList where ordValuationReportStatusCd does not contain UPDATED_ORD_VALUATION_REPORT_STATUS_CD
        defaultOrdersShouldBeFound("ordValuationReportStatusCd.doesNotContain=" + UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt equals to DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.equals=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt equals to UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.equals=" + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt not equals to DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.notEquals=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt not equals to UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.notEquals=" + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt in DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT or UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.in=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT + "," + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt equals to UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.in=" + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt is not null
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.specified=true");

        // Get all the ordersList where ordValuationAppraisedValueAmt is null
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt is greater than or equal to DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt is greater than or equal to UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt is less than or equal to DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt is less than or equal to SMALLER_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt is less than DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.lessThan=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt is less than UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.lessThan=" + UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAppraisedValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAppraisedValueAmt is greater than DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationAppraisedValueAmt.greaterThan=" + DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT);

        // Get all the ordersList where ordValuationAppraisedValueAmt is greater than SMALLER_ORD_VALUATION_APPRAISED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationAppraisedValueAmt.greaterThan=" + SMALLER_ORD_VALUATION_APPRAISED_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt equals to DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.equals=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt equals to UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.equals=" + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt not equals to DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.notEquals=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt not equals to UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.notEquals=" + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt in DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT or UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.in=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT + "," + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt equals to UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.in=" + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is not null
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.specified=true");

        // Get all the ordersList where ordValuationBpoMarketValueAmt is null
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is greater than or equal to DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is greater than or equal to UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is less than or equal to DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is less than or equal to SMALLER_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is less than DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.lessThan=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is less than UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.lessThan=" + UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoMarketValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is greater than DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoMarketValueAmt.greaterThan=" + DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoMarketValueAmt is greater than SMALLER_ORD_VALUATION_BPO_MARKET_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoMarketValueAmt.greaterThan=" + SMALLER_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt equals to DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.equals=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt equals to UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.equals=" + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt not equals to DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.notEquals=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt not equals to UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.notEquals=" + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt in DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT or UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.in=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT + "," + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt equals to UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.in=" + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt is not null
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.specified=true");

        // Get all the ordersList where ordValuationBpoAsIsAmt is null
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt is greater than or equal to DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt is greater than or equal to UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt is less than or equal to DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt is less than or equal to SMALLER_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt is less than DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.lessThan=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt is less than UPDATED_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.lessThan=" + UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsIsAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsIsAmt is greater than DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsIsAmt.greaterThan=" + DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT);

        // Get all the ordersList where ordValuationBpoAsIsAmt is greater than SMALLER_ORD_VALUATION_BPO_AS_IS_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsIsAmt.greaterThan=" + SMALLER_ORD_VALUATION_BPO_AS_IS_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt equals to DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.equals=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt equals to UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.equals=" + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt not equals to DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.notEquals=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt not equals to UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.notEquals=" + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt in DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT or UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.in=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT + "," + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt equals to UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.in=" + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is not null
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.specified=true");

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is null
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is greater than or equal to DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is greater than or equal to UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is less than or equal to DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is less than or equal to SMALLER_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.lessThanOrEqual=" + SMALLER_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is less than DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.lessThan=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is less than UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.lessThan=" + UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationBpoAsRepairedValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is greater than DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordValuationBpoAsRepairedValueAmt.greaterThan=" + DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);

        // Get all the ordersList where ordValuationBpoAsRepairedValueAmt is greater than SMALLER_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT
        defaultOrdersShouldBeFound("ordValuationBpoAsRepairedValueAmt.greaterThan=" + SMALLER_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode equals to DEFAULT_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.equals=" + DEFAULT_ORD_VALUATION_RED_FLAG_CODE);

        // Get all the ordersList where ordValuationRedFlagCode equals to UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.equals=" + UPDATED_ORD_VALUATION_RED_FLAG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode not equals to DEFAULT_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.notEquals=" + DEFAULT_ORD_VALUATION_RED_FLAG_CODE);

        // Get all the ordersList where ordValuationRedFlagCode not equals to UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.notEquals=" + UPDATED_ORD_VALUATION_RED_FLAG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode in DEFAULT_ORD_VALUATION_RED_FLAG_CODE or UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.in=" + DEFAULT_ORD_VALUATION_RED_FLAG_CODE + "," + UPDATED_ORD_VALUATION_RED_FLAG_CODE);

        // Get all the ordersList where ordValuationRedFlagCode equals to UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.in=" + UPDATED_ORD_VALUATION_RED_FLAG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode is not null
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.specified=true");

        // Get all the ordersList where ordValuationRedFlagCode is null
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode contains DEFAULT_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.contains=" + DEFAULT_ORD_VALUATION_RED_FLAG_CODE);

        // Get all the ordersList where ordValuationRedFlagCode contains UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.contains=" + UPDATED_ORD_VALUATION_RED_FLAG_CODE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagCodeNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagCode does not contain DEFAULT_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldNotBeFound("ordValuationRedFlagCode.doesNotContain=" + DEFAULT_ORD_VALUATION_RED_FLAG_CODE);

        // Get all the ordersList where ordValuationRedFlagCode does not contain UPDATED_ORD_VALUATION_RED_FLAG_CODE
        defaultOrdersShouldBeFound("ordValuationRedFlagCode.doesNotContain=" + UPDATED_ORD_VALUATION_RED_FLAG_CODE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc equals to DEFAULT_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.equals=" + DEFAULT_ORD_VALUATION_RED_FLAG_DESC);

        // Get all the ordersList where ordValuationRedFlagDesc equals to UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.equals=" + UPDATED_ORD_VALUATION_RED_FLAG_DESC);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc not equals to DEFAULT_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.notEquals=" + DEFAULT_ORD_VALUATION_RED_FLAG_DESC);

        // Get all the ordersList where ordValuationRedFlagDesc not equals to UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.notEquals=" + UPDATED_ORD_VALUATION_RED_FLAG_DESC);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc in DEFAULT_ORD_VALUATION_RED_FLAG_DESC or UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.in=" + DEFAULT_ORD_VALUATION_RED_FLAG_DESC + "," + UPDATED_ORD_VALUATION_RED_FLAG_DESC);

        // Get all the ordersList where ordValuationRedFlagDesc equals to UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.in=" + UPDATED_ORD_VALUATION_RED_FLAG_DESC);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc is not null
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.specified=true");

        // Get all the ordersList where ordValuationRedFlagDesc is null
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc contains DEFAULT_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.contains=" + DEFAULT_ORD_VALUATION_RED_FLAG_DESC);

        // Get all the ordersList where ordValuationRedFlagDesc contains UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.contains=" + UPDATED_ORD_VALUATION_RED_FLAG_DESC);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationRedFlagDescNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationRedFlagDesc does not contain DEFAULT_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldNotBeFound("ordValuationRedFlagDesc.doesNotContain=" + DEFAULT_ORD_VALUATION_RED_FLAG_DESC);

        // Get all the ordersList where ordValuationRedFlagDesc does not contain UPDATED_ORD_VALUATION_RED_FLAG_DESC
        defaultOrdersShouldBeFound("ordValuationRedFlagDesc.doesNotContain=" + UPDATED_ORD_VALUATION_RED_FLAG_DESC);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId equals to DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.equals=" + DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID);

        // Get all the ordersList where ordValuationAmcAppraisalId equals to UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.equals=" + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId not equals to DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.notEquals=" + DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID);

        // Get all the ordersList where ordValuationAmcAppraisalId not equals to UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.notEquals=" + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId in DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID or UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.in=" + DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID + "," + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);

        // Get all the ordersList where ordValuationAmcAppraisalId equals to UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.in=" + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId is not null
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.specified=true");

        // Get all the ordersList where ordValuationAmcAppraisalId is null
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId contains DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.contains=" + DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID);

        // Get all the ordersList where ordValuationAmcAppraisalId contains UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.contains=" + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationAmcAppraisalIdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationAmcAppraisalId does not contain DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldNotBeFound("ordValuationAmcAppraisalId.doesNotContain=" + DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID);

        // Get all the ordersList where ordValuationAmcAppraisalId does not contain UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID
        defaultOrdersShouldBeFound("ordValuationAmcAppraisalId.doesNotContain=" + UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId equals to DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.equals=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId equals to UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.equals=" + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId not equals to DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.notEquals=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId not equals to UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.notEquals=" + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId in DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID or UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.in=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID + "," + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId equals to UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.in=" + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId is not null
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.specified=true");

        // Get all the ordersList where ordValuationFinalReviewId is null
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId is greater than or equal to DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.greaterThanOrEqual=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId is greater than or equal to UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.greaterThanOrEqual=" + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId is less than or equal to DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.lessThanOrEqual=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId is less than or equal to SMALLER_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.lessThanOrEqual=" + SMALLER_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId is less than DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.lessThan=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId is less than UPDATED_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.lessThan=" + UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdValuationFinalReviewIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordValuationFinalReviewId is greater than DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldNotBeFound("ordValuationFinalReviewId.greaterThan=" + DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID);

        // Get all the ordersList where ordValuationFinalReviewId is greater than SMALLER_ORD_VALUATION_FINAL_REVIEW_ID
        defaultOrdersShouldBeFound("ordValuationFinalReviewId.greaterThan=" + SMALLER_ORD_VALUATION_FINAL_REVIEW_ID);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt equals to DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.equals=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt equals to UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.equals=" + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt not equals to DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.notEquals=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt not equals to UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.notEquals=" + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt in DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT or UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.in=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT + "," + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt equals to UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.in=" + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt is not null
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.specified=true");

        // Get all the ordersList where ordReviewRecommendedValueAmt is null
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt is greater than or equal to DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt is greater than or equal to UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.greaterThanOrEqual=" + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt is less than or equal to DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.lessThanOrEqual=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt is less than or equal to SMALLER_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.lessThanOrEqual=" + SMALLER_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt is less than DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.lessThan=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt is less than UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.lessThan=" + UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedValueAmt is greater than DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewRecommendedValueAmt.greaterThan=" + DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT);

        // Get all the ordersList where ordReviewRecommendedValueAmt is greater than SMALLER_ORD_REVIEW_RECOMMENDED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewRecommendedValueAmt.greaterThan=" + SMALLER_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt equals to DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.equals=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt equals to UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.equals=" + UPDATED_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt not equals to DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.notEquals=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt not equals to UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.notEquals=" + UPDATED_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt in DEFAULT_ORD_REVIEW_DT or UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.in=" + DEFAULT_ORD_REVIEW_DT + "," + UPDATED_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt equals to UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.in=" + UPDATED_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt is not null
        defaultOrdersShouldBeFound("ordReviewDt.specified=true");

        // Get all the ordersList where ordReviewDt is null
        defaultOrdersShouldNotBeFound("ordReviewDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt is greater than or equal to DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.greaterThanOrEqual=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt is greater than or equal to UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.greaterThanOrEqual=" + UPDATED_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt is less than or equal to DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.lessThanOrEqual=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt is less than or equal to SMALLER_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.lessThanOrEqual=" + SMALLER_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt is less than DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.lessThan=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt is less than UPDATED_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.lessThan=" + UPDATED_ORD_REVIEW_DT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewDt is greater than DEFAULT_ORD_REVIEW_DT
        defaultOrdersShouldNotBeFound("ordReviewDt.greaterThan=" + DEFAULT_ORD_REVIEW_DT);

        // Get all the ordersList where ordReviewDt is greater than SMALLER_ORD_REVIEW_DT
        defaultOrdersShouldBeFound("ordReviewDt.greaterThan=" + SMALLER_ORD_REVIEW_DT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd equals to DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.equals=" + DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewRecommendedActionCd equals to UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.equals=" + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd not equals to DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.notEquals=" + DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewRecommendedActionCd not equals to UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.notEquals=" + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd in DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD or UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.in=" + DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD + "," + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewRecommendedActionCd equals to UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.in=" + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd is not null
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.specified=true");

        // Get all the ordersList where ordReviewRecommendedActionCd is null
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd contains DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.contains=" + DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewRecommendedActionCd contains UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.contains=" + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewRecommendedActionCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewRecommendedActionCd does not contain DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewRecommendedActionCd.doesNotContain=" + DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewRecommendedActionCd does not contain UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewRecommendedActionCd.doesNotContain=" + UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd equals to DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.equals=" + DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD);

        // Get all the ordersList where ordReviewReviewerDecisionCd equals to UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.equals=" + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd not equals to DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.notEquals=" + DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD);

        // Get all the ordersList where ordReviewReviewerDecisionCd not equals to UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.notEquals=" + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd in DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD or UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.in=" + DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD + "," + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);

        // Get all the ordersList where ordReviewReviewerDecisionCd equals to UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.in=" + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd is not null
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.specified=true");

        // Get all the ordersList where ordReviewReviewerDecisionCd is null
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd contains DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.contains=" + DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD);

        // Get all the ordersList where ordReviewReviewerDecisionCd contains UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.contains=" + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewerDecisionCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewerDecisionCd does not contain DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldNotBeFound("ordReviewReviewerDecisionCd.doesNotContain=" + DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD);

        // Get all the ordersList where ordReviewReviewerDecisionCd does not contain UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD
        defaultOrdersShouldBeFound("ordReviewReviewerDecisionCd.doesNotContain=" + UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore equals to DEFAULT_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldBeFound("ordReviewReviewScore.equals=" + DEFAULT_ORD_REVIEW_REVIEW_SCORE);

        // Get all the ordersList where ordReviewReviewScore equals to UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.equals=" + UPDATED_ORD_REVIEW_REVIEW_SCORE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore not equals to DEFAULT_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.notEquals=" + DEFAULT_ORD_REVIEW_REVIEW_SCORE);

        // Get all the ordersList where ordReviewReviewScore not equals to UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldBeFound("ordReviewReviewScore.notEquals=" + UPDATED_ORD_REVIEW_REVIEW_SCORE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore in DEFAULT_ORD_REVIEW_REVIEW_SCORE or UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldBeFound("ordReviewReviewScore.in=" + DEFAULT_ORD_REVIEW_REVIEW_SCORE + "," + UPDATED_ORD_REVIEW_REVIEW_SCORE);

        // Get all the ordersList where ordReviewReviewScore equals to UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.in=" + UPDATED_ORD_REVIEW_REVIEW_SCORE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore is not null
        defaultOrdersShouldBeFound("ordReviewReviewScore.specified=true");

        // Get all the ordersList where ordReviewReviewScore is null
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore contains DEFAULT_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldBeFound("ordReviewReviewScore.contains=" + DEFAULT_ORD_REVIEW_REVIEW_SCORE);

        // Get all the ordersList where ordReviewReviewScore contains UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.contains=" + UPDATED_ORD_REVIEW_REVIEW_SCORE);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewReviewScoreNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewReviewScore does not contain DEFAULT_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldNotBeFound("ordReviewReviewScore.doesNotContain=" + DEFAULT_ORD_REVIEW_REVIEW_SCORE);

        // Get all the ordersList where ordReviewReviewScore does not contain UPDATED_ORD_REVIEW_REVIEW_SCORE
        defaultOrdersShouldBeFound("ordReviewReviewScore.doesNotContain=" + UPDATED_ORD_REVIEW_REVIEW_SCORE);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed equals to DEFAULT_ORD_REVIEW_FORM_USED
        defaultOrdersShouldBeFound("ordReviewFormUsed.equals=" + DEFAULT_ORD_REVIEW_FORM_USED);

        // Get all the ordersList where ordReviewFormUsed equals to UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.equals=" + UPDATED_ORD_REVIEW_FORM_USED);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed not equals to DEFAULT_ORD_REVIEW_FORM_USED
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.notEquals=" + DEFAULT_ORD_REVIEW_FORM_USED);

        // Get all the ordersList where ordReviewFormUsed not equals to UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldBeFound("ordReviewFormUsed.notEquals=" + UPDATED_ORD_REVIEW_FORM_USED);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed in DEFAULT_ORD_REVIEW_FORM_USED or UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldBeFound("ordReviewFormUsed.in=" + DEFAULT_ORD_REVIEW_FORM_USED + "," + UPDATED_ORD_REVIEW_FORM_USED);

        // Get all the ordersList where ordReviewFormUsed equals to UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.in=" + UPDATED_ORD_REVIEW_FORM_USED);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed is not null
        defaultOrdersShouldBeFound("ordReviewFormUsed.specified=true");

        // Get all the ordersList where ordReviewFormUsed is null
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed contains DEFAULT_ORD_REVIEW_FORM_USED
        defaultOrdersShouldBeFound("ordReviewFormUsed.contains=" + DEFAULT_ORD_REVIEW_FORM_USED);

        // Get all the ordersList where ordReviewFormUsed contains UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.contains=" + UPDATED_ORD_REVIEW_FORM_USED);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFormUsedNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFormUsed does not contain DEFAULT_ORD_REVIEW_FORM_USED
        defaultOrdersShouldNotBeFound("ordReviewFormUsed.doesNotContain=" + DEFAULT_ORD_REVIEW_FORM_USED);

        // Get all the ordersList where ordReviewFormUsed does not contain UPDATED_ORD_REVIEW_FORM_USED
        defaultOrdersShouldBeFound("ordReviewFormUsed.doesNotContain=" + UPDATED_ORD_REVIEW_FORM_USED);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewProviderOnWatchListIndIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewProviderOnWatchListInd equals to DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldBeFound("ordReviewProviderOnWatchListInd.equals=" + DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);

        // Get all the ordersList where ordReviewProviderOnWatchListInd equals to UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldNotBeFound("ordReviewProviderOnWatchListInd.equals=" + UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewProviderOnWatchListIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewProviderOnWatchListInd not equals to DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldNotBeFound("ordReviewProviderOnWatchListInd.notEquals=" + DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);

        // Get all the ordersList where ordReviewProviderOnWatchListInd not equals to UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldBeFound("ordReviewProviderOnWatchListInd.notEquals=" + UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewProviderOnWatchListIndIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewProviderOnWatchListInd in DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND or UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldBeFound("ordReviewProviderOnWatchListInd.in=" + DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND + "," + UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);

        // Get all the ordersList where ordReviewProviderOnWatchListInd equals to UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND
        defaultOrdersShouldNotBeFound("ordReviewProviderOnWatchListInd.in=" + UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewProviderOnWatchListIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewProviderOnWatchListInd is not null
        defaultOrdersShouldBeFound("ordReviewProviderOnWatchListInd.specified=true");

        // Get all the ordersList where ordReviewProviderOnWatchListInd is null
        defaultOrdersShouldNotBeFound("ordReviewProviderOnWatchListInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt equals to DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.equals=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt equals to UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.equals=" + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt not equals to DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.notEquals=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt not equals to UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.notEquals=" + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt in DEFAULT_ORD_REVIEW_CURED_VALUE_AMT or UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.in=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT + "," + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt equals to UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.in=" + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt is not null
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.specified=true");

        // Get all the ordersList where ordReviewCuredValueAmt is null
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt is greater than or equal to DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.greaterThanOrEqual=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt is greater than or equal to UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.greaterThanOrEqual=" + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt is less than or equal to DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.lessThanOrEqual=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt is less than or equal to SMALLER_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.lessThanOrEqual=" + SMALLER_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsLessThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt is less than DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.lessThan=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt is less than UPDATED_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.lessThan=" + UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewCuredValueAmtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewCuredValueAmt is greater than DEFAULT_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldNotBeFound("ordReviewCuredValueAmt.greaterThan=" + DEFAULT_ORD_REVIEW_CURED_VALUE_AMT);

        // Get all the ordersList where ordReviewCuredValueAmt is greater than SMALLER_ORD_REVIEW_CURED_VALUE_AMT
        defaultOrdersShouldBeFound("ordReviewCuredValueAmt.greaterThan=" + SMALLER_ORD_REVIEW_CURED_VALUE_AMT);
    }


    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd equals to DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.equals=" + DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd equals to UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.equals=" + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd not equals to DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.notEquals=" + DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd not equals to UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.notEquals=" + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdIsInShouldWork() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd in DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD or UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.in=" + DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD + "," + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd equals to UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.in=" + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd is not null
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.specified=true");

        // Get all the ordersList where ordReviewFinalRecommendedActionCd is null
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd contains DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.contains=" + DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd contains UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.contains=" + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void getAllOrdersByOrdReviewFinalRecommendedActionCdNotContainsSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd does not contain DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldNotBeFound("ordReviewFinalRecommendedActionCd.doesNotContain=" + DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        // Get all the ordersList where ordReviewFinalRecommendedActionCd does not contain UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD
        defaultOrdersShouldBeFound("ordReviewFinalRecommendedActionCd.doesNotContain=" + UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }


    @Test
    @Transactional
    public void getAllOrdersByCommentsIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);
        OrderComments comments = OrderCommentsResourceIT.createEntity(em);
        em.persist(comments);
        em.flush();
        orders.addComments(comments);
        ordersRepository.saveAndFlush(orders);
        Long commentsId = comments.getId();

        // Get all the ordersList where comments equals to commentsId
        defaultOrdersShouldBeFound("commentsId.equals=" + commentsId);

        // Get all the ordersList where comments equals to commentsId + 1
        defaultOrdersShouldNotBeFound("commentsId.equals=" + (commentsId + 1));
    }


    @Test
    @Transactional
    public void getAllOrdersByDocumentsIsEqualToSomething() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);
        OrderDocuments documents = OrderDocumentsResourceIT.createEntity(em);
        em.persist(documents);
        em.flush();
        orders.addDocuments(documents);
        ordersRepository.saveAndFlush(orders);
        Long documentsId = documents.getId();

        // Get all the ordersList where documents equals to documentsId
        defaultOrdersShouldBeFound("documentsId.equals=" + documentsId);

        // Get all the ordersList where documents equals to documentsId + 1
        defaultOrdersShouldNotBeFound("documentsId.equals=" + (documentsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrdersShouldBeFound(String filter) throws Exception {
        restOrdersMockMvc.perform(get("/api/orders?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orders.getId().intValue())))
            .andExpect(jsonPath("$.[*].ordNumber").value(hasItem(DEFAULT_ORD_NUMBER)))
            .andExpect(jsonPath("$.[*].ordStageCd").value(hasItem(DEFAULT_ORD_STAGE_CD)))
            .andExpect(jsonPath("$.[*].ordOrgOrderStatusCd").value(hasItem(DEFAULT_ORD_ORG_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordCmpOrderStatusCd").value(hasItem(DEFAULT_ORD_CMP_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordProOrderStatusCd").value(hasItem(DEFAULT_ORD_PRO_ORDER_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordCmpNbr").value(hasItem(DEFAULT_ORD_CMP_NBR)))
            .andExpect(jsonPath("$.[*].ordBrnNbr").value(hasItem(DEFAULT_ORD_BRN_NBR)))
            .andExpect(jsonPath("$.[*].ordProNbr").value(hasItem(DEFAULT_ORD_PRO_NBR)))
            .andExpect(jsonPath("$.[*].ordProductCode").value(hasItem(DEFAULT_ORD_PRODUCT_CODE)))
            .andExpect(jsonPath("$.[*].ordOriginalValueAmt").value(hasItem(DEFAULT_ORD_ORIGINAL_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordParentTrackingNbr").value(hasItem(DEFAULT_ORD_PARENT_TRACKING_NBR)))
            .andExpect(jsonPath("$.[*].ordUserNbr").value(hasItem(DEFAULT_ORD_USER_NBR)))
            .andExpect(jsonPath("$.[*].ordOrderDt").value(hasItem(DEFAULT_ORD_ORDER_DT.toString())))
            .andExpect(jsonPath("$.[*].ordRushRequestDueDt").value(hasItem(DEFAULT_ORD_RUSH_REQUEST_DUE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordOrgInstructions").value(hasItem(DEFAULT_ORD_ORG_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].ordOrgOtherInstructions").value(hasItem(DEFAULT_ORD_ORG_OTHER_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].ordMultiOrderInd").value(hasItem(DEFAULT_ORD_MULTI_ORDER_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordOrgDuedate").value(hasItem(DEFAULT_ORD_ORG_DUEDATE.toString())))
            .andExpect(jsonPath("$.[*].ordProDuedate").value(hasItem(DEFAULT_ORD_PRO_DUEDATE.toString())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFnmaSubmitToUcdpInd").value(hasItem(DEFAULT_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFnmaServicerNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_FNMA_SERVICER_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpDataFhlmcSubmitToUcdpInd").value(hasItem(DEFAULT_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordUcdpDataFhlmcIdentificationNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpDataUcdpBussinessUnitNbr").value(hasItem(DEFAULT_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFnamaDocumentId").value(hasItem(DEFAULT_ORD_UCDP_SD_FNAMA_DOCUMENT_ID)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFnmaSubmissionStatusId").value(hasItem(DEFAULT_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID)))
            .andExpect(jsonPath("$.[*].ordUcdpSdFhlmcDocumentFileCd").value(hasItem(DEFAULT_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD)))
            .andExpect(jsonPath("$.[*].ordUcdpSdEadSubmissionStatusCd").value(hasItem(DEFAULT_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordUcdpFinalSubmissionDt").value(hasItem(DEFAULT_ORD_UCDP_FINAL_SUBMISSION_DT.toString())))
            .andExpect(jsonPath("$.[*].ordLoanNbr").value(hasItem(DEFAULT_ORD_LOAN_NBR)))
            .andExpect(jsonPath("$.[*].ordloanOldLoanNbr").value(hasItem(DEFAULT_ORDLOAN_OLD_LOAN_NBR)))
            .andExpect(jsonPath("$.[*].ordLoanProgram").value(hasItem(DEFAULT_ORD_LOAN_PROGRAM)))
            .andExpect(jsonPath("$.[*].ordLoanPurpose").value(hasItem(DEFAULT_ORD_LOAN_PURPOSE)))
            .andExpect(jsonPath("$.[*].ordLoanType").value(hasItem(DEFAULT_ORD_LOAN_TYPE)))
            .andExpect(jsonPath("$.[*].ordLoanAmt").value(hasItem(DEFAULT_ORD_LOAN_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanQualifyingValueAmt").value(hasItem(DEFAULT_ORD_LOAN_QUALIFYING_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanSalesPriceAmt").value(hasItem(DEFAULT_ORD_LOAN_SALES_PRICE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerLastName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerFirstName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerMiddleName").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerIsCoBorrowerInd").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanFhaCaseNbr").value(hasItem(DEFAULT_ORD_LOAN_FHA_CASE_NBR)))
            .andExpect(jsonPath("$.[*].ordLoanDeedRestrictionInd").value(hasItem(DEFAULT_ORD_LOAN_DEED_RESTRICTION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanEstimatedCloseDt").value(hasItem(DEFAULT_ORD_LOAN_ESTIMATED_CLOSE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordLoanHpmlInd").value(hasItem(DEFAULT_ORD_LOAN_HPML_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanIsNewConstructionInd").value(hasItem(DEFAULT_ORD_LOAN_IS_NEW_CONSTRUCTION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanCustomerSegmentCode").value(hasItem(DEFAULT_ORD_LOAN_CUSTOMER_SEGMENT_CODE)))
            .andExpect(jsonPath("$.[*].ordLoanNonSubjectPropertyInd").value(hasItem(DEFAULT_ORD_LOAN_NON_SUBJECT_PROPERTY_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordLoanBorrowerRequestedCloseDt").value(hasItem(DEFAULT_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT.toString())))
            .andExpect(jsonPath("$.[*].ordPropertyTypeCd").value(hasItem(DEFAULT_ORD_PROPERTY_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordPropertyAddress1").value(hasItem(DEFAULT_ORD_PROPERTY_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].ordPropertyAddress2").value(hasItem(DEFAULT_ORD_PROPERTY_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].ordPropertyCity").value(hasItem(DEFAULT_ORD_PROPERTY_CITY)))
            .andExpect(jsonPath("$.[*].ordPropertyStateCd").value(hasItem(DEFAULT_ORD_PROPERTY_STATE_CD)))
            .andExpect(jsonPath("$.[*].ordPropertyZip").value(hasItem(DEFAULT_ORD_PROPERTY_ZIP)))
            .andExpect(jsonPath("$.[*].ordPropertyCounty").value(hasItem(DEFAULT_ORD_PROPERTY_COUNTY)))
            .andExpect(jsonPath("$.[*].ordPropertyLatitue").value(hasItem(DEFAULT_ORD_PROPERTY_LATITUE)))
            .andExpect(jsonPath("$.[*].ordPropertyLongitude").value(hasItem(DEFAULT_ORD_PROPERTY_LONGITUDE)))
            .andExpect(jsonPath("$.[*].ordPropertyProjectName").value(hasItem(DEFAULT_ORD_PROPERTY_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].ordContact1TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_1_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact1Name").value(hasItem(DEFAULT_ORD_CONTACT_1_NAME)))
            .andExpect(jsonPath("$.[*].ordContact1WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_1_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_1_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact1Email").value(hasItem(DEFAULT_ORD_CONTACT_1_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact1OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_1_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact2TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_2_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact2Name").value(hasItem(DEFAULT_ORD_CONTACT_2_NAME)))
            .andExpect(jsonPath("$.[*].ordContact2WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_2_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_2_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact2Email").value(hasItem(DEFAULT_ORD_CONTACT_2_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact2OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_2_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact3TypeCd").value(hasItem(DEFAULT_ORD_CONTACT_3_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ordContact3Name").value(hasItem(DEFAULT_ORD_CONTACT_3_NAME)))
            .andExpect(jsonPath("$.[*].ordContact3WorkPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_WORK_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3HomePhone").value(hasItem(DEFAULT_ORD_CONTACT_3_HOME_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3CellPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3OtherCellPhone").value(hasItem(DEFAULT_ORD_CONTACT_3_OTHER_CELL_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].ordContact3Email").value(hasItem(DEFAULT_ORD_CONTACT_3_EMAIL)))
            .andExpect(jsonPath("$.[*].ordContact3OtherEmail").value(hasItem(DEFAULT_ORD_CONTACT_3_OTHER_EMAIL)))
            .andExpect(jsonPath("$.[*].ordRequestRrStatusCd").value(hasItem(DEFAULT_ORD_REQUEST_RR_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordRequestRrVendorAppraisalId").value(hasItem(DEFAULT_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorAppraisalDraftRcvdDt").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT.toString())))
            .andExpect(jsonPath("$.[*].ordRequestRrXmlFormId").value(hasItem(DEFAULT_ORD_REQUEST_RR_XML_FORM_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrPdfFormId").value(hasItem(DEFAULT_ORD_REQUEST_RR_PDF_FORM_ID)))
            .andExpect(jsonPath("$.[*].ordRequestRrApprFileDataSourcCd").value(hasItem(DEFAULT_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD)))
            .andExpect(jsonPath("$.[*].ordRequestRrReportValueAmt").value(hasItem(DEFAULT_ORD_REQUEST_RR_REPORT_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordRequestRrAppraisalCompanyName").value(hasItem(DEFAULT_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorName").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_NAME)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorLicenseNbr").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_NBR)))
            .andExpect(jsonPath("$.[*].ordRequestRrvendorLicenseStateCd").value(hasItem(DEFAULT_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD)))
            .andExpect(jsonPath("$.[*].ordValuationCompletedProduct").value(hasItem(DEFAULT_ORD_VALUATION_COMPLETED_PRODUCT)))
            .andExpect(jsonPath("$.[*].ordValuationDueToClientDt").value(hasItem(DEFAULT_ORD_VALUATION_DUE_TO_CLIENT_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationReportRecivedDt").value(hasItem(DEFAULT_ORD_VALUATION_REPORT_RECIVED_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationCompletionDt").value(hasItem(DEFAULT_ORD_VALUATION_COMPLETION_DT.toString())))
            .andExpect(jsonPath("$.[*].ordValuationReportStatusCd").value(hasItem(DEFAULT_ORD_VALUATION_REPORT_STATUS_CD)))
            .andExpect(jsonPath("$.[*].ordValuationAppraisedValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_APPRAISED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoMarketValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_MARKET_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoAsIsAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_AS_IS_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationBpoAsRepairedValueAmt").value(hasItem(DEFAULT_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordValuationRedFlagCode").value(hasItem(DEFAULT_ORD_VALUATION_RED_FLAG_CODE)))
            .andExpect(jsonPath("$.[*].ordValuationRedFlagDesc").value(hasItem(DEFAULT_ORD_VALUATION_RED_FLAG_DESC)))
            .andExpect(jsonPath("$.[*].ordValuationAmcAppraisalId").value(hasItem(DEFAULT_ORD_VALUATION_AMC_APPRAISAL_ID)))
            .andExpect(jsonPath("$.[*].ordValuationFinalReviewId").value(hasItem(DEFAULT_ORD_VALUATION_FINAL_REVIEW_ID.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewRecommendedValueAmt").value(hasItem(DEFAULT_ORD_REVIEW_RECOMMENDED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewDt").value(hasItem(DEFAULT_ORD_REVIEW_DT.toString())))
            .andExpect(jsonPath("$.[*].ordReviewRecommendedActionCd").value(hasItem(DEFAULT_ORD_REVIEW_RECOMMENDED_ACTION_CD)))
            .andExpect(jsonPath("$.[*].ordReviewReviewerDecisionCd").value(hasItem(DEFAULT_ORD_REVIEW_REVIEWER_DECISION_CD)))
            .andExpect(jsonPath("$.[*].ordReviewReviewScore").value(hasItem(DEFAULT_ORD_REVIEW_REVIEW_SCORE)))
            .andExpect(jsonPath("$.[*].ordReviewFormUsed").value(hasItem(DEFAULT_ORD_REVIEW_FORM_USED)))
            .andExpect(jsonPath("$.[*].ordReviewProviderOnWatchListInd").value(hasItem(DEFAULT_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ordReviewCuredValueAmt").value(hasItem(DEFAULT_ORD_REVIEW_CURED_VALUE_AMT.intValue())))
            .andExpect(jsonPath("$.[*].ordReviewFinalRecommendedActionCd").value(hasItem(DEFAULT_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD)));

        // Check, that the count call also returns 1
        restOrdersMockMvc.perform(get("/api/orders/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrdersShouldNotBeFound(String filter) throws Exception {
        restOrdersMockMvc.perform(get("/api/orders?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrdersMockMvc.perform(get("/api/orders/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrders() throws Exception {
        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrders() throws Exception {
        // Initialize the database
        ordersService.save(orders);

        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Update the orders
        Orders updatedOrders = ordersRepository.findById(orders.getId()).get();
        // Disconnect from session so that the updates on updatedOrders are not directly saved in db
        em.detach(updatedOrders);
        updatedOrders
            .ordNumber(UPDATED_ORD_NUMBER)
            .ordStageCd(UPDATED_ORD_STAGE_CD)
            .ordOrgOrderStatusCd(UPDATED_ORD_ORG_ORDER_STATUS_CD)
            .ordCmpOrderStatusCd(UPDATED_ORD_CMP_ORDER_STATUS_CD)
            .ordProOrderStatusCd(UPDATED_ORD_PRO_ORDER_STATUS_CD)
            .ordCmpNbr(UPDATED_ORD_CMP_NBR)
            .ordBrnNbr(UPDATED_ORD_BRN_NBR)
            .ordProNbr(UPDATED_ORD_PRO_NBR)
            .ordProductCode(UPDATED_ORD_PRODUCT_CODE)
            .ordOriginalValueAmt(UPDATED_ORD_ORIGINAL_VALUE_AMT)
            .ordParentTrackingNbr(UPDATED_ORD_PARENT_TRACKING_NBR)
            .ordUserNbr(UPDATED_ORD_USER_NBR)
            .ordOrderDt(UPDATED_ORD_ORDER_DT)
            .ordRushRequestDueDt(UPDATED_ORD_RUSH_REQUEST_DUE_DT)
            .ordOrgInstructions(UPDATED_ORD_ORG_INSTRUCTIONS)
            .ordOrgOtherInstructions(UPDATED_ORD_ORG_OTHER_INSTRUCTIONS)
            .ordMultiOrderInd(UPDATED_ORD_MULTI_ORDER_IND)
            .ordOrgDuedate(UPDATED_ORD_ORG_DUEDATE)
            .ordProDuedate(UPDATED_ORD_PRO_DUEDATE)
            .ordUcdpDataFnmaSubmitToUcdpInd(UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFnmaServicerNbr(UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR)
            .ordUcdpDataFhlmcSubmitToUcdpInd(UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND)
            .ordUcdpDataFhlmcIdentificationNbr(UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR)
            .ordUcdpDataUcdpBussinessUnitNbr(UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR)
            .ordUcdpSdFnamaDocumentId(UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID)
            .ordUcdpSdFnmaSubmissionStatusId(UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID)
            .ordUcdpSdFhlmcDocumentFileCd(UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD)
            .ordUcdpSdEadSubmissionStatusCd(UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD)
            .ordUcdpFinalSubmissionDt(UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT)
            .ordLoanNbr(UPDATED_ORD_LOAN_NBR)
            .ordloanOldLoanNbr(UPDATED_ORDLOAN_OLD_LOAN_NBR)
            .ordLoanProgram(UPDATED_ORD_LOAN_PROGRAM)
            .ordLoanPurpose(UPDATED_ORD_LOAN_PURPOSE)
            .ordLoanType(UPDATED_ORD_LOAN_TYPE)
            .ordLoanAmt(UPDATED_ORD_LOAN_AMT)
            .ordLoanQualifyingValueAmt(UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT)
            .ordLoanSalesPriceAmt(UPDATED_ORD_LOAN_SALES_PRICE_AMT)
            .ordLoanBorrowerLastName(UPDATED_ORD_LOAN_BORROWER_LAST_NAME)
            .ordLoanBorrowerFirstName(UPDATED_ORD_LOAN_BORROWER_FIRST_NAME)
            .ordLoanBorrowerMiddleName(UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME)
            .ordLoanBorrowerIsCoBorrowerInd(UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND)
            .ordLoanFhaCaseNbr(UPDATED_ORD_LOAN_FHA_CASE_NBR)
            .ordLoanDeedRestrictionInd(UPDATED_ORD_LOAN_DEED_RESTRICTION_IND)
            .ordLoanEstimatedCloseDt(UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT)
            .ordLoanHpmlInd(UPDATED_ORD_LOAN_HPML_IND)
            .ordLoanIsNewConstructionInd(UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND)
            .ordLoanCustomerSegmentCode(UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE)
            .ordLoanNonSubjectPropertyInd(UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND)
            .ordLoanBorrowerRequestedCloseDt(UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT)
            .ordPropertyTypeCd(UPDATED_ORD_PROPERTY_TYPE_CD)
            .ordPropertyAddress1(UPDATED_ORD_PROPERTY_ADDRESS_1)
            .ordPropertyAddress2(UPDATED_ORD_PROPERTY_ADDRESS_2)
            .ordPropertyCity(UPDATED_ORD_PROPERTY_CITY)
            .ordPropertyStateCd(UPDATED_ORD_PROPERTY_STATE_CD)
            .ordPropertyZip(UPDATED_ORD_PROPERTY_ZIP)
            .ordPropertyCounty(UPDATED_ORD_PROPERTY_COUNTY)
            .ordPropertyLatitue(UPDATED_ORD_PROPERTY_LATITUE)
            .ordPropertyLongitude(UPDATED_ORD_PROPERTY_LONGITUDE)
            .ordPropertyProjectName(UPDATED_ORD_PROPERTY_PROJECT_NAME)
            .ordContact1TypeCd(UPDATED_ORD_CONTACT_1_TYPE_CD)
            .ordContact1Name(UPDATED_ORD_CONTACT_1_NAME)
            .ordContact1WorkPhone(UPDATED_ORD_CONTACT_1_WORK_PHONE)
            .ordContact1HomePhone(UPDATED_ORD_CONTACT_1_HOME_PHONE)
            .ordContact1CellPhone(UPDATED_ORD_CONTACT_1_CELL_PHONE)
            .ordContact1OtherCellPhone(UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE)
            .ordContact1Email(UPDATED_ORD_CONTACT_1_EMAIL)
            .ordContact1OtherEmail(UPDATED_ORD_CONTACT_1_OTHER_EMAIL)
            .ordContact2TypeCd(UPDATED_ORD_CONTACT_2_TYPE_CD)
            .ordContact2Name(UPDATED_ORD_CONTACT_2_NAME)
            .ordContact2WorkPhone(UPDATED_ORD_CONTACT_2_WORK_PHONE)
            .ordContact2HomePhone(UPDATED_ORD_CONTACT_2_HOME_PHONE)
            .ordContact2CellPhone(UPDATED_ORD_CONTACT_2_CELL_PHONE)
            .ordContact2OtherCellPhone(UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE)
            .ordContact2Email(UPDATED_ORD_CONTACT_2_EMAIL)
            .ordContact2OtherEmail(UPDATED_ORD_CONTACT_2_OTHER_EMAIL)
            .ordContact3TypeCd(UPDATED_ORD_CONTACT_3_TYPE_CD)
            .ordContact3Name(UPDATED_ORD_CONTACT_3_NAME)
            .ordContact3WorkPhone(UPDATED_ORD_CONTACT_3_WORK_PHONE)
            .ordContact3HomePhone(UPDATED_ORD_CONTACT_3_HOME_PHONE)
            .ordContact3CellPhone(UPDATED_ORD_CONTACT_3_CELL_PHONE)
            .ordContact3OtherCellPhone(UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE)
            .ordContact3Email(UPDATED_ORD_CONTACT_3_EMAIL)
            .ordContact3OtherEmail(UPDATED_ORD_CONTACT_3_OTHER_EMAIL)
            .ordRequestRrStatusCd(UPDATED_ORD_REQUEST_RR_STATUS_CD)
            .ordRequestRrVendorAppraisalId(UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID)
            .ordRequestRrvendorAppraisalDraftRcvdDt(UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT)
            .ordRequestRrXmlFormId(UPDATED_ORD_REQUEST_RR_XML_FORM_ID)
            .ordRequestRrPdfFormId(UPDATED_ORD_REQUEST_RR_PDF_FORM_ID)
            .ordRequestRrApprFileDataSourcCd(UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD)
            .ordRequestRrReportValueAmt(UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT)
            .ordRequestRrAppraisalCompanyName(UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME)
            .ordRequestRrvendorName(UPDATED_ORD_REQUEST_RRVENDOR_NAME)
            .ordRequestRrvendorLicenseNbr(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR)
            .ordRequestRrvendorLicenseStateCd(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD)
            .ordValuationCompletedProduct(UPDATED_ORD_VALUATION_COMPLETED_PRODUCT)
            .ordValuationDueToClientDt(UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT)
            .ordValuationReportRecivedDt(UPDATED_ORD_VALUATION_REPORT_RECIVED_DT)
            .ordValuationCompletionDt(UPDATED_ORD_VALUATION_COMPLETION_DT)
            .ordValuationReportStatusCd(UPDATED_ORD_VALUATION_REPORT_STATUS_CD)
            .ordValuationAppraisedValueAmt(UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT)
            .ordValuationBpoMarketValueAmt(UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT)
            .ordValuationBpoAsIsAmt(UPDATED_ORD_VALUATION_BPO_AS_IS_AMT)
            .ordValuationBpoAsRepairedValueAmt(UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT)
            .ordValuationRedFlagCode(UPDATED_ORD_VALUATION_RED_FLAG_CODE)
            .ordValuationRedFlagDesc(UPDATED_ORD_VALUATION_RED_FLAG_DESC)
            .ordValuationAmcAppraisalId(UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID)
            .ordValuationFinalReviewId(UPDATED_ORD_VALUATION_FINAL_REVIEW_ID)
            .ordReviewRecommendedValueAmt(UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT)
            .ordReviewDt(UPDATED_ORD_REVIEW_DT)
            .ordReviewRecommendedActionCd(UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD)
            .ordReviewReviewerDecisionCd(UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD)
            .ordReviewReviewScore(UPDATED_ORD_REVIEW_REVIEW_SCORE)
            .ordReviewFormUsed(UPDATED_ORD_REVIEW_FORM_USED)
            .ordReviewProviderOnWatchListInd(UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND)
            .ordReviewCuredValueAmt(UPDATED_ORD_REVIEW_CURED_VALUE_AMT)
            .ordReviewFinalRecommendedActionCd(UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);

        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrders)))
            .andExpect(status().isOk());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getOrdNumber()).isEqualTo(UPDATED_ORD_NUMBER);
        assertThat(testOrders.getOrdStageCd()).isEqualTo(UPDATED_ORD_STAGE_CD);
        assertThat(testOrders.getOrdOrgOrderStatusCd()).isEqualTo(UPDATED_ORD_ORG_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdCmpOrderStatusCd()).isEqualTo(UPDATED_ORD_CMP_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdProOrderStatusCd()).isEqualTo(UPDATED_ORD_PRO_ORDER_STATUS_CD);
        assertThat(testOrders.getOrdCmpNbr()).isEqualTo(UPDATED_ORD_CMP_NBR);
        assertThat(testOrders.getOrdBrnNbr()).isEqualTo(UPDATED_ORD_BRN_NBR);
        assertThat(testOrders.getOrdProNbr()).isEqualTo(UPDATED_ORD_PRO_NBR);
        assertThat(testOrders.getOrdProductCode()).isEqualTo(UPDATED_ORD_PRODUCT_CODE);
        assertThat(testOrders.getOrdOriginalValueAmt()).isEqualTo(UPDATED_ORD_ORIGINAL_VALUE_AMT);
        assertThat(testOrders.getOrdParentTrackingNbr()).isEqualTo(UPDATED_ORD_PARENT_TRACKING_NBR);
        assertThat(testOrders.getOrdUserNbr()).isEqualTo(UPDATED_ORD_USER_NBR);
        assertThat(testOrders.getOrdOrderDt()).isEqualTo(UPDATED_ORD_ORDER_DT);
        assertThat(testOrders.getOrdRushRequestDueDt()).isEqualTo(UPDATED_ORD_RUSH_REQUEST_DUE_DT);
        assertThat(testOrders.getOrdOrgInstructions()).isEqualTo(UPDATED_ORD_ORG_INSTRUCTIONS);
        assertThat(testOrders.getOrdOrgOtherInstructions()).isEqualTo(UPDATED_ORD_ORG_OTHER_INSTRUCTIONS);
        assertThat(testOrders.isOrdMultiOrderInd()).isEqualTo(UPDATED_ORD_MULTI_ORDER_IND);
        assertThat(testOrders.getOrdOrgDuedate()).isEqualTo(UPDATED_ORD_ORG_DUEDATE);
        assertThat(testOrders.getOrdProDuedate()).isEqualTo(UPDATED_ORD_PRO_DUEDATE);
        assertThat(testOrders.isOrdUcdpDataFnmaSubmitToUcdpInd()).isEqualTo(UPDATED_ORD_UCDP_DATA_FNMA_SUBMIT_TO_UCDP_IND);
        assertThat(testOrders.getOrdUcdpDataFnmaServicerNbr()).isEqualTo(UPDATED_ORD_UCDP_DATA_FNMA_SERVICER_NBR);
        assertThat(testOrders.isOrdUcdpDataFhlmcSubmitToUcdpInd()).isEqualTo(UPDATED_ORD_UCDP_DATA_FHLMC_SUBMIT_TO_UCDP_IND);
        assertThat(testOrders.getOrdUcdpDataFhlmcIdentificationNbr()).isEqualTo(UPDATED_ORD_UCDP_DATA_FHLMC_IDENTIFICATION_NBR);
        assertThat(testOrders.getOrdUcdpDataUcdpBussinessUnitNbr()).isEqualTo(UPDATED_ORD_UCDP_DATA_UCDP_BUSSINESS_UNIT_NBR);
        assertThat(testOrders.getOrdUcdpSdFnamaDocumentId()).isEqualTo(UPDATED_ORD_UCDP_SD_FNAMA_DOCUMENT_ID);
        assertThat(testOrders.getOrdUcdpSdFnmaSubmissionStatusId()).isEqualTo(UPDATED_ORD_UCDP_SD_FNMA_SUBMISSION_STATUS_ID);
        assertThat(testOrders.getOrdUcdpSdFhlmcDocumentFileCd()).isEqualTo(UPDATED_ORD_UCDP_SD_FHLMC_DOCUMENT_FILE_CD);
        assertThat(testOrders.getOrdUcdpSdEadSubmissionStatusCd()).isEqualTo(UPDATED_ORD_UCDP_SD_EAD_SUBMISSION_STATUS_CD);
        assertThat(testOrders.getOrdUcdpFinalSubmissionDt()).isEqualTo(UPDATED_ORD_UCDP_FINAL_SUBMISSION_DT);
        assertThat(testOrders.getOrdLoanNbr()).isEqualTo(UPDATED_ORD_LOAN_NBR);
        assertThat(testOrders.getOrdloanOldLoanNbr()).isEqualTo(UPDATED_ORDLOAN_OLD_LOAN_NBR);
        assertThat(testOrders.getOrdLoanProgram()).isEqualTo(UPDATED_ORD_LOAN_PROGRAM);
        assertThat(testOrders.getOrdLoanPurpose()).isEqualTo(UPDATED_ORD_LOAN_PURPOSE);
        assertThat(testOrders.getOrdLoanType()).isEqualTo(UPDATED_ORD_LOAN_TYPE);
        assertThat(testOrders.getOrdLoanAmt()).isEqualTo(UPDATED_ORD_LOAN_AMT);
        assertThat(testOrders.getOrdLoanQualifyingValueAmt()).isEqualTo(UPDATED_ORD_LOAN_QUALIFYING_VALUE_AMT);
        assertThat(testOrders.getOrdLoanSalesPriceAmt()).isEqualTo(UPDATED_ORD_LOAN_SALES_PRICE_AMT);
        assertThat(testOrders.getOrdLoanBorrowerLastName()).isEqualTo(UPDATED_ORD_LOAN_BORROWER_LAST_NAME);
        assertThat(testOrders.getOrdLoanBorrowerFirstName()).isEqualTo(UPDATED_ORD_LOAN_BORROWER_FIRST_NAME);
        assertThat(testOrders.getOrdLoanBorrowerMiddleName()).isEqualTo(UPDATED_ORD_LOAN_BORROWER_MIDDLE_NAME);
        assertThat(testOrders.isOrdLoanBorrowerIsCoBorrowerInd()).isEqualTo(UPDATED_ORD_LOAN_BORROWER_IS_CO_BORROWER_IND);
        assertThat(testOrders.getOrdLoanFhaCaseNbr()).isEqualTo(UPDATED_ORD_LOAN_FHA_CASE_NBR);
        assertThat(testOrders.isOrdLoanDeedRestrictionInd()).isEqualTo(UPDATED_ORD_LOAN_DEED_RESTRICTION_IND);
        assertThat(testOrders.getOrdLoanEstimatedCloseDt()).isEqualTo(UPDATED_ORD_LOAN_ESTIMATED_CLOSE_DT);
        assertThat(testOrders.isOrdLoanHpmlInd()).isEqualTo(UPDATED_ORD_LOAN_HPML_IND);
        assertThat(testOrders.isOrdLoanIsNewConstructionInd()).isEqualTo(UPDATED_ORD_LOAN_IS_NEW_CONSTRUCTION_IND);
        assertThat(testOrders.getOrdLoanCustomerSegmentCode()).isEqualTo(UPDATED_ORD_LOAN_CUSTOMER_SEGMENT_CODE);
        assertThat(testOrders.isOrdLoanNonSubjectPropertyInd()).isEqualTo(UPDATED_ORD_LOAN_NON_SUBJECT_PROPERTY_IND);
        assertThat(testOrders.getOrdLoanBorrowerRequestedCloseDt()).isEqualTo(UPDATED_ORD_LOAN_BORROWER_REQUESTED_CLOSE_DT);
        assertThat(testOrders.getOrdPropertyTypeCd()).isEqualTo(UPDATED_ORD_PROPERTY_TYPE_CD);
        assertThat(testOrders.getOrdPropertyAddress1()).isEqualTo(UPDATED_ORD_PROPERTY_ADDRESS_1);
        assertThat(testOrders.getOrdPropertyAddress2()).isEqualTo(UPDATED_ORD_PROPERTY_ADDRESS_2);
        assertThat(testOrders.getOrdPropertyCity()).isEqualTo(UPDATED_ORD_PROPERTY_CITY);
        assertThat(testOrders.getOrdPropertyStateCd()).isEqualTo(UPDATED_ORD_PROPERTY_STATE_CD);
        assertThat(testOrders.getOrdPropertyZip()).isEqualTo(UPDATED_ORD_PROPERTY_ZIP);
        assertThat(testOrders.getOrdPropertyCounty()).isEqualTo(UPDATED_ORD_PROPERTY_COUNTY);
        assertThat(testOrders.getOrdPropertyLatitue()).isEqualTo(UPDATED_ORD_PROPERTY_LATITUE);
        assertThat(testOrders.getOrdPropertyLongitude()).isEqualTo(UPDATED_ORD_PROPERTY_LONGITUDE);
        assertThat(testOrders.getOrdPropertyProjectName()).isEqualTo(UPDATED_ORD_PROPERTY_PROJECT_NAME);
        assertThat(testOrders.getOrdContact1TypeCd()).isEqualTo(UPDATED_ORD_CONTACT_1_TYPE_CD);
        assertThat(testOrders.getOrdContact1Name()).isEqualTo(UPDATED_ORD_CONTACT_1_NAME);
        assertThat(testOrders.getOrdContact1WorkPhone()).isEqualTo(UPDATED_ORD_CONTACT_1_WORK_PHONE);
        assertThat(testOrders.getOrdContact1HomePhone()).isEqualTo(UPDATED_ORD_CONTACT_1_HOME_PHONE);
        assertThat(testOrders.getOrdContact1CellPhone()).isEqualTo(UPDATED_ORD_CONTACT_1_CELL_PHONE);
        assertThat(testOrders.getOrdContact1OtherCellPhone()).isEqualTo(UPDATED_ORD_CONTACT_1_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact1Email()).isEqualTo(UPDATED_ORD_CONTACT_1_EMAIL);
        assertThat(testOrders.getOrdContact1OtherEmail()).isEqualTo(UPDATED_ORD_CONTACT_1_OTHER_EMAIL);
        assertThat(testOrders.getOrdContact2TypeCd()).isEqualTo(UPDATED_ORD_CONTACT_2_TYPE_CD);
        assertThat(testOrders.getOrdContact2Name()).isEqualTo(UPDATED_ORD_CONTACT_2_NAME);
        assertThat(testOrders.getOrdContact2WorkPhone()).isEqualTo(UPDATED_ORD_CONTACT_2_WORK_PHONE);
        assertThat(testOrders.getOrdContact2HomePhone()).isEqualTo(UPDATED_ORD_CONTACT_2_HOME_PHONE);
        assertThat(testOrders.getOrdContact2CellPhone()).isEqualTo(UPDATED_ORD_CONTACT_2_CELL_PHONE);
        assertThat(testOrders.getOrdContact2OtherCellPhone()).isEqualTo(UPDATED_ORD_CONTACT_2_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact2Email()).isEqualTo(UPDATED_ORD_CONTACT_2_EMAIL);
        assertThat(testOrders.getOrdContact2OtherEmail()).isEqualTo(UPDATED_ORD_CONTACT_2_OTHER_EMAIL);
        assertThat(testOrders.getOrdContact3TypeCd()).isEqualTo(UPDATED_ORD_CONTACT_3_TYPE_CD);
        assertThat(testOrders.getOrdContact3Name()).isEqualTo(UPDATED_ORD_CONTACT_3_NAME);
        assertThat(testOrders.getOrdContact3WorkPhone()).isEqualTo(UPDATED_ORD_CONTACT_3_WORK_PHONE);
        assertThat(testOrders.getOrdContact3HomePhone()).isEqualTo(UPDATED_ORD_CONTACT_3_HOME_PHONE);
        assertThat(testOrders.getOrdContact3CellPhone()).isEqualTo(UPDATED_ORD_CONTACT_3_CELL_PHONE);
        assertThat(testOrders.getOrdContact3OtherCellPhone()).isEqualTo(UPDATED_ORD_CONTACT_3_OTHER_CELL_PHONE);
        assertThat(testOrders.getOrdContact3Email()).isEqualTo(UPDATED_ORD_CONTACT_3_EMAIL);
        assertThat(testOrders.getOrdContact3OtherEmail()).isEqualTo(UPDATED_ORD_CONTACT_3_OTHER_EMAIL);
        assertThat(testOrders.getOrdRequestRrStatusCd()).isEqualTo(UPDATED_ORD_REQUEST_RR_STATUS_CD);
        assertThat(testOrders.getOrdRequestRrVendorAppraisalId()).isEqualTo(UPDATED_ORD_REQUEST_RR_VENDOR_APPRAISAL_ID);
        assertThat(testOrders.getOrdRequestRrvendorAppraisalDraftRcvdDt()).isEqualTo(UPDATED_ORD_REQUEST_RRVENDOR_APPRAISAL_DRAFT_RCVD_DT);
        assertThat(testOrders.getOrdRequestRrXmlFormId()).isEqualTo(UPDATED_ORD_REQUEST_RR_XML_FORM_ID);
        assertThat(testOrders.getOrdRequestRrPdfFormId()).isEqualTo(UPDATED_ORD_REQUEST_RR_PDF_FORM_ID);
        assertThat(testOrders.getOrdRequestRrApprFileDataSourcCd()).isEqualTo(UPDATED_ORD_REQUEST_RR_APPR_FILE_DATA_SOURC_CD);
        assertThat(testOrders.getOrdRequestRrReportValueAmt()).isEqualTo(UPDATED_ORD_REQUEST_RR_REPORT_VALUE_AMT);
        assertThat(testOrders.getOrdRequestRrAppraisalCompanyName()).isEqualTo(UPDATED_ORD_REQUEST_RR_APPRAISAL_COMPANY_NAME);
        assertThat(testOrders.getOrdRequestRrvendorName()).isEqualTo(UPDATED_ORD_REQUEST_RRVENDOR_NAME);
        assertThat(testOrders.getOrdRequestRrvendorLicenseNbr()).isEqualTo(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_NBR);
        assertThat(testOrders.getOrdRequestRrvendorLicenseStateCd()).isEqualTo(UPDATED_ORD_REQUEST_RRVENDOR_LICENSE_STATE_CD);
        assertThat(testOrders.getOrdValuationCompletedProduct()).isEqualTo(UPDATED_ORD_VALUATION_COMPLETED_PRODUCT);
        assertThat(testOrders.getOrdValuationDueToClientDt()).isEqualTo(UPDATED_ORD_VALUATION_DUE_TO_CLIENT_DT);
        assertThat(testOrders.getOrdValuationReportRecivedDt()).isEqualTo(UPDATED_ORD_VALUATION_REPORT_RECIVED_DT);
        assertThat(testOrders.getOrdValuationCompletionDt()).isEqualTo(UPDATED_ORD_VALUATION_COMPLETION_DT);
        assertThat(testOrders.getOrdValuationReportStatusCd()).isEqualTo(UPDATED_ORD_VALUATION_REPORT_STATUS_CD);
        assertThat(testOrders.getOrdValuationAppraisedValueAmt()).isEqualTo(UPDATED_ORD_VALUATION_APPRAISED_VALUE_AMT);
        assertThat(testOrders.getOrdValuationBpoMarketValueAmt()).isEqualTo(UPDATED_ORD_VALUATION_BPO_MARKET_VALUE_AMT);
        assertThat(testOrders.getOrdValuationBpoAsIsAmt()).isEqualTo(UPDATED_ORD_VALUATION_BPO_AS_IS_AMT);
        assertThat(testOrders.getOrdValuationBpoAsRepairedValueAmt()).isEqualTo(UPDATED_ORD_VALUATION_BPO_AS_REPAIRED_VALUE_AMT);
        assertThat(testOrders.getOrdValuationRedFlagCode()).isEqualTo(UPDATED_ORD_VALUATION_RED_FLAG_CODE);
        assertThat(testOrders.getOrdValuationRedFlagDesc()).isEqualTo(UPDATED_ORD_VALUATION_RED_FLAG_DESC);
        assertThat(testOrders.getOrdValuationAmcAppraisalId()).isEqualTo(UPDATED_ORD_VALUATION_AMC_APPRAISAL_ID);
        assertThat(testOrders.getOrdValuationFinalReviewId()).isEqualTo(UPDATED_ORD_VALUATION_FINAL_REVIEW_ID);
        assertThat(testOrders.getOrdReviewRecommendedValueAmt()).isEqualTo(UPDATED_ORD_REVIEW_RECOMMENDED_VALUE_AMT);
        assertThat(testOrders.getOrdReviewDt()).isEqualTo(UPDATED_ORD_REVIEW_DT);
        assertThat(testOrders.getOrdReviewRecommendedActionCd()).isEqualTo(UPDATED_ORD_REVIEW_RECOMMENDED_ACTION_CD);
        assertThat(testOrders.getOrdReviewReviewerDecisionCd()).isEqualTo(UPDATED_ORD_REVIEW_REVIEWER_DECISION_CD);
        assertThat(testOrders.getOrdReviewReviewScore()).isEqualTo(UPDATED_ORD_REVIEW_REVIEW_SCORE);
        assertThat(testOrders.getOrdReviewFormUsed()).isEqualTo(UPDATED_ORD_REVIEW_FORM_USED);
        assertThat(testOrders.isOrdReviewProviderOnWatchListInd()).isEqualTo(UPDATED_ORD_REVIEW_PROVIDER_ON_WATCH_LIST_IND);
        assertThat(testOrders.getOrdReviewCuredValueAmt()).isEqualTo(UPDATED_ORD_REVIEW_CURED_VALUE_AMT);
        assertThat(testOrders.getOrdReviewFinalRecommendedActionCd()).isEqualTo(UPDATED_ORD_REVIEW_FINAL_RECOMMENDED_ACTION_CD);
    }

    @Test
    @Transactional
    public void updateNonExistingOrders() throws Exception {
        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Create the Orders

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orders)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrders() throws Exception {
        // Initialize the database
        ordersService.save(orders);

        int databaseSizeBeforeDelete = ordersRepository.findAll().size();

        // Delete the orders
        restOrdersMockMvc.perform(delete("/api/orders/{id}", orders.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
