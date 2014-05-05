package checkdep.value.depend;

import com.google.common.collect.ComparisonChain;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class DependArrow implements Comparable<DependArrow> {

  public static DependArrow of(String from, String to) {
    return of(PackageName.of(from), PackageName.of(to));
  }

  @NonNull
  private final PackageName from;
  @NonNull
  private final PackageName to;

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
