package checkdep.check;

import checkdep.value.constraint.Constraints;
import checkdep.value.depend.Dependencies;
import checkdep.value.violation.Violations;
import lombok.NonNull;

public interface ConstraintChecker {

  static ConstraintChecker of(@NonNull Constraints constraints) {
    return new DefaultConstraintChecker(constraints);
  }

  Violations check(Dependencies dependencies);
}
