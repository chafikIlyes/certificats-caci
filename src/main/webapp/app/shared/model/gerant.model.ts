export interface IGerant {
  id?: number;
  nom?: string;
  prenom?: string;
  email?: string;
  tel?: string;
  fax?: string;
  mobile?: string;
}

export class Gerant implements IGerant {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public email?: string,
    public tel?: string,
    public fax?: string,
    public mobile?: string
  ) {}
}
