import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { SystemAccessComponent } from 'app/entities/system-access/system-access.component';
import { SystemAccessService } from 'app/entities/system-access/system-access.service';
import { SystemAccess } from 'app/shared/model/system-access.model';

describe('Component Tests', () => {
  describe('SystemAccess Management Component', () => {
    let comp: SystemAccessComponent;
    let fixture: ComponentFixture<SystemAccessComponent>;
    let service: SystemAccessService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [SystemAccessComponent]
      })
        .overrideTemplate(SystemAccessComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SystemAccessComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SystemAccessService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SystemAccess(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.systemAccesses && comp.systemAccesses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
