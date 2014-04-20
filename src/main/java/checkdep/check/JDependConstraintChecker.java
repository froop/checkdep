package checkdep.check;

import java.util.ArrayList;
import java.util.List;

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
    List<Violation> res = new ArrayList<Violation>();
    Dependencies constraintDeps = toDependencies(createJDependConstraint());
    for (Dependency item : dependencies) {
      if (!constraintDeps.contains(item)) {
        res.add(new Violation(item.getName()));
      }
    }
    return new Violations(res);
  }

  private DependencyConstraint createJDependConstraint() {
    DependencyConstraint constraint = new DependencyConstraint();
    for (Constraint item : constraints) {
      PackageName afferent = item.getAfferent();
      PackageName efferent = item.getEfferent();
      constraint.addPackage(afferent.getValue()).dependsUpon(
          constraint.addPackage(efferent.getValue()));
    }
    return constraint;
  }

  @SuppressWarnings("unchecked")
  private Dependencies toDependencies(DependencyConstraint constraint) {
    return JDependDependency.toDependencies(constraint.getPackages());
  }
}
