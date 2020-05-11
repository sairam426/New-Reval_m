import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CompaniesService } from 'app/entities/companies/companies.service';
import { ICompanies, Companies } from 'app/shared/model/companies.model';

describe('Service Tests', () => {
  describe('Companies Service', () => {
    let injector: TestBed;
    let service: CompaniesService;
    let httpMock: HttpTestingController;
    let elemDefault: ICompanies;
    let expectedResult: ICompanies | ICompanies[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CompaniesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Companies(
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
        'AAAAAAA',
        'AAAAAAA',
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Companies', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Companies()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Companies', () => {
        const returnedFromService = Object.assign(
          {
            cmpNbr: 'BBBBBB',
            cmpName: 'BBBBBB',
            cmpServiceTypeCd: 'BBBBBB',
            cmpShortName: 'BBBBBB',
            cmpStatusCd: 'BBBBBB',
            cmpTypeCd: 'BBBBBB',
            cmpAddress1: 'BBBBBB',
            cmpAddress2: 'BBBBBB',
            cmpCity: 'BBBBBB',
            cmpStateCd: 'BBBBBB',
            cmpZip: 'BBBBBB',
            cmpEnabledInd: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Companies', () => {
        const returnedFromService = Object.assign(
          {
            cmpNbr: 'BBBBBB',
            cmpName: 'BBBBBB',
            cmpServiceTypeCd: 'BBBBBB',
            cmpShortName: 'BBBBBB',
            cmpStatusCd: 'BBBBBB',
            cmpTypeCd: 'BBBBBB',
            cmpAddress1: 'BBBBBB',
            cmpAddress2: 'BBBBBB',
            cmpCity: 'BBBBBB',
            cmpStateCd: 'BBBBBB',
            cmpZip: 'BBBBBB',
            cmpEnabledInd: true
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

      it('should delete a Companies', () => {
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
