package checkdep.value.constraint;

import checkdep.value.depend.DependArrow;

public class Constraint extends DependArrow {

  public static Constraint of (String from, String to) {
    return new Constraint(from, to);
  }

  private Constraint(String from, String to) {
    super(from, to);
  }
}
