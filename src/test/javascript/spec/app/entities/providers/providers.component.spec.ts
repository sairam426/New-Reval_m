import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { ProvidersComponent } from 'app/entities/providers/providers.component';
import { ProvidersService } from 'app/entities/providers/providers.service';
import { Providers } from 'app/shared/model/providers.model';

describe('Component Tests', () => {
  describe('Providers Management Component', () => {
    let comp: ProvidersComponent;
    let fixture: ComponentFixture<ProvidersComponent>;
    let service: ProvidersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [ProvidersComponent]
      })
        .overrideTemplate(ProvidersComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProvidersComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProvidersService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Providers(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.providers && comp.providers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
