package checkdep.value.constraint;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import checkdep.util.CollectionBase;
import checkdep.value.depend.DependArrow;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;

public class Constraints extends CollectionBase<Constraint> {

  public static Builder builder() {
    return new Constraints.Builder();
  }

  public static class Builder {
    private final Set<Constraint> set = new LinkedHashSet<>();

    public Builder add(String from, String to) {
      set.add(Constraint.of(from, to));
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
    // TODO: 直接Dependencies.of()に渡すとエラーになるため、一時変数に格納
    // (EclipseのJava8対応が不十分？)
    Map<PackageName, PackageNames> res = stream().collect(
        toMap(DependArrow::getFrom, item -> PackageNames.of(item.getTo()),
            (left, right) -> left.merge(right)));
//    Multimap<PackageName, PackageName> map = HashMultimap.create();
//    for (Constraint item : this) {
//      map.put(item.getFrom(), item.getTo());
//    }
    return Dependencies.of(res);
  }
}
