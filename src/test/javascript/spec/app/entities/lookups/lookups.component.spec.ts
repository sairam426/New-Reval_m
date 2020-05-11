import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { LookupsComponent } from 'app/entities/lookups/lookups.component';
import { LookupsService } from 'app/entities/lookups/lookups.service';
import { Lookups } from 'app/shared/model/lookups.model';

describe('Component Tests', () => {
  describe('Lookups Management Component', () => {
    let comp: LookupsComponent;
    let fixture: ComponentFixture<LookupsComponent>;
    let service: LookupsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupsComponent]
      })
        .overrideTemplate(LookupsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LookupsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LookupsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Lookups(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.lookups && comp.lookups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
