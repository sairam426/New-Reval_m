import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { LookupTypesComponent } from 'app/entities/lookup-types/lookup-types.component';
import { LookupTypesService } from 'app/entities/lookup-types/lookup-types.service';
import { LookupTypes } from 'app/shared/model/lookup-types.model';

describe('Component Tests', () => {
  describe('LookupTypes Management Component', () => {
    let comp: LookupTypesComponent;
    let fixture: ComponentFixture<LookupTypesComponent>;
    let service: LookupTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupTypesComponent]
      })
        .overrideTemplate(LookupTypesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LookupTypesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LookupTypesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LookupTypes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.lookupTypes && comp.lookupTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
