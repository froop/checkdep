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
    Set<Violation> res = new TreeSet<Violation>();
    Dependencies constraintDeps = toDependencies(createJDependConstraint());
    for (Dependency actual : dependencies.values()) {
      Optional<Dependency> expect = constraintDeps.get(actual.getName());
      if (expect.isPresent()) {
        res.addAll(checkEfferents(actual, expect.get()));
      } else {
        res.addAll(toViolations(actual));
      }
    }
    return new Violations(res);
  }

  private Set<Violation> checkEfferents(Dependency actual, Dependency expect) {
    Set<Violation> res = new LinkedHashSet<Violation>();
    for (PackageName efferent : actual.getEfferents()) {
      if (!expect.getEfferents().contains(efferent)) {
        res.add(new Violation(actual.getName(), efferent));
      }
    }
    return res;
  }

  private Set<Violation> toViolations(Dependency item) {
    Set<Violation> res = new LinkedHashSet<Violation>();
    for (PackageName efferent : item.getEfferents()) {
      res.add(new Violation(item.getName(), efferent));
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
