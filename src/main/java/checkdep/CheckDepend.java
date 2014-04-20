package checkdep;

import checkdep.check.DependChecker;
import checkdep.parse.ImportParser;
import checkdep.value.violation.Violations;

public class CheckDepend {
  private final ImportParser parser;
  private final DependChecker checker;

  public CheckDepend(ImportParser parser, DependChecker checker) {
    this.parser = parser;
    this.checker = checker;
  }

  public Violations execute() {
    return checker.check(parser.parse());
  }
}
