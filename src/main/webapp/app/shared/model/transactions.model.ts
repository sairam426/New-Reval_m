import { Moment } from 'moment';
import { ITransactionDetails } from 'app/shared/model/transaction-details.model';

export interface ITransactions {
  id?: number;
  txnEntityId?: number;
  txnEntityNbr?: string;
  txnTcdCode?: string;
  txnPostDt?: Moment;
  txnStatusCd?: string;
  txnDetails?: ITransactionDetails[];
}

export class Transactions implements ITransactions {
  constructor(
    public id?: number,
    public txnEntityId?: number,
    public txnEntityNbr?: string,
    public txnTcdCode?: string,
    public txnPostDt?: Moment,
    public txnStatusCd?: string,
    public txnDetails?: ITransactionDetails[]
  ) {}
}
