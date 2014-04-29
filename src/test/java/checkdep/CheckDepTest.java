package checkdep;

import static org.junit.Assert.*;

import org.junit.Test;

import checkdep.check.Constraints;
import checkdep.parse.ExcludePackages;
import checkdep.parse.SourceDirectories;
import checkdep.value.violation.Violations;

public class CheckDepTest {

  @Test
  public void testExecute() {
    Violations res = CheckDep.check(
        SourceDirectories.of(
            "target/classes"),
        ExcludePackages.of(
            "java.lang",
            "java.util",
            "org.apache.commons.lang3",
            "com.google.common.collect",
            "checkdep.util"),
        Constraints.builder()
            .add("checkdep", "checkdep.check")
            .add("checkdep", "checkdep.value.violation")
            .add("checkdep", "checkdep.parse")
            .add("checkdep.check", "checkdep.common")
            .add("checkdep.check", "checkdep.value.depend")
            .add("checkdep.check", "checkdep.value.violation")
            .add("checkdep.check", "jdepend.framework")
            .add("checkdep.common", "checkdep.value.depend")
            .add("checkdep.common", "jdepend.framework")
            .add("checkdep.parse", "checkdep.common")
            .add("checkdep.parse", "checkdep.value.depend")
            .add("checkdep.parse", "java.io")
            .add("checkdep.parse", "jdepend.framework")
            .add("checkdep.value.violation", "checkdep.value.depend")
            .build());

    assertTrue(res.toString(), res.isEmpty());
  }
}
