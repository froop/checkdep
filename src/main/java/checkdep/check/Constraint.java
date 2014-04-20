package checkdep.check;

import checkdep.value.depend.PackageName;

public class Constraint {
  private final PackageName from;
  private final PackageName to;

  public Constraint(PackageName from, PackageName to) {
    this.from = from;
    this.to = to;
  }

  public Constraint(String from, String to) {
    this(new PackageName(from), new PackageName(to));
  }

  public PackageName getFrom() {
    return from;
  }

  public PackageName getTo() {
    return to;
  }
}
