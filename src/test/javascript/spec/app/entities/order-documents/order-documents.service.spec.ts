import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OrderDocumentsService } from 'app/entities/order-documents/order-documents.service';
import { IOrderDocuments, OrderDocuments } from 'app/shared/model/order-documents.model';

describe('Service Tests', () => {
  describe('OrderDocuments Service', () => {
    let injector: TestBed;
    let service: OrderDocumentsService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderDocuments;
    let expectedResult: IOrderDocuments | IOrderDocuments[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderDocumentsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OrderDocuments(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            odoDocumentDueDt: currentDate.format(DATE_FORMAT),
            odoDocumentRcvdDt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrderDocuments', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            odoDocumentDueDt: currentDate.format(DATE_FORMAT),
            odoDocumentRcvdDt: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            odoDocumentDueDt: currentDate,
            odoDocumentRcvdDt: currentDate
          },
          returnedFromService
        );

        service.create(new OrderDocuments()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrderDocuments', () => {
        const returnedFromService = Object.assign(
          {
            odoDocumentMimeTypeCd: 'BBBBBB',
            odoDocumentTypeCd: 'BBBBBB',
            odoExternalStorageLink: 'BBBBBB',
            odoExternalStorageFileName: 'BBBBBB',
            odoDocumentDueToCd: 'BBBBBB',
            odoDocumentDueFromCd: 'BBBBBB',
            odoDocumentDueDt: currentDate.format(DATE_FORMAT),
            odoDocumentRcvdDt: currentDate.format(DATE_FORMAT),
            odoDocumentStatusCd: 'BBBBBB',
            odoDocument: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            odoDocumentDueDt: currentDate,
            odoDocumentRcvdDt: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OrderDocuments', () => {
        const returnedFromService = Object.assign(
          {
            odoDocumentMimeTypeCd: 'BBBBBB',
            odoDocumentTypeCd: 'BBBBBB',
            odoExternalStorageLink: 'BBBBBB',
            odoExternalStorageFileName: 'BBBBBB',
            odoDocumentDueToCd: 'BBBBBB',
            odoDocumentDueFromCd: 'BBBBBB',
            odoDocumentDueDt: currentDate.format(DATE_FORMAT),
            odoDocumentRcvdDt: currentDate.format(DATE_FORMAT),
            odoDocumentStatusCd: 'BBBBBB',
            odoDocument: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            odoDocumentDueDt: currentDate,
            odoDocumentRcvdDt: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrderDocuments', () => {
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
