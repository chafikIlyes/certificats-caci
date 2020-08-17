import { element, by, ElementFinder } from 'protractor';

export class ImportateurComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-importateur div table .btn-danger'));
  title = element.all(by.css('jhi-importateur div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ImportateurUpdatePage {
  pageTitle = element(by.id('jhi-importateur-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  raisonSocialInput = element(by.id('field_raisonSocial'));
  formeJuridiqueInput = element(by.id('field_formeJuridique'));
  secterActiviteInput = element(by.id('field_secterActivite'));
  mobileInput = element(by.id('field_mobile'));
  telFixInput = element(by.id('field_telFix'));
  emailInput = element(by.id('field_email'));
  siteWebInput = element(by.id('field_siteWeb'));
  adresseInput = element(by.id('field_adresse'));
  typeImportateurInput = element(by.id('field_typeImportateur'));
  faxInput = element(by.id('field_fax'));

  entrepriseSelect = element(by.id('field_entreprise'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRaisonSocialInput(raisonSocial: string): Promise<void> {
    await this.raisonSocialInput.sendKeys(raisonSocial);
  }

  async getRaisonSocialInput(): Promise<string> {
    return await this.raisonSocialInput.getAttribute('value');
  }

  async setFormeJuridiqueInput(formeJuridique: string): Promise<void> {
    await this.formeJuridiqueInput.sendKeys(formeJuridique);
  }

  async getFormeJuridiqueInput(): Promise<string> {
    return await this.formeJuridiqueInput.getAttribute('value');
  }

  async setSecterActiviteInput(secterActivite: string): Promise<void> {
    await this.secterActiviteInput.sendKeys(secterActivite);
  }

  async getSecterActiviteInput(): Promise<string> {
    return await this.secterActiviteInput.getAttribute('value');
  }

  async setMobileInput(mobile: string): Promise<void> {
    await this.mobileInput.sendKeys(mobile);
  }

  async getMobileInput(): Promise<string> {
    return await this.mobileInput.getAttribute('value');
  }

  async setTelFixInput(telFix: string): Promise<void> {
    await this.telFixInput.sendKeys(telFix);
  }

  async getTelFixInput(): Promise<string> {
    return await this.telFixInput.getAttribute('value');
  }

  async setEmailInput(email: string): Promise<void> {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput(): Promise<string> {
    return await this.emailInput.getAttribute('value');
  }

  async setSiteWebInput(siteWeb: string): Promise<void> {
    await this.siteWebInput.sendKeys(siteWeb);
  }

  async getSiteWebInput(): Promise<string> {
    return await this.siteWebInput.getAttribute('value');
  }

  async setAdresseInput(adresse: string): Promise<void> {
    await this.adresseInput.sendKeys(adresse);
  }

  async getAdresseInput(): Promise<string> {
    return await this.adresseInput.getAttribute('value');
  }

  async setTypeImportateurInput(typeImportateur: string): Promise<void> {
    await this.typeImportateurInput.sendKeys(typeImportateur);
  }

  async getTypeImportateurInput(): Promise<string> {
    return await this.typeImportateurInput.getAttribute('value');
  }

  async setFaxInput(fax: string): Promise<void> {
    await this.faxInput.sendKeys(fax);
  }

  async getFaxInput(): Promise<string> {
    return await this.faxInput.getAttribute('value');
  }

  async entrepriseSelectLastOption(): Promise<void> {
    await this.entrepriseSelect.all(by.tagName('option')).last().click();
  }

  async entrepriseSelectOption(option: string): Promise<void> {
    await this.entrepriseSelect.sendKeys(option);
  }

  getEntrepriseSelect(): ElementFinder {
    return this.entrepriseSelect;
  }

  async getEntrepriseSelectedOption(): Promise<string> {
    return await this.entrepriseSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ImportateurDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-importateur-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-importateur'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
