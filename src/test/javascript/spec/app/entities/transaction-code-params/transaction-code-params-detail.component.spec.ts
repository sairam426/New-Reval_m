import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodeParamsDetailComponent } from 'app/entities/transaction-code-params/transaction-code-params-detail.component';
import { TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

describe('Component Tests', () => {
  describe('TransactionCodeParams Management Detail Component', () => {
    let comp: TransactionCodeParamsDetailComponent;
    let fixture: ComponentFixture<TransactionCodeParamsDetailComponent>;
    const route = ({ data: of({ transactionCodeParams: new TransactionCodeParams(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodeParamsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TransactionCodeParamsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransactionCodeParamsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load transactionCodeParams on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.transactionCodeParams).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
