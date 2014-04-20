package checkdep;

import static org.junit.Assert.*;

import java.io.IOException;

import jdepend.framework.DependencyConstraint;
import jdepend.framework.JDepend;
import jdepend.framework.PackageFilter;

import org.junit.Test;

public class PackageDependencyTest {

  @Test
  public void test() throws IOException {
    JDepend jdepend = new JDepend();
    jdepend.addDirectory("target/classes");
    PackageFilter filter = jdepend.getFilter();
    filter.addPackage("java.lang");
    filter.addPackage("java.util");
    jdepend.analyze();

    DependencyConstraint constraint = new DependencyConstraint();
//    addDep(constraint, "checkdep", "checkdep.value.depend");
//    addDep(constraint, "checkdep", "checkdep.value.violation");
    addDep(constraint, "checkdep", "checkdep.parse");
//    addDep(constraint, "checkdep", "checkdep.check");
    addDep(constraint, "checkdep.parse", "java.io");
    addDep(constraint, "checkdep.parse", "jdepend.framework");
    addDep(constraint, "checkdep.parse", "checkdep.value.depend");
//    addDep(constraint, "checkdep.check", "checkdep.value.depend");
    addDep(constraint, "checkdep.check", "checkdep.value.violation");
    addDep(constraint, "checkdep.value.depend", "com.google.common.collect");

    assertTrue(jdepend.dependencyMatch(constraint));
  }

  void addDep(DependencyConstraint constraint, String from, String to) {
    constraint.addPackage(from).dependsUpon(constraint.addPackage(to));
  }
}
