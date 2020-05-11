import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { CompaniesComponent } from 'app/entities/companies/companies.component';
import { CompaniesService } from 'app/entities/companies/companies.service';
import { Companies } from 'app/shared/model/companies.model';

describe('Component Tests', () => {
  describe('Companies Management Component', () => {
    let comp: CompaniesComponent;
    let fixture: ComponentFixture<CompaniesComponent>;
    let service: CompaniesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [CompaniesComponent]
      })
        .overrideTemplate(CompaniesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompaniesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompaniesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Companies(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.companies && comp.companies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
