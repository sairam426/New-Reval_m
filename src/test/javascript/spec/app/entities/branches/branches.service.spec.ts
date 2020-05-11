import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BranchesService } from 'app/entities/branches/branches.service';
import { IBranches, Branches } from 'app/shared/model/branches.model';

describe('Service Tests', () => {
  describe('Branches Service', () => {
    let injector: TestBed;
    let service: BranchesService;
    let httpMock: HttpTestingController;
    let elemDefault: IBranches;
    let expectedResult: IBranches | IBranches[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BranchesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Branches(
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
        false,
        'AAAAAAA',
        'AAAAAAA'
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

      it('should create a Branches', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Branches()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Branches', () => {
        const returnedFromService = Object.assign(
          {
            brnNbr: 'BBBBBB',
            brnName: 'BBBBBB',
            brnCode: 'BBBBBB',
            brnShortName: 'BBBBBB',
            brnAddress1: 'BBBBBB',
            brnAddress2: 'BBBBBB',
            brnCity: 'BBBBBB',
            brnStateCd: 'BBBBBB',
            brnZip: 'BBBBBB',
            brnEnabledInd: true,
            brnRegioinCd: 'BBBBBB',
            brnServiceTypeCd: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Branches', () => {
        const returnedFromService = Object.assign(
          {
            brnNbr: 'BBBBBB',
            brnName: 'BBBBBB',
            brnCode: 'BBBBBB',
            brnShortName: 'BBBBBB',
            brnAddress1: 'BBBBBB',
            brnAddress2: 'BBBBBB',
            brnCity: 'BBBBBB',
            brnStateCd: 'BBBBBB',
            brnZip: 'BBBBBB',
            brnEnabledInd: true,
            brnRegioinCd: 'BBBBBB',
            brnServiceTypeCd: 'BBBBBB'
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

      it('should delete a Branches', () => {
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
