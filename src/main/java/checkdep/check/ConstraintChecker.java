package checkdep.check;

import checkdep.value.depend.Dependencies;
import checkdep.value.violation.Violations;

public interface ConstraintChecker {

  Violations check(Dependencies dependencies);
}
