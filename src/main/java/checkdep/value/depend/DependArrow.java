package checkdep.value.depend;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class DependArrow {
  private final PackageName from;
  private final PackageName to;

  protected DependArrow(PackageName from, PackageName to) {
    this.from = from;
    this.to = to;
  }

  protected DependArrow(String from, String to) {
    this(new PackageName(from), new PackageName(to));
  }

  @Override
  public String toString() {
    return from + " -> " + to;
  }
}
