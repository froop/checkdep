package checkdep.value.violation;

import checkdep.util.ImmutableSetBase;

public final class Violations extends ImmutableSetBase<Violation> {

  public Violations(Iterable<Violation> set) {
    super(set);
  }
}
