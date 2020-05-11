import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TransactionsComponentsPage, TransactionsDeleteDialog, TransactionsUpdatePage } from './transactions.page-object';

const expect = chai.expect;

describe('Transactions e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transactionsComponentsPage: TransactionsComponentsPage;
  let transactionsUpdatePage: TransactionsUpdatePage;
  let transactionsDeleteDialog: TransactionsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Transactions', async () => {
    await navBarPage.goToEntity('transactions');
    transactionsComponentsPage = new TransactionsComponentsPage();
    await browser.wait(ec.visibilityOf(transactionsComponentsPage.title), 5000);
    expect(await transactionsComponentsPage.getTitle()).to.eq('revalApp.transactions.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(transactionsComponentsPage.entities), ec.visibilityOf(transactionsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Transactions page', async () => {
    await transactionsComponentsPage.clickOnCreateButton();
    transactionsUpdatePage = new TransactionsUpdatePage();
    expect(await transactionsUpdatePage.getPageTitle()).to.eq('revalApp.transactions.home.createOrEditLabel');
    await transactionsUpdatePage.cancel();
  });

  it('should create and save Transactions', async () => {
    const nbButtonsBeforeCreate = await transactionsComponentsPage.countDeleteButtons();

    await transactionsComponentsPage.clickOnCreateButton();

    await promise.all([
      transactionsUpdatePage.setTxnEntityIdInput('5'),
      transactionsUpdatePage.setTxnEntityNbrInput('txnEntityNbr'),
      transactionsUpdatePage.setTxnTcdCodeInput('txnTcdCode'),
      transactionsUpdatePage.setTxnPostDtInput('2000-12-31'),
      transactionsUpdatePage.setTxnStatusCdInput('txnStatusCd')
    ]);

    expect(await transactionsUpdatePage.getTxnEntityIdInput()).to.eq('5', 'Expected txnEntityId value to be equals to 5');
    expect(await transactionsUpdatePage.getTxnEntityNbrInput()).to.eq(
      'txnEntityNbr',
      'Expected TxnEntityNbr value to be equals to txnEntityNbr'
    );
    expect(await transactionsUpdatePage.getTxnTcdCodeInput()).to.eq('txnTcdCode', 'Expected TxnTcdCode value to be equals to txnTcdCode');
    expect(await transactionsUpdatePage.getTxnPostDtInput()).to.eq('2000-12-31', 'Expected txnPostDt value to be equals to 2000-12-31');
    expect(await transactionsUpdatePage.getTxnStatusCdInput()).to.eq(
      'txnStatusCd',
      'Expected TxnStatusCd value to be equals to txnStatusCd'
    );

    await transactionsUpdatePage.save();
    expect(await transactionsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await transactionsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Transactions', async () => {
    const nbButtonsBeforeDelete = await transactionsComponentsPage.countDeleteButtons();
    await transactionsComponentsPage.clickOnLastDeleteButton();

    transactionsDeleteDialog = new TransactionsDeleteDialog();
    expect(await transactionsDeleteDialog.getDialogTitle()).to.eq('revalApp.transactions.delete.question');
    await transactionsDeleteDialog.clickOnConfirmButton();

    expect(await transactionsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
