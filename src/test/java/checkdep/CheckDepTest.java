package checkdep;

import static org.junit.Assert.*;

import org.junit.Test;

import checkdep.value.constraint.Constraints;
import checkdep.value.exclude.ExcludePackages;
import checkdep.value.source.SourceDirectories;
import checkdep.value.violation.Violations;

public class CheckDepTest {

  @Test
  public void testExecute_Full() {
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
            .add("checkdep", "checkdep.parse")
            .add("checkdep", "checkdep.value.constraint")
            .add("checkdep", "checkdep.value.exclude")
            .add("checkdep", "checkdep.value.source")
            .add("checkdep", "checkdep.value.violation")
            .add("checkdep.check", "checkdep.value.constraint")
            .add("checkdep.check", "checkdep.value.depend")
            .add("checkdep.check", "checkdep.value.violation")
            .add("checkdep.common", "checkdep.value.depend")
            .add("checkdep.common", "jdepend.framework")
            .add("checkdep.parse", "checkdep.common")
            .add("checkdep.parse", "checkdep.value.depend")
            .add("checkdep.parse", "checkdep.value.exclude")
            .add("checkdep.parse", "checkdep.value.source")
            .add("checkdep.parse", "java.io")
            .add("checkdep.parse", "jdepend.framework")
            .add("checkdep.value.constraint", "checkdep.value.depend")
            .add("checkdep.value.violation", "checkdep.value.depend")
            .build());

    assertTrue(res.toString(), res.isEmpty());
  }

  @Test
  public void testExecute_Wildcard() {
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
            .add("checkdep", "checkdep.check.*")
            .add("checkdep", "checkdep.parse.*")
            .add("checkdep", "checkdep.value.*")
            .add("checkdep.*", "checkdep.common.*")
            .add("checkdep.check.*", "checkdep.value.*")
            .add("checkdep.common.*", "checkdep.value.*")
            .add("checkdep.common.*", "jdepend.framework")
            .add("checkdep.parse.*", "checkdep.value.*")
            .add("checkdep.parse.*", "java.io")
            .add("checkdep.parse.*", "jdepend.framework")
            .add("checkdep.value.*", "checkdep.value.*")
            .build());

    assertTrue(res.toString(), res.isEmpty());
  }
}
