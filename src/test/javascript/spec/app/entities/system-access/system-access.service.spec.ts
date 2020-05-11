import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SystemAccessService } from 'app/entities/system-access/system-access.service';
import { ISystemAccess, SystemAccess } from 'app/shared/model/system-access.model';

describe('Service Tests', () => {
  describe('SystemAccess Service', () => {
    let injector: TestBed;
    let service: SystemAccessService;
    let httpMock: HttpTestingController;
    let elemDefault: ISystemAccess;
    let expectedResult: ISystemAccess | ISystemAccess[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SystemAccessService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SystemAccess(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SystemAccess', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SystemAccess()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SystemAccess', () => {
        const returnedFromService = Object.assign(
          {
            sacAccessKey: 'BBBBBB',
            sacAccessTypeCd: 'BBBBBB',
            sacAccessValue: 'BBBBBB',
            sacAllowedInd: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SystemAccess', () => {
        const returnedFromService = Object.assign(
          {
            sacAccessKey: 'BBBBBB',
            sacAccessTypeCd: 'BBBBBB',
            sacAccessValue: 'BBBBBB',
            sacAllowedInd: true
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

      it('should delete a SystemAccess', () => {
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
