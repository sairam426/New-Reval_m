import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrganizationsService } from 'app/entities/organizations/organizations.service';
import { IOrganizations, Organizations } from 'app/shared/model/organizations.model';

describe('Service Tests', () => {
  describe('Organizations Service', () => {
    let injector: TestBed;
    let service: OrganizationsService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrganizations;
    let expectedResult: IOrganizations | IOrganizations[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrganizationsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Organizations(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Organizations', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Organizations()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Organizations', () => {
        const returnedFromService = Object.assign(
          {
            orgNbr: 'BBBBBB',
            orgName: 'BBBBBB',
            orgShortName: 'BBBBBB',
            orgAddress1: 'BBBBBB',
            orgAddress2: 'BBBBBB',
            orgCity: 'BBBBBB',
            orgStateCd: 'BBBBBB',
            orgZip: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Organizations', () => {
        const returnedFromService = Object.assign(
          {
            orgNbr: 'BBBBBB',
            orgName: 'BBBBBB',
            orgShortName: 'BBBBBB',
            orgAddress1: 'BBBBBB',
            orgAddress2: 'BBBBBB',
            orgCity: 'BBBBBB',
            orgStateCd: 'BBBBBB',
            orgZip: 'BBBBBB'
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

      it('should delete a Organizations', () => {
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
