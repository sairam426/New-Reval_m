import { ITransactions } from 'app/shared/model/transactions.model';

export interface ITransactionDetails {
  id?: number;
  txdPrmCode?: string;
  txdValue?: string;
  transaction?: ITransactions;
}

export class TransactionDetails implements ITransactionDetails {
  constructor(public id?: number, public txdPrmCode?: string, public txdValue?: string, public transaction?: ITransactions) {}
}
