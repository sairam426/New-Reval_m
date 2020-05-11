package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "ord_number", length = 30, nullable = false)
    private String ordNumber;

    @NotNull
    @Size(max = 30)
    @Column(name = "ord_stage_cd", length = 30, nullable = false)
    private String ordStageCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "ord_org_order_status_cd", length = 30, nullable = false)
    private String ordOrgOrderStatusCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "ord_cmp_order_status_cd", length = 30, nullable = false)
    private String ordCmpOrderStatusCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "ord_pro_order_status_cd", length = 30, nullable = false)
    private String ordProOrderStatusCd;

    @Size(max = 30)
    @Column(name = "ord_cmp_nbr", length = 30)
    private String ordCmpNbr;

    @Size(max = 30)
    @Column(name = "ord_brn_nbr", length = 30)
    private String ordBrnNbr;

    @Size(max = 30)
    @Column(name = "ord_pro_nbr", length = 30)
    private String ordProNbr;

    @Size(max = 30)
    @Column(name = "ord_product_code", length = 30)
    private String ordProductCode;

    @NotNull
    @Column(name = "ord_original_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordOriginalValueAmt;

    @Size(max = 50)
    @Column(name = "ord_parent_tracking_nbr", length = 50)
    private String ordParentTrackingNbr;

    @Size(max = 30)
    @Column(name = "ord_user_nbr", length = 30)
    private String ordUserNbr;

    @NotNull
    @Column(name = "ord_order_dt", nullable = false)
    private LocalDate ordOrderDt;

    @Column(name = "ord_rush_request_due_dt")
    private LocalDate ordRushRequestDueDt;

    @Size(max = 240)
    @Column(name = "ord_org_instructions", length = 240)
    private String ordOrgInstructions;

    @Size(max = 240)
    @Column(name = "ord_org_other_instructions", length = 240)
    private String ordOrgOtherInstructions;

    @NotNull
    @Column(name = "ord_multi_order_ind", nullable = false)
    private Boolean ordMultiOrderInd;

    @Column(name = "ord_org_duedate")
    private LocalDate ordOrgDuedate;

    @Column(name = "ord_pro_duedate")
    private LocalDate ordProDuedate;

    @NotNull
    @Column(name = "ord_ucdp_data_fnma_submit_to_ucdp_ind", nullable = false)
    private Boolean ordUcdpDataFnmaSubmitToUcdpInd;

    @Size(max = 30)
    @Column(name = "ord_ucdp_data_fnma_servicer_nbr", length = 30)
    private String ordUcdpDataFnmaServicerNbr;

    @NotNull
    @Column(name = "ord_ucdp_data_fhlmc_submit_to_ucdp_ind", nullable = false)
    private Boolean ordUcdpDataFhlmcSubmitToUcdpInd;

    @Size(max = 30)
    @Column(name = "ord_ucdp_data_fhlmc_identification_nbr", length = 30)
    private String ordUcdpDataFhlmcIdentificationNbr;

    @Size(max = 30)
    @Column(name = "ord_ucdp_data_ucdp_bussiness_unit_nbr", length = 30)
    private String ordUcdpDataUcdpBussinessUnitNbr;

    @Size(max = 30)
    @Column(name = "ord_ucdp_sd_fnama_document_id", length = 30)
    private String ordUcdpSdFnamaDocumentId;

    @Size(max = 80)
    @Column(name = "ord_ucdp_sd_fnma_submission_status_id", length = 80)
    private String ordUcdpSdFnmaSubmissionStatusId;

    @Size(max = 80)
    @Column(name = "ord_ucdp_sd_fhlmc_document_file_cd", length = 80)
    private String ordUcdpSdFhlmcDocumentFileCd;

    @Size(max = 80)
    @Column(name = "ord_ucdp_sd_ead_submission_status_cd", length = 80)
    private String ordUcdpSdEadSubmissionStatusCd;

    @Column(name = "ord_ucdp_final_submission_dt")
    private LocalDate ordUcdpFinalSubmissionDt;

    @Size(max = 30)
    @Column(name = "ord_loan_nbr", length = 30)
    private String ordLoanNbr;

    @Size(max = 30)
    @Column(name = "ordloan_old_loan_nbr", length = 30)
    private String ordloanOldLoanNbr;

    @Size(max = 80)
    @Column(name = "ord_loan_program", length = 80)
    private String ordLoanProgram;

    @Size(max = 80)
    @Column(name = "ord_loan_purpose", length = 80)
    private String ordLoanPurpose;

    @Size(max = 80)
    @Column(name = "ord_loan_type", length = 80)
    private String ordLoanType;

    @NotNull
    @Column(name = "ord_loan_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordLoanAmt;

    @NotNull
    @Column(name = "ord_loan_qualifying_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordLoanQualifyingValueAmt;

    @NotNull
    @Column(name = "ord_loan_sales_price_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordLoanSalesPriceAmt;

    @Size(max = 30)
    @Column(name = "ord_loan_borrower_last_name", length = 30)
    private String ordLoanBorrowerLastName;

    @Size(max = 30)
    @Column(name = "ord_loan_borrower_first_name", length = 30)
    private String ordLoanBorrowerFirstName;

    @Size(max = 30)
    @Column(name = "ord_loan_borrower_middle_name", length = 30)
    private String ordLoanBorrowerMiddleName;

    @NotNull
    @Column(name = "ord_loan_borrower_is_co_borrower_ind", nullable = false)
    private Boolean ordLoanBorrowerIsCoBorrowerInd;

    @Size(max = 30)
    @Column(name = "ord_loan_fha_case_nbr", length = 30)
    private String ordLoanFhaCaseNbr;

    @NotNull
    @Column(name = "ord_loan_deed_restriction_ind", nullable = false)
    private Boolean ordLoanDeedRestrictionInd;

    @Column(name = "ord_loan_estimated_close_dt")
    private LocalDate ordLoanEstimatedCloseDt;

    @NotNull
    @Column(name = "ord_loan_hpml_ind", nullable = false)
    private Boolean ordLoanHpmlInd;

    @NotNull
    @Column(name = "ord_loan_is_new_construction_ind", nullable = false)
    private Boolean ordLoanIsNewConstructionInd;

    @Size(max = 30)
    @Column(name = "ord_loan_customer_segment_code", length = 30)
    private String ordLoanCustomerSegmentCode;

    @NotNull
    @Column(name = "ord_loan_non_subject_property_ind", nullable = false)
    private Boolean ordLoanNonSubjectPropertyInd;

    @Column(name = "ord_loan_borrower_requested_close_dt")
    private LocalDate ordLoanBorrowerRequestedCloseDt;

    @Size(max = 30)
    @Column(name = "ord_property_type_cd", length = 30)
    private String ordPropertyTypeCd;

    @Size(max = 80)
    @Column(name = "ord_property_address_1", length = 80)
    private String ordPropertyAddress1;

    @Size(max = 80)
    @Column(name = "ord_property_address_2", length = 80)
    private String ordPropertyAddress2;

    @Size(max = 80)
    @Column(name = "ord_property_city", length = 80)
    private String ordPropertyCity;

    @Size(max = 30)
    @Column(name = "ord_property_state_cd", length = 30)
    private String ordPropertyStateCd;

    @Size(max = 30)
    @Column(name = "ord_property_zip", length = 30)
    private String ordPropertyZip;

    @Size(max = 80)
    @Column(name = "ord_property_county", length = 80)
    private String ordPropertyCounty;

    @Size(max = 80)
    @Column(name = "ord_property_latitue", length = 80)
    private String ordPropertyLatitue;

    @Size(max = 80)
    @Column(name = "ord_property_longitude", length = 80)
    private String ordPropertyLongitude;

    @Size(max = 80)
    @Column(name = "ord_property_project_name", length = 80)
    private String ordPropertyProjectName;

    @Column(name = "ord_contact_1_type_cd")
    private String ordContact1TypeCd;

    @Size(max = 120)
    @Column(name = "ord_contact_1_name", length = 120)
    private String ordContact1Name;

    @Column(name = "ord_contact_1_work_phone")
    private Long ordContact1WorkPhone;

    @Column(name = "ord_contact_1_home_phone")
    private Long ordContact1HomePhone;

    @Column(name = "ord_contact_1_cell_phone")
    private Long ordContact1CellPhone;

    @Column(name = "ord_contact_1_other_cell_phone")
    private Long ordContact1OtherCellPhone;

    @Size(max = 80)
    @Column(name = "ord_contact_1_email", length = 80)
    private String ordContact1Email;

    @Size(max = 80)
    @Column(name = "ord_contact_1_other_email", length = 80)
    private String ordContact1OtherEmail;

    @Size(max = 30)
    @Column(name = "ord_contact_2_type_cd", length = 30)
    private String ordContact2TypeCd;

    @Size(max = 120)
    @Column(name = "ord_contact_2_name", length = 120)
    private String ordContact2Name;

    @Column(name = "ord_contact_2_work_phone")
    private Long ordContact2WorkPhone;

    @Column(name = "ord_contact_2_home_phone")
    private Long ordContact2HomePhone;

    @Column(name = "ord_contact_2_cell_phone")
    private Long ordContact2CellPhone;

    @Column(name = "ord_contact_2_other_cell_phone")
    private Long ordContact2OtherCellPhone;

    @Size(max = 80)
    @Column(name = "ord_contact_2_email", length = 80)
    private String ordContact2Email;

    @Size(max = 80)
    @Column(name = "ord_contact_2_other_email", length = 80)
    private String ordContact2OtherEmail;

    @Size(max = 30)
    @Column(name = "ord_contact_3_type_cd", length = 30)
    private String ordContact3TypeCd;

    @Size(max = 120)
    @Column(name = "ord_contact_3_name", length = 120)
    private String ordContact3Name;

    @Column(name = "ord_contact_3_work_phone")
    private Long ordContact3WorkPhone;

    @Column(name = "ord_contact_3_home_phone")
    private Long ordContact3HomePhone;

    @Column(name = "ord_contact_3_cell_phone")
    private Long ordContact3CellPhone;

    @Column(name = "ord_contact_3_other_cell_phone")
    private Long ordContact3OtherCellPhone;

    @Size(max = 80)
    @Column(name = "ord_contact_3_email", length = 80)
    private String ordContact3Email;

    @Size(max = 80)
    @Column(name = "ord_contact_3_other_email", length = 80)
    private String ordContact3OtherEmail;

    @Size(max = 30)
    @Column(name = "ord_request_rr_status_cd", length = 30)
    private String ordRequestRrStatusCd;

    @Size(max = 80)
    @Column(name = "ord_request_rr_vendor_appraisal_id", length = 80)
    private String ordRequestRrVendorAppraisalId;

    @Column(name = "ord_request_rrvendor_appraisal_draft_rcvd_dt")
    private LocalDate ordRequestRrvendorAppraisalDraftRcvdDt;

    @Size(max = 80)
    @Column(name = "ord_request_rr_xml_form_id", length = 80)
    private String ordRequestRrXmlFormId;

    @Size(max = 80)
    @Column(name = "ord_request_rr_pdf_form_id", length = 80)
    private String ordRequestRrPdfFormId;

    @Size(max = 30)
    @Column(name = "ord_request_rr_appr_file_data_sourc_cd", length = 30)
    private String ordRequestRrApprFileDataSourcCd;

    @NotNull
    @Column(name = "ord_request_rr_report_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordRequestRrReportValueAmt;

    @Size(max = 80)
    @Column(name = "ord_request_rr_appraisal_company_name", length = 80)
    private String ordRequestRrAppraisalCompanyName;

    @Size(max = 80)
    @Column(name = "ord_request_rrvendor_name", length = 80)
    private String ordRequestRrvendorName;

    @Size(max = 50)
    @Column(name = "ord_request_rrvendor_license_nbr", length = 50)
    private String ordRequestRrvendorLicenseNbr;

    @Size(max = 30)
    @Column(name = "ord_request_rrvendor_license_state_cd", length = 30)
    private String ordRequestRrvendorLicenseStateCd;

    @Size(max = 30)
    @Column(name = "ord_valuation_completed_product", length = 30)
    private String ordValuationCompletedProduct;

    @Column(name = "ord_valuation_due_to_client_dt")
    private LocalDate ordValuationDueToClientDt;

    @Column(name = "ord_valuation_report_recived_dt")
    private LocalDate ordValuationReportRecivedDt;

    @Column(name = "ord_valuation_completion_dt")
    private LocalDate ordValuationCompletionDt;

    @Size(max = 30)
    @Column(name = "ord_valuation_report_status_cd", length = 30)
    private String ordValuationReportStatusCd;

    @NotNull
    @Column(name = "ord_valuation_appraised_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordValuationAppraisedValueAmt;

    @NotNull
    @Column(name = "ord_valuation_bpo_market_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordValuationBpoMarketValueAmt;

    @NotNull
    @Column(name = "ord_valuation_bpo_as_is_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordValuationBpoAsIsAmt;

    @NotNull
    @Column(name = "ord_valuation_bpo_as_repaired_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordValuationBpoAsRepairedValueAmt;

    @Size(max = 30)
    @Column(name = "ord_valuation_red_flag_code", length = 30)
    private String ordValuationRedFlagCode;

    @Size(max = 80)
    @Column(name = "ord_valuation_red_flag_desc", length = 80)
    private String ordValuationRedFlagDesc;

    @Size(max = 80)
    @Column(name = "ord_valuation_amc_appraisal_id", length = 80)
    private String ordValuationAmcAppraisalId;

    @Column(name = "ord_valuation_final_review_id")
    private Long ordValuationFinalReviewId;

    @NotNull
    @Column(name = "ord_review_recommended_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordReviewRecommendedValueAmt;

    @Column(name = "ord_review_dt")
    private LocalDate ordReviewDt;

    @Size(max = 80)
    @Column(name = "ord_review_recommended_action_cd", length = 80)
    private String ordReviewRecommendedActionCd;

    @Size(max = 80)
    @Column(name = "ord_review_reviewer_decision_cd", length = 80)
    private String ordReviewReviewerDecisionCd;

    @Size(max = 80)
    @Column(name = "ord_review_review_score", length = 80)
    private String ordReviewReviewScore;

    @Size(max = 80)
    @Column(name = "ord_review_form_used", length = 80)
    private String ordReviewFormUsed;

    @NotNull
    @Column(name = "ord_review_provider_on_watch_list_ind", nullable = false)
    private Boolean ordReviewProviderOnWatchListInd;

    @NotNull
    @Column(name = "ord_review_cured_value_amt", precision = 21, scale = 2, nullable = false)
    private BigDecimal ordReviewCuredValueAmt;

    @Size(max = 80)
    @Column(name = "ord_review_final_recommended_action_cd", length = 80)
    private String ordReviewFinalRecommendedActionCd;

    @OneToMany(mappedBy = "order")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderComments> comments = new HashSet<>();

    @OneToMany(mappedBy = "order")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderDocuments> documents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdNumber() {
        return ordNumber;
    }

    public Orders ordNumber(String ordNumber) {
        this.ordNumber = ordNumber;
        return this;
    }

    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
    }

    public String getOrdStageCd() {
        return ordStageCd;
    }

    public Orders ordStageCd(String ordStageCd) {
        this.ordStageCd = ordStageCd;
        return this;
    }

    public void setOrdStageCd(String ordStageCd) {
        this.ordStageCd = ordStageCd;
    }

    public String getOrdOrgOrderStatusCd() {
        return ordOrgOrderStatusCd;
    }

    public Orders ordOrgOrderStatusCd(String ordOrgOrderStatusCd) {
        this.ordOrgOrderStatusCd = ordOrgOrderStatusCd;
        return this;
    }

    public void setOrdOrgOrderStatusCd(String ordOrgOrderStatusCd) {
        this.ordOrgOrderStatusCd = ordOrgOrderStatusCd;
    }

    public String getOrdCmpOrderStatusCd() {
        return ordCmpOrderStatusCd;
    }

    public Orders ordCmpOrderStatusCd(String ordCmpOrderStatusCd) {
        this.ordCmpOrderStatusCd = ordCmpOrderStatusCd;
        return this;
    }

    public void setOrdCmpOrderStatusCd(String ordCmpOrderStatusCd) {
        this.ordCmpOrderStatusCd = ordCmpOrderStatusCd;
    }

    public String getOrdProOrderStatusCd() {
        return ordProOrderStatusCd;
    }

    public Orders ordProOrderStatusCd(String ordProOrderStatusCd) {
        this.ordProOrderStatusCd = ordProOrderStatusCd;
        return this;
    }

    public void setOrdProOrderStatusCd(String ordProOrderStatusCd) {
        this.ordProOrderStatusCd = ordProOrderStatusCd;
    }

    public String getOrdCmpNbr() {
        return ordCmpNbr;
    }

    public Orders ordCmpNbr(String ordCmpNbr) {
        this.ordCmpNbr = ordCmpNbr;
        return this;
    }

    public void setOrdCmpNbr(String ordCmpNbr) {
        this.ordCmpNbr = ordCmpNbr;
    }

    public String getOrdBrnNbr() {
        return ordBrnNbr;
    }

    public Orders ordBrnNbr(String ordBrnNbr) {
        this.ordBrnNbr = ordBrnNbr;
        return this;
    }

    public void setOrdBrnNbr(String ordBrnNbr) {
        this.ordBrnNbr = ordBrnNbr;
    }

    public String getOrdProNbr() {
        return ordProNbr;
    }

    public Orders ordProNbr(String ordProNbr) {
        this.ordProNbr = ordProNbr;
        return this;
    }

    public void setOrdProNbr(String ordProNbr) {
        this.ordProNbr = ordProNbr;
    }

    public String getOrdProductCode() {
        return ordProductCode;
    }

    public Orders ordProductCode(String ordProductCode) {
        this.ordProductCode = ordProductCode;
        return this;
    }

    public void setOrdProductCode(String ordProductCode) {
        this.ordProductCode = ordProductCode;
    }

    public BigDecimal getOrdOriginalValueAmt() {
        return ordOriginalValueAmt;
    }

    public Orders ordOriginalValueAmt(BigDecimal ordOriginalValueAmt) {
        this.ordOriginalValueAmt = ordOriginalValueAmt;
        return this;
    }

    public void setOrdOriginalValueAmt(BigDecimal ordOriginalValueAmt) {
        this.ordOriginalValueAmt = ordOriginalValueAmt;
    }

    public String getOrdParentTrackingNbr() {
        return ordParentTrackingNbr;
    }

    public Orders ordParentTrackingNbr(String ordParentTrackingNbr) {
        this.ordParentTrackingNbr = ordParentTrackingNbr;
        return this;
    }

    public void setOrdParentTrackingNbr(String ordParentTrackingNbr) {
        this.ordParentTrackingNbr = ordParentTrackingNbr;
    }

    public String getOrdUserNbr() {
        return ordUserNbr;
    }

    public Orders ordUserNbr(String ordUserNbr) {
        this.ordUserNbr = ordUserNbr;
        return this;
    }

    public void setOrdUserNbr(String ordUserNbr) {
        this.ordUserNbr = ordUserNbr;
    }

    public LocalDate getOrdOrderDt() {
        return ordOrderDt;
    }

    public Orders ordOrderDt(LocalDate ordOrderDt) {
        this.ordOrderDt = ordOrderDt;
        return this;
    }

    public void setOrdOrderDt(LocalDate ordOrderDt) {
        this.ordOrderDt = ordOrderDt;
    }

    public LocalDate getOrdRushRequestDueDt() {
        return ordRushRequestDueDt;
    }

    public Orders ordRushRequestDueDt(LocalDate ordRushRequestDueDt) {
        this.ordRushRequestDueDt = ordRushRequestDueDt;
        return this;
    }

    public void setOrdRushRequestDueDt(LocalDate ordRushRequestDueDt) {
        this.ordRushRequestDueDt = ordRushRequestDueDt;
    }

    public String getOrdOrgInstructions() {
        return ordOrgInstructions;
    }

    public Orders ordOrgInstructions(String ordOrgInstructions) {
        this.ordOrgInstructions = ordOrgInstructions;
        return this;
    }

    public void setOrdOrgInstructions(String ordOrgInstructions) {
        this.ordOrgInstructions = ordOrgInstructions;
    }

    public String getOrdOrgOtherInstructions() {
        return ordOrgOtherInstructions;
    }

    public Orders ordOrgOtherInstructions(String ordOrgOtherInstructions) {
        this.ordOrgOtherInstructions = ordOrgOtherInstructions;
        return this;
    }

    public void setOrdOrgOtherInstructions(String ordOrgOtherInstructions) {
        this.ordOrgOtherInstructions = ordOrgOtherInstructions;
    }

    public Boolean isOrdMultiOrderInd() {
        return ordMultiOrderInd;
    }

    public Orders ordMultiOrderInd(Boolean ordMultiOrderInd) {
        this.ordMultiOrderInd = ordMultiOrderInd;
        return this;
    }

    public void setOrdMultiOrderInd(Boolean ordMultiOrderInd) {
        this.ordMultiOrderInd = ordMultiOrderInd;
    }

    public LocalDate getOrdOrgDuedate() {
        return ordOrgDuedate;
    }

    public Orders ordOrgDuedate(LocalDate ordOrgDuedate) {
        this.ordOrgDuedate = ordOrgDuedate;
        return this;
    }

    public void setOrdOrgDuedate(LocalDate ordOrgDuedate) {
        this.ordOrgDuedate = ordOrgDuedate;
    }

    public LocalDate getOrdProDuedate() {
        return ordProDuedate;
    }

    public Orders ordProDuedate(LocalDate ordProDuedate) {
        this.ordProDuedate = ordProDuedate;
        return this;
    }

    public void setOrdProDuedate(LocalDate ordProDuedate) {
        this.ordProDuedate = ordProDuedate;
    }

    public Boolean isOrdUcdpDataFnmaSubmitToUcdpInd() {
        return ordUcdpDataFnmaSubmitToUcdpInd;
    }

    public Orders ordUcdpDataFnmaSubmitToUcdpInd(Boolean ordUcdpDataFnmaSubmitToUcdpInd) {
        this.ordUcdpDataFnmaSubmitToUcdpInd = ordUcdpDataFnmaSubmitToUcdpInd;
        return this;
    }

    public void setOrdUcdpDataFnmaSubmitToUcdpInd(Boolean ordUcdpDataFnmaSubmitToUcdpInd) {
        this.ordUcdpDataFnmaSubmitToUcdpInd = ordUcdpDataFnmaSubmitToUcdpInd;
    }

    public String getOrdUcdpDataFnmaServicerNbr() {
        return ordUcdpDataFnmaServicerNbr;
    }

    public Orders ordUcdpDataFnmaServicerNbr(String ordUcdpDataFnmaServicerNbr) {
        this.ordUcdpDataFnmaServicerNbr = ordUcdpDataFnmaServicerNbr;
        return this;
    }

    public void setOrdUcdpDataFnmaServicerNbr(String ordUcdpDataFnmaServicerNbr) {
        this.ordUcdpDataFnmaServicerNbr = ordUcdpDataFnmaServicerNbr;
    }

    public Boolean isOrdUcdpDataFhlmcSubmitToUcdpInd() {
        return ordUcdpDataFhlmcSubmitToUcdpInd;
    }

    public Orders ordUcdpDataFhlmcSubmitToUcdpInd(Boolean ordUcdpDataFhlmcSubmitToUcdpInd) {
        this.ordUcdpDataFhlmcSubmitToUcdpInd = ordUcdpDataFhlmcSubmitToUcdpInd;
        return this;
    }

    public void setOrdUcdpDataFhlmcSubmitToUcdpInd(Boolean ordUcdpDataFhlmcSubmitToUcdpInd) {
        this.ordUcdpDataFhlmcSubmitToUcdpInd = ordUcdpDataFhlmcSubmitToUcdpInd;
    }

    public String getOrdUcdpDataFhlmcIdentificationNbr() {
        return ordUcdpDataFhlmcIdentificationNbr;
    }

    public Orders ordUcdpDataFhlmcIdentificationNbr(String ordUcdpDataFhlmcIdentificationNbr) {
        this.ordUcdpDataFhlmcIdentificationNbr = ordUcdpDataFhlmcIdentificationNbr;
        return this;
    }

    public void setOrdUcdpDataFhlmcIdentificationNbr(String ordUcdpDataFhlmcIdentificationNbr) {
        this.ordUcdpDataFhlmcIdentificationNbr = ordUcdpDataFhlmcIdentificationNbr;
    }

    public String getOrdUcdpDataUcdpBussinessUnitNbr() {
        return ordUcdpDataUcdpBussinessUnitNbr;
    }

    public Orders ordUcdpDataUcdpBussinessUnitNbr(String ordUcdpDataUcdpBussinessUnitNbr) {
        this.ordUcdpDataUcdpBussinessUnitNbr = ordUcdpDataUcdpBussinessUnitNbr;
        return this;
    }

    public void setOrdUcdpDataUcdpBussinessUnitNbr(String ordUcdpDataUcdpBussinessUnitNbr) {
        this.ordUcdpDataUcdpBussinessUnitNbr = ordUcdpDataUcdpBussinessUnitNbr;
    }

    public String getOrdUcdpSdFnamaDocumentId() {
        return ordUcdpSdFnamaDocumentId;
    }

    public Orders ordUcdpSdFnamaDocumentId(String ordUcdpSdFnamaDocumentId) {
        this.ordUcdpSdFnamaDocumentId = ordUcdpSdFnamaDocumentId;
        return this;
    }

    public void setOrdUcdpSdFnamaDocumentId(String ordUcdpSdFnamaDocumentId) {
        this.ordUcdpSdFnamaDocumentId = ordUcdpSdFnamaDocumentId;
    }

    public String getOrdUcdpSdFnmaSubmissionStatusId() {
        return ordUcdpSdFnmaSubmissionStatusId;
    }

    public Orders ordUcdpSdFnmaSubmissionStatusId(String ordUcdpSdFnmaSubmissionStatusId) {
        this.ordUcdpSdFnmaSubmissionStatusId = ordUcdpSdFnmaSubmissionStatusId;
        return this;
    }

    public void setOrdUcdpSdFnmaSubmissionStatusId(String ordUcdpSdFnmaSubmissionStatusId) {
        this.ordUcdpSdFnmaSubmissionStatusId = ordUcdpSdFnmaSubmissionStatusId;
    }

    public String getOrdUcdpSdFhlmcDocumentFileCd() {
        return ordUcdpSdFhlmcDocumentFileCd;
    }

    public Orders ordUcdpSdFhlmcDocumentFileCd(String ordUcdpSdFhlmcDocumentFileCd) {
        this.ordUcdpSdFhlmcDocumentFileCd = ordUcdpSdFhlmcDocumentFileCd;
        return this;
    }

    public void setOrdUcdpSdFhlmcDocumentFileCd(String ordUcdpSdFhlmcDocumentFileCd) {
        this.ordUcdpSdFhlmcDocumentFileCd = ordUcdpSdFhlmcDocumentFileCd;
    }

    public String getOrdUcdpSdEadSubmissionStatusCd() {
        return ordUcdpSdEadSubmissionStatusCd;
    }

    public Orders ordUcdpSdEadSubmissionStatusCd(String ordUcdpSdEadSubmissionStatusCd) {
        this.ordUcdpSdEadSubmissionStatusCd = ordUcdpSdEadSubmissionStatusCd;
        return this;
    }

    public void setOrdUcdpSdEadSubmissionStatusCd(String ordUcdpSdEadSubmissionStatusCd) {
        this.ordUcdpSdEadSubmissionStatusCd = ordUcdpSdEadSubmissionStatusCd;
    }

    public LocalDate getOrdUcdpFinalSubmissionDt() {
        return ordUcdpFinalSubmissionDt;
    }

    public Orders ordUcdpFinalSubmissionDt(LocalDate ordUcdpFinalSubmissionDt) {
        this.ordUcdpFinalSubmissionDt = ordUcdpFinalSubmissionDt;
        return this;
    }

    public void setOrdUcdpFinalSubmissionDt(LocalDate ordUcdpFinalSubmissionDt) {
        this.ordUcdpFinalSubmissionDt = ordUcdpFinalSubmissionDt;
    }

    public String getOrdLoanNbr() {
        return ordLoanNbr;
    }

    public Orders ordLoanNbr(String ordLoanNbr) {
        this.ordLoanNbr = ordLoanNbr;
        return this;
    }

    public void setOrdLoanNbr(String ordLoanNbr) {
        this.ordLoanNbr = ordLoanNbr;
    }

    public String getOrdloanOldLoanNbr() {
        return ordloanOldLoanNbr;
    }

    public Orders ordloanOldLoanNbr(String ordloanOldLoanNbr) {
        this.ordloanOldLoanNbr = ordloanOldLoanNbr;
        return this;
    }

    public void setOrdloanOldLoanNbr(String ordloanOldLoanNbr) {
        this.ordloanOldLoanNbr = ordloanOldLoanNbr;
    }

    public String getOrdLoanProgram() {
        return ordLoanProgram;
    }

    public Orders ordLoanProgram(String ordLoanProgram) {
        this.ordLoanProgram = ordLoanProgram;
        return this;
    }

    public void setOrdLoanProgram(String ordLoanProgram) {
        this.ordLoanProgram = ordLoanProgram;
    }

    public String getOrdLoanPurpose() {
        return ordLoanPurpose;
    }

    public Orders ordLoanPurpose(String ordLoanPurpose) {
        this.ordLoanPurpose = ordLoanPurpose;
        return this;
    }

    public void setOrdLoanPurpose(String ordLoanPurpose) {
        this.ordLoanPurpose = ordLoanPurpose;
    }

    public String getOrdLoanType() {
        return ordLoanType;
    }

    public Orders ordLoanType(String ordLoanType) {
        this.ordLoanType = ordLoanType;
        return this;
    }

    public void setOrdLoanType(String ordLoanType) {
        this.ordLoanType = ordLoanType;
    }

    public BigDecimal getOrdLoanAmt() {
        return ordLoanAmt;
    }

    public Orders ordLoanAmt(BigDecimal ordLoanAmt) {
        this.ordLoanAmt = ordLoanAmt;
        return this;
    }

    public void setOrdLoanAmt(BigDecimal ordLoanAmt) {
        this.ordLoanAmt = ordLoanAmt;
    }

    public BigDecimal getOrdLoanQualifyingValueAmt() {
        return ordLoanQualifyingValueAmt;
    }

    public Orders ordLoanQualifyingValueAmt(BigDecimal ordLoanQualifyingValueAmt) {
        this.ordLoanQualifyingValueAmt = ordLoanQualifyingValueAmt;
        return this;
    }

    public void setOrdLoanQualifyingValueAmt(BigDecimal ordLoanQualifyingValueAmt) {
        this.ordLoanQualifyingValueAmt = ordLoanQualifyingValueAmt;
    }

    public BigDecimal getOrdLoanSalesPriceAmt() {
        return ordLoanSalesPriceAmt;
    }

    public Orders ordLoanSalesPriceAmt(BigDecimal ordLoanSalesPriceAmt) {
        this.ordLoanSalesPriceAmt = ordLoanSalesPriceAmt;
        return this;
    }

    public void setOrdLoanSalesPriceAmt(BigDecimal ordLoanSalesPriceAmt) {
        this.ordLoanSalesPriceAmt = ordLoanSalesPriceAmt;
    }

    public String getOrdLoanBorrowerLastName() {
        return ordLoanBorrowerLastName;
    }

    public Orders ordLoanBorrowerLastName(String ordLoanBorrowerLastName) {
        this.ordLoanBorrowerLastName = ordLoanBorrowerLastName;
        return this;
    }

    public void setOrdLoanBorrowerLastName(String ordLoanBorrowerLastName) {
        this.ordLoanBorrowerLastName = ordLoanBorrowerLastName;
    }

    public String getOrdLoanBorrowerFirstName() {
        return ordLoanBorrowerFirstName;
    }

    public Orders ordLoanBorrowerFirstName(String ordLoanBorrowerFirstName) {
        this.ordLoanBorrowerFirstName = ordLoanBorrowerFirstName;
        return this;
    }

    public void setOrdLoanBorrowerFirstName(String ordLoanBorrowerFirstName) {
        this.ordLoanBorrowerFirstName = ordLoanBorrowerFirstName;
    }

    public String getOrdLoanBorrowerMiddleName() {
        return ordLoanBorrowerMiddleName;
    }

    public Orders ordLoanBorrowerMiddleName(String ordLoanBorrowerMiddleName) {
        this.ordLoanBorrowerMiddleName = ordLoanBorrowerMiddleName;
        return this;
    }

    public void setOrdLoanBorrowerMiddleName(String ordLoanBorrowerMiddleName) {
        this.ordLoanBorrowerMiddleName = ordLoanBorrowerMiddleName;
    }

    public Boolean isOrdLoanBorrowerIsCoBorrowerInd() {
        return ordLoanBorrowerIsCoBorrowerInd;
    }

    public Orders ordLoanBorrowerIsCoBorrowerInd(Boolean ordLoanBorrowerIsCoBorrowerInd) {
        this.ordLoanBorrowerIsCoBorrowerInd = ordLoanBorrowerIsCoBorrowerInd;
        return this;
    }

    public void setOrdLoanBorrowerIsCoBorrowerInd(Boolean ordLoanBorrowerIsCoBorrowerInd) {
        this.ordLoanBorrowerIsCoBorrowerInd = ordLoanBorrowerIsCoBorrowerInd;
    }

    public String getOrdLoanFhaCaseNbr() {
        return ordLoanFhaCaseNbr;
    }

    public Orders ordLoanFhaCaseNbr(String ordLoanFhaCaseNbr) {
        this.ordLoanFhaCaseNbr = ordLoanFhaCaseNbr;
        return this;
    }

    public void setOrdLoanFhaCaseNbr(String ordLoanFhaCaseNbr) {
        this.ordLoanFhaCaseNbr = ordLoanFhaCaseNbr;
    }

    public Boolean isOrdLoanDeedRestrictionInd() {
        return ordLoanDeedRestrictionInd;
    }

    public Orders ordLoanDeedRestrictionInd(Boolean ordLoanDeedRestrictionInd) {
        this.ordLoanDeedRestrictionInd = ordLoanDeedRestrictionInd;
        return this;
    }

    public void setOrdLoanDeedRestrictionInd(Boolean ordLoanDeedRestrictionInd) {
        this.ordLoanDeedRestrictionInd = ordLoanDeedRestrictionInd;
    }

    public LocalDate getOrdLoanEstimatedCloseDt() {
        return ordLoanEstimatedCloseDt;
    }

    public Orders ordLoanEstimatedCloseDt(LocalDate ordLoanEstimatedCloseDt) {
        this.ordLoanEstimatedCloseDt = ordLoanEstimatedCloseDt;
        return this;
    }

    public void setOrdLoanEstimatedCloseDt(LocalDate ordLoanEstimatedCloseDt) {
        this.ordLoanEstimatedCloseDt = ordLoanEstimatedCloseDt;
    }

    public Boolean isOrdLoanHpmlInd() {
        return ordLoanHpmlInd;
    }

    public Orders ordLoanHpmlInd(Boolean ordLoanHpmlInd) {
        this.ordLoanHpmlInd = ordLoanHpmlInd;
        return this;
    }

    public void setOrdLoanHpmlInd(Boolean ordLoanHpmlInd) {
        this.ordLoanHpmlInd = ordLoanHpmlInd;
    }

    public Boolean isOrdLoanIsNewConstructionInd() {
        return ordLoanIsNewConstructionInd;
    }

    public Orders ordLoanIsNewConstructionInd(Boolean ordLoanIsNewConstructionInd) {
        this.ordLoanIsNewConstructionInd = ordLoanIsNewConstructionInd;
        return this;
    }

    public void setOrdLoanIsNewConstructionInd(Boolean ordLoanIsNewConstructionInd) {
        this.ordLoanIsNewConstructionInd = ordLoanIsNewConstructionInd;
    }

    public String getOrdLoanCustomerSegmentCode() {
        return ordLoanCustomerSegmentCode;
    }

    public Orders ordLoanCustomerSegmentCode(String ordLoanCustomerSegmentCode) {
        this.ordLoanCustomerSegmentCode = ordLoanCustomerSegmentCode;
        return this;
    }

    public void setOrdLoanCustomerSegmentCode(String ordLoanCustomerSegmentCode) {
        this.ordLoanCustomerSegmentCode = ordLoanCustomerSegmentCode;
    }

    public Boolean isOrdLoanNonSubjectPropertyInd() {
        return ordLoanNonSubjectPropertyInd;
    }

    public Orders ordLoanNonSubjectPropertyInd(Boolean ordLoanNonSubjectPropertyInd) {
        this.ordLoanNonSubjectPropertyInd = ordLoanNonSubjectPropertyInd;
        return this;
    }

    public void setOrdLoanNonSubjectPropertyInd(Boolean ordLoanNonSubjectPropertyInd) {
        this.ordLoanNonSubjectPropertyInd = ordLoanNonSubjectPropertyInd;
    }

    public LocalDate getOrdLoanBorrowerRequestedCloseDt() {
        return ordLoanBorrowerRequestedCloseDt;
    }

    public Orders ordLoanBorrowerRequestedCloseDt(LocalDate ordLoanBorrowerRequestedCloseDt) {
        this.ordLoanBorrowerRequestedCloseDt = ordLoanBorrowerRequestedCloseDt;
        return this;
    }

    public void setOrdLoanBorrowerRequestedCloseDt(LocalDate ordLoanBorrowerRequestedCloseDt) {
        this.ordLoanBorrowerRequestedCloseDt = ordLoanBorrowerRequestedCloseDt;
    }

    public String getOrdPropertyTypeCd() {
        return ordPropertyTypeCd;
    }

    public Orders ordPropertyTypeCd(String ordPropertyTypeCd) {
        this.ordPropertyTypeCd = ordPropertyTypeCd;
        return this;
    }

    public void setOrdPropertyTypeCd(String ordPropertyTypeCd) {
        this.ordPropertyTypeCd = ordPropertyTypeCd;
    }

    public String getOrdPropertyAddress1() {
        return ordPropertyAddress1;
    }

    public Orders ordPropertyAddress1(String ordPropertyAddress1) {
        this.ordPropertyAddress1 = ordPropertyAddress1;
        return this;
    }

    public void setOrdPropertyAddress1(String ordPropertyAddress1) {
        this.ordPropertyAddress1 = ordPropertyAddress1;
    }

    public String getOrdPropertyAddress2() {
        return ordPropertyAddress2;
    }

    public Orders ordPropertyAddress2(String ordPropertyAddress2) {
        this.ordPropertyAddress2 = ordPropertyAddress2;
        return this;
    }

    public void setOrdPropertyAddress2(String ordPropertyAddress2) {
        this.ordPropertyAddress2 = ordPropertyAddress2;
    }

    public String getOrdPropertyCity() {
        return ordPropertyCity;
    }

    public Orders ordPropertyCity(String ordPropertyCity) {
        this.ordPropertyCity = ordPropertyCity;
        return this;
    }

    public void setOrdPropertyCity(String ordPropertyCity) {
        this.ordPropertyCity = ordPropertyCity;
    }

    public String getOrdPropertyStateCd() {
        return ordPropertyStateCd;
    }

    public Orders ordPropertyStateCd(String ordPropertyStateCd) {
        this.ordPropertyStateCd = ordPropertyStateCd;
        return this;
    }

    public void setOrdPropertyStateCd(String ordPropertyStateCd) {
        this.ordPropertyStateCd = ordPropertyStateCd;
    }

    public String getOrdPropertyZip() {
        return ordPropertyZip;
    }

    public Orders ordPropertyZip(String ordPropertyZip) {
        this.ordPropertyZip = ordPropertyZip;
        return this;
    }

    public void setOrdPropertyZip(String ordPropertyZip) {
        this.ordPropertyZip = ordPropertyZip;
    }

    public String getOrdPropertyCounty() {
        return ordPropertyCounty;
    }

    public Orders ordPropertyCounty(String ordPropertyCounty) {
        this.ordPropertyCounty = ordPropertyCounty;
        return this;
    }

    public void setOrdPropertyCounty(String ordPropertyCounty) {
        this.ordPropertyCounty = ordPropertyCounty;
    }

    public String getOrdPropertyLatitue() {
        return ordPropertyLatitue;
    }

    public Orders ordPropertyLatitue(String ordPropertyLatitue) {
        this.ordPropertyLatitue = ordPropertyLatitue;
        return this;
    }

    public void setOrdPropertyLatitue(String ordPropertyLatitue) {
        this.ordPropertyLatitue = ordPropertyLatitue;
    }

    public String getOrdPropertyLongitude() {
        return ordPropertyLongitude;
    }

    public Orders ordPropertyLongitude(String ordPropertyLongitude) {
        this.ordPropertyLongitude = ordPropertyLongitude;
        return this;
    }

    public void setOrdPropertyLongitude(String ordPropertyLongitude) {
        this.ordPropertyLongitude = ordPropertyLongitude;
    }

    public String getOrdPropertyProjectName() {
        return ordPropertyProjectName;
    }

    public Orders ordPropertyProjectName(String ordPropertyProjectName) {
        this.ordPropertyProjectName = ordPropertyProjectName;
        return this;
    }

    public void setOrdPropertyProjectName(String ordPropertyProjectName) {
        this.ordPropertyProjectName = ordPropertyProjectName;
    }

    public String getOrdContact1TypeCd() {
        return ordContact1TypeCd;
    }

    public Orders ordContact1TypeCd(String ordContact1TypeCd) {
        this.ordContact1TypeCd = ordContact1TypeCd;
        return this;
    }

    public void setOrdContact1TypeCd(String ordContact1TypeCd) {
        this.ordContact1TypeCd = ordContact1TypeCd;
    }

    public String getOrdContact1Name() {
        return ordContact1Name;
    }

    public Orders ordContact1Name(String ordContact1Name) {
        this.ordContact1Name = ordContact1Name;
        return this;
    }

    public void setOrdContact1Name(String ordContact1Name) {
        this.ordContact1Name = ordContact1Name;
    }

    public Long getOrdContact1WorkPhone() {
        return ordContact1WorkPhone;
    }

    public Orders ordContact1WorkPhone(Long ordContact1WorkPhone) {
        this.ordContact1WorkPhone = ordContact1WorkPhone;
        return this;
    }

    public void setOrdContact1WorkPhone(Long ordContact1WorkPhone) {
        this.ordContact1WorkPhone = ordContact1WorkPhone;
    }

    public Long getOrdContact1HomePhone() {
        return ordContact1HomePhone;
    }

    public Orders ordContact1HomePhone(Long ordContact1HomePhone) {
        this.ordContact1HomePhone = ordContact1HomePhone;
        return this;
    }

    public void setOrdContact1HomePhone(Long ordContact1HomePhone) {
        this.ordContact1HomePhone = ordContact1HomePhone;
    }

    public Long getOrdContact1CellPhone() {
        return ordContact1CellPhone;
    }

    public Orders ordContact1CellPhone(Long ordContact1CellPhone) {
        this.ordContact1CellPhone = ordContact1CellPhone;
        return this;
    }

    public void setOrdContact1CellPhone(Long ordContact1CellPhone) {
        this.ordContact1CellPhone = ordContact1CellPhone;
    }

    public Long getOrdContact1OtherCellPhone() {
        return ordContact1OtherCellPhone;
    }

    public Orders ordContact1OtherCellPhone(Long ordContact1OtherCellPhone) {
        this.ordContact1OtherCellPhone = ordContact1OtherCellPhone;
        return this;
    }

    public void setOrdContact1OtherCellPhone(Long ordContact1OtherCellPhone) {
        this.ordContact1OtherCellPhone = ordContact1OtherCellPhone;
    }

    public String getOrdContact1Email() {
        return ordContact1Email;
    }

    public Orders ordContact1Email(String ordContact1Email) {
        this.ordContact1Email = ordContact1Email;
        return this;
    }

    public void setOrdContact1Email(String ordContact1Email) {
        this.ordContact1Email = ordContact1Email;
    }

    public String getOrdContact1OtherEmail() {
        return ordContact1OtherEmail;
    }

    public Orders ordContact1OtherEmail(String ordContact1OtherEmail) {
        this.ordContact1OtherEmail = ordContact1OtherEmail;
        return this;
    }

    public void setOrdContact1OtherEmail(String ordContact1OtherEmail) {
        this.ordContact1OtherEmail = ordContact1OtherEmail;
    }

    public String getOrdContact2TypeCd() {
        return ordContact2TypeCd;
    }

    public Orders ordContact2TypeCd(String ordContact2TypeCd) {
        this.ordContact2TypeCd = ordContact2TypeCd;
        return this;
    }

    public void setOrdContact2TypeCd(String ordContact2TypeCd) {
        this.ordContact2TypeCd = ordContact2TypeCd;
    }

    public String getOrdContact2Name() {
        return ordContact2Name;
    }

    public Orders ordContact2Name(String ordContact2Name) {
        this.ordContact2Name = ordContact2Name;
        return this;
    }

    public void setOrdContact2Name(String ordContact2Name) {
        this.ordContact2Name = ordContact2Name;
    }

    public Long getOrdContact2WorkPhone() {
        return ordContact2WorkPhone;
    }

    public Orders ordContact2WorkPhone(Long ordContact2WorkPhone) {
        this.ordContact2WorkPhone = ordContact2WorkPhone;
        return this;
    }

    public void setOrdContact2WorkPhone(Long ordContact2WorkPhone) {
        this.ordContact2WorkPhone = ordContact2WorkPhone;
    }

    public Long getOrdContact2HomePhone() {
        return ordContact2HomePhone;
    }

    public Orders ordContact2HomePhone(Long ordContact2HomePhone) {
        this.ordContact2HomePhone = ordContact2HomePhone;
        return this;
    }

    public void setOrdContact2HomePhone(Long ordContact2HomePhone) {
        this.ordContact2HomePhone = ordContact2HomePhone;
    }

    public Long getOrdContact2CellPhone() {
        return ordContact2CellPhone;
    }

    public Orders ordContact2CellPhone(Long ordContact2CellPhone) {
        this.ordContact2CellPhone = ordContact2CellPhone;
        return this;
    }

    public void setOrdContact2CellPhone(Long ordContact2CellPhone) {
        this.ordContact2CellPhone = ordContact2CellPhone;
    }

    public Long getOrdContact2OtherCellPhone() {
        return ordContact2OtherCellPhone;
    }

    public Orders ordContact2OtherCellPhone(Long ordContact2OtherCellPhone) {
        this.ordContact2OtherCellPhone = ordContact2OtherCellPhone;
        return this;
    }

    public void setOrdContact2OtherCellPhone(Long ordContact2OtherCellPhone) {
        this.ordContact2OtherCellPhone = ordContact2OtherCellPhone;
    }

    public String getOrdContact2Email() {
        return ordContact2Email;
    }

    public Orders ordContact2Email(String ordContact2Email) {
        this.ordContact2Email = ordContact2Email;
        return this;
    }

    public void setOrdContact2Email(String ordContact2Email) {
        this.ordContact2Email = ordContact2Email;
    }

    public String getOrdContact2OtherEmail() {
        return ordContact2OtherEmail;
    }

    public Orders ordContact2OtherEmail(String ordContact2OtherEmail) {
        this.ordContact2OtherEmail = ordContact2OtherEmail;
        return this;
    }

    public void setOrdContact2OtherEmail(String ordContact2OtherEmail) {
        this.ordContact2OtherEmail = ordContact2OtherEmail;
    }

    public String getOrdContact3TypeCd() {
        return ordContact3TypeCd;
    }

    public Orders ordContact3TypeCd(String ordContact3TypeCd) {
        this.ordContact3TypeCd = ordContact3TypeCd;
        return this;
    }

    public void setOrdContact3TypeCd(String ordContact3TypeCd) {
        this.ordContact3TypeCd = ordContact3TypeCd;
    }

    public String getOrdContact3Name() {
        return ordContact3Name;
    }

    public Orders ordContact3Name(String ordContact3Name) {
        this.ordContact3Name = ordContact3Name;
        return this;
    }

    public void setOrdContact3Name(String ordContact3Name) {
        this.ordContact3Name = ordContact3Name;
    }

    public Long getOrdContact3WorkPhone() {
        return ordContact3WorkPhone;
    }

    public Orders ordContact3WorkPhone(Long ordContact3WorkPhone) {
        this.ordContact3WorkPhone = ordContact3WorkPhone;
        return this;
    }

    public void setOrdContact3WorkPhone(Long ordContact3WorkPhone) {
        this.ordContact3WorkPhone = ordContact3WorkPhone;
    }

    public Long getOrdContact3HomePhone() {
        return ordContact3HomePhone;
    }

    public Orders ordContact3HomePhone(Long ordContact3HomePhone) {
        this.ordContact3HomePhone = ordContact3HomePhone;
        return this;
    }

    public void setOrdContact3HomePhone(Long ordContact3HomePhone) {
        this.ordContact3HomePhone = ordContact3HomePhone;
    }

    public Long getOrdContact3CellPhone() {
        return ordContact3CellPhone;
    }

    public Orders ordContact3CellPhone(Long ordContact3CellPhone) {
        this.ordContact3CellPhone = ordContact3CellPhone;
        return this;
    }

    public void setOrdContact3CellPhone(Long ordContact3CellPhone) {
        this.ordContact3CellPhone = ordContact3CellPhone;
    }

    public Long getOrdContact3OtherCellPhone() {
        return ordContact3OtherCellPhone;
    }

    public Orders ordContact3OtherCellPhone(Long ordContact3OtherCellPhone) {
        this.ordContact3OtherCellPhone = ordContact3OtherCellPhone;
        return this;
    }

    public void setOrdContact3OtherCellPhone(Long ordContact3OtherCellPhone) {
        this.ordContact3OtherCellPhone = ordContact3OtherCellPhone;
    }

    public String getOrdContact3Email() {
        return ordContact3Email;
    }

    public Orders ordContact3Email(String ordContact3Email) {
        this.ordContact3Email = ordContact3Email;
        return this;
    }

    public void setOrdContact3Email(String ordContact3Email) {
        this.ordContact3Email = ordContact3Email;
    }

    public String getOrdContact3OtherEmail() {
        return ordContact3OtherEmail;
    }

    public Orders ordContact3OtherEmail(String ordContact3OtherEmail) {
        this.ordContact3OtherEmail = ordContact3OtherEmail;
        return this;
    }

    public void setOrdContact3OtherEmail(String ordContact3OtherEmail) {
        this.ordContact3OtherEmail = ordContact3OtherEmail;
    }

    public String getOrdRequestRrStatusCd() {
        return ordRequestRrStatusCd;
    }

    public Orders ordRequestRrStatusCd(String ordRequestRrStatusCd) {
        this.ordRequestRrStatusCd = ordRequestRrStatusCd;
        return this;
    }

    public void setOrdRequestRrStatusCd(String ordRequestRrStatusCd) {
        this.ordRequestRrStatusCd = ordRequestRrStatusCd;
    }

    public String getOrdRequestRrVendorAppraisalId() {
        return ordRequestRrVendorAppraisalId;
    }

    public Orders ordRequestRrVendorAppraisalId(String ordRequestRrVendorAppraisalId) {
        this.ordRequestRrVendorAppraisalId = ordRequestRrVendorAppraisalId;
        return this;
    }

    public void setOrdRequestRrVendorAppraisalId(String ordRequestRrVendorAppraisalId) {
        this.ordRequestRrVendorAppraisalId = ordRequestRrVendorAppraisalId;
    }

    public LocalDate getOrdRequestRrvendorAppraisalDraftRcvdDt() {
        return ordRequestRrvendorAppraisalDraftRcvdDt;
    }

    public Orders ordRequestRrvendorAppraisalDraftRcvdDt(LocalDate ordRequestRrvendorAppraisalDraftRcvdDt) {
        this.ordRequestRrvendorAppraisalDraftRcvdDt = ordRequestRrvendorAppraisalDraftRcvdDt;
        return this;
    }

    public void setOrdRequestRrvendorAppraisalDraftRcvdDt(LocalDate ordRequestRrvendorAppraisalDraftRcvdDt) {
        this.ordRequestRrvendorAppraisalDraftRcvdDt = ordRequestRrvendorAppraisalDraftRcvdDt;
    }

    public String getOrdRequestRrXmlFormId() {
        return ordRequestRrXmlFormId;
    }

    public Orders ordRequestRrXmlFormId(String ordRequestRrXmlFormId) {
        this.ordRequestRrXmlFormId = ordRequestRrXmlFormId;
        return this;
    }

    public void setOrdRequestRrXmlFormId(String ordRequestRrXmlFormId) {
        this.ordRequestRrXmlFormId = ordRequestRrXmlFormId;
    }

    public String getOrdRequestRrPdfFormId() {
        return ordRequestRrPdfFormId;
    }

    public Orders ordRequestRrPdfFormId(String ordRequestRrPdfFormId) {
        this.ordRequestRrPdfFormId = ordRequestRrPdfFormId;
        return this;
    }

    public void setOrdRequestRrPdfFormId(String ordRequestRrPdfFormId) {
        this.ordRequestRrPdfFormId = ordRequestRrPdfFormId;
    }

    public String getOrdRequestRrApprFileDataSourcCd() {
        return ordRequestRrApprFileDataSourcCd;
    }

    public Orders ordRequestRrApprFileDataSourcCd(String ordRequestRrApprFileDataSourcCd) {
        this.ordRequestRrApprFileDataSourcCd = ordRequestRrApprFileDataSourcCd;
        return this;
    }

    public void setOrdRequestRrApprFileDataSourcCd(String ordRequestRrApprFileDataSourcCd) {
        this.ordRequestRrApprFileDataSourcCd = ordRequestRrApprFileDataSourcCd;
    }

    public BigDecimal getOrdRequestRrReportValueAmt() {
        return ordRequestRrReportValueAmt;
    }

    public Orders ordRequestRrReportValueAmt(BigDecimal ordRequestRrReportValueAmt) {
        this.ordRequestRrReportValueAmt = ordRequestRrReportValueAmt;
        return this;
    }

    public void setOrdRequestRrReportValueAmt(BigDecimal ordRequestRrReportValueAmt) {
        this.ordRequestRrReportValueAmt = ordRequestRrReportValueAmt;
    }

    public String getOrdRequestRrAppraisalCompanyName() {
        return ordRequestRrAppraisalCompanyName;
    }

    public Orders ordRequestRrAppraisalCompanyName(String ordRequestRrAppraisalCompanyName) {
        this.ordRequestRrAppraisalCompanyName = ordRequestRrAppraisalCompanyName;
        return this;
    }

    public void setOrdRequestRrAppraisalCompanyName(String ordRequestRrAppraisalCompanyName) {
        this.ordRequestRrAppraisalCompanyName = ordRequestRrAppraisalCompanyName;
    }

    public String getOrdRequestRrvendorName() {
        return ordRequestRrvendorName;
    }

    public Orders ordRequestRrvendorName(String ordRequestRrvendorName) {
        this.ordRequestRrvendorName = ordRequestRrvendorName;
        return this;
    }

    public void setOrdRequestRrvendorName(String ordRequestRrvendorName) {
        this.ordRequestRrvendorName = ordRequestRrvendorName;
    }

    public String getOrdRequestRrvendorLicenseNbr() {
        return ordRequestRrvendorLicenseNbr;
    }

    public Orders ordRequestRrvendorLicenseNbr(String ordRequestRrvendorLicenseNbr) {
        this.ordRequestRrvendorLicenseNbr = ordRequestRrvendorLicenseNbr;
        return this;
    }

    public void setOrdRequestRrvendorLicenseNbr(String ordRequestRrvendorLicenseNbr) {
        this.ordRequestRrvendorLicenseNbr = ordRequestRrvendorLicenseNbr;
    }

    public String getOrdRequestRrvendorLicenseStateCd() {
        return ordRequestRrvendorLicenseStateCd;
    }

    public Orders ordRequestRrvendorLicenseStateCd(String ordRequestRrvendorLicenseStateCd) {
        this.ordRequestRrvendorLicenseStateCd = ordRequestRrvendorLicenseStateCd;
        return this;
    }

    public void setOrdRequestRrvendorLicenseStateCd(String ordRequestRrvendorLicenseStateCd) {
        this.ordRequestRrvendorLicenseStateCd = ordRequestRrvendorLicenseStateCd;
    }

    public String getOrdValuationCompletedProduct() {
        return ordValuationCompletedProduct;
    }

    public Orders ordValuationCompletedProduct(String ordValuationCompletedProduct) {
        this.ordValuationCompletedProduct = ordValuationCompletedProduct;
        return this;
    }

    public void setOrdValuationCompletedProduct(String ordValuationCompletedProduct) {
        this.ordValuationCompletedProduct = ordValuationCompletedProduct;
    }

    public LocalDate getOrdValuationDueToClientDt() {
        return ordValuationDueToClientDt;
    }

    public Orders ordValuationDueToClientDt(LocalDate ordValuationDueToClientDt) {
        this.ordValuationDueToClientDt = ordValuationDueToClientDt;
        return this;
    }

    public void setOrdValuationDueToClientDt(LocalDate ordValuationDueToClientDt) {
        this.ordValuationDueToClientDt = ordValuationDueToClientDt;
    }

    public LocalDate getOrdValuationReportRecivedDt() {
        return ordValuationReportRecivedDt;
    }

    public Orders ordValuationReportRecivedDt(LocalDate ordValuationReportRecivedDt) {
        this.ordValuationReportRecivedDt = ordValuationReportRecivedDt;
        return this;
    }

    public void setOrdValuationReportRecivedDt(LocalDate ordValuationReportRecivedDt) {
        this.ordValuationReportRecivedDt = ordValuationReportRecivedDt;
    }

    public LocalDate getOrdValuationCompletionDt() {
        return ordValuationCompletionDt;
    }

    public Orders ordValuationCompletionDt(LocalDate ordValuationCompletionDt) {
        this.ordValuationCompletionDt = ordValuationCompletionDt;
        return this;
    }

    public void setOrdValuationCompletionDt(LocalDate ordValuationCompletionDt) {
        this.ordValuationCompletionDt = ordValuationCompletionDt;
    }

    public String getOrdValuationReportStatusCd() {
        return ordValuationReportStatusCd;
    }

    public Orders ordValuationReportStatusCd(String ordValuationReportStatusCd) {
        this.ordValuationReportStatusCd = ordValuationReportStatusCd;
        return this;
    }

    public void setOrdValuationReportStatusCd(String ordValuationReportStatusCd) {
        this.ordValuationReportStatusCd = ordValuationReportStatusCd;
    }

    public BigDecimal getOrdValuationAppraisedValueAmt() {
        return ordValuationAppraisedValueAmt;
    }

    public Orders ordValuationAppraisedValueAmt(BigDecimal ordValuationAppraisedValueAmt) {
        this.ordValuationAppraisedValueAmt = ordValuationAppraisedValueAmt;
        return this;
    }

    public void setOrdValuationAppraisedValueAmt(BigDecimal ordValuationAppraisedValueAmt) {
        this.ordValuationAppraisedValueAmt = ordValuationAppraisedValueAmt;
    }

    public BigDecimal getOrdValuationBpoMarketValueAmt() {
        return ordValuationBpoMarketValueAmt;
    }

    public Orders ordValuationBpoMarketValueAmt(BigDecimal ordValuationBpoMarketValueAmt) {
        this.ordValuationBpoMarketValueAmt = ordValuationBpoMarketValueAmt;
        return this;
    }

    public void setOrdValuationBpoMarketValueAmt(BigDecimal ordValuationBpoMarketValueAmt) {
        this.ordValuationBpoMarketValueAmt = ordValuationBpoMarketValueAmt;
    }

    public BigDecimal getOrdValuationBpoAsIsAmt() {
        return ordValuationBpoAsIsAmt;
    }

    public Orders ordValuationBpoAsIsAmt(BigDecimal ordValuationBpoAsIsAmt) {
        this.ordValuationBpoAsIsAmt = ordValuationBpoAsIsAmt;
        return this;
    }

    public void setOrdValuationBpoAsIsAmt(BigDecimal ordValuationBpoAsIsAmt) {
        this.ordValuationBpoAsIsAmt = ordValuationBpoAsIsAmt;
    }

    public BigDecimal getOrdValuationBpoAsRepairedValueAmt() {
        return ordValuationBpoAsRepairedValueAmt;
    }

    public Orders ordValuationBpoAsRepairedValueAmt(BigDecimal ordValuationBpoAsRepairedValueAmt) {
        this.ordValuationBpoAsRepairedValueAmt = ordValuationBpoAsRepairedValueAmt;
        return this;
    }

    public void setOrdValuationBpoAsRepairedValueAmt(BigDecimal ordValuationBpoAsRepairedValueAmt) {
        this.ordValuationBpoAsRepairedValueAmt = ordValuationBpoAsRepairedValueAmt;
    }

    public String getOrdValuationRedFlagCode() {
        return ordValuationRedFlagCode;
    }

    public Orders ordValuationRedFlagCode(String ordValuationRedFlagCode) {
        this.ordValuationRedFlagCode = ordValuationRedFlagCode;
        return this;
    }

    public void setOrdValuationRedFlagCode(String ordValuationRedFlagCode) {
        this.ordValuationRedFlagCode = ordValuationRedFlagCode;
    }

    public String getOrdValuationRedFlagDesc() {
        return ordValuationRedFlagDesc;
    }

    public Orders ordValuationRedFlagDesc(String ordValuationRedFlagDesc) {
        this.ordValuationRedFlagDesc = ordValuationRedFlagDesc;
        return this;
    }

    public void setOrdValuationRedFlagDesc(String ordValuationRedFlagDesc) {
        this.ordValuationRedFlagDesc = ordValuationRedFlagDesc;
    }

    public String getOrdValuationAmcAppraisalId() {
        return ordValuationAmcAppraisalId;
    }

    public Orders ordValuationAmcAppraisalId(String ordValuationAmcAppraisalId) {
        this.ordValuationAmcAppraisalId = ordValuationAmcAppraisalId;
        return this;
    }

    public void setOrdValuationAmcAppraisalId(String ordValuationAmcAppraisalId) {
        this.ordValuationAmcAppraisalId = ordValuationAmcAppraisalId;
    }

    public Long getOrdValuationFinalReviewId() {
        return ordValuationFinalReviewId;
    }

    public Orders ordValuationFinalReviewId(Long ordValuationFinalReviewId) {
        this.ordValuationFinalReviewId = ordValuationFinalReviewId;
        return this;
    }

    public void setOrdValuationFinalReviewId(Long ordValuationFinalReviewId) {
        this.ordValuationFinalReviewId = ordValuationFinalReviewId;
    }

    public BigDecimal getOrdReviewRecommendedValueAmt() {
        return ordReviewRecommendedValueAmt;
    }

    public Orders ordReviewRecommendedValueAmt(BigDecimal ordReviewRecommendedValueAmt) {
        this.ordReviewRecommendedValueAmt = ordReviewRecommendedValueAmt;
        return this;
    }

    public void setOrdReviewRecommendedValueAmt(BigDecimal ordReviewRecommendedValueAmt) {
        this.ordReviewRecommendedValueAmt = ordReviewRecommendedValueAmt;
    }

    public LocalDate getOrdReviewDt() {
        return ordReviewDt;
    }

    public Orders ordReviewDt(LocalDate ordReviewDt) {
        this.ordReviewDt = ordReviewDt;
        return this;
    }

    public void setOrdReviewDt(LocalDate ordReviewDt) {
        this.ordReviewDt = ordReviewDt;
    }

    public String getOrdReviewRecommendedActionCd() {
        return ordReviewRecommendedActionCd;
    }

    public Orders ordReviewRecommendedActionCd(String ordReviewRecommendedActionCd) {
        this.ordReviewRecommendedActionCd = ordReviewRecommendedActionCd;
        return this;
    }

    public void setOrdReviewRecommendedActionCd(String ordReviewRecommendedActionCd) {
        this.ordReviewRecommendedActionCd = ordReviewRecommendedActionCd;
    }

    public String getOrdReviewReviewerDecisionCd() {
        return ordReviewReviewerDecisionCd;
    }

    public Orders ordReviewReviewerDecisionCd(String ordReviewReviewerDecisionCd) {
        this.ordReviewReviewerDecisionCd = ordReviewReviewerDecisionCd;
        return this;
    }

    public void setOrdReviewReviewerDecisionCd(String ordReviewReviewerDecisionCd) {
        this.ordReviewReviewerDecisionCd = ordReviewReviewerDecisionCd;
    }

    public String getOrdReviewReviewScore() {
        return ordReviewReviewScore;
    }

    public Orders ordReviewReviewScore(String ordReviewReviewScore) {
        this.ordReviewReviewScore = ordReviewReviewScore;
        return this;
    }

    public void setOrdReviewReviewScore(String ordReviewReviewScore) {
        this.ordReviewReviewScore = ordReviewReviewScore;
    }

    public String getOrdReviewFormUsed() {
        return ordReviewFormUsed;
    }

    public Orders ordReviewFormUsed(String ordReviewFormUsed) {
        this.ordReviewFormUsed = ordReviewFormUsed;
        return this;
    }

    public void setOrdReviewFormUsed(String ordReviewFormUsed) {
        this.ordReviewFormUsed = ordReviewFormUsed;
    }

    public Boolean isOrdReviewProviderOnWatchListInd() {
        return ordReviewProviderOnWatchListInd;
    }

    public Orders ordReviewProviderOnWatchListInd(Boolean ordReviewProviderOnWatchListInd) {
        this.ordReviewProviderOnWatchListInd = ordReviewProviderOnWatchListInd;
        return this;
    }

    public void setOrdReviewProviderOnWatchListInd(Boolean ordReviewProviderOnWatchListInd) {
        this.ordReviewProviderOnWatchListInd = ordReviewProviderOnWatchListInd;
    }

    public BigDecimal getOrdReviewCuredValueAmt() {
        return ordReviewCuredValueAmt;
    }

    public Orders ordReviewCuredValueAmt(BigDecimal ordReviewCuredValueAmt) {
        this.ordReviewCuredValueAmt = ordReviewCuredValueAmt;
        return this;
    }

    public void setOrdReviewCuredValueAmt(BigDecimal ordReviewCuredValueAmt) {
        this.ordReviewCuredValueAmt = ordReviewCuredValueAmt;
    }

    public String getOrdReviewFinalRecommendedActionCd() {
        return ordReviewFinalRecommendedActionCd;
    }

    public Orders ordReviewFinalRecommendedActionCd(String ordReviewFinalRecommendedActionCd) {
        this.ordReviewFinalRecommendedActionCd = ordReviewFinalRecommendedActionCd;
        return this;
    }

    public void setOrdReviewFinalRecommendedActionCd(String ordReviewFinalRecommendedActionCd) {
        this.ordReviewFinalRecommendedActionCd = ordReviewFinalRecommendedActionCd;
    }

    public Set<OrderComments> getComments() {
        return comments;
    }

    public Orders comments(Set<OrderComments> orderComments) {
        this.comments = orderComments;
        return this;
    }

    public Orders addComments(OrderComments orderComments) {
        this.comments.add(orderComments);
        orderComments.setOrder(this);
        return this;
    }

    public Orders removeComments(OrderComments orderComments) {
        this.comments.remove(orderComments);
        orderComments.setOrder(null);
        return this;
    }

    public void setComments(Set<OrderComments> orderComments) {
        this.comments = orderComments;
    }

    public Set<OrderDocuments> getDocuments() {
        return documents;
    }

    public Orders documents(Set<OrderDocuments> orderDocuments) {
        this.documents = orderDocuments;
        return this;
    }

    public Orders addDocuments(OrderDocuments orderDocuments) {
        this.documents.add(orderDocuments);
        orderDocuments.setOrder(this);
        return this;
    }

    public Orders removeDocuments(OrderDocuments orderDocuments) {
        this.documents.remove(orderDocuments);
        orderDocuments.setOrder(null);
        return this;
    }

    public void setDocuments(Set<OrderDocuments> orderDocuments) {
        this.documents = orderDocuments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orders)) {
            return false;
        }
        return id != null && id.equals(((Orders) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", ordNumber='" + getOrdNumber() + "'" +
            ", ordStageCd='" + getOrdStageCd() + "'" +
            ", ordOrgOrderStatusCd='" + getOrdOrgOrderStatusCd() + "'" +
            ", ordCmpOrderStatusCd='" + getOrdCmpOrderStatusCd() + "'" +
            ", ordProOrderStatusCd='" + getOrdProOrderStatusCd() + "'" +
            ", ordCmpNbr='" + getOrdCmpNbr() + "'" +
            ", ordBrnNbr='" + getOrdBrnNbr() + "'" +
            ", ordProNbr='" + getOrdProNbr() + "'" +
            ", ordProductCode='" + getOrdProductCode() + "'" +
            ", ordOriginalValueAmt=" + getOrdOriginalValueAmt() +
            ", ordParentTrackingNbr='" + getOrdParentTrackingNbr() + "'" +
            ", ordUserNbr='" + getOrdUserNbr() + "'" +
            ", ordOrderDt='" + getOrdOrderDt() + "'" +
            ", ordRushRequestDueDt='" + getOrdRushRequestDueDt() + "'" +
            ", ordOrgInstructions='" + getOrdOrgInstructions() + "'" +
            ", ordOrgOtherInstructions='" + getOrdOrgOtherInstructions() + "'" +
            ", ordMultiOrderInd='" + isOrdMultiOrderInd() + "'" +
            ", ordOrgDuedate='" + getOrdOrgDuedate() + "'" +
            ", ordProDuedate='" + getOrdProDuedate() + "'" +
            ", ordUcdpDataFnmaSubmitToUcdpInd='" + isOrdUcdpDataFnmaSubmitToUcdpInd() + "'" +
            ", ordUcdpDataFnmaServicerNbr='" + getOrdUcdpDataFnmaServicerNbr() + "'" +
            ", ordUcdpDataFhlmcSubmitToUcdpInd='" + isOrdUcdpDataFhlmcSubmitToUcdpInd() + "'" +
            ", ordUcdpDataFhlmcIdentificationNbr='" + getOrdUcdpDataFhlmcIdentificationNbr() + "'" +
            ", ordUcdpDataUcdpBussinessUnitNbr='" + getOrdUcdpDataUcdpBussinessUnitNbr() + "'" +
            ", ordUcdpSdFnamaDocumentId='" + getOrdUcdpSdFnamaDocumentId() + "'" +
            ", ordUcdpSdFnmaSubmissionStatusId='" + getOrdUcdpSdFnmaSubmissionStatusId() + "'" +
            ", ordUcdpSdFhlmcDocumentFileCd='" + getOrdUcdpSdFhlmcDocumentFileCd() + "'" +
            ", ordUcdpSdEadSubmissionStatusCd='" + getOrdUcdpSdEadSubmissionStatusCd() + "'" +
            ", ordUcdpFinalSubmissionDt='" + getOrdUcdpFinalSubmissionDt() + "'" +
            ", ordLoanNbr='" + getOrdLoanNbr() + "'" +
            ", ordloanOldLoanNbr='" + getOrdloanOldLoanNbr() + "'" +
            ", ordLoanProgram='" + getOrdLoanProgram() + "'" +
            ", ordLoanPurpose='" + getOrdLoanPurpose() + "'" +
            ", ordLoanType='" + getOrdLoanType() + "'" +
            ", ordLoanAmt=" + getOrdLoanAmt() +
            ", ordLoanQualifyingValueAmt=" + getOrdLoanQualifyingValueAmt() +
            ", ordLoanSalesPriceAmt=" + getOrdLoanSalesPriceAmt() +
            ", ordLoanBorrowerLastName='" + getOrdLoanBorrowerLastName() + "'" +
            ", ordLoanBorrowerFirstName='" + getOrdLoanBorrowerFirstName() + "'" +
            ", ordLoanBorrowerMiddleName='" + getOrdLoanBorrowerMiddleName() + "'" +
            ", ordLoanBorrowerIsCoBorrowerInd='" + isOrdLoanBorrowerIsCoBorrowerInd() + "'" +
            ", ordLoanFhaCaseNbr='" + getOrdLoanFhaCaseNbr() + "'" +
            ", ordLoanDeedRestrictionInd='" + isOrdLoanDeedRestrictionInd() + "'" +
            ", ordLoanEstimatedCloseDt='" + getOrdLoanEstimatedCloseDt() + "'" +
            ", ordLoanHpmlInd='" + isOrdLoanHpmlInd() + "'" +
            ", ordLoanIsNewConstructionInd='" + isOrdLoanIsNewConstructionInd() + "'" +
            ", ordLoanCustomerSegmentCode='" + getOrdLoanCustomerSegmentCode() + "'" +
            ", ordLoanNonSubjectPropertyInd='" + isOrdLoanNonSubjectPropertyInd() + "'" +
            ", ordLoanBorrowerRequestedCloseDt='" + getOrdLoanBorrowerRequestedCloseDt() + "'" +
            ", ordPropertyTypeCd='" + getOrdPropertyTypeCd() + "'" +
            ", ordPropertyAddress1='" + getOrdPropertyAddress1() + "'" +
            ", ordPropertyAddress2='" + getOrdPropertyAddress2() + "'" +
            ", ordPropertyCity='" + getOrdPropertyCity() + "'" +
            ", ordPropertyStateCd='" + getOrdPropertyStateCd() + "'" +
            ", ordPropertyZip='" + getOrdPropertyZip() + "'" +
            ", ordPropertyCounty='" + getOrdPropertyCounty() + "'" +
            ", ordPropertyLatitue='" + getOrdPropertyLatitue() + "'" +
            ", ordPropertyLongitude='" + getOrdPropertyLongitude() + "'" +
            ", ordPropertyProjectName='" + getOrdPropertyProjectName() + "'" +
            ", ordContact1TypeCd='" + getOrdContact1TypeCd() + "'" +
            ", ordContact1Name='" + getOrdContact1Name() + "'" +
            ", ordContact1WorkPhone=" + getOrdContact1WorkPhone() +
            ", ordContact1HomePhone=" + getOrdContact1HomePhone() +
            ", ordContact1CellPhone=" + getOrdContact1CellPhone() +
            ", ordContact1OtherCellPhone=" + getOrdContact1OtherCellPhone() +
            ", ordContact1Email='" + getOrdContact1Email() + "'" +
            ", ordContact1OtherEmail='" + getOrdContact1OtherEmail() + "'" +
            ", ordContact2TypeCd='" + getOrdContact2TypeCd() + "'" +
            ", ordContact2Name='" + getOrdContact2Name() + "'" +
            ", ordContact2WorkPhone=" + getOrdContact2WorkPhone() +
            ", ordContact2HomePhone=" + getOrdContact2HomePhone() +
            ", ordContact2CellPhone=" + getOrdContact2CellPhone() +
            ", ordContact2OtherCellPhone=" + getOrdContact2OtherCellPhone() +
            ", ordContact2Email='" + getOrdContact2Email() + "'" +
            ", ordContact2OtherEmail='" + getOrdContact2OtherEmail() + "'" +
            ", ordContact3TypeCd='" + getOrdContact3TypeCd() + "'" +
            ", ordContact3Name='" + getOrdContact3Name() + "'" +
            ", ordContact3WorkPhone=" + getOrdContact3WorkPhone() +
            ", ordContact3HomePhone=" + getOrdContact3HomePhone() +
            ", ordContact3CellPhone=" + getOrdContact3CellPhone() +
            ", ordContact3OtherCellPhone=" + getOrdContact3OtherCellPhone() +
            ", ordContact3Email='" + getOrdContact3Email() + "'" +
            ", ordContact3OtherEmail='" + getOrdContact3OtherEmail() + "'" +
            ", ordRequestRrStatusCd='" + getOrdRequestRrStatusCd() + "'" +
            ", ordRequestRrVendorAppraisalId='" + getOrdRequestRrVendorAppraisalId() + "'" +
            ", ordRequestRrvendorAppraisalDraftRcvdDt='" + getOrdRequestRrvendorAppraisalDraftRcvdDt() + "'" +
            ", ordRequestRrXmlFormId='" + getOrdRequestRrXmlFormId() + "'" +
            ", ordRequestRrPdfFormId='" + getOrdRequestRrPdfFormId() + "'" +
            ", ordRequestRrApprFileDataSourcCd='" + getOrdRequestRrApprFileDataSourcCd() + "'" +
            ", ordRequestRrReportValueAmt=" + getOrdRequestRrReportValueAmt() +
            ", ordRequestRrAppraisalCompanyName='" + getOrdRequestRrAppraisalCompanyName() + "'" +
            ", ordRequestRrvendorName='" + getOrdRequestRrvendorName() + "'" +
            ", ordRequestRrvendorLicenseNbr='" + getOrdRequestRrvendorLicenseNbr() + "'" +
            ", ordRequestRrvendorLicenseStateCd='" + getOrdRequestRrvendorLicenseStateCd() + "'" +
            ", ordValuationCompletedProduct='" + getOrdValuationCompletedProduct() + "'" +
            ", ordValuationDueToClientDt='" + getOrdValuationDueToClientDt() + "'" +
            ", ordValuationReportRecivedDt='" + getOrdValuationReportRecivedDt() + "'" +
            ", ordValuationCompletionDt='" + getOrdValuationCompletionDt() + "'" +
            ", ordValuationReportStatusCd='" + getOrdValuationReportStatusCd() + "'" +
            ", ordValuationAppraisedValueAmt=" + getOrdValuationAppraisedValueAmt() +
            ", ordValuationBpoMarketValueAmt=" + getOrdValuationBpoMarketValueAmt() +
            ", ordValuationBpoAsIsAmt=" + getOrdValuationBpoAsIsAmt() +
            ", ordValuationBpoAsRepairedValueAmt=" + getOrdValuationBpoAsRepairedValueAmt() +
            ", ordValuationRedFlagCode='" + getOrdValuationRedFlagCode() + "'" +
            ", ordValuationRedFlagDesc='" + getOrdValuationRedFlagDesc() + "'" +
            ", ordValuationAmcAppraisalId='" + getOrdValuationAmcAppraisalId() + "'" +
            ", ordValuationFinalReviewId=" + getOrdValuationFinalReviewId() +
            ", ordReviewRecommendedValueAmt=" + getOrdReviewRecommendedValueAmt() +
            ", ordReviewDt='" + getOrdReviewDt() + "'" +
            ", ordReviewRecommendedActionCd='" + getOrdReviewRecommendedActionCd() + "'" +
            ", ordReviewReviewerDecisionCd='" + getOrdReviewReviewerDecisionCd() + "'" +
            ", ordReviewReviewScore='" + getOrdReviewReviewScore() + "'" +
            ", ordReviewFormUsed='" + getOrdReviewFormUsed() + "'" +
            ", ordReviewProviderOnWatchListInd='" + isOrdReviewProviderOnWatchListInd() + "'" +
            ", ordReviewCuredValueAmt=" + getOrdReviewCuredValueAmt() +
            ", ordReviewFinalRecommendedActionCd='" + getOrdReviewFinalRecommendedActionCd() + "'" +
            "}";
    }
}
