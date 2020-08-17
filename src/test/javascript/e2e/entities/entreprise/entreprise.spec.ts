import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EntrepriseComponentsPage, EntrepriseDeleteDialog, EntrepriseUpdatePage } from './entreprise.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Entreprise e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entrepriseComponentsPage: EntrepriseComponentsPage;
  let entrepriseUpdatePage: EntrepriseUpdatePage;
  let entrepriseDeleteDialog: EntrepriseDeleteDialog;
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

  it('should load Entreprises', async () => {
    await navBarPage.goToEntity('entreprise');
    entrepriseComponentsPage = new EntrepriseComponentsPage();
    await browser.wait(ec.visibilityOf(entrepriseComponentsPage.title), 5000);
    expect(await entrepriseComponentsPage.getTitle()).to.eq('certificatcaciApp.entreprise.home.title');
    await browser.wait(ec.or(ec.visibilityOf(entrepriseComponentsPage.entities), ec.visibilityOf(entrepriseComponentsPage.noResult)), 1000);
  });

  it('should load create Entreprise page', async () => {
    await entrepriseComponentsPage.clickOnCreateButton();
    entrepriseUpdatePage = new EntrepriseUpdatePage();
    expect(await entrepriseUpdatePage.getPageTitle()).to.eq('certificatcaciApp.entreprise.home.createOrEditLabel');
    await entrepriseUpdatePage.cancel();
  });

  it('should create and save Entreprises', async () => {
    const nbButtonsBeforeCreate = await entrepriseComponentsPage.countDeleteButtons();

    await entrepriseComponentsPage.clickOnCreateButton();

    await promise.all([
      entrepriseUpdatePage.setRaisonSocialInput('raisonSocial'),
      entrepriseUpdatePage.setFormeJuridiqueInput('formeJuridique'),
      entrepriseUpdatePage.setSecterActiviteInput('secterActivite'),
      entrepriseUpdatePage.setRcInput(absolutePath),
      entrepriseUpdatePage.setNifInput(absolutePath),
      entrepriseUpdatePage.setNisInput(absolutePath),
      entrepriseUpdatePage.setCodeActiviteInput('codeActivite'),
      entrepriseUpdatePage.setCodeActiviteExportInput('codeActiviteExport'),
      entrepriseUpdatePage.setMobileInput('mobile'),
      entrepriseUpdatePage.setTelFixInput('telFix'),
      entrepriseUpdatePage.setEmailInput('email'),
      entrepriseUpdatePage.setSiteWebInput('siteWeb'),
      entrepriseUpdatePage.setAdresseInput('adresse'),
      entrepriseUpdatePage.setTypeExportateurInput('typeExportateur'),
      entrepriseUpdatePage.setFaxInput('fax'),
      entrepriseUpdatePage.setSoldeCertifInput('soldeCertif'),
      entrepriseUpdatePage.gerantSelectLastOption(),
      entrepriseUpdatePage.chargeExportSelectLastOption(),
      entrepriseUpdatePage.userSelectLastOption(),
    ]);

    expect(await entrepriseUpdatePage.getRaisonSocialInput()).to.eq(
      'raisonSocial',
      'Expected RaisonSocial value to be equals to raisonSocial'
    );
    expect(await entrepriseUpdatePage.getFormeJuridiqueInput()).to.eq(
      'formeJuridique',
      'Expected FormeJuridique value to be equals to formeJuridique'
    );
    expect(await entrepriseUpdatePage.getSecterActiviteInput()).to.eq(
      'secterActivite',
      'Expected SecterActivite value to be equals to secterActivite'
    );
    expect(await entrepriseUpdatePage.getRcInput()).to.endsWith(fileNameToUpload, 'Expected Rc value to be end with ' + fileNameToUpload);
    expect(await entrepriseUpdatePage.getNifInput()).to.endsWith(fileNameToUpload, 'Expected Nif value to be end with ' + fileNameToUpload);
    expect(await entrepriseUpdatePage.getNisInput()).to.endsWith(fileNameToUpload, 'Expected Nis value to be end with ' + fileNameToUpload);
    expect(await entrepriseUpdatePage.getCodeActiviteInput()).to.eq(
      'codeActivite',
      'Expected CodeActivite value to be equals to codeActivite'
    );
    expect(await entrepriseUpdatePage.getCodeActiviteExportInput()).to.eq(
      'codeActiviteExport',
      'Expected CodeActiviteExport value to be equals to codeActiviteExport'
    );
    expect(await entrepriseUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await entrepriseUpdatePage.getTelFixInput()).to.eq('telFix', 'Expected TelFix value to be equals to telFix');
    expect(await entrepriseUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await entrepriseUpdatePage.getSiteWebInput()).to.eq('siteWeb', 'Expected SiteWeb value to be equals to siteWeb');
    expect(await entrepriseUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await entrepriseUpdatePage.getTypeExportateurInput()).to.eq(
      'typeExportateur',
      'Expected TypeExportateur value to be equals to typeExportateur'
    );
    expect(await entrepriseUpdatePage.getFaxInput()).to.eq('fax', 'Expected Fax value to be equals to fax');
    expect(await entrepriseUpdatePage.getSoldeCertifInput()).to.eq('soldeCertif', 'Expected SoldeCertif value to be equals to soldeCertif');

    await entrepriseUpdatePage.save();
    expect(await entrepriseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entrepriseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Entreprise', async () => {
    const nbButtonsBeforeDelete = await entrepriseComponentsPage.countDeleteButtons();
    await entrepriseComponentsPage.clickOnLastDeleteButton();

    entrepriseDeleteDialog = new EntrepriseDeleteDialog();
    expect(await entrepriseDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.entreprise.delete.question');
    await entrepriseDeleteDialog.clickOnConfirmButton();

    expect(await entrepriseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
