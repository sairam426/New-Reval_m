import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { LookupsDetailComponent } from 'app/entities/lookups/lookups-detail.component';
import { Lookups } from 'app/shared/model/lookups.model';

describe('Component Tests', () => {
  describe('Lookups Management Detail Component', () => {
    let comp: LookupsDetailComponent;
    let fixture: ComponentFixture<LookupsDetailComponent>;
    const route = ({ data: of({ lookups: new Lookups(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LookupsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LookupsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lookups on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lookups).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
