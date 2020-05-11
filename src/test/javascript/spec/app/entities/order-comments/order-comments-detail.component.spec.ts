import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RevalTestModule } from '../../../test.module';
import { OrderCommentsDetailComponent } from 'app/entities/order-comments/order-comments-detail.component';
import { OrderComments } from 'app/shared/model/order-comments.model';

describe('Component Tests', () => {
  describe('OrderComments Management Detail Component', () => {
    let comp: OrderCommentsDetailComponent;
    let fixture: ComponentFixture<OrderCommentsDetailComponent>;
    const route = ({ data: of({ orderComments: new OrderComments(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderCommentsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderCommentsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderCommentsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load orderComments on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderComments).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
