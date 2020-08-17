package caci.certificats.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import caci.certificats.web.rest.TestUtil;

public class ChargeExportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChargeExport.class);
        ChargeExport chargeExport1 = new ChargeExport();
        chargeExport1.setId(1L);
        ChargeExport chargeExport2 = new ChargeExport();
        chargeExport2.setId(chargeExport1.getId());
        assertThat(chargeExport1).isEqualTo(chargeExport2);
        chargeExport2.setId(2L);
        assertThat(chargeExport1).isNotEqualTo(chargeExport2);
        chargeExport1.setId(null);
        assertThat(chargeExport1).isNotEqualTo(chargeExport2);
    }
}
