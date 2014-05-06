package checkdep.value.violation;

import checkdep.util.ReadOnlySet;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Violations implements Iterable<Violation> {

  public static Violations copyOf(Iterable<Violation> raw) {
    return of(ReadOnlySet.copyOf(raw));
  }

  @Delegate
  @NonNull
  private final ReadOnlySet<Violation> delegate;
}
