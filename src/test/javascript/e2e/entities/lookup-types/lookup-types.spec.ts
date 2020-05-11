import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LookupTypesComponentsPage, LookupTypesDeleteDialog, LookupTypesUpdatePage } from './lookup-types.page-object';

const expect = chai.expect;

describe('LookupTypes e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lookupTypesComponentsPage: LookupTypesComponentsPage;
  let lookupTypesUpdatePage: LookupTypesUpdatePage;
  let lookupTypesDeleteDialog: LookupTypesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load LookupTypes', async () => {
    await navBarPage.goToEntity('lookup-types');
    lookupTypesComponentsPage = new LookupTypesComponentsPage();
    await browser.wait(ec.visibilityOf(lookupTypesComponentsPage.title), 5000);
    expect(await lookupTypesComponentsPage.getTitle()).to.eq('revalApp.lookupTypes.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(lookupTypesComponentsPage.entities), ec.visibilityOf(lookupTypesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create LookupTypes page', async () => {
    await lookupTypesComponentsPage.clickOnCreateButton();
    lookupTypesUpdatePage = new LookupTypesUpdatePage();
    expect(await lookupTypesUpdatePage.getPageTitle()).to.eq('revalApp.lookupTypes.home.createOrEditLabel');
    await lookupTypesUpdatePage.cancel();
  });

  it('should create and save LookupTypes', async () => {
    const nbButtonsBeforeCreate = await lookupTypesComponentsPage.countDeleteButtons();

    await lookupTypesComponentsPage.clickOnCreateButton();

    await promise.all([lookupTypesUpdatePage.setLktTypeInput('lktType'), lookupTypesUpdatePage.setLktDescInput('lktDesc')]);

    expect(await lookupTypesUpdatePage.getLktTypeInput()).to.eq('lktType', 'Expected LktType value to be equals to lktType');
    expect(await lookupTypesUpdatePage.getLktDescInput()).to.eq('lktDesc', 'Expected LktDesc value to be equals to lktDesc');

    await lookupTypesUpdatePage.save();
    expect(await lookupTypesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await lookupTypesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last LookupTypes', async () => {
    const nbButtonsBeforeDelete = await lookupTypesComponentsPage.countDeleteButtons();
    await lookupTypesComponentsPage.clickOnLastDeleteButton();

    lookupTypesDeleteDialog = new LookupTypesDeleteDialog();
    expect(await lookupTypesDeleteDialog.getDialogTitle()).to.eq('revalApp.lookupTypes.delete.question');
    await lookupTypesDeleteDialog.clickOnConfirmButton();

    expect(await lookupTypesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
