package checkdep.check;

import java.util.Set;
import java.util.stream.Collectors;

import jdepend.framework.DependencyConstraint;
import checkdep.common.JDependDependency;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.violation.Violation;
import checkdep.value.violation.Violations;

public class JDependConstraintChecker implements ConstraintChecker {
  private final Constraints constraints;

  public JDependConstraintChecker(Constraints constraints) {
    this.constraints = constraints;
  }

  @Override
  public Violations check(Dependencies dependencies) {
    Dependencies constraintDeps = toDependencies(createJDependConstraint());
    Set<Violation> needlessSet = check(constraintDeps, dependencies);
    if (!needlessSet.isEmpty()) {
      throw new NeedlessConstraintException(needlessSet.toString());
    }
    return new Violations(check(dependencies, constraintDeps));
  }

  private Set<Violation> check(Dependencies actualDeps, Dependencies expectDeps) {
    return actualDeps.values().stream()
        .flatMap(actual -> checkEfferents(actual,
            expectDeps.get(actual.getName()).orElse(Dependency.NULL)).stream())
        .sorted()
        .collect(Collectors.toSet());
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependency expect) {
    return actual.getEfferents().stream()
        .filter(efferent -> !expect.getEfferents().contains(efferent))
        .map(efferent -> new Violation(actual.getName(), efferent))
        .collect(Collectors.toSet());
  }

  private DependencyConstraint createJDependConstraint() {
    DependencyConstraint constraint = new DependencyConstraint();
    for (Constraint item : constraints) {
      PackageName from = item.getFrom();
      PackageName to = item.getTo();
      constraint.addPackage(from.getValue()).dependsUpon(
          constraint.addPackage(to.getValue()));
    }
    return constraint;
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(DependencyConstraint constraint) {
    return JDependDependency.toDependencies(constraint.getPackages());
  }
}
