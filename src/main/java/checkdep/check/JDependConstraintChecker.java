package checkdep.check;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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
    Set<Violation> res = new TreeSet<>();
    for (Dependency actual : actualDeps.values()) {
      Optional<Dependency> expect = expectDeps.get(actual.getName());
      res.addAll(checkEfferents(actual,
          expect.isPresent() ? expect.get() : Dependency.NULL));
    }
    return res;
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependency expect) {
    Set<Violation> res = new LinkedHashSet<>();
    for (PackageName efferent : actual.getEfferents()) {
      if (!expect.getEfferents().contains(efferent)) {
        res.add(new Violation(actual.getName(), efferent));
      }
    }
    return res;
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
