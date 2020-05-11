import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { TransactionCodeParamsUpdateComponent } from 'app/entities/transaction-code-params/transaction-code-params-update.component';
import { TransactionCodeParamsService } from 'app/entities/transaction-code-params/transaction-code-params.service';
import { TransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

describe('Component Tests', () => {
  describe('TransactionCodeParams Management Update Component', () => {
    let comp: TransactionCodeParamsUpdateComponent;
    let fixture: ComponentFixture<TransactionCodeParamsUpdateComponent>;
    let service: TransactionCodeParamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [TransactionCodeParamsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TransactionCodeParamsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionCodeParamsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionCodeParamsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TransactionCodeParams(123);
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
        const entity = new TransactionCodeParams();
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
