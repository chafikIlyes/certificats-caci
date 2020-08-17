import { element, by, ElementFinder } from 'protractor';

export class EntrepriseComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-entreprise div table .btn-danger'));
  title = element.all(by.css('jhi-entreprise div h2#page-heading span')).first();
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

export class EntrepriseUpdatePage {
  pageTitle = element(by.id('jhi-entreprise-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  raisonSocialInput = element(by.id('field_raisonSocial'));
  formeJuridiqueInput = element(by.id('field_formeJuridique'));
  secterActiviteInput = element(by.id('field_secterActivite'));
  rcInput = element(by.id('file_rc'));
  nifInput = element(by.id('file_nif'));
  nisInput = element(by.id('file_nis'));
  codeActiviteInput = element(by.id('field_codeActivite'));
  codeActiviteExportInput = element(by.id('field_codeActiviteExport'));
  mobileInput = element(by.id('field_mobile'));
  telFixInput = element(by.id('field_telFix'));
  emailInput = element(by.id('field_email'));
  siteWebInput = element(by.id('field_siteWeb'));
  adresseInput = element(by.id('field_adresse'));
  typeExportateurInput = element(by.id('field_typeExportateur'));
  faxInput = element(by.id('field_fax'));
  soldeCertifInput = element(by.id('field_soldeCertif'));

  gerantSelect = element(by.id('field_gerant'));
  chargeExportSelect = element(by.id('field_chargeExport'));
  userSelect = element(by.id('field_user'));

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

  async setRcInput(rc: string): Promise<void> {
    await this.rcInput.sendKeys(rc);
  }

  async getRcInput(): Promise<string> {
    return await this.rcInput.getAttribute('value');
  }

  async setNifInput(nif: string): Promise<void> {
    await this.nifInput.sendKeys(nif);
  }

  async getNifInput(): Promise<string> {
    return await this.nifInput.getAttribute('value');
  }

  async setNisInput(nis: string): Promise<void> {
    await this.nisInput.sendKeys(nis);
  }

  async getNisInput(): Promise<string> {
    return await this.nisInput.getAttribute('value');
  }

  async setCodeActiviteInput(codeActivite: string): Promise<void> {
    await this.codeActiviteInput.sendKeys(codeActivite);
  }

  async getCodeActiviteInput(): Promise<string> {
    return await this.codeActiviteInput.getAttribute('value');
  }

  async setCodeActiviteExportInput(codeActiviteExport: string): Promise<void> {
    await this.codeActiviteExportInput.sendKeys(codeActiviteExport);
  }

  async getCodeActiviteExportInput(): Promise<string> {
    return await this.codeActiviteExportInput.getAttribute('value');
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

  async setTypeExportateurInput(typeExportateur: string): Promise<void> {
    await this.typeExportateurInput.sendKeys(typeExportateur);
  }

  async getTypeExportateurInput(): Promise<string> {
    return await this.typeExportateurInput.getAttribute('value');
  }

  async setFaxInput(fax: string): Promise<void> {
    await this.faxInput.sendKeys(fax);
  }

  async getFaxInput(): Promise<string> {
    return await this.faxInput.getAttribute('value');
  }

  async setSoldeCertifInput(soldeCertif: string): Promise<void> {
    await this.soldeCertifInput.sendKeys(soldeCertif);
  }

  async getSoldeCertifInput(): Promise<string> {
    return await this.soldeCertifInput.getAttribute('value');
  }

  async gerantSelectLastOption(): Promise<void> {
    await this.gerantSelect.all(by.tagName('option')).last().click();
  }

  async gerantSelectOption(option: string): Promise<void> {
    await this.gerantSelect.sendKeys(option);
  }

  getGerantSelect(): ElementFinder {
    return this.gerantSelect;
  }

  async getGerantSelectedOption(): Promise<string> {
    return await this.gerantSelect.element(by.css('option:checked')).getText();
  }

  async chargeExportSelectLastOption(): Promise<void> {
    await this.chargeExportSelect.all(by.tagName('option')).last().click();
  }

  async chargeExportSelectOption(option: string): Promise<void> {
    await this.chargeExportSelect.sendKeys(option);
  }

  getChargeExportSelect(): ElementFinder {
    return this.chargeExportSelect;
  }

  async getChargeExportSelectedOption(): Promise<string> {
    return await this.chargeExportSelect.element(by.css('option:checked')).getText();
  }

  async userSelectLastOption(): Promise<void> {
    await this.userSelect.all(by.tagName('option')).last().click();
  }

  async userSelectOption(option: string): Promise<void> {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption(): Promise<string> {
    return await this.userSelect.element(by.css('option:checked')).getText();
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

export class EntrepriseDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-entreprise-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-entreprise'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
