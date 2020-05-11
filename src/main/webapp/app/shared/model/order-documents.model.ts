import { Moment } from 'moment';
import { IOrders } from 'app/shared/model/orders.model';

export interface IOrderDocuments {
  id?: number;
  odoDocumentMimeTypeCd?: string;
  odoDocumentTypeCd?: string;
  odoExternalStorageLink?: string;
  odoExternalStorageFileName?: string;
  odoDocumentDueToCd?: string;
  odoDocumentDueFromCd?: string;
  odoDocumentDueDt?: Moment;
  odoDocumentRcvdDt?: Moment;
  odoDocumentStatusCd?: string;
  odoDocumentContentType?: string;
  odoDocument?: any;
  order?: IOrders;
}

export class OrderDocuments implements IOrderDocuments {
  constructor(
    public id?: number,
    public odoDocumentMimeTypeCd?: string,
    public odoDocumentTypeCd?: string,
    public odoExternalStorageLink?: string,
    public odoExternalStorageFileName?: string,
    public odoDocumentDueToCd?: string,
    public odoDocumentDueFromCd?: string,
    public odoDocumentDueDt?: Moment,
    public odoDocumentRcvdDt?: Moment,
    public odoDocumentStatusCd?: string,
    public odoDocumentContentType?: string,
    public odoDocument?: any,
    public order?: IOrders
  ) {}
}
