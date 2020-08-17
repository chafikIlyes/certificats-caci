import { IEntreprise } from 'app/shared/model/entreprise.model';

export interface IImportateur {
  id?: number;
  raisonSocial?: string;
  formeJuridique?: string;
  secterActivite?: string;
  mobile?: string;
  telFix?: string;
  email?: string;
  siteWeb?: string;
  adresse?: string;
  typeImportateur?: string;
  fax?: string;
  entreprise?: IEntreprise;
}

export class Importateur implements IImportateur {
  constructor(
    public id?: number,
    public raisonSocial?: string,
    public formeJuridique?: string,
    public secterActivite?: string,
    public mobile?: string,
    public telFix?: string,
    public email?: string,
    public siteWeb?: string,
    public adresse?: string,
    public typeImportateur?: string,
    public fax?: string,
    public entreprise?: IEntreprise
  ) {}
}
