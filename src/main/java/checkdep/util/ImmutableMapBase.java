package checkdep.util;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import lombok.Value;

@Value
public class ImmutableMapBase<K, V> {

  public static <K, V> ImmutableMapBase<K, V> of(ImmutableMap<K, V> map) {
    return new ImmutableMapBase<>(map);
  }

  public static <K, V> ImmutableMapBase<K, V> copyOf(Map<? extends K, ? extends V> map) {
    return of(ImmutableMap.copyOf(map));
  }

  @NonNull
  private final ImmutableMap<K, V> map;

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
  public String toString() {
    return map.toString();
  }
}
