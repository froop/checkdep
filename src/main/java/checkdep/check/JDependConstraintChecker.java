package checkdep.check;

import static java.util.stream.Collectors.*;

import java.util.Set;

import jdepend.framework.DependencyConstraint;
import checkdep.common.JDependDependency;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;
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
        .flatMap(item -> checkEfferents(item,
            expectDeps.find(item.getName())).stream())
        .sorted()
        .collect(toSet());
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependencies expects) {
    if (expects.isEmpty()) {
      return checkEfferents(actual, Dependency.NULL);
    } else {
      return expects.values().stream()
          .flatMap(item -> checkEfferents(actual, item).stream())
          .collect(toSet());
    }
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependency expect) {
    PackageNames expectPackages = expect.getEfferents();
    return actual.getEfferents().stream()
        .filter(item -> !expectPackages.contains(item))
        .map(item -> new Violation(actual.getName(), item))
        .collect(toSet());
  }

  private DependencyConstraint createJDependConstraint() {
    DependencyConstraint constraint = new DependencyConstraint();
    constraints.forEach(item -> {
      PackageName from = item.getFrom();
      PackageName to = item.getTo();
      constraint.addPackage(from.getValue()).dependsUpon(
          constraint.addPackage(to.getValue()));
    });
    return constraint;
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(DependencyConstraint constraint) {
    return JDependDependency.toDependencies(constraint.getPackages());
  }
}
