import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TransactionCodesComponentsPage, TransactionCodesDeleteDialog, TransactionCodesUpdatePage } from './transaction-codes.page-object';

const expect = chai.expect;

describe('TransactionCodes e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transactionCodesComponentsPage: TransactionCodesComponentsPage;
  let transactionCodesUpdatePage: TransactionCodesUpdatePage;
  let transactionCodesDeleteDialog: TransactionCodesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TransactionCodes', async () => {
    await navBarPage.goToEntity('transaction-codes');
    transactionCodesComponentsPage = new TransactionCodesComponentsPage();
    await browser.wait(ec.visibilityOf(transactionCodesComponentsPage.title), 5000);
    expect(await transactionCodesComponentsPage.getTitle()).to.eq('revalApp.transactionCodes.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(transactionCodesComponentsPage.entities), ec.visibilityOf(transactionCodesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TransactionCodes page', async () => {
    await transactionCodesComponentsPage.clickOnCreateButton();
    transactionCodesUpdatePage = new TransactionCodesUpdatePage();
    expect(await transactionCodesUpdatePage.getPageTitle()).to.eq('revalApp.transactionCodes.home.createOrEditLabel');
    await transactionCodesUpdatePage.cancel();
  });

  it('should create and save TransactionCodes', async () => {
    const nbButtonsBeforeCreate = await transactionCodesComponentsPage.countDeleteButtons();

    await transactionCodesComponentsPage.clickOnCreateButton();

    await promise.all([
      transactionCodesUpdatePage.setTcdCodeInput('tcdCode'),
      transactionCodesUpdatePage.setTcdEntityGroupCdInput('tcdEntityGroupCd'),
      transactionCodesUpdatePage.setTcdDescInput('tcdDesc')
    ]);

    expect(await transactionCodesUpdatePage.getTcdCodeInput()).to.eq('tcdCode', 'Expected TcdCode value to be equals to tcdCode');
    expect(await transactionCodesUpdatePage.getTcdEntityGroupCdInput()).to.eq(
      'tcdEntityGroupCd',
      'Expected TcdEntityGroupCd value to be equals to tcdEntityGroupCd'
    );
    expect(await transactionCodesUpdatePage.getTcdDescInput()).to.eq('tcdDesc', 'Expected TcdDesc value to be equals to tcdDesc');
    const selectedTcdEnabledInd = transactionCodesUpdatePage.getTcdEnabledIndInput();
    if (await selectedTcdEnabledInd.isSelected()) {
      await transactionCodesUpdatePage.getTcdEnabledIndInput().click();
      expect(await transactionCodesUpdatePage.getTcdEnabledIndInput().isSelected(), 'Expected tcdEnabledInd not to be selected').to.be
        .false;
    } else {
      await transactionCodesUpdatePage.getTcdEnabledIndInput().click();
      expect(await transactionCodesUpdatePage.getTcdEnabledIndInput().isSelected(), 'Expected tcdEnabledInd to be selected').to.be.true;
    }

    await transactionCodesUpdatePage.save();
    expect(await transactionCodesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await transactionCodesComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TransactionCodes', async () => {
    const nbButtonsBeforeDelete = await transactionCodesComponentsPage.countDeleteButtons();
    await transactionCodesComponentsPage.clickOnLastDeleteButton();

    transactionCodesDeleteDialog = new TransactionCodesDeleteDialog();
    expect(await transactionCodesDeleteDialog.getDialogTitle()).to.eq('revalApp.transactionCodes.delete.question');
    await transactionCodesDeleteDialog.clickOnConfirmButton();

    expect(await transactionCodesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
