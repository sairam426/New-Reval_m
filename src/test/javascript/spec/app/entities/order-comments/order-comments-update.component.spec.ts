import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { OrderCommentsUpdateComponent } from 'app/entities/order-comments/order-comments-update.component';
import { OrderCommentsService } from 'app/entities/order-comments/order-comments.service';
import { OrderComments } from 'app/shared/model/order-comments.model';

describe('Component Tests', () => {
  describe('OrderComments Management Update Component', () => {
    let comp: OrderCommentsUpdateComponent;
    let fixture: ComponentFixture<OrderCommentsUpdateComponent>;
    let service: OrderCommentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderCommentsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderCommentsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderCommentsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderCommentsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderComments(123);
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
        const entity = new OrderComments();
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
