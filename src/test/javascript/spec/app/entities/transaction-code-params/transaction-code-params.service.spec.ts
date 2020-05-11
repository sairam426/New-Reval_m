import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TransactionCodeParamsService } from 'app/entities/transaction-code-params/transaction-code-params.service';
import { ITransactionCodeParams, TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

describe('Service Tests', () => {
  describe('TransactionCodeParams Service', () => {
    let injector: TestBed;
    let service: TransactionCodeParamsService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransactionCodeParams;
    let expectedResult: ITransactionCodeParams | ITransactionCodeParams[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionCodeParamsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TransactionCodeParams(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TransactionCodeParams', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TransactionCodeParams()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TransactionCodeParams', () => {
        const returnedFromService = Object.assign(
          {
            tcpTcdCode: 'BBBBBB',
            tcpParamName: 'BBBBBB',
            tcpParamDesc: 'BBBBBB',
            tcpEnabledInd: true,
            tcpParamDataTypeCd: 'BBBBBB',
            tcpParamLength: 'BBBBBB',
            tcpDefaultValue: 'BBBBBB',
            tcpParamLovValidationInd: true,
            tcpParamLktType: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TransactionCodeParams', () => {
        const returnedFromService = Object.assign(
          {
            tcpTcdCode: 'BBBBBB',
            tcpParamName: 'BBBBBB',
            tcpParamDesc: 'BBBBBB',
            tcpEnabledInd: true,
            tcpParamDataTypeCd: 'BBBBBB',
            tcpParamLength: 'BBBBBB',
            tcpDefaultValue: 'BBBBBB',
            tcpParamLovValidationInd: true,
            tcpParamLktType: 'BBBBBB'
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

      it('should delete a TransactionCodeParams', () => {
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
