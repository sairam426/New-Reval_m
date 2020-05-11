import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { CompaniesDetailComponent } from 'app/entities/companies/companies-detail.component';
import { Companies } from 'app/shared/model/companies.model';

describe('Component Tests', () => {
  describe('Companies Management Detail Component', () => {
    let comp: CompaniesDetailComponent;
    let fixture: ComponentFixture<CompaniesDetailComponent>;
    const route = ({ data: of({ companies: new Companies(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [CompaniesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CompaniesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompaniesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load companies on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.companies).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
