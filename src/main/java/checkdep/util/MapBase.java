package checkdep.util;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class MapBase<K, V> extends ValueBase {
  private final ImmutableMap<K, V> map;

  protected MapBase(Map<K, V> map) {
    this.map = new ImmutableMap.Builder<K, V>().putAll(map).build();
  }

  public Optional<V> get(K key) {
    return Optional.ofNullable(map.get(key));
  }

  public ImmutableSet<K> keySet() {
    return map.keySet();
  }

  public ImmutableCollection<V> values() {
    return map.values();
  }
}
