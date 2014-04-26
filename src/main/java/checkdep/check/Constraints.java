package checkdep.check;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import checkdep.util.CollectionBase;

public class Constraints extends CollectionBase<Constraint> {

  public static Builder builder() {
    return new Constraints.Builder();
  }

  public static class Builder {
    private final Set<Constraint> set = new LinkedHashSet<Constraint>();

    public Builder add(String from, String to) {
      set.add(new Constraint(from, to));
      return this;
    }

    public Constraints build() {
      return new Constraints(set);
    }
  }

  private Constraints(Collection<Constraint> collection) {
    super(collection);
  }
}
