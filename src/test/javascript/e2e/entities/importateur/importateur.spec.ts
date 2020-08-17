import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ImportateurComponentsPage, ImportateurDeleteDialog, ImportateurUpdatePage } from './importateur.page-object';

const expect = chai.expect;

describe('Importateur e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let importateurComponentsPage: ImportateurComponentsPage;
  let importateurUpdatePage: ImportateurUpdatePage;
  let importateurDeleteDialog: ImportateurDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Importateurs', async () => {
    await navBarPage.goToEntity('importateur');
    importateurComponentsPage = new ImportateurComponentsPage();
    await browser.wait(ec.visibilityOf(importateurComponentsPage.title), 5000);
    expect(await importateurComponentsPage.getTitle()).to.eq('certificatcaciApp.importateur.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(importateurComponentsPage.entities), ec.visibilityOf(importateurComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Importateur page', async () => {
    await importateurComponentsPage.clickOnCreateButton();
    importateurUpdatePage = new ImportateurUpdatePage();
    expect(await importateurUpdatePage.getPageTitle()).to.eq('certificatcaciApp.importateur.home.createOrEditLabel');
    await importateurUpdatePage.cancel();
  });

  it('should create and save Importateurs', async () => {
    const nbButtonsBeforeCreate = await importateurComponentsPage.countDeleteButtons();

    await importateurComponentsPage.clickOnCreateButton();

    await promise.all([
      importateurUpdatePage.setRaisonSocialInput('raisonSocial'),
      importateurUpdatePage.setFormeJuridiqueInput('formeJuridique'),
      importateurUpdatePage.setSecterActiviteInput('secterActivite'),
      importateurUpdatePage.setMobileInput('mobile'),
      importateurUpdatePage.setTelFixInput('telFix'),
      importateurUpdatePage.setEmailInput('email'),
      importateurUpdatePage.setSiteWebInput('siteWeb'),
      importateurUpdatePage.setAdresseInput('adresse'),
      importateurUpdatePage.setTypeImportateurInput('typeImportateur'),
      importateurUpdatePage.setFaxInput('fax'),
      importateurUpdatePage.entrepriseSelectLastOption(),
    ]);

    expect(await importateurUpdatePage.getRaisonSocialInput()).to.eq(
      'raisonSocial',
      'Expected RaisonSocial value to be equals to raisonSocial'
    );
    expect(await importateurUpdatePage.getFormeJuridiqueInput()).to.eq(
      'formeJuridique',
      'Expected FormeJuridique value to be equals to formeJuridique'
    );
    expect(await importateurUpdatePage.getSecterActiviteInput()).to.eq(
      'secterActivite',
      'Expected SecterActivite value to be equals to secterActivite'
    );
    expect(await importateurUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await importateurUpdatePage.getTelFixInput()).to.eq('telFix', 'Expected TelFix value to be equals to telFix');
    expect(await importateurUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await importateurUpdatePage.getSiteWebInput()).to.eq('siteWeb', 'Expected SiteWeb value to be equals to siteWeb');
    expect(await importateurUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await importateurUpdatePage.getTypeImportateurInput()).to.eq(
      'typeImportateur',
      'Expected TypeImportateur value to be equals to typeImportateur'
    );
    expect(await importateurUpdatePage.getFaxInput()).to.eq('fax', 'Expected Fax value to be equals to fax');

    await importateurUpdatePage.save();
    expect(await importateurUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await importateurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Importateur', async () => {
    const nbButtonsBeforeDelete = await importateurComponentsPage.countDeleteButtons();
    await importateurComponentsPage.clickOnLastDeleteButton();

    importateurDeleteDialog = new ImportateurDeleteDialog();
    expect(await importateurDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.importateur.delete.question');
    await importateurDeleteDialog.clickOnConfirmButton();

    expect(await importateurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
