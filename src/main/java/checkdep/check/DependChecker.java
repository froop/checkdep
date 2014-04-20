package checkdep.check;

import checkdep.value.depend.Dependencies;
import checkdep.value.violation.Violations;

public interface DependChecker {

  Violations check(Dependencies dependencies);
}
