package checkdep;

import checkdep.check.ConstraintChecker;
import checkdep.check.DefaultConstraintChecker;
import checkdep.parse.ImportParser;
import checkdep.parse.JDependImportParser;
import checkdep.value.constraint.Constraints;
import checkdep.value.exclude.ExcludePackages;
import checkdep.value.source.SourceDirectories;
import checkdep.value.violation.Violations;

public class CheckDep {

  public static Violations check(SourceDirectories directories,
      ExcludePackages packages, Constraints constraints) {
    return new CheckDep(
        new JDependImportParser(directories, packages),
        new DefaultConstraintChecker(constraints)).execute();
  }

  private final ImportParser parser;
  private final ConstraintChecker checker;

  private CheckDep(ImportParser parser, ConstraintChecker checker) {
    this.parser = parser;
    this.checker = checker;
  }

  public Violations execute() {
    return checker.check(parser.parse());
  }
}
