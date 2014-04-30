package checkdep.value.constraint;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import checkdep.util.CollectionBase;
import checkdep.value.depend.Dependencies;
import checkdep.value.depend.Dependency;
import checkdep.value.depend.PackageName;
import checkdep.value.depend.PackageNames;

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
//  stream().reduce(HashMultimap.create(), (map, item) -> map.put(item.getFrom(), item.getTo()), Map::putAll);
    Multimap<PackageName, PackageName> map = HashMultimap.create();
    for (Constraint item : this) {
      map.put(item.getFrom(), item.getTo());
    }
    return Dependencies.of(map.asMap().entrySet().stream()
        .map(item -> new DependencyImpl(item.getKey(), item.getValue()))
        .collect(toList()));
  }

  private static class DependencyImpl implements Dependency {
    private final PackageName name;
    private final PackageNames efferents;

    public DependencyImpl(PackageName name, Collection<PackageName> efferents) {
      this.name = name;
      this.efferents = new PackageNames(efferents);
    }

    @Override
    public PackageName getName() {
      return name;
    }

    @Override
    public PackageNames getEfferents() {
      return efferents;
    }
  }
}
