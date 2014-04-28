package checkdep.value.depend;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import checkdep.util.MapBase;

public class Dependencies extends MapBase<PackageName, Dependency> {

  public static Dependencies of(Collection<Dependency> collection) {
    return new Dependencies(collection.stream()
        .collect(Collectors.toMap(Dependency::getName,
            Function.<Dependency>identity())));
  }

  private Dependencies(Map<PackageName, Dependency> map) {
    super(map);
  }
}
