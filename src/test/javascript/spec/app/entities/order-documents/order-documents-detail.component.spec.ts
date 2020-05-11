import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { RevalTestModule } from '../../../test.module';
import { OrderDocumentsDetailComponent } from 'app/entities/order-documents/order-documents-detail.component';
import { OrderDocuments } from 'app/shared/model/order-documents.model';

describe('Component Tests', () => {
  describe('OrderDocuments Management Detail Component', () => {
    let comp: OrderDocumentsDetailComponent;
    let fixture: ComponentFixture<OrderDocumentsDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ orderDocuments: new OrderDocuments(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RevalTestModule],
        declarations: [OrderDocumentsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderDocumentsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderDocumentsDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load orderDocuments on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderDocuments).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
