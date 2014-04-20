package checkdep;

import checkdep.check.DependChecker;
import checkdep.graph.DependMapper;
import checkdep.parse.ImportParser;
import checkdep.value.violation.Violations;

public class CheckDepend {

  public static void main(String[] args) {
    // TODO
  }

  private final ImportParser parser;
  private final DependMapper mapper;
  private final DependChecker checker;

  public CheckDepend(ImportParser parser, DependMapper mapper,
      DependChecker checker) {
    this.parser = parser;
    this.mapper = mapper;
    this.checker = checker;
  }

  public Violations execute() {
    return checker.check(mapper.map(parser.parse()));
  }
}
