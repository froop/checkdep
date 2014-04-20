package checkdep.value.violation;

import org.apache.commons.lang3.builder.CompareToBuilder;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;

public class Violation extends DependArrow implements Comparable<Violation> {

  public Violation(PackageName from, PackageName to) {
    super(from, to);
  }

  @Override
  public int compareTo(Violation other) {
    return new CompareToBuilder()
        .append(getFrom(), other.getFrom())
        .append(getTo(), other.getTo())
        .toComparison();
  }
}
