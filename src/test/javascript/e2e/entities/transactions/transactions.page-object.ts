import { element, by, ElementFinder } from 'protractor';

export class TransactionsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-transactions div table .btn-danger'));
  title = element.all(by.css('jhi-transactions div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class TransactionsUpdatePage {
  pageTitle = element(by.id('jhi-transactions-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  txnEntityIdInput = element(by.id('field_txnEntityId'));
  txnEntityNbrInput = element(by.id('field_txnEntityNbr'));
  txnTcdCodeInput = element(by.id('field_txnTcdCode'));
  txnPostDtInput = element(by.id('field_txnPostDt'));
  txnStatusCdInput = element(by.id('field_txnStatusCd'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTxnEntityIdInput(txnEntityId: string): Promise<void> {
    await this.txnEntityIdInput.sendKeys(txnEntityId);
  }

  async getTxnEntityIdInput(): Promise<string> {
    return await this.txnEntityIdInput.getAttribute('value');
  }

  async setTxnEntityNbrInput(txnEntityNbr: string): Promise<void> {
    await this.txnEntityNbrInput.sendKeys(txnEntityNbr);
  }

  async getTxnEntityNbrInput(): Promise<string> {
    return await this.txnEntityNbrInput.getAttribute('value');
  }

  async setTxnTcdCodeInput(txnTcdCode: string): Promise<void> {
    await this.txnTcdCodeInput.sendKeys(txnTcdCode);
  }

  async getTxnTcdCodeInput(): Promise<string> {
    return await this.txnTcdCodeInput.getAttribute('value');
  }

  async setTxnPostDtInput(txnPostDt: string): Promise<void> {
    await this.txnPostDtInput.sendKeys(txnPostDt);
  }

  async getTxnPostDtInput(): Promise<string> {
    return await this.txnPostDtInput.getAttribute('value');
  }

  async setTxnStatusCdInput(txnStatusCd: string): Promise<void> {
    await this.txnStatusCdInput.sendKeys(txnStatusCd);
  }

  async getTxnStatusCdInput(): Promise<string> {
    return await this.txnStatusCdInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class TransactionsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-transactions-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-transactions'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
