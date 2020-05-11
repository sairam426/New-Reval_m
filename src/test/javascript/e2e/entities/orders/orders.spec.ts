import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrdersComponentsPage, OrdersDeleteDialog, OrdersUpdatePage } from './orders.page-object';

const expect = chai.expect;

describe('Orders e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let ordersComponentsPage: OrdersComponentsPage;
  let ordersUpdatePage: OrdersUpdatePage;
  let ordersDeleteDialog: OrdersDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Orders', async () => {
    await navBarPage.goToEntity('orders');
    ordersComponentsPage = new OrdersComponentsPage();
    await browser.wait(ec.visibilityOf(ordersComponentsPage.title), 5000);
    expect(await ordersComponentsPage.getTitle()).to.eq('revalApp.orders.home.title');
    await browser.wait(ec.or(ec.visibilityOf(ordersComponentsPage.entities), ec.visibilityOf(ordersComponentsPage.noResult)), 1000);
  });

  it('should load create Orders page', async () => {
    await ordersComponentsPage.clickOnCreateButton();
    ordersUpdatePage = new OrdersUpdatePage();
    expect(await ordersUpdatePage.getPageTitle()).to.eq('revalApp.orders.home.createOrEditLabel');
    await ordersUpdatePage.cancel();
  });

  it('should create and save Orders', async () => {
    const nbButtonsBeforeCreate = await ordersComponentsPage.countDeleteButtons();

    await ordersComponentsPage.clickOnCreateButton();

    await promise.all([
      ordersUpdatePage.setOrdNumberInput('ordNumber'),
      ordersUpdatePage.setOrdStageCdInput('ordStageCd'),
      ordersUpdatePage.setOrdOrgOrderStatusCdInput('ordOrgOrderStatusCd'),
      ordersUpdatePage.setOrdCmpOrderStatusCdInput('ordCmpOrderStatusCd'),
      ordersUpdatePage.setOrdProOrderStatusCdInput('ordProOrderStatusCd'),
      ordersUpdatePage.setOrdCmpNbrInput('ordCmpNbr'),
      ordersUpdatePage.setOrdBrnNbrInput('ordBrnNbr'),
      ordersUpdatePage.setOrdProNbrInput('ordProNbr'),
      ordersUpdatePage.setOrdProductCodeInput('ordProductCode'),
      ordersUpdatePage.setOrdOriginalValueAmtInput('5'),
      ordersUpdatePage.setOrdParentTrackingNbrInput('ordParentTrackingNbr'),
      ordersUpdatePage.setOrdUserNbrInput('ordUserNbr'),
      ordersUpdatePage.setOrdOrderDtInput('2000-12-31'),
      ordersUpdatePage.setOrdRushRequestDueDtInput('2000-12-31'),
      ordersUpdatePage.setOrdOrgInstructionsInput('ordOrgInstructions'),
      ordersUpdatePage.setOrdOrgOtherInstructionsInput('ordOrgOtherInstructions'),
      ordersUpdatePage.setOrdOrgDuedateInput('2000-12-31'),
      ordersUpdatePage.setOrdProDuedateInput('2000-12-31'),
      ordersUpdatePage.setOrdUcdpDataFnmaServicerNbrInput('ordUcdpDataFnmaServicerNbr'),
      ordersUpdatePage.setOrdUcdpDataFhlmcIdentificationNbrInput('ordUcdpDataFhlmcIdentificationNbr'),
      ordersUpdatePage.setOrdUcdpDataUcdpBussinessUnitNbrInput('ordUcdpDataUcdpBussinessUnitNbr'),
      ordersUpdatePage.setOrdUcdpSdFnamaDocumentIdInput('ordUcdpSdFnamaDocumentId'),
      ordersUpdatePage.setOrdUcdpSdFnmaSubmissionStatusIdInput('ordUcdpSdFnmaSubmissionStatusId'),
      ordersUpdatePage.setOrdUcdpSdFhlmcDocumentFileCdInput('ordUcdpSdFhlmcDocumentFileCd'),
      ordersUpdatePage.setOrdUcdpSdEadSubmissionStatusCdInput('ordUcdpSdEadSubmissionStatusCd'),
      ordersUpdatePage.setOrdUcdpFinalSubmissionDtInput('2000-12-31'),
      ordersUpdatePage.setOrdLoanNbrInput('ordLoanNbr'),
      ordersUpdatePage.setOrdloanOldLoanNbrInput('ordloanOldLoanNbr'),
      ordersUpdatePage.setOrdLoanProgramInput('ordLoanProgram'),
      ordersUpdatePage.setOrdLoanPurposeInput('ordLoanPurpose'),
      ordersUpdatePage.setOrdLoanTypeInput('ordLoanType'),
      ordersUpdatePage.setOrdLoanAmtInput('5'),
      ordersUpdatePage.setOrdLoanQualifyingValueAmtInput('5'),
      ordersUpdatePage.setOrdLoanSalesPriceAmtInput('5'),
      ordersUpdatePage.setOrdLoanBorrowerLastNameInput('ordLoanBorrowerLastName'),
      ordersUpdatePage.setOrdLoanBorrowerFirstNameInput('ordLoanBorrowerFirstName'),
      ordersUpdatePage.setOrdLoanBorrowerMiddleNameInput('ordLoanBorrowerMiddleName'),
      ordersUpdatePage.setOrdLoanFhaCaseNbrInput('ordLoanFhaCaseNbr'),
      ordersUpdatePage.setOrdLoanEstimatedCloseDtInput('2000-12-31'),
      ordersUpdatePage.setOrdLoanCustomerSegmentCodeInput('ordLoanCustomerSegmentCode'),
      ordersUpdatePage.setOrdLoanBorrowerRequestedCloseDtInput('2000-12-31'),
      ordersUpdatePage.setOrdPropertyTypeCdInput('ordPropertyTypeCd'),
      ordersUpdatePage.setOrdPropertyAddress1Input('ordPropertyAddress1'),
      ordersUpdatePage.setOrdPropertyAddress2Input('ordPropertyAddress2'),
      ordersUpdatePage.setOrdPropertyCityInput('ordPropertyCity'),
      ordersUpdatePage.setOrdPropertyStateCdInput('ordPropertyStateCd'),
      ordersUpdatePage.setOrdPropertyZipInput('ordPropertyZip'),
      ordersUpdatePage.setOrdPropertyCountyInput('ordPropertyCounty'),
      ordersUpdatePage.setOrdPropertyLatitueInput('ordPropertyLatitue'),
      ordersUpdatePage.setOrdPropertyLongitudeInput('ordPropertyLongitude'),
      ordersUpdatePage.setOrdPropertyProjectNameInput('ordPropertyProjectName'),
      ordersUpdatePage.setOrdContact1TypeCdInput('ordContact1TypeCd'),
      ordersUpdatePage.setOrdContact1NameInput('ordContact1Name'),
      ordersUpdatePage.setOrdContact1WorkPhoneInput('5'),
      ordersUpdatePage.setOrdContact1HomePhoneInput('5'),
      ordersUpdatePage.setOrdContact1CellPhoneInput('5'),
      ordersUpdatePage.setOrdContact1OtherCellPhoneInput('5'),
      ordersUpdatePage.setOrdContact1EmailInput('ordContact1Email'),
      ordersUpdatePage.setOrdContact1OtherEmailInput('ordContact1OtherEmail'),
      ordersUpdatePage.setOrdContact2TypeCdInput('ordContact2TypeCd'),
      ordersUpdatePage.setOrdContact2NameInput('ordContact2Name'),
      ordersUpdatePage.setOrdContact2WorkPhoneInput('5'),
      ordersUpdatePage.setOrdContact2HomePhoneInput('5'),
      ordersUpdatePage.setOrdContact2CellPhoneInput('5'),
      ordersUpdatePage.setOrdContact2OtherCellPhoneInput('5'),
      ordersUpdatePage.setOrdContact2EmailInput('ordContact2Email'),
      ordersUpdatePage.setOrdContact2OtherEmailInput('ordContact2OtherEmail'),
      ordersUpdatePage.setOrdContact3TypeCdInput('ordContact3TypeCd'),
      ordersUpdatePage.setOrdContact3NameInput('ordContact3Name'),
      ordersUpdatePage.setOrdContact3WorkPhoneInput('5'),
      ordersUpdatePage.setOrdContact3HomePhoneInput('5'),
      ordersUpdatePage.setOrdContact3CellPhoneInput('5'),
      ordersUpdatePage.setOrdContact3OtherCellPhoneInput('5'),
      ordersUpdatePage.setOrdContact3EmailInput('ordContact3Email'),
      ordersUpdatePage.setOrdContact3OtherEmailInput('ordContact3OtherEmail'),
      ordersUpdatePage.setOrdRequestRrStatusCdInput('ordRequestRrStatusCd'),
      ordersUpdatePage.setOrdRequestRrVendorAppraisalIdInput('ordRequestRrVendorAppraisalId'),
      ordersUpdatePage.setOrdRequestRrvendorAppraisalDraftRcvdDtInput('2000-12-31'),
      ordersUpdatePage.setOrdRequestRrXmlFormIdInput('ordRequestRrXmlFormId'),
      ordersUpdatePage.setOrdRequestRrPdfFormIdInput('ordRequestRrPdfFormId'),
      ordersUpdatePage.setOrdRequestRrApprFileDataSourcCdInput('ordRequestRrApprFileDataSourcCd'),
      ordersUpdatePage.setOrdRequestRrReportValueAmtInput('5'),
      ordersUpdatePage.setOrdRequestRrAppraisalCompanyNameInput('ordRequestRrAppraisalCompanyName'),
      ordersUpdatePage.setOrdRequestRrvendorNameInput('ordRequestRrvendorName'),
      ordersUpdatePage.setOrdRequestRrvendorLicenseNbrInput('ordRequestRrvendorLicenseNbr'),
      ordersUpdatePage.setOrdRequestRrvendorLicenseStateCdInput('ordRequestRrvendorLicenseStateCd'),
      ordersUpdatePage.setOrdValuationCompletedProductInput('ordValuationCompletedProduct'),
      ordersUpdatePage.setOrdValuationDueToClientDtInput('2000-12-31'),
      ordersUpdatePage.setOrdValuationReportRecivedDtInput('2000-12-31'),
      ordersUpdatePage.setOrdValuationCompletionDtInput('2000-12-31'),
      ordersUpdatePage.setOrdValuationReportStatusCdInput('ordValuationReportStatusCd'),
      ordersUpdatePage.setOrdValuationAppraisedValueAmtInput('5'),
      ordersUpdatePage.setOrdValuationBpoMarketValueAmtInput('5'),
      ordersUpdatePage.setOrdValuationBpoAsIsAmtInput('5'),
      ordersUpdatePage.setOrdValuationBpoAsRepairedValueAmtInput('5'),
      ordersUpdatePage.setOrdValuationRedFlagCodeInput('ordValuationRedFlagCode'),
      ordersUpdatePage.setOrdValuationRedFlagDescInput('ordValuationRedFlagDesc'),
      ordersUpdatePage.setOrdValuationAmcAppraisalIdInput('ordValuationAmcAppraisalId'),
      ordersUpdatePage.setOrdValuationFinalReviewIdInput('5'),
      ordersUpdatePage.setOrdReviewRecommendedValueAmtInput('5'),
      ordersUpdatePage.setOrdReviewDtInput('2000-12-31'),
      ordersUpdatePage.setOrdReviewRecommendedActionCdInput('ordReviewRecommendedActionCd'),
      ordersUpdatePage.setOrdReviewReviewerDecisionCdInput('ordReviewReviewerDecisionCd'),
      ordersUpdatePage.setOrdReviewReviewScoreInput('ordReviewReviewScore'),
      ordersUpdatePage.setOrdReviewFormUsedInput('ordReviewFormUsed'),
      ordersUpdatePage.setOrdReviewCuredValueAmtInput('5'),
      ordersUpdatePage.setOrdReviewFinalRecommendedActionCdInput('ordReviewFinalRecommendedActionCd')
    ]);

    expect(await ordersUpdatePage.getOrdNumberInput()).to.eq('ordNumber', 'Expected OrdNumber value to be equals to ordNumber');
    expect(await ordersUpdatePage.getOrdStageCdInput()).to.eq('ordStageCd', 'Expected OrdStageCd value to be equals to ordStageCd');
    expect(await ordersUpdatePage.getOrdOrgOrderStatusCdInput()).to.eq(
      'ordOrgOrderStatusCd',
      'Expected OrdOrgOrderStatusCd value to be equals to ordOrgOrderStatusCd'
    );
    expect(await ordersUpdatePage.getOrdCmpOrderStatusCdInput()).to.eq(
      'ordCmpOrderStatusCd',
      'Expected OrdCmpOrderStatusCd value to be equals to ordCmpOrderStatusCd'
    );
    expect(await ordersUpdatePage.getOrdProOrderStatusCdInput()).to.eq(
      'ordProOrderStatusCd',
      'Expected OrdProOrderStatusCd value to be equals to ordProOrderStatusCd'
    );
    expect(await ordersUpdatePage.getOrdCmpNbrInput()).to.eq('ordCmpNbr', 'Expected OrdCmpNbr value to be equals to ordCmpNbr');
    expect(await ordersUpdatePage.getOrdBrnNbrInput()).to.eq('ordBrnNbr', 'Expected OrdBrnNbr value to be equals to ordBrnNbr');
    expect(await ordersUpdatePage.getOrdProNbrInput()).to.eq('ordProNbr', 'Expected OrdProNbr value to be equals to ordProNbr');
    expect(await ordersUpdatePage.getOrdProductCodeInput()).to.eq(
      'ordProductCode',
      'Expected OrdProductCode value to be equals to ordProductCode'
    );
    expect(await ordersUpdatePage.getOrdOriginalValueAmtInput()).to.eq('5', 'Expected ordOriginalValueAmt value to be equals to 5');
    expect(await ordersUpdatePage.getOrdParentTrackingNbrInput()).to.eq(
      'ordParentTrackingNbr',
      'Expected OrdParentTrackingNbr value to be equals to ordParentTrackingNbr'
    );
    expect(await ordersUpdatePage.getOrdUserNbrInput()).to.eq('ordUserNbr', 'Expected OrdUserNbr value to be equals to ordUserNbr');
    expect(await ordersUpdatePage.getOrdOrderDtInput()).to.eq('2000-12-31', 'Expected ordOrderDt value to be equals to 2000-12-31');
    expect(await ordersUpdatePage.getOrdRushRequestDueDtInput()).to.eq(
      '2000-12-31',
      'Expected ordRushRequestDueDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdOrgInstructionsInput()).to.eq(
      'ordOrgInstructions',
      'Expected OrdOrgInstructions value to be equals to ordOrgInstructions'
    );
    expect(await ordersUpdatePage.getOrdOrgOtherInstructionsInput()).to.eq(
      'ordOrgOtherInstructions',
      'Expected OrdOrgOtherInstructions value to be equals to ordOrgOtherInstructions'
    );
    const selectedOrdMultiOrderInd = ordersUpdatePage.getOrdMultiOrderIndInput();
    if (await selectedOrdMultiOrderInd.isSelected()) {
      await ordersUpdatePage.getOrdMultiOrderIndInput().click();
      expect(await ordersUpdatePage.getOrdMultiOrderIndInput().isSelected(), 'Expected ordMultiOrderInd not to be selected').to.be.false;
    } else {
      await ordersUpdatePage.getOrdMultiOrderIndInput().click();
      expect(await ordersUpdatePage.getOrdMultiOrderIndInput().isSelected(), 'Expected ordMultiOrderInd to be selected').to.be.true;
    }
    expect(await ordersUpdatePage.getOrdOrgDuedateInput()).to.eq('2000-12-31', 'Expected ordOrgDuedate value to be equals to 2000-12-31');
    expect(await ordersUpdatePage.getOrdProDuedateInput()).to.eq('2000-12-31', 'Expected ordProDuedate value to be equals to 2000-12-31');
    const selectedOrdUcdpDataFnmaSubmitToUcdpInd = ordersUpdatePage.getOrdUcdpDataFnmaSubmitToUcdpIndInput();
    if (await selectedOrdUcdpDataFnmaSubmitToUcdpInd.isSelected()) {
      await ordersUpdatePage.getOrdUcdpDataFnmaSubmitToUcdpIndInput().click();
      expect(
        await ordersUpdatePage.getOrdUcdpDataFnmaSubmitToUcdpIndInput().isSelected(),
        'Expected ordUcdpDataFnmaSubmitToUcdpInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdUcdpDataFnmaSubmitToUcdpIndInput().click();
      expect(
        await ordersUpdatePage.getOrdUcdpDataFnmaSubmitToUcdpIndInput().isSelected(),
        'Expected ordUcdpDataFnmaSubmitToUcdpInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdUcdpDataFnmaServicerNbrInput()).to.eq(
      'ordUcdpDataFnmaServicerNbr',
      'Expected OrdUcdpDataFnmaServicerNbr value to be equals to ordUcdpDataFnmaServicerNbr'
    );
    const selectedOrdUcdpDataFhlmcSubmitToUcdpInd = ordersUpdatePage.getOrdUcdpDataFhlmcSubmitToUcdpIndInput();
    if (await selectedOrdUcdpDataFhlmcSubmitToUcdpInd.isSelected()) {
      await ordersUpdatePage.getOrdUcdpDataFhlmcSubmitToUcdpIndInput().click();
      expect(
        await ordersUpdatePage.getOrdUcdpDataFhlmcSubmitToUcdpIndInput().isSelected(),
        'Expected ordUcdpDataFhlmcSubmitToUcdpInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdUcdpDataFhlmcSubmitToUcdpIndInput().click();
      expect(
        await ordersUpdatePage.getOrdUcdpDataFhlmcSubmitToUcdpIndInput().isSelected(),
        'Expected ordUcdpDataFhlmcSubmitToUcdpInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdUcdpDataFhlmcIdentificationNbrInput()).to.eq(
      'ordUcdpDataFhlmcIdentificationNbr',
      'Expected OrdUcdpDataFhlmcIdentificationNbr value to be equals to ordUcdpDataFhlmcIdentificationNbr'
    );
    expect(await ordersUpdatePage.getOrdUcdpDataUcdpBussinessUnitNbrInput()).to.eq(
      'ordUcdpDataUcdpBussinessUnitNbr',
      'Expected OrdUcdpDataUcdpBussinessUnitNbr value to be equals to ordUcdpDataUcdpBussinessUnitNbr'
    );
    expect(await ordersUpdatePage.getOrdUcdpSdFnamaDocumentIdInput()).to.eq(
      'ordUcdpSdFnamaDocumentId',
      'Expected OrdUcdpSdFnamaDocumentId value to be equals to ordUcdpSdFnamaDocumentId'
    );
    expect(await ordersUpdatePage.getOrdUcdpSdFnmaSubmissionStatusIdInput()).to.eq(
      'ordUcdpSdFnmaSubmissionStatusId',
      'Expected OrdUcdpSdFnmaSubmissionStatusId value to be equals to ordUcdpSdFnmaSubmissionStatusId'
    );
    expect(await ordersUpdatePage.getOrdUcdpSdFhlmcDocumentFileCdInput()).to.eq(
      'ordUcdpSdFhlmcDocumentFileCd',
      'Expected OrdUcdpSdFhlmcDocumentFileCd value to be equals to ordUcdpSdFhlmcDocumentFileCd'
    );
    expect(await ordersUpdatePage.getOrdUcdpSdEadSubmissionStatusCdInput()).to.eq(
      'ordUcdpSdEadSubmissionStatusCd',
      'Expected OrdUcdpSdEadSubmissionStatusCd value to be equals to ordUcdpSdEadSubmissionStatusCd'
    );
    expect(await ordersUpdatePage.getOrdUcdpFinalSubmissionDtInput()).to.eq(
      '2000-12-31',
      'Expected ordUcdpFinalSubmissionDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdLoanNbrInput()).to.eq('ordLoanNbr', 'Expected OrdLoanNbr value to be equals to ordLoanNbr');
    expect(await ordersUpdatePage.getOrdloanOldLoanNbrInput()).to.eq(
      'ordloanOldLoanNbr',
      'Expected OrdloanOldLoanNbr value to be equals to ordloanOldLoanNbr'
    );
    expect(await ordersUpdatePage.getOrdLoanProgramInput()).to.eq(
      'ordLoanProgram',
      'Expected OrdLoanProgram value to be equals to ordLoanProgram'
    );
    expect(await ordersUpdatePage.getOrdLoanPurposeInput()).to.eq(
      'ordLoanPurpose',
      'Expected OrdLoanPurpose value to be equals to ordLoanPurpose'
    );
    expect(await ordersUpdatePage.getOrdLoanTypeInput()).to.eq('ordLoanType', 'Expected OrdLoanType value to be equals to ordLoanType');
    expect(await ordersUpdatePage.getOrdLoanAmtInput()).to.eq('5', 'Expected ordLoanAmt value to be equals to 5');
    expect(await ordersUpdatePage.getOrdLoanQualifyingValueAmtInput()).to.eq(
      '5',
      'Expected ordLoanQualifyingValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdLoanSalesPriceAmtInput()).to.eq('5', 'Expected ordLoanSalesPriceAmt value to be equals to 5');
    expect(await ordersUpdatePage.getOrdLoanBorrowerLastNameInput()).to.eq(
      'ordLoanBorrowerLastName',
      'Expected OrdLoanBorrowerLastName value to be equals to ordLoanBorrowerLastName'
    );
    expect(await ordersUpdatePage.getOrdLoanBorrowerFirstNameInput()).to.eq(
      'ordLoanBorrowerFirstName',
      'Expected OrdLoanBorrowerFirstName value to be equals to ordLoanBorrowerFirstName'
    );
    expect(await ordersUpdatePage.getOrdLoanBorrowerMiddleNameInput()).to.eq(
      'ordLoanBorrowerMiddleName',
      'Expected OrdLoanBorrowerMiddleName value to be equals to ordLoanBorrowerMiddleName'
    );
    const selectedOrdLoanBorrowerIsCoBorrowerInd = ordersUpdatePage.getOrdLoanBorrowerIsCoBorrowerIndInput();
    if (await selectedOrdLoanBorrowerIsCoBorrowerInd.isSelected()) {
      await ordersUpdatePage.getOrdLoanBorrowerIsCoBorrowerIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanBorrowerIsCoBorrowerIndInput().isSelected(),
        'Expected ordLoanBorrowerIsCoBorrowerInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdLoanBorrowerIsCoBorrowerIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanBorrowerIsCoBorrowerIndInput().isSelected(),
        'Expected ordLoanBorrowerIsCoBorrowerInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdLoanFhaCaseNbrInput()).to.eq(
      'ordLoanFhaCaseNbr',
      'Expected OrdLoanFhaCaseNbr value to be equals to ordLoanFhaCaseNbr'
    );
    const selectedOrdLoanDeedRestrictionInd = ordersUpdatePage.getOrdLoanDeedRestrictionIndInput();
    if (await selectedOrdLoanDeedRestrictionInd.isSelected()) {
      await ordersUpdatePage.getOrdLoanDeedRestrictionIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanDeedRestrictionIndInput().isSelected(),
        'Expected ordLoanDeedRestrictionInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdLoanDeedRestrictionIndInput().click();
      expect(await ordersUpdatePage.getOrdLoanDeedRestrictionIndInput().isSelected(), 'Expected ordLoanDeedRestrictionInd to be selected')
        .to.be.true;
    }
    expect(await ordersUpdatePage.getOrdLoanEstimatedCloseDtInput()).to.eq(
      '2000-12-31',
      'Expected ordLoanEstimatedCloseDt value to be equals to 2000-12-31'
    );
    const selectedOrdLoanHpmlInd = ordersUpdatePage.getOrdLoanHpmlIndInput();
    if (await selectedOrdLoanHpmlInd.isSelected()) {
      await ordersUpdatePage.getOrdLoanHpmlIndInput().click();
      expect(await ordersUpdatePage.getOrdLoanHpmlIndInput().isSelected(), 'Expected ordLoanHpmlInd not to be selected').to.be.false;
    } else {
      await ordersUpdatePage.getOrdLoanHpmlIndInput().click();
      expect(await ordersUpdatePage.getOrdLoanHpmlIndInput().isSelected(), 'Expected ordLoanHpmlInd to be selected').to.be.true;
    }
    const selectedOrdLoanIsNewConstructionInd = ordersUpdatePage.getOrdLoanIsNewConstructionIndInput();
    if (await selectedOrdLoanIsNewConstructionInd.isSelected()) {
      await ordersUpdatePage.getOrdLoanIsNewConstructionIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanIsNewConstructionIndInput().isSelected(),
        'Expected ordLoanIsNewConstructionInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdLoanIsNewConstructionIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanIsNewConstructionIndInput().isSelected(),
        'Expected ordLoanIsNewConstructionInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdLoanCustomerSegmentCodeInput()).to.eq(
      'ordLoanCustomerSegmentCode',
      'Expected OrdLoanCustomerSegmentCode value to be equals to ordLoanCustomerSegmentCode'
    );
    const selectedOrdLoanNonSubjectPropertyInd = ordersUpdatePage.getOrdLoanNonSubjectPropertyIndInput();
    if (await selectedOrdLoanNonSubjectPropertyInd.isSelected()) {
      await ordersUpdatePage.getOrdLoanNonSubjectPropertyIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanNonSubjectPropertyIndInput().isSelected(),
        'Expected ordLoanNonSubjectPropertyInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdLoanNonSubjectPropertyIndInput().click();
      expect(
        await ordersUpdatePage.getOrdLoanNonSubjectPropertyIndInput().isSelected(),
        'Expected ordLoanNonSubjectPropertyInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdLoanBorrowerRequestedCloseDtInput()).to.eq(
      '2000-12-31',
      'Expected ordLoanBorrowerRequestedCloseDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdPropertyTypeCdInput()).to.eq(
      'ordPropertyTypeCd',
      'Expected OrdPropertyTypeCd value to be equals to ordPropertyTypeCd'
    );
    expect(await ordersUpdatePage.getOrdPropertyAddress1Input()).to.eq(
      'ordPropertyAddress1',
      'Expected OrdPropertyAddress1 value to be equals to ordPropertyAddress1'
    );
    expect(await ordersUpdatePage.getOrdPropertyAddress2Input()).to.eq(
      'ordPropertyAddress2',
      'Expected OrdPropertyAddress2 value to be equals to ordPropertyAddress2'
    );
    expect(await ordersUpdatePage.getOrdPropertyCityInput()).to.eq(
      'ordPropertyCity',
      'Expected OrdPropertyCity value to be equals to ordPropertyCity'
    );
    expect(await ordersUpdatePage.getOrdPropertyStateCdInput()).to.eq(
      'ordPropertyStateCd',
      'Expected OrdPropertyStateCd value to be equals to ordPropertyStateCd'
    );
    expect(await ordersUpdatePage.getOrdPropertyZipInput()).to.eq(
      'ordPropertyZip',
      'Expected OrdPropertyZip value to be equals to ordPropertyZip'
    );
    expect(await ordersUpdatePage.getOrdPropertyCountyInput()).to.eq(
      'ordPropertyCounty',
      'Expected OrdPropertyCounty value to be equals to ordPropertyCounty'
    );
    expect(await ordersUpdatePage.getOrdPropertyLatitueInput()).to.eq(
      'ordPropertyLatitue',
      'Expected OrdPropertyLatitue value to be equals to ordPropertyLatitue'
    );
    expect(await ordersUpdatePage.getOrdPropertyLongitudeInput()).to.eq(
      'ordPropertyLongitude',
      'Expected OrdPropertyLongitude value to be equals to ordPropertyLongitude'
    );
    expect(await ordersUpdatePage.getOrdPropertyProjectNameInput()).to.eq(
      'ordPropertyProjectName',
      'Expected OrdPropertyProjectName value to be equals to ordPropertyProjectName'
    );
    expect(await ordersUpdatePage.getOrdContact1TypeCdInput()).to.eq(
      'ordContact1TypeCd',
      'Expected OrdContact1TypeCd value to be equals to ordContact1TypeCd'
    );
    expect(await ordersUpdatePage.getOrdContact1NameInput()).to.eq(
      'ordContact1Name',
      'Expected OrdContact1Name value to be equals to ordContact1Name'
    );
    expect(await ordersUpdatePage.getOrdContact1WorkPhoneInput()).to.eq('5', 'Expected ordContact1WorkPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact1HomePhoneInput()).to.eq('5', 'Expected ordContact1HomePhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact1CellPhoneInput()).to.eq('5', 'Expected ordContact1CellPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact1OtherCellPhoneInput()).to.eq(
      '5',
      'Expected ordContact1OtherCellPhone value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdContact1EmailInput()).to.eq(
      'ordContact1Email',
      'Expected OrdContact1Email value to be equals to ordContact1Email'
    );
    expect(await ordersUpdatePage.getOrdContact1OtherEmailInput()).to.eq(
      'ordContact1OtherEmail',
      'Expected OrdContact1OtherEmail value to be equals to ordContact1OtherEmail'
    );
    expect(await ordersUpdatePage.getOrdContact2TypeCdInput()).to.eq(
      'ordContact2TypeCd',
      'Expected OrdContact2TypeCd value to be equals to ordContact2TypeCd'
    );
    expect(await ordersUpdatePage.getOrdContact2NameInput()).to.eq(
      'ordContact2Name',
      'Expected OrdContact2Name value to be equals to ordContact2Name'
    );
    expect(await ordersUpdatePage.getOrdContact2WorkPhoneInput()).to.eq('5', 'Expected ordContact2WorkPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact2HomePhoneInput()).to.eq('5', 'Expected ordContact2HomePhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact2CellPhoneInput()).to.eq('5', 'Expected ordContact2CellPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact2OtherCellPhoneInput()).to.eq(
      '5',
      'Expected ordContact2OtherCellPhone value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdContact2EmailInput()).to.eq(
      'ordContact2Email',
      'Expected OrdContact2Email value to be equals to ordContact2Email'
    );
    expect(await ordersUpdatePage.getOrdContact2OtherEmailInput()).to.eq(
      'ordContact2OtherEmail',
      'Expected OrdContact2OtherEmail value to be equals to ordContact2OtherEmail'
    );
    expect(await ordersUpdatePage.getOrdContact3TypeCdInput()).to.eq(
      'ordContact3TypeCd',
      'Expected OrdContact3TypeCd value to be equals to ordContact3TypeCd'
    );
    expect(await ordersUpdatePage.getOrdContact3NameInput()).to.eq(
      'ordContact3Name',
      'Expected OrdContact3Name value to be equals to ordContact3Name'
    );
    expect(await ordersUpdatePage.getOrdContact3WorkPhoneInput()).to.eq('5', 'Expected ordContact3WorkPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact3HomePhoneInput()).to.eq('5', 'Expected ordContact3HomePhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact3CellPhoneInput()).to.eq('5', 'Expected ordContact3CellPhone value to be equals to 5');
    expect(await ordersUpdatePage.getOrdContact3OtherCellPhoneInput()).to.eq(
      '5',
      'Expected ordContact3OtherCellPhone value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdContact3EmailInput()).to.eq(
      'ordContact3Email',
      'Expected OrdContact3Email value to be equals to ordContact3Email'
    );
    expect(await ordersUpdatePage.getOrdContact3OtherEmailInput()).to.eq(
      'ordContact3OtherEmail',
      'Expected OrdContact3OtherEmail value to be equals to ordContact3OtherEmail'
    );
    expect(await ordersUpdatePage.getOrdRequestRrStatusCdInput()).to.eq(
      'ordRequestRrStatusCd',
      'Expected OrdRequestRrStatusCd value to be equals to ordRequestRrStatusCd'
    );
    expect(await ordersUpdatePage.getOrdRequestRrVendorAppraisalIdInput()).to.eq(
      'ordRequestRrVendorAppraisalId',
      'Expected OrdRequestRrVendorAppraisalId value to be equals to ordRequestRrVendorAppraisalId'
    );
    expect(await ordersUpdatePage.getOrdRequestRrvendorAppraisalDraftRcvdDtInput()).to.eq(
      '2000-12-31',
      'Expected ordRequestRrvendorAppraisalDraftRcvdDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdRequestRrXmlFormIdInput()).to.eq(
      'ordRequestRrXmlFormId',
      'Expected OrdRequestRrXmlFormId value to be equals to ordRequestRrXmlFormId'
    );
    expect(await ordersUpdatePage.getOrdRequestRrPdfFormIdInput()).to.eq(
      'ordRequestRrPdfFormId',
      'Expected OrdRequestRrPdfFormId value to be equals to ordRequestRrPdfFormId'
    );
    expect(await ordersUpdatePage.getOrdRequestRrApprFileDataSourcCdInput()).to.eq(
      'ordRequestRrApprFileDataSourcCd',
      'Expected OrdRequestRrApprFileDataSourcCd value to be equals to ordRequestRrApprFileDataSourcCd'
    );
    expect(await ordersUpdatePage.getOrdRequestRrReportValueAmtInput()).to.eq(
      '5',
      'Expected ordRequestRrReportValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdRequestRrAppraisalCompanyNameInput()).to.eq(
      'ordRequestRrAppraisalCompanyName',
      'Expected OrdRequestRrAppraisalCompanyName value to be equals to ordRequestRrAppraisalCompanyName'
    );
    expect(await ordersUpdatePage.getOrdRequestRrvendorNameInput()).to.eq(
      'ordRequestRrvendorName',
      'Expected OrdRequestRrvendorName value to be equals to ordRequestRrvendorName'
    );
    expect(await ordersUpdatePage.getOrdRequestRrvendorLicenseNbrInput()).to.eq(
      'ordRequestRrvendorLicenseNbr',
      'Expected OrdRequestRrvendorLicenseNbr value to be equals to ordRequestRrvendorLicenseNbr'
    );
    expect(await ordersUpdatePage.getOrdRequestRrvendorLicenseStateCdInput()).to.eq(
      'ordRequestRrvendorLicenseStateCd',
      'Expected OrdRequestRrvendorLicenseStateCd value to be equals to ordRequestRrvendorLicenseStateCd'
    );
    expect(await ordersUpdatePage.getOrdValuationCompletedProductInput()).to.eq(
      'ordValuationCompletedProduct',
      'Expected OrdValuationCompletedProduct value to be equals to ordValuationCompletedProduct'
    );
    expect(await ordersUpdatePage.getOrdValuationDueToClientDtInput()).to.eq(
      '2000-12-31',
      'Expected ordValuationDueToClientDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdValuationReportRecivedDtInput()).to.eq(
      '2000-12-31',
      'Expected ordValuationReportRecivedDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdValuationCompletionDtInput()).to.eq(
      '2000-12-31',
      'Expected ordValuationCompletionDt value to be equals to 2000-12-31'
    );
    expect(await ordersUpdatePage.getOrdValuationReportStatusCdInput()).to.eq(
      'ordValuationReportStatusCd',
      'Expected OrdValuationReportStatusCd value to be equals to ordValuationReportStatusCd'
    );
    expect(await ordersUpdatePage.getOrdValuationAppraisedValueAmtInput()).to.eq(
      '5',
      'Expected ordValuationAppraisedValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdValuationBpoMarketValueAmtInput()).to.eq(
      '5',
      'Expected ordValuationBpoMarketValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdValuationBpoAsIsAmtInput()).to.eq('5', 'Expected ordValuationBpoAsIsAmt value to be equals to 5');
    expect(await ordersUpdatePage.getOrdValuationBpoAsRepairedValueAmtInput()).to.eq(
      '5',
      'Expected ordValuationBpoAsRepairedValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdValuationRedFlagCodeInput()).to.eq(
      'ordValuationRedFlagCode',
      'Expected OrdValuationRedFlagCode value to be equals to ordValuationRedFlagCode'
    );
    expect(await ordersUpdatePage.getOrdValuationRedFlagDescInput()).to.eq(
      'ordValuationRedFlagDesc',
      'Expected OrdValuationRedFlagDesc value to be equals to ordValuationRedFlagDesc'
    );
    expect(await ordersUpdatePage.getOrdValuationAmcAppraisalIdInput()).to.eq(
      'ordValuationAmcAppraisalId',
      'Expected OrdValuationAmcAppraisalId value to be equals to ordValuationAmcAppraisalId'
    );
    expect(await ordersUpdatePage.getOrdValuationFinalReviewIdInput()).to.eq(
      '5',
      'Expected ordValuationFinalReviewId value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdReviewRecommendedValueAmtInput()).to.eq(
      '5',
      'Expected ordReviewRecommendedValueAmt value to be equals to 5'
    );
    expect(await ordersUpdatePage.getOrdReviewDtInput()).to.eq('2000-12-31', 'Expected ordReviewDt value to be equals to 2000-12-31');
    expect(await ordersUpdatePage.getOrdReviewRecommendedActionCdInput()).to.eq(
      'ordReviewRecommendedActionCd',
      'Expected OrdReviewRecommendedActionCd value to be equals to ordReviewRecommendedActionCd'
    );
    expect(await ordersUpdatePage.getOrdReviewReviewerDecisionCdInput()).to.eq(
      'ordReviewReviewerDecisionCd',
      'Expected OrdReviewReviewerDecisionCd value to be equals to ordReviewReviewerDecisionCd'
    );
    expect(await ordersUpdatePage.getOrdReviewReviewScoreInput()).to.eq(
      'ordReviewReviewScore',
      'Expected OrdReviewReviewScore value to be equals to ordReviewReviewScore'
    );
    expect(await ordersUpdatePage.getOrdReviewFormUsedInput()).to.eq(
      'ordReviewFormUsed',
      'Expected OrdReviewFormUsed value to be equals to ordReviewFormUsed'
    );
    const selectedOrdReviewProviderOnWatchListInd = ordersUpdatePage.getOrdReviewProviderOnWatchListIndInput();
    if (await selectedOrdReviewProviderOnWatchListInd.isSelected()) {
      await ordersUpdatePage.getOrdReviewProviderOnWatchListIndInput().click();
      expect(
        await ordersUpdatePage.getOrdReviewProviderOnWatchListIndInput().isSelected(),
        'Expected ordReviewProviderOnWatchListInd not to be selected'
      ).to.be.false;
    } else {
      await ordersUpdatePage.getOrdReviewProviderOnWatchListIndInput().click();
      expect(
        await ordersUpdatePage.getOrdReviewProviderOnWatchListIndInput().isSelected(),
        'Expected ordReviewProviderOnWatchListInd to be selected'
      ).to.be.true;
    }
    expect(await ordersUpdatePage.getOrdReviewCuredValueAmtInput()).to.eq('5', 'Expected ordReviewCuredValueAmt value to be equals to 5');
    expect(await ordersUpdatePage.getOrdReviewFinalRecommendedActionCdInput()).to.eq(
      'ordReviewFinalRecommendedActionCd',
      'Expected OrdReviewFinalRecommendedActionCd value to be equals to ordReviewFinalRecommendedActionCd'
    );

    await ordersUpdatePage.save();
    expect(await ordersUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await ordersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Orders', async () => {
    const nbButtonsBeforeDelete = await ordersComponentsPage.countDeleteButtons();
    await ordersComponentsPage.clickOnLastDeleteButton();

    ordersDeleteDialog = new OrdersDeleteDialog();
    expect(await ordersDeleteDialog.getDialogTitle()).to.eq('revalApp.orders.delete.question');
    await ordersDeleteDialog.clickOnConfirmButton();

    expect(await ordersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
