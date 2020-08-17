package caci.certificats.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import caci.certificats.web.rest.TestUtil;

public class ImportateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Importateur.class);
        Importateur importateur1 = new Importateur();
        importateur1.setId(1L);
        Importateur importateur2 = new Importateur();
        importateur2.setId(importateur1.getId());
        assertThat(importateur1).isEqualTo(importateur2);
        importateur2.setId(2L);
        assertThat(importateur1).isNotEqualTo(importateur2);
        importateur1.setId(null);
        assertThat(importateur1).isNotEqualTo(importateur2);
    }
}
