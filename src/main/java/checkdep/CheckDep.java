package checkdep;

import checkdep.check.ConstraintChecker;
import checkdep.parse.ImportParser;
import checkdep.value.violation.Violations;

public class CheckDep {
  private final ImportParser parser;
  private final ConstraintChecker checker;

  public CheckDep(ImportParser parser, ConstraintChecker checker) {
    this.parser = parser;
    this.checker = checker;
  }

  public Violations execute() {
    return checker.check(parser.parse());
  }
}
