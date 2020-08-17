import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ChargeExportComponentsPage, ChargeExportDeleteDialog, ChargeExportUpdatePage } from './charge-export.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('ChargeExport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let chargeExportComponentsPage: ChargeExportComponentsPage;
  let chargeExportUpdatePage: ChargeExportUpdatePage;
  let chargeExportDeleteDialog: ChargeExportDeleteDialog;
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ChargeExports', async () => {
    await navBarPage.goToEntity('charge-export');
    chargeExportComponentsPage = new ChargeExportComponentsPage();
    await browser.wait(ec.visibilityOf(chargeExportComponentsPage.title), 5000);
    expect(await chargeExportComponentsPage.getTitle()).to.eq('certificatcaciApp.chargeExport.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(chargeExportComponentsPage.entities), ec.visibilityOf(chargeExportComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ChargeExport page', async () => {
    await chargeExportComponentsPage.clickOnCreateButton();
    chargeExportUpdatePage = new ChargeExportUpdatePage();
    expect(await chargeExportUpdatePage.getPageTitle()).to.eq('certificatcaciApp.chargeExport.home.createOrEditLabel');
    await chargeExportUpdatePage.cancel();
  });

  it('should create and save ChargeExports', async () => {
    const nbButtonsBeforeCreate = await chargeExportComponentsPage.countDeleteButtons();

    await chargeExportComponentsPage.clickOnCreateButton();

    await promise.all([
      chargeExportUpdatePage.setNomInput('nom'),
      chargeExportUpdatePage.setPrenomInput('prenom'),
      chargeExportUpdatePage.setFonctionInput('fonction'),
      chargeExportUpdatePage.setTelInput('tel'),
      chargeExportUpdatePage.setFaxInput('fax'),
      chargeExportUpdatePage.setMobileInput('mobile'),
      chargeExportUpdatePage.setSignatureInput(absolutePath),
      chargeExportUpdatePage.setCachetInput(absolutePath),
    ]);

    expect(await chargeExportUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await chargeExportUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await chargeExportUpdatePage.getFonctionInput()).to.eq('fonction', 'Expected Fonction value to be equals to fonction');
    expect(await chargeExportUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await chargeExportUpdatePage.getFaxInput()).to.eq('fax', 'Expected Fax value to be equals to fax');
    expect(await chargeExportUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await chargeExportUpdatePage.getSignatureInput()).to.endsWith(
      fileNameToUpload,
      'Expected Signature value to be end with ' + fileNameToUpload
    );
    expect(await chargeExportUpdatePage.getCachetInput()).to.endsWith(
      fileNameToUpload,
      'Expected Cachet value to be end with ' + fileNameToUpload
    );

    await chargeExportUpdatePage.save();
    expect(await chargeExportUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await chargeExportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ChargeExport', async () => {
    const nbButtonsBeforeDelete = await chargeExportComponentsPage.countDeleteButtons();
    await chargeExportComponentsPage.clickOnLastDeleteButton();

    chargeExportDeleteDialog = new ChargeExportDeleteDialog();
    expect(await chargeExportDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.chargeExport.delete.question');
    await chargeExportDeleteDialog.clickOnConfirmButton();

    expect(await chargeExportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
