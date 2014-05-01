package checkdep.check;

import static java.util.stream.Collectors.*;

import java.util.Set;

import checkdep.value.constraint.Constraints;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageNames;
import checkdep.value.violation.Violation;
import checkdep.value.violation.Violations;

public class DefaultConstraintChecker implements ConstraintChecker {
  private final Constraints constraints;

  public DefaultConstraintChecker(Constraints constraints) {
    this.constraints = constraints;
  }

  @Override
  public Violations check(Dependencies dependencies) {
    Dependencies constraintDeps = constraints.toDependencies();
    Set<Violation> needlessSet = check(constraintDeps, dependencies);
    if (!needlessSet.isEmpty()) {
      throw new NeedlessConstraintException(needlessSet.toString());
    }
    return new Violations(check(dependencies, constraintDeps));
  }

  private Set<Violation> check(Dependencies actualDeps, Dependencies expectDeps) {
    return actualDeps.values().stream()
        .flatMap(item -> checkEfferents(item,
            expectDeps.find(item.getName())).stream())
        .sorted()
        .collect(toSet());
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependencies expects) {
    PackageNames expectPackages = new PackageNames(expects.values().stream()
        .flatMap(item -> item.getEfferents().stream())
        .collect(toSet()));
    return actual.getEfferents().stream()
        .filter(item -> !expectPackages.contains(item))
        .map(item -> new Violation(actual.getName(), item))
        .collect(toSet());
  }
}