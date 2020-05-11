import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodesComponent } from 'app/entities/transaction-codes/transaction-codes.component';
import { TransactionCodesService } from 'app/entities/transaction-codes/transaction-codes.service';
import { TransactionCodes } from 'app/shared/model/transaction-codes.model';

describe('Component Tests', () => {
  describe('TransactionCodes Management Component', () => {
    let comp: TransactionCodesComponent;
    let fixture: ComponentFixture<TransactionCodesComponent>;
    let service: TransactionCodesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodesComponent]
      })
        .overrideTemplate(TransactionCodesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionCodesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionCodesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TransactionCodes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.transactionCodes && comp.transactionCodes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
