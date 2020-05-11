package com.jhipster.reval.ui.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.jhipster.reval.ui.domain.Orders} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.OrdersResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /orders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrdersCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ordNumber;

    private StringFilter ordStageCd;

    private StringFilter ordOrgOrderStatusCd;

    private StringFilter ordCmpOrderStatusCd;

    private StringFilter ordProOrderStatusCd;

    private StringFilter ordCmpNbr;

    private StringFilter ordBrnNbr;

    private StringFilter ordProNbr;

    private StringFilter ordProductCode;

    private BigDecimalFilter ordOriginalValueAmt;

    private StringFilter ordParentTrackingNbr;

    private StringFilter ordUserNbr;

    private LocalDateFilter ordOrderDt;

    private LocalDateFilter ordRushRequestDueDt;

    private StringFilter ordOrgInstructions;

    private StringFilter ordOrgOtherInstructions;

    private BooleanFilter ordMultiOrderInd;

    private LocalDateFilter ordOrgDuedate;

    private LocalDateFilter ordProDuedate;

    private BooleanFilter ordUcdpDataFnmaSubmitToUcdpInd;

    private StringFilter ordUcdpDataFnmaServicerNbr;

    private BooleanFilter ordUcdpDataFhlmcSubmitToUcdpInd;

    private StringFilter ordUcdpDataFhlmcIdentificationNbr;

    private StringFilter ordUcdpDataUcdpBussinessUnitNbr;

    private StringFilter ordUcdpSdFnamaDocumentId;

    private StringFilter ordUcdpSdFnmaSubmissionStatusId;

    private StringFilter ordUcdpSdFhlmcDocumentFileCd;

    private StringFilter ordUcdpSdEadSubmissionStatusCd;

    private LocalDateFilter ordUcdpFinalSubmissionDt;

    private StringFilter ordLoanNbr;

    private StringFilter ordloanOldLoanNbr;

    private StringFilter ordLoanProgram;

    private StringFilter ordLoanPurpose;

    private StringFilter ordLoanType;

    private BigDecimalFilter ordLoanAmt;

    private BigDecimalFilter ordLoanQualifyingValueAmt;

    private BigDecimalFilter ordLoanSalesPriceAmt;

    private StringFilter ordLoanBorrowerLastName;

    private StringFilter ordLoanBorrowerFirstName;

    private StringFilter ordLoanBorrowerMiddleName;

    private BooleanFilter ordLoanBorrowerIsCoBorrowerInd;

    private StringFilter ordLoanFhaCaseNbr;

    private BooleanFilter ordLoanDeedRestrictionInd;

    private LocalDateFilter ordLoanEstimatedCloseDt;

    private BooleanFilter ordLoanHpmlInd;

    private BooleanFilter ordLoanIsNewConstructionInd;

    private StringFilter ordLoanCustomerSegmentCode;

    private BooleanFilter ordLoanNonSubjectPropertyInd;

    private LocalDateFilter ordLoanBorrowerRequestedCloseDt;

    private StringFilter ordPropertyTypeCd;

    private StringFilter ordPropertyAddress1;

    private StringFilter ordPropertyAddress2;

    private StringFilter ordPropertyCity;

    private StringFilter ordPropertyStateCd;

    private StringFilter ordPropertyZip;

    private StringFilter ordPropertyCounty;

    private StringFilter ordPropertyLatitue;

    private StringFilter ordPropertyLongitude;

    private StringFilter ordPropertyProjectName;

    private StringFilter ordContact1TypeCd;

    private StringFilter ordContact1Name;

    private LongFilter ordContact1WorkPhone;

    private LongFilter ordContact1HomePhone;

    private LongFilter ordContact1CellPhone;

    private LongFilter ordContact1OtherCellPhone;

    private StringFilter ordContact1Email;

    private StringFilter ordContact1OtherEmail;

    private StringFilter ordContact2TypeCd;

    private StringFilter ordContact2Name;

    private LongFilter ordContact2WorkPhone;

    private LongFilter ordContact2HomePhone;

    private LongFilter ordContact2CellPhone;

    private LongFilter ordContact2OtherCellPhone;

    private StringFilter ordContact2Email;

    private StringFilter ordContact2OtherEmail;

    private StringFilter ordContact3TypeCd;

    private StringFilter ordContact3Name;

    private LongFilter ordContact3WorkPhone;

    private LongFilter ordContact3HomePhone;

    private LongFilter ordContact3CellPhone;

    private LongFilter ordContact3OtherCellPhone;

    private StringFilter ordContact3Email;

    private StringFilter ordContact3OtherEmail;

    private StringFilter ordRequestRrStatusCd;

    private StringFilter ordRequestRrVendorAppraisalId;

    private LocalDateFilter ordRequestRrvendorAppraisalDraftRcvdDt;

    private StringFilter ordRequestRrXmlFormId;

    private StringFilter ordRequestRrPdfFormId;

    private StringFilter ordRequestRrApprFileDataSourcCd;

    private BigDecimalFilter ordRequestRrReportValueAmt;

    private StringFilter ordRequestRrAppraisalCompanyName;

    private StringFilter ordRequestRrvendorName;

    private StringFilter ordRequestRrvendorLicenseNbr;

    private StringFilter ordRequestRrvendorLicenseStateCd;

    private StringFilter ordValuationCompletedProduct;

    private LocalDateFilter ordValuationDueToClientDt;

    private LocalDateFilter ordValuationReportRecivedDt;

    private LocalDateFilter ordValuationCompletionDt;

    private StringFilter ordValuationReportStatusCd;

    private BigDecimalFilter ordValuationAppraisedValueAmt;

    private BigDecimalFilter ordValuationBpoMarketValueAmt;

    private BigDecimalFilter ordValuationBpoAsIsAmt;

    private BigDecimalFilter ordValuationBpoAsRepairedValueAmt;

    private StringFilter ordValuationRedFlagCode;

    private StringFilter ordValuationRedFlagDesc;

    private StringFilter ordValuationAmcAppraisalId;

    private LongFilter ordValuationFinalReviewId;

    private BigDecimalFilter ordReviewRecommendedValueAmt;

    private LocalDateFilter ordReviewDt;

    private StringFilter ordReviewRecommendedActionCd;

    private StringFilter ordReviewReviewerDecisionCd;

    private StringFilter ordReviewReviewScore;

    private StringFilter ordReviewFormUsed;

    private BooleanFilter ordReviewProviderOnWatchListInd;

    private BigDecimalFilter ordReviewCuredValueAmt;

    private StringFilter ordReviewFinalRecommendedActionCd;

    private LongFilter commentsId;

    private LongFilter documentsId;

    public OrdersCriteria() {
    }

    public OrdersCriteria(OrdersCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ordNumber = other.ordNumber == null ? null : other.ordNumber.copy();
        this.ordStageCd = other.ordStageCd == null ? null : other.ordStageCd.copy();
        this.ordOrgOrderStatusCd = other.ordOrgOrderStatusCd == null ? null : other.ordOrgOrderStatusCd.copy();
        this.ordCmpOrderStatusCd = other.ordCmpOrderStatusCd == null ? null : other.ordCmpOrderStatusCd.copy();
        this.ordProOrderStatusCd = other.ordProOrderStatusCd == null ? null : other.ordProOrderStatusCd.copy();
        this.ordCmpNbr = other.ordCmpNbr == null ? null : other.ordCmpNbr.copy();
        this.ordBrnNbr = other.ordBrnNbr == null ? null : other.ordBrnNbr.copy();
        this.ordProNbr = other.ordProNbr == null ? null : other.ordProNbr.copy();
        this.ordProductCode = other.ordProductCode == null ? null : other.ordProductCode.copy();
        this.ordOriginalValueAmt = other.ordOriginalValueAmt == null ? null : other.ordOriginalValueAmt.copy();
        this.ordParentTrackingNbr = other.ordParentTrackingNbr == null ? null : other.ordParentTrackingNbr.copy();
        this.ordUserNbr = other.ordUserNbr == null ? null : other.ordUserNbr.copy();
        this.ordOrderDt = other.ordOrderDt == null ? null : other.ordOrderDt.copy();
        this.ordRushRequestDueDt = other.ordRushRequestDueDt == null ? null : other.ordRushRequestDueDt.copy();
        this.ordOrgInstructions = other.ordOrgInstructions == null ? null : other.ordOrgInstructions.copy();
        this.ordOrgOtherInstructions = other.ordOrgOtherInstructions == null ? null : other.ordOrgOtherInstructions.copy();
        this.ordMultiOrderInd = other.ordMultiOrderInd == null ? null : other.ordMultiOrderInd.copy();
        this.ordOrgDuedate = other.ordOrgDuedate == null ? null : other.ordOrgDuedate.copy();
        this.ordProDuedate = other.ordProDuedate == null ? null : other.ordProDuedate.copy();
        this.ordUcdpDataFnmaSubmitToUcdpInd = other.ordUcdpDataFnmaSubmitToUcdpInd == null ? null : other.ordUcdpDataFnmaSubmitToUcdpInd.copy();
        this.ordUcdpDataFnmaServicerNbr = other.ordUcdpDataFnmaServicerNbr == null ? null : other.ordUcdpDataFnmaServicerNbr.copy();
        this.ordUcdpDataFhlmcSubmitToUcdpInd = other.ordUcdpDataFhlmcSubmitToUcdpInd == null ? null : other.ordUcdpDataFhlmcSubmitToUcdpInd.copy();
        this.ordUcdpDataFhlmcIdentificationNbr = other.ordUcdpDataFhlmcIdentificationNbr == null ? null : other.ordUcdpDataFhlmcIdentificationNbr.copy();
        this.ordUcdpDataUcdpBussinessUnitNbr = other.ordUcdpDataUcdpBussinessUnitNbr == null ? null : other.ordUcdpDataUcdpBussinessUnitNbr.copy();
        this.ordUcdpSdFnamaDocumentId = other.ordUcdpSdFnamaDocumentId == null ? null : other.ordUcdpSdFnamaDocumentId.copy();
        this.ordUcdpSdFnmaSubmissionStatusId = other.ordUcdpSdFnmaSubmissionStatusId == null ? null : other.ordUcdpSdFnmaSubmissionStatusId.copy();
        this.ordUcdpSdFhlmcDocumentFileCd = other.ordUcdpSdFhlmcDocumentFileCd == null ? null : other.ordUcdpSdFhlmcDocumentFileCd.copy();
        this.ordUcdpSdEadSubmissionStatusCd = other.ordUcdpSdEadSubmissionStatusCd == null ? null : other.ordUcdpSdEadSubmissionStatusCd.copy();
        this.ordUcdpFinalSubmissionDt = other.ordUcdpFinalSubmissionDt == null ? null : other.ordUcdpFinalSubmissionDt.copy();
        this.ordLoanNbr = other.ordLoanNbr == null ? null : other.ordLoanNbr.copy();
        this.ordloanOldLoanNbr = other.ordloanOldLoanNbr == null ? null : other.ordloanOldLoanNbr.copy();
        this.ordLoanProgram = other.ordLoanProgram == null ? null : other.ordLoanProgram.copy();
        this.ordLoanPurpose = other.ordLoanPurpose == null ? null : other.ordLoanPurpose.copy();
        this.ordLoanType = other.ordLoanType == null ? null : other.ordLoanType.copy();
        this.ordLoanAmt = other.ordLoanAmt == null ? null : other.ordLoanAmt.copy();
        this.ordLoanQualifyingValueAmt = other.ordLoanQualifyingValueAmt == null ? null : other.ordLoanQualifyingValueAmt.copy();
        this.ordLoanSalesPriceAmt = other.ordLoanSalesPriceAmt == null ? null : other.ordLoanSalesPriceAmt.copy();
        this.ordLoanBorrowerLastName = other.ordLoanBorrowerLastName == null ? null : other.ordLoanBorrowerLastName.copy();
        this.ordLoanBorrowerFirstName = other.ordLoanBorrowerFirstName == null ? null : other.ordLoanBorrowerFirstName.copy();
        this.ordLoanBorrowerMiddleName = other.ordLoanBorrowerMiddleName == null ? null : other.ordLoanBorrowerMiddleName.copy();
        this.ordLoanBorrowerIsCoBorrowerInd = other.ordLoanBorrowerIsCoBorrowerInd == null ? null : other.ordLoanBorrowerIsCoBorrowerInd.copy();
        this.ordLoanFhaCaseNbr = other.ordLoanFhaCaseNbr == null ? null : other.ordLoanFhaCaseNbr.copy();
        this.ordLoanDeedRestrictionInd = other.ordLoanDeedRestrictionInd == null ? null : other.ordLoanDeedRestrictionInd.copy();
        this.ordLoanEstimatedCloseDt = other.ordLoanEstimatedCloseDt == null ? null : other.ordLoanEstimatedCloseDt.copy();
        this.ordLoanHpmlInd = other.ordLoanHpmlInd == null ? null : other.ordLoanHpmlInd.copy();
        this.ordLoanIsNewConstructionInd = other.ordLoanIsNewConstructionInd == null ? null : other.ordLoanIsNewConstructionInd.copy();
        this.ordLoanCustomerSegmentCode = other.ordLoanCustomerSegmentCode == null ? null : other.ordLoanCustomerSegmentCode.copy();
        this.ordLoanNonSubjectPropertyInd = other.ordLoanNonSubjectPropertyInd == null ? null : other.ordLoanNonSubjectPropertyInd.copy();
        this.ordLoanBorrowerRequestedCloseDt = other.ordLoanBorrowerRequestedCloseDt == null ? null : other.ordLoanBorrowerRequestedCloseDt.copy();
        this.ordPropertyTypeCd = other.ordPropertyTypeCd == null ? null : other.ordPropertyTypeCd.copy();
        this.ordPropertyAddress1 = other.ordPropertyAddress1 == null ? null : other.ordPropertyAddress1.copy();
        this.ordPropertyAddress2 = other.ordPropertyAddress2 == null ? null : other.ordPropertyAddress2.copy();
        this.ordPropertyCity = other.ordPropertyCity == null ? null : other.ordPropertyCity.copy();
        this.ordPropertyStateCd = other.ordPropertyStateCd == null ? null : other.ordPropertyStateCd.copy();
        this.ordPropertyZip = other.ordPropertyZip == null ? null : other.ordPropertyZip.copy();
        this.ordPropertyCounty = other.ordPropertyCounty == null ? null : other.ordPropertyCounty.copy();
        this.ordPropertyLatitue = other.ordPropertyLatitue == null ? null : other.ordPropertyLatitue.copy();
        this.ordPropertyLongitude = other.ordPropertyLongitude == null ? null : other.ordPropertyLongitude.copy();
        this.ordPropertyProjectName = other.ordPropertyProjectName == null ? null : other.ordPropertyProjectName.copy();
        this.ordContact1TypeCd = other.ordContact1TypeCd == null ? null : other.ordContact1TypeCd.copy();
        this.ordContact1Name = other.ordContact1Name == null ? null : other.ordContact1Name.copy();
        this.ordContact1WorkPhone = other.ordContact1WorkPhone == null ? null : other.ordContact1WorkPhone.copy();
        this.ordContact1HomePhone = other.ordContact1HomePhone == null ? null : other.ordContact1HomePhone.copy();
        this.ordContact1CellPhone = other.ordContact1CellPhone == null ? null : other.ordContact1CellPhone.copy();
        this.ordContact1OtherCellPhone = other.ordContact1OtherCellPhone == null ? null : other.ordContact1OtherCellPhone.copy();
        this.ordContact1Email = other.ordContact1Email == null ? null : other.ordContact1Email.copy();
        this.ordContact1OtherEmail = other.ordContact1OtherEmail == null ? null : other.ordContact1OtherEmail.copy();
        this.ordContact2TypeCd = other.ordContact2TypeCd == null ? null : other.ordContact2TypeCd.copy();
        this.ordContact2Name = other.ordContact2Name == null ? null : other.ordContact2Name.copy();
        this.ordContact2WorkPhone = other.ordContact2WorkPhone == null ? null : other.ordContact2WorkPhone.copy();
        this.ordContact2HomePhone = other.ordContact2HomePhone == null ? null : other.ordContact2HomePhone.copy();
        this.ordContact2CellPhone = other.ordContact2CellPhone == null ? null : other.ordContact2CellPhone.copy();
        this.ordContact2OtherCellPhone = other.ordContact2OtherCellPhone == null ? null : other.ordContact2OtherCellPhone.copy();
        this.ordContact2Email = other.ordContact2Email == null ? null : other.ordContact2Email.copy();
        this.ordContact2OtherEmail = other.ordContact2OtherEmail == null ? null : other.ordContact2OtherEmail.copy();
        this.ordContact3TypeCd = other.ordContact3TypeCd == null ? null : other.ordContact3TypeCd.copy();
        this.ordContact3Name = other.ordContact3Name == null ? null : other.ordContact3Name.copy();
        this.ordContact3WorkPhone = other.ordContact3WorkPhone == null ? null : other.ordContact3WorkPhone.copy();
        this.ordContact3HomePhone = other.ordContact3HomePhone == null ? null : other.ordContact3HomePhone.copy();
        this.ordContact3CellPhone = other.ordContact3CellPhone == null ? null : other.ordContact3CellPhone.copy();
        this.ordContact3OtherCellPhone = other.ordContact3OtherCellPhone == null ? null : other.ordContact3OtherCellPhone.copy();
        this.ordContact3Email = other.ordContact3Email == null ? null : other.ordContact3Email.copy();
        this.ordContact3OtherEmail = other.ordContact3OtherEmail == null ? null : other.ordContact3OtherEmail.copy();
        this.ordRequestRrStatusCd = other.ordRequestRrStatusCd == null ? null : other.ordRequestRrStatusCd.copy();
        this.ordRequestRrVendorAppraisalId = other.ordRequestRrVendorAppraisalId == null ? null : other.ordRequestRrVendorAppraisalId.copy();
        this.ordRequestRrvendorAppraisalDraftRcvdDt = other.ordRequestRrvendorAppraisalDraftRcvdDt == null ? null : other.ordRequestRrvendorAppraisalDraftRcvdDt.copy();
        this.ordRequestRrXmlFormId = other.ordRequestRrXmlFormId == null ? null : other.ordRequestRrXmlFormId.copy();
        this.ordRequestRrPdfFormId = other.ordRequestRrPdfFormId == null ? null : other.ordRequestRrPdfFormId.copy();
        this.ordRequestRrApprFileDataSourcCd = other.ordRequestRrApprFileDataSourcCd == null ? null : other.ordRequestRrApprFileDataSourcCd.copy();
        this.ordRequestRrReportValueAmt = other.ordRequestRrReportValueAmt == null ? null : other.ordRequestRrReportValueAmt.copy();
        this.ordRequestRrAppraisalCompanyName = other.ordRequestRrAppraisalCompanyName == null ? null : other.ordRequestRrAppraisalCompanyName.copy();
        this.ordRequestRrvendorName = other.ordRequestRrvendorName == null ? null : other.ordRequestRrvendorName.copy();
        this.ordRequestRrvendorLicenseNbr = other.ordRequestRrvendorLicenseNbr == null ? null : other.ordRequestRrvendorLicenseNbr.copy();
        this.ordRequestRrvendorLicenseStateCd = other.ordRequestRrvendorLicenseStateCd == null ? null : other.ordRequestRrvendorLicenseStateCd.copy();
        this.ordValuationCompletedProduct = other.ordValuationCompletedProduct == null ? null : other.ordValuationCompletedProduct.copy();
        this.ordValuationDueToClientDt = other.ordValuationDueToClientDt == null ? null : other.ordValuationDueToClientDt.copy();
        this.ordValuationReportRecivedDt = other.ordValuationReportRecivedDt == null ? null : other.ordValuationReportRecivedDt.copy();
        this.ordValuationCompletionDt = other.ordValuationCompletionDt == null ? null : other.ordValuationCompletionDt.copy();
        this.ordValuationReportStatusCd = other.ordValuationReportStatusCd == null ? null : other.ordValuationReportStatusCd.copy();
        this.ordValuationAppraisedValueAmt = other.ordValuationAppraisedValueAmt == null ? null : other.ordValuationAppraisedValueAmt.copy();
        this.ordValuationBpoMarketValueAmt = other.ordValuationBpoMarketValueAmt == null ? null : other.ordValuationBpoMarketValueAmt.copy();
        this.ordValuationBpoAsIsAmt = other.ordValuationBpoAsIsAmt == null ? null : other.ordValuationBpoAsIsAmt.copy();
        this.ordValuationBpoAsRepairedValueAmt = other.ordValuationBpoAsRepairedValueAmt == null ? null : other.ordValuationBpoAsRepairedValueAmt.copy();
        this.ordValuationRedFlagCode = other.ordValuationRedFlagCode == null ? null : other.ordValuationRedFlagCode.copy();
        this.ordValuationRedFlagDesc = other.ordValuationRedFlagDesc == null ? null : other.ordValuationRedFlagDesc.copy();
        this.ordValuationAmcAppraisalId = other.ordValuationAmcAppraisalId == null ? null : other.ordValuationAmcAppraisalId.copy();
        this.ordValuationFinalReviewId = other.ordValuationFinalReviewId == null ? null : other.ordValuationFinalReviewId.copy();
        this.ordReviewRecommendedValueAmt = other.ordReviewRecommendedValueAmt == null ? null : other.ordReviewRecommendedValueAmt.copy();
        this.ordReviewDt = other.ordReviewDt == null ? null : other.ordReviewDt.copy();
        this.ordReviewRecommendedActionCd = other.ordReviewRecommendedActionCd == null ? null : other.ordReviewRecommendedActionCd.copy();
        this.ordReviewReviewerDecisionCd = other.ordReviewReviewerDecisionCd == null ? null : other.ordReviewReviewerDecisionCd.copy();
        this.ordReviewReviewScore = other.ordReviewReviewScore == null ? null : other.ordReviewReviewScore.copy();
        this.ordReviewFormUsed = other.ordReviewFormUsed == null ? null : other.ordReviewFormUsed.copy();
        this.ordReviewProviderOnWatchListInd = other.ordReviewProviderOnWatchListInd == null ? null : other.ordReviewProviderOnWatchListInd.copy();
        this.ordReviewCuredValueAmt = other.ordReviewCuredValueAmt == null ? null : other.ordReviewCuredValueAmt.copy();
        this.ordReviewFinalRecommendedActionCd = other.ordReviewFinalRecommendedActionCd == null ? null : other.ordReviewFinalRecommendedActionCd.copy();
        this.commentsId = other.commentsId == null ? null : other.commentsId.copy();
        this.documentsId = other.documentsId == null ? null : other.documentsId.copy();
    }

    @Override
    public OrdersCriteria copy() {
        return new OrdersCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOrdNumber() {
        return ordNumber;
    }

    public void setOrdNumber(StringFilter ordNumber) {
        this.ordNumber = ordNumber;
    }

    public StringFilter getOrdStageCd() {
        return ordStageCd;
    }

    public void setOrdStageCd(StringFilter ordStageCd) {
        this.ordStageCd = ordStageCd;
    }

    public StringFilter getOrdOrgOrderStatusCd() {
        return ordOrgOrderStatusCd;
    }

    public void setOrdOrgOrderStatusCd(StringFilter ordOrgOrderStatusCd) {
        this.ordOrgOrderStatusCd = ordOrgOrderStatusCd;
    }

    public StringFilter getOrdCmpOrderStatusCd() {
        return ordCmpOrderStatusCd;
    }

    public void setOrdCmpOrderStatusCd(StringFilter ordCmpOrderStatusCd) {
        this.ordCmpOrderStatusCd = ordCmpOrderStatusCd;
    }

    public StringFilter getOrdProOrderStatusCd() {
        return ordProOrderStatusCd;
    }

    public void setOrdProOrderStatusCd(StringFilter ordProOrderStatusCd) {
        this.ordProOrderStatusCd = ordProOrderStatusCd;
    }

    public StringFilter getOrdCmpNbr() {
        return ordCmpNbr;
    }

    public void setOrdCmpNbr(StringFilter ordCmpNbr) {
        this.ordCmpNbr = ordCmpNbr;
    }

    public StringFilter getOrdBrnNbr() {
        return ordBrnNbr;
    }

    public void setOrdBrnNbr(StringFilter ordBrnNbr) {
        this.ordBrnNbr = ordBrnNbr;
    }

    public StringFilter getOrdProNbr() {
        return ordProNbr;
    }

    public void setOrdProNbr(StringFilter ordProNbr) {
        this.ordProNbr = ordProNbr;
    }

    public StringFilter getOrdProductCode() {
        return ordProductCode;
    }

    public void setOrdProductCode(StringFilter ordProductCode) {
        this.ordProductCode = ordProductCode;
    }

    public BigDecimalFilter getOrdOriginalValueAmt() {
        return ordOriginalValueAmt;
    }

    public void setOrdOriginalValueAmt(BigDecimalFilter ordOriginalValueAmt) {
        this.ordOriginalValueAmt = ordOriginalValueAmt;
    }

    public StringFilter getOrdParentTrackingNbr() {
        return ordParentTrackingNbr;
    }

    public void setOrdParentTrackingNbr(StringFilter ordParentTrackingNbr) {
        this.ordParentTrackingNbr = ordParentTrackingNbr;
    }

    public StringFilter getOrdUserNbr() {
        return ordUserNbr;
    }

    public void setOrdUserNbr(StringFilter ordUserNbr) {
        this.ordUserNbr = ordUserNbr;
    }

    public LocalDateFilter getOrdOrderDt() {
        return ordOrderDt;
    }

    public void setOrdOrderDt(LocalDateFilter ordOrderDt) {
        this.ordOrderDt = ordOrderDt;
    }

    public LocalDateFilter getOrdRushRequestDueDt() {
        return ordRushRequestDueDt;
    }

    public void setOrdRushRequestDueDt(LocalDateFilter ordRushRequestDueDt) {
        this.ordRushRequestDueDt = ordRushRequestDueDt;
    }

    public StringFilter getOrdOrgInstructions() {
        return ordOrgInstructions;
    }

    public void setOrdOrgInstructions(StringFilter ordOrgInstructions) {
        this.ordOrgInstructions = ordOrgInstructions;
    }

    public StringFilter getOrdOrgOtherInstructions() {
        return ordOrgOtherInstructions;
    }

    public void setOrdOrgOtherInstructions(StringFilter ordOrgOtherInstructions) {
        this.ordOrgOtherInstructions = ordOrgOtherInstructions;
    }

    public BooleanFilter getOrdMultiOrderInd() {
        return ordMultiOrderInd;
    }

    public void setOrdMultiOrderInd(BooleanFilter ordMultiOrderInd) {
        this.ordMultiOrderInd = ordMultiOrderInd;
    }

    public LocalDateFilter getOrdOrgDuedate() {
        return ordOrgDuedate;
    }

    public void setOrdOrgDuedate(LocalDateFilter ordOrgDuedate) {
        this.ordOrgDuedate = ordOrgDuedate;
    }

    public LocalDateFilter getOrdProDuedate() {
        return ordProDuedate;
    }

    public void setOrdProDuedate(LocalDateFilter ordProDuedate) {
        this.ordProDuedate = ordProDuedate;
    }

    public BooleanFilter getOrdUcdpDataFnmaSubmitToUcdpInd() {
        return ordUcdpDataFnmaSubmitToUcdpInd;
    }

    public void setOrdUcdpDataFnmaSubmitToUcdpInd(BooleanFilter ordUcdpDataFnmaSubmitToUcdpInd) {
        this.ordUcdpDataFnmaSubmitToUcdpInd = ordUcdpDataFnmaSubmitToUcdpInd;
    }

    public StringFilter getOrdUcdpDataFnmaServicerNbr() {
        return ordUcdpDataFnmaServicerNbr;
    }

    public void setOrdUcdpDataFnmaServicerNbr(StringFilter ordUcdpDataFnmaServicerNbr) {
        this.ordUcdpDataFnmaServicerNbr = ordUcdpDataFnmaServicerNbr;
    }

    public BooleanFilter getOrdUcdpDataFhlmcSubmitToUcdpInd() {
        return ordUcdpDataFhlmcSubmitToUcdpInd;
    }

    public void setOrdUcdpDataFhlmcSubmitToUcdpInd(BooleanFilter ordUcdpDataFhlmcSubmitToUcdpInd) {
        this.ordUcdpDataFhlmcSubmitToUcdpInd = ordUcdpDataFhlmcSubmitToUcdpInd;
    }

    public StringFilter getOrdUcdpDataFhlmcIdentificationNbr() {
        return ordUcdpDataFhlmcIdentificationNbr;
    }

    public void setOrdUcdpDataFhlmcIdentificationNbr(StringFilter ordUcdpDataFhlmcIdentificationNbr) {
        this.ordUcdpDataFhlmcIdentificationNbr = ordUcdpDataFhlmcIdentificationNbr;
    }

    public StringFilter getOrdUcdpDataUcdpBussinessUnitNbr() {
        return ordUcdpDataUcdpBussinessUnitNbr;
    }

    public void setOrdUcdpDataUcdpBussinessUnitNbr(StringFilter ordUcdpDataUcdpBussinessUnitNbr) {
        this.ordUcdpDataUcdpBussinessUnitNbr = ordUcdpDataUcdpBussinessUnitNbr;
    }

    public StringFilter getOrdUcdpSdFnamaDocumentId() {
        return ordUcdpSdFnamaDocumentId;
    }

    public void setOrdUcdpSdFnamaDocumentId(StringFilter ordUcdpSdFnamaDocumentId) {
        this.ordUcdpSdFnamaDocumentId = ordUcdpSdFnamaDocumentId;
    }

    public StringFilter getOrdUcdpSdFnmaSubmissionStatusId() {
        return ordUcdpSdFnmaSubmissionStatusId;
    }

    public void setOrdUcdpSdFnmaSubmissionStatusId(StringFilter ordUcdpSdFnmaSubmissionStatusId) {
        this.ordUcdpSdFnmaSubmissionStatusId = ordUcdpSdFnmaSubmissionStatusId;
    }

    public StringFilter getOrdUcdpSdFhlmcDocumentFileCd() {
        return ordUcdpSdFhlmcDocumentFileCd;
    }

    public void setOrdUcdpSdFhlmcDocumentFileCd(StringFilter ordUcdpSdFhlmcDocumentFileCd) {
        this.ordUcdpSdFhlmcDocumentFileCd = ordUcdpSdFhlmcDocumentFileCd;
    }

    public StringFilter getOrdUcdpSdEadSubmissionStatusCd() {
        return ordUcdpSdEadSubmissionStatusCd;
    }

    public void setOrdUcdpSdEadSubmissionStatusCd(StringFilter ordUcdpSdEadSubmissionStatusCd) {
        this.ordUcdpSdEadSubmissionStatusCd = ordUcdpSdEadSubmissionStatusCd;
    }

    public LocalDateFilter getOrdUcdpFinalSubmissionDt() {
        return ordUcdpFinalSubmissionDt;
    }

    public void setOrdUcdpFinalSubmissionDt(LocalDateFilter ordUcdpFinalSubmissionDt) {
        this.ordUcdpFinalSubmissionDt = ordUcdpFinalSubmissionDt;
    }

    public StringFilter getOrdLoanNbr() {
        return ordLoanNbr;
    }

    public void setOrdLoanNbr(StringFilter ordLoanNbr) {
        this.ordLoanNbr = ordLoanNbr;
    }

    public StringFilter getOrdloanOldLoanNbr() {
        return ordloanOldLoanNbr;
    }

    public void setOrdloanOldLoanNbr(StringFilter ordloanOldLoanNbr) {
        this.ordloanOldLoanNbr = ordloanOldLoanNbr;
    }

    public StringFilter getOrdLoanProgram() {
        return ordLoanProgram;
    }

    public void setOrdLoanProgram(StringFilter ordLoanProgram) {
        this.ordLoanProgram = ordLoanProgram;
    }

    public StringFilter getOrdLoanPurpose() {
        return ordLoanPurpose;
    }

    public void setOrdLoanPurpose(StringFilter ordLoanPurpose) {
        this.ordLoanPurpose = ordLoanPurpose;
    }

    public StringFilter getOrdLoanType() {
        return ordLoanType;
    }

    public void setOrdLoanType(StringFilter ordLoanType) {
        this.ordLoanType = ordLoanType;
    }

    public BigDecimalFilter getOrdLoanAmt() {
        return ordLoanAmt;
    }

    public void setOrdLoanAmt(BigDecimalFilter ordLoanAmt) {
        this.ordLoanAmt = ordLoanAmt;
    }

    public BigDecimalFilter getOrdLoanQualifyingValueAmt() {
        return ordLoanQualifyingValueAmt;
    }

    public void setOrdLoanQualifyingValueAmt(BigDecimalFilter ordLoanQualifyingValueAmt) {
        this.ordLoanQualifyingValueAmt = ordLoanQualifyingValueAmt;
    }

    public BigDecimalFilter getOrdLoanSalesPriceAmt() {
        return ordLoanSalesPriceAmt;
    }

    public void setOrdLoanSalesPriceAmt(BigDecimalFilter ordLoanSalesPriceAmt) {
        this.ordLoanSalesPriceAmt = ordLoanSalesPriceAmt;
    }

    public StringFilter getOrdLoanBorrowerLastName() {
        return ordLoanBorrowerLastName;
    }

    public void setOrdLoanBorrowerLastName(StringFilter ordLoanBorrowerLastName) {
        this.ordLoanBorrowerLastName = ordLoanBorrowerLastName;
    }

    public StringFilter getOrdLoanBorrowerFirstName() {
        return ordLoanBorrowerFirstName;
    }

    public void setOrdLoanBorrowerFirstName(StringFilter ordLoanBorrowerFirstName) {
        this.ordLoanBorrowerFirstName = ordLoanBorrowerFirstName;
    }

    public StringFilter getOrdLoanBorrowerMiddleName() {
        return ordLoanBorrowerMiddleName;
    }

    public void setOrdLoanBorrowerMiddleName(StringFilter ordLoanBorrowerMiddleName) {
        this.ordLoanBorrowerMiddleName = ordLoanBorrowerMiddleName;
    }

    public BooleanFilter getOrdLoanBorrowerIsCoBorrowerInd() {
        return ordLoanBorrowerIsCoBorrowerInd;
    }

    public void setOrdLoanBorrowerIsCoBorrowerInd(BooleanFilter ordLoanBorrowerIsCoBorrowerInd) {
        this.ordLoanBorrowerIsCoBorrowerInd = ordLoanBorrowerIsCoBorrowerInd;
    }

    public StringFilter getOrdLoanFhaCaseNbr() {
        return ordLoanFhaCaseNbr;
    }

    public void setOrdLoanFhaCaseNbr(StringFilter ordLoanFhaCaseNbr) {
        this.ordLoanFhaCaseNbr = ordLoanFhaCaseNbr;
    }

    public BooleanFilter getOrdLoanDeedRestrictionInd() {
        return ordLoanDeedRestrictionInd;
    }

    public void setOrdLoanDeedRestrictionInd(BooleanFilter ordLoanDeedRestrictionInd) {
        this.ordLoanDeedRestrictionInd = ordLoanDeedRestrictionInd;
    }

    public LocalDateFilter getOrdLoanEstimatedCloseDt() {
        return ordLoanEstimatedCloseDt;
    }

    public void setOrdLoanEstimatedCloseDt(LocalDateFilter ordLoanEstimatedCloseDt) {
        this.ordLoanEstimatedCloseDt = ordLoanEstimatedCloseDt;
    }

    public BooleanFilter getOrdLoanHpmlInd() {
        return ordLoanHpmlInd;
    }

    public void setOrdLoanHpmlInd(BooleanFilter ordLoanHpmlInd) {
        this.ordLoanHpmlInd = ordLoanHpmlInd;
    }

    public BooleanFilter getOrdLoanIsNewConstructionInd() {
        return ordLoanIsNewConstructionInd;
    }

    public void setOrdLoanIsNewConstructionInd(BooleanFilter ordLoanIsNewConstructionInd) {
        this.ordLoanIsNewConstructionInd = ordLoanIsNewConstructionInd;
    }

    public StringFilter getOrdLoanCustomerSegmentCode() {
        return ordLoanCustomerSegmentCode;
    }

    public void setOrdLoanCustomerSegmentCode(StringFilter ordLoanCustomerSegmentCode) {
        this.ordLoanCustomerSegmentCode = ordLoanCustomerSegmentCode;
    }

    public BooleanFilter getOrdLoanNonSubjectPropertyInd() {
        return ordLoanNonSubjectPropertyInd;
    }

    public void setOrdLoanNonSubjectPropertyInd(BooleanFilter ordLoanNonSubjectPropertyInd) {
        this.ordLoanNonSubjectPropertyInd = ordLoanNonSubjectPropertyInd;
    }

    public LocalDateFilter getOrdLoanBorrowerRequestedCloseDt() {
        return ordLoanBorrowerRequestedCloseDt;
    }

    public void setOrdLoanBorrowerRequestedCloseDt(LocalDateFilter ordLoanBorrowerRequestedCloseDt) {
        this.ordLoanBorrowerRequestedCloseDt = ordLoanBorrowerRequestedCloseDt;
    }

    public StringFilter getOrdPropertyTypeCd() {
        return ordPropertyTypeCd;
    }

    public void setOrdPropertyTypeCd(StringFilter ordPropertyTypeCd) {
        this.ordPropertyTypeCd = ordPropertyTypeCd;
    }

    public StringFilter getOrdPropertyAddress1() {
        return ordPropertyAddress1;
    }

    public void setOrdPropertyAddress1(StringFilter ordPropertyAddress1) {
        this.ordPropertyAddress1 = ordPropertyAddress1;
    }

    public StringFilter getOrdPropertyAddress2() {
        return ordPropertyAddress2;
    }

    public void setOrdPropertyAddress2(StringFilter ordPropertyAddress2) {
        this.ordPropertyAddress2 = ordPropertyAddress2;
    }

    public StringFilter getOrdPropertyCity() {
        return ordPropertyCity;
    }

    public void setOrdPropertyCity(StringFilter ordPropertyCity) {
        this.ordPropertyCity = ordPropertyCity;
    }

    public StringFilter getOrdPropertyStateCd() {
        return ordPropertyStateCd;
    }

    public void setOrdPropertyStateCd(StringFilter ordPropertyStateCd) {
        this.ordPropertyStateCd = ordPropertyStateCd;
    }

    public StringFilter getOrdPropertyZip() {
        return ordPropertyZip;
    }

    public void setOrdPropertyZip(StringFilter ordPropertyZip) {
        this.ordPropertyZip = ordPropertyZip;
    }

    public StringFilter getOrdPropertyCounty() {
        return ordPropertyCounty;
    }

    public void setOrdPropertyCounty(StringFilter ordPropertyCounty) {
        this.ordPropertyCounty = ordPropertyCounty;
    }

    public StringFilter getOrdPropertyLatitue() {
        return ordPropertyLatitue;
    }

    public void setOrdPropertyLatitue(StringFilter ordPropertyLatitue) {
        this.ordPropertyLatitue = ordPropertyLatitue;
    }

    public StringFilter getOrdPropertyLongitude() {
        return ordPropertyLongitude;
    }

    public void setOrdPropertyLongitude(StringFilter ordPropertyLongitude) {
        this.ordPropertyLongitude = ordPropertyLongitude;
    }

    public StringFilter getOrdPropertyProjectName() {
        return ordPropertyProjectName;
    }

    public void setOrdPropertyProjectName(StringFilter ordPropertyProjectName) {
        this.ordPropertyProjectName = ordPropertyProjectName;
    }

    public StringFilter getOrdContact1TypeCd() {
        return ordContact1TypeCd;
    }

    public void setOrdContact1TypeCd(StringFilter ordContact1TypeCd) {
        this.ordContact1TypeCd = ordContact1TypeCd;
    }

    public StringFilter getOrdContact1Name() {
        return ordContact1Name;
    }

    public void setOrdContact1Name(StringFilter ordContact1Name) {
        this.ordContact1Name = ordContact1Name;
    }

    public LongFilter getOrdContact1WorkPhone() {
        return ordContact1WorkPhone;
    }

    public void setOrdContact1WorkPhone(LongFilter ordContact1WorkPhone) {
        this.ordContact1WorkPhone = ordContact1WorkPhone;
    }

    public LongFilter getOrdContact1HomePhone() {
        return ordContact1HomePhone;
    }

    public void setOrdContact1HomePhone(LongFilter ordContact1HomePhone) {
        this.ordContact1HomePhone = ordContact1HomePhone;
    }

    public LongFilter getOrdContact1CellPhone() {
        return ordContact1CellPhone;
    }

    public void setOrdContact1CellPhone(LongFilter ordContact1CellPhone) {
        this.ordContact1CellPhone = ordContact1CellPhone;
    }

    public LongFilter getOrdContact1OtherCellPhone() {
        return ordContact1OtherCellPhone;
    }

    public void setOrdContact1OtherCellPhone(LongFilter ordContact1OtherCellPhone) {
        this.ordContact1OtherCellPhone = ordContact1OtherCellPhone;
    }

    public StringFilter getOrdContact1Email() {
        return ordContact1Email;
    }

    public void setOrdContact1Email(StringFilter ordContact1Email) {
        this.ordContact1Email = ordContact1Email;
    }

    public StringFilter getOrdContact1OtherEmail() {
        return ordContact1OtherEmail;
    }

    public void setOrdContact1OtherEmail(StringFilter ordContact1OtherEmail) {
        this.ordContact1OtherEmail = ordContact1OtherEmail;
    }

    public StringFilter getOrdContact2TypeCd() {
        return ordContact2TypeCd;
    }

    public void setOrdContact2TypeCd(StringFilter ordContact2TypeCd) {
        this.ordContact2TypeCd = ordContact2TypeCd;
    }

    public StringFilter getOrdContact2Name() {
        return ordContact2Name;
    }

    public void setOrdContact2Name(StringFilter ordContact2Name) {
        this.ordContact2Name = ordContact2Name;
    }

    public LongFilter getOrdContact2WorkPhone() {
        return ordContact2WorkPhone;
    }

    public void setOrdContact2WorkPhone(LongFilter ordContact2WorkPhone) {
        this.ordContact2WorkPhone = ordContact2WorkPhone;
    }

    public LongFilter getOrdContact2HomePhone() {
        return ordContact2HomePhone;
    }

    public void setOrdContact2HomePhone(LongFilter ordContact2HomePhone) {
        this.ordContact2HomePhone = ordContact2HomePhone;
    }

    public LongFilter getOrdContact2CellPhone() {
        return ordContact2CellPhone;
    }

    public void setOrdContact2CellPhone(LongFilter ordContact2CellPhone) {
        this.ordContact2CellPhone = ordContact2CellPhone;
    }

    public LongFilter getOrdContact2OtherCellPhone() {
        return ordContact2OtherCellPhone;
    }

    public void setOrdContact2OtherCellPhone(LongFilter ordContact2OtherCellPhone) {
        this.ordContact2OtherCellPhone = ordContact2OtherCellPhone;
    }

    public StringFilter getOrdContact2Email() {
        return ordContact2Email;
    }

    public void setOrdContact2Email(StringFilter ordContact2Email) {
        this.ordContact2Email = ordContact2Email;
    }

    public StringFilter getOrdContact2OtherEmail() {
        return ordContact2OtherEmail;
    }

    public void setOrdContact2OtherEmail(StringFilter ordContact2OtherEmail) {
        this.ordContact2OtherEmail = ordContact2OtherEmail;
    }

    public StringFilter getOrdContact3TypeCd() {
        return ordContact3TypeCd;
    }

    public void setOrdContact3TypeCd(StringFilter ordContact3TypeCd) {
        this.ordContact3TypeCd = ordContact3TypeCd;
    }

    public StringFilter getOrdContact3Name() {
        return ordContact3Name;
    }

    public void setOrdContact3Name(StringFilter ordContact3Name) {
        this.ordContact3Name = ordContact3Name;
    }

    public LongFilter getOrdContact3WorkPhone() {
        return ordContact3WorkPhone;
    }

    public void setOrdContact3WorkPhone(LongFilter ordContact3WorkPhone) {
        this.ordContact3WorkPhone = ordContact3WorkPhone;
    }

    public LongFilter getOrdContact3HomePhone() {
        return ordContact3HomePhone;
    }

    public void setOrdContact3HomePhone(LongFilter ordContact3HomePhone) {
        this.ordContact3HomePhone = ordContact3HomePhone;
    }

    public LongFilter getOrdContact3CellPhone() {
        return ordContact3CellPhone;
    }

    public void setOrdContact3CellPhone(LongFilter ordContact3CellPhone) {
        this.ordContact3CellPhone = ordContact3CellPhone;
    }

    public LongFilter getOrdContact3OtherCellPhone() {
        return ordContact3OtherCellPhone;
    }

    public void setOrdContact3OtherCellPhone(LongFilter ordContact3OtherCellPhone) {
        this.ordContact3OtherCellPhone = ordContact3OtherCellPhone;
    }

    public StringFilter getOrdContact3Email() {
        return ordContact3Email;
    }

    public void setOrdContact3Email(StringFilter ordContact3Email) {
        this.ordContact3Email = ordContact3Email;
    }

    public StringFilter getOrdContact3OtherEmail() {
        return ordContact3OtherEmail;
    }

    public void setOrdContact3OtherEmail(StringFilter ordContact3OtherEmail) {
        this.ordContact3OtherEmail = ordContact3OtherEmail;
    }

    public StringFilter getOrdRequestRrStatusCd() {
        return ordRequestRrStatusCd;
    }

    public void setOrdRequestRrStatusCd(StringFilter ordRequestRrStatusCd) {
        this.ordRequestRrStatusCd = ordRequestRrStatusCd;
    }

    public StringFilter getOrdRequestRrVendorAppraisalId() {
        return ordRequestRrVendorAppraisalId;
    }

    public void setOrdRequestRrVendorAppraisalId(StringFilter ordRequestRrVendorAppraisalId) {
        this.ordRequestRrVendorAppraisalId = ordRequestRrVendorAppraisalId;
    }

    public LocalDateFilter getOrdRequestRrvendorAppraisalDraftRcvdDt() {
        return ordRequestRrvendorAppraisalDraftRcvdDt;
    }

    public void setOrdRequestRrvendorAppraisalDraftRcvdDt(LocalDateFilter ordRequestRrvendorAppraisalDraftRcvdDt) {
        this.ordRequestRrvendorAppraisalDraftRcvdDt = ordRequestRrvendorAppraisalDraftRcvdDt;
    }

    public StringFilter getOrdRequestRrXmlFormId() {
        return ordRequestRrXmlFormId;
    }

    public void setOrdRequestRrXmlFormId(StringFilter ordRequestRrXmlFormId) {
        this.ordRequestRrXmlFormId = ordRequestRrXmlFormId;
    }

    public StringFilter getOrdRequestRrPdfFormId() {
        return ordRequestRrPdfFormId;
    }

    public void setOrdRequestRrPdfFormId(StringFilter ordRequestRrPdfFormId) {
        this.ordRequestRrPdfFormId = ordRequestRrPdfFormId;
    }

    public StringFilter getOrdRequestRrApprFileDataSourcCd() {
        return ordRequestRrApprFileDataSourcCd;
    }

    public void setOrdRequestRrApprFileDataSourcCd(StringFilter ordRequestRrApprFileDataSourcCd) {
        this.ordRequestRrApprFileDataSourcCd = ordRequestRrApprFileDataSourcCd;
    }

    public BigDecimalFilter getOrdRequestRrReportValueAmt() {
        return ordRequestRrReportValueAmt;
    }

    public void setOrdRequestRrReportValueAmt(BigDecimalFilter ordRequestRrReportValueAmt) {
        this.ordRequestRrReportValueAmt = ordRequestRrReportValueAmt;
    }

    public StringFilter getOrdRequestRrAppraisalCompanyName() {
        return ordRequestRrAppraisalCompanyName;
    }

    public void setOrdRequestRrAppraisalCompanyName(StringFilter ordRequestRrAppraisalCompanyName) {
        this.ordRequestRrAppraisalCompanyName = ordRequestRrAppraisalCompanyName;
    }

    public StringFilter getOrdRequestRrvendorName() {
        return ordRequestRrvendorName;
    }

    public void setOrdRequestRrvendorName(StringFilter ordRequestRrvendorName) {
        this.ordRequestRrvendorName = ordRequestRrvendorName;
    }

    public StringFilter getOrdRequestRrvendorLicenseNbr() {
        return ordRequestRrvendorLicenseNbr;
    }

    public void setOrdRequestRrvendorLicenseNbr(StringFilter ordRequestRrvendorLicenseNbr) {
        this.ordRequestRrvendorLicenseNbr = ordRequestRrvendorLicenseNbr;
    }

    public StringFilter getOrdRequestRrvendorLicenseStateCd() {
        return ordRequestRrvendorLicenseStateCd;
    }

    public void setOrdRequestRrvendorLicenseStateCd(StringFilter ordRequestRrvendorLicenseStateCd) {
        this.ordRequestRrvendorLicenseStateCd = ordRequestRrvendorLicenseStateCd;
    }

    public StringFilter getOrdValuationCompletedProduct() {
        return ordValuationCompletedProduct;
    }

    public void setOrdValuationCompletedProduct(StringFilter ordValuationCompletedProduct) {
        this.ordValuationCompletedProduct = ordValuationCompletedProduct;
    }

    public LocalDateFilter getOrdValuationDueToClientDt() {
        return ordValuationDueToClientDt;
    }

    public void setOrdValuationDueToClientDt(LocalDateFilter ordValuationDueToClientDt) {
        this.ordValuationDueToClientDt = ordValuationDueToClientDt;
    }

    public LocalDateFilter getOrdValuationReportRecivedDt() {
        return ordValuationReportRecivedDt;
    }

    public void setOrdValuationReportRecivedDt(LocalDateFilter ordValuationReportRecivedDt) {
        this.ordValuationReportRecivedDt = ordValuationReportRecivedDt;
    }

    public LocalDateFilter getOrdValuationCompletionDt() {
        return ordValuationCompletionDt;
    }

    public void setOrdValuationCompletionDt(LocalDateFilter ordValuationCompletionDt) {
        this.ordValuationCompletionDt = ordValuationCompletionDt;
    }

    public StringFilter getOrdValuationReportStatusCd() {
        return ordValuationReportStatusCd;
    }

    public void setOrdValuationReportStatusCd(StringFilter ordValuationReportStatusCd) {
        this.ordValuationReportStatusCd = ordValuationReportStatusCd;
    }

    public BigDecimalFilter getOrdValuationAppraisedValueAmt() {
        return ordValuationAppraisedValueAmt;
    }

    public void setOrdValuationAppraisedValueAmt(BigDecimalFilter ordValuationAppraisedValueAmt) {
        this.ordValuationAppraisedValueAmt = ordValuationAppraisedValueAmt;
    }

    public BigDecimalFilter getOrdValuationBpoMarketValueAmt() {
        return ordValuationBpoMarketValueAmt;
    }

    public void setOrdValuationBpoMarketValueAmt(BigDecimalFilter ordValuationBpoMarketValueAmt) {
        this.ordValuationBpoMarketValueAmt = ordValuationBpoMarketValueAmt;
    }

    public BigDecimalFilter getOrdValuationBpoAsIsAmt() {
        return ordValuationBpoAsIsAmt;
    }

    public void setOrdValuationBpoAsIsAmt(BigDecimalFilter ordValuationBpoAsIsAmt) {
        this.ordValuationBpoAsIsAmt = ordValuationBpoAsIsAmt;
    }

    public BigDecimalFilter getOrdValuationBpoAsRepairedValueAmt() {
        return ordValuationBpoAsRepairedValueAmt;
    }

    public void setOrdValuationBpoAsRepairedValueAmt(BigDecimalFilter ordValuationBpoAsRepairedValueAmt) {
        this.ordValuationBpoAsRepairedValueAmt = ordValuationBpoAsRepairedValueAmt;
    }

    public StringFilter getOrdValuationRedFlagCode() {
        return ordValuationRedFlagCode;
    }

    public void setOrdValuationRedFlagCode(StringFilter ordValuationRedFlagCode) {
        this.ordValuationRedFlagCode = ordValuationRedFlagCode;
    }

    public StringFilter getOrdValuationRedFlagDesc() {
        return ordValuationRedFlagDesc;
    }

    public void setOrdValuationRedFlagDesc(StringFilter ordValuationRedFlagDesc) {
        this.ordValuationRedFlagDesc = ordValuationRedFlagDesc;
    }

    public StringFilter getOrdValuationAmcAppraisalId() {
        return ordValuationAmcAppraisalId;
    }

    public void setOrdValuationAmcAppraisalId(StringFilter ordValuationAmcAppraisalId) {
        this.ordValuationAmcAppraisalId = ordValuationAmcAppraisalId;
    }

    public LongFilter getOrdValuationFinalReviewId() {
        return ordValuationFinalReviewId;
    }

    public void setOrdValuationFinalReviewId(LongFilter ordValuationFinalReviewId) {
        this.ordValuationFinalReviewId = ordValuationFinalReviewId;
    }

    public BigDecimalFilter getOrdReviewRecommendedValueAmt() {
        return ordReviewRecommendedValueAmt;
    }

    public void setOrdReviewRecommendedValueAmt(BigDecimalFilter ordReviewRecommendedValueAmt) {
        this.ordReviewRecommendedValueAmt = ordReviewRecommendedValueAmt;
    }

    public LocalDateFilter getOrdReviewDt() {
        return ordReviewDt;
    }

    public void setOrdReviewDt(LocalDateFilter ordReviewDt) {
        this.ordReviewDt = ordReviewDt;
    }

    public StringFilter getOrdReviewRecommendedActionCd() {
        return ordReviewRecommendedActionCd;
    }

    public void setOrdReviewRecommendedActionCd(StringFilter ordReviewRecommendedActionCd) {
        this.ordReviewRecommendedActionCd = ordReviewRecommendedActionCd;
    }

    public StringFilter getOrdReviewReviewerDecisionCd() {
        return ordReviewReviewerDecisionCd;
    }

    public void setOrdReviewReviewerDecisionCd(StringFilter ordReviewReviewerDecisionCd) {
        this.ordReviewReviewerDecisionCd = ordReviewReviewerDecisionCd;
    }

    public StringFilter getOrdReviewReviewScore() {
        return ordReviewReviewScore;
    }

    public void setOrdReviewReviewScore(StringFilter ordReviewReviewScore) {
        this.ordReviewReviewScore = ordReviewReviewScore;
    }

    public StringFilter getOrdReviewFormUsed() {
        return ordReviewFormUsed;
    }

    public void setOrdReviewFormUsed(StringFilter ordReviewFormUsed) {
        this.ordReviewFormUsed = ordReviewFormUsed;
    }

    public BooleanFilter getOrdReviewProviderOnWatchListInd() {
        return ordReviewProviderOnWatchListInd;
    }

    public void setOrdReviewProviderOnWatchListInd(BooleanFilter ordReviewProviderOnWatchListInd) {
        this.ordReviewProviderOnWatchListInd = ordReviewProviderOnWatchListInd;
    }

    public BigDecimalFilter getOrdReviewCuredValueAmt() {
        return ordReviewCuredValueAmt;
    }

    public void setOrdReviewCuredValueAmt(BigDecimalFilter ordReviewCuredValueAmt) {
        this.ordReviewCuredValueAmt = ordReviewCuredValueAmt;
    }

    public StringFilter getOrdReviewFinalRecommendedActionCd() {
        return ordReviewFinalRecommendedActionCd;
    }

    public void setOrdReviewFinalRecommendedActionCd(StringFilter ordReviewFinalRecommendedActionCd) {
        this.ordReviewFinalRecommendedActionCd = ordReviewFinalRecommendedActionCd;
    }

    public LongFilter getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(LongFilter commentsId) {
        this.commentsId = commentsId;
    }

    public LongFilter getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(LongFilter documentsId) {
        this.documentsId = documentsId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrdersCriteria that = (OrdersCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ordNumber, that.ordNumber) &&
            Objects.equals(ordStageCd, that.ordStageCd) &&
            Objects.equals(ordOrgOrderStatusCd, that.ordOrgOrderStatusCd) &&
            Objects.equals(ordCmpOrderStatusCd, that.ordCmpOrderStatusCd) &&
            Objects.equals(ordProOrderStatusCd, that.ordProOrderStatusCd) &&
            Objects.equals(ordCmpNbr, that.ordCmpNbr) &&
            Objects.equals(ordBrnNbr, that.ordBrnNbr) &&
            Objects.equals(ordProNbr, that.ordProNbr) &&
            Objects.equals(ordProductCode, that.ordProductCode) &&
            Objects.equals(ordOriginalValueAmt, that.ordOriginalValueAmt) &&
            Objects.equals(ordParentTrackingNbr, that.ordParentTrackingNbr) &&
            Objects.equals(ordUserNbr, that.ordUserNbr) &&
            Objects.equals(ordOrderDt, that.ordOrderDt) &&
            Objects.equals(ordRushRequestDueDt, that.ordRushRequestDueDt) &&
            Objects.equals(ordOrgInstructions, that.ordOrgInstructions) &&
            Objects.equals(ordOrgOtherInstructions, that.ordOrgOtherInstructions) &&
            Objects.equals(ordMultiOrderInd, that.ordMultiOrderInd) &&
            Objects.equals(ordOrgDuedate, that.ordOrgDuedate) &&
            Objects.equals(ordProDuedate, that.ordProDuedate) &&
            Objects.equals(ordUcdpDataFnmaSubmitToUcdpInd, that.ordUcdpDataFnmaSubmitToUcdpInd) &&
            Objects.equals(ordUcdpDataFnmaServicerNbr, that.ordUcdpDataFnmaServicerNbr) &&
            Objects.equals(ordUcdpDataFhlmcSubmitToUcdpInd, that.ordUcdpDataFhlmcSubmitToUcdpInd) &&
            Objects.equals(ordUcdpDataFhlmcIdentificationNbr, that.ordUcdpDataFhlmcIdentificationNbr) &&
            Objects.equals(ordUcdpDataUcdpBussinessUnitNbr, that.ordUcdpDataUcdpBussinessUnitNbr) &&
            Objects.equals(ordUcdpSdFnamaDocumentId, that.ordUcdpSdFnamaDocumentId) &&
            Objects.equals(ordUcdpSdFnmaSubmissionStatusId, that.ordUcdpSdFnmaSubmissionStatusId) &&
            Objects.equals(ordUcdpSdFhlmcDocumentFileCd, that.ordUcdpSdFhlmcDocumentFileCd) &&
            Objects.equals(ordUcdpSdEadSubmissionStatusCd, that.ordUcdpSdEadSubmissionStatusCd) &&
            Objects.equals(ordUcdpFinalSubmissionDt, that.ordUcdpFinalSubmissionDt) &&
            Objects.equals(ordLoanNbr, that.ordLoanNbr) &&
            Objects.equals(ordloanOldLoanNbr, that.ordloanOldLoanNbr) &&
            Objects.equals(ordLoanProgram, that.ordLoanProgram) &&
            Objects.equals(ordLoanPurpose, that.ordLoanPurpose) &&
            Objects.equals(ordLoanType, that.ordLoanType) &&
            Objects.equals(ordLoanAmt, that.ordLoanAmt) &&
            Objects.equals(ordLoanQualifyingValueAmt, that.ordLoanQualifyingValueAmt) &&
            Objects.equals(ordLoanSalesPriceAmt, that.ordLoanSalesPriceAmt) &&
            Objects.equals(ordLoanBorrowerLastName, that.ordLoanBorrowerLastName) &&
            Objects.equals(ordLoanBorrowerFirstName, that.ordLoanBorrowerFirstName) &&
            Objects.equals(ordLoanBorrowerMiddleName, that.ordLoanBorrowerMiddleName) &&
            Objects.equals(ordLoanBorrowerIsCoBorrowerInd, that.ordLoanBorrowerIsCoBorrowerInd) &&
            Objects.equals(ordLoanFhaCaseNbr, that.ordLoanFhaCaseNbr) &&
            Objects.equals(ordLoanDeedRestrictionInd, that.ordLoanDeedRestrictionInd) &&
            Objects.equals(ordLoanEstimatedCloseDt, that.ordLoanEstimatedCloseDt) &&
            Objects.equals(ordLoanHpmlInd, that.ordLoanHpmlInd) &&
            Objects.equals(ordLoanIsNewConstructionInd, that.ordLoanIsNewConstructionInd) &&
            Objects.equals(ordLoanCustomerSegmentCode, that.ordLoanCustomerSegmentCode) &&
            Objects.equals(ordLoanNonSubjectPropertyInd, that.ordLoanNonSubjectPropertyInd) &&
            Objects.equals(ordLoanBorrowerRequestedCloseDt, that.ordLoanBorrowerRequestedCloseDt) &&
            Objects.equals(ordPropertyTypeCd, that.ordPropertyTypeCd) &&
            Objects.equals(ordPropertyAddress1, that.ordPropertyAddress1) &&
            Objects.equals(ordPropertyAddress2, that.ordPropertyAddress2) &&
            Objects.equals(ordPropertyCity, that.ordPropertyCity) &&
            Objects.equals(ordPropertyStateCd, that.ordPropertyStateCd) &&
            Objects.equals(ordPropertyZip, that.ordPropertyZip) &&
            Objects.equals(ordPropertyCounty, that.ordPropertyCounty) &&
            Objects.equals(ordPropertyLatitue, that.ordPropertyLatitue) &&
            Objects.equals(ordPropertyLongitude, that.ordPropertyLongitude) &&
            Objects.equals(ordPropertyProjectName, that.ordPropertyProjectName) &&
            Objects.equals(ordContact1TypeCd, that.ordContact1TypeCd) &&
            Objects.equals(ordContact1Name, that.ordContact1Name) &&
            Objects.equals(ordContact1WorkPhone, that.ordContact1WorkPhone) &&
            Objects.equals(ordContact1HomePhone, that.ordContact1HomePhone) &&
            Objects.equals(ordContact1CellPhone, that.ordContact1CellPhone) &&
            Objects.equals(ordContact1OtherCellPhone, that.ordContact1OtherCellPhone) &&
            Objects.equals(ordContact1Email, that.ordContact1Email) &&
            Objects.equals(ordContact1OtherEmail, that.ordContact1OtherEmail) &&
            Objects.equals(ordContact2TypeCd, that.ordContact2TypeCd) &&
            Objects.equals(ordContact2Name, that.ordContact2Name) &&
            Objects.equals(ordContact2WorkPhone, that.ordContact2WorkPhone) &&
            Objects.equals(ordContact2HomePhone, that.ordContact2HomePhone) &&
            Objects.equals(ordContact2CellPhone, that.ordContact2CellPhone) &&
            Objects.equals(ordContact2OtherCellPhone, that.ordContact2OtherCellPhone) &&
            Objects.equals(ordContact2Email, that.ordContact2Email) &&
            Objects.equals(ordContact2OtherEmail, that.ordContact2OtherEmail) &&
            Objects.equals(ordContact3TypeCd, that.ordContact3TypeCd) &&
            Objects.equals(ordContact3Name, that.ordContact3Name) &&
            Objects.equals(ordContact3WorkPhone, that.ordContact3WorkPhone) &&
            Objects.equals(ordContact3HomePhone, that.ordContact3HomePhone) &&
            Objects.equals(ordContact3CellPhone, that.ordContact3CellPhone) &&
            Objects.equals(ordContact3OtherCellPhone, that.ordContact3OtherCellPhone) &&
            Objects.equals(ordContact3Email, that.ordContact3Email) &&
            Objects.equals(ordContact3OtherEmail, that.ordContact3OtherEmail) &&
            Objects.equals(ordRequestRrStatusCd, that.ordRequestRrStatusCd) &&
            Objects.equals(ordRequestRrVendorAppraisalId, that.ordRequestRrVendorAppraisalId) &&
            Objects.equals(ordRequestRrvendorAppraisalDraftRcvdDt, that.ordRequestRrvendorAppraisalDraftRcvdDt) &&
            Objects.equals(ordRequestRrXmlFormId, that.ordRequestRrXmlFormId) &&
            Objects.equals(ordRequestRrPdfFormId, that.ordRequestRrPdfFormId) &&
            Objects.equals(ordRequestRrApprFileDataSourcCd, that.ordRequestRrApprFileDataSourcCd) &&
            Objects.equals(ordRequestRrReportValueAmt, that.ordRequestRrReportValueAmt) &&
            Objects.equals(ordRequestRrAppraisalCompanyName, that.ordRequestRrAppraisalCompanyName) &&
            Objects.equals(ordRequestRrvendorName, that.ordRequestRrvendorName) &&
            Objects.equals(ordRequestRrvendorLicenseNbr, that.ordRequestRrvendorLicenseNbr) &&
            Objects.equals(ordRequestRrvendorLicenseStateCd, that.ordRequestRrvendorLicenseStateCd) &&
            Objects.equals(ordValuationCompletedProduct, that.ordValuationCompletedProduct) &&
            Objects.equals(ordValuationDueToClientDt, that.ordValuationDueToClientDt) &&
            Objects.equals(ordValuationReportRecivedDt, that.ordValuationReportRecivedDt) &&
            Objects.equals(ordValuationCompletionDt, that.ordValuationCompletionDt) &&
            Objects.equals(ordValuationReportStatusCd, that.ordValuationReportStatusCd) &&
            Objects.equals(ordValuationAppraisedValueAmt, that.ordValuationAppraisedValueAmt) &&
            Objects.equals(ordValuationBpoMarketValueAmt, that.ordValuationBpoMarketValueAmt) &&
            Objects.equals(ordValuationBpoAsIsAmt, that.ordValuationBpoAsIsAmt) &&
            Objects.equals(ordValuationBpoAsRepairedValueAmt, that.ordValuationBpoAsRepairedValueAmt) &&
            Objects.equals(ordValuationRedFlagCode, that.ordValuationRedFlagCode) &&
            Objects.equals(ordValuationRedFlagDesc, that.ordValuationRedFlagDesc) &&
            Objects.equals(ordValuationAmcAppraisalId, that.ordValuationAmcAppraisalId) &&
            Objects.equals(ordValuationFinalReviewId, that.ordValuationFinalReviewId) &&
            Objects.equals(ordReviewRecommendedValueAmt, that.ordReviewRecommendedValueAmt) &&
            Objects.equals(ordReviewDt, that.ordReviewDt) &&
            Objects.equals(ordReviewRecommendedActionCd, that.ordReviewRecommendedActionCd) &&
            Objects.equals(ordReviewReviewerDecisionCd, that.ordReviewReviewerDecisionCd) &&
            Objects.equals(ordReviewReviewScore, that.ordReviewReviewScore) &&
            Objects.equals(ordReviewFormUsed, that.ordReviewFormUsed) &&
            Objects.equals(ordReviewProviderOnWatchListInd, that.ordReviewProviderOnWatchListInd) &&
            Objects.equals(ordReviewCuredValueAmt, that.ordReviewCuredValueAmt) &&
            Objects.equals(ordReviewFinalRecommendedActionCd, that.ordReviewFinalRecommendedActionCd) &&
            Objects.equals(commentsId, that.commentsId) &&
            Objects.equals(documentsId, that.documentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ordNumber,
        ordStageCd,
        ordOrgOrderStatusCd,
        ordCmpOrderStatusCd,
        ordProOrderStatusCd,
        ordCmpNbr,
        ordBrnNbr,
        ordProNbr,
        ordProductCode,
        ordOriginalValueAmt,
        ordParentTrackingNbr,
        ordUserNbr,
        ordOrderDt,
        ordRushRequestDueDt,
        ordOrgInstructions,
        ordOrgOtherInstructions,
        ordMultiOrderInd,
        ordOrgDuedate,
        ordProDuedate,
        ordUcdpDataFnmaSubmitToUcdpInd,
        ordUcdpDataFnmaServicerNbr,
        ordUcdpDataFhlmcSubmitToUcdpInd,
        ordUcdpDataFhlmcIdentificationNbr,
        ordUcdpDataUcdpBussinessUnitNbr,
        ordUcdpSdFnamaDocumentId,
        ordUcdpSdFnmaSubmissionStatusId,
        ordUcdpSdFhlmcDocumentFileCd,
        ordUcdpSdEadSubmissionStatusCd,
        ordUcdpFinalSubmissionDt,
        ordLoanNbr,
        ordloanOldLoanNbr,
        ordLoanProgram,
        ordLoanPurpose,
        ordLoanType,
        ordLoanAmt,
        ordLoanQualifyingValueAmt,
        ordLoanSalesPriceAmt,
        ordLoanBorrowerLastName,
        ordLoanBorrowerFirstName,
        ordLoanBorrowerMiddleName,
        ordLoanBorrowerIsCoBorrowerInd,
        ordLoanFhaCaseNbr,
        ordLoanDeedRestrictionInd,
        ordLoanEstimatedCloseDt,
        ordLoanHpmlInd,
        ordLoanIsNewConstructionInd,
        ordLoanCustomerSegmentCode,
        ordLoanNonSubjectPropertyInd,
        ordLoanBorrowerRequestedCloseDt,
        ordPropertyTypeCd,
        ordPropertyAddress1,
        ordPropertyAddress2,
        ordPropertyCity,
        ordPropertyStateCd,
        ordPropertyZip,
        ordPropertyCounty,
        ordPropertyLatitue,
        ordPropertyLongitude,
        ordPropertyProjectName,
        ordContact1TypeCd,
        ordContact1Name,
        ordContact1WorkPhone,
        ordContact1HomePhone,
        ordContact1CellPhone,
        ordContact1OtherCellPhone,
        ordContact1Email,
        ordContact1OtherEmail,
        ordContact2TypeCd,
        ordContact2Name,
        ordContact2WorkPhone,
        ordContact2HomePhone,
        ordContact2CellPhone,
        ordContact2OtherCellPhone,
        ordContact2Email,
        ordContact2OtherEmail,
        ordContact3TypeCd,
        ordContact3Name,
        ordContact3WorkPhone,
        ordContact3HomePhone,
        ordContact3CellPhone,
        ordContact3OtherCellPhone,
        ordContact3Email,
        ordContact3OtherEmail,
        ordRequestRrStatusCd,
        ordRequestRrVendorAppraisalId,
        ordRequestRrvendorAppraisalDraftRcvdDt,
        ordRequestRrXmlFormId,
        ordRequestRrPdfFormId,
        ordRequestRrApprFileDataSourcCd,
        ordRequestRrReportValueAmt,
        ordRequestRrAppraisalCompanyName,
        ordRequestRrvendorName,
        ordRequestRrvendorLicenseNbr,
        ordRequestRrvendorLicenseStateCd,
        ordValuationCompletedProduct,
        ordValuationDueToClientDt,
        ordValuationReportRecivedDt,
        ordValuationCompletionDt,
        ordValuationReportStatusCd,
        ordValuationAppraisedValueAmt,
        ordValuationBpoMarketValueAmt,
        ordValuationBpoAsIsAmt,
        ordValuationBpoAsRepairedValueAmt,
        ordValuationRedFlagCode,
        ordValuationRedFlagDesc,
        ordValuationAmcAppraisalId,
        ordValuationFinalReviewId,
        ordReviewRecommendedValueAmt,
        ordReviewDt,
        ordReviewRecommendedActionCd,
        ordReviewReviewerDecisionCd,
        ordReviewReviewScore,
        ordReviewFormUsed,
        ordReviewProviderOnWatchListInd,
        ordReviewCuredValueAmt,
        ordReviewFinalRecommendedActionCd,
        commentsId,
        documentsId
        );
    }

    @Override
    public String toString() {
        return "OrdersCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ordNumber != null ? "ordNumber=" + ordNumber + ", " : "") +
                (ordStageCd != null ? "ordStageCd=" + ordStageCd + ", " : "") +
                (ordOrgOrderStatusCd != null ? "ordOrgOrderStatusCd=" + ordOrgOrderStatusCd + ", " : "") +
                (ordCmpOrderStatusCd != null ? "ordCmpOrderStatusCd=" + ordCmpOrderStatusCd + ", " : "") +
                (ordProOrderStatusCd != null ? "ordProOrderStatusCd=" + ordProOrderStatusCd + ", " : "") +
                (ordCmpNbr != null ? "ordCmpNbr=" + ordCmpNbr + ", " : "") +
                (ordBrnNbr != null ? "ordBrnNbr=" + ordBrnNbr + ", " : "") +
                (ordProNbr != null ? "ordProNbr=" + ordProNbr + ", " : "") +
                (ordProductCode != null ? "ordProductCode=" + ordProductCode + ", " : "") +
                (ordOriginalValueAmt != null ? "ordOriginalValueAmt=" + ordOriginalValueAmt + ", " : "") +
                (ordParentTrackingNbr != null ? "ordParentTrackingNbr=" + ordParentTrackingNbr + ", " : "") +
                (ordUserNbr != null ? "ordUserNbr=" + ordUserNbr + ", " : "") +
                (ordOrderDt != null ? "ordOrderDt=" + ordOrderDt + ", " : "") +
                (ordRushRequestDueDt != null ? "ordRushRequestDueDt=" + ordRushRequestDueDt + ", " : "") +
                (ordOrgInstructions != null ? "ordOrgInstructions=" + ordOrgInstructions + ", " : "") +
                (ordOrgOtherInstructions != null ? "ordOrgOtherInstructions=" + ordOrgOtherInstructions + ", " : "") +
                (ordMultiOrderInd != null ? "ordMultiOrderInd=" + ordMultiOrderInd + ", " : "") +
                (ordOrgDuedate != null ? "ordOrgDuedate=" + ordOrgDuedate + ", " : "") +
                (ordProDuedate != null ? "ordProDuedate=" + ordProDuedate + ", " : "") +
                (ordUcdpDataFnmaSubmitToUcdpInd != null ? "ordUcdpDataFnmaSubmitToUcdpInd=" + ordUcdpDataFnmaSubmitToUcdpInd + ", " : "") +
                (ordUcdpDataFnmaServicerNbr != null ? "ordUcdpDataFnmaServicerNbr=" + ordUcdpDataFnmaServicerNbr + ", " : "") +
                (ordUcdpDataFhlmcSubmitToUcdpInd != null ? "ordUcdpDataFhlmcSubmitToUcdpInd=" + ordUcdpDataFhlmcSubmitToUcdpInd + ", " : "") +
                (ordUcdpDataFhlmcIdentificationNbr != null ? "ordUcdpDataFhlmcIdentificationNbr=" + ordUcdpDataFhlmcIdentificationNbr + ", " : "") +
                (ordUcdpDataUcdpBussinessUnitNbr != null ? "ordUcdpDataUcdpBussinessUnitNbr=" + ordUcdpDataUcdpBussinessUnitNbr + ", " : "") +
                (ordUcdpSdFnamaDocumentId != null ? "ordUcdpSdFnamaDocumentId=" + ordUcdpSdFnamaDocumentId + ", " : "") +
                (ordUcdpSdFnmaSubmissionStatusId != null ? "ordUcdpSdFnmaSubmissionStatusId=" + ordUcdpSdFnmaSubmissionStatusId + ", " : "") +
                (ordUcdpSdFhlmcDocumentFileCd != null ? "ordUcdpSdFhlmcDocumentFileCd=" + ordUcdpSdFhlmcDocumentFileCd + ", " : "") +
                (ordUcdpSdEadSubmissionStatusCd != null ? "ordUcdpSdEadSubmissionStatusCd=" + ordUcdpSdEadSubmissionStatusCd + ", " : "") +
                (ordUcdpFinalSubmissionDt != null ? "ordUcdpFinalSubmissionDt=" + ordUcdpFinalSubmissionDt + ", " : "") +
                (ordLoanNbr != null ? "ordLoanNbr=" + ordLoanNbr + ", " : "") +
                (ordloanOldLoanNbr != null ? "ordloanOldLoanNbr=" + ordloanOldLoanNbr + ", " : "") +
                (ordLoanProgram != null ? "ordLoanProgram=" + ordLoanProgram + ", " : "") +
                (ordLoanPurpose != null ? "ordLoanPurpose=" + ordLoanPurpose + ", " : "") +
                (ordLoanType != null ? "ordLoanType=" + ordLoanType + ", " : "") +
                (ordLoanAmt != null ? "ordLoanAmt=" + ordLoanAmt + ", " : "") +
                (ordLoanQualifyingValueAmt != null ? "ordLoanQualifyingValueAmt=" + ordLoanQualifyingValueAmt + ", " : "") +
                (ordLoanSalesPriceAmt != null ? "ordLoanSalesPriceAmt=" + ordLoanSalesPriceAmt + ", " : "") +
                (ordLoanBorrowerLastName != null ? "ordLoanBorrowerLastName=" + ordLoanBorrowerLastName + ", " : "") +
                (ordLoanBorrowerFirstName != null ? "ordLoanBorrowerFirstName=" + ordLoanBorrowerFirstName + ", " : "") +
                (ordLoanBorrowerMiddleName != null ? "ordLoanBorrowerMiddleName=" + ordLoanBorrowerMiddleName + ", " : "") +
                (ordLoanBorrowerIsCoBorrowerInd != null ? "ordLoanBorrowerIsCoBorrowerInd=" + ordLoanBorrowerIsCoBorrowerInd + ", " : "") +
                (ordLoanFhaCaseNbr != null ? "ordLoanFhaCaseNbr=" + ordLoanFhaCaseNbr + ", " : "") +
                (ordLoanDeedRestrictionInd != null ? "ordLoanDeedRestrictionInd=" + ordLoanDeedRestrictionInd + ", " : "") +
                (ordLoanEstimatedCloseDt != null ? "ordLoanEstimatedCloseDt=" + ordLoanEstimatedCloseDt + ", " : "") +
                (ordLoanHpmlInd != null ? "ordLoanHpmlInd=" + ordLoanHpmlInd + ", " : "") +
                (ordLoanIsNewConstructionInd != null ? "ordLoanIsNewConstructionInd=" + ordLoanIsNewConstructionInd + ", " : "") +
                (ordLoanCustomerSegmentCode != null ? "ordLoanCustomerSegmentCode=" + ordLoanCustomerSegmentCode + ", " : "") +
                (ordLoanNonSubjectPropertyInd != null ? "ordLoanNonSubjectPropertyInd=" + ordLoanNonSubjectPropertyInd + ", " : "") +
                (ordLoanBorrowerRequestedCloseDt != null ? "ordLoanBorrowerRequestedCloseDt=" + ordLoanBorrowerRequestedCloseDt + ", " : "") +
                (ordPropertyTypeCd != null ? "ordPropertyTypeCd=" + ordPropertyTypeCd + ", " : "") +
                (ordPropertyAddress1 != null ? "ordPropertyAddress1=" + ordPropertyAddress1 + ", " : "") +
                (ordPropertyAddress2 != null ? "ordPropertyAddress2=" + ordPropertyAddress2 + ", " : "") +
                (ordPropertyCity != null ? "ordPropertyCity=" + ordPropertyCity + ", " : "") +
                (ordPropertyStateCd != null ? "ordPropertyStateCd=" + ordPropertyStateCd + ", " : "") +
                (ordPropertyZip != null ? "ordPropertyZip=" + ordPropertyZip + ", " : "") +
                (ordPropertyCounty != null ? "ordPropertyCounty=" + ordPropertyCounty + ", " : "") +
                (ordPropertyLatitue != null ? "ordPropertyLatitue=" + ordPropertyLatitue + ", " : "") +
                (ordPropertyLongitude != null ? "ordPropertyLongitude=" + ordPropertyLongitude + ", " : "") +
                (ordPropertyProjectName != null ? "ordPropertyProjectName=" + ordPropertyProjectName + ", " : "") +
                (ordContact1TypeCd != null ? "ordContact1TypeCd=" + ordContact1TypeCd + ", " : "") +
                (ordContact1Name != null ? "ordContact1Name=" + ordContact1Name + ", " : "") +
                (ordContact1WorkPhone != null ? "ordContact1WorkPhone=" + ordContact1WorkPhone + ", " : "") +
                (ordContact1HomePhone != null ? "ordContact1HomePhone=" + ordContact1HomePhone + ", " : "") +
                (ordContact1CellPhone != null ? "ordContact1CellPhone=" + ordContact1CellPhone + ", " : "") +
                (ordContact1OtherCellPhone != null ? "ordContact1OtherCellPhone=" + ordContact1OtherCellPhone + ", " : "") +
                (ordContact1Email != null ? "ordContact1Email=" + ordContact1Email + ", " : "") +
                (ordContact1OtherEmail != null ? "ordContact1OtherEmail=" + ordContact1OtherEmail + ", " : "") +
                (ordContact2TypeCd != null ? "ordContact2TypeCd=" + ordContact2TypeCd + ", " : "") +
                (ordContact2Name != null ? "ordContact2Name=" + ordContact2Name + ", " : "") +
                (ordContact2WorkPhone != null ? "ordContact2WorkPhone=" + ordContact2WorkPhone + ", " : "") +
                (ordContact2HomePhone != null ? "ordContact2HomePhone=" + ordContact2HomePhone + ", " : "") +
                (ordContact2CellPhone != null ? "ordContact2CellPhone=" + ordContact2CellPhone + ", " : "") +
                (ordContact2OtherCellPhone != null ? "ordContact2OtherCellPhone=" + ordContact2OtherCellPhone + ", " : "") +
                (ordContact2Email != null ? "ordContact2Email=" + ordContact2Email + ", " : "") +
                (ordContact2OtherEmail != null ? "ordContact2OtherEmail=" + ordContact2OtherEmail + ", " : "") +
                (ordContact3TypeCd != null ? "ordContact3TypeCd=" + ordContact3TypeCd + ", " : "") +
                (ordContact3Name != null ? "ordContact3Name=" + ordContact3Name + ", " : "") +
                (ordContact3WorkPhone != null ? "ordContact3WorkPhone=" + ordContact3WorkPhone + ", " : "") +
                (ordContact3HomePhone != null ? "ordContact3HomePhone=" + ordContact3HomePhone + ", " : "") +
                (ordContact3CellPhone != null ? "ordContact3CellPhone=" + ordContact3CellPhone + ", " : "") +
                (ordContact3OtherCellPhone != null ? "ordContact3OtherCellPhone=" + ordContact3OtherCellPhone + ", " : "") +
                (ordContact3Email != null ? "ordContact3Email=" + ordContact3Email + ", " : "") +
                (ordContact3OtherEmail != null ? "ordContact3OtherEmail=" + ordContact3OtherEmail + ", " : "") +
                (ordRequestRrStatusCd != null ? "ordRequestRrStatusCd=" + ordRequestRrStatusCd + ", " : "") +
                (ordRequestRrVendorAppraisalId != null ? "ordRequestRrVendorAppraisalId=" + ordRequestRrVendorAppraisalId + ", " : "") +
                (ordRequestRrvendorAppraisalDraftRcvdDt != null ? "ordRequestRrvendorAppraisalDraftRcvdDt=" + ordRequestRrvendorAppraisalDraftRcvdDt + ", " : "") +
                (ordRequestRrXmlFormId != null ? "ordRequestRrXmlFormId=" + ordRequestRrXmlFormId + ", " : "") +
                (ordRequestRrPdfFormId != null ? "ordRequestRrPdfFormId=" + ordRequestRrPdfFormId + ", " : "") +
                (ordRequestRrApprFileDataSourcCd != null ? "ordRequestRrApprFileDataSourcCd=" + ordRequestRrApprFileDataSourcCd + ", " : "") +
                (ordRequestRrReportValueAmt != null ? "ordRequestRrReportValueAmt=" + ordRequestRrReportValueAmt + ", " : "") +
                (ordRequestRrAppraisalCompanyName != null ? "ordRequestRrAppraisalCompanyName=" + ordRequestRrAppraisalCompanyName + ", " : "") +
                (ordRequestRrvendorName != null ? "ordRequestRrvendorName=" + ordRequestRrvendorName + ", " : "") +
                (ordRequestRrvendorLicenseNbr != null ? "ordRequestRrvendorLicenseNbr=" + ordRequestRrvendorLicenseNbr + ", " : "") +
                (ordRequestRrvendorLicenseStateCd != null ? "ordRequestRrvendorLicenseStateCd=" + ordRequestRrvendorLicenseStateCd + ", " : "") +
                (ordValuationCompletedProduct != null ? "ordValuationCompletedProduct=" + ordValuationCompletedProduct + ", " : "") +
                (ordValuationDueToClientDt != null ? "ordValuationDueToClientDt=" + ordValuationDueToClientDt + ", " : "") +
                (ordValuationReportRecivedDt != null ? "ordValuationReportRecivedDt=" + ordValuationReportRecivedDt + ", " : "") +
                (ordValuationCompletionDt != null ? "ordValuationCompletionDt=" + ordValuationCompletionDt + ", " : "") +
                (ordValuationReportStatusCd != null ? "ordValuationReportStatusCd=" + ordValuationReportStatusCd + ", " : "") +
                (ordValuationAppraisedValueAmt != null ? "ordValuationAppraisedValueAmt=" + ordValuationAppraisedValueAmt + ", " : "") +
                (ordValuationBpoMarketValueAmt != null ? "ordValuationBpoMarketValueAmt=" + ordValuationBpoMarketValueAmt + ", " : "") +
                (ordValuationBpoAsIsAmt != null ? "ordValuationBpoAsIsAmt=" + ordValuationBpoAsIsAmt + ", " : "") +
                (ordValuationBpoAsRepairedValueAmt != null ? "ordValuationBpoAsRepairedValueAmt=" + ordValuationBpoAsRepairedValueAmt + ", " : "") +
                (ordValuationRedFlagCode != null ? "ordValuationRedFlagCode=" + ordValuationRedFlagCode + ", " : "") +
                (ordValuationRedFlagDesc != null ? "ordValuationRedFlagDesc=" + ordValuationRedFlagDesc + ", " : "") +
                (ordValuationAmcAppraisalId != null ? "ordValuationAmcAppraisalId=" + ordValuationAmcAppraisalId + ", " : "") +
                (ordValuationFinalReviewId != null ? "ordValuationFinalReviewId=" + ordValuationFinalReviewId + ", " : "") +
                (ordReviewRecommendedValueAmt != null ? "ordReviewRecommendedValueAmt=" + ordReviewRecommendedValueAmt + ", " : "") +
                (ordReviewDt != null ? "ordReviewDt=" + ordReviewDt + ", " : "") +
                (ordReviewRecommendedActionCd != null ? "ordReviewRecommendedActionCd=" + ordReviewRecommendedActionCd + ", " : "") +
                (ordReviewReviewerDecisionCd != null ? "ordReviewReviewerDecisionCd=" + ordReviewReviewerDecisionCd + ", " : "") +
                (ordReviewReviewScore != null ? "ordReviewReviewScore=" + ordReviewReviewScore + ", " : "") +
                (ordReviewFormUsed != null ? "ordReviewFormUsed=" + ordReviewFormUsed + ", " : "") +
                (ordReviewProviderOnWatchListInd != null ? "ordReviewProviderOnWatchListInd=" + ordReviewProviderOnWatchListInd + ", " : "") +
                (ordReviewCuredValueAmt != null ? "ordReviewCuredValueAmt=" + ordReviewCuredValueAmt + ", " : "") +
                (ordReviewFinalRecommendedActionCd != null ? "ordReviewFinalRecommendedActionCd=" + ordReviewFinalRecommendedActionCd + ", " : "") +
                (commentsId != null ? "commentsId=" + commentsId + ", " : "") +
                (documentsId != null ? "documentsId=" + documentsId + ", " : "") +
            "}";
    }

}
