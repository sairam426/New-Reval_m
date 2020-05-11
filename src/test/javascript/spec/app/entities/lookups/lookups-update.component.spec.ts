import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { LookupsUpdateComponent } from 'app/entities/lookups/lookups-update.component';
import { LookupsService } from 'app/entities/lookups/lookups.service';
import { Lookups } from 'app/shared/model/lookups.model';

describe('Component Tests', () => {
  describe('Lookups Management Update Component', () => {
    let comp: LookupsUpdateComponent;
    let fixture: ComponentFixture<LookupsUpdateComponent>;
    let service: LookupsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LookupsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LookupsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LookupsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Lookups(123);
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
        const entity = new Lookups();
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
