import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LookupsService } from 'app/entities/lookups/lookups.service';
import { ILookups, Lookups } from 'app/shared/model/lookups.model';

describe('Service Tests', () => {
  describe('Lookups Service', () => {
    let injector: TestBed;
    let service: LookupsService;
    let httpMock: HttpTestingController;
    let elemDefault: ILookups;
    let expectedResult: ILookups | ILookups[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LookupsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Lookups(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Lookups', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Lookups()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Lookups', () => {
        const returnedFromService = Object.assign(
          {
            lkcCode: 'BBBBBB',
            lkcSubCode: 'BBBBBB',
            lkcSort: 'BBBBBB',
            lkcEnabledInd: true,
            lkcDesc: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Lookups', () => {
        const returnedFromService = Object.assign(
          {
            lkcCode: 'BBBBBB',
            lkcSubCode: 'BBBBBB',
            lkcSort: 'BBBBBB',
            lkcEnabledInd: true,
            lkcDesc: 'BBBBBB'
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

      it('should delete a Lookups', () => {
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
