import { element, by, ElementFinder } from 'protractor';

export class ProvidersComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-providers div table .btn-danger'));
  title = element.all(by.css('jhi-providers div h2#page-heading span')).first();
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

export class ProvidersUpdatePage {
  pageTitle = element(by.id('jhi-providers-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  proNbrInput = element(by.id('field_proNbr'));
  proNameInput = element(by.id('field_proName'));
  proShortNameInput = element(by.id('field_proShortName'));
  proStatusCdInput = element(by.id('field_proStatusCd'));
  proTypeCdInput = element(by.id('field_proTypeCd'));
  proGroupCdInput = element(by.id('field_proGroupCd'));
  proLicenseNbrInput = element(by.id('field_proLicenseNbr'));
  proLicenseStatusCdInput = element(by.id('field_proLicenseStatusCd'));
  proAddress1Input = element(by.id('field_proAddress1'));
  proAddress2Input = element(by.id('field_proAddress2'));
  proCityInput = element(by.id('field_proCity'));
  proStateCdInput = element(by.id('field_proStateCd'));
  proZipInput = element(by.id('field_proZip'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProNbrInput(proNbr: string): Promise<void> {
    await this.proNbrInput.sendKeys(proNbr);
  }

  async getProNbrInput(): Promise<string> {
    return await this.proNbrInput.getAttribute('value');
  }

  async setProNameInput(proName: string): Promise<void> {
    await this.proNameInput.sendKeys(proName);
  }

  async getProNameInput(): Promise<string> {
    return await this.proNameInput.getAttribute('value');
  }

  async setProShortNameInput(proShortName: string): Promise<void> {
    await this.proShortNameInput.sendKeys(proShortName);
  }

  async getProShortNameInput(): Promise<string> {
    return await this.proShortNameInput.getAttribute('value');
  }

  async setProStatusCdInput(proStatusCd: string): Promise<void> {
    await this.proStatusCdInput.sendKeys(proStatusCd);
  }

  async getProStatusCdInput(): Promise<string> {
    return await this.proStatusCdInput.getAttribute('value');
  }

  async setProTypeCdInput(proTypeCd: string): Promise<void> {
    await this.proTypeCdInput.sendKeys(proTypeCd);
  }

  async getProTypeCdInput(): Promise<string> {
    return await this.proTypeCdInput.getAttribute('value');
  }

  async setProGroupCdInput(proGroupCd: string): Promise<void> {
    await this.proGroupCdInput.sendKeys(proGroupCd);
  }

  async getProGroupCdInput(): Promise<string> {
    return await this.proGroupCdInput.getAttribute('value');
  }

  async setProLicenseNbrInput(proLicenseNbr: string): Promise<void> {
    await this.proLicenseNbrInput.sendKeys(proLicenseNbr);
  }

  async getProLicenseNbrInput(): Promise<string> {
    return await this.proLicenseNbrInput.getAttribute('value');
  }

  async setProLicenseStatusCdInput(proLicenseStatusCd: string): Promise<void> {
    await this.proLicenseStatusCdInput.sendKeys(proLicenseStatusCd);
  }

  async getProLicenseStatusCdInput(): Promise<string> {
    return await this.proLicenseStatusCdInput.getAttribute('value');
  }

  async setProAddress1Input(proAddress1: string): Promise<void> {
    await this.proAddress1Input.sendKeys(proAddress1);
  }

  async getProAddress1Input(): Promise<string> {
    return await this.proAddress1Input.getAttribute('value');
  }

  async setProAddress2Input(proAddress2: string): Promise<void> {
    await this.proAddress2Input.sendKeys(proAddress2);
  }

  async getProAddress2Input(): Promise<string> {
    return await this.proAddress2Input.getAttribute('value');
  }

  async setProCityInput(proCity: string): Promise<void> {
    await this.proCityInput.sendKeys(proCity);
  }

  async getProCityInput(): Promise<string> {
    return await this.proCityInput.getAttribute('value');
  }

  async setProStateCdInput(proStateCd: string): Promise<void> {
    await this.proStateCdInput.sendKeys(proStateCd);
  }

  async getProStateCdInput(): Promise<string> {
    return await this.proStateCdInput.getAttribute('value');
  }

  async setProZipInput(proZip: string): Promise<void> {
    await this.proZipInput.sendKeys(proZip);
  }

  async getProZipInput(): Promise<string> {
    return await this.proZipInput.getAttribute('value');
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

export class ProvidersDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-providers-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-providers'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
