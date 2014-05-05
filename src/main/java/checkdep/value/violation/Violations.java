package checkdep.value.violation;

import checkdep.util.ImmutableSetBase;

public final class Violations extends ImmutableSetBase<Violation> {

  public static Violations of(Iterable<Violation> raw) {
    return new Violations(raw);
  }

  private Violations(Iterable<Violation> raw) {
    super(raw);
  }
}
