package checkdep.value.depend;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import checkdep.util.MapBase;

public class Dependencies extends MapBase<PackageName, Dependency> {

  public static Dependencies of(Collection<Dependency> collection) {
    Map<PackageName, Dependency> map = new LinkedHashMap<>();
    collection.forEach(item -> map.put(item.getName(), item));
    return new Dependencies(map);
  }

  private Dependencies(Map<PackageName, Dependency> map) {
    super(map);
  }
}
