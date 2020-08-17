export interface IChargeExport {
  id?: number;
  nom?: string;
  prenom?: string;
  fonction?: string;
  tel?: string;
  fax?: string;
  mobile?: string;
  signatureContentType?: string;
  signature?: any;
  cachetContentType?: string;
  cachet?: any;
}

export class ChargeExport implements IChargeExport {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public fonction?: string,
    public tel?: string,
    public fax?: string,
    public mobile?: string,
    public signatureContentType?: string,
    public signature?: any,
    public cachetContentType?: string,
    public cachet?: any
  ) {}
}
