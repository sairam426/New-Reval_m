import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OrderCommentsService } from 'app/entities/order-comments/order-comments.service';
import { IOrderComments, OrderComments } from 'app/shared/model/order-comments.model';

describe('Service Tests', () => {
  describe('OrderComments Service', () => {
    let injector: TestBed;
    let service: OrderCommentsService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderComments;
    let expectedResult: IOrderComments | IOrderComments[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrderCommentsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OrderComments(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, false, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ocmCommentDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrderComments', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ocmCommentDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ocmCommentDate: currentDate
          },
          returnedFromService
        );

        service.create(new OrderComments()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrderComments', () => {
        const returnedFromService = Object.assign(
          {
            ocmCommentTypeCd: 'BBBBBB',
            ocmCommentSubTypeCd: 'BBBBBB',
            ocmCommentBy: 'BBBBBB',
            ocmCommentDate: currentDate.format(DATE_FORMAT),
            ocmCommentImpInd: true,
            ocmComment: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ocmCommentDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OrderComments', () => {
        const returnedFromService = Object.assign(
          {
            ocmCommentTypeCd: 'BBBBBB',
            ocmCommentSubTypeCd: 'BBBBBB',
            ocmCommentBy: 'BBBBBB',
            ocmCommentDate: currentDate.format(DATE_FORMAT),
            ocmCommentImpInd: true,
            ocmComment: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ocmCommentDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrderComments', () => {
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
