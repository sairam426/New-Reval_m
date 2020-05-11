import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  TransactionDetailsComponentsPage,
  TransactionDetailsDeleteDialog,
  TransactionDetailsUpdatePage
} from './transaction-details.page-object';

const expect = chai.expect;

describe('TransactionDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transactionDetailsComponentsPage: TransactionDetailsComponentsPage;
  let transactionDetailsUpdatePage: TransactionDetailsUpdatePage;
  let transactionDetailsDeleteDialog: TransactionDetailsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TransactionDetails', async () => {
    await navBarPage.goToEntity('transaction-details');
    transactionDetailsComponentsPage = new TransactionDetailsComponentsPage();
    await browser.wait(ec.visibilityOf(transactionDetailsComponentsPage.title), 5000);
    expect(await transactionDetailsComponentsPage.getTitle()).to.eq('revalApp.transactionDetails.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(transactionDetailsComponentsPage.entities), ec.visibilityOf(transactionDetailsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TransactionDetails page', async () => {
    await transactionDetailsComponentsPage.clickOnCreateButton();
    transactionDetailsUpdatePage = new TransactionDetailsUpdatePage();
    expect(await transactionDetailsUpdatePage.getPageTitle()).to.eq('revalApp.transactionDetails.home.createOrEditLabel');
    await transactionDetailsUpdatePage.cancel();
  });

  it('should create and save TransactionDetails', async () => {
    const nbButtonsBeforeCreate = await transactionDetailsComponentsPage.countDeleteButtons();

    await transactionDetailsComponentsPage.clickOnCreateButton();

    await promise.all([
      transactionDetailsUpdatePage.setTxdPrmCodeInput('txdPrmCode'),
      transactionDetailsUpdatePage.setTxdValueInput('txdValue'),
      transactionDetailsUpdatePage.transactionSelectLastOption()
    ]);

    expect(await transactionDetailsUpdatePage.getTxdPrmCodeInput()).to.eq(
      'txdPrmCode',
      'Expected TxdPrmCode value to be equals to txdPrmCode'
    );
    expect(await transactionDetailsUpdatePage.getTxdValueInput()).to.eq('txdValue', 'Expected TxdValue value to be equals to txdValue');

    await transactionDetailsUpdatePage.save();
    expect(await transactionDetailsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await transactionDetailsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TransactionDetails', async () => {
    const nbButtonsBeforeDelete = await transactionDetailsComponentsPage.countDeleteButtons();
    await transactionDetailsComponentsPage.clickOnLastDeleteButton();

    transactionDetailsDeleteDialog = new TransactionDetailsDeleteDialog();
    expect(await transactionDetailsDeleteDialog.getDialogTitle()).to.eq('revalApp.transactionDetails.delete.question');
    await transactionDetailsDeleteDialog.clickOnConfirmButton();

    expect(await transactionDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
