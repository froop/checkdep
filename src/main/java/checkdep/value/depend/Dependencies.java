package checkdep.value.depend;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Map;

import checkdep.util.ReadOnlyMap;
import com.google.common.collect.Maps;
import lombok.Delegate;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Dependencies {

  public static Dependencies of(Collection<Dependency> collection) {
//    return toDependencies(collection.stream().collect(toMap(Dependency::getName, item -> item)));
    return toDependencies(Maps.uniqueIndex(collection, Dependency::getName));
  }

  private static Dependencies toDependencies(Map<PackageName, Dependency> map) {
    return of(ReadOnlyMap.copyOf(map));
  }

  public static Dependencies of(Map<PackageName, PackageNames> map) {
    return of(map.entrySet().stream()
        .map(item -> Dependency.of(item.getKey(), item.getValue()))
        .collect(toSet()));
  }

  @Delegate
  @NonNull
  private final ReadOnlyMap<PackageName, Dependency> delegate;

  public Dependencies find(PackageName name) {
    return of(values().stream()
        .filter(item -> name.matches(item.getName()))
        .collect(toSet()));
  }
}
