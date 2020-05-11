import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { OrderDocumentsUpdateComponent } from 'app/entities/order-documents/order-documents-update.component';
import { OrderDocumentsService } from 'app/entities/order-documents/order-documents.service';
import { OrderDocuments } from 'app/shared/model/order-documents.model';

describe('Component Tests', () => {
  describe('OrderDocuments Management Update Component', () => {
    let comp: OrderDocumentsUpdateComponent;
    let fixture: ComponentFixture<OrderDocumentsUpdateComponent>;
    let service: OrderDocumentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderDocumentsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderDocumentsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderDocumentsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderDocumentsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderDocuments(123);
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
        const entity = new OrderDocuments();
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
