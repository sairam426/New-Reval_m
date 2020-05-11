import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { SystemAccessUpdateComponent } from 'app/entities/system-access/system-access-update.component';
import { SystemAccessService } from 'app/entities/system-access/system-access.service';
import { SystemAccess } from 'app/shared/model/system-access.model';

describe('Component Tests', () => {
  describe('SystemAccess Management Update Component', () => {
    let comp: SystemAccessUpdateComponent;
    let fixture: ComponentFixture<SystemAccessUpdateComponent>;
    let service: SystemAccessService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [SystemAccessUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SystemAccessUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SystemAccessUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SystemAccessService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SystemAccess(123);
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
        const entity = new SystemAccess();
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
