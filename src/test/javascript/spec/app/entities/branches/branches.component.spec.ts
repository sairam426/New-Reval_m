import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { BranchesComponent } from 'app/entities/branches/branches.component';
import { BranchesService } from 'app/entities/branches/branches.service';
import { Branches } from 'app/shared/model/branches.model';

describe('Component Tests', () => {
  describe('Branches Management Component', () => {
    let comp: BranchesComponent;
    let fixture: ComponentFixture<BranchesComponent>;
    let service: BranchesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [BranchesComponent]
      })
        .overrideTemplate(BranchesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BranchesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BranchesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Branches(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.branches && comp.branches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
