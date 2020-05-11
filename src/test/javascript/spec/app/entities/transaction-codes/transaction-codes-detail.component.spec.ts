import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodesDetailComponent } from 'app/entities/transaction-codes/transaction-codes-detail.component';
import { TransactionCodes } from 'app/shared/model/transaction-codes.model';

describe('Component Tests', () => {
  describe('TransactionCodes Management Detail Component', () => {
    let comp: TransactionCodesDetailComponent;
    let fixture: ComponentFixture<TransactionCodesDetailComponent>;
    const route = ({ data: of({ transactionCodes: new TransactionCodes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TransactionCodesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransactionCodesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load transactionCodes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.transactionCodes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
