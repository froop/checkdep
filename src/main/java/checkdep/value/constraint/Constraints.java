package checkdep.value.constraint;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import checkdep.util.CollectionBase;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class Constraints extends CollectionBase<Constraint> {

  public static Builder builder() {
    return new Constraints.Builder();
  }

  public static class Builder {
    private final Set<Constraint> set = new LinkedHashSet<>();

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

  public Dependencies toDependencies() {
//    Map<PackageName, Set<PackageName>> map = stream().collect(
//        toMap(item -> item.getFrom(), item -> Sets.newHashSet(item.getTo()),
//            (left, right) -> {
//          left.addAll(right);
//          return left;
//        }));
    Multimap<PackageName, PackageName> map = HashMultimap.create();
    for (Constraint item : this) {
      map.put(item.getFrom(), item.getTo());
    }
    return Dependencies.of(map.asMap().entrySet().stream()
        .map(item -> Dependency.of(item.getKey(), item.getValue()))
        .collect(toList()));
  }
}
