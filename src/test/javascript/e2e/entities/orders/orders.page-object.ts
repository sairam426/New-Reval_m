import { element, by, ElementFinder } from 'protractor';

export class OrdersComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-orders div table .btn-danger'));
  title = element.all(by.css('jhi-orders div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class OrdersUpdatePage {
  pageTitle = element(by.id('jhi-orders-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  ordNumberInput = element(by.id('field_ordNumber'));
  ordStageCdInput = element(by.id('field_ordStageCd'));
  ordOrgOrderStatusCdInput = element(by.id('field_ordOrgOrderStatusCd'));
  ordCmpOrderStatusCdInput = element(by.id('field_ordCmpOrderStatusCd'));
  ordProOrderStatusCdInput = element(by.id('field_ordProOrderStatusCd'));
  ordCmpNbrInput = element(by.id('field_ordCmpNbr'));
  ordBrnNbrInput = element(by.id('field_ordBrnNbr'));
  ordProNbrInput = element(by.id('field_ordProNbr'));
  ordProductCodeInput = element(by.id('field_ordProductCode'));
  ordOriginalValueAmtInput = element(by.id('field_ordOriginalValueAmt'));
  ordParentTrackingNbrInput = element(by.id('field_ordParentTrackingNbr'));
  ordUserNbrInput = element(by.id('field_ordUserNbr'));
  ordOrderDtInput = element(by.id('field_ordOrderDt'));
  ordRushRequestDueDtInput = element(by.id('field_ordRushRequestDueDt'));
  ordOrgInstructionsInput = element(by.id('field_ordOrgInstructions'));
  ordOrgOtherInstructionsInput = element(by.id('field_ordOrgOtherInstructions'));
  ordMultiOrderIndInput = element(by.id('field_ordMultiOrderInd'));
  ordOrgDuedateInput = element(by.id('field_ordOrgDuedate'));
  ordProDuedateInput = element(by.id('field_ordProDuedate'));
  ordUcdpDataFnmaSubmitToUcdpIndInput = element(by.id('field_ordUcdpDataFnmaSubmitToUcdpInd'));
  ordUcdpDataFnmaServicerNbrInput = element(by.id('field_ordUcdpDataFnmaServicerNbr'));
  ordUcdpDataFhlmcSubmitToUcdpIndInput = element(by.id('field_ordUcdpDataFhlmcSubmitToUcdpInd'));
  ordUcdpDataFhlmcIdentificationNbrInput = element(by.id('field_ordUcdpDataFhlmcIdentificationNbr'));
  ordUcdpDataUcdpBussinessUnitNbrInput = element(by.id('field_ordUcdpDataUcdpBussinessUnitNbr'));
  ordUcdpSdFnamaDocumentIdInput = element(by.id('field_ordUcdpSdFnamaDocumentId'));
  ordUcdpSdFnmaSubmissionStatusIdInput = element(by.id('field_ordUcdpSdFnmaSubmissionStatusId'));
  ordUcdpSdFhlmcDocumentFileCdInput = element(by.id('field_ordUcdpSdFhlmcDocumentFileCd'));
  ordUcdpSdEadSubmissionStatusCdInput = element(by.id('field_ordUcdpSdEadSubmissionStatusCd'));
  ordUcdpFinalSubmissionDtInput = element(by.id('field_ordUcdpFinalSubmissionDt'));
  ordLoanNbrInput = element(by.id('field_ordLoanNbr'));
  ordloanOldLoanNbrInput = element(by.id('field_ordloanOldLoanNbr'));
  ordLoanProgramInput = element(by.id('field_ordLoanProgram'));
  ordLoanPurposeInput = element(by.id('field_ordLoanPurpose'));
  ordLoanTypeInput = element(by.id('field_ordLoanType'));
  ordLoanAmtInput = element(by.id('field_ordLoanAmt'));
  ordLoanQualifyingValueAmtInput = element(by.id('field_ordLoanQualifyingValueAmt'));
  ordLoanSalesPriceAmtInput = element(by.id('field_ordLoanSalesPriceAmt'));
  ordLoanBorrowerLastNameInput = element(by.id('field_ordLoanBorrowerLastName'));
  ordLoanBorrowerFirstNameInput = element(by.id('field_ordLoanBorrowerFirstName'));
  ordLoanBorrowerMiddleNameInput = element(by.id('field_ordLoanBorrowerMiddleName'));
  ordLoanBorrowerIsCoBorrowerIndInput = element(by.id('field_ordLoanBorrowerIsCoBorrowerInd'));
  ordLoanFhaCaseNbrInput = element(by.id('field_ordLoanFhaCaseNbr'));
  ordLoanDeedRestrictionIndInput = element(by.id('field_ordLoanDeedRestrictionInd'));
  ordLoanEstimatedCloseDtInput = element(by.id('field_ordLoanEstimatedCloseDt'));
  ordLoanHpmlIndInput = element(by.id('field_ordLoanHpmlInd'));
  ordLoanIsNewConstructionIndInput = element(by.id('field_ordLoanIsNewConstructionInd'));
  ordLoanCustomerSegmentCodeInput = element(by.id('field_ordLoanCustomerSegmentCode'));
  ordLoanNonSubjectPropertyIndInput = element(by.id('field_ordLoanNonSubjectPropertyInd'));
  ordLoanBorrowerRequestedCloseDtInput = element(by.id('field_ordLoanBorrowerRequestedCloseDt'));
  ordPropertyTypeCdInput = element(by.id('field_ordPropertyTypeCd'));
  ordPropertyAddress1Input = element(by.id('field_ordPropertyAddress1'));
  ordPropertyAddress2Input = element(by.id('field_ordPropertyAddress2'));
  ordPropertyCityInput = element(by.id('field_ordPropertyCity'));
  ordPropertyStateCdInput = element(by.id('field_ordPropertyStateCd'));
  ordPropertyZipInput = element(by.id('field_ordPropertyZip'));
  ordPropertyCountyInput = element(by.id('field_ordPropertyCounty'));
  ordPropertyLatitueInput = element(by.id('field_ordPropertyLatitue'));
  ordPropertyLongitudeInput = element(by.id('field_ordPropertyLongitude'));
  ordPropertyProjectNameInput = element(by.id('field_ordPropertyProjectName'));
  ordContact1TypeCdInput = element(by.id('field_ordContact1TypeCd'));
  ordContact1NameInput = element(by.id('field_ordContact1Name'));
  ordContact1WorkPhoneInput = element(by.id('field_ordContact1WorkPhone'));
  ordContact1HomePhoneInput = element(by.id('field_ordContact1HomePhone'));
  ordContact1CellPhoneInput = element(by.id('field_ordContact1CellPhone'));
  ordContact1OtherCellPhoneInput = element(by.id('field_ordContact1OtherCellPhone'));
  ordContact1EmailInput = element(by.id('field_ordContact1Email'));
  ordContact1OtherEmailInput = element(by.id('field_ordContact1OtherEmail'));
  ordContact2TypeCdInput = element(by.id('field_ordContact2TypeCd'));
  ordContact2NameInput = element(by.id('field_ordContact2Name'));
  ordContact2WorkPhoneInput = element(by.id('field_ordContact2WorkPhone'));
  ordContact2HomePhoneInput = element(by.id('field_ordContact2HomePhone'));
  ordContact2CellPhoneInput = element(by.id('field_ordContact2CellPhone'));
  ordContact2OtherCellPhoneInput = element(by.id('field_ordContact2OtherCellPhone'));
  ordContact2EmailInput = element(by.id('field_ordContact2Email'));
  ordContact2OtherEmailInput = element(by.id('field_ordContact2OtherEmail'));
  ordContact3TypeCdInput = element(by.id('field_ordContact3TypeCd'));
  ordContact3NameInput = element(by.id('field_ordContact3Name'));
  ordContact3WorkPhoneInput = element(by.id('field_ordContact3WorkPhone'));
  ordContact3HomePhoneInput = element(by.id('field_ordContact3HomePhone'));
  ordContact3CellPhoneInput = element(by.id('field_ordContact3CellPhone'));
  ordContact3OtherCellPhoneInput = element(by.id('field_ordContact3OtherCellPhone'));
  ordContact3EmailInput = element(by.id('field_ordContact3Email'));
  ordContact3OtherEmailInput = element(by.id('field_ordContact3OtherEmail'));
  ordRequestRrStatusCdInput = element(by.id('field_ordRequestRrStatusCd'));
  ordRequestRrVendorAppraisalIdInput = element(by.id('field_ordRequestRrVendorAppraisalId'));
  ordRequestRrvendorAppraisalDraftRcvdDtInput = element(by.id('field_ordRequestRrvendorAppraisalDraftRcvdDt'));
  ordRequestRrXmlFormIdInput = element(by.id('field_ordRequestRrXmlFormId'));
  ordRequestRrPdfFormIdInput = element(by.id('field_ordRequestRrPdfFormId'));
  ordRequestRrApprFileDataSourcCdInput = element(by.id('field_ordRequestRrApprFileDataSourcCd'));
  ordRequestRrReportValueAmtInput = element(by.id('field_ordRequestRrReportValueAmt'));
  ordRequestRrAppraisalCompanyNameInput = element(by.id('field_ordRequestRrAppraisalCompanyName'));
  ordRequestRrvendorNameInput = element(by.id('field_ordRequestRrvendorName'));
  ordRequestRrvendorLicenseNbrInput = element(by.id('field_ordRequestRrvendorLicenseNbr'));
  ordRequestRrvendorLicenseStateCdInput = element(by.id('field_ordRequestRrvendorLicenseStateCd'));
  ordValuationCompletedProductInput = element(by.id('field_ordValuationCompletedProduct'));
  ordValuationDueToClientDtInput = element(by.id('field_ordValuationDueToClientDt'));
  ordValuationReportRecivedDtInput = element(by.id('field_ordValuationReportRecivedDt'));
  ordValuationCompletionDtInput = element(by.id('field_ordValuationCompletionDt'));
  ordValuationReportStatusCdInput = element(by.id('field_ordValuationReportStatusCd'));
  ordValuationAppraisedValueAmtInput = element(by.id('field_ordValuationAppraisedValueAmt'));
  ordValuationBpoMarketValueAmtInput = element(by.id('field_ordValuationBpoMarketValueAmt'));
  ordValuationBpoAsIsAmtInput = element(by.id('field_ordValuationBpoAsIsAmt'));
  ordValuationBpoAsRepairedValueAmtInput = element(by.id('field_ordValuationBpoAsRepairedValueAmt'));
  ordValuationRedFlagCodeInput = element(by.id('field_ordValuationRedFlagCode'));
  ordValuationRedFlagDescInput = element(by.id('field_ordValuationRedFlagDesc'));
  ordValuationAmcAppraisalIdInput = element(by.id('field_ordValuationAmcAppraisalId'));
  ordValuationFinalReviewIdInput = element(by.id('field_ordValuationFinalReviewId'));
  ordReviewRecommendedValueAmtInput = element(by.id('field_ordReviewRecommendedValueAmt'));
  ordReviewDtInput = element(by.id('field_ordReviewDt'));
  ordReviewRecommendedActionCdInput = element(by.id('field_ordReviewRecommendedActionCd'));
  ordReviewReviewerDecisionCdInput = element(by.id('field_ordReviewReviewerDecisionCd'));
  ordReviewReviewScoreInput = element(by.id('field_ordReviewReviewScore'));
  ordReviewFormUsedInput = element(by.id('field_ordReviewFormUsed'));
  ordReviewProviderOnWatchListIndInput = element(by.id('field_ordReviewProviderOnWatchListInd'));
  ordReviewCuredValueAmtInput = element(by.id('field_ordReviewCuredValueAmt'));
  ordReviewFinalRecommendedActionCdInput = element(by.id('field_ordReviewFinalRecommendedActionCd'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrdNumberInput(ordNumber: string): Promise<void> {
    await this.ordNumberInput.sendKeys(ordNumber);
  }

  async getOrdNumberInput(): Promise<string> {
    return await this.ordNumberInput.getAttribute('value');
  }

  async setOrdStageCdInput(ordStageCd: string): Promise<void> {
    await this.ordStageCdInput.sendKeys(ordStageCd);
  }

  async getOrdStageCdInput(): Promise<string> {
    return await this.ordStageCdInput.getAttribute('value');
  }

  async setOrdOrgOrderStatusCdInput(ordOrgOrderStatusCd: string): Promise<void> {
    await this.ordOrgOrderStatusCdInput.sendKeys(ordOrgOrderStatusCd);
  }

  async getOrdOrgOrderStatusCdInput(): Promise<string> {
    return await this.ordOrgOrderStatusCdInput.getAttribute('value');
  }

  async setOrdCmpOrderStatusCdInput(ordCmpOrderStatusCd: string): Promise<void> {
    await this.ordCmpOrderStatusCdInput.sendKeys(ordCmpOrderStatusCd);
  }

  async getOrdCmpOrderStatusCdInput(): Promise<string> {
    return await this.ordCmpOrderStatusCdInput.getAttribute('value');
  }

  async setOrdProOrderStatusCdInput(ordProOrderStatusCd: string): Promise<void> {
    await this.ordProOrderStatusCdInput.sendKeys(ordProOrderStatusCd);
  }

  async getOrdProOrderStatusCdInput(): Promise<string> {
    return await this.ordProOrderStatusCdInput.getAttribute('value');
  }

  async setOrdCmpNbrInput(ordCmpNbr: string): Promise<void> {
    await this.ordCmpNbrInput.sendKeys(ordCmpNbr);
  }

  async getOrdCmpNbrInput(): Promise<string> {
    return await this.ordCmpNbrInput.getAttribute('value');
  }

  async setOrdBrnNbrInput(ordBrnNbr: string): Promise<void> {
    await this.ordBrnNbrInput.sendKeys(ordBrnNbr);
  }

  async getOrdBrnNbrInput(): Promise<string> {
    return await this.ordBrnNbrInput.getAttribute('value');
  }

  async setOrdProNbrInput(ordProNbr: string): Promise<void> {
    await this.ordProNbrInput.sendKeys(ordProNbr);
  }

  async getOrdProNbrInput(): Promise<string> {
    return await this.ordProNbrInput.getAttribute('value');
  }

  async setOrdProductCodeInput(ordProductCode: string): Promise<void> {
    await this.ordProductCodeInput.sendKeys(ordProductCode);
  }

  async getOrdProductCodeInput(): Promise<string> {
    return await this.ordProductCodeInput.getAttribute('value');
  }

  async setOrdOriginalValueAmtInput(ordOriginalValueAmt: string): Promise<void> {
    await this.ordOriginalValueAmtInput.sendKeys(ordOriginalValueAmt);
  }

  async getOrdOriginalValueAmtInput(): Promise<string> {
    return await this.ordOriginalValueAmtInput.getAttribute('value');
  }

  async setOrdParentTrackingNbrInput(ordParentTrackingNbr: string): Promise<void> {
    await this.ordParentTrackingNbrInput.sendKeys(ordParentTrackingNbr);
  }

  async getOrdParentTrackingNbrInput(): Promise<string> {
    return await this.ordParentTrackingNbrInput.getAttribute('value');
  }

  async setOrdUserNbrInput(ordUserNbr: string): Promise<void> {
    await this.ordUserNbrInput.sendKeys(ordUserNbr);
  }

  async getOrdUserNbrInput(): Promise<string> {
    return await this.ordUserNbrInput.getAttribute('value');
  }

  async setOrdOrderDtInput(ordOrderDt: string): Promise<void> {
    await this.ordOrderDtInput.sendKeys(ordOrderDt);
  }

  async getOrdOrderDtInput(): Promise<string> {
    return await this.ordOrderDtInput.getAttribute('value');
  }

  async setOrdRushRequestDueDtInput(ordRushRequestDueDt: string): Promise<void> {
    await this.ordRushRequestDueDtInput.sendKeys(ordRushRequestDueDt);
  }

  async getOrdRushRequestDueDtInput(): Promise<string> {
    return await this.ordRushRequestDueDtInput.getAttribute('value');
  }

  async setOrdOrgInstructionsInput(ordOrgInstructions: string): Promise<void> {
    await this.ordOrgInstructionsInput.sendKeys(ordOrgInstructions);
  }

  async getOrdOrgInstructionsInput(): Promise<string> {
    return await this.ordOrgInstructionsInput.getAttribute('value');
  }

  async setOrdOrgOtherInstructionsInput(ordOrgOtherInstructions: string): Promise<void> {
    await this.ordOrgOtherInstructionsInput.sendKeys(ordOrgOtherInstructions);
  }

  async getOrdOrgOtherInstructionsInput(): Promise<string> {
    return await this.ordOrgOtherInstructionsInput.getAttribute('value');
  }

  getOrdMultiOrderIndInput(): ElementFinder {
    return this.ordMultiOrderIndInput;
  }

  async setOrdOrgDuedateInput(ordOrgDuedate: string): Promise<void> {
    await this.ordOrgDuedateInput.sendKeys(ordOrgDuedate);
  }

  async getOrdOrgDuedateInput(): Promise<string> {
    return await this.ordOrgDuedateInput.getAttribute('value');
  }

  async setOrdProDuedateInput(ordProDuedate: string): Promise<void> {
    await this.ordProDuedateInput.sendKeys(ordProDuedate);
  }

  async getOrdProDuedateInput(): Promise<string> {
    return await this.ordProDuedateInput.getAttribute('value');
  }

  getOrdUcdpDataFnmaSubmitToUcdpIndInput(): ElementFinder {
    return this.ordUcdpDataFnmaSubmitToUcdpIndInput;
  }

  async setOrdUcdpDataFnmaServicerNbrInput(ordUcdpDataFnmaServicerNbr: string): Promise<void> {
    await this.ordUcdpDataFnmaServicerNbrInput.sendKeys(ordUcdpDataFnmaServicerNbr);
  }

  async getOrdUcdpDataFnmaServicerNbrInput(): Promise<string> {
    return await this.ordUcdpDataFnmaServicerNbrInput.getAttribute('value');
  }

  getOrdUcdpDataFhlmcSubmitToUcdpIndInput(): ElementFinder {
    return this.ordUcdpDataFhlmcSubmitToUcdpIndInput;
  }

  async setOrdUcdpDataFhlmcIdentificationNbrInput(ordUcdpDataFhlmcIdentificationNbr: string): Promise<void> {
    await this.ordUcdpDataFhlmcIdentificationNbrInput.sendKeys(ordUcdpDataFhlmcIdentificationNbr);
  }

  async getOrdUcdpDataFhlmcIdentificationNbrInput(): Promise<string> {
    return await this.ordUcdpDataFhlmcIdentificationNbrInput.getAttribute('value');
  }

  async setOrdUcdpDataUcdpBussinessUnitNbrInput(ordUcdpDataUcdpBussinessUnitNbr: string): Promise<void> {
    await this.ordUcdpDataUcdpBussinessUnitNbrInput.sendKeys(ordUcdpDataUcdpBussinessUnitNbr);
  }

  async getOrdUcdpDataUcdpBussinessUnitNbrInput(): Promise<string> {
    return await this.ordUcdpDataUcdpBussinessUnitNbrInput.getAttribute('value');
  }

  async setOrdUcdpSdFnamaDocumentIdInput(ordUcdpSdFnamaDocumentId: string): Promise<void> {
    await this.ordUcdpSdFnamaDocumentIdInput.sendKeys(ordUcdpSdFnamaDocumentId);
  }

  async getOrdUcdpSdFnamaDocumentIdInput(): Promise<string> {
    return await this.ordUcdpSdFnamaDocumentIdInput.getAttribute('value');
  }

  async setOrdUcdpSdFnmaSubmissionStatusIdInput(ordUcdpSdFnmaSubmissionStatusId: string): Promise<void> {
    await this.ordUcdpSdFnmaSubmissionStatusIdInput.sendKeys(ordUcdpSdFnmaSubmissionStatusId);
  }

  async getOrdUcdpSdFnmaSubmissionStatusIdInput(): Promise<string> {
    return await this.ordUcdpSdFnmaSubmissionStatusIdInput.getAttribute('value');
  }

  async setOrdUcdpSdFhlmcDocumentFileCdInput(ordUcdpSdFhlmcDocumentFileCd: string): Promise<void> {
    await this.ordUcdpSdFhlmcDocumentFileCdInput.sendKeys(ordUcdpSdFhlmcDocumentFileCd);
  }

  async getOrdUcdpSdFhlmcDocumentFileCdInput(): Promise<string> {
    return await this.ordUcdpSdFhlmcDocumentFileCdInput.getAttribute('value');
  }

  async setOrdUcdpSdEadSubmissionStatusCdInput(ordUcdpSdEadSubmissionStatusCd: string): Promise<void> {
    await this.ordUcdpSdEadSubmissionStatusCdInput.sendKeys(ordUcdpSdEadSubmissionStatusCd);
  }

  async getOrdUcdpSdEadSubmissionStatusCdInput(): Promise<string> {
    return await this.ordUcdpSdEadSubmissionStatusCdInput.getAttribute('value');
  }

  async setOrdUcdpFinalSubmissionDtInput(ordUcdpFinalSubmissionDt: string): Promise<void> {
    await this.ordUcdpFinalSubmissionDtInput.sendKeys(ordUcdpFinalSubmissionDt);
  }

  async getOrdUcdpFinalSubmissionDtInput(): Promise<string> {
    return await this.ordUcdpFinalSubmissionDtInput.getAttribute('value');
  }

  async setOrdLoanNbrInput(ordLoanNbr: string): Promise<void> {
    await this.ordLoanNbrInput.sendKeys(ordLoanNbr);
  }

  async getOrdLoanNbrInput(): Promise<string> {
    return await this.ordLoanNbrInput.getAttribute('value');
  }

  async setOrdloanOldLoanNbrInput(ordloanOldLoanNbr: string): Promise<void> {
    await this.ordloanOldLoanNbrInput.sendKeys(ordloanOldLoanNbr);
  }

  async getOrdloanOldLoanNbrInput(): Promise<string> {
    return await this.ordloanOldLoanNbrInput.getAttribute('value');
  }

  async setOrdLoanProgramInput(ordLoanProgram: string): Promise<void> {
    await this.ordLoanProgramInput.sendKeys(ordLoanProgram);
  }

  async getOrdLoanProgramInput(): Promise<string> {
    return await this.ordLoanProgramInput.getAttribute('value');
  }

  async setOrdLoanPurposeInput(ordLoanPurpose: string): Promise<void> {
    await this.ordLoanPurposeInput.sendKeys(ordLoanPurpose);
  }

  async getOrdLoanPurposeInput(): Promise<string> {
    return await this.ordLoanPurposeInput.getAttribute('value');
  }

  async setOrdLoanTypeInput(ordLoanType: string): Promise<void> {
    await this.ordLoanTypeInput.sendKeys(ordLoanType);
  }

  async getOrdLoanTypeInput(): Promise<string> {
    return await this.ordLoanTypeInput.getAttribute('value');
  }

  async setOrdLoanAmtInput(ordLoanAmt: string): Promise<void> {
    await this.ordLoanAmtInput.sendKeys(ordLoanAmt);
  }

  async getOrdLoanAmtInput(): Promise<string> {
    return await this.ordLoanAmtInput.getAttribute('value');
  }

  async setOrdLoanQualifyingValueAmtInput(ordLoanQualifyingValueAmt: string): Promise<void> {
    await this.ordLoanQualifyingValueAmtInput.sendKeys(ordLoanQualifyingValueAmt);
  }

  async getOrdLoanQualifyingValueAmtInput(): Promise<string> {
    return await this.ordLoanQualifyingValueAmtInput.getAttribute('value');
  }

  async setOrdLoanSalesPriceAmtInput(ordLoanSalesPriceAmt: string): Promise<void> {
    await this.ordLoanSalesPriceAmtInput.sendKeys(ordLoanSalesPriceAmt);
  }

  async getOrdLoanSalesPriceAmtInput(): Promise<string> {
    return await this.ordLoanSalesPriceAmtInput.getAttribute('value');
  }

  async setOrdLoanBorrowerLastNameInput(ordLoanBorrowerLastName: string): Promise<void> {
    await this.ordLoanBorrowerLastNameInput.sendKeys(ordLoanBorrowerLastName);
  }

  async getOrdLoanBorrowerLastNameInput(): Promise<string> {
    return await this.ordLoanBorrowerLastNameInput.getAttribute('value');
  }

  async setOrdLoanBorrowerFirstNameInput(ordLoanBorrowerFirstName: string): Promise<void> {
    await this.ordLoanBorrowerFirstNameInput.sendKeys(ordLoanBorrowerFirstName);
  }

  async getOrdLoanBorrowerFirstNameInput(): Promise<string> {
    return await this.ordLoanBorrowerFirstNameInput.getAttribute('value');
  }

  async setOrdLoanBorrowerMiddleNameInput(ordLoanBorrowerMiddleName: string): Promise<void> {
    await this.ordLoanBorrowerMiddleNameInput.sendKeys(ordLoanBorrowerMiddleName);
  }

  async getOrdLoanBorrowerMiddleNameInput(): Promise<string> {
    return await this.ordLoanBorrowerMiddleNameInput.getAttribute('value');
  }

  getOrdLoanBorrowerIsCoBorrowerIndInput(): ElementFinder {
    return this.ordLoanBorrowerIsCoBorrowerIndInput;
  }

  async setOrdLoanFhaCaseNbrInput(ordLoanFhaCaseNbr: string): Promise<void> {
    await this.ordLoanFhaCaseNbrInput.sendKeys(ordLoanFhaCaseNbr);
  }

  async getOrdLoanFhaCaseNbrInput(): Promise<string> {
    return await this.ordLoanFhaCaseNbrInput.getAttribute('value');
  }

  getOrdLoanDeedRestrictionIndInput(): ElementFinder {
    return this.ordLoanDeedRestrictionIndInput;
  }

  async setOrdLoanEstimatedCloseDtInput(ordLoanEstimatedCloseDt: string): Promise<void> {
    await this.ordLoanEstimatedCloseDtInput.sendKeys(ordLoanEstimatedCloseDt);
  }

  async getOrdLoanEstimatedCloseDtInput(): Promise<string> {
    return await this.ordLoanEstimatedCloseDtInput.getAttribute('value');
  }

  getOrdLoanHpmlIndInput(): ElementFinder {
    return this.ordLoanHpmlIndInput;
  }

  getOrdLoanIsNewConstructionIndInput(): ElementFinder {
    return this.ordLoanIsNewConstructionIndInput;
  }

  async setOrdLoanCustomerSegmentCodeInput(ordLoanCustomerSegmentCode: string): Promise<void> {
    await this.ordLoanCustomerSegmentCodeInput.sendKeys(ordLoanCustomerSegmentCode);
  }

  async getOrdLoanCustomerSegmentCodeInput(): Promise<string> {
    return await this.ordLoanCustomerSegmentCodeInput.getAttribute('value');
  }

  getOrdLoanNonSubjectPropertyIndInput(): ElementFinder {
    return this.ordLoanNonSubjectPropertyIndInput;
  }

  async setOrdLoanBorrowerRequestedCloseDtInput(ordLoanBorrowerRequestedCloseDt: string): Promise<void> {
    await this.ordLoanBorrowerRequestedCloseDtInput.sendKeys(ordLoanBorrowerRequestedCloseDt);
  }

  async getOrdLoanBorrowerRequestedCloseDtInput(): Promise<string> {
    return await this.ordLoanBorrowerRequestedCloseDtInput.getAttribute('value');
  }

  async setOrdPropertyTypeCdInput(ordPropertyTypeCd: string): Promise<void> {
    await this.ordPropertyTypeCdInput.sendKeys(ordPropertyTypeCd);
  }

  async getOrdPropertyTypeCdInput(): Promise<string> {
    return await this.ordPropertyTypeCdInput.getAttribute('value');
  }

  async setOrdPropertyAddress1Input(ordPropertyAddress1: string): Promise<void> {
    await this.ordPropertyAddress1Input.sendKeys(ordPropertyAddress1);
  }

  async getOrdPropertyAddress1Input(): Promise<string> {
    return await this.ordPropertyAddress1Input.getAttribute('value');
  }

  async setOrdPropertyAddress2Input(ordPropertyAddress2: string): Promise<void> {
    await this.ordPropertyAddress2Input.sendKeys(ordPropertyAddress2);
  }

  async getOrdPropertyAddress2Input(): Promise<string> {
    return await this.ordPropertyAddress2Input.getAttribute('value');
  }

  async setOrdPropertyCityInput(ordPropertyCity: string): Promise<void> {
    await this.ordPropertyCityInput.sendKeys(ordPropertyCity);
  }

  async getOrdPropertyCityInput(): Promise<string> {
    return await this.ordPropertyCityInput.getAttribute('value');
  }

  async setOrdPropertyStateCdInput(ordPropertyStateCd: string): Promise<void> {
    await this.ordPropertyStateCdInput.sendKeys(ordPropertyStateCd);
  }

  async getOrdPropertyStateCdInput(): Promise<string> {
    return await this.ordPropertyStateCdInput.getAttribute('value');
  }

  async setOrdPropertyZipInput(ordPropertyZip: string): Promise<void> {
    await this.ordPropertyZipInput.sendKeys(ordPropertyZip);
  }

  async getOrdPropertyZipInput(): Promise<string> {
    return await this.ordPropertyZipInput.getAttribute('value');
  }

  async setOrdPropertyCountyInput(ordPropertyCounty: string): Promise<void> {
    await this.ordPropertyCountyInput.sendKeys(ordPropertyCounty);
  }

  async getOrdPropertyCountyInput(): Promise<string> {
    return await this.ordPropertyCountyInput.getAttribute('value');
  }

  async setOrdPropertyLatitueInput(ordPropertyLatitue: string): Promise<void> {
    await this.ordPropertyLatitueInput.sendKeys(ordPropertyLatitue);
  }

  async getOrdPropertyLatitueInput(): Promise<string> {
    return await this.ordPropertyLatitueInput.getAttribute('value');
  }

  async setOrdPropertyLongitudeInput(ordPropertyLongitude: string): Promise<void> {
    await this.ordPropertyLongitudeInput.sendKeys(ordPropertyLongitude);
  }

  async getOrdPropertyLongitudeInput(): Promise<string> {
    return await this.ordPropertyLongitudeInput.getAttribute('value');
  }

  async setOrdPropertyProjectNameInput(ordPropertyProjectName: string): Promise<void> {
    await this.ordPropertyProjectNameInput.sendKeys(ordPropertyProjectName);
  }

  async getOrdPropertyProjectNameInput(): Promise<string> {
    return await this.ordPropertyProjectNameInput.getAttribute('value');
  }

  async setOrdContact1TypeCdInput(ordContact1TypeCd: string): Promise<void> {
    await this.ordContact1TypeCdInput.sendKeys(ordContact1TypeCd);
  }

  async getOrdContact1TypeCdInput(): Promise<string> {
    return await this.ordContact1TypeCdInput.getAttribute('value');
  }

  async setOrdContact1NameInput(ordContact1Name: string): Promise<void> {
    await this.ordContact1NameInput.sendKeys(ordContact1Name);
  }

  async getOrdContact1NameInput(): Promise<string> {
    return await this.ordContact1NameInput.getAttribute('value');
  }

  async setOrdContact1WorkPhoneInput(ordContact1WorkPhone: string): Promise<void> {
    await this.ordContact1WorkPhoneInput.sendKeys(ordContact1WorkPhone);
  }

  async getOrdContact1WorkPhoneInput(): Promise<string> {
    return await this.ordContact1WorkPhoneInput.getAttribute('value');
  }

  async setOrdContact1HomePhoneInput(ordContact1HomePhone: string): Promise<void> {
    await this.ordContact1HomePhoneInput.sendKeys(ordContact1HomePhone);
  }

  async getOrdContact1HomePhoneInput(): Promise<string> {
    return await this.ordContact1HomePhoneInput.getAttribute('value');
  }

  async setOrdContact1CellPhoneInput(ordContact1CellPhone: string): Promise<void> {
    await this.ordContact1CellPhoneInput.sendKeys(ordContact1CellPhone);
  }

  async getOrdContact1CellPhoneInput(): Promise<string> {
    return await this.ordContact1CellPhoneInput.getAttribute('value');
  }

  async setOrdContact1OtherCellPhoneInput(ordContact1OtherCellPhone: string): Promise<void> {
    await this.ordContact1OtherCellPhoneInput.sendKeys(ordContact1OtherCellPhone);
  }

  async getOrdContact1OtherCellPhoneInput(): Promise<string> {
    return await this.ordContact1OtherCellPhoneInput.getAttribute('value');
  }

  async setOrdContact1EmailInput(ordContact1Email: string): Promise<void> {
    await this.ordContact1EmailInput.sendKeys(ordContact1Email);
  }

  async getOrdContact1EmailInput(): Promise<string> {
    return await this.ordContact1EmailInput.getAttribute('value');
  }

  async setOrdContact1OtherEmailInput(ordContact1OtherEmail: string): Promise<void> {
    await this.ordContact1OtherEmailInput.sendKeys(ordContact1OtherEmail);
  }

  async getOrdContact1OtherEmailInput(): Promise<string> {
    return await this.ordContact1OtherEmailInput.getAttribute('value');
  }

  async setOrdContact2TypeCdInput(ordContact2TypeCd: string): Promise<void> {
    await this.ordContact2TypeCdInput.sendKeys(ordContact2TypeCd);
  }

  async getOrdContact2TypeCdInput(): Promise<string> {
    return await this.ordContact2TypeCdInput.getAttribute('value');
  }

  async setOrdContact2NameInput(ordContact2Name: string): Promise<void> {
    await this.ordContact2NameInput.sendKeys(ordContact2Name);
  }

  async getOrdContact2NameInput(): Promise<string> {
    return await this.ordContact2NameInput.getAttribute('value');
  }

  async setOrdContact2WorkPhoneInput(ordContact2WorkPhone: string): Promise<void> {
    await this.ordContact2WorkPhoneInput.sendKeys(ordContact2WorkPhone);
  }

  async getOrdContact2WorkPhoneInput(): Promise<string> {
    return await this.ordContact2WorkPhoneInput.getAttribute('value');
  }

  async setOrdContact2HomePhoneInput(ordContact2HomePhone: string): Promise<void> {
    await this.ordContact2HomePhoneInput.sendKeys(ordContact2HomePhone);
  }

  async getOrdContact2HomePhoneInput(): Promise<string> {
    return await this.ordContact2HomePhoneInput.getAttribute('value');
  }

  async setOrdContact2CellPhoneInput(ordContact2CellPhone: string): Promise<void> {
    await this.ordContact2CellPhoneInput.sendKeys(ordContact2CellPhone);
  }

  async getOrdContact2CellPhoneInput(): Promise<string> {
    return await this.ordContact2CellPhoneInput.getAttribute('value');
  }

  async setOrdContact2OtherCellPhoneInput(ordContact2OtherCellPhone: string): Promise<void> {
    await this.ordContact2OtherCellPhoneInput.sendKeys(ordContact2OtherCellPhone);
  }

  async getOrdContact2OtherCellPhoneInput(): Promise<string> {
    return await this.ordContact2OtherCellPhoneInput.getAttribute('value');
  }

  async setOrdContact2EmailInput(ordContact2Email: string): Promise<void> {
    await this.ordContact2EmailInput.sendKeys(ordContact2Email);
  }

  async getOrdContact2EmailInput(): Promise<string> {
    return await this.ordContact2EmailInput.getAttribute('value');
  }

  async setOrdContact2OtherEmailInput(ordContact2OtherEmail: string): Promise<void> {
    await this.ordContact2OtherEmailInput.sendKeys(ordContact2OtherEmail);
  }

  async getOrdContact2OtherEmailInput(): Promise<string> {
    return await this.ordContact2OtherEmailInput.getAttribute('value');
  }

  async setOrdContact3TypeCdInput(ordContact3TypeCd: string): Promise<void> {
    await this.ordContact3TypeCdInput.sendKeys(ordContact3TypeCd);
  }

  async getOrdContact3TypeCdInput(): Promise<string> {
    return await this.ordContact3TypeCdInput.getAttribute('value');
  }

  async setOrdContact3NameInput(ordContact3Name: string): Promise<void> {
    await this.ordContact3NameInput.sendKeys(ordContact3Name);
  }

  async getOrdContact3NameInput(): Promise<string> {
    return await this.ordContact3NameInput.getAttribute('value');
  }

  async setOrdContact3WorkPhoneInput(ordContact3WorkPhone: string): Promise<void> {
    await this.ordContact3WorkPhoneInput.sendKeys(ordContact3WorkPhone);
  }

  async getOrdContact3WorkPhoneInput(): Promise<string> {
    return await this.ordContact3WorkPhoneInput.getAttribute('value');
  }

  async setOrdContact3HomePhoneInput(ordContact3HomePhone: string): Promise<void> {
    await this.ordContact3HomePhoneInput.sendKeys(ordContact3HomePhone);
  }

  async getOrdContact3HomePhoneInput(): Promise<string> {
    return await this.ordContact3HomePhoneInput.getAttribute('value');
  }

  async setOrdContact3CellPhoneInput(ordContact3CellPhone: string): Promise<void> {
    await this.ordContact3CellPhoneInput.sendKeys(ordContact3CellPhone);
  }

  async getOrdContact3CellPhoneInput(): Promise<string> {
    return await this.ordContact3CellPhoneInput.getAttribute('value');
  }

  async setOrdContact3OtherCellPhoneInput(ordContact3OtherCellPhone: string): Promise<void> {
    await this.ordContact3OtherCellPhoneInput.sendKeys(ordContact3OtherCellPhone);
  }

  async getOrdContact3OtherCellPhoneInput(): Promise<string> {
    return await this.ordContact3OtherCellPhoneInput.getAttribute('value');
  }

  async setOrdContact3EmailInput(ordContact3Email: string): Promise<void> {
    await this.ordContact3EmailInput.sendKeys(ordContact3Email);
  }

  async getOrdContact3EmailInput(): Promise<string> {
    return await this.ordContact3EmailInput.getAttribute('value');
  }

  async setOrdContact3OtherEmailInput(ordContact3OtherEmail: string): Promise<void> {
    await this.ordContact3OtherEmailInput.sendKeys(ordContact3OtherEmail);
  }

  async getOrdContact3OtherEmailInput(): Promise<string> {
    return await this.ordContact3OtherEmailInput.getAttribute('value');
  }

  async setOrdRequestRrStatusCdInput(ordRequestRrStatusCd: string): Promise<void> {
    await this.ordRequestRrStatusCdInput.sendKeys(ordRequestRrStatusCd);
  }

  async getOrdRequestRrStatusCdInput(): Promise<string> {
    return await this.ordRequestRrStatusCdInput.getAttribute('value');
  }

  async setOrdRequestRrVendorAppraisalIdInput(ordRequestRrVendorAppraisalId: string): Promise<void> {
    await this.ordRequestRrVendorAppraisalIdInput.sendKeys(ordRequestRrVendorAppraisalId);
  }

  async getOrdRequestRrVendorAppraisalIdInput(): Promise<string> {
    return await this.ordRequestRrVendorAppraisalIdInput.getAttribute('value');
  }

  async setOrdRequestRrvendorAppraisalDraftRcvdDtInput(ordRequestRrvendorAppraisalDraftRcvdDt: string): Promise<void> {
    await this.ordRequestRrvendorAppraisalDraftRcvdDtInput.sendKeys(ordRequestRrvendorAppraisalDraftRcvdDt);
  }

  async getOrdRequestRrvendorAppraisalDraftRcvdDtInput(): Promise<string> {
    return await this.ordRequestRrvendorAppraisalDraftRcvdDtInput.getAttribute('value');
  }

  async setOrdRequestRrXmlFormIdInput(ordRequestRrXmlFormId: string): Promise<void> {
    await this.ordRequestRrXmlFormIdInput.sendKeys(ordRequestRrXmlFormId);
  }

  async getOrdRequestRrXmlFormIdInput(): Promise<string> {
    return await this.ordRequestRrXmlFormIdInput.getAttribute('value');
  }

  async setOrdRequestRrPdfFormIdInput(ordRequestRrPdfFormId: string): Promise<void> {
    await this.ordRequestRrPdfFormIdInput.sendKeys(ordRequestRrPdfFormId);
  }

  async getOrdRequestRrPdfFormIdInput(): Promise<string> {
    return await this.ordRequestRrPdfFormIdInput.getAttribute('value');
  }

  async setOrdRequestRrApprFileDataSourcCdInput(ordRequestRrApprFileDataSourcCd: string): Promise<void> {
    await this.ordRequestRrApprFileDataSourcCdInput.sendKeys(ordRequestRrApprFileDataSourcCd);
  }

  async getOrdRequestRrApprFileDataSourcCdInput(): Promise<string> {
    return await this.ordRequestRrApprFileDataSourcCdInput.getAttribute('value');
  }

  async setOrdRequestRrReportValueAmtInput(ordRequestRrReportValueAmt: string): Promise<void> {
    await this.ordRequestRrReportValueAmtInput.sendKeys(ordRequestRrReportValueAmt);
  }

  async getOrdRequestRrReportValueAmtInput(): Promise<string> {
    return await this.ordRequestRrReportValueAmtInput.getAttribute('value');
  }

  async setOrdRequestRrAppraisalCompanyNameInput(ordRequestRrAppraisalCompanyName: string): Promise<void> {
    await this.ordRequestRrAppraisalCompanyNameInput.sendKeys(ordRequestRrAppraisalCompanyName);
  }

  async getOrdRequestRrAppraisalCompanyNameInput(): Promise<string> {
    return await this.ordRequestRrAppraisalCompanyNameInput.getAttribute('value');
  }

  async setOrdRequestRrvendorNameInput(ordRequestRrvendorName: string): Promise<void> {
    await this.ordRequestRrvendorNameInput.sendKeys(ordRequestRrvendorName);
  }

  async getOrdRequestRrvendorNameInput(): Promise<string> {
    return await this.ordRequestRrvendorNameInput.getAttribute('value');
  }

  async setOrdRequestRrvendorLicenseNbrInput(ordRequestRrvendorLicenseNbr: string): Promise<void> {
    await this.ordRequestRrvendorLicenseNbrInput.sendKeys(ordRequestRrvendorLicenseNbr);
  }

  async getOrdRequestRrvendorLicenseNbrInput(): Promise<string> {
    return await this.ordRequestRrvendorLicenseNbrInput.getAttribute('value');
  }

  async setOrdRequestRrvendorLicenseStateCdInput(ordRequestRrvendorLicenseStateCd: string): Promise<void> {
    await this.ordRequestRrvendorLicenseStateCdInput.sendKeys(ordRequestRrvendorLicenseStateCd);
  }

  async getOrdRequestRrvendorLicenseStateCdInput(): Promise<string> {
    return await this.ordRequestRrvendorLicenseStateCdInput.getAttribute('value');
  }

  async setOrdValuationCompletedProductInput(ordValuationCompletedProduct: string): Promise<void> {
    await this.ordValuationCompletedProductInput.sendKeys(ordValuationCompletedProduct);
  }

  async getOrdValuationCompletedProductInput(): Promise<string> {
    return await this.ordValuationCompletedProductInput.getAttribute('value');
  }

  async setOrdValuationDueToClientDtInput(ordValuationDueToClientDt: string): Promise<void> {
    await this.ordValuationDueToClientDtInput.sendKeys(ordValuationDueToClientDt);
  }

  async getOrdValuationDueToClientDtInput(): Promise<string> {
    return await this.ordValuationDueToClientDtInput.getAttribute('value');
  }

  async setOrdValuationReportRecivedDtInput(ordValuationReportRecivedDt: string): Promise<void> {
    await this.ordValuationReportRecivedDtInput.sendKeys(ordValuationReportRecivedDt);
  }

  async getOrdValuationReportRecivedDtInput(): Promise<string> {
    return await this.ordValuationReportRecivedDtInput.getAttribute('value');
  }

  async setOrdValuationCompletionDtInput(ordValuationCompletionDt: string): Promise<void> {
    await this.ordValuationCompletionDtInput.sendKeys(ordValuationCompletionDt);
  }

  async getOrdValuationCompletionDtInput(): Promise<string> {
    return await this.ordValuationCompletionDtInput.getAttribute('value');
  }

  async setOrdValuationReportStatusCdInput(ordValuationReportStatusCd: string): Promise<void> {
    await this.ordValuationReportStatusCdInput.sendKeys(ordValuationReportStatusCd);
  }

  async getOrdValuationReportStatusCdInput(): Promise<string> {
    return await this.ordValuationReportStatusCdInput.getAttribute('value');
  }

  async setOrdValuationAppraisedValueAmtInput(ordValuationAppraisedValueAmt: string): Promise<void> {
    await this.ordValuationAppraisedValueAmtInput.sendKeys(ordValuationAppraisedValueAmt);
  }

  async getOrdValuationAppraisedValueAmtInput(): Promise<string> {
    return await this.ordValuationAppraisedValueAmtInput.getAttribute('value');
  }

  async setOrdValuationBpoMarketValueAmtInput(ordValuationBpoMarketValueAmt: string): Promise<void> {
    await this.ordValuationBpoMarketValueAmtInput.sendKeys(ordValuationBpoMarketValueAmt);
  }

  async getOrdValuationBpoMarketValueAmtInput(): Promise<string> {
    return await this.ordValuationBpoMarketValueAmtInput.getAttribute('value');
  }

  async setOrdValuationBpoAsIsAmtInput(ordValuationBpoAsIsAmt: string): Promise<void> {
    await this.ordValuationBpoAsIsAmtInput.sendKeys(ordValuationBpoAsIsAmt);
  }

  async getOrdValuationBpoAsIsAmtInput(): Promise<string> {
    return await this.ordValuationBpoAsIsAmtInput.getAttribute('value');
  }

  async setOrdValuationBpoAsRepairedValueAmtInput(ordValuationBpoAsRepairedValueAmt: string): Promise<void> {
    await this.ordValuationBpoAsRepairedValueAmtInput.sendKeys(ordValuationBpoAsRepairedValueAmt);
  }

  async getOrdValuationBpoAsRepairedValueAmtInput(): Promise<string> {
    return await this.ordValuationBpoAsRepairedValueAmtInput.getAttribute('value');
  }

  async setOrdValuationRedFlagCodeInput(ordValuationRedFlagCode: string): Promise<void> {
    await this.ordValuationRedFlagCodeInput.sendKeys(ordValuationRedFlagCode);
  }

  async getOrdValuationRedFlagCodeInput(): Promise<string> {
    return await this.ordValuationRedFlagCodeInput.getAttribute('value');
  }

  async setOrdValuationRedFlagDescInput(ordValuationRedFlagDesc: string): Promise<void> {
    await this.ordValuationRedFlagDescInput.sendKeys(ordValuationRedFlagDesc);
  }

  async getOrdValuationRedFlagDescInput(): Promise<string> {
    return await this.ordValuationRedFlagDescInput.getAttribute('value');
  }

  async setOrdValuationAmcAppraisalIdInput(ordValuationAmcAppraisalId: string): Promise<void> {
    await this.ordValuationAmcAppraisalIdInput.sendKeys(ordValuationAmcAppraisalId);
  }

  async getOrdValuationAmcAppraisalIdInput(): Promise<string> {
    return await this.ordValuationAmcAppraisalIdInput.getAttribute('value');
  }

  async setOrdValuationFinalReviewIdInput(ordValuationFinalReviewId: string): Promise<void> {
    await this.ordValuationFinalReviewIdInput.sendKeys(ordValuationFinalReviewId);
  }

  async getOrdValuationFinalReviewIdInput(): Promise<string> {
    return await this.ordValuationFinalReviewIdInput.getAttribute('value');
  }

  async setOrdReviewRecommendedValueAmtInput(ordReviewRecommendedValueAmt: string): Promise<void> {
    await this.ordReviewRecommendedValueAmtInput.sendKeys(ordReviewRecommendedValueAmt);
  }

  async getOrdReviewRecommendedValueAmtInput(): Promise<string> {
    return await this.ordReviewRecommendedValueAmtInput.getAttribute('value');
  }

  async setOrdReviewDtInput(ordReviewDt: string): Promise<void> {
    await this.ordReviewDtInput.sendKeys(ordReviewDt);
  }

  async getOrdReviewDtInput(): Promise<string> {
    return await this.ordReviewDtInput.getAttribute('value');
  }

  async setOrdReviewRecommendedActionCdInput(ordReviewRecommendedActionCd: string): Promise<void> {
    await this.ordReviewRecommendedActionCdInput.sendKeys(ordReviewRecommendedActionCd);
  }

  async getOrdReviewRecommendedActionCdInput(): Promise<string> {
    return await this.ordReviewRecommendedActionCdInput.getAttribute('value');
  }

  async setOrdReviewReviewerDecisionCdInput(ordReviewReviewerDecisionCd: string): Promise<void> {
    await this.ordReviewReviewerDecisionCdInput.sendKeys(ordReviewReviewerDecisionCd);
  }

  async getOrdReviewReviewerDecisionCdInput(): Promise<string> {
    return await this.ordReviewReviewerDecisionCdInput.getAttribute('value');
  }

  async setOrdReviewReviewScoreInput(ordReviewReviewScore: string): Promise<void> {
    await this.ordReviewReviewScoreInput.sendKeys(ordReviewReviewScore);
  }

  async getOrdReviewReviewScoreInput(): Promise<string> {
    return await this.ordReviewReviewScoreInput.getAttribute('value');
  }

  async setOrdReviewFormUsedInput(ordReviewFormUsed: string): Promise<void> {
    await this.ordReviewFormUsedInput.sendKeys(ordReviewFormUsed);
  }

  async getOrdReviewFormUsedInput(): Promise<string> {
    return await this.ordReviewFormUsedInput.getAttribute('value');
  }

  getOrdReviewProviderOnWatchListIndInput(): ElementFinder {
    return this.ordReviewProviderOnWatchListIndInput;
  }

  async setOrdReviewCuredValueAmtInput(ordReviewCuredValueAmt: string): Promise<void> {
    await this.ordReviewCuredValueAmtInput.sendKeys(ordReviewCuredValueAmt);
  }

  async getOrdReviewCuredValueAmtInput(): Promise<string> {
    return await this.ordReviewCuredValueAmtInput.getAttribute('value');
  }

  async setOrdReviewFinalRecommendedActionCdInput(ordReviewFinalRecommendedActionCd: string): Promise<void> {
    await this.ordReviewFinalRecommendedActionCdInput.sendKeys(ordReviewFinalRecommendedActionCd);
  }

  async getOrdReviewFinalRecommendedActionCdInput(): Promise<string> {
    return await this.ordReviewFinalRecommendedActionCdInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class OrdersDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orders-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orders'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
