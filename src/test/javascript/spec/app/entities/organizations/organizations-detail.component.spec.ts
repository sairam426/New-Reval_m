import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { OrganizationsDetailComponent } from 'app/entities/organizations/organizations-detail.component';
import { Organizations } from 'app/shared/model/organizations.model';

describe('Component Tests', () => {
  describe('Organizations Management Detail Component', () => {
    let comp: OrganizationsDetailComponent;
    let fixture: ComponentFixture<OrganizationsDetailComponent>;
    const route = ({ data: of({ organizations: new Organizations(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrganizationsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrganizationsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganizationsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load organizations on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organizations).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
