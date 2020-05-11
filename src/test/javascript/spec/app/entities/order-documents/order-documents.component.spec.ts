import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RevalTestModule } from '../../../test.module';
import { OrderDocumentsComponent } from 'app/entities/order-documents/order-documents.component';
import { OrderDocumentsService } from 'app/entities/order-documents/order-documents.service';
import { OrderDocuments } from 'app/shared/model/order-documents.model';

describe('Component Tests', () => {
  describe('OrderDocuments Management Component', () => {
    let comp: OrderDocumentsComponent;
    let fixture: ComponentFixture<OrderDocumentsComponent>;
    let service: OrderDocumentsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderDocumentsComponent]
      })
        .overrideTemplate(OrderDocumentsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderDocumentsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderDocumentsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OrderDocuments(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.orderDocuments && comp.orderDocuments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
