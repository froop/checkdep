package checkdep.value.violation;

import java.util.Set;

import checkdep.util.ListBase;

public class Violations extends ListBase<Violation> {

  public Violations(Set<Violation> list) {
    super(list);
  }
}
