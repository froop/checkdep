package checkdep.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

public class MapBase<K, V> extends ValueBase {
  private final ImmutableMap<K, V> map;

  protected MapBase(Map<K, V> map) {
    this.map = ImmutableMap.copyOf(map);
  }

  public Optional<V> get(K key) {
    return Optional.ofNullable(map.get(key));
  }

  public Set<K> keySet() {
    return map.keySet();
  }

  public Collection<V> values() {
    return map.values();
  }

  public Set<Map.Entry<K, V>> entrySet() {
    return map.entrySet();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }
}
