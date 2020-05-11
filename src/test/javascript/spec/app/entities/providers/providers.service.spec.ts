import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProvidersService } from 'app/entities/providers/providers.service';
import { IProviders, Providers } from 'app/shared/model/providers.model';

describe('Service Tests', () => {
  describe('Providers Service', () => {
    let injector: TestBed;
    let service: ProvidersService;
    let httpMock: HttpTestingController;
    let elemDefault: IProviders;
    let expectedResult: IProviders | IProviders[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProvidersService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Providers(
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
        'AAAAAAA',
        0
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

      it('should create a Providers', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Providers()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Providers', () => {
        const returnedFromService = Object.assign(
          {
            proNbr: 'BBBBBB',
            proName: 'BBBBBB',
            proShortName: 'BBBBBB',
            proStatusCd: 'BBBBBB',
            proTypeCd: 'BBBBBB',
            proGroupCd: 'BBBBBB',
            proLicenseNbr: 'BBBBBB',
            proLicenseStatusCd: 'BBBBBB',
            proAddress1: 'BBBBBB',
            proAddress2: 'BBBBBB',
            proCity: 'BBBBBB',
            proStateCd: 'BBBBBB',
            proZip: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Providers', () => {
        const returnedFromService = Object.assign(
          {
            proNbr: 'BBBBBB',
            proName: 'BBBBBB',
            proShortName: 'BBBBBB',
            proStatusCd: 'BBBBBB',
            proTypeCd: 'BBBBBB',
            proGroupCd: 'BBBBBB',
            proLicenseNbr: 'BBBBBB',
            proLicenseStatusCd: 'BBBBBB',
            proAddress1: 'BBBBBB',
            proAddress2: 'BBBBBB',
            proCity: 'BBBBBB',
            proStateCd: 'BBBBBB',
            proZip: 1
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

      it('should delete a Providers', () => {
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
