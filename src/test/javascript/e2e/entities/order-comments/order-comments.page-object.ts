import { element, by, ElementFinder } from 'protractor';

export class OrderCommentsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-comments div table .btn-danger'));
  title = element.all(by.css('jhi-order-comments div h2#page-heading span')).first();
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

export class OrderCommentsUpdatePage {
  pageTitle = element(by.id('jhi-order-comments-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  ocmCommentTypeCdInput = element(by.id('field_ocmCommentTypeCd'));
  ocmCommentSubTypeCdInput = element(by.id('field_ocmCommentSubTypeCd'));
  ocmCommentByInput = element(by.id('field_ocmCommentBy'));
  ocmCommentDateInput = element(by.id('field_ocmCommentDate'));
  ocmCommentImpIndInput = element(by.id('field_ocmCommentImpInd'));
  ocmCommentInput = element(by.id('field_ocmComment'));

  orderSelect = element(by.id('field_order'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOcmCommentTypeCdInput(ocmCommentTypeCd: string): Promise<void> {
    await this.ocmCommentTypeCdInput.sendKeys(ocmCommentTypeCd);
  }

  async getOcmCommentTypeCdInput(): Promise<string> {
    return await this.ocmCommentTypeCdInput.getAttribute('value');
  }

  async setOcmCommentSubTypeCdInput(ocmCommentSubTypeCd: string): Promise<void> {
    await this.ocmCommentSubTypeCdInput.sendKeys(ocmCommentSubTypeCd);
  }

  async getOcmCommentSubTypeCdInput(): Promise<string> {
    return await this.ocmCommentSubTypeCdInput.getAttribute('value');
  }

  async setOcmCommentByInput(ocmCommentBy: string): Promise<void> {
    await this.ocmCommentByInput.sendKeys(ocmCommentBy);
  }

  async getOcmCommentByInput(): Promise<string> {
    return await this.ocmCommentByInput.getAttribute('value');
  }

  async setOcmCommentDateInput(ocmCommentDate: string): Promise<void> {
    await this.ocmCommentDateInput.sendKeys(ocmCommentDate);
  }

  async getOcmCommentDateInput(): Promise<string> {
    return await this.ocmCommentDateInput.getAttribute('value');
  }

  getOcmCommentImpIndInput(): ElementFinder {
    return this.ocmCommentImpIndInput;
  }

  async setOcmCommentInput(ocmComment: string): Promise<void> {
    await this.ocmCommentInput.sendKeys(ocmComment);
  }

  async getOcmCommentInput(): Promise<string> {
    return await this.ocmCommentInput.getAttribute('value');
  }

  async orderSelectLastOption(): Promise<void> {
    await this.orderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async orderSelectOption(option: string): Promise<void> {
    await this.orderSelect.sendKeys(option);
  }

  getOrderSelect(): ElementFinder {
    return this.orderSelect;
  }

  async getOrderSelectedOption(): Promise<string> {
    return await this.orderSelect.element(by.css('option:checked')).getText();
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

export class OrderCommentsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderComments-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderComments'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
