import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionCodesService } from 'app/entities/transaction-codes/transaction-codes.service';
import { ITransactionCodes, TransactionCodes } from 'app/shared/model/transaction-codes.model';

describe('Service Tests', () => {
  describe('TransactionCodes Service', () => {
    let injector: TestBed;
    let service: TransactionCodesService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransactionCodes;
    let expectedResult: ITransactionCodes | ITransactionCodes[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionCodesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TransactionCodes(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TransactionCodes', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TransactionCodes()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TransactionCodes', () => {
        const returnedFromService = Object.assign(
          {
            tcdCode: 'BBBBBB',
            tcdEntityGroupCd: 'BBBBBB',
            tcdDesc: 'BBBBBB',
            tcdEnabledInd: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TransactionCodes', () => {
        const returnedFromService = Object.assign(
          {
            tcdCode: 'BBBBBB',
            tcdEntityGroupCd: 'BBBBBB',
            tcdDesc: 'BBBBBB',
            tcdEnabledInd: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TransactionCodes', () => {
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
