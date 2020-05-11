import { Moment } from 'moment';
import { IOrderComments } from 'app/shared/model/order-comments.model';
import { IOrderDocuments } from 'app/shared/model/order-documents.model';

export interface IOrders {
  id?: number;
  ordNumber?: string;
  ordStageCd?: string;
  ordOrgOrderStatusCd?: string;
  ordCmpOrderStatusCd?: string;
  ordProOrderStatusCd?: string;
  ordCmpNbr?: string;
  ordBrnNbr?: string;
  ordProNbr?: string;
  ordProductCode?: string;
  ordOriginalValueAmt?: number;
  ordParentTrackingNbr?: string;
  ordUserNbr?: string;
  ordOrderDt?: Moment;
  ordRushRequestDueDt?: Moment;
  ordOrgInstructions?: string;
  ordOrgOtherInstructions?: string;
  ordMultiOrderInd?: boolean;
  ordOrgDuedate?: Moment;
  ordProDuedate?: Moment;
  ordUcdpDataFnmaSubmitToUcdpInd?: boolean;
  ordUcdpDataFnmaServicerNbr?: string;
  ordUcdpDataFhlmcSubmitToUcdpInd?: boolean;
  ordUcdpDataFhlmcIdentificationNbr?: string;
  ordUcdpDataUcdpBussinessUnitNbr?: string;
  ordUcdpSdFnamaDocumentId?: string;
  ordUcdpSdFnmaSubmissionStatusId?: string;
  ordUcdpSdFhlmcDocumentFileCd?: string;
  ordUcdpSdEadSubmissionStatusCd?: string;
  ordUcdpFinalSubmissionDt?: Moment;
  ordLoanNbr?: string;
  ordloanOldLoanNbr?: string;
  ordLoanProgram?: string;
  ordLoanPurpose?: string;
  ordLoanType?: string;
  ordLoanAmt?: number;
  ordLoanQualifyingValueAmt?: number;
  ordLoanSalesPriceAmt?: number;
  ordLoanBorrowerLastName?: string;
  ordLoanBorrowerFirstName?: string;
  ordLoanBorrowerMiddleName?: string;
  ordLoanBorrowerIsCoBorrowerInd?: boolean;
  ordLoanFhaCaseNbr?: string;
  ordLoanDeedRestrictionInd?: boolean;
  ordLoanEstimatedCloseDt?: Moment;
  ordLoanHpmlInd?: boolean;
  ordLoanIsNewConstructionInd?: boolean;
  ordLoanCustomerSegmentCode?: string;
  ordLoanNonSubjectPropertyInd?: boolean;
  ordLoanBorrowerRequestedCloseDt?: Moment;
  ordPropertyTypeCd?: string;
  ordPropertyAddress1?: string;
  ordPropertyAddress2?: string;
  ordPropertyCity?: string;
  ordPropertyStateCd?: string;
  ordPropertyZip?: string;
  ordPropertyCounty?: string;
  ordPropertyLatitue?: string;
  ordPropertyLongitude?: string;
  ordPropertyProjectName?: string;
  ordContact1TypeCd?: string;
  ordContact1Name?: string;
  ordContact1WorkPhone?: number;
  ordContact1HomePhone?: number;
  ordContact1CellPhone?: number;
  ordContact1OtherCellPhone?: number;
  ordContact1Email?: string;
  ordContact1OtherEmail?: string;
  ordContact2TypeCd?: string;
  ordContact2Name?: string;
  ordContact2WorkPhone?: number;
  ordContact2HomePhone?: number;
  ordContact2CellPhone?: number;
  ordContact2OtherCellPhone?: number;
  ordContact2Email?: string;
  ordContact2OtherEmail?: string;
  ordContact3TypeCd?: string;
  ordContact3Name?: string;
  ordContact3WorkPhone?: number;
  ordContact3HomePhone?: number;
  ordContact3CellPhone?: number;
  ordContact3OtherCellPhone?: number;
  ordContact3Email?: string;
  ordContact3OtherEmail?: string;
  ordRequestRrStatusCd?: string;
  ordRequestRrVendorAppraisalId?: string;
  ordRequestRrvendorAppraisalDraftRcvdDt?: Moment;
  ordRequestRrXmlFormId?: string;
  ordRequestRrPdfFormId?: string;
  ordRequestRrApprFileDataSourcCd?: string;
  ordRequestRrReportValueAmt?: number;
  ordRequestRrAppraisalCompanyName?: string;
  ordRequestRrvendorName?: string;
  ordRequestRrvendorLicenseNbr?: string;
  ordRequestRrvendorLicenseStateCd?: string;
  ordValuationCompletedProduct?: string;
  ordValuationDueToClientDt?: Moment;
  ordValuationReportRecivedDt?: Moment;
  ordValuationCompletionDt?: Moment;
  ordValuationReportStatusCd?: string;
  ordValuationAppraisedValueAmt?: number;
  ordValuationBpoMarketValueAmt?: number;
  ordValuationBpoAsIsAmt?: number;
  ordValuationBpoAsRepairedValueAmt?: number;
  ordValuationRedFlagCode?: string;
  ordValuationRedFlagDesc?: string;
  ordValuationAmcAppraisalId?: string;
  ordValuationFinalReviewId?: number;
  ordReviewRecommendedValueAmt?: number;
  ordReviewDt?: Moment;
  ordReviewRecommendedActionCd?: string;
  ordReviewReviewerDecisionCd?: string;
  ordReviewReviewScore?: string;
  ordReviewFormUsed?: string;
  ordReviewProviderOnWatchListInd?: boolean;
  ordReviewCuredValueAmt?: number;
  ordReviewFinalRecommendedActionCd?: string;
  comments?: IOrderComments[];
  documents?: IOrderDocuments[];
}

export class Orders implements IOrders {
  constructor(
    public id?: number,
    public ordNumber?: string,
    public ordStageCd?: string,
    public ordOrgOrderStatusCd?: string,
    public ordCmpOrderStatusCd?: string,
    public ordProOrderStatusCd?: string,
    public ordCmpNbr?: string,
    public ordBrnNbr?: string,
    public ordProNbr?: string,
    public ordProductCode?: string,
    public ordOriginalValueAmt?: number,
    public ordParentTrackingNbr?: string,
    public ordUserNbr?: string,
    public ordOrderDt?: Moment,
    public ordRushRequestDueDt?: Moment,
    public ordOrgInstructions?: string,
    public ordOrgOtherInstructions?: string,
    public ordMultiOrderInd?: boolean,
    public ordOrgDuedate?: Moment,
    public ordProDuedate?: Moment,
    public ordUcdpDataFnmaSubmitToUcdpInd?: boolean,
    public ordUcdpDataFnmaServicerNbr?: string,
    public ordUcdpDataFhlmcSubmitToUcdpInd?: boolean,
    public ordUcdpDataFhlmcIdentificationNbr?: string,
    public ordUcdpDataUcdpBussinessUnitNbr?: string,
    public ordUcdpSdFnamaDocumentId?: string,
    public ordUcdpSdFnmaSubmissionStatusId?: string,
    public ordUcdpSdFhlmcDocumentFileCd?: string,
    public ordUcdpSdEadSubmissionStatusCd?: string,
    public ordUcdpFinalSubmissionDt?: Moment,
    public ordLoanNbr?: string,
    public ordloanOldLoanNbr?: string,
    public ordLoanProgram?: string,
    public ordLoanPurpose?: string,
    public ordLoanType?: string,
    public ordLoanAmt?: number,
    public ordLoanQualifyingValueAmt?: number,
    public ordLoanSalesPriceAmt?: number,
    public ordLoanBorrowerLastName?: string,
    public ordLoanBorrowerFirstName?: string,
    public ordLoanBorrowerMiddleName?: string,
    public ordLoanBorrowerIsCoBorrowerInd?: boolean,
    public ordLoanFhaCaseNbr?: string,
    public ordLoanDeedRestrictionInd?: boolean,
    public ordLoanEstimatedCloseDt?: Moment,
    public ordLoanHpmlInd?: boolean,
    public ordLoanIsNewConstructionInd?: boolean,
    public ordLoanCustomerSegmentCode?: string,
    public ordLoanNonSubjectPropertyInd?: boolean,
    public ordLoanBorrowerRequestedCloseDt?: Moment,
    public ordPropertyTypeCd?: string,
    public ordPropertyAddress1?: string,
    public ordPropertyAddress2?: string,
    public ordPropertyCity?: string,
    public ordPropertyStateCd?: string,
    public ordPropertyZip?: string,
    public ordPropertyCounty?: string,
    public ordPropertyLatitue?: string,
    public ordPropertyLongitude?: string,
    public ordPropertyProjectName?: string,
    public ordContact1TypeCd?: string,
    public ordContact1Name?: string,
    public ordContact1WorkPhone?: number,
    public ordContact1HomePhone?: number,
    public ordContact1CellPhone?: number,
    public ordContact1OtherCellPhone?: number,
    public ordContact1Email?: string,
    public ordContact1OtherEmail?: string,
    public ordContact2TypeCd?: string,
    public ordContact2Name?: string,
    public ordContact2WorkPhone?: number,
    public ordContact2HomePhone?: number,
    public ordContact2CellPhone?: number,
    public ordContact2OtherCellPhone?: number,
    public ordContact2Email?: string,
    public ordContact2OtherEmail?: string,
    public ordContact3TypeCd?: string,
    public ordContact3Name?: string,
    public ordContact3WorkPhone?: number,
    public ordContact3HomePhone?: number,
    public ordContact3CellPhone?: number,
    public ordContact3OtherCellPhone?: number,
    public ordContact3Email?: string,
    public ordContact3OtherEmail?: string,
    public ordRequestRrStatusCd?: string,
    public ordRequestRrVendorAppraisalId?: string,
    public ordRequestRrvendorAppraisalDraftRcvdDt?: Moment,
    public ordRequestRrXmlFormId?: string,
    public ordRequestRrPdfFormId?: string,
    public ordRequestRrApprFileDataSourcCd?: string,
    public ordRequestRrReportValueAmt?: number,
    public ordRequestRrAppraisalCompanyName?: string,
    public ordRequestRrvendorName?: string,
    public ordRequestRrvendorLicenseNbr?: string,
    public ordRequestRrvendorLicenseStateCd?: string,
    public ordValuationCompletedProduct?: string,
    public ordValuationDueToClientDt?: Moment,
    public ordValuationReportRecivedDt?: Moment,
    public ordValuationCompletionDt?: Moment,
    public ordValuationReportStatusCd?: string,
    public ordValuationAppraisedValueAmt?: number,
    public ordValuationBpoMarketValueAmt?: number,
    public ordValuationBpoAsIsAmt?: number,
    public ordValuationBpoAsRepairedValueAmt?: number,
    public ordValuationRedFlagCode?: string,
    public ordValuationRedFlagDesc?: string,
    public ordValuationAmcAppraisalId?: string,
    public ordValuationFinalReviewId?: number,
    public ordReviewRecommendedValueAmt?: number,
    public ordReviewDt?: Moment,
    public ordReviewRecommendedActionCd?: string,
    public ordReviewReviewerDecisionCd?: string,
    public ordReviewReviewScore?: string,
    public ordReviewFormUsed?: string,
    public ordReviewProviderOnWatchListInd?: boolean,
    public ordReviewCuredValueAmt?: number,
    public ordReviewFinalRecommendedActionCd?: string,
    public comments?: IOrderComments[],
    public documents?: IOrderDocuments[]
  ) {
    this.ordMultiOrderInd = this.ordMultiOrderInd || false;
    this.ordUcdpDataFnmaSubmitToUcdpInd = this.ordUcdpDataFnmaSubmitToUcdpInd || false;
    this.ordUcdpDataFhlmcSubmitToUcdpInd = this.ordUcdpDataFhlmcSubmitToUcdpInd || false;
    this.ordLoanBorrowerIsCoBorrowerInd = this.ordLoanBorrowerIsCoBorrowerInd || false;
    this.ordLoanDeedRestrictionInd = this.ordLoanDeedRestrictionInd || false;
    this.ordLoanHpmlInd = this.ordLoanHpmlInd || false;
    this.ordLoanIsNewConstructionInd = this.ordLoanIsNewConstructionInd || false;
    this.ordLoanNonSubjectPropertyInd = this.ordLoanNonSubjectPropertyInd || false;
    this.ordReviewProviderOnWatchListInd = this.ordReviewProviderOnWatchListInd || false;
  }
}
