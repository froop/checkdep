package checkdep.value.violation;

import checkdep.util.ValueBase;
import checkdep.value.depend.PackageName;

public class Violation extends ValueBase {
  private final PackageName from;
  private final PackageName to;

  public Violation(PackageName from, PackageName to) {
    this.from = from;
    this.to = to;
  }

  public PackageName getFrom() {
    return from;
  }

  public PackageName getTo() {
    return to;
  }

  @Override
  public String toString() {
    return from + " -> " + to;
  }
}
