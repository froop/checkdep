package checkdep;

import checkdep.check.DependChecker;
import checkdep.graph.DependMapper;
import checkdep.parse.ImportParser;
import checkdep.write.ViolationWriter;

public class CheckDependMain {

  public static void main(String[] args) {
    // TODO
  }

  private final ImportParser parser;
  private final DependMapper mapper;
  private final DependChecker checker;
  private final ViolationWriter writer;

  public CheckDependMain(ImportParser parser, DependMapper mapper,
      DependChecker checker, ViolationWriter writer) {
    this.parser = parser;
    this.mapper = mapper;
    this.checker = checker;
    this.writer = writer;
  }

  public void execute() {
    writer.write(checker.check(mapper.map(parser.parse())));
  }
}
