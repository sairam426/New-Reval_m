import { element, by, ElementFinder } from 'protractor';

export class SystemAccessComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-system-access div table .btn-danger'));
  title = element.all(by.css('jhi-system-access div h2#page-heading span')).first();
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

export class SystemAccessUpdatePage {
  pageTitle = element(by.id('jhi-system-access-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  sacAccessKeyInput = element(by.id('field_sacAccessKey'));
  sacAccessTypeCdInput = element(by.id('field_sacAccessTypeCd'));
  sacAccessValueInput = element(by.id('field_sacAccessValue'));
  sacAllowedIndInput = element(by.id('field_sacAllowedInd'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSacAccessKeyInput(sacAccessKey: string): Promise<void> {
    await this.sacAccessKeyInput.sendKeys(sacAccessKey);
  }

  async getSacAccessKeyInput(): Promise<string> {
    return await this.sacAccessKeyInput.getAttribute('value');
  }

  async setSacAccessTypeCdInput(sacAccessTypeCd: string): Promise<void> {
    await this.sacAccessTypeCdInput.sendKeys(sacAccessTypeCd);
  }

  async getSacAccessTypeCdInput(): Promise<string> {
    return await this.sacAccessTypeCdInput.getAttribute('value');
  }

  async setSacAccessValueInput(sacAccessValue: string): Promise<void> {
    await this.sacAccessValueInput.sendKeys(sacAccessValue);
  }

  async getSacAccessValueInput(): Promise<string> {
    return await this.sacAccessValueInput.getAttribute('value');
  }

  getSacAllowedIndInput(): ElementFinder {
    return this.sacAllowedIndInput;
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

export class SystemAccessDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-systemAccess-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-systemAccess'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
