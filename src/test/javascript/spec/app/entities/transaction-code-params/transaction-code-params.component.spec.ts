import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodeParamsComponent } from 'app/entities/transaction-code-params/transaction-code-params.component';
import { TransactionCodeParamsService } from 'app/entities/transaction-code-params/transaction-code-params.service';
import { TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

describe('Component Tests', () => {
  describe('TransactionCodeParams Management Component', () => {
    let comp: TransactionCodeParamsComponent;
    let fixture: ComponentFixture<TransactionCodeParamsComponent>;
    let service: TransactionCodeParamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodeParamsComponent]
      })
        .overrideTemplate(TransactionCodeParamsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionCodeParamsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionCodeParamsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TransactionCodeParams(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.transactionCodeParams && comp.transactionCodeParams[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
