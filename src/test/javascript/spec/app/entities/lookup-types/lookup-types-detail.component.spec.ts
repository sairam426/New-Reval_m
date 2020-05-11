import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { LookupTypesDetailComponent } from 'app/entities/lookup-types/lookup-types-detail.component';
import { LookupTypes } from 'app/shared/model/lookup-types.model';

describe('Component Tests', () => {
  describe('LookupTypes Management Detail Component', () => {
    let comp: LookupTypesDetailComponent;
    let fixture: ComponentFixture<LookupTypesDetailComponent>;
    const route = ({ data: of({ lookupTypes: new LookupTypes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [LookupTypesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LookupTypesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LookupTypesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lookupTypes on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lookupTypes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
