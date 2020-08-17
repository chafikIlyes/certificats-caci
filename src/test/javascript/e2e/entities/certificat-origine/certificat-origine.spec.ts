import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CertificatOrigineComponentsPage,
  CertificatOrigineDeleteDialog,
  CertificatOrigineUpdatePage,
} from './certificat-origine.page-object';

const expect = chai.expect;

describe('CertificatOrigine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let certificatOrigineComponentsPage: CertificatOrigineComponentsPage;
  let certificatOrigineUpdatePage: CertificatOrigineUpdatePage;
  let certificatOrigineDeleteDialog: CertificatOrigineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CertificatOrigines', async () => {
    await navBarPage.goToEntity('certificat-origine');
    certificatOrigineComponentsPage = new CertificatOrigineComponentsPage();
    await browser.wait(ec.visibilityOf(certificatOrigineComponentsPage.title), 5000);
    expect(await certificatOrigineComponentsPage.getTitle()).to.eq('certificatcaciApp.certificatOrigine.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(certificatOrigineComponentsPage.entities), ec.visibilityOf(certificatOrigineComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CertificatOrigine page', async () => {
    await certificatOrigineComponentsPage.clickOnCreateButton();
    certificatOrigineUpdatePage = new CertificatOrigineUpdatePage();
    expect(await certificatOrigineUpdatePage.getPageTitle()).to.eq('certificatcaciApp.certificatOrigine.home.createOrEditLabel');
    await certificatOrigineUpdatePage.cancel();
  });

  it('should create and save CertificatOrigines', async () => {
    const nbButtonsBeforeCreate = await certificatOrigineComponentsPage.countDeleteButtons();

    await certificatOrigineComponentsPage.clickOnCreateButton();

    await promise.all([
      certificatOrigineUpdatePage.setNomExportateurInput('nomExportateur'),
      certificatOrigineUpdatePage.setAdresseExportateurInput('adresseExportateur'),
      certificatOrigineUpdatePage.setNomProducteurInput('nomProducteur'),
      certificatOrigineUpdatePage.setAdresseProducteurInput('adresseProducteur'),
      certificatOrigineUpdatePage.setNomImportateurInput('nomImportateur'),
      certificatOrigineUpdatePage.setAdresseImportateurInput('adresseImportateur'),
      certificatOrigineUpdatePage.setPayOrigineInput('payOrigine'),
      certificatOrigineUpdatePage.setPayAutreOrigineInput('payAutreOrigine'),
      certificatOrigineUpdatePage.setDetailTransportInput('detailTransport'),
      certificatOrigineUpdatePage.setObservationInput('observation'),
      certificatOrigineUpdatePage.setEtatCertificatInput('etatCertificat'),
      certificatOrigineUpdatePage.setTypeCertificatsInput('typeCertificats'),
      certificatOrigineUpdatePage.setNomSignataireInput('nomSignataire'),
      certificatOrigineUpdatePage.setPrenomSignataireInput('prenomSignataire'),
      certificatOrigineUpdatePage.setEmailSignataireInput('emailSignataire'),
      certificatOrigineUpdatePage.importateurSelectLastOption(),
      certificatOrigineUpdatePage.entrepriseSelectLastOption(),
    ]);

    expect(await certificatOrigineUpdatePage.getNomExportateurInput()).to.eq(
      'nomExportateur',
      'Expected NomExportateur value to be equals to nomExportateur'
    );
    expect(await certificatOrigineUpdatePage.getAdresseExportateurInput()).to.eq(
      'adresseExportateur',
      'Expected AdresseExportateur value to be equals to adresseExportateur'
    );
    expect(await certificatOrigineUpdatePage.getNomProducteurInput()).to.eq(
      'nomProducteur',
      'Expected NomProducteur value to be equals to nomProducteur'
    );
    expect(await certificatOrigineUpdatePage.getAdresseProducteurInput()).to.eq(
      'adresseProducteur',
      'Expected AdresseProducteur value to be equals to adresseProducteur'
    );
    expect(await certificatOrigineUpdatePage.getNomImportateurInput()).to.eq(
      'nomImportateur',
      'Expected NomImportateur value to be equals to nomImportateur'
    );
    expect(await certificatOrigineUpdatePage.getAdresseImportateurInput()).to.eq(
      'adresseImportateur',
      'Expected AdresseImportateur value to be equals to adresseImportateur'
    );
    expect(await certificatOrigineUpdatePage.getPayOrigineInput()).to.eq(
      'payOrigine',
      'Expected PayOrigine value to be equals to payOrigine'
    );
    const selectedAutreOrigine = certificatOrigineUpdatePage.getAutreOrigineInput();
    if (await selectedAutreOrigine.isSelected()) {
      await certificatOrigineUpdatePage.getAutreOrigineInput().click();
      expect(await certificatOrigineUpdatePage.getAutreOrigineInput().isSelected(), 'Expected autreOrigine not to be selected').to.be.false;
    } else {
      await certificatOrigineUpdatePage.getAutreOrigineInput().click();
      expect(await certificatOrigineUpdatePage.getAutreOrigineInput().isSelected(), 'Expected autreOrigine to be selected').to.be.true;
    }
    expect(await certificatOrigineUpdatePage.getPayAutreOrigineInput()).to.eq(
      'payAutreOrigine',
      'Expected PayAutreOrigine value to be equals to payAutreOrigine'
    );
    expect(await certificatOrigineUpdatePage.getDetailTransportInput()).to.eq(
      'detailTransport',
      'Expected DetailTransport value to be equals to detailTransport'
    );
    expect(await certificatOrigineUpdatePage.getObservationInput()).to.eq(
      'observation',
      'Expected Observation value to be equals to observation'
    );
    expect(await certificatOrigineUpdatePage.getEtatCertificatInput()).to.eq(
      'etatCertificat',
      'Expected EtatCertificat value to be equals to etatCertificat'
    );
    expect(await certificatOrigineUpdatePage.getTypeCertificatsInput()).to.eq(
      'typeCertificats',
      'Expected TypeCertificats value to be equals to typeCertificats'
    );
    expect(await certificatOrigineUpdatePage.getNomSignataireInput()).to.eq(
      'nomSignataire',
      'Expected NomSignataire value to be equals to nomSignataire'
    );
    expect(await certificatOrigineUpdatePage.getPrenomSignataireInput()).to.eq(
      'prenomSignataire',
      'Expected PrenomSignataire value to be equals to prenomSignataire'
    );
    expect(await certificatOrigineUpdatePage.getEmailSignataireInput()).to.eq(
      'emailSignataire',
      'Expected EmailSignataire value to be equals to emailSignataire'
    );

    await certificatOrigineUpdatePage.save();
    expect(await certificatOrigineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await certificatOrigineComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CertificatOrigine', async () => {
    const nbButtonsBeforeDelete = await certificatOrigineComponentsPage.countDeleteButtons();
    await certificatOrigineComponentsPage.clickOnLastDeleteButton();

    certificatOrigineDeleteDialog = new CertificatOrigineDeleteDialog();
    expect(await certificatOrigineDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.certificatOrigine.delete.question');
    await certificatOrigineDeleteDialog.clickOnConfirmButton();

    expect(await certificatOrigineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
