package checkdep.value.violation;

import checkdep.util.ValueBase;
import checkdep.value.depend.PackageName;

public class Violation extends ValueBase {
  private final PackageName name;

  public Violation(PackageName name) {
    this.name = name;
  }

  public PackageName getName() {
    return name;
  }
}
