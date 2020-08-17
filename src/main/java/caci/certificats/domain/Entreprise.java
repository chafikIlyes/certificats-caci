package caci.certificats.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Entreprise.
 */
@Entity
@Table(name = "entreprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "raison_social", nullable = false)
    private String raisonSocial;

    @NotNull
    @Column(name = "forme_juridique", nullable = false)
    private String formeJuridique;

    @NotNull
    @Column(name = "secter_activite", nullable = false)
    private String secterActivite;

    
    @Lob
    @Column(name = "rc", nullable = false)
    private byte[] rc;

    @Column(name = "rc_content_type", nullable = false)
    private String rcContentType;

    
    @Lob
    @Column(name = "nif", nullable = false)
    private byte[] nif;

    @Column(name = "nif_content_type", nullable = false)
    private String nifContentType;

    
    @Lob
    @Column(name = "nis", nullable = false)
    private byte[] nis;

    @Column(name = "nis_content_type", nullable = false)
    private String nisContentType;

    @NotNull
    @Column(name = "code_activite", nullable = false)
    private String codeActivite;

    @Column(name = "code_activite_export")
    private String codeActiviteExport;

    @NotNull
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @NotNull
    @Column(name = "tel_fix", nullable = false)
    private String telFix;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "site_web")
    private String siteWeb;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "type_exportateur")
    private String typeExportateur;

    @Column(name = "fax")
    private String fax;

    @Column(name = "solde_certif")
    private String soldeCertif;

    @OneToOne
    @JoinColumn(unique = true)
    private Gerant gerant;

    @OneToOne
    @JoinColumn(unique = true)
    private ChargeExport chargeExport;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "entreprise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Importateur> importateurs = new HashSet<>();

    @OneToMany(mappedBy = "entreprise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "entreprise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CertificatOrigine> certificatOrigines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public Entreprise raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public Entreprise formeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
        return this;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getSecterActivite() {
        return secterActivite;
    }

    public Entreprise secterActivite(String secterActivite) {
        this.secterActivite = secterActivite;
        return this;
    }

    public void setSecterActivite(String secterActivite) {
        this.secterActivite = secterActivite;
    }

    public byte[] getRc() {
        return rc;
    }

    public Entreprise rc(byte[] rc) {
        this.rc = rc;
        return this;
    }

    public void setRc(byte[] rc) {
        this.rc = rc;
    }

    public String getRcContentType() {
        return rcContentType;
    }

    public Entreprise rcContentType(String rcContentType) {
        this.rcContentType = rcContentType;
        return this;
    }

    public void setRcContentType(String rcContentType) {
        this.rcContentType = rcContentType;
    }

    public byte[] getNif() {
        return nif;
    }

    public Entreprise nif(byte[] nif) {
        this.nif = nif;
        return this;
    }

    public void setNif(byte[] nif) {
        this.nif = nif;
    }

    public String getNifContentType() {
        return nifContentType;
    }

    public Entreprise nifContentType(String nifContentType) {
        this.nifContentType = nifContentType;
        return this;
    }

    public void setNifContentType(String nifContentType) {
        this.nifContentType = nifContentType;
    }

    public byte[] getNis() {
        return nis;
    }

    public Entreprise nis(byte[] nis) {
        this.nis = nis;
        return this;
    }

    public void setNis(byte[] nis) {
        this.nis = nis;
    }

    public String getNisContentType() {
        return nisContentType;
    }

    public Entreprise nisContentType(String nisContentType) {
        this.nisContentType = nisContentType;
        return this;
    }

    public void setNisContentType(String nisContentType) {
        this.nisContentType = nisContentType;
    }

    public String getCodeActivite() {
        return codeActivite;
    }

    public Entreprise codeActivite(String codeActivite) {
        this.codeActivite = codeActivite;
        return this;
    }

    public void setCodeActivite(String codeActivite) {
        this.codeActivite = codeActivite;
    }

    public String getCodeActiviteExport() {
        return codeActiviteExport;
    }

    public Entreprise codeActiviteExport(String codeActiviteExport) {
        this.codeActiviteExport = codeActiviteExport;
        return this;
    }

    public void setCodeActiviteExport(String codeActiviteExport) {
        this.codeActiviteExport = codeActiviteExport;
    }

    public String getMobile() {
        return mobile;
    }

    public Entreprise mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelFix() {
        return telFix;
    }

    public Entreprise telFix(String telFix) {
        this.telFix = telFix;
        return this;
    }

    public void setTelFix(String telFix) {
        this.telFix = telFix;
    }

    public String getEmail() {
        return email;
    }

    public Entreprise email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public Entreprise siteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
        return this;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getAdresse() {
        return adresse;
    }

    public Entreprise adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeExportateur() {
        return typeExportateur;
    }

    public Entreprise typeExportateur(String typeExportateur) {
        this.typeExportateur = typeExportateur;
        return this;
    }

    public void setTypeExportateur(String typeExportateur) {
        this.typeExportateur = typeExportateur;
    }

    public String getFax() {
        return fax;
    }

    public Entreprise fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSoldeCertif() {
        return soldeCertif;
    }

    public Entreprise soldeCertif(String soldeCertif) {
        this.soldeCertif = soldeCertif;
        return this;
    }

    public void setSoldeCertif(String soldeCertif) {
        this.soldeCertif = soldeCertif;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public Entreprise gerant(Gerant gerant) {
        this.gerant = gerant;
        return this;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    public ChargeExport getChargeExport() {
        return chargeExport;
    }

    public Entreprise chargeExport(ChargeExport chargeExport) {
        this.chargeExport = chargeExport;
        return this;
    }

    public void setChargeExport(ChargeExport chargeExport) {
        this.chargeExport = chargeExport;
    }

    public User getUser() {
        return user;
    }

    public Entreprise user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Importateur> getImportateurs() {
        return importateurs;
    }

    public Entreprise importateurs(Set<Importateur> importateurs) {
        this.importateurs = importateurs;
        return this;
    }

    public Entreprise addImportateur(Importateur importateur) {
        this.importateurs.add(importateur);
        importateur.setEntreprise(this);
        return this;
    }

    public Entreprise removeImportateur(Importateur importateur) {
        this.importateurs.remove(importateur);
        importateur.setEntreprise(null);
        return this;
    }

    public void setImportateurs(Set<Importateur> importateurs) {
        this.importateurs = importateurs;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Entreprise produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Entreprise addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setEntreprise(this);
        return this;
    }

    public Entreprise removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setEntreprise(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Set<CertificatOrigine> getCertificatOrigines() {
        return certificatOrigines;
    }

    public Entreprise certificatOrigines(Set<CertificatOrigine> certificatOrigines) {
        this.certificatOrigines = certificatOrigines;
        return this;
    }

    public Entreprise addCertificatOrigine(CertificatOrigine certificatOrigine) {
        this.certificatOrigines.add(certificatOrigine);
        certificatOrigine.setEntreprise(this);
        return this;
    }

    public Entreprise removeCertificatOrigine(CertificatOrigine certificatOrigine) {
        this.certificatOrigines.remove(certificatOrigine);
        certificatOrigine.setEntreprise(null);
        return this;
    }

    public void setCertificatOrigines(Set<CertificatOrigine> certificatOrigines) {
        this.certificatOrigines = certificatOrigines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entreprise)) {
            return false;
        }
        return id != null && id.equals(((Entreprise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entreprise{" +
            "id=" + getId() +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", formeJuridique='" + getFormeJuridique() + "'" +
            ", secterActivite='" + getSecterActivite() + "'" +
            ", rc='" + getRc() + "'" +
            ", rcContentType='" + getRcContentType() + "'" +
            ", nif='" + getNif() + "'" +
            ", nifContentType='" + getNifContentType() + "'" +
            ", nis='" + getNis() + "'" +
            ", nisContentType='" + getNisContentType() + "'" +
            ", codeActivite='" + getCodeActivite() + "'" +
            ", codeActiviteExport='" + getCodeActiviteExport() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", telFix='" + getTelFix() + "'" +
            ", email='" + getEmail() + "'" +
            ", siteWeb='" + getSiteWeb() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", typeExportateur='" + getTypeExportateur() + "'" +
            ", fax='" + getFax() + "'" +
            ", soldeCertif='" + getSoldeCertif() + "'" +
            "}";
    }
}
