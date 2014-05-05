package checkdep.value.violation;

import checkdep.util.ImmutableSetBase;

public class Violations extends ImmutableSetBase<Violation> {

  public Violations(Iterable<Violation> set) {
    super(set);
  }
}
