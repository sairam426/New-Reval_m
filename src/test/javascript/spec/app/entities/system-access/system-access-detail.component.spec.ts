import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { SystemAccessDetailComponent } from 'app/entities/system-access/system-access-detail.component';
import { SystemAccess } from 'app/shared/model/system-access.model';

describe('Component Tests', () => {
  describe('SystemAccess Management Detail Component', () => {
    let comp: SystemAccessDetailComponent;
    let fixture: ComponentFixture<SystemAccessDetailComponent>;
    const route = ({ data: of({ systemAccess: new SystemAccess(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [SystemAccessDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SystemAccessDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SystemAccessDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load systemAccess on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.systemAccess).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
