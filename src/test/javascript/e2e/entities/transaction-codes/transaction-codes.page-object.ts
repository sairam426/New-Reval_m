import { element, by, ElementFinder } from 'protractor';

export class TransactionCodesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-transaction-codes div table .btn-danger'));
  title = element.all(by.css('jhi-transaction-codes div h2#page-heading span')).first();
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

export class TransactionCodesUpdatePage {
  pageTitle = element(by.id('jhi-transaction-codes-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  tcdCodeInput = element(by.id('field_tcdCode'));
  tcdEntityGroupCdInput = element(by.id('field_tcdEntityGroupCd'));
  tcdDescInput = element(by.id('field_tcdDesc'));
  tcdEnabledIndInput = element(by.id('field_tcdEnabledInd'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTcdCodeInput(tcdCode: string): Promise<void> {
    await this.tcdCodeInput.sendKeys(tcdCode);
  }

  async getTcdCodeInput(): Promise<string> {
    return await this.tcdCodeInput.getAttribute('value');
  }

  async setTcdEntityGroupCdInput(tcdEntityGroupCd: string): Promise<void> {
    await this.tcdEntityGroupCdInput.sendKeys(tcdEntityGroupCd);
  }

  async getTcdEntityGroupCdInput(): Promise<string> {
    return await this.tcdEntityGroupCdInput.getAttribute('value');
  }

  async setTcdDescInput(tcdDesc: string): Promise<void> {
    await this.tcdDescInput.sendKeys(tcdDesc);
  }

  async getTcdDescInput(): Promise<string> {
    return await this.tcdDescInput.getAttribute('value');
  }

  getTcdEnabledIndInput(): ElementFinder {
    return this.tcdEnabledIndInput;
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

export class TransactionCodesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-transactionCodes-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-transactionCodes'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
