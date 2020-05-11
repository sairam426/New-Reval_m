import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CompaniesComponentsPage, CompaniesDeleteDialog, CompaniesUpdatePage } from './companies.page-object';

const expect = chai.expect;

describe('Companies e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let companiesComponentsPage: CompaniesComponentsPage;
  let companiesUpdatePage: CompaniesUpdatePage;
  let companiesDeleteDialog: CompaniesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Companies', async () => {
    await navBarPage.goToEntity('companies');
    companiesComponentsPage = new CompaniesComponentsPage();
    await browser.wait(ec.visibilityOf(companiesComponentsPage.title), 5000);
    expect(await companiesComponentsPage.getTitle()).to.eq('revalApp.companies.home.title');
    await browser.wait(ec.or(ec.visibilityOf(companiesComponentsPage.entities), ec.visibilityOf(companiesComponentsPage.noResult)), 1000);
  });

  it('should load create Companies page', async () => {
    await companiesComponentsPage.clickOnCreateButton();
    companiesUpdatePage = new CompaniesUpdatePage();
    expect(await companiesUpdatePage.getPageTitle()).to.eq('revalApp.companies.home.createOrEditLabel');
    await companiesUpdatePage.cancel();
  });

  it('should create and save Companies', async () => {
    const nbButtonsBeforeCreate = await companiesComponentsPage.countDeleteButtons();

    await companiesComponentsPage.clickOnCreateButton();

    await promise.all([
      companiesUpdatePage.setCmpNbrInput('cmpNbr'),
      companiesUpdatePage.setCmpNameInput('cmpName'),
      companiesUpdatePage.setCmpServiceTypeCdInput('cmpServiceTypeCd'),
      companiesUpdatePage.setCmpShortNameInput('cmpShortName'),
      companiesUpdatePage.setCmpStatusCdInput('cmpStatusCd'),
      companiesUpdatePage.setCmpTypeCdInput('cmpTypeCd'),
      companiesUpdatePage.setCmpAddress1Input('cmpAddress1'),
      companiesUpdatePage.setCmpAddress2Input('cmpAddress2'),
      companiesUpdatePage.setCmpCityInput('cmpCity'),
      companiesUpdatePage.setCmpStateCdInput('cmpStateCd'),
      companiesUpdatePage.setCmpZipInput('cmpZip')
    ]);

    expect(await companiesUpdatePage.getCmpNbrInput()).to.eq('cmpNbr', 'Expected CmpNbr value to be equals to cmpNbr');
    expect(await companiesUpdatePage.getCmpNameInput()).to.eq('cmpName', 'Expected CmpName value to be equals to cmpName');
    expect(await companiesUpdatePage.getCmpServiceTypeCdInput()).to.eq(
      'cmpServiceTypeCd',
      'Expected CmpServiceTypeCd value to be equals to cmpServiceTypeCd'
    );
    expect(await companiesUpdatePage.getCmpShortNameInput()).to.eq(
      'cmpShortName',
      'Expected CmpShortName value to be equals to cmpShortName'
    );
    expect(await companiesUpdatePage.getCmpStatusCdInput()).to.eq('cmpStatusCd', 'Expected CmpStatusCd value to be equals to cmpStatusCd');
    expect(await companiesUpdatePage.getCmpTypeCdInput()).to.eq('cmpTypeCd', 'Expected CmpTypeCd value to be equals to cmpTypeCd');
    expect(await companiesUpdatePage.getCmpAddress1Input()).to.eq('cmpAddress1', 'Expected CmpAddress1 value to be equals to cmpAddress1');
    expect(await companiesUpdatePage.getCmpAddress2Input()).to.eq('cmpAddress2', 'Expected CmpAddress2 value to be equals to cmpAddress2');
    expect(await companiesUpdatePage.getCmpCityInput()).to.eq('cmpCity', 'Expected CmpCity value to be equals to cmpCity');
    expect(await companiesUpdatePage.getCmpStateCdInput()).to.eq('cmpStateCd', 'Expected CmpStateCd value to be equals to cmpStateCd');
    expect(await companiesUpdatePage.getCmpZipInput()).to.eq('cmpZip', 'Expected CmpZip value to be equals to cmpZip');
    const selectedCmpEnabledInd = companiesUpdatePage.getCmpEnabledIndInput();
    if (await selectedCmpEnabledInd.isSelected()) {
      await companiesUpdatePage.getCmpEnabledIndInput().click();
      expect(await companiesUpdatePage.getCmpEnabledIndInput().isSelected(), 'Expected cmpEnabledInd not to be selected').to.be.false;
    } else {
      await companiesUpdatePage.getCmpEnabledIndInput().click();
      expect(await companiesUpdatePage.getCmpEnabledIndInput().isSelected(), 'Expected cmpEnabledInd to be selected').to.be.true;
    }

    await companiesUpdatePage.save();
    expect(await companiesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await companiesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Companies', async () => {
    const nbButtonsBeforeDelete = await companiesComponentsPage.countDeleteButtons();
    await companiesComponentsPage.clickOnLastDeleteButton();

    companiesDeleteDialog = new CompaniesDeleteDialog();
    expect(await companiesDeleteDialog.getDialogTitle()).to.eq('revalApp.companies.delete.question');
    await companiesDeleteDialog.clickOnConfirmButton();

    expect(await companiesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
