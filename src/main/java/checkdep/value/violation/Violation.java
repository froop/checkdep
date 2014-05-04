package checkdep.value.violation;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;

public class Violation extends DependArrow {

  public static Violation of(PackageName from, PackageName to) {
    return new Violation(from, to);
  }

  private Violation(PackageName from, PackageName to) {
    super(from, to);
  }
}
