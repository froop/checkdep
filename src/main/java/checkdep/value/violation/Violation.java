package checkdep.value.violation;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;

import com.google.common.collect.ComparisonChain;

public class Violation extends DependArrow implements Comparable<Violation> {

  public Violation(PackageName from, PackageName to) {
    super(from, to);
  }

  @Override
  public int compareTo(Violation other) {
    return ComparisonChain.start()
        .compare(getFrom(), other.getFrom())
        .compare(getTo(), other.getTo())
        .result();
  }
}
