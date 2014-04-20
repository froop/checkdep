package checkdep.value.depend;

import checkdep.util.ValueBase;

public class DependArrow extends ValueBase {
  private final PackageName from;
  private final PackageName to;

  public DependArrow(PackageName from, PackageName to) {
    this.from = from;
    this.to = to;
  }

  public DependArrow(String from, String to) {
    this(new PackageName(from), new PackageName(to));
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
