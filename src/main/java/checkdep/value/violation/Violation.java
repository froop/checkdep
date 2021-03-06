package checkdep.value.violation;

import checkdep.value.depend.DependArrow;
import checkdep.value.depend.PackageName;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Violation implements Comparable<Violation> {

  public static Violation of(@NonNull PackageName from, @NonNull PackageName to) {
    return of(DependArrow.of(from, to));
  }

  @Delegate
  private final DependArrow delegate;

  @Override
  public int compareTo(@NonNull Violation other) {
    return delegate.compareTo(other.delegate);
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
