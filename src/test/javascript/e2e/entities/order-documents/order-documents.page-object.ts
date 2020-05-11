import { element, by, ElementFinder } from 'protractor';

export class OrderDocumentsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-documents div table .btn-danger'));
  title = element.all(by.css('jhi-order-documents div h2#page-heading span')).first();
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

export class OrderDocumentsUpdatePage {
  pageTitle = element(by.id('jhi-order-documents-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  odoDocumentMimeTypeCdInput = element(by.id('field_odoDocumentMimeTypeCd'));
  odoDocumentTypeCdInput = element(by.id('field_odoDocumentTypeCd'));
  odoExternalStorageLinkInput = element(by.id('field_odoExternalStorageLink'));
  odoExternalStorageFileNameInput = element(by.id('field_odoExternalStorageFileName'));
  odoDocumentDueToCdInput = element(by.id('field_odoDocumentDueToCd'));
  odoDocumentDueFromCdInput = element(by.id('field_odoDocumentDueFromCd'));
  odoDocumentDueDtInput = element(by.id('field_odoDocumentDueDt'));
  odoDocumentRcvdDtInput = element(by.id('field_odoDocumentRcvdDt'));
  odoDocumentStatusCdInput = element(by.id('field_odoDocumentStatusCd'));
  odoDocumentInput = element(by.id('file_odoDocument'));

  orderSelect = element(by.id('field_order'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOdoDocumentMimeTypeCdInput(odoDocumentMimeTypeCd: string): Promise<void> {
    await this.odoDocumentMimeTypeCdInput.sendKeys(odoDocumentMimeTypeCd);
  }

  async getOdoDocumentMimeTypeCdInput(): Promise<string> {
    return await this.odoDocumentMimeTypeCdInput.getAttribute('value');
  }

  async setOdoDocumentTypeCdInput(odoDocumentTypeCd: string): Promise<void> {
    await this.odoDocumentTypeCdInput.sendKeys(odoDocumentTypeCd);
  }

  async getOdoDocumentTypeCdInput(): Promise<string> {
    return await this.odoDocumentTypeCdInput.getAttribute('value');
  }

  async setOdoExternalStorageLinkInput(odoExternalStorageLink: string): Promise<void> {
    await this.odoExternalStorageLinkInput.sendKeys(odoExternalStorageLink);
  }

  async getOdoExternalStorageLinkInput(): Promise<string> {
    return await this.odoExternalStorageLinkInput.getAttribute('value');
  }

  async setOdoExternalStorageFileNameInput(odoExternalStorageFileName: string): Promise<void> {
    await this.odoExternalStorageFileNameInput.sendKeys(odoExternalStorageFileName);
  }

  async getOdoExternalStorageFileNameInput(): Promise<string> {
    return await this.odoExternalStorageFileNameInput.getAttribute('value');
  }

  async setOdoDocumentDueToCdInput(odoDocumentDueToCd: string): Promise<void> {
    await this.odoDocumentDueToCdInput.sendKeys(odoDocumentDueToCd);
  }

  async getOdoDocumentDueToCdInput(): Promise<string> {
    return await this.odoDocumentDueToCdInput.getAttribute('value');
  }

  async setOdoDocumentDueFromCdInput(odoDocumentDueFromCd: string): Promise<void> {
    await this.odoDocumentDueFromCdInput.sendKeys(odoDocumentDueFromCd);
  }

  async getOdoDocumentDueFromCdInput(): Promise<string> {
    return await this.odoDocumentDueFromCdInput.getAttribute('value');
  }

  async setOdoDocumentDueDtInput(odoDocumentDueDt: string): Promise<void> {
    await this.odoDocumentDueDtInput.sendKeys(odoDocumentDueDt);
  }

  async getOdoDocumentDueDtInput(): Promise<string> {
    return await this.odoDocumentDueDtInput.getAttribute('value');
  }

  async setOdoDocumentRcvdDtInput(odoDocumentRcvdDt: string): Promise<void> {
    await this.odoDocumentRcvdDtInput.sendKeys(odoDocumentRcvdDt);
  }

  async getOdoDocumentRcvdDtInput(): Promise<string> {
    return await this.odoDocumentRcvdDtInput.getAttribute('value');
  }

  async setOdoDocumentStatusCdInput(odoDocumentStatusCd: string): Promise<void> {
    await this.odoDocumentStatusCdInput.sendKeys(odoDocumentStatusCd);
  }

  async getOdoDocumentStatusCdInput(): Promise<string> {
    return await this.odoDocumentStatusCdInput.getAttribute('value');
  }

  async setOdoDocumentInput(odoDocument: string): Promise<void> {
    await this.odoDocumentInput.sendKeys(odoDocument);
  }

  async getOdoDocumentInput(): Promise<string> {
    return await this.odoDocumentInput.getAttribute('value');
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

export class OrderDocumentsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderDocuments-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderDocuments'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
