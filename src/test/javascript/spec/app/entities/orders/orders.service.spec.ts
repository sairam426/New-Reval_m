import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OrdersService } from 'app/entities/orders/orders.service';
import { IOrders, Orders } from 'app/shared/model/orders.model';

describe('Service Tests', () => {
  describe('Orders Service', () => {
    let injector: TestBed;
    let service: OrdersService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrders;
    let expectedResult: IOrders | IOrders[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrdersService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Orders(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        currentDate,
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        currentDate,
        false,
        false,
        'AAAAAAA',
        false,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ordOrderDt: currentDate.format(DATE_FORMAT),
            ordRushRequestDueDt: currentDate.format(DATE_FORMAT),
            ordOrgDuedate: currentDate.format(DATE_FORMAT),
            ordProDuedate: currentDate.format(DATE_FORMAT),
            ordUcdpFinalSubmissionDt: currentDate.format(DATE_FORMAT),
            ordLoanEstimatedCloseDt: currentDate.format(DATE_FORMAT),
            ordLoanBorrowerRequestedCloseDt: currentDate.format(DATE_FORMAT),
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate.format(DATE_FORMAT),
            ordValuationDueToClientDt: currentDate.format(DATE_FORMAT),
            ordValuationReportRecivedDt: currentDate.format(DATE_FORMAT),
            ordValuationCompletionDt: currentDate.format(DATE_FORMAT),
            ordReviewDt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Orders', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ordOrderDt: currentDate.format(DATE_FORMAT),
            ordRushRequestDueDt: currentDate.format(DATE_FORMAT),
            ordOrgDuedate: currentDate.format(DATE_FORMAT),
            ordProDuedate: currentDate.format(DATE_FORMAT),
            ordUcdpFinalSubmissionDt: currentDate.format(DATE_FORMAT),
            ordLoanEstimatedCloseDt: currentDate.format(DATE_FORMAT),
            ordLoanBorrowerRequestedCloseDt: currentDate.format(DATE_FORMAT),
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate.format(DATE_FORMAT),
            ordValuationDueToClientDt: currentDate.format(DATE_FORMAT),
            ordValuationReportRecivedDt: currentDate.format(DATE_FORMAT),
            ordValuationCompletionDt: currentDate.format(DATE_FORMAT),
            ordReviewDt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ordOrderDt: currentDate,
            ordRushRequestDueDt: currentDate,
            ordOrgDuedate: currentDate,
            ordProDuedate: currentDate,
            ordUcdpFinalSubmissionDt: currentDate,
            ordLoanEstimatedCloseDt: currentDate,
            ordLoanBorrowerRequestedCloseDt: currentDate,
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate,
            ordValuationDueToClientDt: currentDate,
            ordValuationReportRecivedDt: currentDate,
            ordValuationCompletionDt: currentDate,
            ordReviewDt: currentDate
          },
          returnedFromService
        );

        service.create(new Orders()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Orders', () => {
        const returnedFromService = Object.assign(
          {
            ordNumber: 'BBBBBB',
            ordStageCd: 'BBBBBB',
            ordOrgOrderStatusCd: 'BBBBBB',
            ordCmpOrderStatusCd: 'BBBBBB',
            ordProOrderStatusCd: 'BBBBBB',
            ordCmpNbr: 'BBBBBB',
            ordBrnNbr: 'BBBBBB',
            ordProNbr: 'BBBBBB',
            ordProductCode: 'BBBBBB',
            ordOriginalValueAmt: 1,
            ordParentTrackingNbr: 'BBBBBB',
            ordUserNbr: 'BBBBBB',
            ordOrderDt: currentDate.format(DATE_FORMAT),
            ordRushRequestDueDt: currentDate.format(DATE_FORMAT),
            ordOrgInstructions: 'BBBBBB',
            ordOrgOtherInstructions: 'BBBBBB',
            ordMultiOrderInd: true,
            ordOrgDuedate: currentDate.format(DATE_FORMAT),
            ordProDuedate: currentDate.format(DATE_FORMAT),
            ordUcdpDataFnmaSubmitToUcdpInd: true,
            ordUcdpDataFnmaServicerNbr: 'BBBBBB',
            ordUcdpDataFhlmcSubmitToUcdpInd: true,
            ordUcdpDataFhlmcIdentificationNbr: 'BBBBBB',
            ordUcdpDataUcdpBussinessUnitNbr: 'BBBBBB',
            ordUcdpSdFnamaDocumentId: 'BBBBBB',
            ordUcdpSdFnmaSubmissionStatusId: 'BBBBBB',
            ordUcdpSdFhlmcDocumentFileCd: 'BBBBBB',
            ordUcdpSdEadSubmissionStatusCd: 'BBBBBB',
            ordUcdpFinalSubmissionDt: currentDate.format(DATE_FORMAT),
            ordLoanNbr: 'BBBBBB',
            ordloanOldLoanNbr: 'BBBBBB',
            ordLoanProgram: 'BBBBBB',
            ordLoanPurpose: 'BBBBBB',
            ordLoanType: 'BBBBBB',
            ordLoanAmt: 1,
            ordLoanQualifyingValueAmt: 1,
            ordLoanSalesPriceAmt: 1,
            ordLoanBorrowerLastName: 'BBBBBB',
            ordLoanBorrowerFirstName: 'BBBBBB',
            ordLoanBorrowerMiddleName: 'BBBBBB',
            ordLoanBorrowerIsCoBorrowerInd: true,
            ordLoanFhaCaseNbr: 'BBBBBB',
            ordLoanDeedRestrictionInd: true,
            ordLoanEstimatedCloseDt: currentDate.format(DATE_FORMAT),
            ordLoanHpmlInd: true,
            ordLoanIsNewConstructionInd: true,
            ordLoanCustomerSegmentCode: 'BBBBBB',
            ordLoanNonSubjectPropertyInd: true,
            ordLoanBorrowerRequestedCloseDt: currentDate.format(DATE_FORMAT),
            ordPropertyTypeCd: 'BBBBBB',
            ordPropertyAddress1: 'BBBBBB',
            ordPropertyAddress2: 'BBBBBB',
            ordPropertyCity: 'BBBBBB',
            ordPropertyStateCd: 'BBBBBB',
            ordPropertyZip: 'BBBBBB',
            ordPropertyCounty: 'BBBBBB',
            ordPropertyLatitue: 'BBBBBB',
            ordPropertyLongitude: 'BBBBBB',
            ordPropertyProjectName: 'BBBBBB',
            ordContact1TypeCd: 'BBBBBB',
            ordContact1Name: 'BBBBBB',
            ordContact1WorkPhone: 1,
            ordContact1HomePhone: 1,
            ordContact1CellPhone: 1,
            ordContact1OtherCellPhone: 1,
            ordContact1Email: 'BBBBBB',
            ordContact1OtherEmail: 'BBBBBB',
            ordContact2TypeCd: 'BBBBBB',
            ordContact2Name: 'BBBBBB',
            ordContact2WorkPhone: 1,
            ordContact2HomePhone: 1,
            ordContact2CellPhone: 1,
            ordContact2OtherCellPhone: 1,
            ordContact2Email: 'BBBBBB',
            ordContact2OtherEmail: 'BBBBBB',
            ordContact3TypeCd: 'BBBBBB',
            ordContact3Name: 'BBBBBB',
            ordContact3WorkPhone: 1,
            ordContact3HomePhone: 1,
            ordContact3CellPhone: 1,
            ordContact3OtherCellPhone: 1,
            ordContact3Email: 'BBBBBB',
            ordContact3OtherEmail: 'BBBBBB',
            ordRequestRrStatusCd: 'BBBBBB',
            ordRequestRrVendorAppraisalId: 'BBBBBB',
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate.format(DATE_FORMAT),
            ordRequestRrXmlFormId: 'BBBBBB',
            ordRequestRrPdfFormId: 'BBBBBB',
            ordRequestRrApprFileDataSourcCd: 'BBBBBB',
            ordRequestRrReportValueAmt: 1,
            ordRequestRrAppraisalCompanyName: 'BBBBBB',
            ordRequestRrvendorName: 'BBBBBB',
            ordRequestRrvendorLicenseNbr: 'BBBBBB',
            ordRequestRrvendorLicenseStateCd: 'BBBBBB',
            ordValuationCompletedProduct: 'BBBBBB',
            ordValuationDueToClientDt: currentDate.format(DATE_FORMAT),
            ordValuationReportRecivedDt: currentDate.format(DATE_FORMAT),
            ordValuationCompletionDt: currentDate.format(DATE_FORMAT),
            ordValuationReportStatusCd: 'BBBBBB',
            ordValuationAppraisedValueAmt: 1,
            ordValuationBpoMarketValueAmt: 1,
            ordValuationBpoAsIsAmt: 1,
            ordValuationBpoAsRepairedValueAmt: 1,
            ordValuationRedFlagCode: 'BBBBBB',
            ordValuationRedFlagDesc: 'BBBBBB',
            ordValuationAmcAppraisalId: 'BBBBBB',
            ordValuationFinalReviewId: 1,
            ordReviewRecommendedValueAmt: 1,
            ordReviewDt: currentDate.format(DATE_FORMAT),
            ordReviewRecommendedActionCd: 'BBBBBB',
            ordReviewReviewerDecisionCd: 'BBBBBB',
            ordReviewReviewScore: 'BBBBBB',
            ordReviewFormUsed: 'BBBBBB',
            ordReviewProviderOnWatchListInd: true,
            ordReviewCuredValueAmt: 1,
            ordReviewFinalRecommendedActionCd: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ordOrderDt: currentDate,
            ordRushRequestDueDt: currentDate,
            ordOrgDuedate: currentDate,
            ordProDuedate: currentDate,
            ordUcdpFinalSubmissionDt: currentDate,
            ordLoanEstimatedCloseDt: currentDate,
            ordLoanBorrowerRequestedCloseDt: currentDate,
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate,
            ordValuationDueToClientDt: currentDate,
            ordValuationReportRecivedDt: currentDate,
            ordValuationCompletionDt: currentDate,
            ordReviewDt: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Orders', () => {
        const returnedFromService = Object.assign(
          {
            ordNumber: 'BBBBBB',
            ordStageCd: 'BBBBBB',
            ordOrgOrderStatusCd: 'BBBBBB',
            ordCmpOrderStatusCd: 'BBBBBB',
            ordProOrderStatusCd: 'BBBBBB',
            ordCmpNbr: 'BBBBBB',
            ordBrnNbr: 'BBBBBB',
            ordProNbr: 'BBBBBB',
            ordProductCode: 'BBBBBB',
            ordOriginalValueAmt: 1,
            ordParentTrackingNbr: 'BBBBBB',
            ordUserNbr: 'BBBBBB',
            ordOrderDt: currentDate.format(DATE_FORMAT),
            ordRushRequestDueDt: currentDate.format(DATE_FORMAT),
            ordOrgInstructions: 'BBBBBB',
            ordOrgOtherInstructions: 'BBBBBB',
            ordMultiOrderInd: true,
            ordOrgDuedate: currentDate.format(DATE_FORMAT),
            ordProDuedate: currentDate.format(DATE_FORMAT),
            ordUcdpDataFnmaSubmitToUcdpInd: true,
            ordUcdpDataFnmaServicerNbr: 'BBBBBB',
            ordUcdpDataFhlmcSubmitToUcdpInd: true,
            ordUcdpDataFhlmcIdentificationNbr: 'BBBBBB',
            ordUcdpDataUcdpBussinessUnitNbr: 'BBBBBB',
            ordUcdpSdFnamaDocumentId: 'BBBBBB',
            ordUcdpSdFnmaSubmissionStatusId: 'BBBBBB',
            ordUcdpSdFhlmcDocumentFileCd: 'BBBBBB',
            ordUcdpSdEadSubmissionStatusCd: 'BBBBBB',
            ordUcdpFinalSubmissionDt: currentDate.format(DATE_FORMAT),
            ordLoanNbr: 'BBBBBB',
            ordloanOldLoanNbr: 'BBBBBB',
            ordLoanProgram: 'BBBBBB',
            ordLoanPurpose: 'BBBBBB',
            ordLoanType: 'BBBBBB',
            ordLoanAmt: 1,
            ordLoanQualifyingValueAmt: 1,
            ordLoanSalesPriceAmt: 1,
            ordLoanBorrowerLastName: 'BBBBBB',
            ordLoanBorrowerFirstName: 'BBBBBB',
            ordLoanBorrowerMiddleName: 'BBBBBB',
            ordLoanBorrowerIsCoBorrowerInd: true,
            ordLoanFhaCaseNbr: 'BBBBBB',
            ordLoanDeedRestrictionInd: true,
            ordLoanEstimatedCloseDt: currentDate.format(DATE_FORMAT),
            ordLoanHpmlInd: true,
            ordLoanIsNewConstructionInd: true,
            ordLoanCustomerSegmentCode: 'BBBBBB',
            ordLoanNonSubjectPropertyInd: true,
            ordLoanBorrowerRequestedCloseDt: currentDate.format(DATE_FORMAT),
            ordPropertyTypeCd: 'BBBBBB',
            ordPropertyAddress1: 'BBBBBB',
            ordPropertyAddress2: 'BBBBBB',
            ordPropertyCity: 'BBBBBB',
            ordPropertyStateCd: 'BBBBBB',
            ordPropertyZip: 'BBBBBB',
            ordPropertyCounty: 'BBBBBB',
            ordPropertyLatitue: 'BBBBBB',
            ordPropertyLongitude: 'BBBBBB',
            ordPropertyProjectName: 'BBBBBB',
            ordContact1TypeCd: 'BBBBBB',
            ordContact1Name: 'BBBBBB',
            ordContact1WorkPhone: 1,
            ordContact1HomePhone: 1,
            ordContact1CellPhone: 1,
            ordContact1OtherCellPhone: 1,
            ordContact1Email: 'BBBBBB',
            ordContact1OtherEmail: 'BBBBBB',
            ordContact2TypeCd: 'BBBBBB',
            ordContact2Name: 'BBBBBB',
            ordContact2WorkPhone: 1,
            ordContact2HomePhone: 1,
            ordContact2CellPhone: 1,
            ordContact2OtherCellPhone: 1,
            ordContact2Email: 'BBBBBB',
            ordContact2OtherEmail: 'BBBBBB',
            ordContact3TypeCd: 'BBBBBB',
            ordContact3Name: 'BBBBBB',
            ordContact3WorkPhone: 1,
            ordContact3HomePhone: 1,
            ordContact3CellPhone: 1,
            ordContact3OtherCellPhone: 1,
            ordContact3Email: 'BBBBBB',
            ordContact3OtherEmail: 'BBBBBB',
            ordRequestRrStatusCd: 'BBBBBB',
            ordRequestRrVendorAppraisalId: 'BBBBBB',
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate.format(DATE_FORMAT),
            ordRequestRrXmlFormId: 'BBBBBB',
            ordRequestRrPdfFormId: 'BBBBBB',
            ordRequestRrApprFileDataSourcCd: 'BBBBBB',
            ordRequestRrReportValueAmt: 1,
            ordRequestRrAppraisalCompanyName: 'BBBBBB',
            ordRequestRrvendorName: 'BBBBBB',
            ordRequestRrvendorLicenseNbr: 'BBBBBB',
            ordRequestRrvendorLicenseStateCd: 'BBBBBB',
            ordValuationCompletedProduct: 'BBBBBB',
            ordValuationDueToClientDt: currentDate.format(DATE_FORMAT),
            ordValuationReportRecivedDt: currentDate.format(DATE_FORMAT),
            ordValuationCompletionDt: currentDate.format(DATE_FORMAT),
            ordValuationReportStatusCd: 'BBBBBB',
            ordValuationAppraisedValueAmt: 1,
            ordValuationBpoMarketValueAmt: 1,
            ordValuationBpoAsIsAmt: 1,
            ordValuationBpoAsRepairedValueAmt: 1,
            ordValuationRedFlagCode: 'BBBBBB',
            ordValuationRedFlagDesc: 'BBBBBB',
            ordValuationAmcAppraisalId: 'BBBBBB',
            ordValuationFinalReviewId: 1,
            ordReviewRecommendedValueAmt: 1,
            ordReviewDt: currentDate.format(DATE_FORMAT),
            ordReviewRecommendedActionCd: 'BBBBBB',
            ordReviewReviewerDecisionCd: 'BBBBBB',
            ordReviewReviewScore: 'BBBBBB',
            ordReviewFormUsed: 'BBBBBB',
            ordReviewProviderOnWatchListInd: true,
            ordReviewCuredValueAmt: 1,
            ordReviewFinalRecommendedActionCd: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ordOrderDt: currentDate,
            ordRushRequestDueDt: currentDate,
            ordOrgDuedate: currentDate,
            ordProDuedate: currentDate,
            ordUcdpFinalSubmissionDt: currentDate,
            ordLoanEstimatedCloseDt: currentDate,
            ordLoanBorrowerRequestedCloseDt: currentDate,
            ordRequestRrvendorAppraisalDraftRcvdDt: currentDate,
            ordValuationDueToClientDt: currentDate,
            ordValuationReportRecivedDt: currentDate,
            ordValuationCompletionDt: currentDate,
            ordReviewDt: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Orders', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
