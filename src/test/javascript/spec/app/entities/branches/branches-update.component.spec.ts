import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { BranchesUpdateComponent } from 'app/entities/branches/branches-update.component';
import { BranchesService } from 'app/entities/branches/branches.service';
import { Branches } from 'app/shared/model/branches.model';

describe('Component Tests', () => {
  describe('Branches Management Update Component', () => {
    let comp: BranchesUpdateComponent;
    let fixture: ComponentFixture<BranchesUpdateComponent>;
    let service: BranchesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [BranchesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BranchesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BranchesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BranchesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Branches(123);
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
        const entity = new Branches();
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
