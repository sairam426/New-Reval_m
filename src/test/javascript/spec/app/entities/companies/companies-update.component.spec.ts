import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { CompaniesUpdateComponent } from 'app/entities/companies/companies-update.component';
import { CompaniesService } from 'app/entities/companies/companies.service';
import { Companies } from 'app/shared/model/companies.model';

describe('Component Tests', () => {
  describe('Companies Management Update Component', () => {
    let comp: CompaniesUpdateComponent;
    let fixture: ComponentFixture<CompaniesUpdateComponent>;
    let service: CompaniesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [CompaniesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CompaniesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompaniesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompaniesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Companies(123);
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
        const entity = new Companies();
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
