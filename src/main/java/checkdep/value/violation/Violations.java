package checkdep.value.violation;

import checkdep.util.CollectionBase;

public class Violations extends CollectionBase<Violation> {

  public Violations(Iterable<Violation> set) {
    super(set);
  }
}
