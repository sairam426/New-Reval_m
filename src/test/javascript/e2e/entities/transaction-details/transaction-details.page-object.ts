import { element, by, ElementFinder } from 'protractor';

export class TransactionDetailsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-transaction-details div table .btn-danger'));
  title = element.all(by.css('jhi-transaction-details div h2#page-heading span')).first();
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

export class TransactionDetailsUpdatePage {
  pageTitle = element(by.id('jhi-transaction-details-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  txdPrmCodeInput = element(by.id('field_txdPrmCode'));
  txdValueInput = element(by.id('field_txdValue'));

  transactionSelect = element(by.id('field_transaction'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTxdPrmCodeInput(txdPrmCode: string): Promise<void> {
    await this.txdPrmCodeInput.sendKeys(txdPrmCode);
  }

  async getTxdPrmCodeInput(): Promise<string> {
    return await this.txdPrmCodeInput.getAttribute('value');
  }

  async setTxdValueInput(txdValue: string): Promise<void> {
    await this.txdValueInput.sendKeys(txdValue);
  }

  async getTxdValueInput(): Promise<string> {
    return await this.txdValueInput.getAttribute('value');
  }

  async transactionSelectLastOption(): Promise<void> {
    await this.transactionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async transactionSelectOption(option: string): Promise<void> {
    await this.transactionSelect.sendKeys(option);
  }

  getTransactionSelect(): ElementFinder {
    return this.transactionSelect;
  }

  async getTransactionSelectedOption(): Promise<string> {
    return await this.transactionSelect.element(by.css('option:checked')).getText();
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

export class TransactionDetailsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-transactionDetails-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-transactionDetails'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
