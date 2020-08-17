package caci.certificats;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("caci.certificats");

        noClasses()
            .that()
            .resideInAnyPackage("caci.certificats.service..")
            .or()
            .resideInAnyPackage("caci.certificats.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..caci.certificats.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
