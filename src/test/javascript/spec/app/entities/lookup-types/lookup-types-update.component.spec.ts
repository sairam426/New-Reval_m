import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { LookupTypesUpdateComponent } from 'app/entities/lookup-types/lookup-types-update.component';
import { LookupTypesService } from 'app/entities/lookup-types/lookup-types.service';
import { LookupTypes } from 'app/shared/model/lookup-types.model';

describe('Component Tests', () => {
  describe('LookupTypes Management Update Component', () => {
    let comp: LookupTypesUpdateComponent;
    let fixture: ComponentFixture<LookupTypesUpdateComponent>;
    let service: LookupTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupTypesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LookupTypesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LookupTypesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LookupTypesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LookupTypes(123);
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
        const entity = new LookupTypes();
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
