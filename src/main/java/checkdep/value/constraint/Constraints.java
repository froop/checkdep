package checkdep.value.constraint;

import static java.util.stream.Collectors.*;

import java.util.Map;

import checkdep.util.ImmutableSetBase;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;
import com.google.common.collect.ImmutableSet;

public final class Constraints extends ImmutableSetBase<Constraint> {

  public static Builder builder() {
    return new Constraints.Builder();
  }

  public static class Builder {
    private final ImmutableSet.Builder<Constraint> set = ImmutableSet.builder();

    public Builder add(String from, String to) {
      return add(Constraint.of(from, to));
    }

    public Builder add(Constraint element) {
      set.add(element);
      return this;
    }

    public Constraints build() {
      return new Constraints(set.build());
    }
  }

  private Constraints(Iterable<Constraint> collection) {
    super(collection);
  }

  public Dependencies toDependencies() {
    // TODO: 直接Dependencies.of()に渡すとエラーになるため、一時変数に格納
    // (EclipseのJava8対応が不十分？)
    Map<PackageName, PackageNames> res = stream().collect(
        toMap(Constraint::getFrom, item -> PackageNames.of(item.getTo()),
            (left, right) -> left.concat(right)));
//    Multimap<PackageName, PackageName> map = HashMultimap.create();
//    for (Constraint item : this) {
//      map.put(item.getFrom(), item.getTo());
//    }
    return Dependencies.ofPackageMap(res);
  }
}
