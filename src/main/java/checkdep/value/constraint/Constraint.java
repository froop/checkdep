package checkdep.value.constraint;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;

public class Constraint extends DependArrow {

  public Constraint(PackageName from, PackageName to) {
    super(from, to);
  }

  public Constraint(String from, String to) {
    super(from, to);
  }
}
