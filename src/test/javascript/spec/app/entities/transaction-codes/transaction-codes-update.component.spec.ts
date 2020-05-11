import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodesUpdateComponent } from 'app/entities/transaction-codes/transaction-codes-update.component';
import { TransactionCodesService } from 'app/entities/transaction-codes/transaction-codes.service';
import { TransactionCodes } from 'app/shared/model/transaction-codes.model';

describe('Component Tests', () => {
  describe('TransactionCodes Management Update Component', () => {
    let comp: TransactionCodesUpdateComponent;
    let fixture: ComponentFixture<TransactionCodesUpdateComponent>;
    let service: TransactionCodesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TransactionCodesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionCodesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionCodesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TransactionCodes(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TransactionCodes();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
