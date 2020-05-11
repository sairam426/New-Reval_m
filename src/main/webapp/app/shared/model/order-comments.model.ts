import { Moment } from 'moment';
import { IOrders } from 'app/shared/model/orders.model';

export interface IOrderComments {
  id?: number;
  ocmCommentTypeCd?: string;
  ocmCommentSubTypeCd?: string;
  ocmCommentBy?: string;
  ocmCommentDate?: Moment;
  ocmCommentImpInd?: boolean;
  ocmComment?: string;
  order?: IOrders;
}

export class OrderComments implements IOrderComments {
  constructor(
    public id?: number,
    public ocmCommentTypeCd?: string,
    public ocmCommentSubTypeCd?: string,
    public ocmCommentBy?: string,
    public ocmCommentDate?: Moment,
    public ocmCommentImpInd?: boolean,
    public ocmComment?: string,
    public order?: IOrders
  ) {
    this.ocmCommentImpInd = this.ocmCommentImpInd || false;
  }
}
