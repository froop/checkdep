package checkdep.value.depend;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Map;

import checkdep.util.ImmutableMapBase;

public class Dependencies extends ImmutableMapBase<PackageName, Dependency> {

  public static Dependencies of(Collection<Dependency> collection) {
    return new Dependencies(collection.stream().collect(
        toMap(Dependency::getName, item -> item)));
  }

  public static Dependencies of(Map<PackageName, PackageNames> map) {
    return Dependencies.of(map.entrySet().stream()
        .map(item -> Dependency.of(item.getKey(), item.getValue()))
        .collect(toList()));
  }

  private Dependencies(Map<PackageName, Dependency> map) {
    super(map);
  }

  public Dependencies find(PackageName name) {
    return Dependencies.of(values().stream()
        .filter(item -> name.matches(item.getName()))
        .collect(toSet()));
  }
}
