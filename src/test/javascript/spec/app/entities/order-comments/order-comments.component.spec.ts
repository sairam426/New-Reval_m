import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { OrderCommentsComponent } from 'app/entities/order-comments/order-comments.component';
import { OrderCommentsService } from 'app/entities/order-comments/order-comments.service';
import { OrderComments } from 'app/shared/model/order-comments.model';

describe('Component Tests', () => {
  describe('OrderComments Management Component', () => {
    let comp: OrderCommentsComponent;
    let fixture: ComponentFixture<OrderCommentsComponent>;
    let service: OrderCommentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderCommentsComponent]
      })
        .overrideTemplate(OrderCommentsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderCommentsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderCommentsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OrderComments(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.orderComments && comp.orderComments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
