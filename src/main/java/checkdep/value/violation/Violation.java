package checkdep.value.violation;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;

public class Violation extends DependArrow {

  public Violation(PackageName from, PackageName to) {
    super(from, to);
  }
}
