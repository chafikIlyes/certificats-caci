package caci.certificats.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ChargeExport.
 */
@Entity
@Table(name = "charge_export")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChargeExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "fonction", nullable = false)
    private String fonction;

    @NotNull
    @Column(name = "tel", nullable = false)
    private String tel;

    @NotNull
    @Column(name = "fax", nullable = false)
    private String fax;

    @NotNull
    @Column(name = "mobile", nullable = false)
    private String mobile;

    
    @Lob
    @Column(name = "signature", nullable = false)
    private byte[] signature;

    @Column(name = "signature_content_type", nullable = false)
    private String signatureContentType;

    
    @Lob
    @Column(name = "cachet", nullable = false)
    private byte[] cachet;

    @Column(name = "cachet_content_type", nullable = false)
    private String cachetContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public ChargeExport nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public ChargeExport prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFonction() {
        return fonction;
    }

    public ChargeExport fonction(String fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getTel() {
        return tel;
    }

    public ChargeExport tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public ChargeExport fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public ChargeExport mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public byte[] getSignature() {
        return signature;
    }

    public ChargeExport signature(byte[] signature) {
        this.signature = signature;
        return this;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getSignatureContentType() {
        return signatureContentType;
    }

    public ChargeExport signatureContentType(String signatureContentType) {
        this.signatureContentType = signatureContentType;
        return this;
    }

    public void setSignatureContentType(String signatureContentType) {
        this.signatureContentType = signatureContentType;
    }

    public byte[] getCachet() {
        return cachet;
    }

    public ChargeExport cachet(byte[] cachet) {
        this.cachet = cachet;
        return this;
    }

    public void setCachet(byte[] cachet) {
        this.cachet = cachet;
    }

    public String getCachetContentType() {
        return cachetContentType;
    }

    public ChargeExport cachetContentType(String cachetContentType) {
        this.cachetContentType = cachetContentType;
        return this;
    }

    public void setCachetContentType(String cachetContentType) {
        this.cachetContentType = cachetContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChargeExport)) {
            return false;
        }
        return id != null && id.equals(((ChargeExport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChargeExport{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", fonction='" + getFonction() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", signature='" + getSignature() + "'" +
            ", signatureContentType='" + getSignatureContentType() + "'" +
            ", cachet='" + getCachet() + "'" +
            ", cachetContentType='" + getCachetContentType() + "'" +
            "}";
    }
}
