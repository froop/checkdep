package checkdep.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;

public class MapBase<K, V> {
  private final ImmutableMap<K, V> map;

  protected MapBase(@NonNull Map<K, V> map) {
    this.map = ImmutableMap.copyOf(map);
  }

  public Optional<V> get(@NonNull K key) {
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

  @Override
  public int hashCode() {
    return map.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    @SuppressWarnings("unchecked")
    MapBase<K, V> other = (MapBase<K, V>) obj;
    return Objects.equals(map, other.map);
  }

  @Override
  public String toString() {
    return map.toString();
  }
}
