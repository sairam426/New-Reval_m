import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { BranchesDetailComponent } from 'app/entities/branches/branches-detail.component';
import { Branches } from 'app/shared/model/branches.model';

describe('Component Tests', () => {
  describe('Branches Management Detail Component', () => {
    let comp: BranchesDetailComponent;
    let fixture: ComponentFixture<BranchesDetailComponent>;
    const route = ({ data: of({ branches: new Branches(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [BranchesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BranchesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BranchesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load branches on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.branches).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
