import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { OrganizationsComponent } from 'app/entities/organizations/organizations.component';
import { OrganizationsService } from 'app/entities/organizations/organizations.service';
import { Organizations } from 'app/shared/model/organizations.model';

describe('Component Tests', () => {
  describe('Organizations Management Component', () => {
    let comp: OrganizationsComponent;
    let fixture: ComponentFixture<OrganizationsComponent>;
    let service: OrganizationsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrganizationsComponent]
      })
        .overrideTemplate(OrganizationsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizationsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizationsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Organizations(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.organizations && comp.organizations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
