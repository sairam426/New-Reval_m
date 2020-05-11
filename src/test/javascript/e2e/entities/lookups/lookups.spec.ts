import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LookupsComponentsPage, LookupsDeleteDialog, LookupsUpdatePage } from './lookups.page-object';

const expect = chai.expect;

describe('Lookups e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lookupsComponentsPage: LookupsComponentsPage;
  let lookupsUpdatePage: LookupsUpdatePage;
  let lookupsDeleteDialog: LookupsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Lookups', async () => {
    await navBarPage.goToEntity('lookups');
    lookupsComponentsPage = new LookupsComponentsPage();
    await browser.wait(ec.visibilityOf(lookupsComponentsPage.title), 5000);
    expect(await lookupsComponentsPage.getTitle()).to.eq('revalApp.lookups.home.title');
    await browser.wait(ec.or(ec.visibilityOf(lookupsComponentsPage.entities), ec.visibilityOf(lookupsComponentsPage.noResult)), 1000);
  });

  it('should load create Lookups page', async () => {
    await lookupsComponentsPage.clickOnCreateButton();
    lookupsUpdatePage = new LookupsUpdatePage();
    expect(await lookupsUpdatePage.getPageTitle()).to.eq('revalApp.lookups.home.createOrEditLabel');
    await lookupsUpdatePage.cancel();
  });

  it('should create and save Lookups', async () => {
    const nbButtonsBeforeCreate = await lookupsComponentsPage.countDeleteButtons();

    await lookupsComponentsPage.clickOnCreateButton();

    await promise.all([
      lookupsUpdatePage.setLkcCodeInput('lkcCode'),
      lookupsUpdatePage.setLkcSubCodeInput('lkcSubCode'),
      lookupsUpdatePage.setLkcSortInput('lkcSort'),
      lookupsUpdatePage.setLkcDescInput('lkcDesc'),
      lookupsUpdatePage.lookUpTypeSelectLastOption()
    ]);

    expect(await lookupsUpdatePage.getLkcCodeInput()).to.eq('lkcCode', 'Expected LkcCode value to be equals to lkcCode');
    expect(await lookupsUpdatePage.getLkcSubCodeInput()).to.eq('lkcSubCode', 'Expected LkcSubCode value to be equals to lkcSubCode');
    expect(await lookupsUpdatePage.getLkcSortInput()).to.eq('lkcSort', 'Expected LkcSort value to be equals to lkcSort');
    const selectedLkcEnabledInd = lookupsUpdatePage.getLkcEnabledIndInput();
    if (await selectedLkcEnabledInd.isSelected()) {
      await lookupsUpdatePage.getLkcEnabledIndInput().click();
      expect(await lookupsUpdatePage.getLkcEnabledIndInput().isSelected(), 'Expected lkcEnabledInd not to be selected').to.be.false;
    } else {
      await lookupsUpdatePage.getLkcEnabledIndInput().click();
      expect(await lookupsUpdatePage.getLkcEnabledIndInput().isSelected(), 'Expected lkcEnabledInd to be selected').to.be.true;
    }
    expect(await lookupsUpdatePage.getLkcDescInput()).to.eq('lkcDesc', 'Expected LkcDesc value to be equals to lkcDesc');

    await lookupsUpdatePage.save();
    expect(await lookupsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await lookupsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Lookups', async () => {
    const nbButtonsBeforeDelete = await lookupsComponentsPage.countDeleteButtons();
    await lookupsComponentsPage.clickOnLastDeleteButton();

    lookupsDeleteDialog = new LookupsDeleteDialog();
    expect(await lookupsDeleteDialog.getDialogTitle()).to.eq('revalApp.lookups.delete.question');
    await lookupsDeleteDialog.clickOnConfirmButton();

    expect(await lookupsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
