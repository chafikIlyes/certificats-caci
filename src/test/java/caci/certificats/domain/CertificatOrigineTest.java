package caci.certificats.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import caci.certificats.web.rest.TestUtil;

public class CertificatOrigineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificatOrigine.class);
        CertificatOrigine certificatOrigine1 = new CertificatOrigine();
        certificatOrigine1.setId(1L);
        CertificatOrigine certificatOrigine2 = new CertificatOrigine();
        certificatOrigine2.setId(certificatOrigine1.getId());
        assertThat(certificatOrigine1).isEqualTo(certificatOrigine2);
        certificatOrigine2.setId(2L);
        assertThat(certificatOrigine1).isNotEqualTo(certificatOrigine2);
        certificatOrigine1.setId(null);
        assertThat(certificatOrigine1).isNotEqualTo(certificatOrigine2);
    }
}
