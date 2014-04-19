package checkdep.check;

import checkdep.value.graph.DependGraph;
import checkdep.value.violation.Violations;

public interface DependChecker {

  Violations check(DependGraph graph);
}
