package checkdep.value.violation;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;
import lombok.Delegate;
import lombok.Value;

@Value
public class Violation implements Comparable<Violation> {

  public static Violation of(PackageName from, PackageName to) {
    return new Violation(DependArrow.of(from, to));
  }

  @Delegate
  private final DependArrow delegate;

  @Override
  public int compareTo(Violation other) {
    return delegate.compareTo(other.delegate);
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
