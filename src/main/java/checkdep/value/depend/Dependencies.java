package checkdep.value.depend;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import checkdep.util.MapBase;

public class Dependencies extends MapBase<PackageName, Dependency> {

  public static Dependencies of(Collection<Dependency> list) {
    Map<PackageName, Dependency> map = new LinkedHashMap<PackageName, Dependency>();
    for (Dependency item : list) {
      map.put(item.getName(), item);
    }
    return new Dependencies(map);
  }

  public Dependencies(Map<PackageName, Dependency> map) {
    super(map);
  }
}
