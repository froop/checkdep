package checkdep.value.violation;

import checkdep.util.MyImmutableSet;

public final class Violations extends MyImmutableSet<Violation> {

  public static Violations of(Iterable<Violation> raw) {
    return new Violations(raw);
  }

  private Violations(Iterable<Violation> raw) {
    super(raw);
  }
}
