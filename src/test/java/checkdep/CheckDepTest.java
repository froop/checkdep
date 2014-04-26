package checkdep;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import checkdep.check.Constraint;
import checkdep.check.Constraints;
import checkdep.check.JDependConstraintChecker;
import checkdep.parse.Filter;
import checkdep.parse.JDependImportParser;
import checkdep.parse.SourceDirectories;
import checkdep.value.violation.Violation;
import checkdep.value.violation.Violations;

public class CheckDepTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testExecute() {
    Constraints constraints = new Constraints(Arrays.asList(
        new Constraint("checkdep", "checkdep.check"),
        new Constraint("checkdep", "checkdep.value.violation"),
        new Constraint("checkdep", "checkdep.parse"),
        new Constraint("checkdep.check", "checkdep.common"),
        new Constraint("checkdep.check", "checkdep.value.depend"),
        new Constraint("checkdep.check", "checkdep.value.violation"),
        new Constraint("checkdep.check", "com.google.common.collect"),
        new Constraint("checkdep.check", "jdepend.framework"),
        new Constraint("checkdep.common", "checkdep.value.depend"),
        new Constraint("checkdep.common", "jdepend.framework"),
        new Constraint("checkdep.parse", "checkdep.common"),
        new Constraint("checkdep.parse", "checkdep.value.depend"),
        new Constraint("checkdep.parse", "java.io"),
        new Constraint("checkdep.parse", "jdepend.framework"),
        new Constraint("checkdep.value.violation", "checkdep.value.depend")));
    CheckDep target = new CheckDep(
        new JDependImportParser(SourceDirectories.of("target/classes"), new Filter()),
        new JDependConstraintChecker(constraints));

    Violations res = target.execute();

    Iterator<Violation> it = res.iterator();
    assertFalse(it.hasNext());
  }
}
