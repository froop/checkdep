package checkdep.value.violation;

import java.util.Set;

import checkdep.util.CollectionBase;

public class Violations extends CollectionBase<Violation> {

  public Violations(Set<Violation> set) {
    super(set);
  }
}
