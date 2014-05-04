package checkdep.value.depend;

import com.google.common.collect.ComparisonChain;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class DependArrow implements Comparable<DependArrow> {
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
  public int compareTo(DependArrow other) {
    return ComparisonChain.start()
        .compare(getFrom(), other.getFrom())
        .compare(getTo(), other.getTo())
        .result();
  }

  @Override
  public String toString() {
    return from + " -> " + to;
  }
}
