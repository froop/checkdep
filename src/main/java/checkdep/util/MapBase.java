package checkdep.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class MapBase<K, V> extends ValueBase {
  private final ImmutableMap<K, V> map;

  public MapBase(Map<K, V> map) {
    this.map = new ImmutableMap.Builder<K, V>().putAll(map).build();
  }

  public V get(K key) {
    return map.get(key);
  }
}
