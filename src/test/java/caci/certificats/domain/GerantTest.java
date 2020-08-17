package caci.certificats.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import caci.certificats.web.rest.TestUtil;

public class GerantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gerant.class);
        Gerant gerant1 = new Gerant();
        gerant1.setId(1L);
        Gerant gerant2 = new Gerant();
        gerant2.setId(gerant1.getId());
        assertThat(gerant1).isEqualTo(gerant2);
        gerant2.setId(2L);
        assertThat(gerant1).isNotEqualTo(gerant2);
        gerant1.setId(null);
        assertThat(gerant1).isNotEqualTo(gerant2);
    }
}
