package checkdep.value.violation;

import checkdep.util.MyImmutableSet;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Violations implements Iterable<Violation> {

  public static Violations copyOf(Iterable<Violation> raw) {
    return of(MyImmutableSet.copyOf(raw));
  }

  @Delegate
  @NonNull
  private final MyImmutableSet<Violation> delegate;
}
